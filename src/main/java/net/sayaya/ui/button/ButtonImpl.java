package net.sayaya.ui.button;

import elemental2.dom.HTMLElement;

public class ButtonImpl extends Button<ButtonImpl> {
	ButtonImpl(String... classes) {
		super(classes);
	}
	ButtonImpl setIcon(HTMLElement icon) {
		if(icon!=null) this.element().insertBefore(icon, this.element().firstChild);
		return this;
	}
	ButtonImpl setText(String text) {
		this.element().innerHTML = text;
		return this;
	}
}
