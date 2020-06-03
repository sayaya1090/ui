package net.sayaya.ui.layout;

import elemental2.dom.DomGlobal;
import lombok.*;
import org.gwtproject.event.shared.HandlerRegistration;

import java.util.LinkedList;
import java.util.List;

public class GridLayoutResponsive {
	private final static GridLayoutResponsive instance = new GridLayoutResponsive();
	private final List<GridChangeEventHandler> handlers = new LinkedList<>();
	private Integer gutterWidth = null;
	private Integer marginWidth = null;
	private boolean running = false;
	private GridLayoutResponsive(){
		DomGlobal.window.addEventListener("resize", evt->{
			if(running) return;
			running = true;
			GridInfo current = calculateGrid(DomGlobal.window.innerWidth);
			fire(new GridChangeEvent(current));
			running = false;
		});
	}
	private GridInfo calculateGrid(int width) {
		int columnCount;
		int gutterWidth;
		int marginWidth;
		if(width < 360) {
			columnCount = 4;
			gutterWidth = asDefault(this.gutterWidth, 16);
			marginWidth = asDefault(this.marginWidth, 16);
		} else if(width < 400) {
			columnCount = 4;
			gutterWidth = asDefault(this.gutterWidth, 16);
			marginWidth = asDefault(this.marginWidth, 16);
		} else if(width < 480) {
			columnCount = 4;
			gutterWidth = asDefault(this.gutterWidth, 16);
			marginWidth = asDefault(this.marginWidth, 16);
		} else if(width < 600) {
			columnCount = 4;
			gutterWidth = asDefault(this.gutterWidth, 16);
			marginWidth = asDefault(this.marginWidth, 16);
		} else if(width < 720) {
			columnCount = 8;
			gutterWidth = asDefault(this.gutterWidth, 16);
			marginWidth = asDefault(this.marginWidth, 16);
		} else if(width < 840) {
			columnCount = 8;
			gutterWidth = asDefault(this.gutterWidth, 24);
			marginWidth = asDefault(this.marginWidth, 24);
		} else if(width < 960) {
			columnCount = 12;
			gutterWidth = asDefault(this.gutterWidth, 24);
			marginWidth = asDefault(this.marginWidth, 24);
		} else if(width < 1024) {
			columnCount = 12;
			gutterWidth = asDefault(this.gutterWidth, 24);
			marginWidth = asDefault(this.marginWidth, 24);
		} else if(width < 1440) {
			columnCount = 12;
			gutterWidth = asDefault(this.gutterWidth, 24);
			marginWidth = asDefault(this.marginWidth, 24);
		} else if(width < 1600) {
			columnCount = 12;
			gutterWidth = asDefault(this.gutterWidth, 24);
			marginWidth = asDefault(this.marginWidth, 24);
		} else {
			columnCount = 12;
			gutterWidth = asDefault(this.gutterWidth, 24);
			marginWidth = asDefault(this.marginWidth, 24);
		}
		return new GridInfoImpl(width, columnCount, gutterWidth, marginWidth);
	}
	private int asDefault(Integer setting, int _default) {
		if(setting!=null) return setting;
		else return _default;
	}
	public static void clear() {
		instance.handlers.clear();
	}
	public static HandlerRegistration addHandler(GridChangeEventHandler handler) {
		instance.handlers.add(handler);
		return ()->instance.handlers.remove(handler);
	}
	private void fire(GridChangeEvent event) {
		for(GridChangeEventHandler listener : handlers) listener.onChange(event);
	}

	public interface GridChangeEventHandler {
		void onChange(GridChangeEvent evt);
	}
	@Getter
	public final static class GridChangeEvent {
		private GridInfo gridInfo;
		protected GridChangeEvent(GridInfo value) {
			this.gridInfo = value;
		}
	}
	@RequiredArgsConstructor
	@EqualsAndHashCode
	@ToString
	private final class GridInfoImpl implements GridInfo {
		private final int totalWidth;
		private final int columnCount;
		private final int gutterWidth;
		private final int marginWidth;
		private Double columnWidth;

		@Override
		public int totalWidth() {
			return totalWidth;
		}

		@Override
		public int columnCount() {
			return columnCount;
		}

		@Override
		public double columnWidth() {
			if(columnWidth == null) {
				int gutterCount = columnCount() - 1;
				columnWidth = (totalWidth() - 2*marginWidth() - gutterCount*gutterWidth()) / (double)columnCount();
			}
			return columnWidth;
		}

		@Override
		public int gutterWidth() {
			return gutterWidth;
		}
		@Override
		public int marginWidth() {
			return marginWidth;
		}
	}
	public interface GridInfo {
		int totalWidth();
		int columnCount();
		default double columnWidth() {
			int gutterCount = columnCount() - 1;
			return (totalWidth() - 2*marginWidth() - gutterCount*gutterWidth()) / (double)columnCount();
		}
		int gutterWidth();
		int marginWidth();
	}
}
