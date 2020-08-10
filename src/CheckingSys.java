import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class CheckingSys {
	private String username;
	private String password;
	private int permission;
	private ArrayList<String> names;
	private user user;
	private boolean pass = false;
	private boolean inlist = false;
	
	public CheckingSys(JTextField text1, JPasswordField text2) throws IOException {
		System.getProperty("file.encoding", "UTF-8");
		names = new ArrayList<>();
		readlist();
		username = text1.getText();
		password = text2.getText();
		//System.out.println(username);
		//System.out.println(password);
		if(inlist(username)) {
			user = new user(username + ".txt");
			inlist = true;
		}
		if(inlist == true && password.equals(user.getpass())) {
			pass = true;
		}
	}

	public user giveUser() {
		return user;
	}
	
	private boolean inlist(String str) {
		for(String s : names) {
			if(str.contentEquals(s)) {
				//System.out.println("User exists");
				return true;
			}
		}
		return false;
	}
	
	public boolean pass() {
		return pass;
	}
	
	public boolean getinlist() {
		return inlist;
	}
	
	private void readlist() throws IOException {
		InputStream fis = null;
		InputStreamReader isr = null;
		ArrayList<String> list = new ArrayList<String>();
		try {
			// new input stream reader is created 
			fis = this.getClass().getResourceAsStream("resources/userlist.txt");
			//System.out.println(fis);
			isr = new InputStreamReader(fis,"UTF-8");
			String str = "";
			// read till the end of the file
			BufferedReader in = new BufferedReader(isr);
			while((str = in.readLine()) != null ) {
				//System.out.println(str);
				list.add(str);
			}		
			//System.out.println(str);
			names.addAll(list);	
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
	}

}

