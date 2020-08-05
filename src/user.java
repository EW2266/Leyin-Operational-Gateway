import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.ArrayList;

public class user {
	private File userdata; 
	private String username;
	private String password;
	private int permission;

	public user(String name) throws IOException {
		read(name);
	}

	private void read(String str)  throws IOException{
		System.getProperty("file.encoding", "UTF-8");
		InputStream fis = null;
		InputStreamReader isr = null;
		String data = "";
		String filename = "resources/users/" + str;
		System.out.println(filename);
		try {
			// new input stream reader is created 
			// read till the end of the file
			fis = this.getClass().getResourceAsStream(filename);
			System.out.println(fis);
			str = "";
			BufferedReader in = new BufferedReader(new InputStreamReader(fis, "UTF8"));
			while((str = in.readLine()) != null ) {
				data += (str);
			}

		}catch (Exception e) {
			// print error
			e.printStackTrace();
		} finally {
			// closes the stream and releases resources associated
			if(fis!=null)
				fis.close();
			if(isr!=null)
				isr.close();
		}
		
		username = data.substring(data.indexOf("Username : ") + 11 , (data.indexOf("Password : "))); 
		System.out.println(username);
		data = data.substring(data.indexOf("Password : "));
		password = data.substring(data.lastIndexOf("Password : ") + 11, (data.indexOf("Permission")));
		System.out.println(password);
		permission = Integer.parseInt(data.substring(data.length() - 1));
		System.out.println(permission);
	}
	
	public String getpass() {
		return password;
	}
	
	public String getname() {
		return username;
	}
	
	public int getperm() {
		return permission;
	}
}
