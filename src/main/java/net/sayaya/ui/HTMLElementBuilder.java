package net.sayaya.ui;

import elemental2.dom.HTMLElement;
import elemental2.dom.HTMLInputElement;
import org.jboss.elemento.ElementBuilder;
import org.jboss.elemento.InputBuilder;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

public abstract class HTMLElementBuilder<E extends HTMLElement, B extends HTMLElementBuilder<E, B>> extends ElementBuilder<E, B> {
	public HTMLElementBuilder(ElementBuilder<E, ?> element) {
		super(element.element());
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
		// elem.childNodes.forEach((n, i, l)->elem.removeChild(n));
		return that();
	}
}
