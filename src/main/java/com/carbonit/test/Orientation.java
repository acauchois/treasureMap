package com.carbonit.test;

public enum Orientation {

	N("O", "E"), S("E", "O"), E("N", "S"), O("S", "N");

	private String left;
	private String right;

	Orientation(String left, String right) {
		this.left = left;
		this.right = right;
	}

	public Orientation getLeft() {
		return Orientation.valueOf(left);
	}

	public Orientation getRight() {
		return Orientation.valueOf(right);
	}

}
