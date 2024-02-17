package net.sayaya.ui.svg;

public class SvgPolygonBuilder extends AbstractSvgBuilder<SVGPolygonElement, SvgPolygonBuilder> {
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
    public SvgPolygonBuilder stroke(String param) {
        attr("stroke", param);
        return that();
    }
    public SvgPolygonBuilder fillRule(String param) {
        attr("fill-rule", param);
        return that();
    }
    public SvgPolygonBuilder mask(SvgMaskBuilder mask) {
        return mask(mask.id());
    }
    public SvgPolygonBuilder mask(String id) {
        attr("mask", "url(#" + id + ")");
        return that();
    }
    public SvgPolygonBuilder transform(String transform) {
        attr("transform", transform);
        return that();
    }
    @Override
    public SvgPolygonBuilder that() {
        return this;
    }
}
