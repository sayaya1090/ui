package net.sayaya.ui;

import com.google.gwt.core.client.Scheduler;
import elemental2.dom.*;
import elemental2.svg.SVGElement;
import elemental2.svg.SVGPathElement;
import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;
import jsinterop.base.JsPropertyMap;
import lombok.Setter;
import lombok.experimental.Accessors;
import net.sayaya.ui.Animation;
import net.sayaya.ui.event.HasAttachHandlers;
import net.sayaya.ui.event.HasDetachHandlers;
import org.gwtproject.event.shared.HandlerRegistration;
import org.jboss.elemento.EventType;
import org.jboss.elemento.HtmlContentBuilder;
import org.jboss.elemento.IsElement;

import static net.sayaya.ui.Animation.animate;
import static org.jboss.elemento.Elements.*;
import static org.jboss.elemento.EventType.bind;

public class Chip extends HTMLElementBuilder<HTMLDivElement, Chip> implements HasAttachHandlers, HasDetachHandlers {
	public static Chip chip(String text) {
		Chip elem = new Chip(div().css("mdc-chip").attr("role", "row")).text(text);
		bind(elem, "DOMNodeInserted", evt->{
			elem._mdc = inject(elem.element());
		});
		return elem;
	}
	public static ChipCheckable check(String text) {
		ChipCheckable elem = new ChipCheckable(div().css("mdc-chip").attr("role", "row"));
		elem.text(text);
		bind(elem, "DOMNodeInserted", evt->{
			((Chip)elem)._mdc = inject(elem.element());
			((Chip)elem)._mdc.setSelectedFromChipSet(elem.value);
		});
		return elem;
	}
	private static native MdcChip inject(Element elem) /*-{
        return $wnd.mdc.chips.MDCChip.attachTo(elem);
    }-*/;
	private final HtmlContentBuilder<HTMLDivElement> ripple = div().css("mdc-chip__ripple");
	private IsElement<?> iconBefore;
	private final HtmlContentBuilder<HTMLElement> label = span().css("mdc-chip__text");
	private final HtmlContentBuilder<HTMLElement> btn = span().css("mdc-chip__primary-action").attr("role", "checkbox").attr("tabindex", "0")
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
	private final static String SVG_NAMESPACE = "http://www.w3.org/2000/svg";
	public static class ChipCheckable extends Chip {
		private final IsElement<?> check = span().css("mdc_chip__checkmark").add(checkmark());
		private boolean value = false;
		private ChipCheckable(HtmlContentBuilder<HTMLDivElement> e) {
			super(e);
		}
		@Override
		protected void layout() {
			clear();
			super._this.add(super.ripple);
			if(super.iconBefore!=null) super._this.add(super.iconBefore);
			super._this.add(check);
			super._this.add(super.cell);
			if(super.iconTrailing!=null) super._this.add(super.iconTrailing);
		}
		public ChipCheckable select(boolean select) {
			value = select;
			if(super._mdc!=null) super._mdc.setSelectedFromChipSet(select);
			return that();
		}
		@Override
		public ChipCheckable that() {
			return this;
		}
		private static SVGElement checkmark() {
			SVGPathElement path = (SVGPathElement) DomGlobal.document.createElementNS(SVG_NAMESPACE, "path");
			path.classList.add("mdc-chip__checkmark-path");
			path.setAttribute("d", "M1.73,12.91 8.1,19.28 22.79,4.59");
			path.setAttribute("fill", "none");
			path.setAttribute("stroke", "black");

			SVGElement svg = (SVGElement) DomGlobal.document.createElementNS(SVG_NAMESPACE, "svg");
			svg.classList.add("mdc-chip__checkmark-svg");
			svg.setAttribute( "viewBox", "-2 -3 30 30");
			svg.appendChild(path);
			return svg;
		}
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
	private static final class MdcChip {
		public native void beginExit();
		public native void focusPrimaryAction();
		public native void focusTrailingAction();
		public native void removeFocus();
		public native void setSelectedFromChipSet(boolean selected);
	}
}
