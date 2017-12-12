package at.fhooe.mcm.cas.poi;

import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;


import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import at.fhooe.mcm.cas.IUIView;


public class POIView {

	private Panel mPanel;
	
	private List<String> mPOIObjects;
	
	public POIView() {
		
		mPOIObjects = getPOIs();
		
		
        JPanel radioPanel = new JPanel(new GridLayout(0, 1));
	    ButtonGroup group = new ButtonGroup();
		for (String s : mPOIObjects) {
			JRadioButton rb = new JRadioButton(s);
			rb.setMnemonic(KeyEvent.VK_B);
			rb.setActionCommand(s);
			rb.setSelected(false);
			group.add(rb);
			radioPanel.add(rb);
		}
		
		mPanel = new Panel();
		mPanel.add(radioPanel);
	}
	
	private ArrayList<String> getPOIs() {
		ArrayList<String> list = new ArrayList<String>();
		
		list.add("POI1");
		list.add("POI2");
		
		return list;
		
	}

	public Panel getPanel() {
		return mPanel;
	}
}
