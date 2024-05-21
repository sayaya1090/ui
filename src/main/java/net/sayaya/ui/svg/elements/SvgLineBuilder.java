package net.sayaya.ui.svg.elements;

import net.sayaya.ui.svg.dom.SVGElement;

public class SvgLineBuilder extends AbstractSvgBuilder<SVGElement, SvgLineBuilder> implements HasStroke<SVGElement, SvgLineBuilder> {
    public static SvgLineBuilder line() {
        return new SvgLineBuilder();
    }
    private SvgLineBuilder() {
        super("line");
    }
    protected SvgLineBuilder(String tag) {
        super(tag);
    }
    public SvgLineBuilder x1(int param) {
        return x1(String.valueOf(param));
    }
    public SvgLineBuilder x1(String param) {
        element().setAttribute("x1", param);
        return that();
    }
    public SvgLineBuilder y1(int param) {
        return y1(String.valueOf(param));
    }
    public SvgLineBuilder y1(String param) {
        element().setAttribute("y1", param);
        return that();
    }
    public SvgLineBuilder x2(int param) {
        return x2(String.valueOf(param));
    }
    public SvgLineBuilder x2(String param) {
        element().setAttribute("x2", param);
        return that();
    }
    public SvgLineBuilder y2(int param) {
        return y2(String.valueOf(param));
    }
    public SvgLineBuilder y2(String param) {
        element().setAttribute("y2", param);
        return that();
    }
    @Override
    public SvgLineBuilder that() {
        return this;
    }
}
