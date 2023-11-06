package net.sayaya.ui.elements;

import net.sayaya.ui.dom.MdFabElement;
import net.sayaya.ui.dom.MdFabElement.MdPlainFabElement;
import org.jboss.elemento.HTMLContainerBuilder;
import org.jboss.elemento.HasElement;
import org.jboss.elemento.HasHTMLElement;

import static org.jboss.elemento.Elements.htmlContainer;

public interface FabElementBuilder<E extends MdFabElement, SELF extends FabElementBuilder<E, SELF>> extends HasHTMLElement<E, SELF>, HasElement<E, SELF>,
        HasIconSlot<E, SELF>, HasAriaLabel<E, SELF> {
    default SELF label(String label) {
        element().label = label;
        return that();
    }
    default SELF lowered() {
        return lowered(true);
    }
    default SELF lowered(boolean lowered) {
        element().lowered = lowered;
        return that();
    }
    E element();
    final class PlainFabElementBuilder implements FabElementBuilder<MdPlainFabElement, PlainFabElementBuilder> {
        private final HTMLContainerBuilder<MdPlainFabElement> that = htmlContainer("md-fab", MdPlainFabElement.class);
        @Override public PlainFabElementBuilder that() {
            return this;
        }
        @Override public MdPlainFabElement element() {
            return that.element();
        }
    }
}
