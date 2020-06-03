package net.sayaya.ui;

import elemental2.dom.HTMLElement;
import org.jboss.elemento.HtmlContentBuilder;

import static org.jboss.elemento.Elements.i;

public class Icon extends HTMLElementBuilder<HTMLElement, Icon> {
	public static Icon icon(String icon) {
		return new Icon(i().css("material-icons")).value(icon);
	}
	private final HtmlContentBuilder<HTMLElement> _this;
	private Icon(HtmlContentBuilder<HTMLElement> e) {
		super(e);
		_this = e;
		layout();
	}
	private void layout() {

	}
	public Icon value(String icon) {
		element().textContent = icon;
		return this;
	}

	@Override
	public Icon that() {
		return this;
	}
}
