package net.sayaya.ui;

import elemental2.dom.HTMLElement;
import elemental2.dom.Node;
import org.jboss.elemento.HTMLContainerBuilder;
import org.jboss.elemento.IsElement;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

public abstract class HTMLElementBuilder<E extends HTMLElement, B extends HTMLElementBuilder<E, B>> extends HTMLContainerBuilder<E> {
	public HTMLElementBuilder(HTMLContainerBuilder<E> element) {
		super(element.element());
	}
	@Override
	public B that() {
		return (B) this;
	}
	@Override
	public B css(String... classes) {
		super.css(classes);
		return that();
	}
	@Override
	public B style(String style) {
		super.style(style);
		return that();
	}
	@Override
	public B add(Node child) {
		super.add(child);
		return that();
	}
	@Override
	public B add(IsElement<?> child) {
		super.add(child);
		return that();
	}
	@Override
	public B textContent(String text) {
		super.textContent(text);
		return that();
	}
	/** Sets the specified attribute of the element. */
	@Override
	public B attr(String name, String value) {
		if(value!=null) element().setAttribute(name, value);
		else element().removeAttribute(name);
		return that();
	}
	public B ncss(String... classes) {
		if(classes == null) return that();
		List<String> failSafeClasses = new ArrayList<>();
		for (String c : classes) if (c != null) {
			if (c.contains(" ")) failSafeClasses.addAll(asList(c.split(" ")));
			else failSafeClasses.add(c);
		}
		for (String failSafeClass : failSafeClasses) element().classList.remove(failSafeClass);
		return that();
	}
	public B clear() {
		E elem = element();
		elem.childNodes.setLength(0);
		return that();
	}
}
