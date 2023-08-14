package net.sayaya.ui.button;

import elemental2.dom.Element;
import elemental2.dom.HTMLElement;
import elemental2.svg.SVGElement;
import jsinterop.base.Js;
import lombok.experimental.Delegate;
import net.sayaya.ui.ButtonElement;
import org.jboss.elemento.HtmlContent;
import org.jboss.elemento.HtmlContentBuilder;
import org.jboss.elemento.IsElement;
import org.jboss.elemento.TypedBuilder;

public abstract class AbstractButtonBuilder<B extends AbstractButtonBuilder<B>> implements HtmlContent<ButtonElement, HtmlContentBuilder<ButtonElement>> {
    @Delegate(excludes = { IsElement.class, TypedBuilder.class, HtmlContent.class })
    private final HtmlContentBuilder<HTMLElement> delegate;
    private final HtmlContentBuilder<ButtonElement> that;
    public AbstractButtonBuilder(HtmlContentBuilder<HTMLElement> delegate) {
        this.delegate = delegate;
        that = new HtmlContentBuilder<>(element());
    }
    public B leading(IsElement<? super HTMLElement> icon) {
        return new IconParam(icon.element()).build();
    }
    public B leading(SVGElement icon) {
        return new IconParam(icon).build();
    }
    public B trailing(IsElement<? super HTMLElement> icon) {
        delegate.attr("trailingicon", "true");
        return new IconParam(icon.element()).build();
    }
    public B trailing(SVGElement icon) {
        delegate.attr("trailingicon", "true");
        return new IconParam(icon).build();
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

    public class IconParam {
        private IconParam(Element icon) {
            icon.slot = "icon";
            delegate.add(icon);
        }
        public B build() {
            return _this();
        }
    }
}
