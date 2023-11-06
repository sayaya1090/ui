package net.sayaya.ui.elements;

import net.sayaya.ui.dom.MdFabElement;
import net.sayaya.ui.dom.MdFabElement.*;
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
    default SELF variant(Variant variant) {
        if(variant!=null) element().variant = variant.toString();
        else element().variant = null;
        return that();
    }
    default SELF touchTarget(String touchTarget) {
        element().touchTarget = touchTarget;
        return that();
    }
    E element();
    final class PlainFabElementBuilder implements FabElementBuilder<MdPlainFabElement, PlainFabElementBuilder> {
        private final HTMLContainerBuilder<MdPlainFabElement> that = htmlContainer("md-fab", MdPlainFabElement.class);
        public PlainFabElementBuilder size(Size size) {
            if(size!=null) element().size = size.toString();
            else element().size = null;
            return that();
        }
        public BrandedFabElementBuilder branded() {
            return new BrandedFabElementBuilder();
        }
        @Override public PlainFabElementBuilder that() {
            return this;
        }
        @Override public MdPlainFabElement element() {
            return that.element();
        }
        public enum Size {
            Small("small"), Medium("medium"), Large("large");
            private final String value;
            Size(String value) {
                this.value = value;
            }
            public String toString() {
                return value;
            }
        }
    }
    final class BrandedFabElementBuilder implements FabElementBuilder<MdBrandedFabElement, BrandedFabElementBuilder> {
        private final HTMLContainerBuilder<MdBrandedFabElement> that = htmlContainer("md-branded-fab", MdBrandedFabElement.class);
        public BrandedFabElementBuilder size(Size size) {
            if(size!=null) element().size = size.toString();
            else element().size = null;
            return that();
        }
        @Override public BrandedFabElementBuilder that() {
            return this;
        }
        @Override public MdBrandedFabElement element() {
            return that.element();
        }
        public enum Size {
            Medium("medium"), Large("large");
            private final String value;
            Size(String value) {
                this.value = value;
            }
            public String toString() {
                return value;
            }
        }
    }
    enum Variant {
        Primary("primary"), Secondary("secondary"), Tertiary("tertiary");
        private final String value;
        Variant(String value) {
            this.value = value;
        }
        public String toString() {
            return value;
        }
    }
}
