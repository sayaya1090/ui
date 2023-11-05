package net.sayaya.ui.elements;

import net.sayaya.ui.dom.MdSliderElement;
import org.jboss.elemento.HTMLElementBuilder;
import org.jboss.elemento.HasElement;
import org.jboss.elemento.HasHTMLElement;

import static org.jboss.elemento.Elements.htmlElement;

public abstract class SliderElementBuilder<SELF extends SliderElementBuilder<SELF>> implements HasHTMLElement<MdSliderElement, SELF>, HasElement<MdSliderElement, SELF> {
    public static SliderContinuousElementBuilder slider() {
        return new SliderContinuousElementBuilder(htmlElement("md-slider", MdSliderElement.class));
    }
    final HTMLElementBuilder<MdSliderElement> that;
    private SliderElementBuilder(HTMLElementBuilder<MdSliderElement> element) {
        this.that = element;
    }
    public SELF labeled() {
        return labeled(true);
    }
    public SELF labeled(boolean labeled) {
        element().labeled = labeled;
        return that();
    }
    public SELF min(double min) {
        element().min = min;
        return that();
    }
    public SELF max(double max) {
        element().max = max;
        return that();
    }
    @Override public MdSliderElement element() {return that.element();}
    public static class SliderContinuousElementBuilder extends SliderElementBuilder<SliderContinuousElementBuilder> {
        private SliderContinuousElementBuilder(HTMLElementBuilder<MdSliderElement> element) {
            super(element);
        }
        @Override public SliderContinuousElementBuilder that() {return this;}
        public SliderContinuousElementBuilder value(double value) {
            element().value = value;
            return that();
        }
        public SliderDiscreteElementBuilder ticks(double step) {
            return new SliderDiscreteElementBuilder(that).ticks(step);
        }
        public SliderRangeElementBuilder range(double start, double end) {
            return new SliderRangeElementBuilder(that);
        }
    }
    public static class SliderDiscreteElementBuilder extends SliderElementBuilder<SliderDiscreteElementBuilder> {
        private SliderDiscreteElementBuilder(HTMLElementBuilder<MdSliderElement> element) {
            super(element);
            element().ticks = true;
        }
        public SliderDiscreteElementBuilder value(double value) {
            element().value = value;
            return that();
        }
        public SliderDiscreteElementBuilder ticks(double step) {
            element().step = step;
            return that();
        }
        public SliderContinuousElementBuilder continuous() {
            element().ticks = false;
            return new SliderContinuousElementBuilder(that);
        }
        public SliderRangeElementBuilder range(double start, double end) {
            element().ticks = false;
            return new SliderRangeElementBuilder(that);
        }
        @Override public SliderDiscreteElementBuilder that() {return this;}
    }
    public static class SliderRangeElementBuilder extends SliderElementBuilder<SliderRangeElementBuilder> {
        private SliderRangeElementBuilder(HTMLElementBuilder<MdSliderElement> element) {
            super(element);
            element().range = true;
        }
        public SliderRangeElementBuilder valueStart(double value) {
            element().valueStart = value;
            return that();
        }
        public SliderRangeElementBuilder valueEnd(double value) {
            element().valueEnd = value;
            return that();
        }
        @Override public SliderRangeElementBuilder that() {return this;}
    }
}
