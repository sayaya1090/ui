package net.sayaya.ui;

import elemental2.dom.*;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;
import net.sayaya.ui.event.HasValueChangeHandlers;
import org.gwtproject.event.shared.HandlerRegistration;
import org.jboss.elemento.HtmlContentBuilder;
import org.jboss.elemento.IsElement;

import java.util.HashSet;
import java.util.Set;

import static org.jboss.elemento.Elements.div;
import static org.jboss.elemento.Elements.span;

public class ButtonToggle extends HTMLElementBuilder<HTMLButtonElement, ButtonToggle> implements Button, HasValueChangeHandlers<Boolean> {
	static native void inject(Element elem) /*-{
        $wnd.mdc.ripple.MDCRipple.attachTo(elem);
    }-*/;
	private final HtmlContentBuilder<HTMLDivElement> ripple = div().css("mdc-button__ripple");
	private IsElement<?> iconBefore;
	private final HtmlContentBuilder<HTMLElement> label = span().css("mdc-button__label");
	private IsElement<?> iconTrailing;
	private boolean value;
	private final HtmlContentBuilder<HTMLButtonElement> _this;
	ButtonToggle(HtmlContentBuilder<HTMLButtonElement> e) {
		super(e);
		_this = e;
		layout();
		onClick(evt->value(!value, evt));
	}
    private void layout() {
		clear();
		_this.add(ripple);
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
	public final ButtonToggle enabled(boolean enabled) {
		if(enabled) this.attr("disabled", null);
		else _this.attr("disabled", "true");
		return that();
	}
	@Override
	public final ButtonToggle text(String text) {
		label.textContent(text);
		return that();
	}
	public String text() {
		return label.element().innerHTML;
	}
	public ButtonToggle before(Icon icon) {
		if(icon!=null) icon.css("mdc-button__icon");
		this.iconBefore = icon;
		layout();
		return that();
	}
	public ButtonToggle trailing(Icon icon) {
		if(icon!=null) icon.css("mdc-button__icon");
		this.iconTrailing = icon;
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
	public ButtonToggle that() {
		return this;
	}

	@Override
	public ButtonToggle accessKey(char key) {
		element().accessKey = String.valueOf(key);
		return that();
	}
	@Override
	public ButtonToggle focus() {
		element().focus();
		return that();
	}
	private void fire(Event event) {
		HasValueChangeHandlers.ValueChangeEvent<Boolean> e = HasValueChangeHandlers.ValueChangeEvent.event(event, value());
		for(HasValueChangeHandlers.ValueChangeEventListener<Boolean> listener: listeners) listener.handle(e);
	}
	public ButtonToggle value(boolean value) {
		return value(value, new CustomEvent<Boolean>("change"));
	}
	public ButtonToggle value(boolean value, Event evt) {
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
