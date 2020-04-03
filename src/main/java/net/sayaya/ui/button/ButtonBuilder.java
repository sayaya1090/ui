package net.sayaya.ui.button;

import elemental2.dom.HTMLElement;
import lombok.Setter;
import lombok.experimental.Accessors;

import static org.jboss.gwt.elemento.core.Elements.i;

@Setter
@Accessors(fluent=true)
public class ButtonBuilder {
	private String text;
	private HTMLElement icon;
	private ButtonBuilder(){}
	public static AbstractButtonBuilder button() {
		return new AbstractButtonBuilder(new ButtonBuilder());
	}
	public ButtonBuilder icon(String icon) {
		this.icon = i().css("fa " + icon).element();
		return this;
	}
	@Setter
	@Accessors(fluent=true)
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
		public AbstractButtonBuilder text(String text) {
			context.text(text);
			return this;
		}
		public AbstractButtonBuilder icon(String icon) {
			context.icon = i().css("fa " + icon).element();
			return this;
		}
	}
	@Setter
	@Accessors(fluent=true)
	public static class ButtonFlatBuilder {
		private final ButtonBuilder context;
		private ButtonFlatBuilder(ButtonBuilder context) {
			this.context = context;
		}
		public ButtonFlatBuilder text(String text) {
			context.text(text);
			return this;
		}
		public ButtonFlatBuilder icon(String icon) {
			context.icon = i().css("fa " + icon).element();
			return this;
		}
		public ButtonImpl element() {
			ButtonImpl btn = new ButtonImpl(Button.GSS.button(), Button.GSS.flat());
			if(context.text!=null) btn.setText(context.text);
			if(context.icon!=null) btn.setIcon(context.icon);
			return btn;
		}
	}
	@Setter
	@Accessors(fluent=true)
	public static class ButtonOutlineBuilder {
		private final ButtonBuilder context;
		private ButtonOutlineBuilder(ButtonBuilder context) {
			this.context = context;
		}
		public ButtonOutlineBuilder text(String text) {
			context.text(text);
			return this;
		}
		public ButtonOutlineBuilder icon(String icon) {
			context.icon = i().css("fa " + icon).element();
			return this;
		}
		public ButtonImpl element() {
			ButtonImpl btn = new ButtonImpl(Button.GSS.button(), Button.GSS.outline());
			if(context.text!=null) btn.setText(context.text);
			if(context.icon!=null) btn.setIcon(context.icon);
			return btn;
		}
	}
	@Setter
	@Accessors(fluent=true)
	public static class ButtonContainBuilder {
		private final ButtonBuilder context;
		private ButtonContainBuilder(ButtonBuilder context) {
			this.context = context;
		}
		public ButtonContainBuilder text(String text) {
			context.text(text);
			return this;
		}
		public ButtonContainBuilder icon(String icon) {
			context.icon = i().css("fa " + icon).element();
			return this;
		}
		public ButtonImpl element() {
			ButtonImpl btn = new ButtonImpl(Button.GSS.button(), Button.GSS.contain());
			if(context.text!=null) btn.setText(context.text);
			if(context.icon!=null) btn.setIcon(context.icon);
			return btn;
		}
	}
	@Setter
	@Accessors(fluent=true)
	public static class ButtonToggleBuilder {
		private final ButtonBuilder context;
		private ButtonToggleBuilder(ButtonBuilder context) {
			this.context = context;
		}
		public ButtonToggleBuilder text(String text) {
			context.text(text);
			return this;
		}
		public ButtonToggleBuilder icon(String icon) {
			context.icon = i().css("fa " + icon).element();
			return this;
		}
		public ButtonToggle element() {
			return new ButtonToggle(Button.GSS.button(), Button.GSS.toggle());
		}
	}
}
