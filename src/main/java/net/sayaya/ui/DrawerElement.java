package net.sayaya.ui;

import com.google.gwt.core.client.Scheduler;
import elemental2.dom.*;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;
import net.sayaya.ui.event.HasAttachHandlers;
import org.gwtproject.event.shared.HandlerRegistration;
import org.jboss.elemento.HtmlContentBuilder;

import static org.jboss.elemento.Elements.*;

public class DrawerElement extends HTMLElementBuilder<HTMLElement, DrawerElement> implements HasAttachHandlers {
	public static DrawerElement drawer() {
		return new DrawerElement(aside().css("mdc-drawer mdc-drawer--dismissible"));
	}
	public static DrawerHeader header() {
		return new DrawerHeader(div().css("mdc-drawer__header"));
	}
	public static DrawerContent content() {
		return new DrawerContent(div().css("mdc-drawer__content"));
	}
	public static DrawerListItem item() {
		return new DrawerListItem(a().css("mdc-list-item").attr("href", "#"));
	}
	private MDCDrawer _mdc;
	private final HtmlContentBuilder<HTMLElement> _this;
	private DrawerHeader header;
	private DrawerContent content;
	private DrawerElement(HtmlContentBuilder<HTMLElement> e) {
		super(e);
		_this = e;
		layout();
		onAttach(evt->Scheduler.get().scheduleDeferred(()->_mdc = new MDCDrawer(element())));
	}
	private void layout() {
		clear();
		if(header!=null) _this.add(header);
		if(content!=null) _this.add(content);
	}
	public DrawerElement header(DrawerHeader header) {
		this.header = header;
		layout();
		return this;
	}
	public DrawerElement content(DrawerContent content) {
		this.content = content;
		layout();
		return this;
	}
	public DrawerElement target(HtmlContentBuilder<?> elem) {
		elem.css("mdc-drawer-app-content");
		return this;
	}
	public DrawerElement target(HTMLElement elem) {
		elem.classList.add("mdc-drawer-app-content");
		return this;
	}
	public DrawerElement open() {
		_mdc.open = true;
		return this;
	}
	public DrawerElement close() {
		_mdc.open = false;
		return this;
	}
	public DrawerElement toggle() {
		_mdc.open = !_mdc.open;
		return this;
	}
	public boolean isOpened() {
		return _mdc.open;
	}
	@Override
	public DrawerElement that() {
		return this;
	}

	@Override
	public HandlerRegistration onAttach(EventListener listener) {
		return onAttach(element(), listener);
	}

	@JsType(isNative = true, namespace = "mdc.drawer", name="MDCDrawer")
	private final static class MDCDrawer {
		@JsProperty private boolean open;
		public MDCDrawer(Element elem){}
	}
	public static class DrawerHeader extends HTMLElementBuilder<HTMLDivElement, DrawerHeader> {
		private final HtmlContentBuilder<HTMLDivElement> _this;
		private DrawerHeader(HtmlContentBuilder<HTMLDivElement> e) {
			super(e);
			_this = e;
			layout();
		}
		private void layout() {

		}
		public DrawerHeader title(HtmlContentBuilder<?> element) {
			element.css("mdc-drawer__title");
			_this.add(element);
			return this;
		}
		public DrawerHeader subtitle(HtmlContentBuilder<?> element) {
			element.css("mdc-drawer__subtitle");
			_this.add(element);
			return this;
		}
		@Override
		public DrawerHeader that() {
			return this;
		}
	}
	public static class DrawerContent extends HTMLElementBuilder<HTMLDivElement, DrawerContent> {
		private final HtmlContentBuilder<HTMLElement> list = nav().css("mdc-list");
		private final HtmlContentBuilder<HTMLDivElement> _this;
		private DrawerContent(HtmlContentBuilder<HTMLDivElement> e) {
			super(e);
			_this = e;
			layout();
		}
		private void layout() {
			_this.add(list);
		}
		public DrawerContent header(String header) {
			list.add(label().css("mdc-list-group__subheader").add(header));
			return this;
		}
		public DrawerContent add(DrawerListItem item) {
			list.add(item);
			return this;
		}
		public DrawerContent divider() {
			list.add(hr().css("mdc-list-divider"));
			return this;
		}
		@Override
		public DrawerContent that() {
			return this;
		}
	}

	public static class DrawerListItem extends HTMLElementBuilder<HTMLAnchorElement, DrawerListItem> {
		private final HtmlContentBuilder<HTMLAnchorElement> _this;
		private IconElement iconElement;
		private String label;
		private DrawerListItem(HtmlContentBuilder<HTMLAnchorElement> e) {
			super(e);
			_this = e;
			layout();
		}
		private void layout() {
			clear();
			if(this.iconElement !=null) _this.add(iconElement);
			if(this.label!=null) _this.add(label);
		}
		public DrawerListItem activate(boolean activated) {
			if(activated) css("mdc-list-item--activated");
			else ncss("mdc-list-item--activated");
			return this;
		}
		public DrawerListItem icon(IconElement iconElement) {
			if(iconElement !=null) iconElement.css("mdc-list-item__graphic").aria("hidden", "true");
			this.iconElement = iconElement;
			layout();
			return this;
		}
		public DrawerListItem text(String text) {
			this.label = text;
			layout();
			return this;
		}
		@Override
		public DrawerListItem that() {
			return this;
		}
	}
}
