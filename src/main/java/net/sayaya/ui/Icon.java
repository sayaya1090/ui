package net.sayaya.ui;

import elemental2.dom.HTMLElement;

import static org.jboss.gwt.elemento.core.Elements.i;

public class Icon implements IsHTMLElement<HTMLElement, Icon> {
	private final HTMLElement _this = i().css("material-icons").element();
	Icon(String icon) {
		value(icon);
	}
	public Icon value(String icon) {
		element().textContent = icon;
		return this;
	}
	@Override
	public HTMLElement element() {
		return _this;
	}
	public static IconBuilder icon(String icon) {
		return new IconBuilder(icon);
	}
	public final static class IconBuilder {
		private String icon;
		private IconBuilder(String icon){
			this.icon = icon;
		}
		public IconBuilder icon(String icon) {
			this.icon = icon;
			return this;
		}
		public Icon build() {
			return new Icon(icon);
		}
	}
}
