package net.sayaya.ui;

import elemental2.dom.HTMLElement;
import org.jboss.elemento.HtmlContentBuilder;

import static org.jboss.elemento.Elements.i;

public class Icon extends HTMLElementBuilder<HTMLElement, Icon> {
	public static Icon icon(String icon) {
		return new Icon(i().css("material-icons")).value(icon);
	}
	private final HtmlContentBuilder<? extends HTMLElement> _this;
	protected Icon(HtmlContentBuilder<HTMLElement> e) {
		super(e);
		_this = e;
	}
	public Icon value(String icon) {
		element().textContent = icon;
		return this;
	}
	public Icon selectable() {
		element().tabIndex = 0;
		style("outline: none;");
		return this;
	}
	@Override
	public Icon that() {
		return this;
	}
}
