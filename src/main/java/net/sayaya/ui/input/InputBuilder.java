package net.sayaya.ui.input;

import lombok.Setter;
import lombok.experimental.Accessors;

import javax.swing.*;

@Setter
@Accessors(fluent=true)
public class InputBuilder {
	private InputBuilder(){}
	public static InputCheckBoxBuilder checkbox() {
		return new InputCheckBoxBuilder();
	}
	public static InputComboBoxBuilder combo() {
		return new InputComboBoxBuilder();
	}
	public static InputRadioBuilder radio() {
		return new InputRadioBuilder();
	}
	public static InputTextBoxBuilder text() {
		return new InputTextBoxBuilder();
	}
	public static InputNumberBoxBuilder number() {
		return new InputNumberBoxBuilder();
	}
	@Setter
	@Accessors(fluent=true)
	public static class InputCheckBoxBuilder {
		private InputCheckBoxBuilder(){};
		public CheckBox element() {
			return new CheckBox();
		}
	}
	@Setter
	@Accessors(fluent=true)
	public static class InputComboBoxBuilder {
		private InputComboBoxBuilder() {}
		public ComboBox element() {
			return new ComboBox();
		}
	}
	@Setter
	@Accessors(fluent=true)
	public static class InputRadioBuilder {
		private InputRadioBuilder() {}
		public Radio element() {
			return new Radio();
		}
	}
	@Setter
	@Accessors(fluent=true)
	public static class InputTextBoxBuilder {
		private InputTextBoxBuilder() {}
		public TextBox element() {
			return new TextBox();
		}
	}
	@Setter
	@Accessors(fluent=true)
	public static class InputNumberBoxBuilder {
		private Double min;
		private Double max;
		private InputNumberBoxBuilder() {}
		public NumberBox element() {
			return new NumberBox().min(min).max(max);
		}
	}
}
