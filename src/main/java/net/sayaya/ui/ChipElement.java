package net.sayaya.ui;

import elemental2.dom.*;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;
import jsinterop.base.JsPropertyMap;
import net.sayaya.ui.event.HasAttachHandlers;
import net.sayaya.ui.event.HasDetachHandlers;
import org.gwtproject.event.shared.HandlerRegistration;
import org.jboss.elemento.EventType;
import org.jboss.elemento.HTMLContainerBuilder;

import static net.sayaya.ui.Animation.animate;
import static org.jboss.elemento.Elements.div;
import static org.jboss.elemento.Elements.span;

public class ChipElement extends HTMLElementBuilder<HTMLDivElement, ChipElement> implements HasAttachHandlers, HasDetachHandlers {
	public static ChipElement chip(String text) {
		return new ChipElement(div()).text(text);
	}
	public static ChipElementCheckable check(String text) {
		return new ChipElementCheckable(div()).text(text);
	}
	private final HTMLContainerBuilder<HTMLDivElement> ripple = div();
	private final HTMLContainerBuilder<HTMLElement> label = span();
	private final HTMLContainerBuilder<HTMLElement> btn = span();
	private final HTMLContainerBuilder<HTMLElement> cell = span();
	private final HTMLContainerBuilder<HTMLDivElement> _this;
	private final MDCChip _mdc;
	private ChipElement(HTMLContainerBuilder<HTMLDivElement> e) {
		super(e);
		_this = e;
		_this.css("mdc-chip").attr("role", "row")
				.add(ripple.css("mdc-chip__ripple"))
				.add(cell.attr("role", "gridcell")
				.add(btn.css("mdc-chip__primary-action").attr("role", "button").attr("tabindex", "0")
						.add(label.css("mdc-chip__text"))));
		_mdc = new MDCChip(element());
		DomGlobal.console.log(_mdc);
	}
	public ChipElement text(String text) {
		label.textContent(text);
		return that();
	}
	public String text() {
		return label.element().innerHTML;
	}
	public ChipElement before(IconElement iconElement) {
		if(iconElement !=null) element().insertBefore(iconElement.css("mdc-chip__icon", "mdc-chip__icon--leading").element(), cell.element());
		else {
			var icons = element().getElementsByClassName("mdc-chip__icon--leading");
			if(icons!=null) for(var icon: icons.asList()) icon.remove();
		}
		return that();
	}
	public ChipElement trailing(IconElement iconElement) {
		if(iconElement !=null) btn.element().insertAdjacentElement("afterend", iconElement.css("mdc-chip__icon", "mdc-chip__icon--trailing").element());
		else {
			var icons = element().getElementsByClassName("mdc-chip__icon--trailing");
			if(icons!=null) for(var icon: icons.asList()) icon.remove();
		}
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

	@JsType(isNative = true, namespace="mdc.chips")
	static final class MDCChip {
		@JsProperty public boolean selected;
		@JsProperty public MDCChipFoundation foundation;
		public MDCChip(Element elem){}
		public native void beginExit();
		public native void focusPrimaryAction();
		public native void focusTrailingAction();
		public native void removeFocus();
		public native void setSelectedFromChipSet(boolean selected);
	}
	@JsType(isNative = true, namespace= JsPackage.GLOBAL, name="Object")
	static final class MDCChipFoundation {
		public native void setSelected(boolean selected);
	}
}
