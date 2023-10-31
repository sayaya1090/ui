package net.sayaya.ui.elements;

import elemental2.dom.HTMLElement;
import net.sayaya.ui.dom.MdListElement;
import net.sayaya.ui.dom.MdListItemElement;
import org.jboss.elemento.*;

import static org.jboss.elemento.Elements.div;
import static org.jboss.elemento.Elements.htmlContainer;

public class ListElementBuilder implements HasHTMLElement<MdListElement, ListElementBuilder>, Container<MdListElement, ListElementBuilder> {
    public static ListElementBuilder list() {
        return new ListElementBuilder(htmlContainer("md-list", MdListElement.class));
    }
    protected final HTMLContainerBuilder<MdListElement> that;
    private ListElementBuilder(HTMLContainerBuilder<MdListElement> that) {
        this.that = that;
    }
    @Override
    public MdListElement element() {
        return that.element();
    }
    public ListItemElementBuilder item() {
        return new ListItemElementBuilder(this);
    }
    @Override
    public ListElementBuilder that() {
        return this;
    }
    public ListElementBuilder divider() {
        this.add(DividerElementBuilder.divider());
        return this;
    }
    public ListElementBuilder dividerInset() {
        this.add(DividerElementBuilder.dividerInset());
        return this;
    }
    public ListElementBuilder dividerInsetStart() {
        this.add(DividerElementBuilder.dividerInsetStart());
        return this;
    }

    public final static class ListItemElementBuilder implements HasHTMLElement<MdListItemElement, ListItemElementBuilder>, HasElement<MdListItemElement, ListItemElementBuilder>, HasIcon<MdListItemElement, ListItemElementBuilder> {
        private final HTMLContainerBuilder<MdListItemElement> that;
        private final ListElementBuilder parent;
        private ListItemElementBuilder(ListElementBuilder parent) {
    		that = htmlContainer("md-list-item", MdListItemElement.class);
            this.parent = parent;
            parent.that.add(this);
    	}
        public ListElementBuilder start(String start) {
            return start(div().add(start));
        }
        public ListElementBuilder start(IsElement<? extends HTMLElement> element) {
            return start(element.element());
        }
        public ListElementBuilder start(HTMLElement element) {
            element.setAttribute("slot", "start");
            add(element);
            return end();
        }
        public ListElementBuilder end() {
            return parent;
        }
        public ListElementBuilder end(String end) {
            return end(div().add(end));
        }
        public ListElementBuilder end(IsElement<? extends HTMLElement> element) {
            return end(element.element());
        }
        public ListElementBuilder end(HTMLElement element) {
            element.setAttribute("slot", "end");
            add(element);
            return end();
        }
        public ListItemElementBuilder headline(String headline) {
            return headline(div().add(headline));
        }
        public ListItemElementBuilder headline(IsElement<? extends HTMLElement> element) {
            return headline(element.element());
        }
        public ListItemElementBuilder headline(HTMLElement element) {
            element.setAttribute("slot", "headline");
            add(element);
            return that();
        }
        public ListItemElementBuilder supportingText(String supportingText) {
            return supportingText(div().add(supportingText));
        }
        public ListItemElementBuilder supportingText(IsElement<? extends HTMLElement> element) {
            return supportingText(element.element());
        }
        public ListItemElementBuilder supportingText(HTMLElement element) {
            element.setAttribute("slot", "supporting-text");
            add(element);
            return that();
        }
        @Override public MdListItemElement element() {
            return that.element();
        }
        @Override public ListItemElementBuilder that() {
            return this;
        }
    }
}
