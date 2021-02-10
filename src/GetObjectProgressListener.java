
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JProgressBar;

import com.aliyun.oss.event.ProgressEvent;
import com.aliyun.oss.event.ProgressEventType;
import com.aliyun.oss.event.ProgressListener;

class GetObjectProgressListener implements ProgressListener {
	private long bytesRead = 0;
	private long totalBytes = -1;
	private boolean succeed = false;
	private JFrame f;
	private JProgressBar bar;
	
	public GetObjectProgressListener(JFrame frame, JProgressBar pb) {
		// TODO Auto-generated constructor stub
		f = frame;
		bar = pb;
		f.setVisible(true);
		JProgressBar bar = new JProgressBar();
		bar.setBounds(50, 50, 500, 100);
		bar.setStringPainted(true);
		bar.setVisible(true);
		f.add(bar);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		f.setLocation(dim.width/2-f.getSize().width/2, dim.height/2-f.getSize().height/2);
		f.setLocationRelativeTo(null);
		f.setPreferredSize(new Dimension(600,300));
		f.setResizable(false);
		f.setLayout(null);
	}
	private void update() {
		f.pack();
		f.revalidate();
		f.repaint();
	}
	
	@Override
	public void progressChanged(ProgressEvent progressEvent) {
		
		
		long bytes = progressEvent.getBytes();
		ProgressEventType eventType = progressEvent.getEventType();
		
		//ProgressMonitor monitor = new ProgressMonitor(bar, "Downloading", null, 0, 100);
		switch (eventType) {
		case TRANSFER_STARTED_EVENT:
			
			//monitor.setNote("Start to download......");
			break;
		case RESPONSE_CONTENT_LENGTH_EVENT:
			this.totalBytes = bytes;
			//monitor.setNote(this.totalBytes + " bytes in total will be downloaded to a local file");
			break;
		case RESPONSE_BYTE_TRANSFER_EVENT:
			this.bytesRead += bytes;
			if (this.totalBytes != -1) {
				int percent = (int)(this.bytesRead * 100.0 / this.totalBytes);
				bar.setValue(percent);
				try {
					Thread.sleep(100);
				} catch(Exception e) {
					
				}
				update();
				
				System.out.println(bytes + " bytes have been read at this time, download progress: " +
						percent + "%(" + this.bytesRead + "/" + this.totalBytes + ")");
			} else {
				//monitor.setNote(bytes + " bytes have been read at this time, download ratio: unknown" +
						//"(" + this.bytesRead + "/...)");
			}
			break;
		case TRANSFER_COMPLETED_EVENT:
			this.succeed = true;
			//monitor.setNote("Succeed to download, " + this.bytesRead + " bytes have been transferred in total");
			break;
		case TRANSFER_FAILED_EVENT:
			//monitor.setNote("Failed to download, " + this.bytesRead + " bytes have been transferred");
			break;
		default:
			break;
		}
	}
	public boolean isSucceed() {
		return succeed;
	}
	
}

