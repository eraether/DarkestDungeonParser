package com.dd.darkest;

// informationless whitespace
public class DarkestEmptyNode extends DarkestNode {
	private String whitespace;

	public DarkestEmptyNode(String whitespace) {
		this.nodeType = DarkestNodeType.EMPTY_NODE;
		this.whitespace = whitespace;
	}

	public String getWhitespace() {
		return this.whitespace;
	}
}