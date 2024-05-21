package net.sayaya.ui.svg.elements;

import net.sayaya.ui.svg.dom.SVGPolygonElement;

public class SvgPolygonBuilder extends AbstractSvgBuilder<SVGPolygonElement, SvgPolygonBuilder> implements
        HasFill<SVGPolygonElement, SvgPolygonBuilder>, HasStroke<SVGPolygonElement, SvgPolygonBuilder>,
        HasPosition<SVGPolygonElement, SvgPolygonBuilder>, Rotatable<SVGPolygonElement, SvgPolygonBuilder>,
        Transformable<SVGPolygonElement, SvgPolygonBuilder>, Maskable<SVGPolygonElement, SvgPolygonBuilder> {
    public static SvgPolygonBuilder polygon() {
        return new SvgPolygonBuilder();
    }
    private SvgPolygonBuilder() {
        super("polygon");
    }
    public SvgPolygonBuilder points(String param) {
        attr("points", param);
        return that();
    }
    public SvgPolygonBuilder fillRule(String param) {
        attr("fill-rule", param);
        return that();
    }
    @Override
    public SvgPolygonBuilder that() {
        return this;
    }
}
