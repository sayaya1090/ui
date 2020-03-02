package net.sayaya.ui.button;

import elemental2.dom.HTMLElement;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.Delegate;

import static org.jboss.gwt.elemento.core.Elements.i;

@Setter
@Accessors(chain=true)
public class ButtonBuilder {
	private String text;
	private HTMLElement icon;
	public static AbstractButtonBuilder button() {
		return new AbstractButtonBuilder(new ButtonBuilder());
	}
	public ButtonBuilder setIcon(String icon) {
		this.icon = i().css("fa fa-times-circle").element();
		return this;
	}
	@Setter
	@Accessors(chain=true)
	private static class AbstractButtonBuilder {
		@Delegate
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
	}
	@Setter
	@Accessors(chain=true)
	public static class ButtonFlatBuilder {
		@Delegate
		private final ButtonBuilder context;
		private ButtonFlatBuilder(ButtonBuilder context) {
			this.context = context;
		}
		public ButtonImpl element() {
			return new ButtonImpl(context.text);
		}
	}
	@Setter
	@Accessors(chain=true)
	public static class ButtonOutlineBuilder {
		@Delegate
		private final ButtonBuilder context;
		private ButtonOutlineBuilder(ButtonBuilder context) {
			this.context = context;
		}
		public ButtonImpl element() {
			return new ButtonImpl(context.text);
		}
	}
	@Setter
	@Accessors(chain=true)
	public static class ButtonContainBuilder {
		@Delegate
		private final ButtonBuilder context;
		private ButtonContainBuilder(ButtonBuilder context) {
			this.context = context;
		}
		public ButtonImpl element() {
			return new ButtonImpl(context.text);
		}
	}
	@Setter
	@Accessors(chain=true)
	public static class ButtonToggleBuilder {
		@Delegate
		private final ButtonBuilder context;
		private ButtonToggleBuilder(ButtonBuilder context) {
			this.context = context;
		}
		public ButtonToggle element() {
			return new ButtonToggle();
		}
	}
}
