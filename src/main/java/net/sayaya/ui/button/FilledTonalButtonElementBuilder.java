package net.sayaya.ui.button;

import elemental2.dom.HTMLElement;

import static org.jboss.elemento.Elements.htmlElement;

public class FilledTonalButtonElementBuilder extends AbstractButtonBuilder<FilledTonalButtonElementBuilder> {
    public FilledTonalButtonElementBuilder() {
        super(htmlElement("md-filled-tonal-button", HTMLElement.class));
    }
}