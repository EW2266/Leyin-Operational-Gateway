import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URISyntaxException;

import javax.swing.JButton;
import javax.swing.JFrame;

public class systempanel {
	user user;
	JFrame uiframe;
	JButton btn1, btn2, btn3, btn4;
	
	public systempanel(user user) {
		uiframe = new JFrame("EW pre alpha ver.");
		
		this.user = user;
		startframe();
	}
	
	private void startframe() {
		btn1 = new JButton("乐印");
		btn1.setBounds(0,0,500,500);
		
		
		uiframe.add(btn1);
		
		uiframe.setLayout(null);   
		uiframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		uiframe.setLocationRelativeTo(null);
		uiframe.setPreferredSize(new Dimension(500,500));
		uiframe.setResizable(false);
		uiframe.pack();
		uiframe.setVisible(true);
		btn1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("clicked");
				frame2();
			}
		});
	}
	
	private void frame2() {
		System.out.println("second level");
		btn2 = new JButton("文件管理");
		btn3 = new JButton("仓库管理");
		btn4 = new JButton("待办工作");
		
		btn1.setBounds(175, 300, 150, 150);		
		btn2.setBounds(175, 100, 150, 150);
		btn3.setBounds(0, 100, 150, 150);
		btn4.setBounds(350, 100, 150, 150);
		
		uiframe.add(btn2);
		uiframe.add(btn3);
		uiframe.add(btn4);
		
		
		uiframe.pack();
		
		uiframe.repaint();
		
		uiframe.setVisible(true);
		btn2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					FileExplorer system = new FileExplorer();
					system.getUser(user);
					uiframe.setVisible(false);
					uiframe.dispose();
					system.setVisible(true);
				} catch (URISyntaxException e1) {
					
					e1.printStackTrace();
				}
			}
		});
		btn3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//FileExplorer system = new FileExplorer();
				warehouse system = new warehouse(user);
				uiframe.setVisible(false);
				uiframe.dispose();
			}
		});
		btn4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//FileExplorer system = new FileExplorer();
				
				uiframe.setVisible(false);
				uiframe.dispose();
			}
		});
	}
}
