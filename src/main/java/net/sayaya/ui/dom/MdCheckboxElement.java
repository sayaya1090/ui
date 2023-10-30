package net.sayaya.ui.dom;

import elemental2.dom.HTMLElement;
import jsinterop.annotations.JsType;

@JsType(isNative = true, namespace = "mdc", name="MdCheckbox")
public class MdCheckboxElement extends HTMLElement {
    public boolean checked;
    public boolean indeterminate;
}
