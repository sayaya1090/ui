package net.sayaya.ui;

import com.google.gwt.core.client.EntryPoint;
import elemental2.dom.DomGlobal;
import elemental2.dom.HTMLDivElement;
import net.sayaya.ui.elements.DialogElementBuilder;
import net.sayaya.ui.elements.FabElementBuilder;
import net.sayaya.ui.elements.TabsElementBuilder.TabsPrimaryElementBuilder;
import net.sayaya.ui.util.ElementUtil;
import org.jboss.elemento.Elements;
import org.jboss.elemento.EventType;
import org.jboss.elemento.HTMLContainerBuilder;
import org.jboss.elemento.InputType;

import static com.google.gwt.user.client.Window.alert;
import static net.sayaya.ui.elements.BadgeElementBuilder.badge;
import static net.sayaya.ui.elements.ButtonElementBuilder.button;
import static net.sayaya.ui.elements.CardElementBuilder.card;
import static net.sayaya.ui.elements.CheckboxElementBuilder.checkbox;
import static net.sayaya.ui.elements.ChipsElementBuilder.chips;
import static net.sayaya.ui.elements.DialogElementBuilder.dialog;
import static net.sayaya.ui.elements.DividerElementBuilder.*;
import static net.sayaya.ui.elements.FabElementBuilder.Variant.*;
import static net.sayaya.ui.elements.FocusRingElementBuilder.focusRing;
import static net.sayaya.ui.elements.IconElementBuilder.icon;
import static net.sayaya.ui.elements.ListElementBuilder.list;
import static net.sayaya.ui.elements.MenuElementBuilder.Position.*;
import static net.sayaya.ui.elements.MenuElementBuilder.menu;
import static net.sayaya.ui.elements.ProgressElementBuilder.progress;
import static net.sayaya.ui.elements.RadioElementBuilder.radio;
import static net.sayaya.ui.elements.RippleElementBuilder.ripple;
import static net.sayaya.ui.elements.SelectElementBuilder.select;
import static net.sayaya.ui.elements.SliderElementBuilder.slider;
import static net.sayaya.ui.elements.SwitchElementBuilder.sw;
import static net.sayaya.ui.elements.TabsElementBuilder.tabs;
import static net.sayaya.ui.elements.TextFieldElementBuilder.textField;
import static net.sayaya.ui.svg.elements.SvgBuilder.svg;
import static net.sayaya.ui.svg.elements.SvgPathBuilder.path;
import static net.sayaya.ui.svg.elements.SvgPolygonBuilder.polygon;
import static org.jboss.elemento.Elements.*;

public class Test implements EntryPoint {
	private final TabsPrimaryElementBuilder tabs						= tabs().primary();
	private final HTMLContainerBuilder<HTMLDivElement> panelButton		= div().style("margin: 1em;padding: 1em;");
	private final HTMLContainerBuilder<HTMLDivElement> panelTextField	= div().style("margin: 1em;padding: 1em;");
	private final HTMLContainerBuilder<HTMLDivElement> panelSelect		= div().style("margin: 1em;padding: 1em;");
	private final HTMLContainerBuilder<HTMLDivElement> panelSlider		= div().style("margin: 1em;padding: 1em;");
	private final HTMLContainerBuilder<HTMLDivElement> panelSwitch		= div().style("margin: 1em;padding: 1em;");
	private final HTMLContainerBuilder<HTMLDivElement> panelDivider		= div().style("margin: 1em;padding: 1em;");
	private final HTMLContainerBuilder<HTMLDivElement> panelList		= div().style("margin: 1em;padding: 1em;");
	private final HTMLContainerBuilder<HTMLDivElement> panelIcon		= div().style("margin: 1em;padding: 1em;");
	private final HTMLContainerBuilder<HTMLDivElement> panelCheckbox	= div().style("margin: 1em;padding: 1em;");
	private final HTMLContainerBuilder<HTMLDivElement> panelRadio		= div().style("margin: 1em;padding: 1em;");
	private final HTMLContainerBuilder<HTMLDivElement> panelChip		= div().style("margin: 1em;padding: 1em;");
	private final HTMLContainerBuilder<HTMLDivElement> panelMenu		= div().style("margin: 1em;padding: 1em;");
	private final HTMLContainerBuilder<HTMLDivElement> panelProgress	= div().style("margin: 1em;padding: 1em;");
	private final HTMLContainerBuilder<HTMLDivElement> panelTab			= div().style("margin: 1em;padding: 1em;");
	private final HTMLContainerBuilder<HTMLDivElement> panelDialog		= div().style("margin: 1em;padding: 1em;");
	private final HTMLContainerBuilder<HTMLDivElement> panelRipple		= div().style("margin: 1em;padding: 1em;");
	private final HTMLContainerBuilder<HTMLDivElement> panelFocusRing	= div().style("margin: 1em;padding: 1em;");
	private final HTMLContainerBuilder<HTMLDivElement> panelCard		= div().style("margin: 1em;padding: 1em;");
	private final HTMLContainerBuilder<HTMLDivElement> panelBadge		= div().style("margin: 1em;padding: 1em;");
	@Override
	public void onModuleLoad() {
		body().add(tabs.tab().add("Button").panel(panelButton).active(true).end()
						.tab().add("Text Field").panel(panelTextField).end()
						.tab().add("Select").panel(panelSelect).end()
						.tab().add("Slider").panel(panelSlider).end()
						.tab().add("Switch").panel(panelSwitch).end()
						.tab().add("Divider").panel(panelDivider).end()
						.tab().add("List").panel(panelList).end()
						.tab().add("Icon").panel(panelIcon).end()
						.tab().add("Checkbox").panel(panelCheckbox).end()
						.tab().add("Radio").panel(panelRadio).end()
						.tab().add("Chip").panel(panelChip).end()
						.tab().add("Menu").panel(panelMenu).end()
						.tab().add("Progress").panel(panelProgress).end()
						.tab().add("Tab").panel(panelTab).end()
						.tab().add("Dialog").panel(panelDialog).end()
						.tab().add("Ripple").panel(panelRipple).end()
						.tab().add("Focus Ring").panel(panelFocusRing).end()
						.tab().add("Card").panel(panelCard).end()
						.tab().add("Badge").panel(panelBadge).end())
				.add(panelButton)
				.add(panelTextField)
				.add(panelSelect)
				.add(panelSlider)
				.add(panelSwitch)
				.add(panelDivider)
				.add(panelList)
				.add(panelIcon)
				.add(panelCheckbox)
				.add(panelRadio)
				.add(panelChip)
				.add(panelMenu)
				.add(panelProgress)
				.add(panelTab)
				.add(panelDialog)
				.add(panelRipple)
				.add(panelFocusRing)
				.add(panelCard)
				.add(panelBadge);
		TestButton();
		TestTextField();
		TestSelect();
		TestSlider();
		TestSwitch();
		TestDivider();
		TestList();
		TestIcon();
		TestCheckbox();
		TestRadio();
		TestChip();
		TestMenu();
		TestProgress();
		TestTab();
		TestDialog();
		TestRipple();
		TestFocusRing();
		TestCard();
		TestBadge();
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

		panelButton.add(button().fab().ariaLabel("Edit").icon("edit"));
		panelButton.add(button().fab().ariaLabel("Edit").icon("edit").lowered());

		panelButton.add(button().fab().ariaLabel("Edit").icon("edit").label("Edit"));

		panelButton.add(button().fab().label("Reroute"));

		panelButton.add(button().fab().ariaLabel("Edit").icon("edit").variant(Primary));
		panelButton.add(button().fab().ariaLabel("Edit").icon("edit").variant(Secondary));
		panelButton.add(button().fab().ariaLabel("Edit").icon("edit").variant(Tertiary));

		panelButton.add(button().fab().ariaLabel("Edit").icon("edit").touchTarget("none").size(FabElementBuilder.PlainFabElementBuilder.Size.Small));
		panelButton.add(button().fab().ariaLabel("Edit").icon("edit").size(FabElementBuilder.PlainFabElementBuilder.Size.Small));
		panelButton.add(button().fab().ariaLabel("Edit").icon("edit"));
		panelButton.add(button().fab().ariaLabel("Edit").icon("edit").size(FabElementBuilder.PlainFabElementBuilder.Size.Large));

		panelButton.add(button().fab().branded().ariaLabel("Add")
				.icon(svg().viewBox(0, 0, 36, 36)
						.add(path().fill("#34A853").d("M16 16v14h4V20z"))
						.add(path().fill("#4285F4").d("M30 16H20l-4 4h14z"))
						.add(path().fill("#FBBC05").d("M6 16v4h10l4-4z"))
						.add(path().fill("#EA4335").d("M20 16V6h-4v14z"))
						.add(path().fill("none").d("M0 0h36v36H0z"))
				));

		panelButton.add(button().fab().branded().label("Add")
				.icon(svg().viewBox(0, 0, 36, 36)
						.add(path().fill("#34A853").d("M16 16v14h4V20z"))
						.add(path().fill("#4285F4").d("M30 16H20l-4 4h14z"))
						.add(path().fill("#FBBC05").d("M6 16v4h10l4-4z"))
						.add(path().fill("#EA4335").d("M20 16V6h-4v14z"))
						.add(path().fill("none").d("M0 0h36v36H0z"))
				));

		panelButton.add(button().fab().branded().ariaLabel("Add").size(FabElementBuilder.BrandedFabElementBuilder.Size.Large)
				.icon(svg().viewBox(0, 0, 36, 36)
						.add(path().fill("#34A853").d("M16 16v14h4V20z"))
						.add(path().fill("#4285F4").d("M30 16H20l-4 4h14z"))
						.add(path().fill("#FBBC05").d("M6 16v4h10l4-4z"))
						.add(path().fill("#EA4335").d("M20 16V6h-4v14z"))
						.add(path().fill("none").d("M0 0h36v36H0z"))
				));

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
				.iconLeading("search"));
		panelTextField.add(textField().outlined().label("Password").type(InputType.password)
				.iconTrailing(button().icon().add(icon("visibility")).toggle("visibility_off")));
		panelTextField.add(textField().outlined()
				.label("Username").value("jdoe")
				.error("Username not available")
				.iconTrailing("error"));

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
	void TestSelect() {
		panelSelect.add(
				select().outlined()
						.option().ariaLabel("blank").end()
						.option().value("apple").headline("Apple").select().end()
						.option().value("apricot").headline("Apricot").end());
		panelSelect.add(
				select().filled()
						.option().ariaLabel("blank").end()
						.option().value("apple").headline("Apple").select().end()
						.option().value("apricot").headline("Apricot").end());

		panelSelect.add(
				select().filled().required().supportingText("A")
						.option().value("one").headline("One").end()
						.option().value("two").headline("Two").end());
	}
	void TestSlider() {
		panelSlider.add(slider());
		panelSlider.add(slider().ticks(5).value(50));
		panelSlider.add(slider().range(25, 50).valueStart(25).valueEnd(75));

		panelSlider.add(slider().min(0).max(100).value(50));
		panelSlider.add(slider().ticks(5).min(0).max(20));
		panelSlider.add(slider().range(25, 50));

		panelSlider.add(slider().labeled());
	}
	void TestSwitch() {
	    panelSwitch.add(sw());
		panelSwitch.add(sw().select());

		panelSwitch.add(sw().icons());
		panelSwitch.add(sw().icons().select());
		panelSwitch.add(sw().showOnlySelectedIcon());
		panelSwitch.add(sw().showOnlySelectedIcon().select());

		panelSwitch.add(sw().disable());
		panelSwitch.add(sw().required());

		panelSwitch.add(label().add("Wi-Fi").add(sw().select()));

		panelSwitch.add(sw().ariaLabel("Lights"));
		panelSwitch.add(label().add("All").add(sw().ariaLabel("All notifications")));
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
	void TestList() {
		panelList.add(list()
				.item().add("Fruits").end()
				.divider()
				.item().add("Apple").end()
				.item().add("Banana").end()
				.item().headline("Cucumber")
					   .supportingText("Cucumbers are long green fruits that are just as long as this multi-line description").end()
				.item().headline("Shop for Kiwis")
					   .supportingText("This will link you out in a new tab").end(icon("open_in_new"))
		);

		panelList.add(list()
				.item().add("Lit").start(svg().style("height: 24px;").viewBox(0, 0, 160, 200)
						.add(path().fill("currentColor").d("m160 80v80l-40-40zm-40 40v80l40-40zm0-80v80l-40-40zm-40 40v80l40-40zm-40-40v80l40-40zm40-40v80l-40-40zm-40 120v80l-40-40zm-40-40v80l40-40z"))).end()
				.item().add("Polymer").start(icon("polymer")).end()
				.item().add("Angular").start(svg().style("height: 24px;").viewBox(0, 0, 250, 250)
						.add(polygon().points("108,135.4 125,135.4 125,135.4 125,135.4 142,135.4 125,94.5"))
						.add(path().d("M125,30L125,30L125,30L31.9,63.2l14.2,123.1L125,230l0,0l0,0l78.9-43.7l14.2-123.1L125,30z M183.1,182.6h-21.7h0\n" +
								"l-11.7-29.2H125h0h0h-24.7l-11.7,29.2h0H66.9h0L125,52.1l0,0l0,0l0,0l0,0L183.1,182.6L183.1,182.6z"))).end()
		);

		panelList.add(list()
				.item().add("Cat").start(img("https://placekitten.com/112/112").style("width: 56px;")).end()
				.divider()
				.item().add("Kitty Cat").start(img("https://placekitten.com/114/114").style("width: 56px;")).end()
				.divider()
				.item().add("Cate").start(img("https://placekitten.com/116/116").style("width: 56px;")).end()
		);

		panelList.add(list()
				.item().type("button").headline(div().add("Apple"))
					.supportingText(div().add("In stock"))
					.trailingSupportingText(div().add("+100")).end()
				.item().type("button").headline(div().add("Banana"))
					.supportingText(div().add("In stock"))
					.trailingSupportingText(div().add("56")).end()
				.item().type("button").headline(div().add("Cucumber"))
					.supportingText(div().add("Low stock"))
					.trailingSupportingText(div().add("5")).end()
		);

		panelList.add(list()
				.item().headline(div().add("Eggs"))
						.start(icon("egg")).end(icon("recommend")).end()
				.item().headline(div().add("Ice Cream"))
					.start(icon("icecream")).end(icon("dangerous")).end()
				.item().headline(div().add("Orange"))
					.start(icon("nutrition")).end(icon("recommend")).end()
		);
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
		panelCheckbox.add(checkbox().select());
		panelCheckbox.add(checkbox().indeterminate());
	}
	void TestRadio() {
		panelRadio.add(form()
				.add(radio().name("animals").value("cats"))
				.add(radio().name("animals").value("dogs"))
				.add(radio().name("animals").value("birds").select())
		);
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

		panelChip.add(h(3).add("Dates"))
				.add(chips().ariaLabelledBy("dates-label")
						.filter().label("Mon").ariaLabel("Monday").end()
						.filter().label("Tue").ariaLabel("Tuesday").end()
						.filter().label("Wed").ariaLabel("Wednesday").end()
				);

		panelChip.add(chips().ariaLabel("Actions")
				.assist().label("Copy").disable().alwaysFocusable(true).end()
				.assist().label("Paste").disable().alwaysFocusable(true).end()
		).add(textField().outlined().type(InputType.textarea));


		panelChip.add(h(3).add("A restaurant location"))
				.add(chips()
						.assist().label("Add to my itinerary").icon("calendar").end()
						.assist().label("12 mins from hotel").icon("map").end()
				);

		panelChip.add(h(3).add("Choose where to share"))
				.add(chips()
						.filter().label("Docs").end()
						.filter().label("Slides").select().end()
						.filter().label("Sheets").select().end()
						.filter().label("Images").end()
				);

		panelChip.add(h(3).add("Colors"))
				.add(chips()
						.filter().label("Red").removable().select(true).end()
						.filter().label("Yellow").removable().end()
						.filter().label("Blue").removable().end()
						.filter().label("Green").removable().end()
				);

		panelChip.add(textField().outlined().label("Attendees").type(InputType.email))
				.add(chips()
						.input().label("John Doe").avatar().icon(img("...")).end()
						.input().label("Jane Doe").avatar().icon(img("...")).end()
				);

		panelChip.add(h(3).add("Favorite movies"))
				.add(chips()
						.input().label("Star Wars").removeOnly().end()
						.input().label("Star Trek").removeOnly().end()
				);

		panelChip.add(h(3).add("Suggested reply"))
				.add(chips()
						.suggestion().label("I agree").end()
						.suggestion().label("Looks good to me").end()
						.suggestion().label("Thank you").end()
				);
	}
	void TestMenu() {
		var button = button().filled().id(ElementUtil.uniqueId()).add("Set with idref");
		var menu = menu();
		panelMenu.add(span().style("position: relative;")
				.add(button)
				.add(menu.anchor(button.element().id)
						.item().headline("Apple").end()
						.item().headline("Banana").end()
						.item().headline("Cucumber").end()));
		button.on(EventType.click, evt->menu.toggle());

		var button2 = button().filled().add("Set with element ref");
		panelMenu.add(span().style("position: relative;")
				.add(button2)
				.add(menu().anchorElement(button2)
						.item().headline("Apple").end()
						.item().headline("Banana").end()
						.item().headline("Cucumber").end()));

		var button3 = button().filled().add("Menu with Submenus");
		panelMenu.add(span().style("position: relative;")
				.add(button3)
				.add(menu().anchorElement(button3).overflow()
						.sub()
							.item().headline("Fruits with A").end(icon("arrow_right")).end()
							.menu()
								.item().headline("Apricot").end()
								.item().headline("Avocado").end()
								.sub().menuCorner("start-end").anchorCorner("start-start")
									.item().headline("Apples").start(icon("arrow_left")).end()
									.menu()
										.item().headline("Fuji").end()
										.item().headline(div().style("white-space: nowrap;").add("Granny Smith")).end()
										.item().headline(div().style("white-space: nowrap;").add("Red Delicious")).end()
									.end()
								.end()
							.end()
						.item().headline("Banana").end()
						.item().headline("Cucumber").end()
						.end()));


		var button4 = button().filled().add("Open popover menu");
		panelMenu.add(div().style("margin: 16px;").add(button4))
				.add(menu().positioning(Popover).anchorElement(button4)
						.item().headline("Apple").end()
						.item().headline("Banana").end()
						.item().headline("Cucumber").end());

		var button5 = button().filled().add("Open fixed menu");
		panelMenu.add(div().style("margin: 16px;").add(button5))
				.add(menu().positioning(Fixed).anchorElement(button5)
						.item().headline("Apple").end()
						.item().headline("Banana").end()
						.item().headline("Cucumber").end());

		var button6 = button().filled().add("Open document menu");
		panelMenu.add(div().style("margin: 16px;").add(button6))
				.add(menu().positioning(Document).anchorElement(button6)
						.item().headline("Apple").end()
						.item().headline("Banana").end()
						.item().headline("Cucumber").end());
	}
	void TestProgress() {
		panelProgress.add(progress().circular().value(0.75));
		panelProgress.add(progress().circular().indeterminate());
		panelProgress.add(progress().linear().indeterminate());
		panelProgress.add(progress().linear().value(0.5));

		panelProgress.add(progress().circular().fourColor().indeterminate());
		panelProgress.add(progress().linear().fourColor().indeterminate());

		panelProgress.add(progress().circular().value(0.5).ariaLabel("Page refresh progress"));
		panelProgress.add(progress().linear().value(0.5).ariaLabel("Download progress"));

		panelProgress.add(progress().linear().value(0.5).buffer(0.8));
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
	void TestDialog() {
		var form1 = form().id().add("A simple dialog with free-form content.");
		var dialog1 = dialog()
				.headline("Dialog title")
				.content(form1);
		dialog1.actions(div().add(button().text().form(form1).add("OK").value("ok")
				.on(EventType.click, evt->dialog1.close().then(msg->{
			alert(msg);
			alert(dialog1.element().returnValue);
			return null;
		}))));
		panelDialog.add(button().outlined().add("Dialog1").on(EventType.click, evt->dialog1.open()))
				.add(dialog1);

		var dialog2 = dialog().open().content("A dialog that is opened by default.");
		panelDialog.add(button().outlined().add("Dialog2").on(EventType.click, evt->panelDialog.add(dialog2)));

		var form3 = form().id().add("...");
		var dialog3 = dialog()
				.content(form3);
		dialog3.actions(div()
				.add(button().text().form(form3).add("Cancel").value("cancel"))
				.add(button().text().form(form3).add("OK").value("ok")))
				.element().addEventListener("closed", evt-> alert(dialog3.element().returnValue));
		panelDialog.add(button().outlined().add("Dialog3").on(EventType.click, evt->dialog3.open()))
				.add(dialog3);


		var form4 = form().add("An error occurred, details ...");
		var dialog4 = dialog().ariaLabel("Error message").content(form4);
		panelDialog.add(button().outlined().add("Dialog4").on(EventType.click, evt->dialog4.open()))
				.add(dialog4);


		var form5 = form().id().add("Are you sure you wish to delete this item?");
		var dialog5 = DialogElementBuilder.alert()
				.headline("Confirm deletion")
				.content(form5);
		dialog5.actions(div()
						.add(button().text().form(form5).add("Cancel").value("cancel"))
						.add(button().text().form(form5).add("OK").value("ok")))
				.element().addEventListener("closed", evt-> alert(dialog5.element().returnValue));
		panelDialog.add(button().outlined().add("Dialog5").on(EventType.click, evt->dialog5.open()))
				.add(dialog5);
	}
	void TestRipple() {
		panelRipple.add(Elements.button().style("position: relative; width: 40px; height: 40px;").add(ripple()));

		var btn2 = Elements.input("button").id().style("width: 40px; height: 40px;");
		var ripple2 = ripple();
		panelRipple.add(div().style("position: relative;").add(ripple2).add(btn2));
		ripple2.control(btn2);
	}
	void TestFocusRing() {
		var button = Elements.button().style("position: relative; width: 40px; height: 40px;").element();
		button.append(focusRing().element());
		panelFocusRing.add(button);

		var button2 = Elements.button().style("width: 40px; height: 40px;").element();
		panelFocusRing.add(div().style("position: relative;")
				.add(focusRing().control(button2))
				.add(button2));

		var button3 = Elements.button().style("width: 40px; height: 40px;").element();
		var focusRing = focusRing();
		focusRing.element().attach(button3);
		panelFocusRing.add(div().style("position: relative;")
				.add(focusRing)
				.add(button3));

		var button4 = Elements.button().style("position: relative; width: 40px; height: 40px;").element();
		button4.append(focusRing().inward().element());
		panelFocusRing.add(button4);
	}
	void TestCard() {
		var MEDIA_IMAGE =
				"data:image/png;base64,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";
		panelCard.add(div().style("display: flex; flex-wrap: wrap; gap: 8px; color: var(--md-sys-color-on-surface);")
				.add(card().elevated().style("width: 192px;").add(img(MEDIA_IMAGE).style("border-radius: inherit; background: #dadce0; object-fit: contain; height: 128px;")))
				.add(card().filled().style("width: 192px;").add(img(MEDIA_IMAGE).style("border-radius: inherit; background: #dadce0; object-fit: contain; height: 128px;")))
				.add(card().outlined().style("width: 192px;").add(img(MEDIA_IMAGE).style("border-radius: inherit; background: #dadce0; object-fit: contain; height: 128px;"))));
	}
	void TestBadge() {
		var badge = badge();
		badge.element().value = "1";
		var btn = button().filled().add("Button");
		panelBadge.add(div().add(btn.add(badge)));
	}
}
