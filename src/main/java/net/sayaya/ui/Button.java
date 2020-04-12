package net.sayaya.ui;

import elemental2.dom.*;
import lombok.Setter;
import lombok.experimental.Accessors;
import net.sayaya.ui.event.HandlerRegistration;
import net.sayaya.ui.event.HasClickHandlers;

import static org.jboss.gwt.elemento.core.Elements.*;

public class Button implements IsHTMLElement<HTMLButtonElement, Button>, Focusable<Button>, HasClickHandlers {
	private final HTMLDivElement ripple = div().css("mdc-button__ripple").element();
	private final HTMLElement label = span().css("mdc-button__label").element();
	private final HTMLButtonElement _this = org.jboss.gwt.elemento.core.Elements.button().css("mdc-button")
			.add(ripple)
			.add(label)
			.element();
	Button() {inject(_this);}
	private native void inject(Element elem) /*-{
		$wnd.mdc.ripple.MDCRipple.attachTo(elem);
	}-*/;
	@Override
	public final HTMLButtonElement element() {
		return _this;
	}
	@Override
	public final HandlerRegistration addClickHandler(EventListener listener) {
		return addClickHandler(_this, listener);
	}
	public final Button enabled(boolean enabled) {
		if(enabled) _this.removeAttribute("disabled");
		else _this.setAttribute("disabled", true);
		return self();
	}
	@Override
	public Button accessKey(char key) {
		_this.setAttribute("accessKey", String.valueOf(key));
		return self();
	}
	@Override
	public Button focus() {
		_this.focus();
		return self();
	}
	public Button iconBefore(Icon icon) {
		while(ripple.nextSibling!=label) _this.removeChild(ripple.nextSibling);
		if(icon!=null) {
			icon.addClass("mdc-button__icon");
			_this.insertBefore(icon.element(), label);
		}
		return self();
	}
	public Button iconTrailing(Icon icon) {
		while(_this.lastChild!=label) _this.lastElementChild.remove();
		if(icon!=null) {
			icon.addClass("mdc-button__icon");
			_this.appendChild(icon.element());
		}
		return self();
	}
	public Button text(String text) {
		label.innerHTML = text;
		return self();
	}

	public static AbstractButtonBuilder button() {
		return new AbstractButtonBuilder(new ButtonSetting());
	}
	@Setter
	@Accessors(fluent=true)
	private final static class ButtonSetting {
		private String text;
		private Icon iconBefore;
		private Icon iconTrailing;
	}
	public static abstract class ButtonBuilder<B extends ButtonBuilder<B>> {
		private final ButtonSetting settings;
		private ButtonBuilder(ButtonSetting settings) {
			this.settings = settings;
		}
		protected B self() {
			return (B)this;
		}
		protected ButtonSetting context() {
			return settings;
		}
		public B iconBefore(Icon icon) {
			settings.iconBefore = icon;
			return self();
		}
		public B iconTrailing(Icon icon) {
			settings.iconTrailing = icon;
			return self();
		}
		public B text(String text) {
			settings.text(text);
			return self();
		}
	}
	public static class AbstractButtonBuilder extends ButtonBuilder<AbstractButtonBuilder> {
		private AbstractButtonBuilder(ButtonSetting settings) {
			super(settings);
		}
		public ButtonFlatBuilder flat() {
			return new ButtonFlatBuilder(context());
		}
		public ButtonOutlineBuilder outline() {
			return new ButtonOutlineBuilder(context());
		}
		public ButtonContainBuilder contain() {
			return new ButtonContainBuilder(context());
		}
	}
	public static class ButtonFlatBuilder extends ButtonBuilder<ButtonFlatBuilder> {
		private ButtonFlatBuilder(ButtonSetting context) {
			super(context);
		}
		public Button element() {
			Button elem = new Button();
			if(context().iconBefore!=null) elem.iconBefore(context().iconBefore);
			if(context().iconTrailing!=null) elem.iconTrailing(context().iconTrailing);
			return elem.text(context().text);
		}
	}
	public static class ButtonOutlineBuilder extends ButtonBuilder<ButtonOutlineBuilder> {
		private ButtonOutlineBuilder(ButtonSetting context) {
			super(context);
		}
		public Button element() {
			Button elem = new Button().addClass("mdc-button--outlined");
			if(context().iconBefore!=null) elem.iconBefore(context().iconBefore);
			if(context().iconTrailing!=null) elem.iconTrailing(context().iconTrailing);
			return elem.text(context().text);
		}
	}
	public static class ButtonContainBuilder extends ButtonBuilder<ButtonContainBuilder> {
		private ButtonContainBuilder(ButtonSetting context) {
			super(context);
		}
		public Button element() {
			Button elem = new Button().addClass("mdc-button--unelevated");
			if(context().iconBefore!=null) elem.iconBefore(context().iconBefore);
			if(context().iconTrailing!=null) elem.iconTrailing(context().iconTrailing);
			return elem.text(context().text);
		}
	}
}
