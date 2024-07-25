package net.sayaya.ui;

import elemental2.dom.Element;
import elemental2.dom.HTMLDivElement;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;
import net.sayaya.ui.util.OnAttachEvent;
import org.jboss.elemento.Elements;
import org.jboss.elemento.HTMLContainerBuilder;
import org.jboss.elemento.IsElement;

import static org.jboss.elemento.Elements.div;

public class MenuElement extends HTMLElementBuilder<HTMLDivElement, MenuElement> {
	public static MenuElement build(ListElement listElement) {
		MenuElement elem = new MenuElement(div(), listElement);
		return elem;
	}
	private final ListElement<?> listElement;
	private MDCMenu _mdc;
	private MenuElement(HTMLContainerBuilder<HTMLDivElement> e, ListElement<?> listElement) {
		super(e.css("mdc-menu", "mdc-menu-surface"));
		this.listElement = listElement;
		e.add(listElement);
		_mdc = new MDCMenu(element());
	}
	public void with(IsElement<?> e) {
		with(e.element());
	}
	public void with(Element e) {
		var attach = (elemental2.dom.EventListener) evt->{
			Elements.body().add(this);
			e.addEventListener("DOMNodeRemoved", evt2->{
				if(evt.target != e) return;
				element().remove();
			});
		};
		if(e.isConnected) attach.handleEvent(null);
		else OnAttachEvent.observe(e, evt->{
			if(evt.target != e) return;
			else attach.handleEvent(evt);
		});
	}
	@Override
	public MenuElement that() {
		return this;
	}
	public void open() {
		_mdc.open = true;
	}
	public void close() {
		_mdc.open = false;
	}
	public MenuElement positionFixed(boolean fixedPosition) {
		_mdc.setFixedPosition(fixedPosition);
		return that();
	}
	public MenuElement position(int x, int y) {
		_mdc.setAbsolutePosition(x, y);
		return that();
	}

	@JsType(isNative = true, namespace = "mdc.menu")
	private final static class MDCMenu {
		@JsProperty private boolean open;
		public MDCMenu(Element element){}
		public native void setFixedPosition(boolean fixedPosition);
		public native void setAbsolutePosition(int x, int y);
	}
}