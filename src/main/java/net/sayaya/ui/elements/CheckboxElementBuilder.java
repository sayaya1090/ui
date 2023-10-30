package net.sayaya.ui.elements;

import net.sayaya.ui.dom.MdCheckboxElement;
import org.jboss.elemento.HTMLElementBuilder;
import org.jboss.elemento.HasHTMLElement;

import static org.jboss.elemento.Elements.htmlElement;

public class CheckboxElementBuilder implements HasHTMLElement<MdCheckboxElement, CheckboxElementBuilder>, HasAriaLabel<MdCheckboxElement, CheckboxElementBuilder> {
    public static CheckboxElementBuilder checkbox() {
        return new CheckboxElementBuilder();
    }
    private final HTMLElementBuilder<MdCheckboxElement> that = htmlElement("md-checkbox", MdCheckboxElement.class);
    @Override
    public MdCheckboxElement element() {
        return that.element();
    }

    @Override
    public CheckboxElementBuilder that() {
        return this;
    }
    public CheckboxElementBuilder checked(boolean checked) {
        return state(checked?CheckboxState.CHECKED:CheckboxState.UNCHECKED);
    }
    public CheckboxElementBuilder indeterminate() {
        return state(CheckboxState.INDETERMINATE);
    }
    private CheckboxElementBuilder state(CheckboxState state) {
        if(state==null || state == CheckboxState.UNCHECKED) {
            element().checked = false;
            element().indeterminate = false;
        } else if(state == CheckboxState.CHECKED) {
            element().checked = true;
            element().indeterminate = false;
        } else if(state == CheckboxState.INDETERMINATE) {
            element().checked = false;
            element().indeterminate = true;
        }
        return this;
    }

    private enum CheckboxState {
        UNCHECKED, CHECKED, INDETERMINATE
    }
}
