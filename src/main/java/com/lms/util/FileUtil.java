package com.lms.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FileUtil {
	
	public static String getFileData(String fileWithPath) throws IOException {
		File file = new File(fileWithPath);
		FileInputStream fin = new FileInputStream(file);
		return getFileData(fin);
	}
	
	public static String getFileData(InputStream in) throws IOException {
		String stringData = null;
		try (BufferedReader br = new BufferedReader(new InputStreamReader(in))) {
			String line = null;
			StringBuffer sb = new StringBuffer();
			while((line = br.readLine()) != null) {
				sb.append(line);
			}
			stringData = sb.toString();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		return stringData;
	}

}
