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

public class Chip extends HTMLElementBuilder<HTMLDivElement, Chip> implements HasAttachHandlers, HasDetachHandlers {
	public static Chip chip(String text) {
		Chip elem = new Chip(div().css("mdc-chip").attr("role", "row")).text(text);
		elem._mdc = inject(elem.element());
		return elem;
	}
	public static ChipCheckable check(String text) {
		ChipCheckable elem = new ChipCheckable(div().css("mdc-chip").attr("role", "row"));
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
	private Chip(HtmlContentBuilder<HTMLDivElement> e) {
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
	public Chip text(String text) {
		label.textContent(text);
		return that();
	}
	public String text() {
		return label.element().innerHTML;
	}
	public Chip before(Icon icon) {
		if(icon!=null) icon.css("mdc-chip__icon", "mdc-chip__icon--leading");
		this.iconBefore = icon;
		layout();
		return that();
	}
	public Chip trailing(Icon icon) {
		if(icon!=null) icon.css("mdc-chip__icon", "mdc-chip__icon--trailing");
		this.iconTrailing = icon;
		layout();
		return that();
	}
	public Chip removable() {
		Icon remove = Icon.icon("close");
		remove.on(EventType.click, evt->{
			Animation.AnimationImpl fade = animate(element(), 150, JsPropertyMap.of("opacity", "1"), JsPropertyMap.of("opacity", "0"));
			if(fade!=null) fade.onfinish = () -> element().remove();
			else element().parentElement.removeChild(element());
		});
		trailing(remove);
		return this;
	}

	@Override
	public Chip that() {
		return this;
	}
	public Chip removeFocus() {
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
