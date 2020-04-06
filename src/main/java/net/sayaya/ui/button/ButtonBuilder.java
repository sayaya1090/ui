package net.sayaya.ui.button;

import elemental2.dom.HTMLElement;
import lombok.Setter;
import lombok.experimental.Accessors;

import static org.jboss.gwt.elemento.core.Elements.i;

public abstract class ButtonBuilder<B extends ButtonBuilder<B>> {
	@Setter
	@Accessors(fluent=true)
	private final static class ButtonSetting {
		private String text;
		private HTMLElement icon;
	}
	@Setter
	@Accessors(fluent=true)
	public static class AbstractButtonBuilder {
		private final ButtonSetting context;
		private AbstractButtonBuilder(ButtonSetting context) {
			this.context = context;
		}
		public ButtonFlatBuilder flat() {
			return new ButtonFlatBuilder(context);
		}
		public ButtonOutlineBuilder outline() {
			return new ButtonOutlineBuilder(context);
		}
		public ButtonContainBuilder contain() {
			return new ButtonContainBuilder(context);
		}
		public ButtonToggleBuilder toggle() {
			return new ButtonToggleBuilder(context);
		}
		public AbstractButtonBuilder text(String text) {
			context.text(text);
			return this;
		}
		public AbstractButtonBuilder icon(String icon) {
			context.icon = i().css("fa " + icon).element();
			return this;
		}
	}
	protected final ButtonSetting context;
	private ButtonBuilder(ButtonSetting context){
		this.context = context;
	}
	public static AbstractButtonBuilder button() {
		return new AbstractButtonBuilder(new ButtonSetting());
	}
	protected B self() {
		return (B)this;
	}
	public B icon(String icon) {
		context.icon = i().css("material-icons", "mdc-button__icon").add(icon).element();
		return self();
	}
	public B text(String text) {
		context.text(text);
		return self();
	}
	public abstract Button element();
	public static class ButtonFlatBuilder extends ButtonBuilder<ButtonFlatBuilder> {
		private ButtonFlatBuilder(ButtonSetting context) {
			super(context);
		}
		@Override
		public Button element() {
			Button btn = new Button();
			if(context.icon!=null) btn.icon(context.icon);
			if(context.text!=null) btn.text(context.text);
			return btn;
		}
	}
	public static class ButtonOutlineBuilder extends ButtonBuilder<ButtonOutlineBuilder> {
		private ButtonOutlineBuilder(ButtonSetting context) {
			super(context);
		}
		@Override
		public Button element() {
			Button btn = new Button().addClass("mdc-button--outlined");
			if(context.icon!=null) btn.icon(context.icon);
			if(context.text!=null) btn.text(context.text);
			return btn;
		}
	}
	public static class ButtonContainBuilder extends ButtonBuilder<ButtonContainBuilder> {
		private ButtonContainBuilder(ButtonSetting context) {
			super(context);
		}
		@Override
		public Button element() {
			Button btn = new Button().addClass("mdc-button--unelevated");
			if(context.icon!=null) btn.icon(context.icon);
			if(context.text!=null) btn.text(context.text);
			return btn;
		}
	}
	public static class ButtonToggleBuilder extends ButtonBuilder<ButtonToggleBuilder> {
		private ButtonToggleBuilder(ButtonSetting context) {
			super(context);
		}
		@Override
		public ButtonToggle element() {
			return new ButtonToggle();
		}
	}
}
