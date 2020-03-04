package net.sayaya.ui.chip;

import elemental2.dom.HTMLElement;
import elemental2.dom.HTMLLabelElement;
import net.sayaya.ui.IsHTMLElement;
import net.sayaya.ui.style.Style;
import static org.jboss.gwt.elemento.core.Elements.*;

public abstract class Chip<W extends Chip<W>> implements IsHTMLElement<HTMLElement, W> {
	public abstract W setText(String text);
	public abstract String getText();
	abstract HTMLLabelElement getLabel();
	final static class ChipImpl extends Chip<ChipImpl> {
		private final HTMLLabelElement label = label().element();
		private final HTMLElement _this = span().add(label).element();
		private final Style style = new Style().setColor("rgb(35, 47, 52)")
											   .setBackgroundColor("rgba(35, 47, 52, 0.12)")
											   .setCursor("pointer")
											   .setWhiteSpace("nowrap").setFontFamily("'Montserrat', 'Noto Sans KR', sans-serif")
											   .setFontWeight("normal")
											   .setFontSize("14px").setBorderRadius("14px")
											   .setOutlineStyle("none")
											   .setPaddingLeft("10px").setPaddingRight("10px").setPaddingTop("5px").setPaddingBottom("5px")
											   .setMarginLeft("0").setMarginRight("10px")
											   .setLetterSpacing("1px");
		ChipImpl() {
			style.apply(_this.style);
		}
		@Override
		HTMLLabelElement getLabel() {
			return label;
		}
		@Override
		public ChipImpl setText(String text) {
			label.textContent = text;
			return this;
		}
		@Override
		public String getText() {
			return label.textContent;
		}
		@Override
		public HTMLElement element() {
			return _this;
		}
	}
}
