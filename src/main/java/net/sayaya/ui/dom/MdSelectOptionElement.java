package net.sayaya.ui.dom;

import elemental2.dom.HTMLElement;
import jsinterop.annotations.JsType;

@JsType(isNative = true, namespace = "mdc", name="MdSelectOption")
public class MdSelectOptionElement extends HTMLElement {
    public boolean disabled;
    public boolean selected;
    public String value;
    public String type;
    public String typeaheadText;
    public String displayText;
}