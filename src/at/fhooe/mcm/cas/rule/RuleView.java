package at.fhooe.mcm.cas.rule;

import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Panel;

import at.fhooe.mcm.cas.IUIView;

public class RuleView {
	private Panel mPanel;
	private RuleController mController;
	
	public RuleView(RuleController controller) {
		
		mController = controller;
		  
	    
		Button btnLoadFileSystem = new Button("Load from FileSystem");
		btnLoadFileSystem.setName("btnLoadFileSystem");
		btnLoadFileSystem.addActionListener(controller);
		      
		
		mPanel = new Panel(new FlowLayout());
		mPanel.add(btnLoadFileSystem, FlowLayout.LEFT);
	}
	
	public Panel getPanel() {
		return mPanel;
	}
}
