package com.dd.darkest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class DarkestParser {
	public DarkestFile parse(String s) {

		List<DarkestNode> darkestNodes = new ArrayList<DarkestNode>();

		String[] lines = s.split("\n");

		List<String> temp = new ArrayList<String>();

		for (String line : lines) {
			String trimmedLine = line.trim();
			if (trimmedLine.equals("")) {
				darkestNodes.add(processEmptyNode(line));
				continue;
			}

			if (trimmedLine.startsWith("//")) {
				darkestNodes.add(processCommentNode(line));
				continue;
			}

			if (line.contains(":")) {
				if (temp.isEmpty() == false) {
					darkestNodes.add(processPropertyNode(temp));
					temp.clear();
				}
			}
			temp.add(line);
		}

		if (temp.isEmpty() == false) {
			darkestNodes.add(processPropertyNode(temp));
		}

		return new DarkestFile(darkestNodes);
	}

	private DarkestEmptyNode processEmptyNode(String whitespace) {
		return new DarkestEmptyNode(whitespace);
	}

	private DarkestCommentNode processCommentNode(String comment) {
		return new DarkestCommentNode(comment.trim());
	}

	private DarkestPropertyNode processPropertyNode(List<String> strings) {
		String unprocessedNode = String.join("\n", strings);

		int colonIndex = unprocessedNode.indexOf(":");
		if (colonIndex == -1) {
			System.out.println("Failure imminent!");
			System.out.println(unprocessedNode);
		}

		String nodeName = unprocessedNode.substring(0, colonIndex).trim();
		String unprocessedProperties = unprocessedNode
				.substring(colonIndex + 1);

		List<DarkestProperty> properties = processProperties(unprocessedProperties);
		DarkestPropertyNode node = new DarkestPropertyNode(nodeName, properties);
		return node;
	}

	private List<DarkestProperty> processProperties(
			String unparsedPropertiesString) {
		List<DarkestProperty> properties = new ArrayList<DarkestProperty>();

		String[] lines = unparsedPropertiesString.split("\\s\\.");
		for (String line : lines) {
			line = line.trim();
			if (line.equals(""))
				continue;
			if (line.startsWith("//"))
				continue;
			String[] values = line.split("\\s+");
			List<String> propertyValues = new ArrayList<String>();
			propertyValues.addAll(Arrays.asList(values));
			String propertyName = propertyValues.remove(0);
			properties.add(new DarkestProperty(propertyName, String.join(" ",
					propertyValues)));
		}

		return properties;
	}
}