package net.sayaya.ui.table;

import com.google.gwt.core.client.Scheduler;
import elemental2.dom.*;
import net.sayaya.ui.IsHTMLElement;
import net.sayaya.ui.event.HasValueChangeHandlers;

import static net.sayaya.ui.table.Table.GSS;
import static org.jboss.gwt.elemento.core.Elements.div;

public class Viewport implements IsHTMLElement<HTMLDivElement, Viewport> {
	private final HTMLDivElement virtual = div().element();
	private final HTMLDivElement element = div().css(GSS.viewport()).add(virtual).element();
	private final Table<?> table;
	private final ViewportParam param = new ViewportParam();
	public Viewport(Table<?> table) {
		this.table = table;
		virtual.appendChild(table.element());
		table.addValueChangeHandler(this::update);
		this.addAttachHandler(attach->update(null));
		this.element.onscroll = p0 -> {
			param.prevScrollTop = param.scrollTop;
			param.scrollTop = element.scrollTop;
			table.viewport(param);
			return null;
		};
		DomGlobal.window.addEventListener("resize", evt->{
			param.viewportWidth = element().offsetWidth;
			param.viewportHeight = element().offsetHeight;
			table.viewport(param);
			virtual.style.height = CSSProperties.HeightUnionType.of(param.getVirtualHeight() + "px");
		});
	}

	private void update(HasValueChangeHandlers.ValueChangeEvent<Data[]> evt) {
	//	table.viewport(param);
		virtual.style.height = CSSProperties.HeightUnionType.of(param.getVirtualHeight() + "px");
	}
/*
	private void onScroll() {
		double scrollTop = element.scrollTop;
		if(Math.abs(scrollTop - prevScrollTop) > viewportHeight) onJump(scrollTop);
		else onNearScroll(scrollTop);
		renderViewport(scrollTop);
	}

	private void onJump(double scrollTop) {
		page = Math.floor(scrollTop / pageHeight);
		offset = Math.round(page * cj);
		prevScrollTop = scrollTop;
		table.element().style.top = scrollTop + "px";
	}

	private void onNearScroll(double scrollTop) {

	}

	private void renderViewport(double scrollTop) {

	}*/
	@Override
	public HTMLDivElement element() {
		return element;
	}

	@lombok.Data
	public static class ViewportParam {
		private double viewportWidth;
		private double viewportHeight;
		private double virtualWidth;
		private double virtualHeight;
		private double scrollTop = 0;
		private double prevScrollTop = 0;

	}
}
