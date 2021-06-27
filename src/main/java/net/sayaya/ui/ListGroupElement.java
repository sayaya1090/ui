package net.sayaya.ui;

import elemental2.dom.HTMLDivElement;
import elemental2.dom.HTMLHeadingElement;
import org.jboss.elemento.HtmlContentBuilder;

import static org.jboss.elemento.Elements.*;

public class ListGroupElement extends HTMLElementBuilder<HTMLDivElement, ListGroupElement> {
	public static ListGroupElement group() {
		return new ListGroupElement(div());
	}
	public static HtmlContentBuilder<HTMLHeadingElement> header(int n) {
		return h(n).css("mdc-list-group__subheader");
	}
	private final HtmlContentBuilder<HTMLDivElement> _this;
	private ListGroupElement(HtmlContentBuilder<HTMLDivElement> e) {
		super(e.css("mdc-list-group"));
		_this = e;
	}
	public ListGroupElement add(HtmlContentBuilder<HTMLHeadingElement> header) {
		_this.add(header);
		return that();
	}
	public ListGroupElement add(ListElement<?> listElement) {
		_this.add(listElement);
		return that();
	}
	@Override
	public ListGroupElement that() {
		return this;
	}
}
