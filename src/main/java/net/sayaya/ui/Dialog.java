package net.sayaya.ui;

import elemental2.dom.*;
import jsinterop.annotations.JsFunction;
import jsinterop.annotations.JsType;
import org.jboss.elemento.Elements;
import org.jboss.elemento.HtmlContentBuilder;
import org.jboss.elemento.IsElement;

import static org.jboss.elemento.Elements.div;

public class Dialog extends HTMLElementBuilder<HTMLDivElement, Dialog> {
	public static Dialog alert(String msg, ButtonElementText action1) {
		return alert(msg, action1, null);
	}
	public static Dialog alert(String msg, ButtonElementText action1, ButtonElementText action2) {
		return new Dialog().add(msg).action(action1).action(action2);
	}
	public static Dialog simple(String title) {
		return new Dialog().title(title);
	}
	public static Dialog confirmation(String title, ButtonElementText action1) {
		return confirmation(title, action1, null);
	}
	public static Dialog confirmation(String title, ButtonElementText action1, ButtonElementText action2) {
		return new Dialog().title(title).action(action1).action(action2);
	}
	private final HtmlContentBuilder<HTMLDivElement> content = div().id();
	private final HtmlContentBuilder<HTMLDivElement> surface = div();
	private final MCDDialog mdc;
	private Dialog() {
		this(div());
	}
	private Dialog(HtmlContentBuilder<HTMLDivElement> e) {
		super(e);
		e.css("mdc-dialog")
			.add(div().css("mdc-dialog__container")
					.add(surface.css("mdc-dialog__surface")
							.attr("role", "alertdialog")
							.attr("area-modal", "true")
							.attr("aria-describedby", content.element().id)
							.add(content.css("mdc-dialog__content"))))
			.add(div().css("mdc-dialog__scrim"));
		mdc = new MCDDialog(element());
		mdc.listen("MDCDialog:opened", ()->content.attr("aria-hidden", "true"));
		mdc.listen("MDCDialog:closing", ()-> content.element().removeAttribute("aria-hidden"));
	}
	public Dialog title(String title) {
		if(title != null && !title.trim().isEmpty()) {
			var elemTitle = Elements.htmlElement("h2", HTMLElement.class).css("mdc-dialog__title").id().add(title);
			surface.attr("aria-labelledby", elemTitle.element().id)
					.element().insertBefore(elemTitle.element(), content.element());
		} else {
			var elemTitle = element().getElementsByClassName("mdc-dialog__title");
			if(elemTitle!=null) for(var e: elemTitle.asList()) e.remove();
			surface.element().removeAttribute("aria-labelledby");
		}
		return that();
	}
	private Dialog action(ButtonElementText btn) {
		var elemActions = element().getElementsByClassName("mdc-dialog__actions");
		if(btn != null) {
			if(elemActions==null || elemActions.length <= 0) {
				var elemAction = div().css("mdc-dialog__actions").add(btn.css("mdc-dialog__button"));
				surface.add(elemAction);
			} else elemActions.getAt(0).append(btn.css("mdc-dialog__button").element());
		} else {
			if(elemActions!=null) for(var e: elemActions.asList()) e.remove();
		}
		return that();
	}
	public Dialog add(String text) {
		content.add(text);
		return that();
	}
	public Dialog add(Node element) {
		content.add(element);
		return that();
	}
	public Dialog add(IsElement<?> element) {
		content.add(element);
		return that();
	}
	public final void open() {
		mdc.open();
	}
	public final void close() {
		mdc.close();
	}
	@Override
	public Dialog that() {
		return this;
	}

	@JsType(isNative = true, namespace = "mdc.dialog", name="MDCDialog")
	private final static class MCDDialog {
		public MCDDialog(Element elem){}
		public native void listen(String msg, DialogEventHandler handler);
		public native void open();
		public native void close();
	}
	@JsFunction
	private interface DialogEventHandler {
		void exec();
	}
}