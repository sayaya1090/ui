package net.sayaya.ui;

import com.google.gwt.core.client.Scheduler;
import elemental2.dom.CustomEvent;
import elemental2.dom.EventListener;
import elemental2.dom.HTMLDivElement;
import elemental2.dom.HTMLElement;
import jsinterop.base.JsPropertyMap;
import net.sayaya.ui.event.HasAttachHandlers;
import net.sayaya.ui.event.HasDetachHandlers;
import net.sayaya.ui.event.HasValueChangeHandlers;
import org.gwtproject.event.shared.HandlerRegistration;
import org.jboss.elemento.EventType;
import org.jboss.elemento.HTMLContainerBuilder;

import java.util.HashSet;
import java.util.Set;

import static net.sayaya.ui.Animation.animate;
import static net.sayaya.ui.svg.SvgBuilder.svg;
import static net.sayaya.ui.svg.SvgPathBuilder.path;
import static org.jboss.elemento.Elements.span;

public final class ChipElementCheckable extends HTMLElementBuilder<HTMLDivElement, ChipElementCheckable> implements HasAttachHandlers, HasDetachHandlers, HasValueChangeHandlers<Boolean> {
	private final HTMLContainerBuilder<HTMLElement> ripple = span();
	private final HTMLContainerBuilder<HTMLElement> graphic = span();
	private final HTMLContainerBuilder<HTMLElement> check = span();
	private final HTMLContainerBuilder<HTMLElement> label = span();
	private final HTMLContainerBuilder<HTMLElement> btn = span();
	private final HTMLContainerBuilder<HTMLDivElement> _this;
	private ChipElement.MDCChip _mdc;
	ChipElementCheckable(HTMLContainerBuilder<HTMLDivElement> e) {
		super(e);
		_this = e.css("mdc-chip")
				.add(btn.css("mdc-chip__action", "mdc-chip__action--primary").attr("role", "option").attr("tabindex", "0")
						.add(ripple.css("mdc-chip__ripple", "mdc-chip__ripple--primary"))
						.add(graphic.css("mdc-chip__graphic")
								.add(check.css("mdc_chip__checkmark")
										.add(svg().css("mdc-chip__checkmark-svg").viewBox(-2, -8, 30, 30)
												.add(path().css("mdc-chip__checkmark-path")
														.d("M1.73,12.91 8.1,19.28 22.79,4.59")
														.fill("none").stroke("black")).element())))
						.add(label.css("mdc-chip__text-label").style("line-height: 2em;")));
		onAttach(evt->_mdc = new ChipElement.MDCChip(element()));
		on(EventType.click, evt-> Scheduler.get().scheduleDeferred(this::fire));
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
		Scheduler.get().scheduleDeferred(()->{
			_mdc.foundation.setSelected(value);
			fire();
		});
		return this;
	}
	public Boolean value() {
		return _mdc.selected;
	}
	private void fire() {
		CustomEvent<Boolean> evt = new CustomEvent<>("change");
		var value = value();
		evt.detail = value;
		ValueChangeEvent<Boolean> e = ValueChangeEvent.event(evt, value);
		for(ValueChangeEventListener<Boolean> listener: listeners) listener.handle(e);
	}
	private final Set<ValueChangeEventListener<Boolean>> listeners = new HashSet<>();
	@Override
	public HandlerRegistration onValueChange(ValueChangeEventListener<Boolean> listener) {
		listeners.add(listener);
		return ()->listeners.remove(listener);
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
