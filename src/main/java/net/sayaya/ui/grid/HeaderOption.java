package net.sayaya.ui.grid;

import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.util.LinkedList;
import java.util.List;

@JsType
@Getter
@Builder
@SuppressWarnings("unusable-by-js")
public class HeaderOption {
	@JsProperty
	private Double height;
	@JsProperty
	@Getter(AccessLevel.NONE)
	private String align;
	@JsProperty
	@Getter(AccessLevel.NONE)
	private String valign;
	@JsProperty
	private HeaderColumn[] columns;
	public static class HeaderOptionBuilder {
		private final List<HeaderColumn> _columns = new LinkedList<>();
		public HeaderOptionBuilder height(int height) {
			this.height = height + 0.0;
			return this;
		}
		public HeaderOptionBuilder alignHorizontal(@NotNull AlignHorizontal align) {
			this.align = align.name();
			return this;
		}
		public HeaderOptionBuilder alignVertical(@NotNull AlignVertical align) {
			this.valign = align.name();
			return this;
		}
		public HeaderOptionBuilder column(HeaderColumn column) {
			_columns.add(column);
			this.columns = _columns.toArray(new HeaderColumn[0]);
			return this;
		}
	}
	@JsMethod
	public AlignHorizontal alignHorizontal() {
		if(align == null) return null;
		else return AlignHorizontal.valueOf(align);
	}
	@JsMethod
	public AlignVertical alignVertical() {
		if(valign == null) return null;
		else return AlignVertical.valueOf(align);
	}
}
