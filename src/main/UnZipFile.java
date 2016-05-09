package main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/* http://www.mkyong.com/java/how-to-decompress-files-from-a-zip-file/ */

public class UnZipFile {

	List<String> fileList;
	private static final String INPUT_ZIP_FILE = "string_de_teste_compactada.qzip";
	private static final String OUTPUT_FOLDER = "./unziped";

	public static void main(String[] args) {
		UnZipFile unZipFile = new UnZipFile();
		unZipFile.unZipIt(INPUT_ZIP_FILE, OUTPUT_FOLDER);
	}

	public void unZipIt(String zipFile, String outputFolder) {

		byte[] buffer = new byte[1024];

		try {

			// create output directory is not exists
			File folder = new File(OUTPUT_FOLDER);
			if (!folder.exists()) {
				folder.mkdir();
			}

			// get the zip file content
			ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(zipFile));
			// get the zipped file list entry
			ZipEntry zipEntry = zipInputStream.getNextEntry();

			while (zipEntry != null) {

				String fileName = zipEntry.getName();
				File newFile = new File(outputFolder + File.separator + fileName);

				System.out.println("file unzip : " + newFile.getAbsoluteFile());

				// create all non exists folders
				// else you will hit FileNotFoundException for compressed folder
				new File(newFile.getParent()).mkdirs();

				FileOutputStream fileOutputStream = new FileOutputStream(newFile);

				int len;
				while ((len = zipInputStream.read(buffer)) > 0) {
					fileOutputStream.write(buffer, 0, len);
				}

				fileOutputStream.close();
				zipEntry = zipInputStream.getNextEntry();
			}

			zipInputStream.closeEntry();
			zipInputStream.close();

			System.out.println("Done");

		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}