package net.sayaya.ui.dom;

import elemental2.dom.*;
import elemental2.promise.Promise;
import jsinterop.annotations.JsType;

@JsType(isNative = true)
public class MdSelectElement extends HTMLElement {
    public boolean quick;
    public boolean required;
    public boolean disabled;
    public String errorText;
    public String label;
    public String supportingText;
    public boolean error;
    public String menuPositioning;
    public double typeaheadDelay;
    public boolean hasLeadingIcon;
    public String displayText;
    public String value;
    public Double selectedIndex;
    public MdSelectOptionElement[] options;
    public MdSelectOptionElement[] selectedOptions;
    public String name;
    public HTMLFormElement form;
    public NodeList<HTMLLabelElement> labels;
    public ValidityState validity;
    public String validationMessage;
    public boolean willValidate;

    public native void select(String value);
    public native void selectIndex(int index);
    public native void reset();
    public native boolean checkValidity();
    public native boolean reportValidity();
    public native void setCustomValidity(String error);
    public native Promise<Boolean> getUpdateComplete();

    @JsType(isNative = true, namespace = "mdc", name="MdFilledSelect")
    public static class MdFilledSelectElement extends MdSelectElement { }
    @JsType(isNative = true, namespace = "mdc", name="MdOutlinedSelect")
    public static class MdOutlinedSelectElement extends MdSelectElement { }
}