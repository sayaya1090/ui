package net.sayaya.ui.table;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import elemental2.dom.HTMLTableElement;
import net.sayaya.ui.IsHTMLElement;

import java.util.Arrays;

import static org.jboss.gwt.elemento.core.Elements.*;

public class Table<V> implements IsHTMLElement<HTMLTableElement, Table<V>> {
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
	private final HTMLTableElement element = table().css(GSS.table()).element();
	private boolean selectionEnabled;
	private TableHeader header;
	private TableBody body;
	private Data[] data;
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
	public Table<V> update(V[] values) {
		data = Arrays.stream(values).map(mapper::map).toArray(Data[]::new);
		body.update(data);
		return this;
	}
	@Override
	public HTMLTableElement element() {
		return element;
	}
}
