
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
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class FileExplorer extends javax.swing.JFrame {

	ArrayList<File> stack;
	user logged;
	
	public FileExplorer() throws URISyntaxException {
		initComponents();
		this.setLocationRelativeTo(null);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		stack = new ArrayList<>();
		CodeSource codeSource = main.class.getProtectionDomain().getCodeSource();
		File jarFile = new File(codeSource.getLocation().toURI().getPath());
		String jarDir = jarFile.getParentFile().getPath();
		System.out.println(jarDir.toString());
		String path = jarDir + "/resources";
		LoadBase(path);
	}
	
	public void getUser(user u) {
		logged = u;
	}
	
	
	
	public void LoadBase(String dir) {
		File f = new File(dir);
		File[] s = f.listFiles();
		try {
			for (File s1 : s) {
				JButton btn;
				if (s1.getName().length() > 20) {
					btn = new JButton(s1.getName().substring(0, 20)+ "..") ;
				} else {
					btn = new JButton(s1.getName());
				}
				if (s1.isFile()) {
					btn.setBackground(Color.BLACK);
					btn.setForeground(Color.WHITE);
				} else {
					btn.setBackground(Color.WHITE);
				}
				btn.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						if (s1.isFile()) {
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
						} else {
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
				System.out.println(s1);
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
		if (new File(in.getText()).exists()) {
			container.removeAll();
			stack.add(new File(in.getText()));
			LoadBase(in.getText());
			container.revalidate();
			container.repaint();
		} else {
			JOptionPane.showMessageDialog(rootPane, "Invalid Directory", "Error", JOptionPane.ERROR_MESSAGE);
		}

	}//GEN-LAST:event_inActionPerformed


	//</editor-fold>

	/* Create and display the form */

	public void run() throws URISyntaxException {
		new FileExplorer().setVisible(true);
	}


	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JPanel container;
	private javax.swing.JTextField in;
	private javax.swing.JButton jButton1;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JScrollPane jScrollPane1;
	// End of variables declaration//GEN-END:variables
}
