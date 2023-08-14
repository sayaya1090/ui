package net.sayaya.ui.button.icon;

import elemental2.dom.DomGlobal;
import elemental2.dom.Element;
import elemental2.dom.HTMLElement;

import static org.jboss.elemento.Elements.htmlElement;

public class FilledTonalIconButtonElementBuilder extends AbstractIconButtonBuilder<FilledTonalIconButtonElementBuilder> {
    public FilledTonalIconButtonElementBuilder(Element icon) {
        super(htmlElement("md-filled-tonal-icon-button", HTMLElement.class), icon);
        DomGlobal.console.log("md-filled-tonal-icon-button");
    }
}