package net.sayaya.ui;

import elemental2.dom.HTMLElement;
import org.jboss.elemento.HtmlContentBuilder;

public abstract class HTMLElementBuilder<E extends HTMLElement & IsHTMLElement<E, ? extends E>> extends HtmlContentBuilder<E> {
	public HTMLElementBuilder(E element) {
		super(element);
	}
}
