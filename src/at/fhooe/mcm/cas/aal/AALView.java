package at.fhooe.mcm.cas.aal;

import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Panel;

public class AALView {

	private Panel mPanel;
	private AALController mController;
	
	public AALView(AALController controller) {
		
		mController = controller;
		
		Button btnLoadFileSystem = new Button("Load from FileSystem");
		btnLoadFileSystem.setName("btnLoadFileSystem");
		btnLoadFileSystem.addActionListener(controller);
		
		Button btnLoadServer = new Button("Load from Server");
		btnLoadServer.setName("btnLoadServer");
		btnLoadServer.addActionListener(controller);
       
		
		mPanel = new Panel(new FlowLayout());
		mPanel.add(btnLoadServer, FlowLayout.LEFT);
		mPanel.add(btnLoadFileSystem, FlowLayout.LEFT);
	}
	

	public Panel getPanel() {
		return mPanel;
	}
}

