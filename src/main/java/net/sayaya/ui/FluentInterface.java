package net.sayaya.ui;

public interface FluentInterface<T extends FluentInterface<T>> {
	default T self() {
		return (T) this;
	}
}
