package net.sayaya.ui.input;

import elemental2.core.JsDate;
import elemental2.dom.DomGlobal;
import elemental2.dom.HTMLInputElement;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.apache.tapestry.wml.Do;
import org.jboss.gwt.elemento.core.InputType;

import java.util.function.Consumer;
import java.util.function.Supplier;

import static org.jboss.gwt.elemento.core.Elements.input;

public class TextFieldBuilder<V, S extends TextFieldBuilder<V, S>> {
	@Setter
	@Accessors(fluent = true)
	private final static class TextFieldSetting<V> {
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
	public static TextFieldBuilder<String, ?> textBox() {
		HTMLInputElement input = input(InputType.text).element();
		return new TextFieldBuilder<>(new TextFieldSetting<String>().input(input).getter(()->input.value).settter(v->input.value = v));
	}
	public static TextFieldBuilder<Double, ?> numberBox() {
		HTMLInputElement input = input(InputType.number).element();
		return new TextFieldBuilder<>(new TextFieldSetting<Double>().input(input).getter(()->input.valueAsNumber).settter(v->input.value=v!=null?String.valueOf(v):null));
	}
	public static TextFieldBuilder<String, ?> emailBox() {
		HTMLInputElement input = input(InputType.email).element();
		return new TextFieldBuilder<>(new TextFieldSetting<String>().input(input).getter(()->input.value).settter(v->input.value = v));
	}
	public static TextFieldBuilder<JsDate, ?> dateBox() {
		HTMLInputElement input = input(InputType.date).element();
		return new TextFieldBuilder<>(new TextFieldSetting<JsDate>().input(input).getter(()->input.valueAsDate).settter(v->input.valueAsDate = v));
	}
	public static TextFieldBuilder<String, ?> fileBox() {
		HTMLInputElement input = input(InputType.file).element();
		return new TextFieldBuilder<>(new TextFieldSetting<String>().input(input).getter(()->input.value).settter(v->{DomGlobal.console.log("Unimplemented method: InputType.file -> setValue");}));
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
	private TextFieldBuilder(TextFieldSetting<V> context){
		this.context = context;
	}
	public static class TextFieldFilledBuilder<V> extends TextFieldBuilder<V, TextFieldFilledBuilder<V>> {
		private TextFieldFilledBuilder(TextFieldSetting<V> context) {
			super(context);
		}
		public TextFieldFilled<V> build() {
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
			TextField.inject(elem.element());
			return elem;
		}
	}
	public static class TextFieldOutlinedBuilder<V> extends TextFieldBuilder<V, TextFieldOutlinedBuilder<V>> {
		private TextFieldOutlinedBuilder(TextFieldSetting<V> context) {
			super(context);
		}
		public TextFieldOutlined<V> build() {
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
			TextField.inject(elem.element());
			return elem;
		}
	}
}
