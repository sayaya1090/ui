package net.sayaya.ui.dom;

import elemental2.dom.*;
import jsinterop.annotations.JsType;

@JsType(isNative = true, namespace = "mdc", name="MdSwitch")
public class MdSwitchElement extends HTMLElement {
    public boolean disabled;
    public boolean selected;
    public boolean icons;
    public boolean showOnlySelectedIcon;
    public boolean required;
    public String value;
    public String name;
    public HTMLFormElement form;
    public NodeList<HTMLLabelElement> labels;
    public ValidityState validity;
    public String validationMessage;
    public boolean willValidate;

    public native boolean checkValidity();
    public native boolean reportValidity();
    public native void setCustomValidity(String message);
}
