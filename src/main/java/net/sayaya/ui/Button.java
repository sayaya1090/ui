package net.sayaya.ui;

import elemental2.dom.*;
import net.sayaya.ui.event.HasClickHandlers;
import org.gwtproject.event.shared.HandlerRegistration;
import org.jboss.elemento.HtmlContentBuilder;
import org.jboss.elemento.IsElement;

import static org.jboss.elemento.Elements.*;
import static org.jboss.elemento.EventType.bind;

public class Button extends HTMLElementBuilder<HTMLButtonElement, Button> implements HasClickHandlers, Focusable<Button> {
	public static Button flat() {
		Button elem = new Button(button());
		elem.css("mdc-button");
		bind(elem, "DOMNodeInserted", evt->inject(elem.element()));
		return elem;
	}
	public static Button outline() {
		Button elem = new Button(button());
		elem.css("mdc-button", "mdc-button--outlined");
		inject(elem.element());
		return elem;
	}
	public static Button contain() {
		Button elem = new Button(button());
		elem.css("mdc-button", "mdc-button--unelevated");
		inject(elem.element());
		return elem;
	}
	private static native void inject(Element elem) /*-{
        $wnd.mdc.ripple.MDCRipple.attachTo(elem);
    }-*/;
	private final HtmlContentBuilder<HTMLDivElement> ripple = div().css("mdc-button__ripple");
	private IsElement<?> iconBefore;
	private final HtmlContentBuilder<HTMLElement> label = span().css("mdc-button__label");
	private IsElement<?> iconTrailing;
	private final HtmlContentBuilder<HTMLButtonElement> _this;
	private Button(HtmlContentBuilder<HTMLButtonElement> e) {
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
	public final Button enabled(boolean enabled) {
		if(enabled) this.attr("disabled", null);
		else _this.attr("disabled", "true");
		return that();
	}
	public final Button text(String text) {
		label.textContent(text);
		return that();
	}
	public String text() {
		return label.element().innerHTML;
	}
	public Button before(Icon icon) {
		if(icon!=null) icon.css("mdc-button__icon");
		this.iconBefore = icon;
		layout();
		return that();
	}
	public Button trailing(Icon icon) {
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
	public Button that() {
		return this;
	}

	@Override
	public Button accessKey(char key) {
		element().accessKey = String.valueOf(key);
		return that();
	}
	@Override
	public Button focus() {
		element().focus();
		return that();
	}
}
