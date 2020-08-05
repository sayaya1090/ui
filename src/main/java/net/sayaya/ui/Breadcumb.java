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
		splitter = Icon.icon("double_arrow").style(splitter()).element();
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
		style.color = "rgba(0, 0, 0, 0.54)";
		style.userSelect = "none";
		style.setProperty("text-rendering", "optimizeLegibility");
		style.setProperty("-webkit-font-smoothing", "antialiased");
		return that();
	}
	public <E extends ElementBuilder<?, ?>> E wrap(E elem, EventListener listener) {
		elem.style(item());
		elem.on(EventType.click, listener::handleEvent);
		CSSStyleDeclaration style = elem.element().style;
		elem.on(EventType.mouseover, evt->style.color = "var(--mdc-theme-secondary)");
		elem.on(EventType.mouseout, evt->style.color = "rgba(0, 0, 0, 0.54)");
		return elem;
	}
	private static String splitter() {
		return "margin-left: 2em; margin-right: 2em; color: rgba(0, 0, 0, 0.54);" +
				"text-rendering: optimizeLegibility;" +
				"-webkit-font-smoothing: antialiased;" +
				"-webkit-user-select: none;" +
				"-khtml-user-select: none;" +
				"-moz-user-select: none;" +
				"-o-user-select: none;" +
				"user-select: none;";
	}
	private static String item() {
		return "cursor: pointer; text-rendering: optimizeLegibility; -webkit-font-smoothing: antialiased; text-decoration: unset; color: rgba(0, 0, 0, 0.54);";
	}
	@Override
	public Breadcumb that() {
		return this;
	}
}