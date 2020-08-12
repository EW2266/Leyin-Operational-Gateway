import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.security.CodeSource;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;


public class secondlevelpanel {
	private user user;
	private JFrame f;
	private JButton[] buttons;
	private JLabel bg1,bg2;

	public secondlevelpanel(user u) {
		user = u;
		buttons = new JButton[19];
		init();

	}

	private void init() {
		f = new JFrame("EW pre Alpha ver.");
		f.setLayout(null);
		f.setResizable(false);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setPreferredSize(new Dimension(1280,720));

		ImageIcon background = new ImageIcon(getClass().getResource("resources/asserts/Central_logo.png"));
		background = new ImageIcon(change(background, 1280, 720));
		bg1 = new JLabel(background);
		bg1.setBounds(0,0,1280,720);



		buttons[0] = new JButton();
		ImageIcon button = new ImageIcon(getClass().getResource("resources/asserts/button.png"));
		button = new ImageIcon(change(button, 160, 160));
		buttons[0] = new JButton(button);
		buttons[0].setContentAreaFilled(false);
		buttons[0].setFocusPainted(false);
		buttons[0].setBorderPainted(false);
		buttons[0].setHorizontalTextPosition(JButton.CENTER);
		buttons[0].setVerticalTextPosition(JButton.CENTER);
		buttons[0].setFont(new java.awt.Font("Monospaced", 1, 20));
		buttons[0].setText("乐印总店");
		buttons[0].addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				level2();  
			}
		});


		buttons[0].setBounds(560, 280, 160, 160);
		f.add(buttons[0]);
		f.add(bg1);
		f.setVisible(true);
		f.pack();
		f.setLocationRelativeTo(null);
		f.repaint();
	}

	private Image change(ImageIcon icon, int w, int h) {
		Image img = icon.getImage();
		Image newimg = img.getScaledInstance(w, h, java.awt.Image.SCALE_SMOOTH);
		return newimg;
	}

	private void level2() {
		for(int i = 1; i < 7; i++) {
			System.out.println(i);
			buttons[i] = new JButton();
			buttons[i].setContentAreaFilled(false);
			buttons[i].setFocusPainted(false);
			buttons[i].setBorderPainted(false);
			buttons[i].setHorizontalTextPosition(JButton.CENTER);
			buttons[i].setVerticalTextPosition(JButton.CENTER);
			buttons[i].setFont(new java.awt.Font("Monospaced", 1, 20));	
		}

		setnames(1, 7, "乐印总店");

		for(int i = 1; i < 7; i++) {
			String name = buttons[i].getName();
			if(name.equals("教研")) {
				System.out.println(buttons[i].getName());
				f.add(buttons[i]);
				buttons[i].setBounds(570, 130, 130, 80);
				buttons[i].addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						System.out.println("教研");
						catN();	
					}
				});
			}
			else if(name.equals("财务")) {
				System.out.println(buttons[i].getName());
				f.add(buttons[i]);
				buttons[i].setBounds(710, 200, 130, 160);
				buttons[i].addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						System.out.println("财务");
						catNE();	
					}
				});
			}
			else if(name.equals("客服")) {
				System.out.println(buttons[i].getName());
				f.add(buttons[i]);
				buttons[i].setBounds(710, 360, 130, 160);
				buttons[i].addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						System.out.println("客服");
						catSE();	
					}
				});
			}
			else if(name.equals("评估")) {
				System.out.println(buttons[i].getName());
				f.add(buttons[i]);
				buttons[i].setBounds(570, 475, 130, 80);
				buttons[i].addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						System.out.println("评估");
						catS();	
					}
				});
			}
			else if(name.equals("职员")) {
				System.out.println(buttons[i].getName());
				f.add(buttons[i]);
				buttons[i].setBounds(440, 360, 130, 160);
				buttons[i].addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						System.out.println("职员");
						catSW();	
					}
				});
			}
			else if(name.equals("培训")) {
				System.out.println(buttons[i].getName());
				f.add(buttons[i]);
				buttons[i].setBounds(440, 200, 130, 160);
				buttons[i].addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						System.out.println("培训");
						catNW();	
					}
				});
			}
			
			
			
			
			
			switch(buttons[i].getName()) {
			case "教研": 
				System.out.println(1);
				break;

			case "财务":
				System.out.println(2);
				break;

			case "客服":
				System.out.println(3);
				break;

			case "评估":
				System.out.println(4);
				break;

			case "职员":
				System.out.println(5);
				break;

			case "培训":
				System.out.println(6);
				break;
			}

		}
		
		ImageIcon background = new ImageIcon(getClass().getResource("resources/asserts/Secondary_cat.png"));
		background = new ImageIcon(change(background, 1280, 720));
		bg2 = new JLabel(background);
		bg2.setBounds(0,0,1280,720);
		f.add(bg2);
		f.pack();
		f.repaint();
	}

	protected void catSW() {
		// TODO Auto-generated method stub
		for(int i = 15; i < 17; i++) {
			buttons[i] = new JButton();
			f.add(buttons[i]);
			buttons[i].setContentAreaFilled(false);
			buttons[i].setFocusPainted(false);
			buttons[i].setBorderPainted(false);
		}

		JLabel bg = new JLabel();
		ImageIcon background = new ImageIcon(getClass().getResource("resources/asserts/Third_cat_SW.png"));
		background = new ImageIcon(change(background, 1280, 720));
		bg = new JLabel(background);
		bg.setBounds(0,0,1280,720);
		buttons[15].setBounds(300, 510, 130, 80);
		buttons[16].setBounds(235, 400, 130, 80);

		setnames(15,17,"乐印总店/职员");


		f.add(bg);
		f.pack();
		f.repaint();
	}

	protected void catNW() {
		// TODO Auto-generated method stub
		for(int i = 17; i < 19; i++) {
			buttons[i] = new JButton();
			f.add(buttons[i]);
			buttons[i].setContentAreaFilled(false);
			buttons[i].setFocusPainted(false);
			buttons[i].setBorderPainted(false);
		}

		JLabel bg = new JLabel();
		ImageIcon background = new ImageIcon(getClass().getResource("resources/asserts/Third_cat_NW.png"));
		background = new ImageIcon(change(background, 1280, 720));
		bg = new JLabel(background);
		bg.setBounds(0,0,1280,720);
		buttons[17].setBounds(300, 128, 130, 80);
		buttons[18].setBounds(235, 240, 130, 80);

		setnames(17,19,"乐印总店/培训");


		f.add(bg);
		f.pack();
		f.repaint();
	}

	protected void catS() {
		// TODO Auto-generated method stub
		for(int i = 13; i < 15; i++) {
			buttons[i] = new JButton();
			f.add(buttons[i]);
			buttons[i].setContentAreaFilled(false);
			buttons[i].setFocusPainted(false);
			buttons[i].setBorderPainted(false);
		}

		JLabel bg = new JLabel();
		ImageIcon background = new ImageIcon(getClass().getResource("resources/asserts/Third_cat_S.png"));
		background = new ImageIcon(change(background, 1280, 720));
		bg = new JLabel(background);
		bg.setBounds(0,0,1280,720);
		buttons[13].setBounds(440, 585, 130, 80);
		buttons[14].setBounds(700, 585, 130, 80);

		setnames(13,15,"乐印总店/评估");


		f.add(bg);
		f.pack();
		f.repaint();
	}

	protected void catSE() {
		// TODO Auto-generated method stub
		for(int i = 11; i < 13; i++) {
			buttons[i] = new JButton();
			f.add(buttons[i]);
			buttons[i].setContentAreaFilled(false);
			buttons[i].setFocusPainted(false);
			buttons[i].setBorderPainted(false);
		}

		JLabel bg = new JLabel();
		ImageIcon background = new ImageIcon(getClass().getResource("resources/asserts/Third_cat_SE.png"));
		background = new ImageIcon(change(background, 1280, 720));
		bg = new JLabel(background);
		bg.setBounds(0,0,1280,720);
		buttons[11].setBounds(835, 500, 130, 80);
		buttons[12].setBounds(900, 400, 130, 80);

		setnames(11,13,"乐印总店/客服");


		f.add(bg);
		f.pack();
		f.repaint();
	}

	protected void catNE() {
		// TODO Auto-generated method stub
		for(int i = 9; i < 11; i++) {
			buttons[i] = new JButton();
			f.add(buttons[i]);
			buttons[i].setContentAreaFilled(false);
			buttons[i].setFocusPainted(false);
			buttons[i].setBorderPainted(false);
		}

		JLabel bg = new JLabel();
		ImageIcon background = new ImageIcon(getClass().getResource("resources/asserts/Third_cat_NE.png"));
		background = new ImageIcon(change(background, 1280, 720));
		bg = new JLabel(background);
		bg.setBounds(0,0,1280,720);
		buttons[9].setBounds(835, 130, 130, 80);
		buttons[10].setBounds(900, 250, 130, 80);

		setnames(9,11,"乐印总店/财务");

		f.add(bg);
		f.pack();
		f.repaint();
	}

	private void catN() {
		// TODO Auto-generated method stub
		for(int i = 7; i < 9; i++) {
			buttons[i] = new JButton();
			f.add(buttons[i]);
			buttons[i].setContentAreaFilled(false);
			buttons[i].setFocusPainted(false);
			buttons[i].setBorderPainted(false);
		}

		JLabel bg = new JLabel();
		ImageIcon background = new ImageIcon(getClass().getResource("resources/asserts/Third_cat_N.png"));
		background = new ImageIcon(change(background, 1280, 720));
		bg = new JLabel(background);
		bg.setBounds(0,0,1280,720);
		buttons[7].setBounds(440, 50, 130, 80);
		buttons[8].setBounds(700, 50, 130, 80);

		setnames(7,9,"乐印总店/教研");
		buttons[7].addActionListener(new ActionListener() {
			FileExplorer sys;
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(user.getperm() == 1) {
					try {
						sys = new FileExplorer("乐印总店/教研/" + buttons[7].getName());
						sys.setVisible(true);
						sys.getUser(user);
						f.dispose();
					} catch (URISyntaxException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				else {
					System.out.println("not high enough permisison");
					JOptionPane.showMessageDialog(f.getRootPane(), "You don't have high engough permission", "Sorry", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		buttons[8].addActionListener(new ActionListener() {
			FileExplorer sys;
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(user.getperm() == 1) {
					try {
						sys = new FileExplorer("乐印总店/教研/" + buttons[8].getName());
						sys.setVisible(true);
						sys.getUser(user);
						f.dispose();
					} catch (URISyntaxException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				else {
					System.out.println("not high enough permisison");
					JOptionPane.showMessageDialog(f.getRootPane(), "You don't have high engough permission", "Sorry", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		f.add(bg);
		f.pack();
		f.repaint();
	}

	
	private void setnames(int start, int end, String str) {
		CodeSource codeSource = main.class.getProtectionDomain().getCodeSource();
		File jarFile = null;
		try {
			jarFile = new File(codeSource.getLocation().toURI().getPath());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		String jarDir = jarFile.getParentFile().getPath();
		//System.out.println(jarDir.toString());
		String path = jarDir + "/resources/" + str;
		File folder = new File(path);
		File[] listOfFiles = folder.listFiles();
		for(int i = start; i < end; i++) {
			if(listOfFiles.length > 2) {
				System.out.println("buttons NO." + i + "Name is: " + listOfFiles[i- 1].getName());
				buttons[i].setName(listOfFiles[i - 1].getName());
				buttons[i].setText(listOfFiles[i - 1].getName());
			}
			else {
				buttons[start].setName(listOfFiles[0].getName());
				buttons[start].setText(listOfFiles[0].getName());
				buttons[end - 1].setName(listOfFiles[1].getName());
				buttons[end - 1].setText(listOfFiles[1].getName());
			}
		}
		
	}


}
