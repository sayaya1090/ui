package net.sayaya.ui.elements;

import elemental2.dom.Element;
import elemental2.dom.HTMLElement;
import elemental2.dom.MouseEvent;
import net.sayaya.ui.dom.MdIconButtonElement;
import net.sayaya.ui.dom.MdIconButtonElement.MdFilledIconButtonElement;
import net.sayaya.ui.dom.MdIconButtonElement.MdFilledTonalIconButtonElement;
import net.sayaya.ui.dom.MdIconButtonElement.MdOutlinedIconButtonElement;
import net.sayaya.ui.svg.IsSvgElement;
import org.jboss.elemento.*;

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
    default SELF toggle(IsElement<? extends HTMLElement> elem) {
        return toggle(elem, false);
    }
    default SELF toggle(IsElement<? extends HTMLElement> elem, boolean selected) {
        return toggle(elem.element(), selected);
    }
    default SELF toggle(HTMLElement elem) {
        return toggle(elem, false);
    }
    default SELF toggle(IsSvgElement<?, ?> elem) {
        return toggle(elem, false);
    }
    default SELF toggle(IsSvgElement<?, ?> elem, boolean selected) {
        return toggle(elem.element(), selected);
    }
    default SELF toggle(Element elem, boolean selected) {
        this.add(elem);
        elem.setAttribute("slot", "selected");
        element().selected = selected;
        return that();
    }
    default SELF ariaLabelSelected(String label) {
        element().setAttribute("aria-label-selected", label);
        return that();
    }
    default SELF onClick(EventCallbackFn<MouseEvent> callback) {
        return on(EventType.click, callback);
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
