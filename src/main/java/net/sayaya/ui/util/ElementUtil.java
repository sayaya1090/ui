package net.sayaya.ui.util;

import elemental2.core.JsArray;
import elemental2.dom.Element;
import jsinterop.base.JsArrayLike;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ElementUtil {
    public boolean isPrev(Element e1, Element e2) {
        var children = JsArray.from((JsArrayLike)e1.parentElement.childNodes);
        return children.indexOf(e1) < children.indexOf(e2);
    }
    public boolean isNext(Element e1, Element e2) {
        return isPrev(e2, e1);
    }
}
