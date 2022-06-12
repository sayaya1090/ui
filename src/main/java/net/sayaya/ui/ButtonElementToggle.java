package net.sayaya.ui;

import elemental2.dom.*;
import net.sayaya.ui.event.HasValueChangeHandlers;
import com.google.web.bindery.event.shared.HandlerRegistration;
import net.sayaya.ui.mdc.MDCRipple;
import org.jboss.elemento.HtmlContentBuilder;
import org.jboss.elemento.IsElement;

import java.util.HashSet;
import java.util.Set;

import static org.jboss.elemento.Elements.div;
import static org.jboss.elemento.Elements.span;

public class ButtonElementToggle extends HTMLElementBuilder<HTMLButtonElement, ButtonElementToggle> implements ButtonElement, HasValueChangeHandlers<Boolean> {
	private final HtmlContentBuilder<HTMLDivElement> ripple = div().css("mdc-button__ripple");
	private IsElement<?> iconBefore;
	private final HtmlContentBuilder<HTMLElement> label = span().css("mdc-button__label");
	private IsElement<?> iconTrailing;
	private boolean value;
	private final HtmlContentBuilder<HTMLButtonElement> _this;
	ButtonElementToggle(HtmlContentBuilder<HTMLButtonElement> e) {
		super(e);
		_this = e;
		layout();
		onClick(evt->value(!value, evt));
		new MDCRipple(element());
	}
    private void layout() {
		clear();
		_this.css("mdc-button", "mdc-button--outlined", "mdc-button-toggle").add(ripple);
		if(iconBefore!=null) _this.add(iconBefore);
		_this.add(label);
		if(iconTrailing!=null) _this.add(iconTrailing);
	}
	private void update() {
		attr("value", value?"true":"false");
		if(value) css("mdc-button--on");
		else ncss("mdc-button--on");
	}
	@Override
	public final ButtonElementToggle enabled(boolean enabled) {
		if(enabled) this.attr("disabled", null);
		else _this.attr("disabled", "true");
		return that();
	}
	@Override
	public final ButtonElementToggle text(String text) {
		label.textContent(text);
		return that();
	}
	public String text() {
		return label.element().innerHTML;
	}
	public ButtonElementToggle before(IconElement iconElement) {
		if(iconElement !=null) iconElement.css("mdc-button__icon");
		this.iconBefore = iconElement;
		layout();
		return that();
	}
	public ButtonElementToggle trailing(IconElement iconElement) {
		if(iconElement !=null) iconElement.css("mdc-button__icon");
		this.iconTrailing = iconElement;
		layout();
		return that();
	}
	@Override
	public HandlerRegistration onClick(EventListener listener) {
		return onClick(_this.element(), listener);
	}

	@Override
	public HTMLButtonElement element() {
		return _this.element();
	}

	@Override
	public ButtonElementToggle that() {
		return this;
	}

	@Override
	public ButtonElementToggle accessKey(char key) {
		element().accessKey = String.valueOf(key);
		return that();
	}
	@Override
	public ButtonElementToggle focus() {
		element().focus();
		return that();
	}
	private void fire(Event event) {
		HasValueChangeHandlers.ValueChangeEvent<Boolean> e = HasValueChangeHandlers.ValueChangeEvent.event(event, value());
		for(HasValueChangeHandlers.ValueChangeEventListener<Boolean> listener: listeners) listener.handle(e);
	}
	public ButtonElementToggle value(boolean value) {
		return value(value, new CustomEvent<Boolean>("change"));
	}
	public ButtonElementToggle value(boolean value, Event evt) {
		this.value = value;
		update();
		fire(evt);
		return that();
	}
	@Override
	public Boolean value() {
		return value;
	}
	private final Set<ValueChangeEventListener<Boolean>> listeners = new HashSet<>();
	@Override
	public HandlerRegistration onValueChange(ValueChangeEventListener<Boolean> listener) {
		listeners.add(listener);
		return ()->listeners.remove(listener);
	}
}
