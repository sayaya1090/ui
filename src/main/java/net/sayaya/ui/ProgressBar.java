package net.sayaya.ui;

import elemental2.dom.DomGlobal;
import elemental2.dom.Element;
import elemental2.dom.HTMLButtonElement;
import elemental2.dom.HTMLDivElement;
import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;
import lombok.Builder;
import lombok.Setter;
import lombok.experimental.Accessors;

import static org.jboss.gwt.elemento.core.Elements.*;

public class ProgressBar implements IsHTMLElement<HTMLDivElement, ProgressBar> {
	private final HTMLDivElement bufferingDots = div().css("mdc-linear-progress__buffering-dots").element();
	private final HTMLDivElement buffer = div().css("mdc-linear-progress__buffer").element();
	private final HTMLDivElement primary = div().css("mdc-linear-progress__bar", "mdc-linear-progress__primary-bar")
												.add(span().css("mdc-linear-progress__bar-inner")).element();
	private final HTMLDivElement secondary = div().css("mdc-linear-progress__bar", "mdc-linear-progress__secondary-bar")
			.add(span().css("mdc-linear-progress__bar-inner")).element();
	private final HTMLDivElement _this = div().css("mdc-linear-progress")
			.attr("role", "progressbar")
			.attr("aria-valuemin", "0")
			.attr("aria-valuemax", "1")
			.add(bufferingDots)
			.add(buffer)
			.add(primary).add(secondary).element();
	private final MdcProgressBar instance;
	ProgressBar() {
		instance = inject(element());
	}
	private native MdcProgressBar inject(Element elem) /*-{
		return $wnd.mdc.linearProgress.MDCLinearProgress.attachTo(elem);
	}-*/;
	@Override
	public HTMLDivElement element() {
		return _this;
	}
	public ProgressBar open() {
		instance.open();
		return this;
	}
	public ProgressBar close() {
		instance.close();
		return this;
	}
	public ProgressBar determinate(boolean isDetermined) {
		instance.determinate = isDetermined;
		return this;
	}
	public ProgressBar progress(double progress) {
		assert progress >= 0 && progress <= 1;
		instance.progress = progress;
		return this;
	}
	public ProgressBar buffer(double buffer) {
		assert buffer >= 0 && buffer <= 1;
		instance.buffer = buffer;
		return this;
	}
	public ProgressBar reverse(boolean isReversed) {
		instance.reverse = isReversed;
		return this;
	}
	public static ProgressBarBuilder progressBar() {
		return new ProgressBarBuilder();
	}
	public static class ProgressBarBuilder {
		private ProgressBarBuilder(){}
		public ProgressBar element() {
			return new ProgressBar();
		}
	}
	@JsType(isNative = true, namespace= JsPackage.GLOBAL, name="Object")
	@Setter(onMethod_= {@JsOverlay})
	@Accessors(fluent=true)
	private static final class MdcProgressBar {
		@JsProperty
		private boolean determinate;
		@JsProperty
		private double progress;
		@JsProperty
		private double buffer;
		@JsProperty
		private boolean reverse;
		public native void open();
		public native void close();
		// public native MdcProgressBarFoundation getDefaultFoundation();
	}
	@JsType(isNative = true, namespace= JsPackage.GLOBAL, name="Object")
	@Setter(onMethod_= {@JsOverlay})
	@Accessors(fluent=true)
	private static final class MdcProgressBarFoundation {
		public native void setDeterminate(boolean isDetermined);
		public native void setProgress(double progress);
		public native void setBuffer(double buffer);
		public native void setReverse(boolean isReversed);
	}
}