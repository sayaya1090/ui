package net.sayaya.ui.dom;

import elemental2.dom.HTMLElement;
import elemental2.dom.HTMLFormElement;
import elemental2.dom.HTMLLabelElement;
import elemental2.dom.NodeList;
import jsinterop.annotations.JsType;

@JsType(isNative = true, namespace = "mdc", name="MdRadio")
public class MdRadioElement extends HTMLElement {
    public boolean disabled;
    public String value;
    public boolean checked;
    public String name;
    public HTMLFormElement form;
    public NodeList<HTMLLabelElement> labels;
}
