package net.sayaya.ui.dom;

import elemental2.dom.HTMLElement;
import jsinterop.annotations.JsType;

@JsType(isNative = true)
public class MdChipElement extends HTMLElement {
    public boolean disabled;
    public boolean alwaysFocusable;
    public String label;

    @JsType(isNative = true, namespace = "mdc", name="MdAssistChip")
    public static class MdAssistChipElement extends MdChipElement {
        public boolean elevated;
        public String href;
        public String target;
    }
    @JsType(isNative = true, namespace = "mdc", name="MdFilterChip")
    public static class MdFilterChipElement extends MdChipElement {
        public boolean elevated;
        public boolean removable;
        public boolean selected;
        public Object handleTrailingActionFocus;
        public String ariaLabelRemove;
    }
    @JsType(isNative = true, namespace = "mdc", name="MdInputChip")
    public static class MdInputChipElement extends MdChipElement {
        public boolean avatar;
        public String href;
        public String target;
        public boolean removeOnly;
        public boolean selected;
        public Object handleTrailingActionFocus;
        public String ariaLabelRemove;
    }
    @JsType(isNative = true, namespace = "mdc", name="MdSuggestionChip")
    public static class MdSuggestionChipElement extends MdChipElement {
        public boolean elevated;
        public String href;
        public String target;
    }
}