package net.sayaya.ui.button.icon;

import elemental2.dom.DomGlobal;
import elemental2.dom.Element;
import elemental2.dom.HTMLElement;

import static org.jboss.elemento.Elements.htmlElement;

public class OutlinedIconButtonElementBuilder extends AbstractIconButtonBuilder<OutlinedIconButtonElementBuilder> {
    public OutlinedIconButtonElementBuilder(Element icon) {
        super(htmlElement("md-outlined-icon-button", HTMLElement.class), icon);
        DomGlobal.console.log("md-outlined-icon-button");
    }
}