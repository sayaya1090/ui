package net.sayaya.ui;

import elemental2.dom.Element;
import elemental2.dom.HTMLDivElement;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;
import org.jboss.elemento.HtmlContentBuilder;

import static org.jboss.elemento.Elements.div;
import static org.jboss.elemento.Elements.span;

public class ProgressBarElement extends HTMLElementBuilder<HTMLDivElement, ProgressBarElement> {
	public static ProgressBarElement progressBar() {
		return new ProgressBarElement(div());
	}
	private MDCProgressBar _mdc;
	private ProgressBarElement(HtmlContentBuilder<HTMLDivElement> e) {
		super(e);
		e.css("mdc-linear-progress")
		.aria("valuemin", "0").aria("valuemax", "1").aria("valuenow", "0")
		.attr("role", "progressbar")
		.add(div().css("mdc-linear-progress__buffer")
				.add(div().css("mdc-linear-progress__buffer-bar"))
				.add(div().css("mdc-linear-progress__buffer-dots")))
		.add(div().css("mdc-linear-progress__bar", "mdc-linear-progress__primary-bar")
				.add(span().css("mdc-linear-progress__bar-inner")))
		.add(div().css("mdc-linear-progress__bar", "mdc-linear-progress__secondary-bar")
				.add(span().css("mdc-linear-progress__bar-inner")));
		_mdc = new MDCProgressBar(element());
	}
	public ProgressBarElement open() {
		_mdc.open();
		return this;
	}
	public ProgressBarElement close() {
		_mdc.close();
		return this;
	}
	public ProgressBarElement determinate(boolean isDetermined) {
		_mdc.determinate = isDetermined;
		return this;
	}
	public ProgressBarElement progress(double progress) {
		assert progress >= 0 && progress <= 1;
		_mdc.progress = progress;
		return this;
	}
	public ProgressBarElement buffer(double buffer) {
		assert buffer >= 0 && buffer <= 1;
		_mdc.buffer = buffer;
		return this;
	}
	public ProgressBarElement reverse(boolean isReversed) {
		_mdc.reverse = isReversed;
		return this;
	}
	@Override
	public ProgressBarElement that() {
		return this;
	}

	@JsType(isNative = true, namespace="mdc.linearProgress", name="MDCLinearProgress")
	private static final class MDCProgressBar {
		@JsProperty private boolean determinate;
		@JsProperty private double progress;
		@JsProperty private double buffer;
		@JsProperty private boolean reverse;
		public MDCProgressBar(Element element){}
		public native void open();
		public native void close();
	}
}
