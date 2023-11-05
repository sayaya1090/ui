package net.sayaya.ui.dom;

import elemental2.dom.HTMLElement;
import elemental2.dom.HTMLFormElement;
import elemental2.dom.HTMLLabelElement;
import elemental2.dom.NodeList;
import jsinterop.annotations.JsType;

@JsType(isNative = true, namespace = "mdc", name="MdIconButton")
public class MdIconButtonElement extends HTMLElement {
    public boolean disabled;
    public boolean flipIconInRtl;
    public String href;
    public String target;
    public String ariaLabelSelected;
    public boolean toggle;
    public boolean selected;
    public String type;
    public String value;
    public String name;
    public HTMLFormElement form;
    public NodeList<HTMLLabelElement> labels;

    @JsType(isNative = true, namespace = "mdc", name="MdFilledIconButton")
    public static class MdFilledIconButtonElement extends MdIconButtonElement {

    }
    @JsType(isNative = true, namespace = "mdc", name="MdFilledTonalIconButton")
    public static class MdFilledTonalIconButtonElement extends MdIconButtonElement {

    }
    @JsType(isNative = true, namespace = "mdc", name="MdOutlinedIconButton")
    public static class MdOutlinedIconButtonElement extends MdIconButtonElement {

    }
}