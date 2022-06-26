package net.sayaya.ui.svg;

import elemental2.svg.SVGPathElement;

public class SvgPathBuilder extends AbstractSvgBuilder<SVGPathElement, SvgPathBuilder> {
    public static SvgPathBuilder path() {
        return new SvgPathBuilder();
    }
    private SvgPathBuilder() {
        super("path");
    }
    public SvgPathBuilder d(String param) {
        element().setAttribute("d", param);
        return that();
    }
    public SvgPathBuilder fill(String param) {
        element().setAttribute("fill", param);
        return that();
    }
    public SvgPathBuilder stroke(String param) {
        element().setAttribute("stroke", param);
        return that();
    }
}
