package net.sayaya.ui.button;

import elemental2.dom.HTMLElement;

import static org.jboss.elemento.Elements.htmlElement;

public class FilledButtonElementBuilder extends AbstractButtonBuilder<FilledButtonElementBuilder> {
    public FilledButtonElementBuilder() {
        super(htmlElement("md-filled-button", HTMLElement.class));
    }
}