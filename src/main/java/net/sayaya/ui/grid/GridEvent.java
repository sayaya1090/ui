package net.sayaya.ui.grid;

import jsinterop.annotations.*;
import lombok.Getter;

@JsType(isNative = true, namespace= JsPackage.GLOBAL)
@Getter(onMethod_= {@JsOverlay})
public final class GridEvent<T> {
	@JsProperty
	private boolean stopped;
	@JsProperty
	private Double rowKey;
	@JsProperty
	private String columnName;
	@JsProperty
	private T value;
	@JsProperty
	private T nextValue;
	@JsOverlay
	public Integer rowKey() {
		if(rowKey!=null) return rowKey.intValue();
		return null;
	}
	@JsMethod
	public native void stop();
	@JsFunction
	public interface EventListener<T> {
		void handleEvent(GridEvent<T> evt);
	}
}
