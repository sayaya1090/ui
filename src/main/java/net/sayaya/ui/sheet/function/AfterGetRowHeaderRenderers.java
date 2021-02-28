package net.sayaya.ui.sheet.function;

import elemental2.dom.HTMLTableCellElement;
import jsinterop.annotations.JsFunction;
import jsinterop.annotations.JsOverlay;

import java.util.Objects;

@JsFunction
public interface AfterGetRowHeaderRenderers {
	void apply(BiConsumer<Integer, HTMLTableCellElement>[] renderers);

	@FunctionalInterface
	@JsFunction
	interface BiConsumer<T, U> {
		void apply(int row, HTMLTableCellElement th);
		@JsOverlay
		default BiConsumer<T, U> andThen(BiConsumer<? super T, ? super U> after) {
			Objects.requireNonNull(after);
			return (l, r) -> {
				apply(l, r);
				after.apply(l, r);
			};
		}
	}
}
