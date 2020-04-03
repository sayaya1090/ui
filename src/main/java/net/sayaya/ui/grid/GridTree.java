package net.sayaya.ui.grid;

import elemental2.dom.HTMLDivElement;
import net.sayaya.ui.IsHTMLElement;

public class GridTree extends Grid {
	GridTree(GridBuilder builder) {
		super(builder);
	}
	public GridTree expand() {
		return this;
	}
}

