package net.sayaya.ui;

import com.google.gwt.core.client.Scheduler;
import elemental2.dom.*;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;
import org.jboss.elemento.HtmlContentBuilder;

import static org.jboss.elemento.Elements.*;
import static org.jboss.elemento.EventType.bind;

public class DrawerElement extends HTMLElementBuilder<HTMLElement, DrawerElement> {
	public static DrawerElement drawer() {
		DrawerElement elem = new DrawerElement(aside().css("mdc-drawer mdc-drawer--dismissible"));
		bind(elem, "DOMNodeInserted", evt->Scheduler.get().scheduleDeferred(()->elem._mdc=inject(elem.element())));
		return elem;
	}
	public static DrawerHeader header() {
		DrawerHeader elem = new DrawerHeader(div().css("mdc-drawer__header"));
		return elem;
	}
	public static DrawerContent content() {
		DrawerContent elem = new DrawerContent(div().css("mdc-drawer__content"));
		return elem;
	}
	public static DrawerListItem item() {
		DrawerListItem elem = new DrawerListItem(a().css("mdc-list-item").attr("href", "#"));
		return elem;
	}
	private native static MCDDrawer inject(Element elem) /*-{
        return $wnd.mdc.drawer.MDCDrawer.attachTo(elem);
    }-*/;
	private MCDDrawer _mdc;
	private final HtmlContentBuilder<HTMLElement> _this;
	private DrawerHeader header;
	private DrawerContent content;
	private DrawerElement(HtmlContentBuilder<HTMLElement> e) {
		super(e);
		_this = e;
		layout();
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
	@Override
	public DrawerElement that() {
		return this;
	}

	@JsType(isNative = true, namespace = "mdc.drawer", name="MDCDrawer")
	private final static class MCDDrawer {
		@JsProperty
		private boolean open;
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
			if(iconElement !=null) iconElement.css("mdc-list-item__graphic").attr("area-hidden", "true");
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
