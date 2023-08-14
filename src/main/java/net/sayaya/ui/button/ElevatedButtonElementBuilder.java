package net.sayaya.ui.button;

import elemental2.dom.HTMLElement;

import static org.jboss.elemento.Elements.htmlElement;

public class ElevatedButtonElementBuilder extends AbstractButtonBuilder<ElevatedButtonElementBuilder> {
    public ElevatedButtonElementBuilder() {
        super(htmlElement("md-elevated-button", HTMLElement.class));
    }
}
