package net.sayaya.ui.n;

import elemental2.dom.HTMLElement;
import org.jboss.elemento.ElementBuilder;
import org.jboss.elemento.HtmlContent;
import org.jboss.elemento.IsElement;

public class HTMLElementBuilder<E extends HTMLElement, P extends IsElement<E>> extends ElementBuilder<E, HTMLElementBuilder<E, P>> implements HtmlContent<E, HTMLElementBuilder<E, P>> {
	private final P proxy;
	public HTMLElementBuilder(P proxy) {
		super(proxy.element());
		this.proxy = proxy;
	}
	public final P proxy() {
		return proxy;
	}
	@Override
	public HTMLElementBuilder<E, P> that() {
		return this;
	}
}
