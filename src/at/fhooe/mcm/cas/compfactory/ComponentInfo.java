package at.fhooe.mcm.cas.compfactory;

public class ComponentInfo {
	private String name;
	private String drawingContext;
	private String uiView;
	
	public ComponentInfo(String name, String drawingContext, String uiView) {
		this.name = name;
		this.drawingContext = drawingContext;
		this.uiView = uiView;
	}

	public String getName() {
		return name;
	}

	public String getDrawingContext() {
		return drawingContext;
	}

	public String getUiView() {
		return uiView;
	}
}
