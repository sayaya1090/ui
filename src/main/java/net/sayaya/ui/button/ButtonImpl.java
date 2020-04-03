package net.sayaya.ui.button;

import elemental2.dom.DomGlobal;
import elemental2.dom.HTMLDivElement;
import elemental2.dom.HTMLElement;
import org.jboss.gwt.elemento.core.Elements;

public class ButtonImpl extends Button<ButtonImpl> {
	ButtonImpl(String... classes) {
		super(classes);
	}
	ButtonImpl setIcon(HTMLElement icon) {
		this.element().insertBefore(icon, element().firstChild);
		return this;
	}
	ButtonImpl setText(String text) {
		this.element().innerHTML = text;
		return this;
	}

}
