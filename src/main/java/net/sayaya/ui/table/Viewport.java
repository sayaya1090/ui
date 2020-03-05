package net.sayaya.ui.table;

import com.google.gwt.core.client.Scheduler;
import elemental2.dom.*;
import net.sayaya.ui.IsHTMLElement;

import static net.sayaya.ui.table.Table.GSS;
import static org.jboss.gwt.elemento.core.Elements.div;

public class Viewport implements IsHTMLElement<HTMLDivElement, Viewport> {
	private int width, height;
	private final HTMLDivElement virtual = div().element();
	private final HTMLDivElement element = div().css(GSS.viewport()).add(virtual).element();
	private final Table<?> table;
	public Viewport(Table<?> table) {
		this.table = table;
		virtual.appendChild(table.element());
		Scheduler.get().scheduleDeferred(()->update());
		this.element.onscroll = p0 -> {
			onScroll();
			return null;
		};
	}
	int viewportHeight = 0;
	int rowHeight = 100;
	long itemCount = 100;
	int cj = 0;
	double page = 0;
	long offset = 0;
	double prevScrollTop = 0;
	long virtualHeight = rowHeight * itemCount;
	long pageHeight = virtualHeight / 100;
	double pageNum = Math.ceil(virtualHeight / (double) pageHeight); // 100?
	private void update() {
		viewportHeight = element.offsetHeight;
		virtual.style.height = CSSProperties.HeightUnionType.of(virtualHeight + "px");
	}

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

	}
	@Override
	public HTMLDivElement element() {
		return element;
	}
}
