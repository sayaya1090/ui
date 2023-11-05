package net.sayaya.ui.dom;

import elemental2.dom.HTMLElement;
import jsinterop.annotations.JsType;

@JsType(isNative = true, namespace = "mdc", name="MdMenu")
public class MdMenuElement extends HTMLElement {
    public String anchor;
    public String positioning;
    public boolean quick;
    public boolean hasOverflow;
    public boolean open;
    public double xOffset;
    public double yOffset;
    public double typeaheadDelay;
    public String anchorCorner;
    public String menuCorner;
    public boolean stayOpenOnOutsideClick;
    public boolean stayOpenOnFocusout;
    public boolean skipRestoreFocus;
    public String defaultFocus;
    public boolean isSubmenu;
    public Object typeaheadController;
    public HTMLElement anchorElement;
    public MdMenuItemElement[] items;

    public native void close();
    public native void show();
    public native MdMenuItemElement activateNextItem();
    public native MdMenuItemElement activatePreviousItem();
}
