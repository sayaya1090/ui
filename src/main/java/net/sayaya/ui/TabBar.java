package net.sayaya.ui;

import elemental2.dom.*;
import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;
import jsinterop.base.Js;
import lombok.Setter;
import lombok.experimental.Accessors;
import net.sayaya.ui.event.HasValueChangeHandlers;
import org.gwtproject.event.shared.HandlerRegistration;
import org.jboss.elemento.HtmlContentBuilder;

import java.util.HashSet;
import java.util.Set;

import static org.jboss.elemento.Elements.*;

public class TabBar extends HTMLElementBuilder<HTMLDivElement, TabBar> implements HasValueChangeHandlers<Integer> {
	public static TabBar tabBar(Tab... tabs) {
		TabBar elem = new TabBar(div());
		for(Tab tab: tabs) elem.add(tab);
		elem._mdc = inject(elem.element());
		elem.foundation = foundation(elem._mdc);
		return elem;
	}
	public static Tab tab() {
		return new Tab(button());
	}
	private native static MDCTabBar inject(Element elem) /*-{
        return $wnd.mdc.tabBar.MDCTabBar.attachTo(elem);
    }-*/;
	private static native MDCTabBarFoundation foundation(MDCTabBar mdc) /*-{
        return mdc.foundation.adapter;
    }-*/;
	private final HtmlContentBuilder<HTMLDivElement> content = div().css("mdc-tab-scroller__scroll-content");
	private final HtmlContentBuilder<HTMLDivElement> scroller = div().css("mdc-tab-scroller")
																	 .add(div().css("mdc-tab-scroller__scroll-area")
																			   .add(content));
	private final HtmlContentBuilder<HTMLDivElement> _this;
	private MDCTabBar _mdc;
	private MDCTabBarFoundation foundation;
	private final Set<ValueChangeEventListener<Integer>> listeners = new HashSet<>();
	private TabBar(HtmlContentBuilder<HTMLDivElement> e) {
		super(e.css("mdc-tab-bar").attr("role", "tablist"));
		_this = e;
		_this.add(scroller);
		EventListener wrapper = evt->{
			Object detail = Js.asPropertyMap(evt).get("detail");
			int idx = Integer.parseInt(String.valueOf(Js.asPropertyMap(detail).get("index")));
			ValueChangeEvent<Integer> cast = ValueChangeEvent.event(evt, idx);
			for(ValueChangeEventListener<Integer> listener: listeners) listener.handle(cast);
		};
		element().addEventListener("MDCTabBar:activated", wrapper);
	}
	public TabBar add(Tab tab) {
		content.add(tab);
		return that();
	}
	public TabBar activate(int idx) {
		foundation.activateTabAtIndex(idx);
		return that();
	}
	@Override
	public TabBar that() {
		return this;
	}

	@Override
	public Integer value() {
		return _mdc.getFocusedTabIndex();
	}

	@Override
	public HandlerRegistration onValueChange(ValueChangeEventListener<Integer> listener) {
		listeners.add(listener);
		return ()->listeners.remove(listener);
	}

	public final static class Tab extends HTMLElementBuilder<HTMLButtonElement, Tab> {
		private HtmlContentBuilder<HTMLElement> content = span().css("mdc-tab__content");
		private HtmlContentBuilder<HTMLElement> icon;
		private  HtmlContentBuilder<HTMLElement> label;
		private final HtmlContentBuilder<HTMLButtonElement> _this;
		public Tab(HtmlContentBuilder<HTMLButtonElement> e) {
			super(e.css("mdc-tab")
				   .attr("role", "tab")
				   .attr("tabindex", "0"));
			_this = e.add(content)
					 .add(span().css("mdc-tab-indicator")
								.add(span().css("mdc-tab-indicator__content", "mdc-tab-indicator__content--underline")))
					 .add(span().css("mdc-tab__ripple"));
		}
		private void layout() {
			if(icon!=null) content.add(icon);
			if(label!=null) content.add(label);
		}
		public Tab icon(String icon) {
			if(icon != null) this.icon = span().css("mdc-tab__icon", "material-icons").attr("aria-hidden", "true").add(icon);
			else this.icon = null;
			layout();
			return that();
		}
		public Tab text(String text) {
			if(text != null) this.label = span().css("mdc-tab__text-label").add(text);
			else this.label = null;
			layout();
			return that();
		}
		@Override
		public Tab that() {
			return this;
		}
	}
	@JsType(isNative = true, namespace = "mdc.tabbar", name="MDCTabBar")
	@Setter(onMethod_ = {@JsOverlay})
	@Accessors(fluent=true)
	private static final class MDCTabBar {
		public native int getFocusedTabIndex();
		public native void addEventListener(String type, EventListener listener);
		public native void removeEventListener(String type, EventListener listener);
	}
	@JsType(isNative = true, namespace= JsPackage.GLOBAL, name="Object")
	@Setter(onMethod_ = {@JsOverlay})
	@Accessors(fluent=true)
	private static final class MDCTabBarFoundation {
		public native void activateTabAtIndex(int index);
		public native void setUseAutomaticActivation(boolean a);
	}
}
