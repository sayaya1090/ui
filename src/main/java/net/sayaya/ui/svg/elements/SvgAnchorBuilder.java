package net.sayaya.ui.svg.elements;

public class SvgAnchorBuilder extends SvgGroupBuilder {
    public static SvgAnchorBuilder a() {
        return new SvgAnchorBuilder();
    }
    private SvgAnchorBuilder() {
        super("a");
    }
    public SvgAnchorBuilder href(String href) {
        attr("href", href);
        return that();
    }
    @Override
    public SvgAnchorBuilder that() {
        return this;
    }
}
