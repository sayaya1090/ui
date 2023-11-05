package net.sayaya.ui.elements;

import elemental2.dom.HTMLElement;
import net.sayaya.ui.dom.MdMenuElement;
import net.sayaya.ui.dom.MdMenuItemElement;
import net.sayaya.ui.dom.MdSubMenuElement;
import org.jboss.elemento.HTMLContainerBuilder;
import org.jboss.elemento.HasElement;
import org.jboss.elemento.HasHTMLElement;
import org.jboss.elemento.IsElement;

import static org.jboss.elemento.Elements.htmlContainer;

public abstract class MenuElementBuilder<E extends HTMLElement, SELF extends MenuElementBuilder<E, SELF>> implements HasElement<E, SELF>, HasHTMLElement<E, SELF>, HasMenuItemElementBuilder<E, SELF> {
    public static TopMenuElementBuilder menu() {
        return new TopMenuElementBuilder();
    }
    public SubMenuGroupBuilder<SELF> sub() {
        var subMenu = new SubMenuGroupBuilder<>(that());
        that().add(subMenu);
        return subMenu;
    }
    public static class TopMenuElementBuilder extends MenuElementBuilder<MdMenuElement, TopMenuElementBuilder> {
        private final HTMLContainerBuilder<MdMenuElement> that = htmlContainer("md-menu", MdMenuElement.class);
        public TopMenuElementBuilder anchor(String id) {
            that.element().anchor = id;
            return that();
        }
        public TopMenuElementBuilder anchorElement(IsElement<? extends HTMLElement> elem) {
            return anchorElement(elem.element());
        }
        public TopMenuElementBuilder anchorElement(IsElement<? extends HTMLElement> elem, String event) {
            return anchorElement(elem.element(), event);
        }
        public TopMenuElementBuilder anchorElement(HTMLElement anchor) {
            return anchorElement(anchor, "click");
        }
        public TopMenuElementBuilder anchorElement(HTMLElement anchor, String event) {
            that.element().anchorElement = anchor;
            anchor.addEventListener(event, evt->toggle());
            return that();
        }
        public TopMenuElementBuilder positioning(String position) {
            that.element().positioning = position;
            return that();
        }
        public void toggle() {
            element().open = !element().open;
        }
        public TopMenuElementBuilder overflow() {
            return overflow(true);
        }
        public TopMenuElementBuilder overflow(boolean hasOverflow) {
            that.element().hasOverflow = hasOverflow;
            return that();
        }
        @Override public MdMenuElement element() {return that.element();}
        @Override public TopMenuElementBuilder that() {return this;}
    }
    public static class SubMenuGroupBuilder<P extends MenuElementBuilder<?, P>> implements HasMenuItemElementBuilder<MdSubMenuElement, SubMenuGroupBuilder<P>> {
        private final HTMLContainerBuilder<MdSubMenuElement> that = htmlContainer("md-sub-menu", MdSubMenuElement.class);
        private final P parent;
        private SubMenuGroupBuilder(P parent) {
            this.parent = parent;
        }
        public MenuItemElementBuilder<SubMenuGroupBuilder<P>> item() {
            var item = new MenuItemElementBuilder<>(that());
            item.element().setAttribute("slot", "item");
            that().add(item);
            return item;
        }
        public SubMenuElementBuilder<P> menu() {
            var subMenu = new SubMenuElementBuilder<>(this);
            subMenu.element().setAttribute("slot", "menu");
            that().add(subMenu);
            return subMenu;
        }
        public SubMenuGroupBuilder<P> menuCorner(String menuCorner) {
            element().setAttribute("menu-corner", menuCorner);
            return that();
        }
        public SubMenuGroupBuilder<P> anchorCorner(String anchorCorner) {
            element().setAttribute("anchor-corner", anchorCorner);
            return that();
        }
        public P end() { return parent; }
        @Override public MdSubMenuElement element() {return that.element();}
        @Override public SubMenuGroupBuilder<P> that() {return this;}
    }
    public static class SubMenuElementBuilder<P extends MenuElementBuilder<?, P>> extends MenuElementBuilder<MdMenuElement, SubMenuElementBuilder<P>> {
        private final HTMLContainerBuilder<MdMenuElement> that = htmlContainer("md-menu", MdMenuElement.class);
        private final SubMenuGroupBuilder<P> parent;
        private SubMenuElementBuilder(SubMenuGroupBuilder<P> parent) {
            this.parent = parent;
        }
        public SubMenuGroupBuilder<P> end() { return parent; }
        @Override public MdMenuElement element() {return that.element();}
        @Override public SubMenuElementBuilder<P> that() {return this;}
    }
    public static class MenuItemElementBuilder<P extends HasMenuItemElementBuilder<?, P>> implements HasHeadlineSlot<MdMenuItemElement, MenuItemElementBuilder<P>>,
            HasStartSlot<MdMenuItemElement, MenuItemElementBuilder<P>>, HasEndSlot<MdMenuItemElement, MenuItemElementBuilder<P>> {
        private final HTMLContainerBuilder<MdMenuItemElement> that = htmlContainer("md-menu-item", MdMenuItemElement.class);
        private final P parent;
        MenuItemElementBuilder(P parent) {
            this.parent = parent;
        }
        public P end() { return parent; }
        @Override public MdMenuItemElement element() {return that.element();}
        @Override public MenuItemElementBuilder<P> that() {return this;}
    }
}
