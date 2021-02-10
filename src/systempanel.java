import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.URISyntaxException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;


public class systempanel{
	user user;
	JButton lb2, lb3, lb4;
	JLabel lb1, top;
	JFrame uiframe;

	public systempanel(user user){	
		this.user = user;
		
			initComponents();
		
	}

	private void initComponents() {

		uiframe = new JFrame("EW pre Alpha ver.");
		uiframe.setLayout(null);
		uiframe.setResizable(false);
		uiframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		uiframe.setPreferredSize(new Dimension(1280,720));



		ImageIcon logo = new ImageIcon(getClass().getResource("resources/asserts/logo.png"));
		logo = new ImageIcon(change(logo, 729, 598));
		lb1 = new JLabel(logo);
		lb1.setBorder(null);
		lb1.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				try {
					level2();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
			}
		});
		Timer time = new Timer(3000, new ActionListener() {
			int x = 0;
			public void actionPerformed(ActionEvent evt) {
				x += 10;
				lb1.setForeground(new Color(0,0,0,x));
				lb1.setBackground(new Color(255,255,255,x));
				
				try {
					level2();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			}
		});
		
		
		time.setRepeats(false);
		time.start();

		lb1.setBounds(276, 10, 729, 598);
		uiframe.add(lb1);
		uiframe.pack();
		uiframe.setLocationRelativeTo(null);
		uiframe.setVisible(true);
		uiframe.repaint();
	}



	private void level2() throws IOException {
		//System.out.println("second level");
		uiframe.remove(lb1);
		uiframe.repaint();
		ImageIcon background = new ImageIcon(ImageIO.read(getClass().getResource("resources/asserts/top.png")));
		background = new ImageIcon(change(background, 1280, 720));
		top = new JLabel(background);
		top.setBounds(0,0,1280,720);
		uiframe.add(top);

		ImageIcon button = new ImageIcon(getClass().getResource("resources/asserts/button.png"));
		button = new ImageIcon(change(button, 300, 300));

		lb2 = new JButton(button);
		lb2.setBounds(500, 250, 300, 300);
		lb2.setContentAreaFilled(false);
		lb2.setFocusPainted(false);
		lb2.setBorderPainted(false);
		lb2.setHorizontalTextPosition(JButton.CENTER);
		lb2.setVerticalTextPosition(JButton.CENTER);
		lb2.setFont(new java.awt.Font("Monospaced", 1, 20));
		lb2.setText("数据管理");

		lb3 = new JButton(button);
		lb3.setBounds(80, 250, 300, 300);
		lb3.setContentAreaFilled(false);
		lb3.setFocusPainted(false);
		lb3.setBorderPainted(false);
		lb3.setHorizontalTextPosition(JButton.CENTER);
		lb3.setVerticalTextPosition(JButton.CENTER);
		lb3.setFont(new java.awt.Font("Monospaced", 1, 20));
		lb3.setText("待办事项");

		lb4 = new JButton(button);
		lb4.setBounds(920, 250, 300, 300);
		lb4.setContentAreaFilled(false);
		lb4.setFocusPainted(false);
		lb4.setBorderPainted(false);
		lb4.setHorizontalTextPosition(JButton.CENTER);
		lb4.setVerticalTextPosition(JButton.CENTER);
		lb4.setFont(new java.awt.Font("Monospaced", 1, 20));
		lb4.setText("仓库管理");

		lb2.addActionListener(new ActionListener() 
		{
			secondlevelpanel sys;

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				sys = new secondlevelpanel(user);
				uiframe.dispose();
			}
		});

		uiframe.add(lb2);
		uiframe.add(lb3);
		uiframe.add(lb4);
		uiframe.pack();
		uiframe.setLocationRelativeTo(null);
		uiframe.repaint();
	}

	private Image change(ImageIcon icon, int w, int h) {
		Image img = icon.getImage();
		Image newimg = img.getScaledInstance(w, h, java.awt.Image.SCALE_SMOOTH);
		return newimg;
	}
}
