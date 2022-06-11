package net.sayaya.ui;

import com.google.web.bindery.event.shared.HandlerRegistration;
import elemental2.dom.*;

import org.jboss.elemento.HtmlContentBuilder;
import org.jboss.elemento.IsElement;

import static org.jboss.elemento.Elements.*;

public class ButtonElementText extends HTMLElementBuilder<HTMLButtonElement, ButtonElementText> implements ButtonElement {
	static native void inject(Element elem) /*-{
        $wnd.mdc.ripple.MDCRipple.attachTo(elem);
    }-*/;
	private final HtmlContentBuilder<HTMLDivElement> ripple = div().css("mdc-button__ripple");
	private IsElement<?> iconBefore;
	private final HtmlContentBuilder<HTMLElement> label = span().css("mdc-button__label");
	private IsElement<?> iconTrailing;
	private final HtmlContentBuilder<HTMLButtonElement> _this;
	ButtonElementText(HtmlContentBuilder<HTMLButtonElement> e) {
		super(e);
		_this = e;
		layout();
	}
    private void layout() {
		clear();
		_this.add(ripple);
		if(iconBefore!=null) _this.add(iconBefore);
		_this.add(label);
		if(iconTrailing!=null) _this.add(iconTrailing);
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
		if(iconElement !=null) iconElement.css("mdc-button__icon");
		this.iconBefore = iconElement;
		layout();
		return that();
	}
	public ButtonElementText trailing(IconElement iconElement) {
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
