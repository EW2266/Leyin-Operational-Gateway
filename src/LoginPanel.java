import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URISyntaxException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginPanel extends JFrame implements ActionListener{
	private JFrame uiframe;
	private JTextField text1;
	private JPasswordField text2;
	private JButton button1;
	private boolean loginpassed;

	public LoginPanel() throws IOException {	
		uiframe = new JFrame("EW pre alpha ver.");
		JLabel bg1 = new JLabel();
		ImageIcon background = new ImageIcon(getClass().getResource("resources/asserts/login.png"));
		background = new ImageIcon(change(background, 400, 600));
		bg1 = new JLabel(background);
		bg1.setBounds(-5,-10,400,600);
		
		JLabel bg2 = new JLabel();
		ImageIcon background1 = new ImageIcon(getClass().getResource("resources/asserts/loginlogo.png"));
		background1 = new ImageIcon(change(background1, 400, 600));
		bg2 = new JLabel(background1);
		bg2.setBounds(0,0,400,600);
		
		uiframe.setBackground(Color.WHITE);
		uiframe.add(bg2);
		uiframe.add(bg1);
		text1 = new JTextField(); 
		text2 = new JPasswordField();
		button1 = new JButton();
		
		start_frame(uiframe,text1,text2,button1);		
		text2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					CheckingSys check = new CheckingSys(text1, text2);
					if(!check.getinlist()) {
						notinlist();
					}
					else if (!check.pass()) {
						wrongpass();
					}
					else {
						//System.out.println("longging in...");
						loginpassed = true;

						//System.out.println(this.getloginpassed());
						systempanel system = new systempanel(check.giveUser());
						this.dispose();

					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

			private void dispose() {
				uiframe.setVisible(false);
				uiframe.dispose();
				
			}

		});
	}

	private Image change(ImageIcon icon, int w, int h) {
		Image img = icon.getImage();
		Image newimg = img.getScaledInstance(w, h, java.awt.Image.SCALE_SMOOTH);
		return newimg;
	}

	public boolean getloginpassed() {
		return loginpassed;
	}

	private void wrongpass() {
		JOptionPane.showMessageDialog(this.uiframe,
				"Invalid password. Try again.",
				"Error Message",
				JOptionPane.ERROR_MESSAGE);
		text2.setText("");
	}

	private void notinlist() {
		//System.out.println("Not Registered");
		JOptionPane.showMessageDialog(this.uiframe,
				"Not registered user. Try again.",
				"Error Message",
				JOptionPane.ERROR_MESSAGE);
		text1.setText("");
		text2.setText("");
	}

	private void start_frame(JFrame f, JTextField t1, JTextField t2, JButton b1) {

		JLabel label1 = new JLabel();		
		label1.setFont(new java.awt.Font("Monospaced", 1, 20));
		label1.setText("用户名 ：");
		label1.setBounds(70, 160, 200, 30);

		JLabel label2 = new JLabel();
		label2.setFont(new java.awt.Font("Monospaced", 1, 20));
		label2.setText("密码 ：");
		label2.setBounds(70, 300, 200, 30);

		//Buttons
		//b1.setFont(new java.awt.Font("Monospaced", 1, 15));
		//b1 = new JButton("登录");
		//b1.setBounds(50, 150, 300, 100);    

		t1.setBounds(70, 206, 250, 35);
		t1.setFont(new java.awt.Font("Monospaced", 1, 25));
		t1.setBorder(null);
		
		t2.setBounds(70, 343, 250, 35);
		t2.setFont(new java.awt.Font("Monospaced", 1, 25));
		t2.setBorder(null);
		//adding contents
		f.getContentPane().add(t1);
		f.getContentPane().add(t2);
		//f.getContentPane().add(b1);
		f.getContentPane().add(label1);
		f.getContentPane().add(label2);
		
		JLabel l3 = new JLabel();
		ImageIcon background = new ImageIcon(getClass().getResource("resources/asserts/background.png"));
		background = new ImageIcon(change(background, 400, 600));
		l3 = new JLabel(background);
		l3.setBounds(0,0,400,600);


		//frame size and location	

		f.setLayout(null);   
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setLocationRelativeTo(null);
		f.setPreferredSize(new Dimension(400,620));
		f.setResizable(false);
		//System.out.println(f.getSize());
		f.add(l3);
		//packing frame
		f.pack();
		
		f.setLocationRelativeTo(null);

		//showing the frame
		f.setVisible(true);

		b1.addActionListener(this);
	}

	public boolean getbutton(){
		return true;
	}

	public void dispose() {
		uiframe.setVisible(false);
		uiframe.dispose();

	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			CheckingSys check = new CheckingSys(text1, text2);
			if(!check.getinlist()) {
				notinlist();
			}
			else if (!check.pass()) {
				wrongpass();
			}
			else {
				//System.out.println("longging in...");
				loginpassed = true;

				//System.out.println(this.getloginpassed());
				systempanel system = new systempanel(check.giveUser());
				this.dispose();



			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
