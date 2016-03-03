package com.dd;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.dd.darkest.DarkestFile;
import com.dd.darkest.DarkestProperty;
import com.dd.darkest.DarkestPropertyNode;

public class MultiplyAllDamageAndHealth extends GameplayChange {

	@Override
	public void makeGameplayChanges(Map<File, DarkestFile> darkestFiles) {
		for (File physicalFile : darkestFiles.keySet()) {
			DarkestFile darkestFile = darkestFiles.get(physicalFile);

			double multiplicationAmount = 1000;
			multiplyAllDamageAndHealthValues(darkestFile, multiplicationAmount,
					multiplicationAmount, multiplicationAmount,
					multiplicationAmount, multiplicationAmount);
		}
	}

	private String multiplyIntegerListProperty(DarkestProperty property,
			double damageAmount) {
		List<Integer> integerList = parsePropertyIntegerList(property
				.getValue());
		if (integerList == null) {
			return property.getValue();
		}

		List<Integer> multipliedValues = new ArrayList<Integer>();
		for (Integer i : integerList) {
			multipliedValues.add((int) Math.round(i * damageAmount));
		}
		return integerListToDarkestPropertyValue(multipliedValues);
	}

	private void multiplyAllDamageAndHealthValues(DarkestFile darkestFile,
			double damageAmount, double healthAmount, double blightAmount,
			double bleedAmount, double healAmount) {
		for (DarkestPropertyNode node : darkestFile.getAllPropertyNodes()) {
			for (DarkestProperty property : node.getAllProperties()) {
				if (property.getName().equals("dmg")) {
					property.setValue(multiplyIntegerListProperty(property,
							damageAmount));
				} else if (property.getName().equals("hp")) {
					property.setValue(multiplyIntegerListProperty(property,
							healthAmount));
				} else if (property.getName().equals("dotPoison")) {
					property.setValue(multiplyIntegerListProperty(property,
							blightAmount));
				} else if (property.getName().equals("dotBleed")) {
					property.setValue(multiplyIntegerListProperty(property,
							bleedAmount));
				} else if (property.getName().equals("heal")) {
					property.setValue(multiplyIntegerListProperty(property,
							healAmount));
				}
			}
		}
	}

}
