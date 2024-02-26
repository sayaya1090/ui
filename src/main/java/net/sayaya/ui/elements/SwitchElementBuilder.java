package net.sayaya.ui.elements;

import elemental2.dom.Event;
import net.sayaya.ui.dom.MdSwitchElement;
import net.sayaya.ui.elements.interfaces.HasAriaLabel;
import net.sayaya.ui.elements.interfaces.Inactivatable;
import net.sayaya.ui.elements.interfaces.Requireable;
import net.sayaya.ui.elements.interfaces.Selectable;
import org.jboss.elemento.*;

import static org.jboss.elemento.Elements.htmlElement;

public class SwitchElementBuilder implements HasElement<MdSwitchElement, SwitchElementBuilder>, HasHTMLElement<MdSwitchElement, SwitchElementBuilder>, HasAriaLabel<MdSwitchElement, SwitchElementBuilder>,
        Selectable<MdSwitchElement, SwitchElementBuilder>, Requireable<MdSwitchElement, SwitchElementBuilder>, Inactivatable<MdSwitchElement, SwitchElementBuilder> {
    public static SwitchElementBuilder sw() {
        return new SwitchElementBuilder();
    }
    private final HTMLElementBuilder<MdSwitchElement> that = htmlElement("md-switch", MdSwitchElement.class);
    @Override public SwitchElementBuilder select(boolean selected) {
        element().selected = selected;
        return that();
    }
    @Override public boolean isSelected() {
        return element().selected;
    }
    @Override public SwitchElementBuilder disable(boolean disabled) {
        element().disabled = disabled;
        return that();
    }
    @Override public SwitchElementBuilder required(boolean required) {
        element().required = required;
        return that();
    }
    public SwitchElementBuilder value(String value) {
        element().value = value;
        return that();
    }
    public SwitchElementBuilder name(String name) {
        element().name = name;
        return that();
    }
    public SwitchElementBuilder icons() {
        return icons(true);
    }
    public SwitchElementBuilder icons(boolean icons) {
        element().icons = icons;
        return that();
    }
    public SwitchElementBuilder showOnlySelectedIcon() {
        return showOnlySelectedIcon(true);
    }
    public SwitchElementBuilder showOnlySelectedIcon(boolean showOnlySelectedIcon) {
        element().showOnlySelectedIcon = showOnlySelectedIcon;
        return icons();
    }
    public SwitchElementBuilder onChange(EventCallbackFn<Event> callback) {
        return on(EventType.change, callback);
    }
    @Override public MdSwitchElement element() {
        return that.element();
    }
    @Override public SwitchElementBuilder that() {
        return this;
    }
}
