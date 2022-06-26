package net.sayaya.ui;

import elemental2.dom.EventListener;
import elemental2.dom.HTMLButtonElement;
import elemental2.dom.HTMLElement;
import net.sayaya.ui.mdc.MDCRipple;
import net.sayaya.ui.util.ElementUtil;
import org.gwtproject.event.shared.HandlerRegistration;
import org.jboss.elemento.HtmlContentBuilder;

import static org.jboss.elemento.Elements.div;
import static org.jboss.elemento.Elements.span;

public class ButtonElementText extends HTMLElementBuilder<HTMLButtonElement, ButtonElementText> implements ButtonElement {
	private final HtmlContentBuilder<HTMLElement> label;
	private final HtmlContentBuilder<HTMLButtonElement> _this;
	ButtonElementText(HtmlContentBuilder<HTMLButtonElement> e) {
		super(e);
		_this = e;
		label = span();
		_this.css("mdc-button")
				.add(div().css("mdc-button__ripple"))
				.add(label.css("mdc-button__label"));
		new MDCRipple(element());
	}
	@Override
	public final ButtonElementText enabled(boolean enabled) {
		if(enabled) this.attr("disabled", null);
		else _this.attr("disabled", "true");
		return that();
	}
	@Override
	public final ButtonElementText text(String text) {
		label.textContent(text);
		return that();
	}
	public String text() {
		return label.element().innerHTML;
	}
	public ButtonElementText before(IconElement iconElement) {
		if(iconElement !=null) element().insertBefore(iconElement.css("mdc-button__icon").element(), label.element());
		else {
			var icons = element().getElementsByClassName("mdc-button__icon");
			if(icons!=null) for(var icon: icons.asList()) if(ElementUtil.isPrev(icon, label.element())) icon.remove();
		}
		return that();
	}
	public ButtonElementText trailing(IconElement iconElement) {
		if(iconElement !=null) label.element().insertAdjacentElement("afterend", iconElement.css("mdc-button__icon").element());
		else {
			var icons = element().getElementsByClassName("mdc-button__icon");
			if(icons!=null) for(var icon: icons.asList()) if(ElementUtil.isNext(icon, label.element())) icon.remove();
		}
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
	public ButtonElementText that() {
		return this;
	}

	@Override
	public ButtonElementText accessKey(char key) {
		element().accessKey = String.valueOf(key);
		return that();
	}
	@Override
	public ButtonElementText focus() {
		element().focus();
		return that();
	}
}
