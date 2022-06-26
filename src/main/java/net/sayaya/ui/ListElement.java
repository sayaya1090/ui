package net.sayaya.ui;

import elemental2.dom.*;
import jsinterop.annotations.JsType;
import net.sayaya.ui.event.HasAttachHandlers;
import net.sayaya.ui.event.HasClickHandlers;
import net.sayaya.ui.util.ElementUtil;
import org.gwtproject.event.shared.HandlerRegistration;
import org.jboss.elemento.HtmlContentBuilder;
import org.jboss.elemento.IsElement;

import java.util.ArrayList;

import static org.jboss.elemento.Elements.*;

public class ListElement<ListItem extends ListElement.AbstractListItem<ListItem>> extends HTMLElementBuilder<HTMLUListElement, ListElement<ListItem>> implements HasAttachHandlers {
	public static ListElement<SingleLineItem> singleLineList() {
		return new ListElement<>(ul());
	}
	public static ListElement<DoubleLineItem> doubleLineList() {
		ListElement<DoubleLineItem> elem = new ListElement<>(ul());
		elem.css("mdc-list--two-line");
		return elem;
	}
	public static SingleLineItem singleLine() {
		return new SingleLineItem(li());
	}
	public static DoubleLineItem doubleLine() {
		return new DoubleLineItem(li());
	}
	private final HtmlContentBuilder<HTMLUListElement> _this;
	private final java.util.List<ListItem> children = new ArrayList<>();
	private ListElement(HtmlContentBuilder<HTMLUListElement> e) {
		super(e);
		_this = e.css("mdc-list").attr("role", "listbox");
		onAttach(evt->new MDCList(element()));
	}
	public ListElement<ListItem> add(ListItem item) {
		_this.add(item);
		children.add(item);
		if(_this.element().childElementCount <= 1) item.attr("tabindex", "0");
		return that();
	}
	public ListElement<ListItem> clear() {
		super.clear();
		while(_this.element().childElementCount > 0) _this.element().firstElementChild.remove();
		children.clear();
		return that();
	}
	public ListElement<ListItem> divider() {
		Divider elem = new Divider(li());
		elem.css("mdc-list-divider").attr("role", "separator");
		_this.add(elem);
		return that();
	}
	public String value(int n) {
		return children.get(n).value();
	}
	public Integer indexOf(String value) {
		return children.stream().filter(child->child.value()!=null).filter(child->child.value().equals(value)).findFirst().map(children::indexOf).orElse(null);
	}
	@Override
	public ListElement<ListItem> that() {
		return this;
	}

	@Override
	public HandlerRegistration onAttach(EventListener listener) {
		return onAttach(element(), listener);
	}

	static abstract class AbstractListItem<L extends AbstractListItem<L>> extends HTMLElementBuilder<HTMLLIElement, L> implements HasClickHandlers {
		private final HtmlContentBuilder<HTMLElement> ripple = span();
		protected final HtmlContentBuilder<HTMLElement> text = span();
		protected final HtmlContentBuilder<HTMLLIElement> _this;
		public AbstractListItem(HtmlContentBuilder<HTMLLIElement> e) {
			super(e);
			_this = e.css("mdc-list-item").attr("role", "option")
					.add(ripple.css("mdc-list-item__ripple"))
					.add(text.css("mdc-list-item__text"));
		}
		public L leading(IsElement<?> element) {
			if(element !=null) element().insertBefore(span().css("mdc-list-item__graphic").add(element).element(), text.element());
			else {
				var containers = element().getElementsByClassName("mdc-list-item__graphic");
				if(containers!=null) for(var container: containers.asList()) if(ElementUtil.isPrev(container, text.element())) container.remove();
			}
			return that();
		}
		public L trailing(IsElement<?> element) {
			if(element !=null) {
				element.element().classList.add("mdc-list-item__meta");
				text.element().insertAdjacentElement("afterend", element.element());
			} else {
				var containers = element().getElementsByClassName("mdc-list-item__meta");
				if(containers!=null) for(var container: containers.asList()) if(ElementUtil.isNext(container, text.element())) container.remove();
			}
			return that();
		}
		public String value() {
			return element().getAttribute("data-value");
		}
		public L enabled(boolean enabled) {
			if(!enabled) css("mdc-list-item--disabled");
			else ncss("mdc-list-item--disabled");
			return that();
		}
		@Override
		public final HandlerRegistration onClick(EventListener listener) {
			return onClick(that().element, listener);
		}
	}
	public static class SingleLineItem extends AbstractListItem<SingleLineItem> {
		private SingleLineItem(HtmlContentBuilder<HTMLLIElement> e) {
			super(e);
		}
		public SingleLineItem label(String msg) {
			text.element().textContent = msg;
			attr("data-value", msg);
			return that();
		}
		@Override
		public SingleLineItem that() {
			return this;
		}
	}
	public static class DoubleLineItem extends AbstractListItem<DoubleLineItem> {
		private final HtmlContentBuilder<HTMLElement> primary = span();
		private final HtmlContentBuilder<HTMLElement> secondary = span();
		public DoubleLineItem(HtmlContentBuilder<HTMLLIElement> e) {
			super(e);
			text.add(primary.css("mdc-list-item__primary-text"))
					.add(secondary.css("mdc-list-item__secondary-text"));
		}
		public DoubleLineItem primary(String msg) {
			primary.element().textContent = msg;
			attr("data-value", msg);
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
		public Divider that() {
			return this;
		}
	}
	@JsType(isNative = true, namespace="mdc.list")
	static final class MDCList {
		public MDCList(Element elem){}
	}
}
