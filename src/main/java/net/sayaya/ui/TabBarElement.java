package net.sayaya.ui;

import elemental2.dom.*;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;
import jsinterop.base.Js;
import net.sayaya.ui.event.HasValueChangeHandlers;
import org.gwtproject.event.shared.HandlerRegistration;
import org.jboss.elemento.HtmlContentBuilder;

import java.util.HashSet;
import java.util.Set;

import static org.jboss.elemento.Elements.*;

public class TabBarElement extends HTMLElementBuilder<HTMLDivElement, TabBarElement> implements HasValueChangeHandlers<Integer> {
	public static TabBarElement tabBar(Tab... tabs) {
		return new TabBarElement(div(), tabs);
	}
	public static Tab tab() {
		return new Tab(button());
	}
	private final MDCTabBar _mdc;
	private final Set<ValueChangeEventListener<Integer>> listeners = new HashSet<>();
	private TabBarElement(HtmlContentBuilder<HTMLDivElement> e, Tab... tabs) {
		super(e);
		var content = div();
		e.css("mdc-tab-bar").attr("role", "tablist")
				.add(div().css("mdc-tab-scroller")
						.add(div().css("mdc-tab-scroller__scroll-area")
								.add(content.css("mdc-tab-scroller__scroll-content"))));
		for(Tab tab: tabs) content.add(tab);
		EventListener wrapper = evt->{
			Object detail = Js.asPropertyMap(evt).get("detail");
			int idx = Integer.parseInt(String.valueOf(Js.asPropertyMap(detail).get("index")));
			ValueChangeEvent<Integer> cast = ValueChangeEvent.event(evt, idx);
			for(ValueChangeEventListener<Integer> listener: listeners) listener.handle(cast);
		};
		_mdc = new MDCTabBar(element());
		element().addEventListener("MDCTabBar:activated", wrapper);
	}
	public TabBarElement activate(int idx) {
		_mdc.foundation.activateTab(idx);
		return that();
	}
	@Override
	public TabBarElement that() {
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
		private HtmlContentBuilder<HTMLElement> content = span();
		public Tab(HtmlContentBuilder<HTMLButtonElement> e) {
			super(e);
			e.css("mdc-tab").attr("role", "tab").attr("tabindex", "0")
					.add(content.css("mdc-tab__content"))
					.add(span().css("mdc-tab-indicator")
							.add(span().css("mdc-tab-indicator__content", "mdc-tab-indicator__content--underline")))
					.add(span().css("mdc-tab__ripple"));
		}
		public Tab icon(String icon) {
			var icons = content.element().getElementsByClassName("mdc-tab__icon");
			if(icons!=null) for(var e: icons.asList()) e.remove();
			if(icon != null) content.element().prepend(span().css("mdc-tab__icon", "material-icons").aria("hidden", "true").add(icon).element());
			return that();
		}
		public Tab text(String text) {
			var labels = content.element().getElementsByClassName("mdc-tab__text-label");
			if(labels!=null) for(var e: labels.asList()) e.remove();
			if(text != null) content.element().append(span().css("mdc-tab__text-label").add(text).element());
			return that();
		}
		@Override
		public Tab that() {
			return this;
		}
	}
	@JsType(isNative = true, namespace = "mdc.tabBar", name="MDCTabBar")
	private static final class MDCTabBar {
		@JsProperty public MDCTabBarFoundation foundation;
		public MDCTabBar(Element element){}
		public native int getFocusedTabIndex();
		public native void addEventListener(String type, EventListener listener);
		public native void removeEventListener(String type, EventListener listener);
	}
	@JsType(isNative = true, namespace= JsPackage.GLOBAL, name="Object")
	private static final class MDCTabBarFoundation {
		@JsProperty public MDCTabBarFoundationAdapter adapter;
		public native void activateTab(int index);
	}
	@JsType(isNative = true, namespace= JsPackage.GLOBAL, name="Object")
	private static final class MDCTabBarFoundationAdapter {
		public native void activateTabAtIndex(int index);
		public native void setUseAutomaticActivation(boolean a);
	}
}
