package net.sayaya.ui.table;

import com.google.gwt.core.client.Scheduler;
import elemental2.dom.*;
import net.sayaya.ui.HTMLElementBuilder;
import org.jboss.elemento.HtmlContentBuilder;

import static net.sayaya.ui.table.Table.GSS;
import static org.jboss.elemento.Elements.div;

public class Viewport extends HTMLElementBuilder<HTMLDivElement, Viewport> {
	private final HTMLDivElement virtual = div().style("border: 2px solid #FF00FF").element();
	private final HtmlContentBuilder<HTMLDivElement> element;
	private final Table<?> table;
	private final ViewportParam param = new ViewportParam();
	public Viewport(HtmlContentBuilder<HTMLDivElement> e, Table<?> table) {
		super(e);
		element = e;
		e.css(GSS.viewport()).add(virtual).element();
		this.table = table;
	//	param.setParent(element);
		virtual.appendChild(table.element());
		table.onValueChange(evt->updateVirtualHeight());
		this.element().onscroll = p0 -> {
			Scheduler.get().scheduleDeferred(()->{
				param.scrollTop = element().scrollTop;
				if(table.viewport(param)) {
					param.prevScrollTop = param.scrollTop;
					element().scrollTop = param.scrollTop;
				}
			});
			return null;
		};
		DomGlobal.window.addEventListener("resize", evt->{
			param.viewportHeight = element().offsetHeight;
			updateVirtualHeight();
		});
		Scheduler.get().scheduleDeferred(()->updateVirtualHeight());
	}

	private void updateVirtualHeight() {
		param.setViewportHeight(element().offsetHeight);
		double virtualHeight = table.totalHeight();
		param.setVirtualHeight(virtualHeight);
		// virtual.style.height = CSSProperties.HeightUnionType.of(virtualHeight + "px");
		virtual.style.setProperty("height", virtualHeight + "px");
	}

	@Override
	public Viewport that() {
		return this;
	}

	@lombok.Data
	public static class ViewportParam {
//		private HTMLDivElement parent;
		private double viewportHeight;
		private double virtualHeight;
		private double scrollTop = 0;
		private double prevScrollTop = 0;
	}
}
