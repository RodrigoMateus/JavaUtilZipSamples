package main;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/* http://www.mkyong.com/java/how-to-compress-files-in-zip-format/ */

public class ZipFile {

	public static void main(String[] args) {
		byte[] buffer = new byte[1024];

		try {

			FileOutputStream fos = new FileOutputStream("D:/MyFile.zip");
			ZipOutputStream zos = new ZipOutputStream(fos);
			ZipEntry ze = new ZipEntry("test.txt");
			zos.putNextEntry(ze);
			FileInputStream in = new FileInputStream("D:/test.txt");

			int len;
			while ((len = in.read(buffer)) > 0) {
				zos.write(buffer, 0, len);
			}

			in.close();
			zos.closeEntry();

			// remember close it
			zos.close();

			System.out.println("Done");

		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}