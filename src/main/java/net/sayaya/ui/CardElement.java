package net.sayaya.ui;

import elemental2.dom.HTMLDivElement;
import org.jboss.elemento.HTMLContainerBuilder;

import static org.jboss.elemento.Elements.div;

public class CardElement extends HTMLElementBuilder<HTMLDivElement, CardElement> {
    public static CardElement card() {
        CardElement elem = new CardElement(div());
        return elem;
    }
    private CardElement(HTMLContainerBuilder<HTMLDivElement> e) {
        super(e.css("mdc-card"));
    }
    @Override
    public CardElement that() {
        return this;
    }
}
