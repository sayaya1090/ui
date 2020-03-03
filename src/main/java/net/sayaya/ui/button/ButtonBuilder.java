package net.sayaya.ui.button;

import elemental2.dom.HTMLElement;
import lombok.Setter;
import lombok.experimental.Accessors;

import static org.jboss.gwt.elemento.core.Elements.i;

@Setter
@Accessors(chain=true)
public class ButtonBuilder {
	private String text;
	private HTMLElement icon;
	private ButtonBuilder(){}
	public static AbstractButtonBuilder button() {
		return new AbstractButtonBuilder(new ButtonBuilder());
	}
	public ButtonBuilder setIcon(String icon) {
		this.icon = i().css("fa " + icon).element();
		return this;
	}
	@Setter
	@Accessors(chain=true)
	public static class AbstractButtonBuilder {
		private final ButtonBuilder context;
		private AbstractButtonBuilder(ButtonBuilder context) {
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
		public AbstractButtonBuilder setText(String text) {
			context.setText(text);
			return this;
		}
		public AbstractButtonBuilder setIcon(String icon) {
			context.icon = i().css("fa " + icon).element();
			return this;
		}
	}
	@Setter
	@Accessors(chain=true)
	public static class ButtonFlatBuilder {
		private final ButtonBuilder context;
		private ButtonFlatBuilder(ButtonBuilder context) {
			this.context = context;
		}
		public ButtonFlatBuilder setText(String text) {
			context.setText(text);
			return this;
		}
		public ButtonFlatBuilder setIcon(String icon) {
			context.icon = i().css("fa " + icon).element();
			return this;
		}
		public ButtonImpl element() {
			return new ButtonImpl(context.text);
		}
	}
	@Setter
	@Accessors(chain=true)
	public static class ButtonOutlineBuilder {
		private final ButtonBuilder context;
		private ButtonOutlineBuilder(ButtonBuilder context) {
			this.context = context;
		}
		public ButtonOutlineBuilder setText(String text) {
			context.setText(text);
			return this;
		}
		public ButtonOutlineBuilder setIcon(String icon) {
			context.icon = i().css("fa " + icon).element();
			return this;
		}
		public ButtonImpl element() {
			return new ButtonImpl(context.text);
		}
	}
	@Setter
	@Accessors(chain=true)
	public static class ButtonContainBuilder {
		private final ButtonBuilder context;
		private ButtonContainBuilder(ButtonBuilder context) {
			this.context = context;
		}
		public ButtonContainBuilder setText(String text) {
			context.setText(text);
			return this;
		}
		public ButtonContainBuilder setIcon(String icon) {
			context.icon = i().css("fa " + icon).element();
			return this;
		}
		public ButtonImpl element() {
			return new ButtonImpl(context.text);
		}
	}
	@Setter
	@Accessors(chain=true)
	public static class ButtonToggleBuilder {
		private final ButtonBuilder context;
		private ButtonToggleBuilder(ButtonBuilder context) {
			this.context = context;
		}
		public ButtonToggleBuilder setText(String text) {
			context.setText(text);
			return this;
		}
		public ButtonToggleBuilder setIcon(String icon) {
			context.icon = i().css("fa " + icon).element();
			return this;
		}
		public ButtonToggle element() {
			return new ButtonToggle();
		}
	}
}
