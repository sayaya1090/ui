package net.sayaya.ui.chip;

import elemental2.dom.HTMLElement;
import elemental2.dom.HTMLLabelElement;
import net.sayaya.ui.IsHTMLElement;
import net.sayaya.ui.style.Style;

public abstract class Chip<W extends Chip<W>> implements IsHTMLElement<HTMLElement, W> {
	public abstract W text(String text);
	public abstract String text();
	abstract HTMLLabelElement label();
	final static class ChipImpl extends Chip<ChipImpl> {
		private final HTMLLabelElement label = org.jboss.gwt.elemento.core.Elements.label().element();
		private final HTMLElement _this = org.jboss.gwt.elemento.core.Elements.span().add(label).element();
		private final Style style = Style.build().color("rgb(35, 47, 52)")
										 .backgroundColor("rgba(35, 47, 52, 0.12)")
										 .cursor("pointer")
										 .whiteSpace("nowrap")
										 .fontFamily("'Montserrat', 'Noto Sans KR', sans-serif")
										 .fontWeight("normal")
										 .fontSize("14px").borderRadius("14px")
										 .outlineStyle("none")
										 .paddingLeft("10px").paddingRight("10px").paddingTop("5px").paddingBottom("5px")
										 .marginLeft("0").marginRight("10px")
										 .letterSpacing("1px");
		ChipImpl() {
			style.apply(_this.style);
		}
		@Override
		HTMLLabelElement label() {
			return label;
		}
		@Override
		public ChipImpl text(String text) {
			label.textContent = text;
			return this;
		}
		@Override
		public String text() {
			return label.textContent;
		}
		@Override
		public HTMLElement element() {
			return _this;
		}
	}
}
