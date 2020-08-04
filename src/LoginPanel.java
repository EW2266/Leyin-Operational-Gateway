import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URISyntaxException;

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
		uiframe = new JFrame("pre alpha ver.");
		text1 = new JTextField(); 
		text2 = new JPasswordField();
		button1 = new JButton();

		start_frame(uiframe,text1,text2,button1);		

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
		System.out.println("Not Registered");
		JOptionPane.showMessageDialog(this.uiframe,
				"Not registered user. Try again.",
				"Error Message",
				JOptionPane.ERROR_MESSAGE);
		text1.setText("");
		text2.setText("");
	}

	private void start_frame(JFrame f, JTextField t1, JTextField t2, JButton b1) {

		JLabel label1 = new JLabel();		
		label1.setText("Enter User Name :");
		label1.setBounds(10, 10, 200, 30);

		JLabel label2 = new JLabel();
		label2.setText("Enter User Password: ");
		label2.setBounds(10, 100, 200, 30);

		//Buttons
		b1 = new JButton("Login");
		b1.setBounds(50, 150, 200, 100);    

		t1.setBounds(150, 10, 130, 30);

		t2.setBounds(150, 100, 130, 30);

		//adding contents
		f.getContentPane().add(t1);
		f.getContentPane().add(t2);
		f.getContentPane().add(b1);
		f.getContentPane().add(label1);
		f.getContentPane().add(label2);



		//frame size and location	

		f.setLayout(null);   
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setLocationRelativeTo(null);
		f.setPreferredSize(new Dimension(300,300));
		f.setResizable(false);
		//System.out.println(f.getSize());

		//packing frame
		f.pack();

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

				System.out.println(this.getloginpassed());
				FileExplorer system = new FileExplorer();
				system.getUser(check.giveUSer());
				this.dispose();
				
				system.setVisible(true);

			}
		} catch (IOException | URISyntaxException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
