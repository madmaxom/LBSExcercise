package at.fhooe.mcm.cas;

import java.awt.Panel;

public abstract class IComponent {
	
	private IMediator mMediator;
	private String mName;
	
	public IComponent(IMediator mediator, String name) {
		mMediator = mediator;
		mName = name;
	}
	
	public String getName() {
		return mName;
	}
	
	
	public abstract Panel getView();

}
