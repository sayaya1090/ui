package net.sayaya.ui;

import elemental2.dom.HTMLElement;
import elemental2.dom.HTMLFormElement;
import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;
import jsinterop.base.Js;
import lombok.experimental.Delegate;
import net.sayaya.ui.util.ElementUtil;
import org.jboss.elemento.HtmlContent;
import org.jboss.elemento.HtmlContentBuilder;
import org.jboss.elemento.IsElement;
import org.jboss.elemento.TypedBuilder;

import static org.jboss.elemento.Elements.htmlElement;

@JsType(isNative = true, namespace = JsPackage.GLOBAL)
public final class DialogElement extends HTMLElement {
    @JsOverlay
    public static DialogElementBuilder dialog() {
        return new DialogElementBuilder();
    }
    public boolean open;

    public static class DialogElementBuilder implements HtmlContent<DialogElement, HtmlContentBuilder<DialogElement>> {
        @Delegate(excludes = { IsElement.class, TypedBuilder.class, HtmlContent.class })
        private final HtmlContentBuilder<DialogElement> delegate;
        private final HtmlContentBuilder<DialogElement> that;
        private String formId = ElementUtil.uniqueId();
        private DialogElementBuilder() {
            delegate = htmlElement("md-dialog", DialogElement.class);
            that = new HtmlContentBuilder<>(element());
        }
        public DialogElementBuilder icon(IsElement<HTMLElement> icon) {
            icon.element().slot = "icon";
            delegate.add(icon);
            return this;
        }
        public DialogElementBuilder headline(IsElement<? extends HTMLElement> div) {
            div.element().slot = "headline";
            delegate.add(div);
            return this;
        }
        public DialogElementBuilder content(IsElement<? extends HTMLElement> form) {
            var elem = form.element();
            elem.slot = "content";
            delegate.add(form);
            if(elem instanceof HTMLFormElement) {
                var cast = (HTMLFormElement)elem;
                cast.method = "dialog";
                cast.id = formId;
            }
            return this;
        }
        public DialogElementBuilder actions(IsElement<? extends HTMLElement> div) {
            div.element().slot = "actions";
            delegate.add(div);
            //for(var k in div.element().child)
            return this;
        }
        @Override
        public DialogElement element() {
            return Js.uncheckedCast(delegate.element());
        }
        @Override
        public HtmlContentBuilder<DialogElement> that() {
            return that;
        }
    }
}
