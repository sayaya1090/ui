package net.sayaya.ui.input;

import elemental2.dom.HTMLInputElement;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class TextFieldBuilder<V, S extends TextFieldBuilder<V, S>> {
	@Setter
	@Accessors(fluent = true)
	final static class TextFieldSetting<V> {
		HTMLInputElement input;
		Supplier<V> getter;
		Consumer<V> settter;
		Boolean enabled;
		String label;
		String iconLeading;
		String iconTrailing;
	}
	public S self() {
		return (S)this;
	}
	protected TextFieldSetting<V> context;
	public S enabled(boolean enabled) {
		context.enabled = enabled;
		return self();
	}
	public S label(String label) {
		context.label = label;
		return self();
	}
	public S iconLeading(String icon) {
		context.iconLeading = icon;
		return self();
	}
	public S iconTrailing(String icon) {
		context.iconTrailing = icon;
		return self();
	}
	public TextFieldFilledBuilder<V> filled() {
		return new TextFieldFilledBuilder<>(context);
	}
	public TextFieldOutlinedBuilder<V> outlined() {
		return new TextFieldOutlinedBuilder<>(context);
	}
	TextFieldBuilder(TextFieldSetting<V> context){
		this.context = context;
	}
	public static class TextFieldFilledBuilder<V> extends TextFieldBuilder<V, TextFieldFilledBuilder<V>> {
		private TextFieldFilledBuilder(TextFieldSetting<V> context) {
			super(context);
		}
		public TextFieldFilled<V> element() {
			TextFieldFilled<V> elem = new TextFieldFilled<V>(context.input) {
				@Override
				public TextFieldFilled<V> value(V value) {
					context.settter.accept(value);
					inject();
					return self();
				}
				@Override
				public V value() {
					return context.getter.get();
				}
			};
			if(context.enabled!=null) elem.enabled(context.enabled);
			if(context.label!=null) elem.label(context.label);
			if(context.iconLeading!=null) elem.iconLeading(context.iconLeading);
			if(context.iconTrailing!=null) elem.iconTrailing(context.iconTrailing);
			elem.inject();
			return elem;
		}
	}
	public static class TextFieldOutlinedBuilder<V> extends TextFieldBuilder<V, TextFieldOutlinedBuilder<V>> {
		private TextFieldOutlinedBuilder(TextFieldSetting<V> context) {
			super(context);
		}
		public TextFieldOutlined<V> element() {
			TextFieldOutlined<V> elem = new TextFieldOutlined<V>(context.input) {
				@Override
				public TextFieldOutlined<V> value(V value) {
					context.settter.accept(value);
					inject();
					return self();
				}

				@Override
				public V value() {
					return context.getter.get();
				}
			};
			if(context.enabled!=null) elem.enabled(context.enabled);
			if(context.label!=null) elem.label(context.label);
			if(context.iconLeading!=null) elem.iconLeading(context.iconLeading);
			if(context.iconTrailing!=null) elem.iconTrailing(context.iconTrailing);
			elem.inject();
			return elem;
		}
	}
}
