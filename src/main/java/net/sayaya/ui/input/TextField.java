package net.sayaya.ui.input;

import com.google.gwt.user.client.DOM;
import elemental2.core.JsDate;
import elemental2.dom.*;
import lombok.AccessLevel;
import lombok.Setter;
import lombok.experimental.Accessors;
import net.sayaya.ui.HTMLElementBuilder;
import net.sayaya.ui.Icon;
import net.sayaya.ui.event.HasClickHandlers;
import net.sayaya.ui.event.HasValueChangeHandlers;
import org.gwtproject.event.shared.HandlerRegistration;
import org.jboss.elemento.*;

import java.util.function.Consumer;
import java.util.function.Supplier;

import static org.jboss.elemento.Elements.span;
import static org.jboss.elemento.EventType.bind;

public abstract class TextField<V> extends HTMLElementBuilder<HTMLLabelElement, TextField<V>> implements HasValueChangeHandlers<V>, HasClickHandlers {
	@Setter
	@Accessors(fluent = true)
	public final static class TextFieldBuilder<V> {
		private InputBuilder<HTMLInputElement> input;
		@Setter(AccessLevel.PRIVATE)
		private Supplier<V> getter;
		@Setter(AccessLevel.PRIVATE)
		private Consumer<V> setter;
		public TextField<V> filled() {
			TextFieldFilled<V> elem = new TextFieldFilled<>(Elements.label(), input, getter, setter);
			elem.css("mdc-text-field", "mdc-text-field--filled");
			bind(elem, "DOMNodeInserted", evt->inject(elem.element()));
			return elem;
		}
		public TextField<V> outlined() {
			TextFieldOutlined<V> elem = new TextFieldOutlined<>(Elements.label(), input, getter, setter);
			elem.css("mdc-text-field", "mdc-text-field--outlined");
			bind(elem, "DOMNodeInserted", evt->inject(elem.element()));
			return elem;
		}
	}
	public static TextFieldBuilder<String> textBox() {
		InputBuilder<HTMLInputElement> input = Elements.input(InputType.text).css("mdc-text-field__input");
		return new TextFieldBuilder<String>().input(input)
											 .setter(v->input.element().value=v)
											 .getter(()->input.element().value);
	}
	public static TextFieldBuilder<Double> numberBox() {
		InputBuilder<HTMLInputElement> input = Elements.input(InputType.number).css("mdc-text-field__input");
		return new TextFieldBuilder<Double>().input(input)
											 .setter(v->input.element().value=v!=null?String.valueOf(v):null)
											 .getter(()->input.element().valueAsNumber);
	}
	public static TextFieldBuilder<String> emailBox() {
		InputBuilder<HTMLInputElement> input = Elements.input(InputType.email).css("mdc-text-field__input");
		return new TextFieldBuilder<String>().input(input)
											 .setter(v->input.element().value=v)
											 .getter(()->input.element().value);
	}
	public static TextFieldBuilder<JsDate> dateBox() {
		InputBuilder<HTMLInputElement> input = Elements.input(InputType.date).css("mdc-text-field__input");
		return new TextFieldBuilder<JsDate>().input(input)
											 .setter(v->input.element().valueAsDate = v)
											 .getter(()->input.element().valueAsDate);
	}
	public static TextFieldBuilder<String> fileBox() {
		InputBuilder<HTMLInputElement> input = Elements.input(InputType.file).css("mdc-text-field__input").style("position: relative; top: calc(50% - 15px);");
		return new TextFieldBuilder<String>().input(input)
											 .setter(v->{DomGlobal.console.log("Unimplemented method: InputType.file -> setValue");})
											 .getter(()->input.element().value);
	}
	public static TextFieldBuilder<String> password() {
		InputBuilder<HTMLInputElement> input = Elements.input(InputType.password).css("mdc-text-field__input");
		return new TextFieldBuilder<String>().input(input)
											 .setter(v->input.element().value=v)
											 .getter(()->input.element().value);
	}
	private static native void inject(Element elem) /*-{
        $wnd.mdc.textField.MDCTextField.attachTo(elem);
    }-*/;
	private final HtmlContentBuilder<HTMLLabelElement> _this;
	protected IsElement<?> iconBefore;
	protected IsElement<?> iconTrailing;
	private final Supplier<V> getter;
	private final Consumer<V> setter;
	public TextField(HtmlContentBuilder<HTMLLabelElement> e, Supplier<V> getter, Consumer<V> setter) {
		super(e);
		_this = e;
		this.getter = getter;
		this.setter = setter;
	}
	protected abstract void layout();
	public abstract InputBuilder<HTMLInputElement> input();
	@Override
	public final HandlerRegistration onClick(EventListener listener) {
		return onClick(null, listener);
	}
	@Override
	public final V value() {
		return getter.get();
	}
	public final TextField<V> value(V value) {
		setter.accept(value);
		return that();
	}
	public abstract TextField<V> text(String label);
	public String text() {
		return this.element().innerHTML;
	}
	public TextField<V> before(Icon icon) {
		if(icon!=null) icon.attr("role", "button").css("mdc-text-field__icon", "mdc-text-field__icon--leading");
		this.iconBefore = icon;
		layout();
		return that();
	}
	public TextField<V> trailing(Icon icon) {
		if(icon!=null) icon.attr("role", "button").css("mdc-text-field__icon", "mdc-text-field__icon--trailing");
		this.iconTrailing = icon;
		layout();
		return that();
	}
	public final TextField<V> enabled(boolean enabled) {
		if(!enabled) {
			css("mdc-text-field--disabled");
			input().attr("disabled", "true");
		} else {
			ncss("mdc-text-field--disabled");
			input().element().removeAttribute("disabled");
		}
		return that();
	}
	public final TextField<V> fire(Event evt) {
		input().element().dispatchEvent(evt);
		return that();
	}
	@Override
	public final HandlerRegistration onValueChange(ValueChangeEventListener<V> listener) {
		return onValueChange(input().element(), listener);
	}
	private static class TextFieldFilled<V> extends TextField<V> {
		private final HtmlContentBuilder<HTMLLabelElement> _this;
		private final InputBuilder<HTMLInputElement> input;
		private final HtmlContentBuilder<HTMLElement> rippleInput = span().css("mdc-text-field__ripple");
		private final HtmlContentBuilder<HTMLLabelElement> label = Elements.label().css("mdc-floating-label");
		private final HtmlContentBuilder<HTMLElement> rippleLine = span().css("mdc-line-ripple");
		public TextFieldFilled(HtmlContentBuilder<HTMLLabelElement> e, InputBuilder<HTMLInputElement> i, Supplier<V> getter, Consumer<V> setter) {
			super(e, getter, setter);
			_this = e;
			this.input = i;
			String id = DOM.createUniqueId();
			label.id(id);
			i.attr("aria-labelledby", id);
			layout();
		}
		@Override
		protected void layout() {
			clear();
			if(iconBefore!=null) {
				_this.add(iconBefore);
				css("mdc-text-field--with-leading-icon");
			} else ncss("mdc-text-field--with-leading-icon");
			_this.add(rippleInput)
				 .add(input);
			if(iconTrailing!=null) {
				_this.add(iconTrailing);
				css("mdc-text-field--with-trailing-icon");
			} else ncss("mdc-text-field--with-trailing-icon");
			_this.add(label)
				 .add(rippleLine);
		}
		@Override
		public final TextFieldFilled<V> text(String label) {
			this.label.textContent(label);
			return that();
		}
		@Override
		public InputBuilder<HTMLInputElement> input() {
			return input;
		}

		@Override
		public TextFieldFilled<V> that() {
			return this;
		}
	}
	private static class TextFieldOutlined<V> extends TextField<V> {
		private final HtmlContentBuilder<HTMLLabelElement> _this;
		private final InputBuilder<HTMLInputElement> input;
		private final HtmlContentBuilder<HTMLLabelElement> label = Elements.label().css("mdc-floating-label");
		private final HtmlContentBuilder<HTMLElement> outline = span().css("mdc-notched-outline");
		private final HtmlContentBuilder<HTMLElement> outlineLeading = span().css("mdc-notched-outline__leading");
		private final HtmlContentBuilder<HTMLElement> outlineNotch = span().css("mdc-notched-outline__notch");
		private final HtmlContentBuilder<HTMLElement> outlineTrailing = span().css("mdc-notched-outline__trailing");
		public TextFieldOutlined(HtmlContentBuilder<HTMLLabelElement> e, InputBuilder<HTMLInputElement> i, Supplier<V> getter, Consumer<V> setter) {
			super(e, getter, setter);
			_this = e;
			this.input = i;
			String id = DOM.createUniqueId();
			label.id(id);
			i.attr("aria-labelledby", id);
			outline.add(outlineLeading).add(outlineNotch).add(outlineTrailing);
			outlineNotch.add(label);
			layout();
		}
		@Override
		protected void layout() {
			clear();
			if(iconBefore!=null) {
				_this.add(iconBefore);
				css("mdc-text-field--with-leading-icon");
			} else ncss("mdc-text-field--with-leading-icon");
			_this.add(input);
			if(iconTrailing!=null) {
				_this.add(iconTrailing);
				css("mdc-text-field--with-trailing-icon");
			} else ncss("mdc-text-field--with-trailing-icon");
			_this.add(outline);
		}
		@Override
		public final TextFieldOutlined<V> text(String label) {
			this.label.textContent(label);
			return that();
		}
		@Override
		public InputBuilder<HTMLInputElement> input() {
			return input;
		}
		@Override
		public TextFieldOutlined<V> that() {
			return this;
		}
	}
}
