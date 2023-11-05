package net.sayaya.ui.elements;

import net.sayaya.ui.dom.MdProgressElement;
import net.sayaya.ui.dom.MdProgressElement.MdCircularProgressElement;
import net.sayaya.ui.dom.MdProgressElement.MdLinearProgressElement;
import org.jboss.elemento.HTMLContainerBuilder;

import static org.jboss.elemento.Elements.htmlContainer;

public interface ProgressElementBuilder <E extends MdProgressElement, SELF extends ProgressElementBuilder<E, SELF>> extends HasAriaLabel<E, SELF> {
    static ProgressPrepareElementBuilder progress() {
        return new ProgressPrepareElementBuilder();
    }
    default SELF value(double value) {
        element().value = value;
        return that();
    }
    default SELF indeterminate() {
        return indeterminate(true);
    }
    default SELF indeterminate(boolean indeterminate) {
        element().indeterminate = indeterminate;
        return that();
    }
    default SELF fourColor() {
        return fourColor(true);
    }
    default SELF fourColor(boolean fourColor) {
        element().fourColor = fourColor;
        return that();
    }
    E element();
    final class ProgressPrepareElementBuilder {
        public LinearProgressElementBuilder linear() {
            return new LinearProgressElementBuilder();
        }
        public CircularProgressElementBuilder circular() {
            return new CircularProgressElementBuilder();
        }
    }
    final class LinearProgressElementBuilder implements ProgressElementBuilder<MdLinearProgressElement, LinearProgressElementBuilder> {
        private final HTMLContainerBuilder<MdLinearProgressElement> that = htmlContainer("md-linear-progress", MdLinearProgressElement.class);
        @Override
        public LinearProgressElementBuilder that() {
            return this;
        }
        @Override
        public MdLinearProgressElement element() {
            return that.element();
        }
        public LinearProgressElementBuilder buffer(double buffer) {
            element().buffer = buffer;
            return that();
        }
    }
    final class CircularProgressElementBuilder implements ProgressElementBuilder<MdCircularProgressElement, CircularProgressElementBuilder> {
        private final HTMLContainerBuilder<MdCircularProgressElement> that = htmlContainer("md-circular-progress", MdCircularProgressElement.class);
        @Override
        public CircularProgressElementBuilder that() {
            return this;
        }
        @Override
        public MdCircularProgressElement element() {
            return that.element();
        }
    }
}
