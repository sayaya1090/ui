package dev.sayaya.ui.elements;

import dev.sayaya.ui.dom.MdDialogElement;
import dev.sayaya.ui.elements.interfaces.HasAriaLabel;
import dev.sayaya.ui.elements.interfaces.HasContentSlot;
import dev.sayaya.ui.elements.interfaces.HasHeadlineSlot;
import elemental2.dom.HTMLElement;
import elemental2.promise.Promise;
import org.jboss.elemento.HTMLElementBuilder;
import org.jboss.elemento.HTMLElementStyleMethods;
import org.jboss.elemento.HTMLElementVisibilityMethods;
import org.jboss.elemento.IsElement;

import static org.jboss.elemento.Elements.htmlElement;

public class DialogElementBuilder implements HTMLElementStyleMethods<MdDialogElement, DialogElementBuilder>,
        HTMLElementVisibilityMethods<MdDialogElement, DialogElementBuilder>,
        HasHeadlineSlot<MdDialogElement, DialogElementBuilder>,
        HasContentSlot<MdDialogElement, DialogElementBuilder>, HasAriaLabel<MdDialogElement, DialogElementBuilder> {
    public static DialogElementBuilder dialog() {
        return new DialogElementBuilder();
    }
    public static DialogElementBuilder alert() {
        return new DialogElementBuilder().type("alert");
    }
    private final HTMLElementBuilder<MdDialogElement> that = htmlElement("md-dialog", MdDialogElement.class);
    public DialogElementBuilder type(String type) {
        element().type = type;
        return that();
    }
    public DialogElementBuilder open() {
        return open(true);
    }
    public Promise<String> close() {
        return element().close();
    }
    public DialogElementBuilder open(boolean open) {
        element().open = open;
        return that();
    }
    @Override public DialogElementBuilder content(HTMLElement element) {
        if(element.tagName.equalsIgnoreCase("form")) element.setAttribute("method", "dialog");
        element.setAttribute("slot", "content");
        add(element);
        return that();
    }
    public DialogElementBuilder actions(IsElement<? extends HTMLElement> element) {
        return actions(element.element());
    }
    public DialogElementBuilder actions(HTMLElement element) {
        element.setAttribute("slot", "actions");
        add(element);
        return that();
    }
    @Override public MdDialogElement element() {
        return that.element();
    }
    @Override public DialogElementBuilder that() {
        return this;
    }
}
