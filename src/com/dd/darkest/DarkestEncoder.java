package com.dd.darkest;

public class DarkestEncoder {
	public String marshall(DarkestFile f) {
		StringBuilder builder = new StringBuilder();

		for (DarkestNode node : f.getAllNodes()) {
			switch (node.getNodeType()) {
			case EMPTY_NODE:
				builder.append(marshallEmptyNode((DarkestEmptyNode) node));
				break;
			case COMMENT_NODE:
				builder.append(marshallCommentNode((DarkestCommentNode) node)
						+ "\n");
				break;
			case DARKEST_PROPERTY_NODE:
				builder.append(marshallPropertyNode((DarkestPropertyNode) node)
						+ "\n\n");
				break;
			default:
				System.out.println("Unknown node type:" + node.getNodeType());
				break;
			}
		}

		return builder.toString();
	}

	private String marshallEmptyNode(DarkestEmptyNode node) {
		return "";// node.getWhitespace();
	}

	private String marshallCommentNode(DarkestCommentNode node) {
		return node.getComment();
	}

	private String marshallPropertyNode(DarkestPropertyNode node) {
		String out = "";
		out += node.getName() + ":";
		for (DarkestProperty property : node.getAllProperties()) {
			out += "\n\t" + "." + property.getName() + " "
					+ property.getValue();
		}
		return out;
	}

}