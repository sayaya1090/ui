package net.sayaya.ui.svg.elements;

import net.sayaya.ui.svg.dom.SVGPolygonElement;
import net.sayaya.ui.svg.dom.SVGRectElement;

public class SvgRectBuilder extends AbstractSvgBuilder<SVGRectElement, SvgRectBuilder> implements
        HasFill<SVGRectElement, SvgRectBuilder>, HasStroke<SVGRectElement, SvgRectBuilder>,
        HasPosition<SVGRectElement, SvgRectBuilder>, Rotatable<SVGRectElement, SvgRectBuilder>,
        Transformable<SVGRectElement, SvgRectBuilder>, Maskable<SVGRectElement, SvgRectBuilder> {
    public static SvgRectBuilder rect() {
        return new SvgRectBuilder();
    }
    private SvgRectBuilder() {
        super("rect");
    }

    public SvgRectBuilder width(String w) {
        attr("width", w);
        return that();
    }
    public SvgRectBuilder height(String h) {
        attr("height", h);
        return that();
    }
    @Override
    public SvgRectBuilder that() {
        return this;
    }
}
