package net.sayaya.ui;

import com.google.gwt.core.client.*;
import elemental2.dom.*;
import jsinterop.base.JsPropertyMap;
import net.sayaya.ui.sheet.Data;
import net.sayaya.ui.sheet.SheetElement;
import net.sayaya.ui.sheet.SheetElementSelectableSingle;
import net.sayaya.ui.sheet.column.ColumnBuilder;
import org.gwtproject.event.shared.HandlerRegistration;
import org.jboss.elemento.Elements;
import org.jboss.elemento.HtmlContentBuilder;

import static net.sayaya.ui.IconElement.icon;
import static org.jboss.elemento.Elements.*;

public class Test implements EntryPoint {
	private final HtmlContentBuilder<HTMLDivElement> content = div();
	@Override
	public void onModuleLoad() {
		LayoutTest();
		TestMenu();
		/*TestBreadcumb();
		ProgressTest();
		RadioTest();
		TestSwitch();
		AnimationTest();
		TestButton();
		TestCheckBox();
		TestDropDown();
		TestText();
		TestChip();
		//TestList();
		// TestDialog();
		//TestPage();
		//TestTab();*/
		//TestSheet();
	}
	void LayoutTest() {
		TopBarElement.TopBarButton menu = TopBarElement.buttonNavigation("menu");
		TopBarElement top = TopBarElement.topBarFixed()
						   .add(TopBarElement.section().add(menu).title("Test Top Bar"))
						   .add(TopBarElement.section().right().add(TopBarElement.buttonAction("file_download")))
						   .target(content);
		HtmlContentBuilder<HTMLDivElement> div = div().add(top).add(content);
		DrawerElement drawer = DrawerElement.drawer().header(DrawerElement.header()
													 .title(label().add("Mail"))
													 .subtitle(label().add("AAA")))
							  .content(DrawerElement.content()
											 .header("Mail")
											 .divider()
											 .add(DrawerElement.item().icon(IconElement.icon("inbox")).text("Inbox").activate(true))
											 .add(DrawerElement.item().icon(IconElement.icon("star")).text("Star").activate(true))
											 .add(DrawerElement.item().icon(IconElement.icon("send")).text("Sent Main")))
							  .target(div);
		Elements.body().add(drawer);
		Elements.body().add(div);
		menu.onClick(evt->drawer.toggle());
	}
	void TestMenu() {
		ListElement list = ListElement.singleLineList()
									  .add(ListElement.singleLine().label("AA").trailing(SwitchElement.sw(false)))
									  .add(ListElement.singleLine().label("BB"))
									  .add(ListElement.singleLine().label("CC"))
									  .divider()
									  .add(ListElement.singleLine().label("DD"))
									  .add(ListElement.singleLine().label("EE"));
		MenuElement elem = MenuElement.build(list);
		content.add(elem);
		elem.positionFixed(true).open();
	}
	void TestBreadcumb() {
		BreadcumbElement elem = BreadcumbElement.home(IconElement.icon("home"), evt->{}).add("Text", evt->{}, "http://href").add(IconElement.icon("article"), evt->{});
		content.add(elem);
	}
	void ProgressTest() {
		ProgressBarElement elem = ProgressBarElement.progressBar().determinate(true).buffer(0.5).progress(0.2);
		content.add(elem);
	}
	void RadioTest() {
		RadioElement<String> a = RadioElement.radio("RADIO", "A");
		RadioElement<String> b = RadioElement.radio("RADIO", "B");
		RadioElement<String> c = RadioElement.radio("RADIO", "C");
		RadioElement<String> d = RadioElement.radio("RADIO", "D");
		a.onValueChange(evt->{
			DomGlobal.alert(evt.value());
		});
		content.add(a).add(b).add(c).add(d).element();
	}
	void AnimationTest() {
		ButtonElement tmp = ButtonElement.contain().text("Text Button").style("position: relative");
		content.add(tmp);
		Animation.AnimationImpl t = Animation.animate(tmp.element(), 5000
				, JsPropertyMap.of("left", "0px", "backgroundColor", "#FF00FF", "opacity", "1")
				, JsPropertyMap.of("left", "300px", "opacity", "0")
				, JsPropertyMap.of("left", "0px", "backgroundColor", "#0000FF", "opacity", "1"));
		// Scheduler.get().scheduleFixedDelay(()->{t.finish(); return false;}, 1000);
		DomGlobal.console.info(t);
		if(t!=null) t.onfinish = ()->{
			GWT.log("A");
		};
	}
	void TestChip() {

		ChipElementCheckable chip3 = ChipElement.check("Chip 3").before(IconElement.icon("face")).value(true);
		ChipElementCheckable chip4 = ChipElement.check("Chip 4").before(IconElement.icon("face"));
		ChipSetElement chips = ChipSetElement.filters(chip3, chip4);
		content.add(chips);
		//chips.add(chip3).add(chip4);

	}
	void TestList() {
		ListGroupElement group = ListGroupElement.group();
		group.add(ListGroupElement.header(3).textContent("List Single line"))
			 .add(ListElement.singleLineList()
					  .add(ListElement.singleLine().label("AA").trailing(SwitchElement.sw(false)))
					  .add(ListElement.singleLine().label("BB"))
					  .add(ListElement.singleLine().label("CC"))
					  .divider()
					  .add(ListElement.singleLine().label("DD"))
					  .add(ListElement.singleLine().label("EE")))
			 .add(ListGroupElement.header(3).textContent("List Double line"))
			 .add(ListElement.doubleLineList()
					  .add(ListElement.doubleLine().primary("AA"))
					  .add(ListElement.doubleLine().primary("BB").secondary("CCCCCCCCC").leading(IconElement.icon("event")))
					  .add(ListElement.doubleLine().primary("CC").secondary("CCCCCCCCC"))
					  .divider()
					  .add(ListElement.doubleLine().primary("DD").secondary("CCCCCCCCC"))
					  .add(ListElement.doubleLine().primary("EE").secondary("CCCCCCCCC")));
		content.add(group);
	}
	private void TestCheckBox() {
		CheckBoxElement checkBoxElement = CheckBoxElement.checkBox(true).text("Test");
		content.add(checkBoxElement);
		checkBoxElement.onValueChange(evt->
			DomGlobal.console.log(evt.value() + ", " + checkBoxElement.value())
		);
	}

	private void TestDropDown() {
		ListElement<ListElement.SingleLineItem> listElement = ListElement.singleLineList()
				.add(ListElement.singleLine().label("AA"))
				.add(ListElement.singleLine().label("BB"))
				.add(ListElement.singleLine().label("CC"))
				.add(ListElement.singleLine().label("DD"))
				.add(ListElement.singleLine().label("EE"));
		DropDownElement dropdown = DropDownElement.outlined(listElement).text("Dropdown").style("width: 300px;");
		content.add(dropdown);
		dropdown.onValueChange(evt->{
			DomGlobal.console.log(evt.value());
		});
		ListElement<ListElement.SingleLineItem> listElement2 = ListElement.singleLineList()
				.add(ListElement.singleLine().label("AA").attr("data-value", "A"))
				.add(ListElement.singleLine().label("BB").attr("data-value", "B"))
				.add(ListElement.singleLine().label("CC").attr("data-value", "C"))
				.add(ListElement.singleLine().label("DD").attr("data-value", "D"))
				.add(ListElement.singleLine().label("EE").attr("data-value", "E"));
		DropDownElement dropdown2 = DropDownElement.filled(listElement2);
		content.add(dropdown2);
		dropdown2.onSelectionChange(evt->{
			DomGlobal.alert(dropdown2.value());
		});
	}
	void TestButton() {
		ButtonElement tmp = ButtonElement.contain().text("Text Button");
		HandlerRegistration handler = tmp.onClick(evt->{
			DomGlobal.alert("Hello, World!!");
		});
		content.add(tmp);

		ButtonElement tmp2 = ButtonElement.flat().text("Button Enabled")
							.before(IconElement.icon("sync"))
							.enabled(true);
		content.add(tmp2);

		ButtonElement tmp3 = ButtonElement.contain().text("Button Disabled").enabled(false);
		content.add(tmp3);

		ButtonElement tmp4 = ButtonElement.outline().text("Button Focused");
		tmp4.element().accessKey = "A";
		tmp4.element().focus();
		content.add(tmp4);

		ButtonElement tmp5 = ButtonElement.floating(IconElement.icon("add"));
		content.add(tmp5);

		ButtonElement tmp6 = ButtonElement.floating(IconElement.icon("add")).text("Create");
		tmp6.onClick(evt->DomGlobal.alert("Created"));
		content.add(tmp6);

		ButtonElementToggle tmp7 = ButtonElement.toggle().text("Toggle");
		tmp7.onValueChange(evt->{
			DomGlobal.alert(evt.value()+"");
		});
		Scheduler.get().scheduleFixedDelay(()->{
			tmp7.value(true);
			return false;
		}, 1000);
		content.add(tmp7);
	}

	void TestText() {
		TextFieldElement<String> tmp1 = TextFieldElement.textBox().outlined().text("tmp1").before(IconElement.icon("favorite")).value("FFF");
		tmp1.onValueChange(evt->{
		//	DomGlobal.alert(evt.value());
		});
		content.add(tmp1);
		TextFieldElement tmp3 = TextFieldElement.textBox().outlined().before(icon("title")).text("Title").style("width: 100%;").required(true);
		content.add(tmp3);
		Scheduler.get().scheduleFixedDelay(()->{
			tmp3.value("FFFFFFFFFFFFFFFFF");
			return false;
		}, 1000);
		//content.add(TextField.dateBox().outlined().before(icon("today")).text("Date").required(true).value(new JsDate((double) 1595919979408L)));
		TextFieldElement<String> tmp2 = TextFieldElement.password().filled().text("tmp2").before(IconElement.icon("vpn_key")).trailing(IconElement.icon("visibility"));
		tmp2.onValueChange(evt->{
			DomGlobal.console.log(evt.value());
		});
		content.add(tmp2);
		TextAreaElement<String> tmp4 = TextAreaElement.textBox().filled().text("tmp4").before(IconElement.icon("vpn_key")).trailing(IconElement.icon("visibility"));
		content.add(tmp4);
	}
	/*void TestDialog() {
		ButtonText close1 = Button.flat().text("Cancel");
		ButtonText close2 = Button.flat().text("OK");
		Dialog dialog1 = Dialog.alert("Alert", close1, close2);
		close1.onClick(evt->dialog1.close());
		close2.onClick(evt->dialog1.close());
		content.add(dialog1);
		dialog1.open();

		ButtonText close3 = Button.flat().text("Cancel");
		ButtonText close4 = Button.flat().text("OK");
		Dialog dialog2 = Dialog.confirmation("Confirmation", close3, close4).add("Content");
		close3.onClick(evt->dialog2.close());
		close4.onClick(evt->dialog2.close());
		content.add(dialog2);
		dialog2.open();
	}*/
	private void TestPage() {
		PageElement page = PageElement.instance().idx(0).show(30).total(1000);
		page.sortable("A1", "AC", "CC");
		content.add(page);
	}

	private void TestSwitch() {
		SwitchElement sw = SwitchElement.sw(true);
		content.add(sw);
		sw.onValueChange(evt->{
			DomGlobal.alert(evt.value() + "!");
		});
	}
	private void TestTab() {
		TabBarElement tab = TabBarElement.tabBar(TabBarElement.tab().text("Favorites").icon("favorite"), TabBarElement.tab().text("Favorites").icon("favorite"), TabBarElement.tab().text("Favorites").icon("favorite")).activate(0);
		content.add(tab);
		tab.onValueChange(evt->{
			DomGlobal.alert(evt.value());
		});
	}
	private void TestSheet() {
		SheetElement sheetElement = SheetElement.builder()
				.columns(ColumnBuilder.string("A").name("A").pattern("^a$").colorConditional("red").colorConditionalBackground("yellow").color("blue").build(),
						ColumnBuilder.string("B").build(),
						ColumnBuilder.checkbox("C").build()).build();
		SheetElementSelectableSingle.header(sheetElement);
		content.add(sheetElement);
		sheetElement.values(new Data[] {
			new Data("1").put("A", "FFF"), new Data("2").put("A", "a"), new Data("3")
		});
	}
}
