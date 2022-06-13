package net.sayaya.ui.util;

import elemental2.core.JsArray;
import elemental2.dom.Element;
import jsinterop.base.JsArrayLike;
import lombok.experimental.UtilityClass;

import java.util.Date;

@UtilityClass
public class ElementUtil {
    public String uniqueId() {
        final var dateString = Long.toString(new Date().getTime(), 36);
        final var randomness = Long.toString(Double.valueOf(Math.random()*1000000000000L).longValue(), 36);
        return dateString + randomness;
    }
    public boolean isPrev(Element e1, Element e2) {
        var children = JsArray.from((JsArrayLike)e1.parentElement.childNodes);
        return children.indexOf(e1) < children.indexOf(e2);
    }
    public boolean isNext(Element e1, Element e2) {
        return isPrev(e2, e1);
    }
}
