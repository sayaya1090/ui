package net.sayaya.ui.handler;

public interface FluentInterface<T extends FluentInterface<T>> {
	default T self() {
		return (T) this;
	}
}
