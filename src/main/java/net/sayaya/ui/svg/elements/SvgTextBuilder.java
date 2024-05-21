package net.sayaya.ui.svg.elements;

import net.sayaya.ui.svg.dom.SVGTextElement;

public class SvgTextBuilder extends AbstractSvgBuilder<SVGTextElement, SvgTextBuilder> implements
        HasFill<SVGTextElement, SvgTextBuilder>, HasStroke<SVGTextElement, SvgTextBuilder>,
        HasPosition<SVGTextElement, SvgTextBuilder>, Rotatable<SVGTextElement, SvgTextBuilder>,
        Transformable<SVGTextElement, SvgTextBuilder>, Maskable<SVGTextElement, SvgTextBuilder> {
    public static SvgTextBuilder text() {
        return new SvgTextBuilder();
    }
    private SvgTextBuilder() {
        super("text");
    }

    public SvgTextBuilder dx(String dx) {
        attr("dx", dx);
        return that();
    }
    public SvgTextBuilder dy(String dy) {
        attr("dy", dy);
        return that();
    }
    public SvgTextBuilder length(String length) {
        attr("textLength", length);
        return that();
    }
    @Override
    public SvgTextBuilder that() {
        return this;
    }
}
