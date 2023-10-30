package net.sayaya.ui.elements;

import elemental2.dom.HTMLElement;
import elemental2.dom.HTMLImageElement;
import net.sayaya.ui.dom.MdChipElement;
import net.sayaya.ui.dom.MdChipElement.MdAssistChipElement;
import net.sayaya.ui.dom.MdChipElement.MdFilterChipElement;
import net.sayaya.ui.dom.MdChipElement.MdInputChipElement;
import net.sayaya.ui.dom.MdChipElement.MdSuggestionChipElement;
import net.sayaya.ui.dom.MdChipSetElement;
import org.jboss.elemento.*;

import static org.jboss.elemento.Elements.htmlContainer;

public class ChipsElementBuilder implements HasHTMLElement<MdChipSetElement, ChipsElementBuilder>, HasAriaLabel<MdChipSetElement, ChipsElementBuilder> {
    public static ChipsElementBuilder chips() {
        return new ChipsElementBuilder(htmlContainer("md-chip-set", MdChipSetElement.class));
    }
    protected final HTMLContainerBuilder<MdChipSetElement> that;
    private ChipsElementBuilder(HTMLContainerBuilder<MdChipSetElement> that) {
        this.that = that;
    }
    @Override
    public MdChipSetElement element() {
        return that.element();
    }
    public AssistChipElementBuilder assist() {
        return new AssistChipElementBuilder(this);
    }
    public FilterChipElementBuilder filter() {
        return new FilterChipElementBuilder(this);
    }
    public InputChipElementBuilder input() {
        return new InputChipElementBuilder(this);
    }
    public SuggestionChipElementBuilder suggestion() {
        return new SuggestionChipElementBuilder(this);
    }
    @Override
    public ChipsElementBuilder that() {
        return this;
    }


    interface ChipElementBuilder<E extends MdChipElement, SELF extends ChipsElementBuilder.ChipElementBuilder<E, SELF>> extends HasHTMLElement<E, SELF>, HasElement<E, SELF>, HasAriaLabel<E, SELF>, HasIcon<E, SELF> {
        default SELF label(String label) {
            element().label = label;
            return that();
        }
        default SELF icon(HTMLElementBuilder<HTMLImageElement> icon) {
            return icon(icon.element());
        }
        default SELF icon(HTMLImageElement icon) {
            this.add(icon);
            icon.setAttribute("slot", "icon");
            return that();
        }
        ChipsElementBuilder end();
        @Override E element();
    }
    public final static class AssistChipElementBuilder implements ChipElementBuilder<MdAssistChipElement, AssistChipElementBuilder>, Elevatable<MdAssistChipElement, AssistChipElementBuilder> {
        private final HTMLContainerBuilder<MdAssistChipElement> that;
        private final ChipsElementBuilder parent;
        private AssistChipElementBuilder(ChipsElementBuilder parent) {
    		that = htmlContainer("md-assist-chip", MdAssistChipElement.class);
            this.parent = parent;
            parent.that.add(this);
    	}
        @Override public ChipsElementBuilder end() {
            return parent;
        }
        @Override public MdAssistChipElement element() {
            return that.element();
        }
        @Override public AssistChipElementBuilder that() {
            return this;
        }
        @Override public AssistChipElementBuilder elevated() {
            element().elevated = true;
            return that();
        }
    }
    public final static class FilterChipElementBuilder implements ChipElementBuilder<MdFilterChipElement, FilterChipElementBuilder>, Elevatable<MdFilterChipElement, FilterChipElementBuilder> {
        private final HTMLContainerBuilder<MdFilterChipElement> that;
        private final ChipsElementBuilder parent;
        private FilterChipElementBuilder(ChipsElementBuilder parent) {
            that = htmlContainer("md-filter-chip", MdFilterChipElement.class);
            this.parent = parent;
            parent.that.add(this);
        }
        @Override public ChipsElementBuilder end() {
            return parent;
        }
        @Override public MdFilterChipElement element() {
            return that.element();
        }
        @Override public FilterChipElementBuilder that() {
            return this;
        }
        @Override public FilterChipElementBuilder elevated() {
            element().elevated = true;
            return that();
        }
    }
    public final static class InputChipElementBuilder implements ChipElementBuilder<MdInputChipElement, InputChipElementBuilder> {
        private final HTMLContainerBuilder<MdInputChipElement> that;
        private final ChipsElementBuilder parent;
        private InputChipElementBuilder(ChipsElementBuilder parent) {
            that = htmlContainer("md-input-chip", MdInputChipElement.class);
            this.parent = parent;
            parent.that.add(this);
        }
        public InputChipElementBuilder avatar() {
            element().avatar = true;
            return that();
        }
        @Override public ChipsElementBuilder end() {
            return parent;
        }
        @Override public MdInputChipElement element() {
            return that.element();
        }
        @Override public InputChipElementBuilder that() {
            return this;
        }
    }
    public final static class SuggestionChipElementBuilder implements ChipElementBuilder<MdSuggestionChipElement, SuggestionChipElementBuilder>, Elevatable<MdSuggestionChipElement, SuggestionChipElementBuilder> {
        private final HTMLContainerBuilder<MdSuggestionChipElement> that;
        private final ChipsElementBuilder parent;
        private SuggestionChipElementBuilder(ChipsElementBuilder parent) {
            that = htmlContainer("md-suggestion-chip", MdSuggestionChipElement.class);
            this.parent = parent;
            parent.that.add(this);
        }
        @Override public ChipsElementBuilder end() {
            return parent;
        }
        @Override public MdSuggestionChipElement element() {
            return that.element();
        }
        @Override public SuggestionChipElementBuilder that() {
            return this;
        }
        @Override public SuggestionChipElementBuilder elevated() {
            element().elevated = true;
            return that();
        }
    }

    public interface Elevatable<E extends HTMLElement, SELF extends Elevatable<E, SELF>> extends Container<E, SELF> {
        SELF elevated();
    }
}
