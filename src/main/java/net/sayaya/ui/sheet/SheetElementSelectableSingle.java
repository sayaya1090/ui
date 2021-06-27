package net.sayaya.ui.sheet;

import com.google.gwt.user.client.Random;
import elemental2.core.JsArray;
import elemental2.dom.*;
import net.sayaya.ui.event.HasSelectionChangeHandlers;
import org.gwtproject.event.shared.HandlerRegistration;

import java.util.Arrays;
import java.util.Optional;

import static org.jboss.elemento.EventType.bind;

public interface SheetElementSelectableSingle extends HasSelectionChangeHandlers<Optional<Data>> {
	Data[] value();
	@Override
	default Optional<Data> selection() {
		return Arrays.stream(value())
				.filter(d->d.state()==Data.DataState.SELECTED)
				.findAny();
	}
	static void header(SheetElement sheetElement) {
		SheetElement.SheetConfiguration config = sheetElement.configuration();
		config.afterGetRowHeaderRenderers(renderers->{
			Data[] data = config.data();
			String id = "Sheet-row-select-" + Random.nextInt();
			JsArray.asJsArray(renderers).push((row, TH)->{
				if(data[row]==null) return;
				boolean checked = data[row].state() == Data.DataState.SELECTED;
				TH.classList.add("row-header-checkbox");
				TH.style.cursor = "pointer";
				TH.innerHTML = "<input name='" + id + "' " +
						"idx='" + data[row].idx() + "' " + (checked?"checked ":"") +
						"type='radio' style='vertical-align: middle; margin: 0px;'/>";
			});
		}).rowHeaderWidth(30);
		sheetElement.element().addEventListener("click", evt->{
			HTMLElement target = (HTMLElement) evt.target;
			if(target.classList.contains("row-header-checkbox")) {
				HTMLTableCellElement th = (HTMLTableCellElement)target;
				HTMLInputElement radio = (HTMLInputElement) th.firstElementChild;
				radio.checked = true;
				String idx = radio.getAttribute("idx");
				Arrays.stream(config.data()).forEach(d->d.select(false));
				Arrays.stream(config.data()).filter(d->idx.equals(d.idx())).findAny().get().select(true);
				sheetElement.element().dispatchEvent(new CustomEvent("selection-change"));
			} else if(target.parentElement!=null && target.parentElement.classList.contains("row-header-checkbox")) {
				HTMLInputElement radio = (HTMLInputElement) target;
				radio.checked = true;
				String idx = radio.getAttribute("idx");
				Arrays.stream(config.data()).forEach(d->d.select(false));
				Arrays.stream(config.data()).filter(d->idx.equals(d.idx())).findAny().get().select(true);
				sheetElement.element().dispatchEvent(new CustomEvent("selection-change"));
			}
		});
	}
	@Override
	default HandlerRegistration onSelectionChange(EventTarget dom, SelectionChangeEventListener<Optional<Data>> listener) {
		EventListener wrapper = evt->listener.handle(SelectionChangeEvent.event(evt, selection()));
		return bind(dom, "selection-change", wrapper);
	}
}
