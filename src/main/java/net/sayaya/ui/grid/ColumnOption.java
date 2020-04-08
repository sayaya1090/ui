package net.sayaya.ui.grid;

import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@JsType
@ToString
@Getter
@Builder
@SuppressWarnings("unusable-by-js")
public class ColumnOption {
	@JsProperty
	private Integer minWidth;
	@JsProperty
	private boolean resizable;
	@JsProperty
	private Integer frozenCount;
	@JsProperty
	private Integer frozenBorderWidth;
}
