package net.sayaya.ui.button;

import elemental2.dom.HTMLElement;

import static org.jboss.elemento.Elements.htmlElement;

public class OutlinedButtonElementBuilder extends AbstractButtonBuilder<OutlinedButtonElementBuilder> {
    public OutlinedButtonElementBuilder() {
        super(htmlElement("md-outlined-button", HTMLElement.class));
    }
}