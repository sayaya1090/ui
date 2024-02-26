package net.sayaya.ui.elements;

import com.google.gwt.core.client.Scheduler;
import elemental2.dom.HTMLElement;
import net.sayaya.ui.dom.MdTabElement;
import net.sayaya.ui.dom.MdTabElement.MdPrimaryTabElement;
import net.sayaya.ui.dom.MdTabElement.MdSecondaryTabElement;
import net.sayaya.ui.dom.MdTabsElement;
import net.sayaya.ui.elements.interfaces.HasAriaLabel;
import net.sayaya.ui.elements.interfaces.HasIconSlot;
import org.jboss.elemento.HTMLContainerBuilder;
import org.jboss.elemento.HasElement;
import org.jboss.elemento.HasHTMLElement;

import static org.jboss.elemento.Elements.htmlContainer;

public abstract class TabsElementBuilder<SELF extends TabsElementBuilder<SELF>> implements HasHTMLElement<MdTabsElement, SELF>, HasAriaLabel<MdTabsElement, SELF> {
    public static TabsPrepareElementBuilder tabs() {
        return new TabsPrepareElementBuilder(htmlContainer("md-tabs", MdTabsElement.class));
    }
    protected final HTMLContainerBuilder<MdTabsElement> that;
    private TabsElementBuilder(HTMLContainerBuilder<MdTabsElement> that) {
        this.that = that;
    }
    @Override
    public MdTabsElement element() {
        return that.element();
    }
    public SELF autoActivate() {
        element().autoActivate = true;
        return that();
    }

    public final static class TabsPrepareElementBuilder extends TabsElementBuilder<TabsPrepareElementBuilder> {
        private TabsPrepareElementBuilder(HTMLContainerBuilder<MdTabsElement> that) {
            super(that);
        }
        public TabsPrimaryElementBuilder primary() {
            return new TabsPrimaryElementBuilder(that);
        }
        public TabsSecondaryElementBuilder secondary() {
            return new TabsSecondaryElementBuilder(that);
        }
        @Override
        public TabsPrepareElementBuilder that() {
            return this;
        }
    }
    public final static class TabsPrimaryElementBuilder extends TabsElementBuilder<TabsPrimaryElementBuilder> {
        private TabsPrimaryElementBuilder(HTMLContainerBuilder<MdTabsElement> that) {
            super(that);
        }
        public PrimaryTabElementBuilder tab() {
            return new PrimaryTabElementBuilder(this);
        }
        @Override
        public TabsPrimaryElementBuilder that() {
            return this;
        }
    }
    public final static class TabsSecondaryElementBuilder extends TabsElementBuilder<TabsSecondaryElementBuilder> {
        private TabsSecondaryElementBuilder(HTMLContainerBuilder<MdTabsElement> that) {
            super(that);
        }
        public SecondaryTabElementBuilder tab() {
            return new SecondaryTabElementBuilder(this);
        }
        @Override
        public TabsSecondaryElementBuilder that() {
            return this;
        }
    }

    private interface TabElementBuilder<E extends MdTabElement, P extends TabsElementBuilder<?>, SELF extends TabElementBuilder<E, P, SELF>> extends HasHTMLElement<E, SELF>, HasElement<E, SELF>, HasAriaLabel<E, SELF>, HasIconSlot<E, SELF> {
        P end();
        default SELF active(boolean active) {
            element().active = active;
            return that();
        }
        default SELF panel(HTMLContainerBuilder<?> container) {
            return panel(container.element());
        }
        default SELF panel(HTMLElement panel) {
            if(element().id == null || element().id.isEmpty()) element().id = "md-tab-" + System.currentTimeMillis();
            if(panel.id == null || panel.id.isEmpty()) panel.id = "md-tab-panel-" + System.currentTimeMillis();
            element().setAttribute("aria-controls", panel.id);
            panel.setAttribute("role", "tabpanel");
            panel.setAttribute("aria-labelledby", element().id);
            end().element().addEventListener("change", evt->{
                Scheduler.get().scheduleDeferred(()->{
                    if(element().tabIndex<0) panel.setAttribute("hidden", true);
                    else panel.removeAttribute("hidden");
                });
            });
            if(element().tabIndex<0) panel.setAttribute("hidden", true);
            else panel.removeAttribute("hidden");
            return that();
        }
        @Override E element();
    }
    public final static class PrimaryTabElementBuilder implements TabElementBuilder<MdPrimaryTabElement, TabsPrimaryElementBuilder, PrimaryTabElementBuilder> {
        private final HTMLContainerBuilder<MdPrimaryTabElement> that;
        private final TabsPrimaryElementBuilder parent;
        private PrimaryTabElementBuilder(TabsPrimaryElementBuilder parent) {
    		that = htmlContainer("md-primary-tab", MdPrimaryTabElement.class);
            this.parent = parent;
            parent.that.add(this);
    	}
        public PrimaryTabElementBuilder inlineIcon(boolean inline) {
            element().inlineIcon = inline;
            return this;
        }
        @Override public TabsPrimaryElementBuilder end() {
            return parent;
        }
        @Override public MdPrimaryTabElement element() {
            return that.element();
        }
        @Override public PrimaryTabElementBuilder that() {
            return this;
        }
    }
    public final static class SecondaryTabElementBuilder implements TabElementBuilder<MdSecondaryTabElement, TabsSecondaryElementBuilder, SecondaryTabElementBuilder> {
        private final HTMLContainerBuilder<MdSecondaryTabElement> that;
        private final TabsSecondaryElementBuilder parent;
        private SecondaryTabElementBuilder(TabsSecondaryElementBuilder parent) {
            that = htmlContainer("md-secondary-tab", MdSecondaryTabElement.class);
            this.parent = parent;
            parent.that.add(this);
        }
        @Override public TabsSecondaryElementBuilder end() {
            return parent;
        }
        @Override public MdSecondaryTabElement element() {
            return that.element();
        }
        @Override public SecondaryTabElementBuilder that() {
            return this;
        }
    }
}
