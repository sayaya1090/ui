package net.sayaya.ui.table;

import java.util.HashMap;
import java.util.Objects;

public final class Data {
	private final Address reference = new Address();
	private final HashMap<String, Object> map = new HashMap<>();
	public <T> Data put(String key, T value) {
		map.put(Objects.requireNonNull(key), value);
		return this;
	}
	public <T> T get(String key, Class<T> clazz) {
		return (T) map.get(key);
	}
	public String toString() {
		return map.toString();
	}
}
