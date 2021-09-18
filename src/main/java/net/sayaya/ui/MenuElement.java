package net.sayaya.ui;

import elemental2.dom.Element;
import elemental2.dom.HTMLDivElement;
import jsinterop.annotations.JsIgnore;
import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;
import net.sayaya.ui.event.HasSelectionChangeHandlers;
import net.sayaya.ui.event.HasValueChangeHandlers;
import org.jboss.elemento.HtmlContentBuilder;

import static org.jboss.elemento.Elements.div;
import static org.jboss.elemento.EventType.bind;

public class MenuElement extends HTMLElementBuilder<HTMLDivElement, MenuElement> {
	public static MenuElement build(ListElement listElement) {
		MenuElement elem = new MenuElement(div(), listElement);
		bind(elem.element,"DOMNodeInserted", evt->{
			elem._mdc = inject(elem.element());
		//	elem._foundation = foundation(elem._mdc);
		});
		return elem;
	}
	private static native MDCMenu inject(Element elem) /*-{
        return $wnd.mdc.menu.MDCMenu.attachTo(elem);
    }-*/;
	private final ListElement<?> listElement;
	private MDCMenu _mdc;
	private MenuElement(HtmlContentBuilder<HTMLDivElement> e, ListElement<?> listElement) {
		super(e.css("mdc-menu", "mdc-menu-surface"));
		this.listElement = listElement;
		e.add(listElement);
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

	@JsType(isNative = true, namespace = "mdc.menu", name="MDCMenu")
	private final static class MDCMenu {
		@JsProperty
		private boolean open;
		public native void setFixedPosition(boolean fixedPosition);
		public native void setAbsolutePosition(int x, int y);
	}
}

/*
<div class="mdc-menu mdc-menu-surface">
  <ul class="mdc-list" role="menu" aria-hidden="true" aria-orientation="vertical" tabindex="-1">
    <li class="mdc-list-item" role="menuitem">
      <span class="mdc-list-item__ripple"></span>
      <span class="mdc-list-item__text">A Menu Item</span>
    </li>
    <li class="mdc-list-item" role="menuitem">
      <span class="mdc-list-item__ripple"></span>
      <span class="mdc-list-item__text">Another Menu Item</span>
    </li>
  </ul>
</div>
 */