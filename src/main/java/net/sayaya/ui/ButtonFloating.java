package net.sayaya.ui;

import elemental2.dom.*;

import org.gwtproject.event.shared.HandlerRegistration;
import org.jboss.elemento.HtmlContentBuilder;
import org.jboss.elemento.IsElement;

import static org.jboss.elemento.Elements.*;

public class ButtonFloating extends HTMLElementBuilder<HTMLButtonElement, ButtonFloating> implements Button {
	private final HtmlContentBuilder<HTMLButtonElement> _this;
	private final HtmlContentBuilder<HTMLDivElement> ripple = div().css("mdc-button__ripple");
	private final IsElement<?> icon;
	private HtmlContentBuilder<HTMLElement> label = null;
	ButtonFloating (HtmlContentBuilder<HTMLButtonElement> e, Icon icon) {
		super(e);
		_this = e;
		this.icon = icon.css("mdc-fab__icon");
		layout();
	}
	private void layout() {
		clear();
		_this.add(ripple).add(icon);
		if(label!=null) _this.add(label);
	}
	@Override
	public final ButtonFloating enabled(boolean enabled) {
		if(enabled) this.attr("disabled", null);
		else _this.attr("disabled", "true");
		return that();
	}
	@Override
	public final ButtonFloating text(String text) {
		if(text == null && label == null) return that();
		else if(text == null) {
			ncss("mdc-fab--extended");
			label = null;
			layout();
			return that();
		} else if(label == null) {
			css("mdc-fab--extended");
			label = span().css("mdc-fab__label");
			layout();
		}
		label.textContent(text);
		return that();
	}
	@Override
	public String text() {
		if(label==null) return null;
		return label.element().innerHTML;
	}
	@Override
	public ButtonFloating that() {
		return this;
	}

	@Override
	public ButtonFloating accessKey(char key) {
		element().accessKey = String.valueOf(key);
		return that();
	}
	@Override
	public ButtonFloating focus() {
		element().focus();
		return that();
	}
	@Override
	public HandlerRegistration onClick(EventListener listener) {
		return onClick(_this.element(), listener);
	}
}
