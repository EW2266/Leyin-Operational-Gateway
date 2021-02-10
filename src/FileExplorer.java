import com.aliyun.oss.*;
import com.aliyun.oss.model.GetObjectRequest;
import com.aliyun.oss.model.ListObjectsV2Request;
import com.aliyun.oss.model.ListObjectsV2Result;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.OSSObjectSummary;
import com.aliyun.oss.model.ObjectListing;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.security.CodeSource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;

public class FileExplorer extends javax.swing.JFrame {
	private javax.swing.JPanel container;
	private javax.swing.JTextField in;
	private javax.swing.JButton jButton1;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JScrollPane jScrollPane1;
	private String bucketName;
	private OSS ossClient;
	ArrayList<String> stack;
	ArrayList<String> all;
	user logged;
	Image top;

	public FileExplorer(String str) throws URISyntaxException {
		String endpoint = "https://oss-us-west-1.aliyuncs.com";
		String accessKeyId = "LTAI4GDxcW1PHxsHNi1mHayL";
		String accessKeySecret = "QmK2T457Jicpo7KHtaQjWmiMg51Igc";
		bucketName = "leyin-test";
		ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

		initComponents();
		this.setLocationRelativeTo(null);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		//this.addWindowListener(new java.awt.event.WindowAdapter() {
		// @Override
		// public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		// if (JOptionPane.showConfirmDialog(this, 
		// "Are you sure you want to close this window?", "Close Window?", 
		//   JOptionPane.YES_NO_OPTION,
		//   JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
		//ossClient.shutdown();
		// System.exit(0);
		//}
		//  }
		//});
		container.setLayout(null);
		stack = new ArrayList<>();
		String path = "resources/" + str + "/";
		System.out.println(path);
		all = new ArrayList<String>();
		getallfiles();
		LoadBase(path);
	}

	public void getUser(user u) {
		logged = u;
	}

	public void getallfiles() {
		ObjectListing objectListing = ossClient.listObjects(bucketName);
		for (OSSObjectSummary objectSummary : objectListing.getObjectSummaries()) {
			//System.out.println(" - " + objectSummary.getKey() + "  " +
				//	"(size = " + objectSummary.getSize() + ")");
			all.add(objectSummary.getKey());
		}
	}

	public void LoadBase(String dir) {
		//		File f = new File(dir);
		//		File[] s = f.listFiles();
		//		try {
		ArrayList<String> filesindir = new ArrayList<>();
		ArrayList<String> folders = new ArrayList<>();
		ArrayList<String> files = new ArrayList<>();
		ListObjectsV2Request listObjectsV2Request = new ListObjectsV2Request(bucketName);
		listObjectsV2Request.setPrefix(dir);
		listObjectsV2Request.setDelimiter("/");
		//System.out.println(dir);
		ListObjectsV2Result result = ossClient.listObjectsV2(listObjectsV2Request);
		System.out.println("Objects:");
		for (OSSObjectSummary objectSummary : result.getObjectSummaries()) {
			System.out.println(objectSummary.getKey());
			if(!objectSummary.getKey().equals(dir))
				files.add(objectSummary.getKey());
		}
		System.out.println("\nCommonPrefixes:");
		for (String commonPrefix : result.getCommonPrefixes()) {
			System.out.println(commonPrefix);
			folders.add(commonPrefix);
		}
		filesindir.addAll(folders);
		filesindir.addAll(files);
		for (int i = 0; i < filesindir.size(); i++) {
			String s = filesindir.get(i); 
			//System.out.println("s is: " + s);
			//System.out.println("dir is: " + dir);
			final String s1 = s.substring(dir.length());
			//System.out.println("s1 is: " + s1);
			JButton btn;
			btn = new JButton();
			if (files.contains(s)) {
				if(s1.contains("jpg")||s1.contains("png")||s1.contains("jfif")) {
					btn = new JButton("图片                                                            "+s1 + "                           ");
					btn.setBackground(Color.CYAN);

				}
				else if(s1.contains("pdf")) {
					btn = new JButton("PDF                               "+s1 + "                           ");
					btn.setBackground(Color.GRAY);

				}
				else if(s1.contains("docx")||(s1.contains("doc"))||s1.contains("txt")){
					btn = new JButton("文档                                                              "+s1 + "                           ");
					btn.setBackground(Color.BLUE);

				}
				else if(s1.contains("xlsx")){
					btn = new JButton("表格                                                                "+s1 + "                           ");
					btn.setBackground(Color.GREEN);

				}
				else if(s1.contains("pptx")||(s1.contains("ppt"))){
					btn = new JButton("PPT                            "+s1 + "                           ");
					btn.setBackground(Color.ORANGE);

				}
				else{
					btn = new JButton("其他                                                                "+s1 + "                           ");
					btn.setBackground(Color.RED);

				}
				btn.setForeground(Color.WHITE);
			} else {
				btn = new JButton("文件夹                                                               "+s1 + "                           ");
				btn.setBackground(Color.WHITE);

			}
			btn.setBounds(0, i*50, container.getWidth(), 50);
			btn.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if(stack.size() >= 1 && logged.getperm() != 1) {
						System.out.println("not high enough permisison");
						JOptionPane.showMessageDialog(rootPane, "You don't have high engough permission", "Sorry", JOptionPane.ERROR_MESSAGE);
					}
					else if (files.contains(s)) {
						try {
							if(logged.getperm() == 1) {
								//Desktop.getDesktop().open(s1);
								JFrame f = new JFrame();
								JProgressBar bar = new JProgressBar(0,100);
								ossClient.getObject(new GetObjectRequest(bucketName, s).
					                    <GetObjectRequest>withProgressListener(new GetObjectProgressListener(f,bar)), new File(s1));
							}		
							else {
								JOptionPane.showMessageDialog(rootPane, "You Don't Have Permission To View This File", "Sorry" , JOptionPane.ERROR_MESSAGE);
							}
						} catch (Exception ex) {
							JOptionPane.showMessageDialog(rootPane, "Cant open the file!", "Sorry", JOptionPane.ERROR_MESSAGE);
							Logger.getLogger(FileExplorer.class.getName()).log(Level.SEVERE, null, ex);
						}
					} 
					else {
						System.out.println("going to next directory");
						stack.add(dir);
						container.removeAll();
						LoadBase(s);
						System.out.println(s);
						container.revalidate();
						container.repaint();
					}
				}
			});
			container.add(btn);
			//System.out.println(s1);
		}
		//		} catch (Exception e) {
		//			JOptionPane.showMessageDialog(rootPane, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		//		}
	}

	@Override
	public void paintComponents(Graphics g) {
		super.paintComponents(g);

		// Draw the background image.
		g.drawImage(top, 0, 0, getWidth(),getHeight(),this);
	}
	@SuppressWarnings("unchecked")

	private void initComponents() {

		jPanel1 = new javax.swing.JPanel();
		jButton1 = new javax.swing.JButton();
		jLabel1 = new javax.swing.JLabel();
		in = new javax.swing.JTextField();
		jScrollPane1 = new javax.swing.JScrollPane();
		container = new javax.swing.JPanel();
		jLabel2 = new javax.swing.JLabel();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setTitle("EW pre Alpha ver.");


		//jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Toolbar"));
		//try {
		//top = ImageIO.read(getClass().getResource("resources/asserts/top.png"));
		//} catch (IOException e) {
		// TODO Auto-generated catch block
		//e.printStackTrace();
		//	}

		jButton1.setFont(new java.awt.Font("Monospaced", 1, 15));
		jButton1.setText("返回上级");
		jButton1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton1ActionPerformed(evt);
			}
		});

		jLabel1.setFont(new java.awt.Font("Monospaced", 1, 15)); // NOI18N
		jLabel1.setText("查找");

		in.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				inActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
		jPanel1.setLayout(jPanel1Layout);



		jPanel1Layout.setHorizontalGroup(
				jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel1Layout.createSequentialGroup()
						.addContainerGap()
						.addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 347, Short.MAX_VALUE)
						.addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(in, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addContainerGap())
				);



		jPanel1Layout.setVerticalGroup(
				jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel1Layout.createSequentialGroup()
						.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
								.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addComponent(in, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
										.addGroup(jPanel1Layout.createSequentialGroup()
												.addGap(1, 1, 1)
												.addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
								.addComponent(jButton1))
						.addContainerGap(13, Short.MAX_VALUE))
				);


		container.setLayout(new java.awt.GridLayout(0, 3, 10, 10));
		jScrollPane1.setViewportView(container);

		jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		jLabel2.setText("EW pre Alpha ver.");

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 776, Short.MAX_VALUE)
				.addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				);
		layout.setVerticalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
						.addGap(59, 59, 59)
						.addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 401, Short.MAX_VALUE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup()
								.addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(0, 427, Short.MAX_VALUE)))
				);

		pack();
	}// </editor-fold>//GEN-END:initComponents

	private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
		if (stack.size() > 0) {
			String path = stack.get(stack.size() - 1);
			System.out.println(path + "    返回上级");
			stack.remove(stack.size() - 1);
			container.removeAll();
			LoadBase(path);
			container.revalidate();
			container.repaint();
		}
		else{
			dispose();
		}
	}//GEN-LAST:event_jButton1ActionPerformed



	@Override
	public void dispose() {
		if (JOptionPane.showConfirmDialog(this, 
				"Are you sure you want to close this window?", "Close Window?", 
				JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
			ossClient.shutdown();
			secondlevelpanel sys;
			sys = new secondlevelpanel(logged);
			super.dispose();
		}
	}

	private void inActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inActionPerformed

		System.out.println(in.getText());
		int i = 1;
		for(String a : all) {
			i++;
			if (in.getText().contains(a)) {
				container.removeAll();
				stack.add(a);
				LoadBase(a);
				container.revalidate();
				container.repaint();
				break;
			}
		}
		if(i > all.size()) {
			JOptionPane.showMessageDialog(rootPane, "Invalid Directory", "Error", JOptionPane.ERROR_MESSAGE);
		}


	}//GEN-LAST:event_inActionPerformed
	
}
