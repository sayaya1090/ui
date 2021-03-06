package net.sayaya.ui;

import elemental2.dom.*;

import org.gwtproject.event.shared.HandlerRegistration;
import org.jboss.elemento.HtmlContentBuilder;
import org.jboss.elemento.IsElement;

import static org.jboss.elemento.Elements.*;

public class ButtonText extends HTMLElementBuilder<HTMLButtonElement, ButtonText> implements Button {
	static native void inject(Element elem) /*-{
        $wnd.mdc.ripple.MDCRipple.attachTo(elem);
    }-*/;
	private final HtmlContentBuilder<HTMLDivElement> ripple = div().css("mdc-button__ripple");
	private IsElement<?> iconBefore;
	private final HtmlContentBuilder<HTMLElement> label = span().css("mdc-button__label");
	private IsElement<?> iconTrailing;
	private final HtmlContentBuilder<HTMLButtonElement> _this;
	ButtonText(HtmlContentBuilder<HTMLButtonElement> e) {
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
	public final ButtonText enabled(boolean enabled) {
		if(enabled) this.attr("disabled", null);
		else _this.attr("disabled", "true");
		return that();
	}
	@Override
	public final ButtonText text(String text) {
		label.textContent(text);
		return that();
	}
	public String text() {
		return label.element().innerHTML;
	}
	public ButtonText before(Icon icon) {
		if(icon!=null) icon.css("mdc-button__icon");
		this.iconBefore = icon;
		layout();
		return that();
	}
	public ButtonText trailing(Icon icon) {
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
	public ButtonText that() {
		return this;
	}

	@Override
	public ButtonText accessKey(char key) {
		element().accessKey = String.valueOf(key);
		return that();
	}
	@Override
	public ButtonText focus() {
		element().focus();
		return that();
	}
}
