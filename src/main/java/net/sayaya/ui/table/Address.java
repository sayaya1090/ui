package net.sayaya.ui.table;

import lombok.Data;

@Data
public final class Address implements Comparable<Address> {
	private int row;
	private int col;

	@Override
	public int compareTo(Address other) {
		if(row < other.row) return -1;
		else if(row > other.row) return 1;
		else return Integer.compare(col, other.col);
	}
}
