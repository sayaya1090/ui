package net.sayaya.ui.elements;

import elemental2.dom.Event;
import net.sayaya.ui.dom.MdRadioElement;
import org.jboss.elemento.*;

import static org.jboss.elemento.Elements.htmlElement;

public class RadioElementBuilder implements HasElement<MdRadioElement, RadioElementBuilder>, HasHTMLElement<MdRadioElement, RadioElementBuilder>, HasAriaLabel<MdRadioElement, RadioElementBuilder> {
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
    public RadioElementBuilder checked() {
        return checked(true);
    }
    public RadioElementBuilder checked(boolean checked) {
        element().checked = checked;
        return that();
    }
    public RadioElementBuilder disabled() {
        return disabled(true);
    }
    public RadioElementBuilder disabled(boolean disabled) {
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
