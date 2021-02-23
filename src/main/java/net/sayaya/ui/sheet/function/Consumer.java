package net.sayaya.ui.sheet.function;

import jsinterop.annotations.JsFunction;
import jsinterop.annotations.JsOverlay;

import java.util.Objects;

@FunctionalInterface
@JsFunction
public interface Consumer<T> {
	void accept(T t);
	@JsOverlay
	default Consumer<T> andThen(Consumer<? super T> after) {
		Objects.requireNonNull(after);
		return (T t) -> { accept(t); after.accept(t); };
	}
}
