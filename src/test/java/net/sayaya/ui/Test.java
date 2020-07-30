package net.sayaya.ui;

import com.google.gwt.core.client.*;
import com.google.gwt.user.client.Random;
import elemental2.dom.*;
import jsinterop.base.JsPropertyMap;
import lombok.Setter;
import lombok.experimental.Accessors;
import net.sayaya.ui.grid.*;
import net.sayaya.ui.layout.GridLayoutResponsive;
import org.gwtproject.event.shared.HandlerRegistration;
import org.jboss.elemento.Elements;
import org.jboss.elemento.InputType;
import org.jboss.elemento.HtmlContentBuilder;

import java.util.Date;

import static org.jboss.elemento.Elements.*;

public class Test implements EntryPoint {
	private final HtmlContentBuilder<HTMLDivElement> content = div();
	@Override
	public void onModuleLoad() {
		LayoutTest();
		ProgressTest();
		RadioTest();
		AnimationTest();
		TestButton();
		TestText();
		TestChip();
		TestList();
		TestGrid();
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
		ChipSet chips = ChipSet.chips();
		content.add(chips);
		chips.onValueChange(evt->DomGlobal.alert("Chipset value changed"));
		Chip chip = Chip.chip("Chip 1").removable();
		chip.onDetach(evt->DomGlobal.alert("Remove Chip 1"));
		chips.add(chip);
		Chip chip2 = Chip.chip("Chip 2").removable();
		chip2.onDetach(evt->DomGlobal.alert("Remove Chip 2"));
		chips.add(chip2);
		Chip chip3 = Chip.chip("Chip 3").removable();
		chip3.onDetach(evt->DomGlobal.alert("Remove Chip 3"));
		chips.add(chip3);

	}
	void TestList() {
		List list = List.singleLineList()
				.add(List.item().label("AA"))
				.add(List.item().label("BB", "CCCCCCCCC"))
				.add(List.item().label("CC", "CCCCCCCCC"))
				.add(List.divider())
				.add(List.item().label("DD", "CCCCCCCCC").selectable(true))
				.add(List.item().label("EE", "CCCCCCCCC").selectable(true));
		content.add(list);
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
	}

	void TestText() {
		TextField<String> tmp1 = TextField.textBox().outlined().text("tmp1").before(Icon.icon("favorite"));
		tmp1.onValueChange(evt->{
		//	DomGlobal.alert(evt.value());
		});
		content.add(tmp1);
		TextField<String> tmp2 = TextField.password().filled().text("tmp2").before(Icon.icon("vpn_key")).trailing(Icon.icon("visibility"));
		tmp2.onValueChange(evt->{
		//	DomGlobal.alert(evt.value());
		});
		content.add(tmp2);
	}
	void TestGrid() {
		Data[] gridData1 = new Data[]{
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
		content.add(grid);
	}
}
