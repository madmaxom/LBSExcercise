package at.fhooe.mcm.cas;

import java.awt.Panel;

import at.fhooe.mcm.cas.poi.POIView;

public class POIComponent implements IComponent {

	private String mName;
	private Panel mPanel;
	
	public POIComponent() {
		mName = "POI";
		
		POIView v = new POIView();
		mPanel = v.getPanel();
	}
	
	@Override
	public Panel getView() {
		return mPanel;
	}

	@Override
	public String getName() {
		return mName;
	}

}
