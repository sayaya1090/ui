package net.sayaya.ui.animate;

import elemental2.dom.Element;
import elemental2.promise.Promise;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;
import jsinterop.base.JsPropertyMap;

public class Animation {
	public static native AnimationImpl animate(Element elem, int duration, JsPropertyMap... keyframes) /*-{
	    return elem.animate(keyframes, duration);
	}-*/;

	@JsType(isNative = true, namespace = JsPackage.GLOBAL)
	public static class AnimationImpl {
		/*attribute AnimationEffectReadOnly effect;
		attribute AnimationTimeline timeline;
		attribute double startTime;
		attribute double currentTime;
		attribute double playbackRate;
		readonly attribute AnimationPlayState playState;*/
		public Promise<AnimationImpl> ready;
		public Promise<AnimationImpl> finished;
		public native void cancel ();
		public native void finish ();
		public native void play ();
		public native void pause ();
		public native void reverse ();
	};

	/*
	player = document.getElementById("code2").animate([
	{ offset: 0, transform: 'translate3d(0px,0px,0)' },
	{ offset: 0.25, transform: 'translate3d(100px,0px,0)' },
	{ offset: 0.5, transform: 'translate3d(100px,100px,0)' },
	{ offset: 0.75, transform: 'translate3d(0,100px,0)' },
	{ offset: 1, transform: 'translate3d(0,0px,0)' }
], {
	duration: 1500, //milliseconds
	easing: "ease-in-out", // "linear", "ease", "ease-in", "ease-out" a bezier curve, etc.
	delay: 10, //milliseconds
	iterations: Infinity, //or a number
	direction: 'alternate' //'normal', 'reverse', 'alternate', 'alternate-reverse' etc.
});
	 */
}
