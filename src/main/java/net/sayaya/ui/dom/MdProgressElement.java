package net.sayaya.ui.dom;

import elemental2.dom.HTMLElement;
import jsinterop.annotations.JsType;

@JsType(isNative = true)
public class MdProgressElement extends HTMLElement {
    public Double value;
    public Double max;
    public boolean indeterminate;
    public boolean fourColor;

    @JsType(isNative = true, namespace = "mdc", name="MdLinearProgress")
    public static class MdLinearProgressElement extends MdProgressElement {
        public Double buffer;
    }
    @JsType(isNative = true, namespace = "mdc", name="MdCircularProgress")
    public static class MdCircularProgressElement extends MdProgressElement {

    }
}
