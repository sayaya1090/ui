package net.sayaya.ui;

import com.google.gwt.core.client.EntryPoint;
import elemental2.dom.DomGlobal;
import elemental2.dom.HTMLDivElement;
import net.sayaya.ui.elements.TabsElementBuilder.TabsPrimaryElementBuilder;
import org.jboss.elemento.EventType;
import org.jboss.elemento.HTMLContainerBuilder;
import org.jboss.elemento.InputType;

import static net.sayaya.ui.elements.ButtonElementBuilder.button;
import static net.sayaya.ui.elements.CheckboxElementBuilder.checkbox;
import static net.sayaya.ui.elements.ChipsElementBuilder.chips;
import static net.sayaya.ui.elements.DividerElementBuilder.*;
import static net.sayaya.ui.elements.IconElementBuilder.icon;
import static net.sayaya.ui.elements.TabsElementBuilder.tabs;
import static net.sayaya.ui.elements.TextFieldElementBuilder.textField;
import static net.sayaya.ui.svg.SvgBuilder.svg;
import static net.sayaya.ui.svg.SvgPathBuilder.path;
import static org.jboss.elemento.Elements.*;

public class Test implements EntryPoint {
	private final TabsPrimaryElementBuilder tabs						= tabs().primary();
	private final HTMLContainerBuilder<HTMLDivElement> panelButton		= div().style("margin: 1em;padding: 1em;");
	private final HTMLContainerBuilder<HTMLDivElement> panelTextField	= div().style("margin: 1em;padding: 1em;");
	private final HTMLContainerBuilder<HTMLDivElement> panelDivider		= div().style("margin: 1em;padding: 1em;");
	private final HTMLContainerBuilder<HTMLDivElement> panelIcon		= div().style("margin: 1em;padding: 1em;");
	private final HTMLContainerBuilder<HTMLDivElement> panelCheckbox	= div().style("margin: 1em;padding: 1em;");
	private final HTMLContainerBuilder<HTMLDivElement> panelChip		= div().style("margin: 1em;padding: 1em;");
	private final HTMLContainerBuilder<HTMLDivElement> panelTab			= div().style("margin: 1em;padding: 1em;");
	@Override
	public void onModuleLoad() {
		body().add(tabs.tab().add("Button").panel(panelButton).end()
						.tab().add("Text Field").panel(panelTextField).end()
						.tab().add("Divider").panel(panelDivider).end()
						.tab().add("Icon").panel(panelIcon).end()
						.tab().add("Checkbox").panel(panelCheckbox).end()
						.tab().add("Chip").panel(panelChip).end()
						.tab().add("Tab").panel(panelTab).active(true).end())
				.add(panelButton)
				.add(panelTextField)
				.add(panelDivider)
				.add(panelIcon)
				.add(panelCheckbox)
				.add(panelChip)
				.add(panelTab);
		TestButton();
		TestTextField();
		TestDivider();
		TestIcon();
		TestCheckbox();
		TestChip();
		TestTab();

		panelButton.element().removeAttribute("hidden");
	}
	void TestButton() {
		panelButton.add(button().elevated().add("Elevated"))
				.add(button().filled().add("Filled"))
				.add(button().filledTonal().add("Filled Tonal"))
				.add(button().outlined().add("Outlined"))
				.add(button().text().add("Text"));

		panelButton.add(button().filledTonal()
						.icon(icon(svg().viewBox(0,0,48,48)
								.add(path().d("M6 40V8l38 16Zm3-4.65L36.2 24 9 12.5v8.4L21.1 24 9 27Zm0 0V12.5 27Z"))))
						.add("Send"))
				.add(button().text()
						.add("Open")
						.icon(icon(svg().viewBox(0,0,48,48)
								.add(path().d("M9 42q-1.2 0-2.1-.9Q6 40.2 6 39V9q0-1.2.9-2.1Q7.8 6 9 6h13.95v3H9v30h30V25.05h3V39q0 1.2-.9 2.1-.9.9-2.1.9Zm10.1-10.95L17 28.9 36.9 9H25.95V6H42v16.05h-3v-10.9Z"))), true));

		panelButton.add(button().elevated().add("Add").ariaLabel("Add a new contact"));

		panelButton.add(button().icon().add(icon("check")))
				.add(button().icon().filled().add(icon("check")))
				.add(button().icon().filledTonal().add(icon("check")))
				.add(button().icon().outlined().add(icon("check")));

		panelButton.add(button().icon().href("https://google.com").add(icon("check")));

		panelButton.add(div()
				.add(button().icon().add(icon("close")).toggle("check"))
				.add(button().icon().filled().add(icon("close")).toggle("check"))
				.add(button().icon().filledTonal().add(icon("close")).toggle("check"))
				.add(button().icon().outlined().add(icon("close")).toggle("check"))
		).add(div()
				.add(button().icon().add(icon("close")).toggle("check", true))
				.add(button().icon().filled().add(icon("close")).toggle("check", true))
				.add(button().icon().filledTonal().add(icon("close")).toggle("check", true))
				.add(button().icon().outlined().add(icon("close")).toggle("check", true))
		);
	}
	void TestTextField() {
		panelTextField.add(textField().filled().label("Label").value("Value"));
		panelTextField.add(textField().outlined().label("Label").value("Value"));

		panelTextField.add(textField().outlined().type(InputType.text).label("Text"));
		panelTextField.add(textField().outlined().type(InputType.email).label("Email"));
		panelTextField.add(textField().outlined().type(InputType.number).label("Number"));
		panelTextField.add(textField().outlined().type(InputType.password).label("Password"));
		panelTextField.add(textField().outlined().type(InputType.search).label("Search"));
		panelTextField.add(textField().outlined().type(InputType.tel).label("Tel"));
		panelTextField.add(textField().outlined().type(InputType.url).label("Url"));
		panelTextField.add(textField().outlined().type(InputType.textarea).label("Textarea"));

		panelTextField.add(textField().outlined().label("Favorite food"));
		panelTextField.add(textField().outlined().placeholder("email@domain.com"));
		panelTextField.add(div().add("First name"));
		panelTextField.add(textField().outlined().ariaLabel("First name"));

		panelTextField.add(textField().outlined().type(InputType.textarea).label("Vertical resize").style("resize: vertical;").rows(3));

		panelTextField.add(textField().outlined().placeholder("Search for messages")
				.icon("search"));
		panelTextField.add(textField().outlined().label("Password").type(InputType.password)
				.icon(button().icon().add(icon("visibility")).toggle("visibility_off"), true));
		panelTextField.add(textField().outlined()
				.label("Username").value("jdoe")
				.error("Username not available")
				.icon("error", true));

		panelTextField.add(textField().outlined()
				.label("Dollar amount").type(InputType.number).value("0")
				.prefixText("$").suffixText(".00"));

		panelTextField.add(textField().filled()
				.label("Comments").supportingText("Provide comments for the issue"));
		panelTextField.add(textField().filled()
				.label("Name").required(true).supportingText("*required")
				.errorText("Please fill out this field"));

		panelTextField.add(textField().outlined()
				.label("Title").value("Short").maxLength(10));

		var elemName = textField().filled().name("name").label("Name").required(true);
		var elemEmail = textField().filled().name("email").label("Email").required(true).pattern("[\\w\\d-]+").supportingText("@gmail.com");
		panelTextField.add(form().add(elemName).add(elemEmail));
		elemName.on(EventType.change, evt->elemName.element().reportValidity());
		elemEmail.on(EventType.change, evt->elemEmail.element().reportValidity());

		panelTextField.add(div().add("Username"))
				.add(textField().filled().ariaLabel("Username"))
				.add(textField().filled().label("First").ariaLabel("First name"));

	}
	void TestChip() {
		panelChip.add(chips()
				.assist().label("Assist").end()
				.filter().label("Filter").end()
				.input().label("Input").end()
				.suggestion().label("Suggestion").end());

		panelChip.add(h(3).add("New event"))
				.add(chips()
						.filter().label("All day").end()
						.assist().label("Add to calendar").end()
						.assist().label("Set a reminder").end()
				);

		panelChip.add(chips()
						.assist().label("Add to calendar").icon("event").end()
						.input().label("John Doe").avatar().icon(img("...")).end()
				);

		panelChip.add(img("..."))
				.add(chips()
						.suggestion().label("Share").elevated().end()
						.suggestion().label("Favorite").elevated().end()
				);
	}
	void TestDivider() {
		var section1 = section()
				.add(p().add("Lorem ipsum..."))
				.add(divider())
				.add(p().add("Lorem ipsum..."));

		var section2 = section()
				.add(p().add("Material 2"))
				.add(dividerInset())
				.add(p().add("Material 3"));

		var section3 = section()
				.add(p().add("Material 2"))
				.add(dividerInsetStart())
				.add(p().add("Material 3"));

		var section4 = ul()
				.add(li().add("Item one"))
				.add(dividerInset())
				.add(li().add("Item two"))
				.add(divider().separator())
				.add(li().add("Item three"))
				.add(dividerInset())
				.add(li().add("Item four"))
				;
		panelDivider.add(section1)
				.add(section2)
				.add(section3)
				.add(section4);
	}
	void TestIcon() {
		panelIcon.add(icon("settings"));
		//panelIcon.add(icon("&#xe834"));
		panelIcon.add(icon(svg().viewBox(0, 0, 48, 48)
				.add(path().d("M10 40V24H4L24 6l10 8.85V9h4v9.55L44 24h-6v16H26.5V28h-5v12Zm3-3h5.5V25h11v12H35V19.95l-11-10-11 10Zm5.5-12h11-11Zm1.25-5.5h8.5q0-1.65-1.275-2.725Q25.7 15.7 24 15.7q-1.7 0-2.975 1.075Q19.75 17.85 19.75 19.5Z"))));
	}
	void TestFocus() {

	}
	void TestField() {

	}
	void TestCheckbox() {
		panelCheckbox.add(checkbox());
		panelCheckbox.add(checkbox().checked(true));
		panelCheckbox.add(checkbox().indeterminate());
	}
	void TestProgress() {

	}

	void TestDialog() {


	}

	void TestTab() {
		var tab1 = tabs().primary().ariaLabel("Test")
				.tab().add("Video").end()
				.tab().add("Photos").end()
				.tab().add("Audio").end();

		panelTab.add(tab1);

		panelTab.add(tabs().secondary()
				.tab().add("Birds").end()
				.tab().add("Cats").end()
				.tab().add("Dogs").end()
		);
		tab1.element().activeTabIndex = 2;
		tab1.element().onchange = (e)->{
			DomGlobal.console.log("Tab changed to" + tab1.element().activeTabIndex);
			return null;
		};
		DomGlobal.console.log(tab1.element().activeTabIndex);
		DomGlobal.console.log(tab1.element().activeTab);

		panelTab.add(tabs().primary()
				.tab().add(icon("piano")).add("Keyboard").inlineIcon(true).end()
				.tab().add(icon("tune")).add("Guitar").end()
		);

		panelTab.add(tabs().ariaLabel("Content to view").primary()
				.tab().ariaLabel("Photos").add(icon("photo")).end()
				.tab().ariaLabel("Videos").add(icon("videocam")).end()
				.tab().ariaLabel("Music").add(icon("audiotrack")).end()
		);
	}
}
