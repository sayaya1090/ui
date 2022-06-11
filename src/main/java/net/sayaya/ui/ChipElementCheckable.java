package net.sayaya.ui;

import com.google.gwt.core.client.Scheduler;
import elemental2.dom.*;
import elemental2.svg.SVGElement;
import elemental2.svg.SVGPathElement;
import jsinterop.base.JsPropertyMap;
import net.sayaya.ui.event.HasAttachHandlers;
import net.sayaya.ui.event.HasDetachHandlers;
import net.sayaya.ui.event.HasValueChangeHandlers;
import com.google.web.bindery.event.shared.HandlerRegistration;
import org.jboss.elemento.EventType;
import org.jboss.elemento.HtmlContentBuilder;
import org.jboss.elemento.IsElement;

import java.util.HashSet;
import java.util.Set;

import static net.sayaya.ui.Animation.animate;
import static org.jboss.elemento.Elements.div;
import static org.jboss.elemento.Elements.span;

public final class ChipElementCheckable extends HTMLElementBuilder<HTMLDivElement, ChipElementCheckable> implements HasAttachHandlers, HasDetachHandlers, HasValueChangeHandlers<Boolean> {
	private final static String SVG_NAMESPACE = "http://www.w3.org/2000/svg";
	private static native ChipElement.MdcChip inject(Element elem) /*-{
        return $wnd.mdc.chips.MDCChip.attachTo(elem);
    }-*/;
	private final HtmlContentBuilder<HTMLDivElement> ripple = div().css("mdc-chip__ripple");
	private IsElement<?> iconBefore;
	private final IsElement<?> check = span().css("mdc_chip__checkmark").add(checkmark());
	private final HtmlContentBuilder<HTMLElement> label = span().css("mdc-chip__text");
	private final HtmlContentBuilder<HTMLElement> btn = span().css("mdc-chip__primary-action").attr("role", "check").attr("tabindex", "0")
															  .add(label);
	private final HtmlContentBuilder<HTMLElement> cell = span().attr("role", "gridcell")
															   .add(btn);
	private IsElement<?> iconTrailing;
	private final HtmlContentBuilder<HTMLDivElement> _this;
	ChipElement.MdcChip _mdc;
	boolean value;
	ChipElementCheckable(HtmlContentBuilder<HTMLDivElement> e) {
		super(e);
		_this = e;
		layout();
		on(EventType.click, evt->Scheduler.get().scheduleDeferred(()->fire()));
	}
	protected void layout() {
		clear();
		_this.add(ripple);
		if(iconBefore!=null) _this.add(iconBefore);
		_this.add(check);
		_this.add(cell);
		if(iconTrailing!=null) _this.add(iconTrailing);
	}
	public ChipElementCheckable text(String text) {
		label.textContent(text);
		return that();
	}
	public String text() {
		return label.element().innerHTML;
	}
	public ChipElementCheckable before(IconElement iconElement) {
		if(iconElement !=null) iconElement.css("mdc-chip__icon", "mdc-chip__icon--leading");
		this.iconBefore = iconElement;
		layout();
		return that();
	}
	public ChipElementCheckable trailing(IconElement iconElement) {
		if(iconElement !=null) iconElement.css("mdc-chip__icon", "mdc-chip__icon--trailing");
		this.iconTrailing = iconElement;
		layout();
		return that();
	}
	public ChipElementCheckable removable() {
		IconElement remove = IconElement.icon("close");
		remove.on(EventType.click, evt->{
			Animation.AnimationImpl fade = animate(element(), 150, JsPropertyMap.of("opacity", "1"), JsPropertyMap.of("opacity", "0"));
			if(fade!=null) fade.onfinish = () -> element().remove();
			else element().parentElement.removeChild(element());
		});
		trailing(remove);
		return this;
	}
	public ChipElementCheckable value(boolean value) {
		this.value = value;
		if(_mdc!=null) _mdc.selected = value;
		return this;
	}
	public Boolean value() {
		if(_mdc!=null) return _mdc.selected;
		else return value;
	}
	private void fire() {
		CustomEvent<Boolean> evt = new CustomEvent<>("change");
		evt.detail = value();
		ValueChangeEvent<Boolean> e = ValueChangeEvent.event(evt, value());
		for(ValueChangeEventListener<Boolean> listener: listeners) listener.handle(e);
	}
	private final Set<ValueChangeEventListener<Boolean>> listeners = new HashSet<>();
	@Override
	public HandlerRegistration onValueChange(ValueChangeEventListener<Boolean> listener) {
		listeners.add(listener);
		return ()->listeners.remove(listener);
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

	@Override
	public HandlerRegistration onAttach(EventListener listener) {
		return onAttach(element(), listener);
	}
	@Override
	public HandlerRegistration onDetach(EventListener listener) {
		return onDetach(element(), listener);
	}
	@Override
	public ChipElementCheckable that() {
		return this;
	}
}
