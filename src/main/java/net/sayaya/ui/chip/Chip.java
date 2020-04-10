package net.sayaya.ui.chip;

import elemental2.dom.Element;
import elemental2.dom.HTMLDivElement;
import elemental2.dom.HTMLElement;
import net.sayaya.ui.IsHTMLElement;

import static org.jboss.gwt.elemento.core.Elements.*;
import static org.jboss.gwt.elemento.core.Elements.i;

public class Chip implements IsHTMLElement<HTMLElement, Chip> {
	private final HTMLDivElement ripple = div().css("mdc-chip__ripple").element();
	private final HTMLElement text = span().css("mdc-chip__text").element();
	private final HTMLElement btn = span().css("mdc-chip__primary-action").attr("role", "button")
										  .add(text)
										  .element();
	private final HTMLElement cell = span().attr("role", "gridcell")
										   .add(btn)
										   .element();
	private final HTMLDivElement _this = div().css("mdc-chip").attr("role", "row")
											  .add(ripple)
											  .add(cell)
											  .element();
	Chip(String text) {
		this.text.innerHTML = text;
	}
	public static ChipBuilder chip() {
		return ChipBuilder.builder();
	}
	native static void inject(Element elem) /*-{
        $wnd.mdc.chips.MDCChip.attachTo(elem);
    }-*/;
	final void inject() {
		inject(_this);
	}
	public final Chip iconLeading(String icon) {
		HTMLElement i = i().css("material-icons", "mdc-chip__icon", "mdc-chip__icon--leading").add(icon).element();
		_this.insertBefore(i, cell);
		return self();
	}
	public final Chip iconTrailing(String icon) {
		HTMLElement i = i().css("material-icons", "mdc-chip__icon", "mdc-chip__icon--trailing").add(icon).element();
		_this.insertBefore(i, cell.nextSibling);
		return self();
	}
	public String value() {
		return this.text.innerHTML;
	}
	@Override
	public HTMLElement element() {
		return _this;
	}
}
