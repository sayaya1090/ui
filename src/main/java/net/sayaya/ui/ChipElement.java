package net.sayaya.ui;

import elemental2.dom.HTMLElement;
import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;
import jsinterop.base.Js;
import lombok.experimental.Delegate;
import net.sayaya.ui.chip.AssistChipElement;
import net.sayaya.ui.chip.FilterChipElement;
import org.jboss.elemento.HtmlContent;
import org.jboss.elemento.HtmlContentBuilder;
import org.jboss.elemento.IsElement;
import org.jboss.elemento.TypedBuilder;

import static org.jboss.elemento.Elements.htmlElement;

@JsType(isNative = true, namespace = JsPackage.GLOBAL)
public abstract class ChipElement extends HTMLElement {
    public String label;
    public String ariaLabel;
    public boolean disabled;
    public boolean elevated;
    public String href;

    @JsOverlay
    public static ChipElementBuilder chip() {return new ChipElementBuilder();}

    public static class ChipElementBuilder {
        public String label;
        public IsElement<HTMLElement> icon;
        protected ChipElementBuilder() {}
        public AssistChipElement.AssistChipElementBuilder assist() {
            var builder = new AssistChipElement.AssistChipElementBuilder();
            builder.clone(this);
            return builder;
        }
        public FilterChipElement.FilterChipElementBuilder filter() {
            var builder = new FilterChipElement.FilterChipElementBuilder();
            builder.clone(this);
            return builder;
        }
        protected final void clone(ChipElementBuilder parent) {
            this.label = parent.label;
            this.icon = parent.icon;
        }
        public ChipElementBuilder icon(IsElement<HTMLElement> icon) {
            icon.element().slot = "icon";
            this.icon = icon;
            return this;
        }
        protected <T extends ChipElement> void build(HtmlContent<T, HtmlContentBuilder<T>> builder) {
            if(label!=null) {
                builder.add(label);
                label = null;
            }
            if(icon!=null) {
                builder.add(icon);
                icon = null;
            }
        }
    }
    @JsType(isNative = true, namespace = JsPackage.GLOBAL)
    public static class ChipSetElement extends HTMLElement {
        public String type;

        @JsOverlay
        public static HtmlContentBuilder<ChipSetElement> chipset() {
            return htmlElement("md-chip-set", ChipSetElement.class);
        }

        public enum ChipSetType {
            ASSIST, SUGGESTION, FILTER, INPUT
        }

        public static class ChipSetElementBuilder implements HtmlContent<ChipSetElement, HtmlContentBuilder<ChipSetElement>> {
            @Delegate(excludes = { IsElement.class, TypedBuilder.class, HtmlContent.class })
            private final HtmlContentBuilder<HTMLElement> delegate;
            private final HtmlContentBuilder<ChipSetElement> that;
            private ChipSetElementBuilder() {
                delegate = htmlElement("md-chip-set", HTMLElement.class);
                that = new HtmlContentBuilder<>(element());
            }
            public ChipSetElement.ChipSetElementBuilder type(ChipSetType type) {
                if(type==null) type = ChipSetType.ASSIST;
                element().type = type.name().toLowerCase();
                return this;
            }
            @Override
            public ChipSetElement element() {
                return Js.uncheckedCast(delegate.element());
            }
            @Override
            public HtmlContentBuilder<ChipSetElement> that() {
                return that;
            }
        }
    }
}
