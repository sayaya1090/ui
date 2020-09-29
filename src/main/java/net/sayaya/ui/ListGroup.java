package net.sayaya.ui;

import elemental2.dom.HTMLDivElement;
import elemental2.dom.HTMLHeadingElement;
import org.jboss.elemento.HtmlContentBuilder;

import static org.jboss.elemento.Elements.*;

public class ListGroup extends HTMLElementBuilder<HTMLDivElement, ListGroup> {
	public static ListGroup group() {
		return new ListGroup(div());
	}
	public static HtmlContentBuilder<HTMLHeadingElement> header(int n) {
		return h(n).css("mdc-list-group__subheader");
	}
	private final HtmlContentBuilder<HTMLDivElement> _this;
	private ListGroup(HtmlContentBuilder<HTMLDivElement> e) {
		super(e.css("mdc-list-group"));
		_this = e;
	}
	public ListGroup add(HtmlContentBuilder<HTMLHeadingElement> header) {
		_this.add(header);
		return that();
	}
	public ListGroup add(List<?> list) {
		_this.add(list);
		return that();
	}
	@Override
	public ListGroup that() {
		return this;
	}
}
