package net.sayaya.ui;

import elemental2.dom.*;
import org.jboss.elemento.ElementBuilder;
import org.jboss.elemento.EventType;
import org.jboss.elemento.HtmlContentBuilder;

import static org.jboss.elemento.Elements.a;
import static org.jboss.elemento.Elements.div;

public class Breadcumb extends HTMLElementBuilder<HTMLDivElement, Breadcumb> {
	public static Breadcumb home(Icon icon, EventListener listener) {
		return new Breadcumb(div().style("display: flex; margin-top: 5px; margin-bottom: 5px;"), icon, listener);
	}
	private final HtmlContentBuilder<HTMLDivElement> _this;
	private Element splitter;
	private Breadcumb(HtmlContentBuilder<HTMLDivElement> e, Icon icon, EventListener listener) {
		super(e);
		_this = e.add(wrap(icon, listener));
		splitter(Icon.icon("double_arrow").element());
	}
	public Breadcumb add(String label, EventListener listener) {
		return add(label, listener, "#");
	}
	public Breadcumb add(String label, EventListener listener, String url) {
		_this.add(splitter.cloneNode(true)).add(wrap(a(url).add(label), listener));
		return that();
	}
	public Breadcumb add(Icon icon, EventListener listener) {
		_this.add(splitter.cloneNode(true)).add(wrap(icon, listener));
		return that();
	}
	public Breadcumb splitter(HTMLElement splitter) {
		this.splitter = splitter;
		CSSStyleDeclaration style = splitter.style;
		style.marginLeft = CSSProperties.MarginLeftUnionType.of("2em");
		style.marginRight = CSSProperties.MarginRightUnionType.of("2em");
		style.color = "var(--mdc-theme-text-secondary-on-background, rgba(0, 0, 0, 0.54))";
		style.userSelect = "none";
		style.setProperty("text-rendering", "optimizeLegibility");
		style.setProperty("-webkit-font-smoothing", "subpixel-antialiased");
		return that();
	}
	public <E extends ElementBuilder<?, ?>> E wrap(E elem, EventListener listener) {
		elem.on(EventType.click, listener::handleEvent);
		CSSStyleDeclaration style = elem.element().style;
		style.cursor = "pointer";
		style.setProperty("text-rendering", "optimizeLegibility");
		style.setProperty("-webkit-font-smoothing", "subpixel-antialiased");
		style.textDecoration = "unset";
		style.color = "var(--mdc-theme-text-primary-on-background, rgba(0, 0, 0, 0.54))";
		elem.on(EventType.mouseover, evt->style.color = "var(--mdc-theme-secondary)");
		elem.on(EventType.mouseout, evt->style.color = "var(--mdc-theme-text-secondary-on-background, rgba(0, 0, 0, 0.54))");
		return elem;
	}
	@Override
	public Breadcumb that() {
		return this;
	}
}
