package net.sayaya.ui;

import com.google.gwt.core.client.*;
import elemental2.core.JsDate;
import elemental2.dom.*;
import jsinterop.base.JsPropertyMap;
import net.sayaya.ui.grid.*;
import org.gwtproject.event.shared.HandlerRegistration;
import org.jboss.elemento.Elements;
import org.jboss.elemento.HtmlContentBuilder;

import java.util.concurrent.ScheduledExecutorService;

import static net.sayaya.ui.Icon.icon;
import static org.jboss.elemento.Elements.*;

public class Test implements EntryPoint {
	private final HtmlContentBuilder<HTMLDivElement> content = div();
	@Override
	public void onModuleLoad() {
		LayoutTest();
		TestBreadcumb();
		ProgressTest();
		RadioTest();
		TestSwtich();
		AnimationTest();
		TestButton();
		TestCheckBox();
		TestDropDown();
		TestText();
		TestChip();
		TestList();
		// TestDialog();
		// TestGrid();
		/*TestPage();
		TestTab();*/
	}

	void LayoutTest() {
		TopBar.TopBarButton menu = TopBar.buttonNavigation("menu");
		TopBar top = TopBar.topBarFixed()
						   .add(TopBar.section().add(menu).title("Test Top Bar"))
						   .add(TopBar.section().right().add(TopBar.buttonAction("file_download")))
						   .target(content);
		HtmlContentBuilder<HTMLDivElement> div = div().add(top).add(content);
		Drawer drawer = Drawer.drawer().header(Drawer.header()
													 .title(label().add("Mail"))
													 .subtitle(label().add("AAA")))
							  .content(Drawer.content()
											 .header("Mail")
											 .divider()
											 .add(Drawer.item().icon(Icon.icon("inbox")).text("Inbox").activate(true))
											 .add(Drawer.item().icon(Icon.icon("star")).text("Star").activate(true))
											 .add(Drawer.item().icon(Icon.icon("send")).text("Sent Main")))
							  .target(div);
		Elements.body().add(drawer);
		Elements.body().add(div);
		menu.onClick(evt->drawer.toggle());
	}
	void TestBreadcumb() {
		Breadcumb elem = Breadcumb.home(Icon.icon("home"), evt->{}).add("Text", evt->{}, "http://href").add(Icon.icon("article"), evt->{});
		content.add(elem);
	}
	void ProgressTest() {
		ProgressBar elem = ProgressBar.progressBar().determinate(true).buffer(0.5).progress(0.2);
		content.add(elem);
	}
	void RadioTest() {
		Radio<String> a = Radio.radio("RADIO", "A");
		Radio<String> b = Radio.radio("RADIO", "B");
		Radio<String> c = Radio.radio("RADIO", "C");
		Radio<String> d = Radio.radio("RADIO", "D");
		a.onValueChange(evt->{
			DomGlobal.alert(evt.value());
		});
		content.add(a).add(b).add(c).add(d).element();
	}
	void AnimationTest() {
		Button tmp = Button.contain().text("Text Button").style("position: relative");
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

		ChipCheckable chip3 = Chip.check("Chip 3").before(Icon.icon("face")).value(true);
		ChipCheckable chip4 = Chip.check("Chip 4").before(Icon.icon("face"));
		ChipSet chips = ChipSet.filters(chip3, chip4);
		content.add(chips);
		//chips.add(chip3).add(chip4);

	}
	void TestList() {
		ListGroup group = ListGroup.group();
		group.add(ListGroup.header(3).textContent("List Single line"))
			 .add(List.singleLineList()
					  .add(List.singleLine().label("AA").trailing(Switch.sw()))
					  .add(List.singleLine().label("BB"))
					  .add(List.singleLine().label("CC"))
					  .divider()
					  .add(List.singleLine().label("DD"))
					  .add(List.singleLine().label("EE")))
			 .add(ListGroup.header(3).textContent("List Double line"))
			 .add(List.doubleLineList()
					  .add(List.doubleLine().primary("AA"))
					  .add(List.doubleLine().primary("BB").secondary("CCCCCCCCC").leading(Icon.icon("event")))
					  .add(List.doubleLine().primary("CC").secondary("CCCCCCCCC"))
					  .divider()
					  .add(List.doubleLine().primary("DD").secondary("CCCCCCCCC"))
					  .add(List.doubleLine().primary("EE").secondary("CCCCCCCCC")));
		content.add(group);
	}
	private void TestCheckBox() {
		CheckBox checkBox = CheckBox.checkBox(true).text("Test");
		content.add(checkBox);
		checkBox.onValueChange(evt->
			DomGlobal.console.log(evt.value() + ", " + checkBox.value())
		);
	}

	private void TestDropDown() {
		List<List.SingleLineItem> list = List.singleLineList()
				.add(List.singleLine().label("AA").attr("data-value", "A"))
				.add(List.singleLine().label("BB").attr("data-value", "B"))
				.add(List.singleLine().label("CC").attr("data-value", "C"))
				.add(List.singleLine().label("DD").attr("data-value", "D"))
				.add(List.singleLine().label("EE").attr("data-value", "E"));
		DropDown dropdown = DropDown.outlined(list).text("Dropdown").style("width: 300px;");
		content.add(dropdown);
		dropdown.onValueChange(evt->{
			DomGlobal.console.log(dropdown.value());
		});
		List<List.SingleLineItem> list2 = List.singleLineList()
				.add(List.singleLine().label("AA").attr("data-value", "A"))
				.add(List.singleLine().label("BB").attr("data-value", "B"))
				.add(List.singleLine().label("CC").attr("data-value", "C"))
				.add(List.singleLine().label("DD").attr("data-value", "D"))
				.add(List.singleLine().label("EE").attr("data-value", "E"));
		DropDown dropdown2 = DropDown.filled(list2);
		content.add(dropdown2);
		dropdown2.onValueChange(evt->{
			DomGlobal.alert(dropdown2.value());
		});
	}
	void TestButton() {
		Button tmp = Button.contain().text("Text Button");
		HandlerRegistration handler = tmp.onClick(evt->{
			DomGlobal.alert("Hello, World!!");
		});
		content.add(tmp);

		Button tmp2 = Button.flat().text("Button Enabled")
							.before(Icon.icon("sync"))
							.enabled(true);
		content.add(tmp2);

		Button tmp3 = Button.contain().text("Button Disabled").enabled(false);
		content.add(tmp3);

		Button tmp4 = Button.outline().text("Button Focused");
		tmp4.element().accessKey = "A";
		tmp4.element().focus();
		content.add(tmp4);

		Button tmp5 = Button.floating(Icon.icon("add"));
		content.add(tmp5);

		Button tmp6 = Button.floating(Icon.icon("add")).text("Create");
		tmp6.onClick(evt->DomGlobal.alert("Created"));
		content.add(tmp6);

		ButtonToggle tmp7 = Button.toggle().text("Toggle");
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
		TextField<String> tmp1 = TextField.textBox().outlined().text("tmp1").before(Icon.icon("favorite")).value("FFF");
		tmp1.onValueChange(evt->{
		//	DomGlobal.alert(evt.value());
		});
		content.add(tmp1);
		TextField tmp3 = TextField.textBox().outlined().before(icon("title")).text("Title").style("width: 100%;").required(true);
		content.add(tmp3);
		Scheduler.get().scheduleFixedDelay(()->{
			tmp3.value("FFFFFFFFFFFFFFFFF");
			return false;
		}, 1000);
		//content.add(TextField.dateBox().outlined().before(icon("today")).text("Date").required(true).value(new JsDate((double) 1595919979408L)));
		TextField<String> tmp2 = TextField.password().filled().text("tmp2").before(Icon.icon("vpn_key")).trailing(Icon.icon("visibility"));
		tmp2.onValueChange(evt->{
			DomGlobal.console.log(evt.value());
		});
		content.add(tmp2);
		TextArea<String> tmp4 = TextArea.textBox().filled().text("tmp4").before(Icon.icon("vpn_key")).trailing(Icon.icon("visibility"));
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
	void TestGrid() {
		/*Data[] gridData1 = new Data[]{
				new Data().put("id", 549731).put("name", "Beautiful Lies")
						  .put("artist", "Birdy")
						  .put("release", "2016.03.26")
						  .put("type", "Deluxe")
						  .put("typeCode", "1")
						  .put("genre", "Pop")
						  .put("genreCode", "1")
						  .put("grade", "2")
						  .put("price", 10000)
						  .put("downloadCount", 1000)
						.put("listenCount", 5000),
				new Data()
						.put("id", 436461)
						.put("name", "X")
						.put("artist", "Ed Sheeran")
						.put("release", "2014.06.24")
						.put("type", "Deluxe")
						.put("typeCode", "1")
						.put("genre", "Pop")
						.put("genreCode", "1")
						.put("grade", "3")
						.put("price", 20000)
						.put("downloadCount", 1000)
						.put("listenCount", 5000),
				new Data()
						.put("id", 295651)
						.put("name", "Moves Like Jagger")
						.put("release", "2011.08.08")
						.put("artist", "Maroon5")
						.put("type", "Single")
						.put("typeCode", "3")
						.put("genre", "Pop,Rock")
						.put("genreCode", "1,2")
						.put("grade", "2")
						.put("price", 7000)
						.put("downloadCount", 1000)
						.put("listenCount", 5000),
				new Data()
						.put("id", 541713)
						.put("name", "A Head Full Of Dreams")
						.put("artist", "Coldplay")
						.put("release", "2015.12.04")
						.put("type", "Deluxe")
						.put("typeCode", "1")
						.put("genre", "Rock")
						.put("genreCode", "2")
						.put("grade", "3")
						.put("price", 25000)
						.put("downloadCount", 1000)
						.put("listenCount", 5000),
				new Data()
						.put("id", 265289)
						.put("name", "21")
						.put("artist", "Adele")
						.put("release", "2011.01.21")
						.put("type", "Deluxe")
						.put("typeCode", "1")
						.put("genre", "Pop,R&B")
						.put("genreCode", "1,3")
						.put("grade", "3")
						.put("price", 15000)
						.put("downloadCount", 1000)
						.put("listenCount", 5000),
				new Data()
						.put("id", 555871)
						.put("name", "Warm On A Cold Night")
						.put("artist", "HONNE")
						.put("release", "2016.07.22")
						.put("type", "EP")
						.put("typeCode", "1")
						.put("genre", "R&B,Electronic")
						.put("genreCode", "3,4")
						.put("grade", "2")
						.put("price", 11000)
						.put("downloadCount", 1000)
						.put("listenCount", 5000),
				new Data()
						.put("id", 550571)
						.put("name", "Take Me To The Alley")
						.put("artist", "Gregory Porter")
						.put("release", "2016.09.02")
						.put("type", "Deluxe")
						.put("typeCode", "1")
						.put("genre", "Jazz")
						.put("genreCode", "5")
						.put("grade", "3")
						.put("price", 30000)
						.put("downloadCount", 1000)
						.put("listenCount", 5000)
		};
		Grid grid = Grid.builder()
				.scrollY(false).scrollX(false)
				.column(Column.builder(String.class).header("Name").name("name").editor("text").build())
				.column(Column.builder(String.class).header("Artist").name("artist").build())
				.column(Column.builder(String.class).header("Type").name("type").build())
				.column(Column.builder(String.class).header("Release").name("release").build())
				.column(Column.builder(String.class).header("Genre").name("genre").build())
				.data(gridData1).build();
		content.add(grid);*/
	}
	private void TestPage() {
		Page page = Page.instance().idx(0).show(30).total(1000);
		content.add(page);
	}

	private void TestSwtich() {
		Switch sw = Switch.sw();
		content.add(sw);
		sw.onValueChange(evt->{
			DomGlobal.alert(evt.value() + "!");
		});
	}
	private void TestTab() {
		TabBar tab = TabBar.tabBar(TabBar.tab().text("Favorites").icon("favorite"), TabBar.tab().text("Favorites").icon("favorite"), TabBar.tab().text("Favorites").icon("favorite")).activate(0);
		content.add(tab);
		tab.onValueChange(evt->{
			DomGlobal.alert(evt.value());
		});
	}
}
