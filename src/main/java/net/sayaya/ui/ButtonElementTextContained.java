package net.sayaya.ui;

import elemental2.dom.*;
import org.jboss.elemento.HtmlContentBuilder;

public class ButtonElementTextContained extends ButtonElementText {
	ButtonElementTextContained(HtmlContentBuilder<HTMLButtonElement> e) {
		super(e);
	}
	public ButtonElementTextContained rise() {
		this.element().classList.add("mdc-button--raised");
		this.element().classList.remove("mdc-button--unelevated");
		return this;
	}
	public ButtonElementTextContained unelevate() {
		this.element().classList.remove("mdc-button--raised");
		this.element().classList.add("mdc-button--unelevated");
		return this;
	}
}
