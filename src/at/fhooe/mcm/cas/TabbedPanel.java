package at.fhooe.mcm.cas;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class TabbedPanel extends JFrame{
	public TabbedPanel() {
		
        setTitle("CAS-Exercise");
        JTabbedPane jtp = new JTabbedPane();
        getContentPane().add(jtp);
        JPanel jp1 = new JPanel();
        JPanel jp2 = new JPanel();
        JPanel jp3 = new JPanel();
        jtp.addTab("Map", jp1);
        jtp.addTab("Position", jp2);
        jtp.addTab("POI", jp3);
    }
}
