package net.sayaya.ui.util;

import elemental2.dom.EventListener;
import elemental2.dom.EventTarget;
import elemental2.dom.MutationObserver;

public class OnAttachEvent {
    public static native MutationObserver observe(EventTarget targetNode, EventListener listener) /*-{
        var cb = function(mutationList, observer) {
            for (var i = 0; i < mutationList.length; i++) {
                var mutation = mutationList[i];
                if (mutation.type === 'childList') {
                    for (var j = 0; j < mutation.addedNodes.length; j++) {
                        var node = mutation.addedNodes[j];
                        listener.handleEvent(node);
                    }
                }
            }
        };
        var observer = new MutationObserver(cb);
        var config = {
          childList: true,
          subtree: false
        };
        observer.observe(targetNode, config);
        return observer;
    }-*/;
}
