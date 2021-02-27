package net.sayaya.ui.sheet.function;

import elemental2.dom.HTMLTableCellElement;
import jsinterop.annotations.JsFunction;

@JsFunction
public interface AfterGetRowHeaderRenderers {
	void apply(BiConsumer<Integer, HTMLTableCellElement>[] renderers);
}
