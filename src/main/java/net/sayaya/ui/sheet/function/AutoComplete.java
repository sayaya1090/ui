package net.sayaya.ui.sheet.function;

import jsinterop.annotations.JsFunction;

@JsFunction
public interface AutoComplete {
	void exec(String query, Consumer<String[]> process);
}
