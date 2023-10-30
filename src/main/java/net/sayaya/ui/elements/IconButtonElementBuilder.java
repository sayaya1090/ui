package net.sayaya.ui.elements;

import net.sayaya.ui.dom.MdIconButtonElement;
import net.sayaya.ui.dom.MdIconButtonElement.MdFilledIconButtonElement;
import net.sayaya.ui.dom.MdIconButtonElement.MdFilledTonalIconButtonElement;
import net.sayaya.ui.dom.MdIconButtonElement.MdOutlinedIconButtonElement;
import net.sayaya.ui.dom.MdIconElement;
import org.jboss.elemento.Container;
import org.jboss.elemento.HTMLContainerBuilder;
import org.jboss.elemento.HasElement;
import org.jboss.elemento.HasHTMLElement;

import static org.jboss.elemento.Elements.htmlContainer;

public interface IconButtonElementBuilder<E extends MdIconButtonElement, SELF extends IconButtonElementBuilder<E, SELF>> extends HasHTMLElement<E, SELF>, HasElement<E, SELF>, Container<E, SELF> , HasAriaLabel<E, SELF> {
    default SELF href(String href) {
        element().href = href;
        return that();
    }
    default SELF toggle(boolean toggle) {
        element().toggle = toggle;
        return that();
    }
    default SELF toggle(String icon) {
        return toggle(icon, false);
    }
    default SELF toggle(String icon, boolean selected) {
        return toggle(IconElementBuilder.icon(icon), selected);
    }
    default SELF toggle(IconElementBuilder icon) {
        return toggle(icon.element(), false);
    }
    default SELF toggle(IconElementBuilder icon, boolean selected) {
        return toggle(icon.element(), selected);
    }
    default SELF toggle(MdIconElement icon) {
        return toggle(icon, false);
    }
    default SELF toggle(MdIconElement icon, boolean selected) {
        this.add(icon);
        icon.setAttribute("slot", "selected");
        element().selected = selected;
        return that();
    }
    default SELF ariaLabelSelected(String label) {
        element().setAttribute("aria-label-selected", label);
        return that();
    }
    E element();

    final class PlainIconButtonElementBuilder implements IconButtonElementBuilder<MdIconButtonElement, PlainIconButtonElementBuilder> {
        private final HTMLContainerBuilder<MdIconButtonElement> that = htmlContainer("md-icon-button", MdIconButtonElement.class);
        public FilledIconButtonElementBuilder filled() {
            return new FilledIconButtonElementBuilder();
        }
        public FilledTonalIconButtonElementBuilder filledTonal() {
            return new FilledTonalIconButtonElementBuilder();
        }
        public OutlinedIconButtonElementBuilder outlined() {
            return new OutlinedIconButtonElementBuilder();
        }

        @Override
        public PlainIconButtonElementBuilder that() {
            return this;
        }
        @Override
        public MdIconButtonElement element() {
            return that.element();
        }
    }
    final class FilledIconButtonElementBuilder implements IconButtonElementBuilder<MdFilledIconButtonElement, FilledIconButtonElementBuilder> {
        private final HTMLContainerBuilder<MdFilledIconButtonElement> that = htmlContainer("md-filled-icon-button", MdFilledIconButtonElement.class);
        @Override
        public FilledIconButtonElementBuilder that() {
            return this;
        }
        @Override
        public MdFilledIconButtonElement element() {
            return that.element();
        }
    }
    final class FilledTonalIconButtonElementBuilder implements IconButtonElementBuilder<MdFilledTonalIconButtonElement, FilledTonalIconButtonElementBuilder> {
        private final HTMLContainerBuilder<MdFilledTonalIconButtonElement> that = htmlContainer("md-filled-tonal-icon-button", MdFilledTonalIconButtonElement.class);
        @Override
        public FilledTonalIconButtonElementBuilder that() {
            return this;
        }
        @Override
        public MdFilledTonalIconButtonElement element() {
            return that.element();
        }
    }
    final class OutlinedIconButtonElementBuilder implements IconButtonElementBuilder<MdOutlinedIconButtonElement, OutlinedIconButtonElementBuilder> {
        private final HTMLContainerBuilder<MdOutlinedIconButtonElement> that = htmlContainer("md-outlined-icon-button", MdOutlinedIconButtonElement.class);
        @Override
        public OutlinedIconButtonElementBuilder that() {
            return this;
        }
        @Override
        public MdOutlinedIconButtonElement element() {
            return that.element();
        }
    }
}
