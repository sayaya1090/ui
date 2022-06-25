package net.sayaya.ui;

import elemental2.dom.Element;
import elemental2.dom.HTMLElement;
import jsinterop.annotations.JsFunction;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;
import jsinterop.base.JsPropertyMap;

public class Animation {
	public static native AnimationImpl animate(Element elem, int duration, JsPropertyMap... keyframes) /*-{
    	if(elem.animate!=null) return elem.animate(keyframes, duration);
    	else return null;
	}-*/;

	@JsType(isNative = true, namespace = JsPackage.GLOBAL)
	public static class AnimationImpl {
		public AnimationCallback oncancel;
		public AnimationCallback onfinish;
		public native void cancel ();
		public native void finish ();
		public native void play ();
		public native void pause ();
		public native void reverse ();
	};

	@FunctionalInterface
	@JsFunction
	public interface AnimationCallback {
		void invoke();
	}
}
