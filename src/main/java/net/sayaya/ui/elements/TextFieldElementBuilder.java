package net.sayaya.ui.elements;

import net.sayaya.ui.dom.MdIconButtonElement;
import net.sayaya.ui.dom.MdIconElement;
import net.sayaya.ui.dom.MdTextFieldElement;
import net.sayaya.ui.dom.MdTextFieldElement.MdFilledTextFieldElement;
import net.sayaya.ui.dom.MdTextFieldElement.MdOutlinedTextFieldElement;
import org.jboss.elemento.HTMLContainerBuilder;
import org.jboss.elemento.HasElement;
import org.jboss.elemento.InputType;

import static org.jboss.elemento.Elements.htmlContainer;

public interface TextFieldElementBuilder<E extends MdTextFieldElement, SELF extends TextFieldElementBuilder<E, SELF>> extends HasElement<E, SELF>, HasIcon<E, SELF>, HasAriaLabel<E, SELF> {
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
    default SELF type(InputType type) {
        element().type = type!=null?type.name():InputType.text.name();
        return that();
    }
    default SELF placeholder(String placeholder) {
        element().placeholder = placeholder;
        return that();
    }
    default SELF icon(String icon) {
        return icon(icon, false);
    }
    default SELF icon(String icon, boolean trailing) {
        return icon(IconElementBuilder.icon(icon), trailing);
    }
    default SELF icon(IconElementBuilder icon) {
        return icon(icon.element(), false);
    }
    default SELF icon(IconElementBuilder icon, boolean trailing) {
        return icon(icon.element(), trailing);
    }
    default SELF icon(MdIconElement icon) {
        return icon(icon, false);
    }
    default SELF icon(MdIconElement icon, boolean trailing) {
        this.add(icon);
        icon.setAttribute("slot", trailing?"trailing-icon":"leading-icon");
        return that();
    }
    default SELF icon(IconButtonElementBuilder<?, ?> icon) {
        return icon(icon.element(), false);
    }
    default SELF icon(IconButtonElementBuilder<?, ?> icon, boolean trailing) {
        return icon(icon.element(), trailing);
    }
    default SELF icon(MdIconButtonElement icon) {
        return icon(icon, false);
    }
    default SELF icon(MdIconButtonElement icon, boolean trailing) {
        this.add(icon);
        icon.setAttribute("slot", trailing?"trailing-icon":"leading-icon");
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
    default SELF required(boolean required) {
        element().required = required;
        return that();
    }
    default SELF supportingText(String supportingText) {
        element().supportingText = supportingText;
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
