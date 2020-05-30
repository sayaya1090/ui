package net.sayaya.ui;

import elemental2.dom.Element;
import elemental2.dom.HTMLLIElement;
import elemental2.dom.HTMLUListElement;
import org.jboss.elemento.HtmlContentBuilder;

import java.util.LinkedList;

import static org.jboss.elemento.Elements.*;

public class List implements IsHTMLElement<HTMLUListElement, List> {
	private final HtmlContentBuilder<HTMLUListElement> _this = ul().css("mdc-list");
	private List(){
	}
	public static ListBuilderSingleLine singleLine() {
		return new ListBuilderSingleLine(new AbstractListBuilder());
	}
	public static ListBuilderDoubleLine doubleLine() {
		return new ListBuilderDoubleLine(new AbstractListBuilder());
	}

	native static void inject(Element elem) /*-{
        $wnd.mdc.list.MDCList.attachTo(elem);
    }-*/;
	final void inject() {
		inject(_this.element());
	}
	public List add(AbstractListItem<?> item) {
		_this.add(item);
		return this;
	}
	@Override
	public HTMLUListElement element() {
		return _this.element();
	}
	public static class AbstractListBuilder {
		private final java.util.List<AbstractListItem<?>> children = new LinkedList<>();
		private AbstractListBuilder(){}
		public AbstractListBuilder add(AbstractListItem<?> item) {
			children.add(item);
			return this;
		}
	}
	public static class ListBuilderSingleLine {
		private final java.util.List<AbstractListItem<?>> children;
		public ListBuilderSingleLine(AbstractListBuilder context) {
			this.children = context.children;
		}
		public ListBuilderSingleLine add(AbstractListItem<?> item) {
			children.add(item);
			return this;
		}
		public List build() {
			List elem = new List();
			for(AbstractListItem<?> child: children) elem.add(child);
			elem.inject();
			return elem;
		}
	}
	public static class ListBuilderDoubleLine {
		private final java.util.List<AbstractListItem<?>> children;
		public ListBuilderDoubleLine(AbstractListBuilder context) {
			this.children = context.children;
		}
		public ListBuilderDoubleLine add(AbstractListItem<?> item) {
			children.add(item);
			return this;
		}
		public List build() {
			List elem = new List();
			elem._this.css("mdc-list--two-line");
			for(AbstractListItem<?> child: children) elem.add(child);
			elem.inject();
			return elem;
		}
	}
	public interface AbstractListItem<L extends AbstractListItem<L>> extends IsHTMLElement<HTMLLIElement, L> {}
	public static class Divider implements AbstractListItem<Divider> {
		private final HTMLLIElement _this = li().css("mdc-list-divider").attr("role", "separator").element();
		private Divider(){}
		public static Divider divider() {
			return new Divider();
		}
		@Override
		public HTMLLIElement element() {
			return _this;
		}
	}
	public static class ListItem implements AbstractListItem<ListItem> {
		private final HtmlContentBuilder<HTMLLIElement> _this = li().css("mdc-list-item");
		private ListItem(){}
		public static ListItemBuilder item() {
			return new ListItemBuilder();
		}
		@Override
		public HTMLLIElement element() {
			return _this.element();
		}
		public static class ListItemBuilder {
			private String[] label = new String[2];
			private boolean selectable = false;
			private ListItemBuilder(){}
			public ListItemBuilder label(String primary, String secondary) {
				label[0] = primary;
				label[1] = secondary;
				return this;
			}
			public ListItemBuilder label(String text) {
				label[0] = text;
				label[1] = null;
				return this;
			}
			public ListItemBuilder selectable() {
				this.selectable = true;
				return this;
			}
			public ListItem build() {
				ListItem elem = new ListItem();
				if((label[0] != null) && (label[1] == null)) elem._this.add(span().css("mdc-list-item__text").add(label[0]));
				else if((label[0] != null) && (label[1] != null)) elem._this.add(span().css("mdc-list-item__text")
						.add(span().css("mdc-list-item__primary-text").add(label[0]))
						.add(span().css("mdc-list-item__secondary-text").add(label[1])));
				if(selectable) elem._this.attr("role",  "option");
				return elem;
			}
		}
	}
}
