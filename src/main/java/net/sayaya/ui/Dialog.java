package net.sayaya.ui;

import elemental2.dom.*;
import jsinterop.annotations.JsType;
import lombok.experimental.Delegate;
import org.jboss.elemento.Elements;
import org.jboss.elemento.HtmlContentBuilder;
import org.jboss.elemento.IsElement;

import static org.jboss.elemento.Elements.div;
import static org.jboss.elemento.EventType.bind;

public class Dialog extends HTMLElementBuilder<HTMLDivElement, Dialog> {
	public static Dialog alert(String msg, ButtonElementText action1) {
		return alert(msg, action1, null);
	}
	public static Dialog alert(String msg, ButtonElementText action1, ButtonElementText action2) {
		Dialog elem = new Dialog().add(msg).action(action1).action(action2);
		elem.mdc = inject(elem.element());
		return elem;
	}
	public static Dialog simple(String title) {
		Dialog elem = new Dialog().title(title);
		elem.mdc = inject(elem.element());
		return elem;
	}
	public static Dialog confirmation(String title, ButtonElementText action1) {
		return confirmation(title, action1, null);
	}
	public static Dialog confirmation(String title, ButtonElementText action1, ButtonElementText action2) {
		Dialog elem = new Dialog().title(title).action(action1).action(action2);
		elem.mdc = inject(elem.element());
		return elem;
	}
	private static native MCDDialog inject(Element elem) /*-{
        var mdc = $wnd.mdc.dialog.MDCDialog.attachTo(elem);
        var contentElement = elem.querySelector("mdc-dialog__content");
        mdc.listen('MDCDialog:opened', function() {
            contentElement.setAttribute('aria-hidden', 'true');
        });
        mdc.listen('MDCDialog:closing', function() {
			contentElement.removeAttribute('aria-hidden');
		});
		return mdc;
    }-*/;
	private final HtmlContentBuilder<HTMLDivElement> elem;
	private HtmlContentBuilder<HTMLElement> title;
	private final HtmlContentBuilder<HTMLDivElement> content = div().css("mdc-dialog__content").id();
	private HtmlContentBuilder<HTMLDivElement> actions;
	private final HtmlContentBuilder<HTMLDivElement> surface = div().css("mdc-dialog__surface").attr("role", "alertdialog");
	private final HtmlContentBuilder<HTMLDivElement> scrim = div().css("mdc-dialog__scrim");
	private final HtmlContentBuilder<HTMLDivElement> container = div().css("mdc-dialog__container").add(surface);
	@Delegate
	private MCDDialog mdc;
	private Dialog() {
		this(div());
	}
	private Dialog(HtmlContentBuilder<HTMLDivElement> e) {
		super(e.css("mdc-dialog"));
		this.elem = e;
		elem.add(container).add(scrim);
	}
	private Dialog layout() {
		surface.element().innerHTML = "";
		if(title!=null) surface.add(title).attr("aria-labelledby", title.element().id);
		surface.add(content).attr("aria-describedby", content.element().id);
		if(actions!=null) surface.add(actions);
		surface.element().setAttribute("area-modal", true);
		return that();
	}
	public Dialog title(String title) {
		if(title != null && !title.trim().isEmpty()) this.title = Elements.htmlElement("h2", HTMLElement.class).css("mdc-dialog__title").id().add(title);
		else this.title = null;
		layout();
		return that();
	}
	private Dialog action(ButtonElementText btn) {
		if(btn == null) return that();
		if(actions == null) actions = div().css("mdc-dialog__actions");
		btn.css("mdc-dialog__button");
		actions.add(btn);
		layout();
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
	@Override
	public Dialog that() {
		return this;
	}

	@JsType(isNative = true, namespace = "mdc.dialog", name="MDCDialog")
	private final static class MCDDialog {
	//	public native void layout();
		public native void open();
		public native void close();
	}
}