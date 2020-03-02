package net.sayaya.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import elemental2.dom.EventListener;
import elemental2.dom.HTMLElement;
import jsinterop.base.JsPropertyMap;
import net.sayaya.ui.animate.Animation;
import net.sayaya.ui.event.HandlerRegistration;
import net.sayaya.ui.event.HasRemoveHandlers;
import net.sayaya.ui.style.Style;

import static net.sayaya.ui.animate.Animation.*;
import static org.jboss.gwt.elemento.core.Elements.*;

public class Chip implements IsHTMLElement<HTMLElement, Chip>, HasRemoveHandlers {
	private HTMLElement thumbnails;
	private HTMLElement remove;
	private final HTMLElement container = span().element();
	private final Style style = new Style().setColor("rgb(35, 47, 52)")
										   .setBackgroundColor("rgba(35, 47, 52, 0.12)")
										   .setCursor("pointer")
										   .setWhiteSpace("nowrap").setFontFamily("Work Sans")
										   .setFontWeight("normal")
										   .setFontSize("14px").setBorderRadius("14px")
										   .setOutlineStyle("none")
										   .setPaddingLeft("10px").setPaddingRight("10px").setPaddingTop("5px").setPaddingBottom("5px")
										   .setMarginLeft("0").setMarginRight("10px")
										   .setLetterSpacing("1px");
	private final Style styleRemove = new Style().setMarginLeft("5px");
	private final String text;
	public Chip(String text) {
		this.text = text;
		container.innerHTML = text;
		setStyle(style);
	}
	public String getText() {
		return text;
	}
	public Chip setRemovable(boolean removable) {
		if(removable) {
			if(remove == null) {
				remove = i().css("fa fa-times-circle").element();
				remove.addEventListener("click", evt->{
					AnimationImpl fade = animate(container, 300, JsPropertyMap.of("opacity", "1"), JsPropertyMap.of("opacity", "0"));
					fade.onfinish = container::remove;
				});
				styleRemove.apply(remove.style);
			}
			container.appendChild(remove);
		} else if(remove!=null) remove.remove();
		return this;
	}
	@Override
	public HandlerRegistration addRemoveHandler(EventListener listener) {
		return addRemoveHandler(container, listener);
	}
	@Override
	public HTMLElement element() {
		return container;
	}
}
