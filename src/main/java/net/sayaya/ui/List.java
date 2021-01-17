package net.sayaya.ui;

import elemental2.dom.*;
import net.sayaya.ui.event.HasClickHandlers;
import org.gwtproject.event.shared.HandlerRegistration;
import org.jboss.elemento.HtmlContentBuilder;
import org.jboss.elemento.IsElement;

import static org.jboss.elemento.Elements.*;
import static org.jboss.elemento.EventType.bind;

public class List<ListItem extends List.AbstractListItem<ListItem>> extends HTMLElementBuilder<HTMLUListElement, List<ListItem>> {
	public static List<SingleLineItem> singleLineList() {
		List<SingleLineItem> elem = new List<>(ul());
		elem.css("mdc-list");
		bind(elem, "DOMNodeInserted", evt->inject(elem.element()));
		return elem;
	}
	public static List<DoubleLineItem> doubleLineList() {
		List<DoubleLineItem> elem = new List<>(ul());
		elem.css("mdc-list", "mdc-list--two-line");
		bind(elem, "DOMNodeInserted", evt->inject(elem.element()));
		return elem;
	}
	public static SingleLineItem singleLine() {
		SingleLineItem elem = new SingleLineItem(li());
		elem.css("mdc-list-item");
		return elem;
	}
	public static DoubleLineItem doubleLine() {
		DoubleLineItem elem = new DoubleLineItem(li());
		elem.css("mdc-list-item");
		return elem;
	}
	private native static void inject(Element elem) /*-{
        var mdc = $wnd.mdc.list.MDCList.attachTo(elem);
        // mdc.singleSelection = true;
    }-*/;
	private final HtmlContentBuilder<HTMLUListElement> _this;
	private List(HtmlContentBuilder<HTMLUListElement> e) {
		super(e);
		_this = e;
		_this.attr("role", "listbox");
		layout();
	}
	private void layout() {
		clear();
	}
	public List<ListItem> add(ListItem item) {
		_this.add(item);
		if(_this.element().childElementCount <= 1) item.attr("tabindex", "0");
		return that();
	}
	public List<ListItem> clear() {
		super.clear();
		while(_this.element().childElementCount > 0) _this.element().firstElementChild.remove();
		return that();
	}
	public List<ListItem> divider() {
		Divider elem = new Divider(li());
		elem.css("mdc-list-divider").attr("role", "separator");
		_this.add(elem);
		return that();
	}

	@Override
	public List<ListItem> that() {
		return this;
	}
	static abstract class AbstractListItem<B extends AbstractListItem<B>> extends HTMLElementBuilder<HTMLLIElement, B> implements HasClickHandlers {
		private final HtmlContentBuilder<HTMLElement> ripple = span().css("mdc-list-item__ripple");
		private HtmlContentBuilder<HTMLElement> leading;
		private final HtmlContentBuilder<HTMLElement> text = span().css("mdc-list-item__text");
		private HtmlContentBuilder<HTMLElement> trailing;
		protected final HtmlContentBuilder<HTMLLIElement> _this;
		public AbstractListItem(HtmlContentBuilder<HTMLLIElement> e) {
			super(e);
			_this = e;
			_this.attr("role", "option");
			layout();
			layout(text);
		}
		private void layout() {
			_this.element().innerHTML = "";
			_this.add(ripple);
			if(leading!=null) _this.add(leading);
			_this.add(text);
			if(trailing!=null) _this.add(trailing);
		}
		public B leading(IsElement<?> element) {
			if(element!=null) leading = span().css("mdc-list-item__graphic").add(element);
			else leading = null;
			layout();
			return that();
		}
		public B trailing(IsElement<?> element) {
			if(element!=null) trailing = span().css("mdc-list-item__meta").add(element);
			else trailing = null;
			layout();
			return that();
		}
		protected abstract void layout(HtmlContentBuilder<HTMLElement> text);
		@Override
		public final HandlerRegistration onClick(EventListener listener) {
			return onClick(that().element, listener);
		}
	}
	public static class SingleLineItem extends AbstractListItem<SingleLineItem> {
		private HtmlContentBuilder<HTMLElement> text;
		private SingleLineItem(HtmlContentBuilder<HTMLLIElement> e) {
			super(e);
		}
		public SingleLineItem label(String msg) {
			text.element().textContent = msg;
			attr("data-value", msg);
			return that();
		}
		@Override
		protected void layout(HtmlContentBuilder<HTMLElement> text) {
			this.text = text;
		}
		@Override
		public SingleLineItem that() {
			return this;
		}
	}
	public static class DoubleLineItem extends AbstractListItem<DoubleLineItem> {
		private HtmlContentBuilder<HTMLElement> primary;
		private HtmlContentBuilder<HTMLElement> secondary;
		public DoubleLineItem(HtmlContentBuilder<HTMLLIElement> e) {
			super(e);
		}
		@Override
		protected void layout(HtmlContentBuilder<HTMLElement> text) {
			primary = span().css("mdc-list-item__primary-text");
			secondary = span().css("mdc-list-item__secondary-text");
			text.add(primary).add(secondary);
		}
		public DoubleLineItem primary(String msg) {
			primary.element().textContent = msg;
			return that();
		}
		public DoubleLineItem secondary(String msg) {
			secondary.element().textContent = msg;
			return that();
		}
		@Override
		public DoubleLineItem that() {
			return this;
		}
	}
	public static class Divider extends AbstractListItem<Divider> {
		public Divider(HtmlContentBuilder<HTMLLIElement> e) {
			super(e);
		}
		@Override
		protected void layout(HtmlContentBuilder<HTMLElement> text) {}
		@Override
		public Divider that() {
			return this;
		}
	}
}
