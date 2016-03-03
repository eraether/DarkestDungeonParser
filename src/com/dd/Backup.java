package com.dd;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

class Backup {
	private File backupFile;
	private File gameFile;

	public final static String BACKUP_EXT = "_old";

	public Backup(File backupFile, File actualGameFile) {
		this.backupFile = backupFile;
		this.gameFile = actualGameFile;

		if (backupFile.getName().endsWith(BACKUP_EXT) == false) {
			throw new RuntimeException("Back up file must end with "
					+ BACKUP_EXT);
		}
	}

	public File getBackupFile() {
		return this.backupFile;
	}

	public File getGameFile() {
		return this.gameFile;
	}

	/*
	 * the .darkest_old files this method generates are what become the source
	 * of truth. the .darkest files from this point onward are dirty.
	 */
	public static List<Backup> createBackups(File installationFolder,
			String extension) throws IOException {
		List<Backup> fileMapping = new ArrayList<Backup>();

		Collection<File> files = findFiles(installationFolder, extension);
		for (File f : files) {
			File oldFile = new File(f.getCanonicalFile() + Backup.BACKUP_EXT);
			if (!oldFile.exists()) {
				byte[] oldBytes = Files.readAllBytes(f.toPath());
				FileOutputStream fos = new FileOutputStream(oldFile);
				fos.write(oldBytes);
				fos.flush();
				fos.close();
			}
			fileMapping.add(new Backup(oldFile, f));
		}
		return fileMapping;
	}

	protected static Collection<File> findFiles(File root, String extension) {
		Collection<File> matchedFiles = new ArrayList<File>();

		File[] files = root.listFiles();
		for (File file : files) {
			if (file.isFile()) {
				if ((extension != null && file.getName().endsWith(extension))
						|| extension == null) {
					matchedFiles.add(file);
				}
			} else if (file.isDirectory()) {
				matchedFiles.addAll(findFiles(file, extension));
			}
		}
		return matchedFiles;
	}
}