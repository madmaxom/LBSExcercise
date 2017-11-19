package at.fhooe.mcm.cas.aal;

import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.KeyEvent;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class AALView {

	private Panel mPanel;
	private AALController mController;
	
	public AALView(AALController controller) {
		
		mController = controller;
		
		JPanel radioPanel = new JPanel(new GridLayout(0, 1));
	    ButtonGroup group = new ButtonGroup();
	    
	    JRadioButton rbXml = new JRadioButton("Xml");
	    rbXml.setActionCommand("Xml");
	    rbXml.setSelected(true);
	    rbXml.setName("rbXml");
	    rbXml.addActionListener(mController);
		group.add(rbXml);
		radioPanel.add(rbXml);
		
	    JRadioButton rbJson = new JRadioButton("Json");
	    rbJson.setActionCommand("Json");
	    rbJson.setName("rbJson");
	    rbJson.setSelected(false);
	    rbJson.addActionListener(mController);
		group.add(rbJson);
		radioPanel.add(rbJson);
		
		Button btnLoadFileSystem = new Button("Load from FileSystem");
		btnLoadFileSystem.setName("btnLoadFileSystem");
		btnLoadFileSystem.addActionListener(controller);
		
		Button btnLoadServer = new Button("Load from Server");
		btnLoadServer.setName("btnLoadServer");
		btnLoadServer.addActionListener(controller);
       
		
		mPanel = new Panel(new FlowLayout());
		mPanel.add(btnLoadServer, FlowLayout.LEFT);
		mPanel.add(btnLoadFileSystem, FlowLayout.LEFT);
		mPanel.add(radioPanel, FlowLayout.LEFT);
	}
	

	public Panel getPanel() {
		return mPanel;
	}
}

