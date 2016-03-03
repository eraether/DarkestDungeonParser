package com.dd.darkest;
import java.util.List;


public class DarkestPropertyNode extends DarkestNode {
	private String nodeName;
	private List<DarkestProperty> properties;

	public DarkestPropertyNode(String nodeName, List<DarkestProperty> properties) {
		this.nodeType = DarkestNodeType.DARKEST_PROPERTY_NODE;
		this.nodeName = nodeName;
		this.properties = properties;
	}

	public List<DarkestProperty> getAllProperties() {
		return this.properties;
	}

	public String getName() {
		return this.nodeName;
	}
}