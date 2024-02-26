package net.sayaya.ui.elements;

import elemental2.dom.Event;
import net.sayaya.ui.dom.MdRadioElement;
import net.sayaya.ui.elements.interfaces.HasAriaLabel;
import net.sayaya.ui.elements.interfaces.Inactivatable;
import net.sayaya.ui.elements.interfaces.Selectable;
import org.jboss.elemento.*;

import static org.jboss.elemento.Elements.htmlElement;

public class RadioElementBuilder implements HasElement<MdRadioElement, RadioElementBuilder>, HasHTMLElement<MdRadioElement, RadioElementBuilder>, HasAriaLabel<MdRadioElement, RadioElementBuilder>,
        Selectable<MdRadioElement, RadioElementBuilder>, Inactivatable<MdRadioElement, RadioElementBuilder> {
    public static RadioElementBuilder radio() {
        return new RadioElementBuilder();
    }
    private final HTMLElementBuilder<MdRadioElement> that = htmlElement("md-radio", MdRadioElement.class);
    public RadioElementBuilder name(String name) {
        element().name = name;
        return that();
    }
    public RadioElementBuilder value(String value) {
        element().value = value;
        return that();
    }
    @Override public RadioElementBuilder select(boolean checked) {
        element().checked = checked;
        return that();
    }
    @Override public boolean isSelected() {
        return element().checked;
    }
    @Override public RadioElementBuilder disable(boolean disabled) {
        element().disabled = disabled;
        return that();
    }
    public RadioElementBuilder onChange(EventCallbackFn<Event> callback) {
        return on(EventType.change, callback);
    }
    @Override
    public MdRadioElement element() {
        return that.element();
    }
    @Override
    public RadioElementBuilder that() {
        return this;
    }
}
