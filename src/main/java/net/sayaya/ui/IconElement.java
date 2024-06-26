package net.sayaya.ui;

import elemental2.dom.HTMLElement;
import org.jboss.elemento.HTMLContainerBuilder;

import static org.jboss.elemento.Elements.i;

public class IconElement extends HTMLElementBuilder<HTMLElement, IconElement> {
	public static IconElement icon(String icon) {
		return new IconElement(i().aria("hidden", "true").css("material-icons")).value(icon);
	}
	private final HTMLContainerBuilder<? extends HTMLElement> _this;
	protected IconElement(HTMLContainerBuilder<HTMLElement> e) {
		super(e);
		_this = e;
	}
	public IconElement value(String icon) {
		element().textContent = icon;
		return this;
	}
	public IconElement selectable() {
		element().tabIndex = 0;
		style("outline: none;");
		return this;
	}
	@Override
	public IconElement that() {
		return this;
	}
}
