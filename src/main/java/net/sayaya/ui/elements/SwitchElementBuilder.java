package net.sayaya.ui.elements;

import net.sayaya.ui.dom.MdSwitchElement;
import org.jboss.elemento.HTMLElementBuilder;
import org.jboss.elemento.HasHTMLElement;

import static org.jboss.elemento.Elements.htmlElement;

public class SwitchElementBuilder implements HasHTMLElement<MdSwitchElement, SwitchElementBuilder>, HasAriaLabel<MdSwitchElement, SwitchElementBuilder> {
    public static SwitchElementBuilder sw() {
        return new SwitchElementBuilder();
    }
    private final HTMLElementBuilder<MdSwitchElement> that = htmlElement("md-switch", MdSwitchElement.class);
    public SwitchElementBuilder selected() {
        return selected(true);
    }
    public SwitchElementBuilder selected(boolean selected) {
        element().selected = selected;
        return that();
    }
    public SwitchElementBuilder disabled() {
        return disabled(true);
    }
    public SwitchElementBuilder disabled(boolean disabled) {
        element().disabled = disabled;
        return that();
    }
    public SwitchElementBuilder required() {
        return required(true);
    }
    public SwitchElementBuilder required(boolean required) {
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
    @Override public MdSwitchElement element() {
        return that.element();
    }
    @Override public SwitchElementBuilder that() {
        return this;
    }
}
