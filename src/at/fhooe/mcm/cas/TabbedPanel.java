package at.fhooe.mcm.cas;


import javax.swing.JFrame;
import javax.swing.JTabbedPane;

public class TabbedPanel extends JFrame{
	
	JTabbedPane jtp;
	
	public TabbedPanel() {
		
        setTitle("CAS-Exercise");
        jtp = new JTabbedPane();
        getContentPane().add(jtp);
    }
	
	public void insertPanel(IComponent c) {
		if(c.getView() != null)
			jtp.insertTab(c.getName(), null, c.getView(), null, jtp.getTabCount());
	}

	public void removePanel(IComponent icomp) {
		jtp.remove(icomp.getView());
	}
	
	public int getCount() {
		return jtp.getTabCount();
	}
}
