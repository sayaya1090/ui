package net.sayaya.ui.elements;

import elemental2.dom.Event;
import net.sayaya.ui.dom.MdCheckboxElement;
import net.sayaya.ui.elements.interfaces.HasAriaLabel;
import net.sayaya.ui.elements.interfaces.Selectable;
import org.jboss.elemento.*;

import static org.jboss.elemento.Elements.htmlElement;

public class CheckboxElementBuilder implements HasElement<MdCheckboxElement, CheckboxElementBuilder>, HasHTMLElement<MdCheckboxElement, CheckboxElementBuilder>,
        HasAriaLabel<MdCheckboxElement, CheckboxElementBuilder>, Selectable<MdCheckboxElement, CheckboxElementBuilder> {
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
    @Override public CheckboxElementBuilder select(boolean checked) {
        return state(checked?CheckboxState.CHECKED:CheckboxState.UNCHECKED);
    }
    @Override public boolean isSelected() {
        var elem = element();
        if(elem.indeterminate) return false;
        return elem.checked;
    }
    public boolean isIndeterminate() {
        return element().indeterminate;
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
    public CheckboxElementBuilder onChange(EventCallbackFn<Event> callback) {
        return on(EventType.change, callback);
    }
    private enum CheckboxState {
        UNCHECKED, CHECKED, INDETERMINATE
    }
}
