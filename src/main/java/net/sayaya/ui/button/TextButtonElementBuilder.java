package net.sayaya.ui.button;

import elemental2.dom.HTMLElement;

import static org.jboss.elemento.Elements.htmlElement;

public class TextButtonElementBuilder extends AbstractButtonBuilder<TextButtonElementBuilder> {
    public TextButtonElementBuilder() {
        super(htmlElement("md-text-button", HTMLElement.class));
    }
}