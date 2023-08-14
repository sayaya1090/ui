package net.sayaya.ui.button.icon;

import elemental2.dom.DomGlobal;
import elemental2.dom.Element;
import elemental2.dom.HTMLElement;

import static org.jboss.elemento.Elements.htmlElement;

public class PlainIconButtonElementBuilder extends AbstractIconButtonBuilder<PlainIconButtonElementBuilder> {
    public PlainIconButtonElementBuilder(Element icon) {
        super(htmlElement("md-icon-button", HTMLElement.class), icon);
        DomGlobal.console.log("md-icon-button");
    }
}