package net.sayaya.ui.util;

import elemental2.dom.EventListener;
import elemental2.dom.EventTarget;
import elemental2.dom.MutationObserver;

public class OnChangeEvent {
    public static native MutationObserver observe(EventTarget targetNode, EventListener listener) /*-{
        var cb = function(mutationList, observer) {
            for (var i = 0; i < mutationList.length; i++) {
                var mutation = mutationList[i];
                listener.handleEvent(mutation);
            }
        };
        var observer = new MutationObserver(cb);
        var config = {
          childList: true,
          attributes: true
        };
        observer.observe(targetNode, config);
        return observer;
    }-*/;
}
