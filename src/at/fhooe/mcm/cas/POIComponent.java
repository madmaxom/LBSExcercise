package at.fhooe.mcm.cas;

import java.awt.Panel;

import at.fhooe.mcm.cas.poi.POIView;

public class POIComponent extends IComponent {

	private Panel mPanel;
	
	public POIComponent(IMediator mediator, String name) {
		super(mediator, name);
		
		POIView v = new POIView();
		mPanel = v.getPanel();
	}
	
	@Override
	public Panel getView() {
		return mPanel;
	}

}
