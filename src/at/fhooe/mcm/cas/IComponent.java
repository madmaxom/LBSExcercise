package at.fhooe.mcm.cas;

import java.awt.Panel;

public abstract class IComponent implements CommunicationObserver {
	
	public IMediator mMediator;
	private String mName;
	
	public IComponent(IMediator mediator, String name) {
		mMediator = mediator;
		mName = name;
	}
	
	public void setMediator(IMediator mediator) {
		mMediator = mediator;
	}
	
	public String getName() {
		return mName;
	}
	
	public abstract Panel getView();

	public abstract void setDrawingContext(String s);
}
