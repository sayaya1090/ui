package net.sayaya.ui.sheet.function;

import jsinterop.annotations.JsFunction;
import jsinterop.annotations.JsOverlay;

import java.util.Objects;

@FunctionalInterface
@JsFunction
public interface BiConsumer<T, U> {
	void accept(T t, U u);
	@JsOverlay
	default BiConsumer<T, U> andThen(BiConsumer<? super T, ? super U> after) {
		Objects.requireNonNull(after);
		return (l, r) -> {
			accept(l, r);
			after.accept(l, r);
		};
	}
}
