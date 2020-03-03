package net.sayaya.ui.input;

import elemental2.dom.EventListener;
import elemental2.dom.HTMLInputElement;
import net.sayaya.ui.event.HandlerRegistration;

public class InputDecorator {
	public static class InputLabeled<V, I extends Input<V, I>> implements Input<V, I> {
		private final Input<V, ?> delegate;
		private final String label;
		InputLabeled(Input<V, ?> delegate, String label) {
			this.delegate = delegate;
			this.label = label;
		}

		@Override
		public V getValue() {
			return delegate.getValue();
		}

		@Override
		public HandlerRegistration addValueChangeHandler(ValueChangeEventListener<V> listener) {
			return delegate.addValueChangeHandler(listener);
		}

		@Override
		public I setEnabled(boolean enabled) {
			delegate.setEnabled(enabled);
			return self();
		}

		@Override
		public I setAccessKey(char key) {
			delegate.setAccessKey(key);
			return self();
		}

		@Override
		public I setFocus() {
			delegate.setFocus();
			return self();
		}

		@Override
		public HandlerRegistration addClickHandler(EventListener listener) {
			return delegate.addClickHandler(listener);
		}

		@Override
		public HTMLInputElement element() {
			return null;
		}
	}
	public <V, I extends Input<V, I>> InputLabeled<V, I> label(Input<V, ?> input, String label) {
		return new InputLabeled(input, label);
	}
}
