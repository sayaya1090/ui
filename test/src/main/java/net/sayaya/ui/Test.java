package net.sayaya.ui;

import com.google.gwt.core.client.EntryPoint;
import elemental2.dom.HTMLDivElement;
import org.jboss.elemento.HtmlContentBuilder;

import static net.sayaya.ui.ButtonElement.button;
import static net.sayaya.ui.CheckboxElement.CheckboxState.*;
import static net.sayaya.ui.CheckboxElement.checkbox;
import static net.sayaya.ui.ChipElement.chip;
import static net.sayaya.ui.DividerElement.*;
import static net.sayaya.ui.FocusRingElement.focusRing;
import static net.sayaya.ui.IconElement.icon;
import static net.sayaya.ui.ProgressElement.progressCircular;
import static net.sayaya.ui.ProgressElement.progressLinear;
import static net.sayaya.ui.field.FieldFilledElement.filledField;
import static net.sayaya.ui.field.FieldOutlinedElement.outlinedField;
import static net.sayaya.ui.svg.SvgBuilder.svg;
import static net.sayaya.ui.svg.SvgPathBuilder.path;
import static net.sayaya.ui.textfield.FieldFilledElement.filledTextField;
import static net.sayaya.ui.textfield.FieldOutlinedElement.outlinedTextField;
import static org.jboss.elemento.Elements.*;

public class Test implements EntryPoint {
	private final HtmlContentBuilder<HTMLDivElement> content = div();
	@Override
	public void onModuleLoad() {
		//LayoutTest();
		TestButton();
		TestIconButton();
		TestChip();
		TestDivider();
		TestFocus();
		TestField();
		TestTextField();
		TestCheckbox();
		TestProgress();
		//TestDialog();
		TestIcon();
	}
	void LayoutTest() {

	}
	void TestButton() {
		body().add(button().elevated().leading(icon("search")).add("elevated"));
		body().add(button().filled()
				.leading(svg().viewBox(0, 0, 48, 48)
				.add(path().d("M6 40V8l38 16Zm3-4.65L36.2 24 9 12.5v8.4L21.1 24 9 27Zm0 0V12.5 27Z")).element())
				.add("filled"));
		body().add(button().tonal().ariaLabel("Add a new contact").add("tonal"));
		body().add(button().outlined().add("outlined"));
		body().add(button().text()
				.trailing(svg().viewBox(0, 0, 48, 48)
				.add(path().d("M9 42q-1.2 0-2.1-.9Q6 40.2 6 39V9q0-1.2.9-2.1Q7.8 6 9 6h13.95v3H9v30h30V25.05h3V39q0 1.2-.9 2.1-.9.9-2.1.9Zm10.1-10.95L17 28.9 36.9 9H25.95V6H42v16.05h-3v-10.9Z")).element())
				.add("text"));
	}
	void TestIconButton() {
		body().add(button().icon(svg().viewBox(0, 0, 48, 48)
						.add(path().d("M6 40V8l38 16Zm3-4.65L36.2 24 9 12.5v8.4L21.1 24 9 27Zm0 0V12.5 27Z")).element())
				.filled());
		body().add(button().icon(icon("search")).tonal().ariaLabel("Add a new contact"));
		body().add(button().icon(icon("search")).outlined());
		body().add(button()
				.icon(svg().viewBox(0, 0, 48, 48)
						.add(path().d("M9 42q-1.2 0-2.1-.9Q6 40.2 6 39V9q0-1.2.9-2.1Q7.8 6 9 6h13.95v3H9v30h30V25.05h3V39q0 1.2-.9 2.1-.9.9-2.1.9Zm10.1-10.95L17 28.9 36.9 9H25.95V6H42v16.05h-3v-10.9Z")).element())
				.plain());
	}
	void TestChip() {
		body().add(chip().assist().icon(icon("search")).label("Assist Chip"));
		body().add(chip().filter().icon(icon("search")).label("Filter Chip"));
	}
	void TestDivider() {
		body().add(div()
				.add("A")
				.add(divider())
				.add("B")
				.add(dividerInset())
				.add("C")
				.add(dividerInsetStart())
				.add("D")
		);
	}
	void TestFocus() {
		body().add(button().outlined().add("button1").add(focusRing()));

		var button2 = button().outlined().add("button2");
		body().add(button2);
		body().add(focusRing().attach(button2.element()).inward());
	}
	void TestField() {
		body().add(outlinedField());
		body().add(filledField());
	}
	void TestTextField() {
		body().add(outlinedTextField());
		body().add(filledTextField());
	}
	void TestCheckbox() {
		body().add(checkbox().state(UNCHECKED));
		body().add(checkbox().state(CHECKED));
		body().add(checkbox().state(INDETERMINATE));
		body().add(checkbox().ariaLabel("Agree to terms and conditions"));
	}
	void TestProgress() {
		body().add(progressCircular().indeterminate().fourColor());
		body().add(progressCircular().max(100).value(70).ariaLabel("Page refresh progress"));

		body().add(progressLinear().indeterminate().fourColor());
		body().add(progressLinear().max(100).value(30).ariaLabel("Page refresh progress"));
	}

	void TestDialog() {
		var dialog = DialogElement.dialog()
				.icon(icon("search"))
				.headline(div().add("Title"))
				.content(form().add(span().add("Contents")))
				.actions(div().add(button().text().add("close"))
						.add(button().text().add("ok")));
		body().add(dialog);
		dialog.element().open = true;

	}

	void TestIcon() {
		body().add(icon("check"));
	}
}
