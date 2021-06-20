package login;
import MainPanel.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.AbstractAction;


public class Login_GUI extends JFrame implements ActionListener {
	private JTextField text1;
	private JPasswordField text2;
	private JButton button1, button2;
	private boolean loginpassed;
	private JLabel bg2_1;
	
	public static void main(String[] args) throws IOException {
		Login_GUI login = new Login_GUI();
	}

	public Login_GUI() throws IOException {	
		this.setTitle("Leyin -- Alpha Test");
		JLabel bg1 = new JLabel();
		ImageIcon background = new ImageIcon(this.getClass().getResource("/resource/asserts/login.png"));
		background = new ImageIcon(change(background, 400, 600));
		bg1 = new JLabel(background);
		bg1.setBounds(-5,-10,400,600);
		
		JLabel bg2 = new JLabel();
		ImageIcon background1 = new ImageIcon(this.getClass().getResource("/resource/asserts/loginlogo.png"));
		background1 = new ImageIcon(change(background1, 400, 600));
		bg2_1 = new JLabel(background1);
		bg2_1.setBackground(Color.WHITE);
		bg2_1.setBounds(0,0,400,600);
		
		this.setBackground(Color.WHITE);
		this.getContentPane().add(bg2_1);
		this.getContentPane().add(bg1);
		text1 = new JTextField(); 
		text2 = new JPasswordField();
		button1 = new JButton();
		button2 = new JButton();
		
		start_frame(this,text1,text2,button1,button2);		
		
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
		JOptionPane.showMessageDialog(this,
				"密码错误，请重试",
				"Error Message",
				JOptionPane.ERROR_MESSAGE);
		text2.setText("");
	}

	private void notinlist() {
		//System.out.println("Not Registered");
		JOptionPane.showMessageDialog(this,
				"未知用户",
				"Error Message",
				JOptionPane.ERROR_MESSAGE);
		text1.setText("");
		text2.setText("");
	}
	
	private void empty() {
		//System.out.println("Not Registered");
		JOptionPane.showMessageDialog(this,
				"请输入用户名/密码",
				"Error Message",
				JOptionPane.ERROR_MESSAGE);
		text1.setText("");
		text2.setText("");
	}

	private void start_frame(JFrame f, JTextField t1, JTextField t2, JButton b1, JButton b2) {

		JLabel label1 = new JLabel();		
		label1.setFont(new java.awt.Font("Monospaced", 1, 20));
		label1.setText("用户名");
		label1.setBounds(70, 160, 200, 30);

		JLabel label2 = new JLabel();
		label2.setFont(new java.awt.Font("Monospaced", 1, 20));
		label2.setText("密码");
		label2.setBounds(70, 300, 200, 30);

		b1.setFont(new java.awt.Font("Monospaced", 1, 15));
		b1 = new JButton("登录");
		b1.setBounds(70, 450, 250, 35);   
		b1.addActionListener(this);
		
		b2.setFont(new java.awt.Font("Monospaced", 1, 15));
		b2 = new JButton();
		b2.setBackground(Color.GRAY);
		b2.setText("注册");
		b2.setBounds(70, 500, 250, 35);   
		b2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		t1.setBounds(70, 206, 250, 35);
		t1.setFont(new java.awt.Font("Monospaced", 1, 25));
		t1.setBorder(null);
		
		t2.setBounds(70, 343, 250, 35);
		t2.setFont(new java.awt.Font("Monospaced", 1, 25));
		t2.setBorder(null);
		//adding contents
		f.getContentPane().add(t1);
		f.getContentPane().add(t2);
		f.getContentPane().add(b1);
		f.getContentPane().add(b2);
		f.getContentPane().add(label1);
		f.getContentPane().add(label2);
		
		JLabel l3 = new JLabel();
		ImageIcon background = new ImageIcon(getClass().getResource("/resource/asserts/background.png"));
		background = new ImageIcon(change(background, 400, 600));
		l3 = new JLabel(background);
		l3.setBounds(0,0,400,600);


		//frame size and location	

		f.getContentPane().setLayout(null);   
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setLocationRelativeTo(null);
		f.setPreferredSize(new Dimension(400,620));
		f.setResizable(false);
		f.getContentPane().add(l3);
		//packing frame
		f.pack();
		
		f.setLocationRelativeTo(null);

		//showing the frame
		f.setVisible(true);

	}

	public void dispose() {
		this.setVisible(false);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent e) {
		if(!text1.getText().isEmpty() && !text2.getText().isEmpty()) {
			System.out.println("not empty");
			Checker checker = new Checker(text1.getText());
			if(checker.getuser() == null) {
				notinlist();
			}
			else if(!checker.checkpass(text2.getText())) {
				wrongpass();
			}
			else {
				MP_GUI mp = new MP_GUI(checker.getuser());
				dispose();
			}
		}
		else {
			System.out.println("empty");
			empty();
		}
	}

	
}
