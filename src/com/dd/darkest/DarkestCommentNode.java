package com.dd.darkest;

public class DarkestCommentNode extends DarkestNode {
	private String comment;

	public DarkestCommentNode(String comment) {
		this.nodeType = DarkestNodeType.COMMENT_NODE;
		this.comment = comment;
	}

	public String getComment() {
		return this.comment;
	}
}