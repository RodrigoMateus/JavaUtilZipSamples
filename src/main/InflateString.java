package main;

import java.util.zip.Deflater;
import java.util.zip.Inflater;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import org.apache.commons.codec.binary.Hex;

public class InflateString {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {

			// Encode a String into bytes
			String inputString = "String compactada pela QuaZIP";
			byte[] input = inputString.getBytes("UTF-8");
			System.out.println("input: " + Hex.encodeHexString(input));

			// Compress the bytes
			byte[] output = new byte[2048];
			Deflater compresser = new Deflater();
			compresser.setInput(input);
			compresser.finish();
			int compressedDataLength = compresser.deflate(output);
			System.out.println("output: " + Hex.encodeHexString(output));

//			Path path = Paths.get("D:/string_de_teste_compactada.zip");
//			byte[] data = Files.readAllBytes(path);
//			System.out.println("data: " + Hex.encodeHexString(data));

			// Decompress the bytes
			Inflater decompresser = new Inflater();
			decompresser.setInput(output, 0, compressedDataLength);
//			decompresser.setInput(data, 0, data.length);
			byte[] result = new byte[2048];
			int resultLength = decompresser.inflate(result);
			decompresser.end();

			// Decode the bytes into a String
			String outputString = new String(result, 0, resultLength, "UTF-8");
			System.out.println(outputString);
		} catch (java.io.UnsupportedEncodingException ex) {
			ex.printStackTrace();
		} catch (java.util.zip.DataFormatException ex) {
			ex.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
