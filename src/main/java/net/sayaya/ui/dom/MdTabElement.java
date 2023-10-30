package net.sayaya.ui.dom;

import elemental2.dom.HTMLElement;
import jsinterop.annotations.JsType;

@JsType(isNative = true)
public class MdTabElement extends HTMLElement {
    public boolean isTab;
    public boolean active;
    public boolean hasIcon;
    public boolean iconOnly;
    public boolean selected;

    @JsType(isNative = true, namespace = "mdc", name="MdPrimaryTab")
    public static class MdPrimaryTabElement extends MdTabElement {
        public boolean inlineIcon;
    }

    @JsType(isNative = true, namespace = "mdc", name="MdSecondaryTab")
    public static class MdSecondaryTabElement extends MdTabElement { }
}
