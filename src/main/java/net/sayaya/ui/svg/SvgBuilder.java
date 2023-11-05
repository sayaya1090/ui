package net.sayaya.ui.svg;

public class SvgBuilder extends AbstractSvgBuilder<SVGElement, SvgBuilder> {
    public static SvgBuilder svg() {
        return new SvgBuilder();
    }
    private SvgBuilder() {
        super("svg");
    }
    public SvgBuilder viewBox(int a, int b, int c, int d) {
        attr("viewBox", a + " " + b + " " + c + " " + d);
        return that();
    }

    @Override
    public SvgBuilder that() {
        return this;
    }
}