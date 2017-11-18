package at.fhooe.mcm.cas.aal;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Panel;

public class AALView {

	private Panel mPanel;
	private AALController mController;
	
	public AALView(AALController controller) {
		
		mController = controller;
		
		Button btnStart = new Button("Load from FileSystem");
		btnStart.setName("btnLoadFileSystem");
		btnStart.addActionListener(controller);
       
		
		mPanel = new Panel(new BorderLayout());

		mPanel.add(btnStart, BorderLayout.NORTH);
	}
	

	public Panel getPanel() {
		return mPanel;
	}
}

