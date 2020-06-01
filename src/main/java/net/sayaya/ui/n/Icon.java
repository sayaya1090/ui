package net.sayaya.ui.n;

import elemental2.dom.HTMLElement;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.jboss.elemento.IsElement;

import static org.jboss.elemento.Elements.i;

public class Icon implements IsElement<HTMLElement> {
	public static IconBuilder icon(String icon) {
		return new IconBuilder(new Icon()).value(icon);
	}
	private final HTMLElement _this = i().css("material-icons").element();
	private Icon() {}
	public Icon value(String icon) {
		element().textContent = icon;
		return this;
	}
	@Override
	public HTMLElement element() {
		return _this;
	}

	@Setter
	@Accessors(fluent=true)
	public static class IconBuilder extends HTMLElementBuilder<HTMLElement, Icon> {
		private String value;
		private IconBuilder(Icon elem) {
			super(elem);
		}
		@Override
		public HTMLElement element() {
			if(value!=null) proxy().value(value);
			return proxy().element();
		}
	}
}
