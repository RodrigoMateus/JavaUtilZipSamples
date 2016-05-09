package main;

import java.util.Arrays;
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
			byte[] inputArray = inputString.getBytes("UTF-8");
			System.out.println("input: " + Hex.encodeHexString(inputArray));

			// Compress the bytes
			byte[] compresserOutput = new byte[2048];
			Deflater compresser = new Deflater();
			compresser.setInput(inputArray);
			compresser.finish();
			int compressedOutputLength = compresser.deflate(compresserOutput);
			byte[] compactData = Arrays.copyOfRange(compresserOutput, 0, compressedOutputLength);

			System.out.println("compresserOutput: " + Hex.encodeHexString(compresserOutput));
			System.out.println("compactData: " + Hex.encodeHexString(compactData));

//			Path path = Paths.get("string_de_teste_compactada.qzip");
//			byte[] data = Files.readAllBytes(path);
//			System.out.println("string_de_teste_compactada: " + Hex.encodeHexString(data));

			// Decompress the bytes
			Inflater decompresser = new Inflater();
			decompresser.setInput(compactData);
			byte[] decompresserOutput = new byte[2048];
			int decompresserOutputLength = decompresser.inflate(decompresserOutput);
			decompresser.end();
			byte[] decompressedData = Arrays.copyOfRange(decompresserOutput, 0, decompresserOutputLength);

			// Decode the bytes into a String
			System.out.println("decompressedData: " + Hex.encodeHexString(decompressedData));
			String outputString = new String(decompressedData);
			System.out.println("String original: " + outputString);
		} catch (java.io.UnsupportedEncodingException ex) {
			ex.printStackTrace();
		} catch (java.util.zip.DataFormatException ex) {
			ex.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
