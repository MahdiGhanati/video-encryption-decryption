package video_en_decrypt;

import java.io.*;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.*;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

public class encrypt {

	public static void main(SecretKey key, AlgorithmParameterSpec paramSpec, InputStream in, OutputStream out)
			throws Exception {
		try {
			byte[] IV = new byte[] { (byte) 0x90, 0x05, 0x40, (byte) 0x9E, 0x70, 0x5A, 0x6B, 0x04, (byte) 0x8B, 0x11,
					0x3C, (byte) 0x11, 0x1F, 0x38, 0x52, 0x4A };
			AlgorithmParameterSpec parameterSpec = new IvParameterSpec(IV); // Initialization vector
			Cipher c = Cipher.getInstance("AES/CFB/PKCS5Padding"); // Using CFB as a mode of operation
			c.init(Cipher.ENCRYPT_MODE, key, parameterSpec); // Encrypt the video
			out = new CipherOutputStream(out, c);
			int counter = 0;
			byte[] buffer = new byte[1024];
			while ((counter = in.read(buffer)) >= 0) {
				out.write(buffer, 0, counter);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			out.close();
		}

	}
}