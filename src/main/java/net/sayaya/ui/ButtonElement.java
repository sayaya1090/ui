package net.sayaya.ui;

import elemental2.dom.*;
import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;
import net.sayaya.ui.button.*;

@JsType(isNative = true, namespace = JsPackage.GLOBAL)
public abstract class ButtonElement extends HTMLElement {
    @JsOverlay
    public static ElevatedButtonElementBuilder elevatedButton() {
        return new ElevatedButtonElementBuilder();
    }
    @JsOverlay
    public static FilledButtonElementBuilder filledButton() {
        return new FilledButtonElementBuilder();
    }
    @JsOverlay
    public static FilledTonalButtonElementBuilder tonalButton() {
        return new FilledTonalButtonElementBuilder();
    }
    @JsOverlay
    public static OutlinedButtonElementBuilder outlinedButton() {
        return new OutlinedButtonElementBuilder();
    }
    @JsOverlay
    public static TextButtonElementBuilder textButton() {
        return new TextButtonElementBuilder();
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
}
