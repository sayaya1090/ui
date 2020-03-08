package net.sayaya.ui.table;

import com.google.gwt.core.client.Scheduler;
import elemental2.dom.*;
import net.sayaya.ui.IsHTMLElement;

import static net.sayaya.ui.table.Table.GSS;
import static org.jboss.gwt.elemento.core.Elements.div;

public class Viewport implements IsHTMLElement<HTMLDivElement, Viewport> {
	private final HTMLDivElement virtual = div().style("border: 2px solid #FF00FF").element();
	private final HTMLDivElement element = div().css(GSS.viewport()).add(virtual).element();
	private final Table<?> table;
	private final ViewportParam param = new ViewportParam();
	public Viewport(Table<?> table) {
		this.table = table;
	//	param.setParent(element);
		virtual.appendChild(table.element());
		table.addValueChangeHandler(evt->updateVirtualHeight());
		this.element.onscroll = p0 -> {
			Scheduler.get().scheduleDeferred(()->{
				param.scrollTop = element.scrollTop;
				if(table.viewport(param)) {
					param.prevScrollTop = param.scrollTop;
					element.scrollTop = param.scrollTop;
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
		param.setViewportHeight(element.offsetHeight);
		double virtualHeight = table.getTotalHeight();
		param.setVirtualHeight(virtualHeight);
		// virtual.style.height = CSSProperties.HeightUnionType.of(virtualHeight + "px");
		virtual.style.setProperty("height", virtualHeight + "px");
	}

	@Override
	public HTMLDivElement element() {
		return element;
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
