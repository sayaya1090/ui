package net.sayaya.ui.button.icon;

import elemental2.dom.Element;
import elemental2.dom.HTMLElement;
import jsinterop.base.Js;
import lombok.experimental.Delegate;
import net.sayaya.ui.ButtonElement;
import org.jboss.elemento.HtmlContent;
import org.jboss.elemento.HtmlContentBuilder;
import org.jboss.elemento.IsElement;
import org.jboss.elemento.TypedBuilder;

public abstract class AbstractIconButtonBuilder<B extends AbstractIconButtonBuilder<B>> implements HtmlContent<ButtonElement, HtmlContentBuilder<ButtonElement>> {
    @Delegate(excludes = { IsElement.class, TypedBuilder.class, HtmlContent.class })
    private final HtmlContentBuilder<HTMLElement> delegate;
    private final HtmlContentBuilder<ButtonElement> that;
    public AbstractIconButtonBuilder(HtmlContentBuilder<HTMLElement> delegate, Element icon) {
        this.delegate = delegate;
        that = new HtmlContentBuilder<>(element());
        add(icon);
    }
    public B ariaLabel(String label) {
        delegate.attr("aria-label", label);
        return _this();
    }
    @Override
    public ButtonElement element() {
        return Js.uncheckedCast(delegate.element());
    }
    @Override
    public HtmlContentBuilder<ButtonElement> that() {
        return that;
    }
    private B _this() {
        return (B)this;
    }
}
