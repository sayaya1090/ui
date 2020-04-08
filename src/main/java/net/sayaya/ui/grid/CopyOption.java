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
public class CopyOption {
	@JsProperty
	private boolean useFormattedValue;
	@JsProperty
	private boolean useListItemText;
}
