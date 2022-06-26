package net.sayaya.ui;

import elemental2.dom.EventListener;
import elemental2.dom.HTMLButtonElement;
import elemental2.dom.HTMLElement;
import net.sayaya.ui.mdc.MDCRipple;
import org.gwtproject.event.shared.HandlerRegistration;
import org.jboss.elemento.HtmlContentBuilder;

import static org.jboss.elemento.Elements.div;
import static org.jboss.elemento.Elements.span;

public class ButtonElementFloating extends HTMLElementBuilder<HTMLButtonElement, ButtonElementFloating> implements ButtonElement {
	private final HtmlContentBuilder<HTMLButtonElement> _this;
	ButtonElementFloating(HtmlContentBuilder<HTMLButtonElement> e, IconElement iconElement) {
		super(e);
		_this = e;
		var icon = span().add(iconElement.element().textContent);
		for(var css: iconElement.element().classList.asList()) icon.css(css);
		_this.css("mdc-fab")
				.add(div().css("mdc-fab__ripple"))
				.add(icon.css("mdc-fab__icon"));
		new MDCRipple(element());
	}
	@Override
	public final ButtonElementFloating enabled(boolean enabled) {
		if(enabled) this.attr("disabled", null);
		else _this.attr("disabled", "true");
		return that();
	}
	@Override
	public final ButtonElementFloating text(String text) {
		var labels = element().getElementsByClassName("mdc-fab__label");
		HTMLElement label = null;
		if(labels==null || labels.length<=0) {
			label = span().css("mdc-fab__label").element();
			element().appendChild(label);
			element().classList.add("mdc-fab--extended");
		} else label = (HTMLElement) labels.item(0);
		if(text==null || text.isEmpty()) {
			label.remove();
			element().classList.remove("mdc-fab--extended");
		} else label.textContent = text;
		return that();
	}
	@Override
	public String text() {
		var labels = element().getElementsByClassName("mdc-fab__label");
		HTMLElement label = null;
		if(labels==null && labels.length<=0) return null;
		else return labels.item(0).textContent;
	}
	@Override
	public ButtonElementFloating that() {
		return this;
	}

	@Override
	public ButtonElementFloating accessKey(char key) {
		element().accessKey = String.valueOf(key);
		return that();
	}
	@Override
	public ButtonElementFloating focus() {
		element().focus();
		return that();
	}
	@Override
	public HandlerRegistration onClick(EventListener listener) {
		return onClick(_this.element(), listener);
	}
}
