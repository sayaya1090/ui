package dev.sayaya.ui.svg.elements;

import dev.sayaya.ui.svg.dom.SVGPathElement;

public class SvgPathBuilder extends AbstractSvgBuilder<SVGPathElement, SvgPathBuilder> implements
        HasFill<SVGPathElement, SvgPathBuilder>, HasStroke<SVGPathElement, SvgPathBuilder>,
        Transformable<SVGPathElement, SvgPathBuilder>, Maskable<SVGPathElement, SvgPathBuilder> {
    public static SvgPathBuilder path() {
        return new SvgPathBuilder();
    }
    private SvgPathBuilder() {
        super("path");
    }
    public SvgPathBuilder d(String param) {
        attr("d", param);
        return that();
    }
    @Override
    public SvgPathBuilder that() {
        return this;
    }
}
