package net.sayaya.ui.dom;

import elemental2.dom.HTMLElement;
import elemental2.promise.Promise;
import jsinterop.annotations.JsType;

@JsType(isNative = true, namespace = "mdc", name="MdDialog")
public class MdDialogElement extends HTMLElement {
    public String returnValue;
    public String type;
    public boolean open;

    public native Promise<Void> show();
    public native Promise<String> close();
}
