package com.dd.darkest;
import java.util.ArrayList;
import java.util.List;


public class DarkestFile {
	private List<DarkestNode> nodes;

	public DarkestFile() {
		this(new ArrayList<DarkestNode>());
	}

	public DarkestFile(List<DarkestNode> nodes) {
		this.nodes = nodes;
	}

	public List<DarkestNode> getAllNodes() {
		return this.nodes;
	}

	public List<DarkestPropertyNode> getAllPropertyNodes() {
		List<DarkestPropertyNode> matchedNodes = new ArrayList<DarkestPropertyNode>();
		for (DarkestNode node : this.nodes) {
			if (node.getNodeType()
					.equals(DarkestNodeType.DARKEST_PROPERTY_NODE))
				matchedNodes.add((DarkestPropertyNode) node);
		}
		return matchedNodes;
	}

}