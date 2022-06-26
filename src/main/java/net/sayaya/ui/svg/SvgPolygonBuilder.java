package net.sayaya.ui.svg;

import elemental2.svg.SVGPolygonElement;

public class SvgPolygonBuilder extends AbstractSvgBuilder<SVGPolygonElement, SvgPolygonBuilder> {
    public static SvgPolygonBuilder polygon() {
        return new SvgPolygonBuilder();
    }
    private SvgPolygonBuilder() {
        super("polygon");
    }
    public SvgPolygonBuilder points(String param) {
        element().setAttribute("points", param);
        return that();
    }
    public SvgPolygonBuilder stroke(String param) {
        element().setAttribute("stroke", param);
        return that();
    }
    public SvgPolygonBuilder fillRule(String param) {
        element().setAttribute("fill-rule", param);
        return that();
    }
}
