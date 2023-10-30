package net.sayaya.ui.dom;

import elemental2.dom.HTMLElement;
import elemental2.dom.HTMLFormElement;
import jsinterop.annotations.JsType;

@JsType(isNative = true)
public class MdButtonElement extends HTMLElement {
    public boolean disabled;
    public String href;
    public String target;
    public boolean trailingIcon;
    public boolean hasIcon;
    public String type;
    public String value;
    public String name;
    public HTMLFormElement form;
    @JsType(isNative = true, namespace = "mdc", name="MdElevatedButton")
    public static class MdElevatedButtonElement extends MdButtonElement {

    }
    @JsType(isNative = true, namespace = "mdc", name="MdFilledButton")
    public static class MdFilledButtonElement extends MdButtonElement {

    }
    @JsType(isNative = true, namespace = "mdc", name="MdFilledTonalButton")
    public static class MdFilledTonalButtonElement extends MdButtonElement {

    }
    @JsType(isNative = true, namespace = "mdc", name="MdOutlinedButton")
    public static class MdOutlinedButtonElement extends MdButtonElement {

    }
    @JsType(isNative = true, namespace = "mdc", name="MdTextButton")
    public static class MdTextButtonElement extends MdButtonElement {

    }
}