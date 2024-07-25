package net.sayaya.ui.util;

import elemental2.dom.EventListener;
import elemental2.dom.EventTarget;
import elemental2.dom.MutationObserver;

public class OnDetachEvent {
    public static native MutationObserver observe(EventTarget targetNode, EventListener listener) /*-{

    }-*/;
}
