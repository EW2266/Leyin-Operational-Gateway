
import java.awt.Color;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.URISyntaxException;
import java.security.CodeSource;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class FileExplorer extends javax.swing.JFrame {
	private javax.swing.JPanel container;
	private javax.swing.JTextField in;
	private javax.swing.JButton jButton1;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JScrollPane jScrollPane1;
	ArrayList<File> stack;
	ArrayList<File> all;
	user logged;
	
	public FileExplorer() throws URISyntaxException {
		initComponents();
		this.setLocationRelativeTo(null);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		container.setLayout(null);
		stack = new ArrayList<>();
		CodeSource codeSource = main.class.getProtectionDomain().getCodeSource();
		File jarFile = new File(codeSource.getLocation().toURI().getPath());
		String jarDir = jarFile.getParentFile().getPath();
		System.out.println(jarDir.toString());
		String path = jarDir + "/resources";
		all = new ArrayList<File>();
		getallfiles(path);
		LoadBase(path);
	}
	
	public void getUser(user u) {
		logged = u;
	}
	
	public void getallfiles(String path) {
		File folder = new File(path);
		File[] listOfFiles = folder.listFiles();
		ArrayList<File> folders = new ArrayList<File>();
		for (int i = 0; listOfFiles != null && i < listOfFiles.length; i++) {
		  if (listOfFiles[i].isFile()) {
		    System.out.println("File " + listOfFiles[i].getName());
		    all.add(listOfFiles[i]);
		  } else if (listOfFiles[i].isDirectory()) {
		    System.out.println("Directory " + listOfFiles[i].getName());
		    folders.add(listOfFiles[i]);
		    all.add(listOfFiles[i]);
		  }
		}
		if(folders.size() > 0) {
			File newfolder = new File(folders.get(0).getPath());
			File[] newlistOfFiles = newfolder.listFiles();
			for (int i = 0; i < newlistOfFiles.length; i++) {
			  if (newlistOfFiles[i].isFile()) {
			    System.out.println("File " + newlistOfFiles[i].getName());
			    all.add(newlistOfFiles[i]);
			  } else if (newlistOfFiles[i].isDirectory()) {
			    System.out.println("Directory " + newlistOfFiles[i].getName());
			    folders.add(newlistOfFiles[i]);
			    all.add(newlistOfFiles[i]);
			  }
			}
			folders.remove(0);
		}
	}
	
	public void LoadBase(String dir) {
		File f = new File(dir);
		File[] s = f.listFiles();
		try {
			for (int i = 0; i < s.length; i++) {
				File s1 = s[i]; 
				JButton btn;
				btn = new JButton(s1.getName());
				
				if (s1.isFile()) {
					if(s1.getName().contains("jpg")||s1.getName().contains("png")) {
						btn.setBackground(Color.BLACK);
					}
					else if(s1.getName().contains("pdf")) {
						btn.setBackground(Color.GRAY);
					}
					else if(s1.getName().contains("docx")||(s1.getName().contains("doc"))){
						btn.setBackground(Color.BLUE);
					}
					else if(s1.getName().contains("xlsx")){
						btn.setBackground(Color.GREEN);
					}
					else if(s1.getName().contains("pptx")||(s1.getName().contains("ppt"))){
						btn.setBackground(Color.ORANGE);
					}
					else{
						btn.setBackground(Color.RED);
					}
					btn.setForeground(Color.WHITE);
				} else {
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
						else if (s1.isFile()) {
							try {
								if(logged.getperm() == 1) {
									Desktop.getDesktop().open(s1);
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
							stack.add(f);
							container.removeAll();
							LoadBase(s1.getAbsolutePath());
							System.out.println(s1.getAbsolutePath());
							container.revalidate();
							container.repaint();
						}
					}
				});
				container.add(btn);
				//System.out.println(s1);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(rootPane, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
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

		jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Toolbar"));

		jButton1.setText("BACK");
		jButton1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton1ActionPerformed(evt);
			}
		});

		jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
		jLabel1.setText("Go to");

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
			String path = stack.get(stack.size() - 1).getAbsolutePath();
			System.out.println(path + " Switch");
			stack.remove(stack.size() - 1);
			container.removeAll();
			LoadBase(path);
			container.revalidate();
			container.repaint();
		}
	}//GEN-LAST:event_jButton1ActionPerformed

	private void inActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inActionPerformed
		
		System.out.println(in.getText());
		int i = 1;
		for(File a : all) {
			i++;
			if (in.getText().equals(a.getName())) {
				container.removeAll();
				stack.add(a);
				LoadBase(a.getPath());
				container.revalidate();
				container.repaint();
				break;
			}
		}
		if(i > all.size()) {
			JOptionPane.showMessageDialog(rootPane, "Invalid Directory", "Error", JOptionPane.ERROR_MESSAGE);
		}
		

	}//GEN-LAST:event_inActionPerformed


	//</editor-fold>

	/* Create and display the form */

	public void run() throws URISyntaxException {
		new FileExplorer().setVisible(true);
	}


	// Variables declaration - do not modify//GEN-BEGIN:variables
	
	// End of variables declaration//GEN-END:variables
}
