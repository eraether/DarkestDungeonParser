package com.dd.darkest;

public abstract class DarkestNode {
	protected DarkestNodeType nodeType = DarkestNodeType.UNKNOWN_NODE_TYPE;

	public DarkestNodeType getNodeType() {
		return this.nodeType;
	}
}