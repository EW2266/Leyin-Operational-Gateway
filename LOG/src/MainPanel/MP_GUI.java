package MainPanel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import login.User;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.CompoundBorder;
import java.awt.Color;

public class MP_GUI extends JFrame implements ActionListener{

	
	public MP_GUI(User user) {
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.setTitle("Leyin -- Alpha Test");
		
		//buttons for interaction
		JButton b1 = new JButton("日程");
		b1.setBounds(0, 620, 100, 70);
		b1.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		this.getContentPane().add(b1);
		
		JButton b2 = new JButton("任务");
		b2.setBounds(300, 620, 100, 70);
		this.getContentPane().add(b2);
		b2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		JButton main1 = new JButton("云端储存");
		main1.setBackground(Color.WHITE);
		main1.setBounds(0, 200, 320, 300);
		this.getContentPane().add(main1);
		
		JButton main2 = new JButton("数据管理");
		main2.setBackground(Color.WHITE);
		main2.setBounds(320, 200, 320, 300);
		this.getContentPane().add(main2);
		
		JButton main3 = new JButton("仓库管理");
		main3.setForeground(Color.BLACK);
		main3.setBackground(Color.GRAY);
		main3.setBounds(640, 200, 320, 300);
		this.getContentPane().add(main3);
		
		JButton main4 = new JButton("家校管理");
		main4.setForeground(Color.BLACK);
		main4.setBackground(Color.GRAY);
		main4.setBounds(960, 200, 320, 300);
		this.getContentPane().add(main4);
		
		
		//Labels for showing information
		
		JLabel dateLabel = new JLabel("日期：" + LocalDate.now());
		dateLabel.setBounds(1100, 655, 132, 35);
		this.getContentPane().add(dateLabel);
		
		Clock clock = Clock.systemDefaultZone();
		LocalTime t = LocalTime.now(clock);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
		
		JLabel timeLabel = new JLabel("时间：" + t.format(formatter));
		timeLabel.setBounds(1100, 620, 132, 35);
		this.getContentPane().add(timeLabel);
		
		JLabel username = new JLabel(user.getname());
		username.setBorder(new CompoundBorder());
		username.setBounds(750, 620, 100, 70);
		this.getContentPane().add(username);
		
		JLabel userperm = new JLabel(perm(user.getperm()));
		userperm.setBounds(925, 620, 100, 70);
		this.getContentPane().add(userperm);
		
		JLabel blank = new JLabel("blank");
		this.getContentPane().add(blank);
		System.out.println("");
		
		//creating the pane
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setPreferredSize(new Dimension(1280, 720));
		this.setResizable(false);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
		//setTime(panel, timeLabel);
		
		
	}

	/*
	 
	private void setTime(JLabel label) {
		while(true) {
			Clock clock = Clock.systemDefaultZone();
			LocalTime t = LocalTime.now(clock);
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
			label.setText("时间： " + t.format(formatter));
			System.out.println(t.format(formatter));

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	*/
	
	private String perm(int i) {
		String s = "";
		if(i == 1) s = "管理员";
		else s = "普通用户";
		return s;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
