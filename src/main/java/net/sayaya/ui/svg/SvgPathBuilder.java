package net.sayaya.ui.svg;

public class SvgPathBuilder extends AbstractSvgBuilder<SVGPathElement, SvgPathBuilder> {
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
    public SvgPathBuilder fill(String param) {
        attr("fill", param);
        return that();
    }
    public SvgPathBuilder stroke(String param) {
        attr("stroke", param);
        return that();
    }
    public SvgPathBuilder width(int width) {
        attr("stroke-width", String.valueOf(width));
        return that();
    }
    public SvgPathBuilder mask(SvgMaskBuilder mask) {
        return mask(mask.id());
    }
    public SvgPathBuilder mask(String id) {
        attr("mask", "url(#" + id + ")");
        return that();
    }
    public SvgPathBuilder transform(String transform) {
        attr("transform", transform);
        return that();
    }
    @Override
    public SvgPathBuilder that() {
        return this;
    }
}
