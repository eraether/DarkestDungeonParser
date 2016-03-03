package com.dd;

import com.dd.darkest.DarkestFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class GameplayChange {
	public abstract void makeGameplayChanges(Map<File, DarkestFile> darkestFiles);

	protected String integerListToDarkestPropertyValue(List<Integer> ints) {
		List<String> toStrs = new ArrayList<String>();
		for (Integer i : ints) {
			toStrs.add(i.toString());
		}
		return String.join(" ", toStrs);
	}

	protected List<Integer> parsePropertyIntegerList(String propertyValue) {
		List<Integer> integerValues = new ArrayList<Integer>();
		String[] values = propertyValue.split("\\s");
		for (String value : values) {
			try {
				integerValues.add(Integer.parseInt(value));
			} catch (Exception e) {
				integerValues = null;
				break;
			}
		}

		return integerValues;
	}
}
