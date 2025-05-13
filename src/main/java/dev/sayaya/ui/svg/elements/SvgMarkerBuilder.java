package dev.sayaya.ui.svg.elements;

import dev.sayaya.ui.svg.dom.SVGMarkerElement;

public class SvgMarkerBuilder extends AbstractSvgBuilder<SVGMarkerElement, SvgMarkerBuilder> {
    public static SvgMarkerBuilder marker() {
        return new SvgMarkerBuilder();
    }
    private SvgMarkerBuilder() {
        super("marker");
    }
    public SvgMarkerBuilder viewBox(int a, int b, int c, int d) {
        attr("viewBox", a + " " + b + " " + c + " " + d);
        return that();
    }
    public SvgMarkerBuilder refX(int param) {
        return refX(String.valueOf(param));
    }
    public SvgMarkerBuilder refX(String param) {
        element().setAttribute("refX", param);
        return that();
    }
    public SvgMarkerBuilder refY(int param) {
        return refY(String.valueOf(param));
    }
    public SvgMarkerBuilder refY(String param) {
        element().setAttribute("refY", param);
        return that();
    }
    public SvgMarkerBuilder markerWidth(int w) {
        return markerWidth(String.valueOf(w));
    }
    public SvgMarkerBuilder markerWidth(String w) {
        attr("markerWidth", w);
        return that();
    }
    public SvgMarkerBuilder markerHeight(int h) {
        return markerHeight(String.valueOf(h));
    }
    public SvgMarkerBuilder markerHeight(String h) {
        attr("markerHeight", h);
        return that();
    }
    public SvgMarkerBuilder orient(String orient) {
        attr("orient", orient);
        return that();
    }
    @Override
    public SvgMarkerBuilder that() {
        return this;
    }
}
