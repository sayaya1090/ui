package net.sayaya.ui.svg.elements;

import net.sayaya.ui.svg.dom.SVGElement;

public class SvgCircleBuilder extends AbstractSvgBuilder<SVGElement, SvgCircleBuilder> implements
        HasFill<SVGElement, SvgCircleBuilder>, HasStroke<SVGElement, SvgCircleBuilder>,
        HasPosition<SVGElement, SvgCircleBuilder>, Transformable<SVGElement, SvgCircleBuilder>, Maskable<SVGElement, SvgCircleBuilder> {
    public static SvgCircleBuilder circle() {
        return new SvgCircleBuilder();
    }
    private SvgCircleBuilder() {
        super("circle");
    }
    public SvgCircleBuilder x(int param) {
        return x(String.valueOf(param));
    }
    public SvgCircleBuilder x(String param) {
        element().setAttribute("cx", param);
        return that();
    }
    public SvgCircleBuilder y(int param) {
        return y(String.valueOf(param));
    }
    public SvgCircleBuilder y(String param) {
        element().setAttribute("cy", param);
        return that();
    }
    public SvgCircleBuilder r(int param) {
        return r(String.valueOf(param));
    }
    public SvgCircleBuilder r(String param) {
        element().setAttribute("r", param);
        return that();
    }
    @Override
    public SvgCircleBuilder that() {
        return this;
    }
}
