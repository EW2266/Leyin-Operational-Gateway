package login;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.GetObjectRequest;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.OSSObjectSummary;
import com.aliyun.oss.model.ObjectListing;
import com.aliyun.oss.model.PutObjectRequest;

public class Checker {
	private User user;
	private String endpoint, accessKeyId, accessKeySecret, bucketName;
	private OSS client;
	OSSObject userobject;

	public Checker(String username) {

		this.endpoint = "https://oss-us-west-1.aliyuncs.com";
		accessKeyId = "LTAI4GDxcW1PHxsHNi1mHayL";
		accessKeySecret = "QmK2T457Jicpo7KHtaQjWmiMg51Igc";
		bucketName = "leyin-test";
		client = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
		String key1 = "resources/Users/";
		String key = "resources/Users/" + username + ".txt";
		boolean exists = false;
		
		System.out.println("Listing objects");
		String location = "";
        ObjectListing objectListing = client.listObjects(bucketName, key1);
        for (OSSObjectSummary objectSummary : objectListing.getObjectSummaries()) {
            System.out.println(objectSummary.getKey() + "  " +
                               "(size = " + objectSummary.getSize() + ")");
            location = objectSummary.getKey();
            System.out.println(key);
            if(location.equals(key)) {
    			exists = true;
    			System.out.println("Found User");
    			break;
    		}
        }
        System.out.println();
        
		
		System.out.println(exists);
		
		if(exists == true) {
			userobject = client.getObject(bucketName, key);

			InputStream content = userobject.getObjectContent();
			try {
				user = userinfo(content);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			System.out.println("User not found");
			user = null;
		}
	}


	public User getuser() {
		return user;
	}

	public boolean checkpass(String pass) {
		if(pass.equals(user.getpass())) {
			return true;
		}
		return false;
	}

	private static User userinfo(InputStream input) throws IOException {
		User user;
		String name = "", pass = "", perm = "";
		BufferedReader reader = new BufferedReader(new InputStreamReader(input));
		while (true) {
			String line = reader.readLine();
			System.out.println(line);
			if (line == null) break;

			System.out.println("\t" + line);
			if(line.contains("Username :")) {
				name = line.substring(11);
			}
			else if(line.contains("Password :")) {
				pass = line.substring(11);
			}
			else if(line.contains("Permission: ")) {
				perm = line.substring(12);
			}
		}
		System.out.println(name + " " + pass + " " + perm);
		System.out.println();

		user = new User(name,pass,Integer.parseInt(perm));
		reader.close();
		return user;
	}

}
