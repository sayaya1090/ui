package net.sayaya.ui.svg.elements;

import net.sayaya.ui.svg.dom.SVGGroupElement;

public class SvgGroupBuilder extends AbstractSvgBuilder<SVGGroupElement, SvgGroupBuilder> implements
        HasFill<SVGGroupElement, SvgGroupBuilder>, HasStroke<SVGGroupElement, SvgGroupBuilder>,
        Transformable<SVGGroupElement, SvgGroupBuilder> {
    public static SvgGroupBuilder g() {
        return new SvgGroupBuilder();
    }
    private SvgGroupBuilder() {
        super("g");
    }
    protected SvgGroupBuilder(String tag) {
        super(tag);
    }
    @Override
    public SvgGroupBuilder that() {
        return this;
    }
}
