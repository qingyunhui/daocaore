package test02;

import java.io.File;
import java.io.FileOutputStream;

import javax.servlet.http.HttpServlet;

public class Tes {

	public static void main(String[] args) {
		File file = new File("c:/11.json");
		String c = "222222222222222222222";
		try (FileOutputStream oStream = new FileOutputStream(file)){
			if (!file.exists()) {
				file.createNewFile();
			}
			oStream.write(c.getBytes());
			oStream.flush();
			oStream.close();
		} catch (Exception e) {
		}
		System.out.println("ok");
		//HttpServlet
	}

}
