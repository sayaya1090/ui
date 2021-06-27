package net.sayaya.ui;

import elemental2.dom.*;
import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;
import jsinterop.base.JsPropertyMap;
import lombok.Setter;
import lombok.experimental.Accessors;
import net.sayaya.ui.event.HasAttachHandlers;
import net.sayaya.ui.event.HasDetachHandlers;
import org.gwtproject.event.shared.HandlerRegistration;
import org.jboss.elemento.EventType;
import org.jboss.elemento.HtmlContentBuilder;
import org.jboss.elemento.IsElement;

import static net.sayaya.ui.Animation.animate;
import static org.jboss.elemento.Elements.*;

public class ChipElement extends HTMLElementBuilder<HTMLDivElement, ChipElement> implements HasAttachHandlers, HasDetachHandlers {
	public static ChipElement chip(String text) {
		ChipElement elem = new ChipElement(div().css("mdc-chip").attr("role", "row")).text(text);
		elem._mdc = inject(elem.element());
		return elem;
	}
	public static ChipElementCheckable check(String text) {
		ChipElementCheckable elem = new ChipElementCheckable(div().css("mdc-chip").attr("role", "row"));
		elem.text(text);
		// elem._mdc = inject(elem.element());
		return elem;
	}
	private static native MdcChip inject(Element elem) /*-{
        return $wnd.mdc.chips.MDCChip.attachTo(elem);
    }-*/;
	private final HtmlContentBuilder<HTMLDivElement> ripple = div().css("mdc-chip__ripple");
	private IsElement<?> iconBefore;
	private final HtmlContentBuilder<HTMLElement> label = span().css("mdc-chip__text");
	private final HtmlContentBuilder<HTMLElement> btn = span().css("mdc-chip__primary-action").attr("role", "button").attr("tabindex", "0")
															  .add(label);
	private final HtmlContentBuilder<HTMLElement> cell = span().attr("role", "gridcell")
															   .add(btn);
	private IsElement<?> iconTrailing;
	private final HtmlContentBuilder<HTMLDivElement> _this;
	private MdcChip _mdc;
	private ChipElement(HtmlContentBuilder<HTMLDivElement> e) {
		super(e);
		_this = e;
		layout();
	}
	protected void layout() {
		clear();
		_this.add(ripple);
		if(iconBefore!=null) _this.add(iconBefore);
		_this.add(cell);
		if(iconTrailing!=null) _this.add(iconTrailing);
	}
	public ChipElement text(String text) {
		label.textContent(text);
		return that();
	}
	public String text() {
		return label.element().innerHTML;
	}
	public ChipElement before(IconElement iconElement) {
		if(iconElement !=null) iconElement.css("mdc-chip__icon", "mdc-chip__icon--leading");
		this.iconBefore = iconElement;
		layout();
		return that();
	}
	public ChipElement trailing(IconElement iconElement) {
		if(iconElement !=null) iconElement.css("mdc-chip__icon", "mdc-chip__icon--trailing");
		this.iconTrailing = iconElement;
		layout();
		return that();
	}
	public ChipElement removable() {
		IconElement remove = IconElement.icon("close");
		remove.on(EventType.click, evt->{
			Animation.AnimationImpl fade = animate(element(), 150, JsPropertyMap.of("opacity", "1"), JsPropertyMap.of("opacity", "0"));
			if(fade!=null) fade.onfinish = () -> element().remove();
			else element().parentElement.removeChild(element());
		});
		trailing(remove);
		return this;
	}

	@Override
	public ChipElement that() {
		return this;
	}
	public ChipElement removeFocus() {
		_mdc.removeFocus();
		return that();
	}
	@Override
	public HandlerRegistration onAttach(EventListener listener) {
		return onAttach(element(), listener);
	}
	@Override
	public HandlerRegistration onDetach(EventListener listener) {
		return onDetach(element(), listener);
	}

	@JsType(isNative = true, namespace= JsPackage.GLOBAL, name="Object")
	@Setter(onMethod_ = {@JsOverlay})
	@Accessors(fluent=true)
	static final class MdcChip {
		public boolean selected;
		public native void beginExit();
		public native void focusPrimaryAction();
		public native void focusTrailingAction();
		public native void removeFocus();
		public native void setSelectedFromChipSet(boolean selected);
	}
}
