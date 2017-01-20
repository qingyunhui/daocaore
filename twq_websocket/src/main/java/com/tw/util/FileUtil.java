package com.tw.util;

import java.io.File;
import java.io.FileOutputStream;

public class FileUtil {

	public static void create(String s,String path) {
		File file = new File(path+"/g.json");
		try (FileOutputStream oStream = new FileOutputStream(file)){
			if (!file.exists()) {
				file.createNewFile();
			}
			oStream.write(s.getBytes());
			oStream.flush();
			oStream.close();
		} catch (Exception e) {
		}
	}
}
