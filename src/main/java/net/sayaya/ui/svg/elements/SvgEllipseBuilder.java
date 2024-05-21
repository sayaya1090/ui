package net.sayaya.ui.svg.elements;

import net.sayaya.ui.svg.dom.SVGElement;

public class SvgEllipseBuilder extends AbstractSvgBuilder<SVGElement, SvgEllipseBuilder> implements
        HasFill<SVGElement, SvgEllipseBuilder>, HasStroke<SVGElement, SvgEllipseBuilder>,
        HasPosition<SVGElement, SvgEllipseBuilder>, Rotatable<SVGElement, SvgEllipseBuilder>,
        Transformable<SVGElement, SvgEllipseBuilder>, Maskable<SVGElement, SvgEllipseBuilder> {
    public static SvgEllipseBuilder ellipse() {
        return new SvgEllipseBuilder();
    }
    private SvgEllipseBuilder() {
        super("ellipse");
    }
    public SvgEllipseBuilder x(int param) {
        return x(String.valueOf(param));
    }
    public SvgEllipseBuilder x(String param) {
        element().setAttribute("cx", param);
        return that();
    }
    public SvgEllipseBuilder y(int param) {
        return y(String.valueOf(param));
    }
    public SvgEllipseBuilder y(String param) {
        element().setAttribute("cy", param);
        return that();
    }
    public SvgEllipseBuilder rx(int param) {
        return rx(String.valueOf(param));
    }
    public SvgEllipseBuilder rx(String param) {
        element().setAttribute("rx", param);
        return that();
    }
    public SvgEllipseBuilder ry(int param) {
        return ry(String.valueOf(param));
    }
    public SvgEllipseBuilder ry(String param) {
        element().setAttribute("ry", param);
        return that();
    }
    @Override
    public SvgEllipseBuilder that() {
        return this;
    }
}
