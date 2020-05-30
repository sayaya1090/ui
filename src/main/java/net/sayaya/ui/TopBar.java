package net.sayaya.ui;

import elemental2.dom.*;
import lombok.Setter;
import lombok.Singular;
import lombok.experimental.Accessors;
import net.sayaya.ui.event.HandlerRegistration;
import net.sayaya.ui.event.HasClickHandlers;
import org.jboss.elemento.HtmlContentBuilder;

import java.util.LinkedList;
import java.util.List;

import static org.jboss.elemento.Elements.*;

public class TopBar implements IsHTMLElement<HTMLElement, TopBar> {
	private final HtmlContentBuilder<HTMLDivElement> row = div().css("mdc-top-app-bar__row");
	private final HTMLElement _this = header().css("mdc-top-app-bar")
											  .add(row)
											  .element();
	public static TopBarBuilder topbar() {
		return new TopBarBuilder();
	}
	private TopBar() {}
	native static void inject(Element elem, Element contents) /*-{
        var bar = $wnd.mdc.topAppBar.MDCTopAppBar.attachTo(elem);
        bar.setScrollTarget(contents);
        contents.classList.add("mdc-top-app-bar--fixed-adjust");
    }-*/;
	final void inject(Element elem) {
		inject(_this, elem);
	}
	public TopBar add(TopBarSection section) {
		row.add(section);
		return this;
	}
	@Override
	public HTMLElement element() {
		return _this;
	}

	@Setter
	@Accessors(fluent=true)
	public static class TopBarBuilder {
		private TopBarType type;
		@Singular
		private List<TopBarSection> sections = new LinkedList<>();
		public TopBarBuilder section(TopBarSection section) {
			sections.add(section);
			return this;
		}
		public TopBar build(HTMLElement contents) {
			TopBar topbar = new TopBar();
			if(type == TopBarType.FIXED) topbar.element().classList.add("mdc-top-app-bar--fixed");
			else if(type == TopBarType.SHORT) topbar.element().classList.add("mdc-top-app-bar--short");
			else if(type == TopBarType.DENSE) topbar.element().classList.add("mdc-top-app-bar--dense");
			for(TopBarSection section: sections) topbar.add(section);
			topbar.inject(contents);
			return topbar;
		}
	}
	public enum TopBarType {
		STANDARD, FIXED, DENSE, PROMINENT, SHORT
	}
	public static class TopBarSection implements IsHTMLElement<HTMLElement, TopBarSection> {
		private final HtmlContentBuilder<HTMLElement> _this = section().css("mdc-top-app-bar__section");
		@Override
		public HTMLElement element() {
			return _this.element();
		}
		private TopBarSection(){}
		public static TopBarSectionBuilder left() {
			return new TopBarSectionBuilder().isRight(false);
		}
		public static TopBarSectionBuilder right() {
			return new TopBarSectionBuilder().isRight(true);
		}
		public TopBarSection title(String title) {
			_this.add(span().css("mdc-top-app-bar__title").style("cursor: default;").add(title));
			return this;
		}
		public TopBarSection add(TopBarButton elem) {
			_this.add(elem);
			return this;
		}
		public TopBarSection add(HTMLElement elem) {
			_this.add(elem);
			return this;
		}
		@Setter
		@Accessors(fluent = true)
		public static class TopBarSectionBuilder {
			private boolean isRight = false;
			public TopBarSection build() {
				TopBarSection elem = new TopBarSection();
				if(isRight) elem._this.css("mdc-top-app-bar__section--align-end");
				else elem._this.css("mdc-top-app-bar__section--align-start");
				return elem;
			}
		}
	}
	public static class TopBarButton implements IsHTMLElement<HTMLButtonElement, TopBarButton>, Focusable<TopBarButton>, HasClickHandlers {
		private final HtmlContentBuilder<HTMLButtonElement> _this = org.jboss.elemento.Elements.button().css("material-icons", "mdc-icon-button");
		TopBarButton(String icon) {
			_this.textContent(icon);
		}
		public static TopBarButtonBuilder nav() {
			return new TopBarButtonBuilder().type(TopBarButtonType.NAVIGATION);
		}
		public static TopBarButtonBuilder action() {
			return new TopBarButtonBuilder().type(TopBarButtonType.ACTION);
		}
		@Override
		public final HandlerRegistration addClickHandler(EventListener listener) {
			return addClickHandler(element(), listener);
		}
		public final TopBarButton enabled(boolean enabled) {
			if(enabled) element().removeAttribute("disabled");
			else element().setAttribute("disabled", true);
			return self();
		}
		@Override
		public TopBarButton accessKey(char key) {
			element().setAttribute("accessKey", String.valueOf(key));
			return self();
		}
		@Override
		public TopBarButton focus() {
			element().focus();
			return self();
		}
		@Override
		public final HTMLButtonElement element() {
			return _this.element();
		}
		@Setter
		@Accessors(fluent = true)
		public static class TopBarButtonBuilder {
			private TopBarButtonType type;
			public TopBarButton build(String icon) {
				TopBarButton elem = new TopBarButton(icon);
				if(type == TopBarButtonType.NAVIGATION) elem._this.css("mdc-top-app-bar__navigation-icon--unbounded");
				else if(type == TopBarButtonType.ACTION) elem._this.css("mdc-top-app-bar__action-item--unbounded");
				return elem;
			}
		}
		private enum TopBarButtonType {
			NAVIGATION, ACTION
		}
	}
}
