package net.sayaya.ui;

import elemental2.dom.Element;
import elemental2.dom.HTMLDivElement;
import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.jboss.elemento.HtmlContentBuilder;

import static org.jboss.elemento.Elements.div;
import static org.jboss.elemento.Elements.span;
import static org.jboss.elemento.EventType.bind;

public class ProgressBar extends HTMLElementBuilder<HTMLDivElement, ProgressBar> {
	public static ProgressBar progressBar() {
		ProgressBar elem = new ProgressBar(div());
		elem.css("mdc-linear-progress")
			.attr("role", "progressbar")
			.attr("aria-valuemin", "0")
			.attr("aria-valuemax", "1")
			.attr("aria-valuenow", "0");
		elem._mdc=inject(elem.element());
		// bind(elem, "DOMNodeInserted", evt->elem._mdc=inject(elem.element()));
		return elem;
	}
	private native static MdcProgressBar inject(Element elem) /*-{
        return $wnd.mdc.linearProgress.MDCLinearProgress.attachTo(elem);
    }-*/;
	private final HtmlContentBuilder<HTMLDivElement> buffer = div().css("mdc-linear-progress__buffer")
																   .add(div().css("mdc-linear-progress__buffer-bar"))
																   .add(div().css("mdc-linear-progress__buffer-dots"));
	private final HtmlContentBuilder<HTMLDivElement> primary = div().css("mdc-linear-progress__bar", "mdc-linear-progress__primary-bar").add(span().css("mdc-linear-progress__bar-inner"));
	private final HtmlContentBuilder<HTMLDivElement> secondary = div().css("mdc-linear-progress__bar", "mdc-linear-progress__secondary-bar").add(span().css("mdc-linear-progress__bar-inner"));
	private final HtmlContentBuilder<HTMLDivElement> _this;
	private MdcProgressBar _mdc;
	private ProgressBar(HtmlContentBuilder<HTMLDivElement> e) {
		super(e);
		_this = e;
		layout();
	}
	private void layout() {
		_this.add(buffer).add(primary).add(secondary);
	}
	public ProgressBar open() {
		_mdc.open();
		return this;
	}
	public ProgressBar close() {
		_mdc.close();
		return this;
	}
	public ProgressBar determinate(boolean isDetermined) {
		_mdc.determinate = isDetermined;
		return this;
	}
	public ProgressBar progress(double progress) {
		assert progress >= 0 && progress <= 1;
		_mdc.progress = progress;
		return this;
	}
	public ProgressBar buffer(double buffer) {
		assert buffer >= 0 && buffer <= 1;
		_mdc.buffer = buffer;
		return this;
	}
	public ProgressBar reverse(boolean isReversed) {
		_mdc.reverse = isReversed;
		return this;
	}
	@Override
	public ProgressBar that() {
		return this;
	}

	@JsType(isNative = true, namespace= JsPackage.GLOBAL, name="Object")
	@Setter(onMethod_ = {@JsOverlay})
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
	@Setter(onMethod_ = {@JsOverlay})
	@Accessors(fluent=true)
	private static final class MdcProgressBarFoundation {
		public native void setDeterminate(boolean isDetermined);
		public native void setProgress(double progress);
		public native void setBuffer(double buffer);
		public native void setReverse(boolean isReversed);
	}
}
