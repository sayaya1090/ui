package net.sayaya.ui;

import elemental2.dom.*;
import org.jboss.elemento.ElementBuilder;
import org.jboss.elemento.HtmlContentBuilder;

import static org.jboss.elemento.Elements.*;
import static org.jboss.elemento.EventType.bind;

public class List extends HTMLElementBuilder<HTMLUListElement, List> {
	public static List singleLineList() {
		List elem = new List(ul());
		elem.css("mdc-list");
		bind(elem, "DOMNodeInserted", evt->inject(elem.element()));
		return elem;
	}
	public static List doubleLineList() {
		List elem = new List(ul());
		elem.css("mdc-list", "mdc-list--two-line");
		bind(elem, "DOMNodeInserted", evt->inject(elem.element()));
		return elem;
	}
	public static ListItem item() {
		ListItem elem = new ListItem(li());
		elem.css("mdc-list-item");
		return elem;
	}
	public static Divider divider() {
		Divider elem = new Divider(li());
		elem.css("mdc-list-divider").attr("role", "separator");
		return elem;
	}
	private native static void inject(Element elem) /*-{
        $wnd.mdc.list.MDCList.attachTo(elem);
    }-*/;
	private final HtmlContentBuilder<HTMLUListElement> _this;
	private List(HtmlContentBuilder<HTMLUListElement> e) {
		super(e);
		_this = e;
		layout();
	}
	private void layout() {
		clear();
	}
	public List add(AbstractListItem<?> item) {
		_this.add(item);
		return this;
	}
	@Override
	public List that() {
		return this;
	}
	private static abstract class AbstractListItem<B extends AbstractListItem<B>> extends HTMLElementBuilder<HTMLLIElement, B> {
		protected final HtmlContentBuilder<HTMLLIElement> _this;
		public AbstractListItem(HtmlContentBuilder<HTMLLIElement> e) {
			super(e);
			_this = e;
			layout();
		}
		protected abstract void layout();
	}
	public static class ListItem extends AbstractListItem<ListItem> {
		private HtmlContentBuilder<HTMLElement> label1;
		private HtmlContentBuilder<HTMLElement> label2;
		private HtmlContentBuilder<HTMLElement> label;
		private String[] values;
		public ListItem(HtmlContentBuilder<HTMLLIElement> element) {
			super(element);
		}
		public ListItem label(String primary, String secondary) {
			label1 = span().css("mdc-list-item__primary-text").textContent(primary);
			label2 = span().css("mdc-list-item__secondary-text").textContent(secondary);
			label = span().css("mdc-list-item__text");
			layout();
			return that();
		}
		public ListItem label(String primary) {
			label1 = null;
			label2 = null;
			label = span().css("mdc-list-item__text").textContent(primary);
			layout();
			return that();
		}
		public ListItem selectable(boolean selectable) {
			if(selectable) _this.attr("role",  "option");
			else _this.attr("role", null);
			return that();
		}
		@Override
		protected void layout() {
			clear();
			_this.add(label);
			if(label1 != null) label.add(label1);
			if(label2 != null) label.add(label2);
		}
		@Override
		public ListItem that() {
			return this;
		}
	}
	public static class Divider extends AbstractListItem<Divider> {
		public Divider(HtmlContentBuilder<HTMLLIElement> e) {
			super(e);
		}
		@Override
		protected void layout() {}
		@Override
		public Divider that() {
			return this;
		}
	}
}
