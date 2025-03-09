package dev.sayaya.ui.svg.elements;

import dev.sayaya.ui.svg.dom.SVGMaskElement;

public class SvgMaskBuilder extends AbstractSvgBuilder<SVGMaskElement, SvgMaskBuilder> {
    public static SvgMaskBuilder mask(String id) {
        return new SvgMaskBuilder(id);
    }
    private SvgMaskBuilder(String id) {
        super("mask");
        this.id(id);
    }
    public SvgMaskBuilder that() {
        return this;
    }
}
