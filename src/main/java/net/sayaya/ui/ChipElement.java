package net.sayaya.ui;

import elemental2.dom.HTMLElement;
import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;
import jsinterop.base.Js;
import lombok.experimental.Delegate;
import net.sayaya.ui.chip.AssistChipElement;
import net.sayaya.ui.chip.FilterChipElement;
import net.sayaya.ui.chip.InputChipElement;
import net.sayaya.ui.chip.SuggestionChipElement;
import org.jboss.elemento.HtmlContent;
import org.jboss.elemento.HtmlContentBuilder;
import org.jboss.elemento.IsElement;
import org.jboss.elemento.TypedBuilder;

import static org.jboss.elemento.Elements.htmlElement;

@JsType(isNative = true, namespace = JsPackage.GLOBAL)
public abstract class ChipElement extends HTMLElement {
    @JsOverlay
    public static ChipElementFactory chip() {
        return new ChipElementFactory();
    }

    public static class ChipElementFactory {
        public AssistChipElement.AssistChipElementBuilder assist() {
            return new AssistChipElement.AssistChipElementBuilder();
        }
        public FilterChipElement.FilterChipElementBuilder filter() {
            return new FilterChipElement.FilterChipElementBuilder();
        }
        public InputChipElement.InputChipElementBuilder input() {
            return new InputChipElement.InputChipElementBuilder();
        }
        public SuggestionChipElement.SuggestionChipElementBuilder suggestion() {
            return new SuggestionChipElement.SuggestionChipElementBuilder();
        }
    }
    public static abstract class ChipElementBuilder<T extends ChipElement, SELF extends ChipElementBuilder<T, SELF>> implements HtmlContent<T, HtmlContentBuilder<T>> {
        protected ChipElementBuilder() {}
        public SELF icon(IsElement<HTMLElement> icon) {
            icon.element().slot = "icon";
            add(icon);
            return _this();
        }
        private SELF _this() {
            return (SELF) this;
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
