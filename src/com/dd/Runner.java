package com.dd;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dd.darkest.DarkestEncoder;
import com.dd.darkest.DarkestFile;
import com.dd.darkest.DarkestParser;

public class Runner {
	// /////////////////EDIT ME//////////////////////////////
	public static void main(String[] args) throws IOException {
		List<GameplayChange> gameplayChanges = new ArrayList<GameplayChange>();
		gameplayChanges.add(new MultiplyAllDamageAndHealth());

		Runner runner = new Runner();
		runner.run("E:/steam/steamapps/common/DarkestDungeon/",
				gameplayChanges, false);
	}

	// //////////////////////////////////////////////////////

	public void run(String installationDirectory,
			List<GameplayChange> gameplayChanges, boolean dryrun)
			throws IOException {
		List<Backup> backups = Backup.createBackups(new File(
				installationDirectory), ".darkest");
		process(backups, gameplayChanges, dryrun);
	}

	public void process(List<Backup> backups,
			List<GameplayChange> gameplayChanges, boolean dryrun)
			throws IOException {
		Map<File, DarkestFile> files = readDarkestFiles(backups);
		makeGameplayChanges(files, gameplayChanges);
		writeDarkestFiles(files, dryrun);
	}

	protected static Map<File, DarkestFile> readDarkestFiles(
			List<Backup> backups) throws IOException {
		Map<File, DarkestFile> darkestFiles = new HashMap<File, DarkestFile>();

		for (Backup backup : backups) {

			byte[] oldBytes = Files.readAllBytes(backup.getBackupFile()
					.toPath());
			DarkestParser parser = new DarkestParser();
			DarkestFile darkestFile = parser
					.parse(new String(oldBytes, "UTF-8"));
			darkestFiles.put(backup.getGameFile(), darkestFile);
		}
		return darkestFiles;
	}

	protected static void writeDarkestFiles(
			Map<File, DarkestFile> darkestFiles, boolean dryrun)
			throws FileNotFoundException {

		if (dryrun)
			return;

		for (File darkestFile : darkestFiles.keySet()) {
			System.out.println("Writing:   " + darkestFile);

			DarkestEncoder encoder = new DarkestEncoder();
			String result = encoder.marshall(darkestFiles.get(darkestFile));

			PrintWriter newWriter = new PrintWriter(darkestFile);
			newWriter.write(result);
			newWriter.flush();
			newWriter.close();
		}
	}

	protected void makeGameplayChanges(Map<File, DarkestFile> files,
			List<GameplayChange> gameplayChanges) {
		for (GameplayChange change : gameplayChanges)
			change.makeGameplayChanges(files);
	}
}