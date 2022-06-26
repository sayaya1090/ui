package net.sayaya.ui;

import com.google.gwt.core.client.Scheduler;
import elemental2.dom.*;
import elemental2.svg.SVGElement;
import elemental2.svg.SVGPathElement;
import jsinterop.base.JsPropertyMap;
import net.sayaya.ui.event.HasAttachHandlers;
import net.sayaya.ui.event.HasDetachHandlers;
import net.sayaya.ui.event.HasValueChangeHandlers;
import org.gwtproject.event.shared.HandlerRegistration;
import org.jboss.elemento.EventType;
import org.jboss.elemento.HtmlContentBuilder;

import java.util.HashSet;
import java.util.Set;

import static net.sayaya.ui.Animation.animate;
import static org.jboss.elemento.Elements.span;

public final class ChipElementCheckable extends HTMLElementBuilder<HTMLDivElement, ChipElementCheckable> implements HasAttachHandlers, HasDetachHandlers, HasValueChangeHandlers<Boolean> {
	private final static String SVG_NAMESPACE = "http://www.w3.org/2000/svg";
	private final HtmlContentBuilder<HTMLElement> ripple = span();
	private final HtmlContentBuilder<HTMLElement> graphic = span();
	private final HtmlContentBuilder<HTMLElement> check = span();
	private final HtmlContentBuilder<HTMLElement> label = span();
	private final HtmlContentBuilder<HTMLElement> btn = span();
	private final HtmlContentBuilder<HTMLDivElement> _this;
	private boolean value;
	ChipElementCheckable(HtmlContentBuilder<HTMLDivElement> e) {
		super(e);
		_this = e.css("mdc-chip")
				.add(btn.css("mdc-chip__action", "mdc-chip__action--primary").attr("role", "option").attr("tabindex", "0")
						.add(ripple.css("mdc-chip__ripple", "mdc-chip__ripple--primary"))
						.add(graphic.css("mdc-chip__graphic").add(check.css("mdc_chip__checkmark").add(checkmark())))
						.add(label.css("mdc-chip__text-label")));
		on(EventType.click, evt->{
			this.value = !value;
			Scheduler.get().scheduleDeferred(this::fire);
		});
	}
	public ChipElementCheckable text(String text) {
		label.textContent(text);
		return that();
	}
	public String text() {
		return label.element().innerHTML;
	}
	public ChipElementCheckable before(IconElement iconElement) {
		if(iconElement !=null) graphic.element().append(iconElement.css("mdc-chip__icon", "mdc-chip__icon--leading").element());
		else {
			var icons = element().getElementsByClassName("mdc-chip__icon--leading");
			if(icons!=null) for(var icon: icons.asList()) icon.remove();
		}
		return that();
	}
	public ChipElementCheckable trailing(IconElement iconElement) {
		if(iconElement !=null) label.element().insertAdjacentElement("afterend", iconElement.css("mdc-chip__icon", "mdc-chip__icon--trailing").element());
		else {
			var icons = element().getElementsByClassName("mdc-chip__icon--trailing");
			if(icons!=null) for(var icon: icons.asList()) icon.remove();
		}
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
		//_mdc.selected = value;
		this.value = value;
		return this;
	}
	public Boolean value() {
		// return _mdc.selected;
		return this.value;
	}
	private void fire() {
		CustomEvent<Boolean> evt = new CustomEvent<>("change");
		DomGlobal.console.log(value());
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
