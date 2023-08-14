package net.sayaya.ui;

import elemental2.dom.*;
import elemental2.svg.SVGElement;
import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;
import net.sayaya.ui.button.*;
import net.sayaya.ui.button.icon.FilledIconButtonElementBuilder;
import net.sayaya.ui.button.icon.FilledTonalIconButtonElementBuilder;
import net.sayaya.ui.button.icon.OutlinedIconButtonElementBuilder;
import net.sayaya.ui.button.icon.PlainIconButtonElementBuilder;
import org.jboss.elemento.IsElement;

@JsType(isNative = true, namespace = JsPackage.GLOBAL)
public abstract class ButtonElement extends HTMLElement {
    @JsOverlay
    public static ButtonElementBuilder button() {
        return new ButtonElementBuilder();
    }
    public String accessKey;
    public boolean autofocus;
    public boolean disabled;
    public HTMLFormElement form;
    public String formAction;
    public String formEnctype;
    public String formMethod;
    public String formTarget;
    public NodeList<HTMLLabelElement> labels;
    public String name;
    public int tabIndex;
    public String type;
    public String validationMessage;
    public ValidityState validity;
    public String value;
    public boolean willValidate;
    @JsOverlay
    public final ButtonElement label(String label) {
        this.append(label);
        return this;
    }

    public static class ButtonElementBuilder {
        public ElevatedButtonElementBuilder elevated() {
            return new ElevatedButtonElementBuilder();
        }
        public FilledButtonElementBuilder filled() {
            return new FilledButtonElementBuilder();
        }
        public FilledTonalButtonElementBuilder tonal() {
            return new FilledTonalButtonElementBuilder();
        }
        public OutlinedButtonElementBuilder outlined() {
            return new OutlinedButtonElementBuilder();
        }
        public TextButtonElementBuilder text() {
            return new TextButtonElementBuilder();
        }
        public IconButtonElementBuilder icon(IsElement<? super HTMLElement> icon) {
            return new IconButtonElementBuilder(icon.element());
        }
        public IconButtonElementBuilder icon(SVGElement icon) {
            return new IconButtonElementBuilder(icon);
        }
    }
    public static class IconButtonElementBuilder {
        private Element icon;
        private IconButtonElementBuilder(Element icon) {
            this.icon = icon;
        }
        public PlainIconButtonElementBuilder plain() {
            return new PlainIconButtonElementBuilder(icon);
        }
        public FilledIconButtonElementBuilder filled() {
            return new FilledIconButtonElementBuilder(icon);
        }
        public FilledTonalIconButtonElementBuilder tonal() {
            return new FilledTonalIconButtonElementBuilder(icon);
        }
        public OutlinedIconButtonElementBuilder outlined() {
            return new OutlinedIconButtonElementBuilder(icon);
        }
    }
}
