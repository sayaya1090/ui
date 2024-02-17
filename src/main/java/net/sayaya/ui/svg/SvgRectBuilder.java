package net.sayaya.ui.svg;

public class SvgRectBuilder extends AbstractSvgBuilder<SVGRectElement, SvgRectBuilder> {
    public static SvgRectBuilder rect() {
        return new SvgRectBuilder();
    }
    private SvgRectBuilder() {
        super("rect");
    }
    public SvgRectBuilder x(int x) {
        attr("x", String.valueOf(x));
        return that();
    }
    public SvgRectBuilder y(int y) {
        attr("y", String.valueOf(y));
        return that();
    }
    public SvgRectBuilder width(String w) {
        attr("width", w);
        return that();
    }
    public SvgRectBuilder height(String h) {
        attr("height", h);
        return that();
    }
    public SvgRectBuilder stroke(String param) {
        attr("stroke", param);
        return that();
    }
    public SvgRectBuilder fill(String param) {
        attr("fill", param);
        return that();
    }
    public SvgRectBuilder mask(SvgMaskBuilder mask) {
        attr("mask", "url(#" + mask.id() + ")");
        return that();
    }
    public SvgRectBuilder transform(String transform) {
        attr("transform", transform);
        return that();
    }
    @Override
    public SvgRectBuilder that() {
        return this;
    }
}
