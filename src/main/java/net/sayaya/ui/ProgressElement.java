package net.sayaya.ui;

import elemental2.dom.HTMLElement;
import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;
import jsinterop.base.Js;
import lombok.experimental.Delegate;
import net.sayaya.ui.progress.CircularElement;
import net.sayaya.ui.progress.LinearElement;
import org.jboss.elemento.HtmlContent;
import org.jboss.elemento.HtmlContentBuilder;
import org.jboss.elemento.IsElement;
import org.jboss.elemento.TypedBuilder;

@JsType(isNative = true, namespace = JsPackage.GLOBAL)
public abstract class ProgressElement extends HTMLElement {
    @JsOverlay
    public static CircularElement.CircularElementBuilder progressCircular() {
        return new CircularElement.CircularElementBuilder();
    }
    @JsOverlay
    public static LinearElement.LinearElementBuilder progressLinear() {
        return new LinearElement.LinearElementBuilder();
    }
    public Double max;
    public Double value;
    public boolean indeterminate;
    public boolean fourColor;
    public String ariaLabel;

    public static abstract class ProgressElementBuilder<T extends ProgressElement, B extends ProgressElementBuilder<T, B>> implements HtmlContent<T, HtmlContentBuilder<T>> {
        @Delegate(excludes = { IsElement.class, TypedBuilder.class, HtmlContent.class })
        private final HtmlContentBuilder<HTMLElement> delegate;
        private final HtmlContentBuilder<T> that;
        public ProgressElementBuilder(HtmlContentBuilder<HTMLElement> delegate) {
            this.delegate = delegate;
            that = new HtmlContentBuilder<>(element());
        }
        public B max(double value) {
            element().max = value;
            element().indeterminate = false;
            return _this();
        }
        public B value(double value) {
            element().value = value;
            element().indeterminate = false;
            return _this();
        }
        public B indeterminate() {
            element().max = null;
            element().value = null;
            element().indeterminate = true;
            element().fourColor = false;
            return _this();
        }
        public B fourColor() {
            indeterminate();
            element().fourColor = true;
            return _this();
        }
        public B ariaLabel(String label) {
            element().ariaLabel = label;
            return _this();
        }
        private B _this() {
            return (B)this;
        }
        @Override
        public T element() {
            return Js.uncheckedCast(delegate.element());
        }
        @Override
        public HtmlContentBuilder<T> that() {
            return that;
        }
    }
}
