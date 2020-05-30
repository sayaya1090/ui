package net.sayaya.ui;

import com.google.gwt.core.client.Scheduler;
import elemental2.dom.*;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.jboss.elemento.Elements;
import org.jboss.elemento.HtmlContentBuilder;

import static org.jboss.elemento.Elements.*;

public class Drawer implements IsHTMLElement<HTMLElement, Drawer> {
	private final HtmlContentBuilder<HTMLElement> _this = aside().css("mdc-drawer mdc-drawer--dismissible");
	private Object _mdc;
	private Drawer() {}
	public static DrawerBuilder drawer() {
		return new DrawerBuilder();
	}
	Drawer header(DrawerHeader.DrawerHeaderBuilder header) {
		_this.add(header.element());
		return this;
	}
	Drawer content(DrawerContent content) {
		_this.add(content);
		return this;
	}
	native static Object inject(Element elem) /*-{
        return $wnd.mdc.drawer.MDCDrawer.attachTo(elem);
    }-*/;
	public final void inject() {
		_mdc = inject(element());
	}
	public static void setContent(Element elem) {
		elem.classList.add("mdc-drawer-app-content");
	}
	public native Drawer open() /*-{
    	this.@net.sayaya.ui.Drawer::_mdc.open = true;
		return this;
	}-*/;
	public native Drawer close() /*-{
    	this.@net.sayaya.ui.Drawer::_mdc.open = false;
		return this;
	}-*/;
	public native Drawer toggle() /*-{
    	this.@net.sayaya.ui.Drawer::_mdc.open = !this.@net.sayaya.ui.Drawer::_mdc.open;
		return this;
	}-*/;
	@Override
	public HTMLElement element() {
		return _this.element();
	}
	@Setter
	@Accessors(fluent=true)
	public static class DrawerBuilder {
		private DrawerHeader.DrawerHeaderBuilder header;
		private DrawerContent content;
		private DrawerBuilder(){}
		public Drawer build() {
			Drawer elem = new Drawer();
			if(header!=null) elem.header(header);
			if(content!=null) elem.content(content);
			Scheduler.get().scheduleDeferred(elem::inject);
			return elem;
		}
	}
	public static class DrawerHeader implements IsHTMLElement<HTMLDivElement, DrawerHeader> {
		private final HtmlContentBuilder<HTMLDivElement> _this = div().css("mdc-drawer__header");
		private DrawerHeader(){}
		public static DrawerHeaderBuilder drawerHeader() {
			return new DrawerHeaderBuilder();
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
		public HTMLDivElement element() {
			return _this.element();
		}
		@Setter
		@Accessors(fluent=true)
		public static class DrawerHeaderBuilder {
			private HtmlContentBuilder<?> title;
			private HtmlContentBuilder<?> subtitle;
			public DrawerHeader element() {
				DrawerHeader elem = new DrawerHeader();
				if(title!=null) elem.title(title);
				if(subtitle!=null) elem.subtitle(subtitle);
				return elem;
			}
		}
	}
	public static class DrawerContent implements IsHTMLElement<HTMLDivElement, DrawerContent> {
		private final HtmlContentBuilder<HTMLElement> list = Elements.nav().css("mdc-list");
		private final HTMLDivElement _this = div().css("mdc-drawer__content")
													.add(list)
													.element();
		private DrawerContent() {}
		public static DrawerContent drawerContent() {
			return new DrawerContent();
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
		public HTMLDivElement element() {
			return _this;
		}
	}
	public static class DrawerListItem implements IsHTMLElement<HTMLAnchorElement, DrawerListItem> {
		private final HtmlContentBuilder<HTMLAnchorElement> _this = a().css("mdc-list-item").attr("href", "#");
		private DrawerListItem(){}
		public static DrawerListItem item() {
			return new DrawerListItem();
		}
		public DrawerListItem activate(boolean activated) {
			if(activated) element().classList.add("mdc-list-item--activated");
			else element().classList.remove("mdc-list-item--activated");
			return this;
		}
		public DrawerListItem icon(String icon) {
			_this.add(i().css("material-icons", "mdc-list-item__graphic").add(icon).attr("area-hidden", "true"));
			return this;
		}
		public DrawerListItem text(String text) {
			_this.add(text);
			return this;
		}
		@Override
		public HTMLAnchorElement element() {
			return _this.element();
		}
	}
}
