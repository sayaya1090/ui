package net.sayaya.ui.elements;

import net.sayaya.ui.dom.MdTextFieldElement;
import net.sayaya.ui.dom.MdTextFieldElement.MdFilledTextFieldElement;
import net.sayaya.ui.dom.MdTextFieldElement.MdOutlinedTextFieldElement;
import org.jboss.elemento.HTMLContainerBuilder;
import org.jboss.elemento.HasElement;
import org.jboss.elemento.InputType;

import static org.jboss.elemento.Elements.htmlContainer;

public interface TextFieldElementBuilder<E extends MdTextFieldElement, SELF extends TextFieldElementBuilder<E, SELF>> extends HasElement<E, SELF>,
        HasLeadingIconSlot<E, SELF>, HasTrailingIconSlot<E, SELF>, HasAriaLabel<E, SELF>, HasSupportingTextSlot<E, SELF> {
    static TextFieldPrepareElementBuilder textField() {
        return new TextFieldPrepareElementBuilder();
    }
    HTMLContainerBuilder<E> delegate();
    default SELF style(String style) {
        delegate().style(style);
        return that();
    }
    default SELF name(String name) {
        element().name = name;
        return that();
    }
    default SELF label(String label) {
        element().label = label;
        return that();
    }
    default SELF value(String value) {
        element().value = value;
        return that();
    }
    default String value() {
        return element().value;
    }
    default SELF type(InputType type) {
        element().type = type!=null?type.name():InputType.text.name();
        return that();
    }
    default SELF placeholder(String placeholder) {
        element().placeholder = placeholder;
        return that();
    }
    default SELF rows(int rows) {
        element().rows = rows;
        return that();
    }
    default SELF cols(int cols) {
        element().cols = cols;
        return that();
    }
    default SELF required() {
        return required(true);
    }
    default SELF required(boolean required) {
        element().required = required;
        return that();
    }
    default SELF disabled() {
        return disabled(true);
    }
    default SELF disabled(boolean disabled) {
        element().disabled = disabled;
        return that();
    }
    default SELF pattern(String pattern) {
        element().pattern = pattern;
        return that();
    }
    default SELF error(boolean error) {
        element().error = error;
        return that();
    }
    default SELF error(String errorText) {
        return error(true).errorText(errorText);
    }
    default SELF errorText(String errorText) {
        element().errorText = errorText;
        return that();
    }
    default SELF prefixText(String prefixText) {
        element().prefixText = prefixText;
        return that();
    }
    default SELF suffixText(String suffixText) {
        element().suffixText = suffixText;
        return that();
    }
    default SELF max(String max) {
        element().max = max;
        return that();
    }
    default SELF maxLength(int maxLength) {
        element().maxLength = maxLength;
        return that();
    }
    default SELF min(String min) {
        element().min = min;
        return that();
    }
    default SELF minLength(int minLength) {
        element().minLength = minLength;
        return that();
    }
    E element();
    final class TextFieldPrepareElementBuilder {
        public FilledTextFieldElementBuilder filled() {
            return new FilledTextFieldElementBuilder();
        }
        public OutlinedTextFieldElementBuilder outlined() {
            return new OutlinedTextFieldElementBuilder();
        }
    }
    final class FilledTextFieldElementBuilder implements TextFieldElementBuilder<MdFilledTextFieldElement, FilledTextFieldElementBuilder> {
        private final HTMLContainerBuilder<MdFilledTextFieldElement> that = htmlContainer("md-filled-text-field", MdFilledTextFieldElement.class);
        @Override
        public FilledTextFieldElementBuilder that() {
            return this;
        }
        @Override
        public HTMLContainerBuilder<MdFilledTextFieldElement> delegate() {
            return that;
        }
        @Override
        public MdFilledTextFieldElement element() {
            return that.element();
        }
    }
    final class OutlinedTextFieldElementBuilder implements TextFieldElementBuilder<MdOutlinedTextFieldElement, OutlinedTextFieldElementBuilder> {
        private final HTMLContainerBuilder<MdOutlinedTextFieldElement> that = htmlContainer("md-outlined-text-field", MdOutlinedTextFieldElement.class);
        @Override
        public OutlinedTextFieldElementBuilder that() {
            return this;
        }
        @Override
        public HTMLContainerBuilder<MdOutlinedTextFieldElement> delegate() {
            return that;
        }
        @Override
        public MdOutlinedTextFieldElement element() {
            return that.element();
        }
    }
}
