package net.sayaya.ui.table;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import elemental2.dom.DomGlobal;
import elemental2.dom.HTMLTableElement;
import net.sayaya.ui.IsHTMLElement;
import net.sayaya.ui.event.HandlerRegistration;
import net.sayaya.ui.event.HasValueChangeHandlers;

import java.util.Arrays;
import java.util.HashSet;

import static org.jboss.gwt.elemento.core.Elements.*;

public class Table<V> implements IsHTMLElement<HTMLTableElement, Table<V>>, HasValueChangeHandlers<Data[]> {
	protected interface Resource extends ClientBundle {
		@Source("Table.gss")
		Resource.Style style();

		interface Style extends CssResource {
			String viewport();
			String table();
		}
	}
	private static final Resource RESOURCE =  GWT.create(Resource.class);
	static final Resource.Style GSS = RESOURCE.style();
	static {
		RESOURCE.style().ensureInjected();
	}
	private final Mapper<V, Data> mapper;
	private final HTMLTableElement element = table().css(GSS.table()).style("top: 0px;").element();
	private final HashSet<ValueChangeEventListener<Data[]>> listeners = new HashSet<>();
	private boolean selectionEnabled;
	private TableHeader header;
	private TableBody body;
	private Data[] data;
	private double top = 0;
	public Table(Mapper<V, Data> mapper) {
		this.mapper = mapper;
	}
	public Table<V> set(TableHeader header) {
		this.header = header;
		element.appendChild(header.element());
		return this;
	}
	public Table<V> set(TableBody body) {
		this.body = body;
		element.appendChild(body.element());
		return this;
	}
	public Table<V> values(V[] values) {
		data = Arrays.stream(values).map(mapper::map).toArray(Data[]::new);
		body.values(data).set(0);
		return this;
	}
	@Override
	public Data[] value() {
		return data;
	}
	@Override
	public HandlerRegistration addValueChangeHandler(ValueChangeEventListener<Data[]> listener) {
		listeners.add(listener);
		return ()->listeners.remove(listener);
	}
	@Override
	public HTMLTableElement element() {
		return element;
	}

	double totalHeight() {
		double headerHeigth = header.element().offsetHeight;
		double bodyHeight = body.element().offsetHeight;
		double rowHeight = bodyHeight / body.bufferSize();
		return headerHeigth + rowHeight * data.length * header.rowCount();
	}
	boolean viewport(Viewport.ViewportParam param) {
		double bodyHeight = body.element().offsetHeight;
		// Jump
		if(Math.abs(param.getScrollTop() - param.getPrevScrollTop()) > bodyHeight / 2) {
			int page = (int) Math.floor(param.getScrollTop() / bodyHeight);
			body.values(data).set(page * body.bufferSize());
			top = param.getScrollTop() - 1000;
			element.style.setProperty("top", top + "px");
			return true;
		} else {
			// Step
			double remainsBottom = top + body.element().offsetHeight - param.getScrollTop() - param.getViewportHeight();
			double remainsTop = param.getScrollTop() - top;
			DomGlobal.console.log("Remains Bottom:" + remainsBottom);
			DomGlobal.console.log("Remains Top:" + remainsTop);
			if (remainsBottom < 1000) {
				for(int i = 0; i < 5; ++i) {
					double delta = body.increase();
					top += delta;
				}
				element.style.setProperty("top", top + "px");
				return true;
			} else if (remainsTop < 1000) {
				for(int i = 0; i < 5; ++i) {
					double delta = body.decrease();
					top -= Math.min(delta, top);
				}
				element.style.setProperty("top", top + "px");
				return true;
			}
		}
		return false;
	}
}
