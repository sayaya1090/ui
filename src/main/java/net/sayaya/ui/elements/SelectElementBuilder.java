package net.sayaya.ui.elements;

import net.sayaya.ui.dom.MdSelectElement;
import net.sayaya.ui.dom.MdSelectElement.MdFilledSelectElement;
import net.sayaya.ui.dom.MdSelectElement.MdOutlinedSelectElement;
import net.sayaya.ui.dom.MdSelectOptionElement;
import org.jboss.elemento.Container;
import org.jboss.elemento.HTMLContainerBuilder;
import org.jboss.elemento.HasElement;
import org.jboss.elemento.HasHTMLElement;

import static org.jboss.elemento.Elements.htmlContainer;

public interface SelectElementBuilder<E extends MdSelectElement, SELF extends SelectElementBuilder<E, SELF>> extends HasHTMLElement<E, SELF>, HasElement<E, SELF>, Container<E, SELF> {
    static SelectPrepareElementBuilder select() {
        return new SelectPrepareElementBuilder();
    }
    default SelectOptionElementBuilder<SELF> option() {
        return new SelectOptionElementBuilder<>(this);
    }
    default SELF required() {
        return required(true);
    }
    default SELF required(boolean required) {
        element().required = required;
        return that();
    }
    default SELF disabled() {
        return disabled(true);
    }
    default SELF disabled(boolean disabled) {
        element().disabled = disabled;
        return that();
    }
    default SELF supportingText(String supportingText) {
        element().supportingText = supportingText;
        return that();
    }
    E element();
    final class SelectPrepareElementBuilder {
        public FilledSelectElementBuilder filled() {
            return new FilledSelectElementBuilder();
        }
        public OutlinedSelectElementBuilder outlined() {
            return new OutlinedSelectElementBuilder();
        }
    }
    final class SelectOptionElementBuilder<P extends SelectElementBuilder<?, P>> implements HasHTMLElement<MdSelectOptionElement, SelectOptionElementBuilder<P>>, HasElement<MdSelectOptionElement, SelectOptionElementBuilder<P>>,
            HasAriaLabel<MdSelectOptionElement, SelectOptionElementBuilder<P>>, HasHeadlineSlot<MdSelectOptionElement, SelectOptionElementBuilder<P>>, HasSupportingTextSlot<MdSelectOptionElement, SelectOptionElementBuilder<P>> {
        private final HTMLContainerBuilder<MdSelectOptionElement> that;
        private final SelectElementBuilder<?, P> parent;
        private SelectOptionElementBuilder(SelectElementBuilder<?, P> parent) {
            that = htmlContainer("md-select-option", MdSelectOptionElement.class);
            this.parent = parent;
            parent.add(this);
        }
        public SelectElementBuilder<?, P> end() {
            return parent;
        }
        public SelectOptionElementBuilder<P> value(String value) {
            element().value = value;
            return that();
        }
        public SelectOptionElementBuilder<P> selected() {
            return selected(true);
        }
        public SelectOptionElementBuilder<P> selected(boolean selected) {
            element().selected = selected;
            return that();
        }
        @Override public MdSelectOptionElement element() {
            return that.element();
        }
        @Override public SelectOptionElementBuilder<P> that() {
            return this;
        }
    }
    final class FilledSelectElementBuilder implements SelectElementBuilder<MdFilledSelectElement, FilledSelectElementBuilder> {
        private final HTMLContainerBuilder<MdFilledSelectElement> that = htmlContainer("md-filled-select", MdFilledSelectElement.class);
        @Override
        public FilledSelectElementBuilder that() {
            return this;
        }
        @Override
        public MdFilledSelectElement element() {
            return that.element();
        }
    }
    final class OutlinedSelectElementBuilder implements SelectElementBuilder<MdOutlinedSelectElement, OutlinedSelectElementBuilder> {
        private final HTMLContainerBuilder<MdOutlinedSelectElement> that = htmlContainer("md-outlined-select", MdOutlinedSelectElement.class);
        @Override
        public OutlinedSelectElementBuilder that() {
            return this;
        }
        @Override
        public MdOutlinedSelectElement element() {
            return that.element();
        }
    }
}
