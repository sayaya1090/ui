package net.sayaya.ui.n;

import elemental2.dom.*;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;
import lombok.Setter;
import lombok.experimental.Accessors;
import net.sayaya.ui.event.HandlerRegistration;
import net.sayaya.ui.event.HasClickHandlers;
import org.jboss.elemento.IsElement;

import static org.jboss.elemento.Elements.div;
import static org.jboss.elemento.Elements.span;

public class Button implements IsElement<HTMLButtonElement>, HasClickHandlers {
	public static ButtonBuilder flat() {
		return new ButtonFlatBuilder(new Button());
	}
	public static ButtonBuilder outline() {
		return new ButtonOutlineBuilder(new Button());
	}
	public static ButtonBuilder contain() {
		return new ButtonContainBuilder(new Button());
	}
	private final HTMLDivElement ripple = div().css("mdc-button__ripple").element();
	private final HTMLElement label = span().css("mdc-button__label").element();
	private final HTMLButtonElement _this = org.jboss.elemento.Elements.button().css("mdc-button")
																	   .add(ripple)
																	   .add(label)
																	   .element();
	private Button() {
		inject(_this);
	}
	private native void inject(Element elem) /*-{
        $wnd.mdc.ripple.MDCRipple.attachTo(elem);
    }-*/;
	public final Button enabled(boolean enabled) {
		if(enabled) _this.removeAttribute("disabled");
		else _this.setAttribute("disabled", true);
		return this;
	}
	public final Button text(String text) {
		label.innerHTML = text;
		return this;
	}
	public Button iconBefore(Icon.IconBuilder icon) {
		while(ripple.nextSibling!=label) _this.removeChild(ripple.nextSibling);
		if(icon!=null) {
			icon.css("mdc-button__icon");
			_this.insertBefore(icon.element(), label);
		}
		return this;
	}
	public Button iconTrailing(Icon.IconBuilder icon) {
		while(_this.lastChild!=label) _this.lastElementChild.remove();
		if(icon!=null) {
			icon.css("mdc-button__icon");
			_this.appendChild(icon.element());
		}
		return this;
	}
	@Override
	public HandlerRegistration addClickHandler(EventListener listener) {
		return addClickHandler(_this, listener);
	}
	@Override
	public HTMLButtonElement element() {
		return _this;
	}

	@Setter
	@Accessors(fluent=true)
	public static abstract class ButtonBuilder extends HTMLElementBuilder<HTMLButtonElement, Button> {
		private String text = "";
		private Icon.IconBuilder iconBefore;
		private Icon.IconBuilder iconTrailing;
		private ButtonBuilder(Button elem) {
			super(elem);
		}
		@Override
		public HTMLButtonElement element() {
			Button elem = proxy();
			if(iconBefore!=null) elem.iconBefore(iconBefore);
			if(iconTrailing!=null) elem.iconTrailing(iconTrailing);
			return elem.text(text).element();
		}
	}
	private static class ButtonFlatBuilder extends ButtonBuilder {
		private ButtonFlatBuilder(Button elem) {
			super(elem);
		}
	}
	public static class ButtonOutlineBuilder extends ButtonBuilder {
		public ButtonOutlineBuilder(Button elem) {
			super(elem);
			this.css("mdc-button--outlined");
		}
	}
	public static class ButtonContainBuilder extends ButtonBuilder {
		public ButtonContainBuilder(Button elem) {
			super(elem);
			this.css("mdc-button--unelevated");
		}
	}
}
