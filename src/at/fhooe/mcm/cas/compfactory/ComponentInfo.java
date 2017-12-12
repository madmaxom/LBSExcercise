package at.fhooe.mcm.cas.compfactory;

public class ComponentInfo {
	private String name;
	private String drawingContext;
	
	public ComponentInfo(String name, String drawingContext) {
		this.name = name;
		this.drawingContext = drawingContext;
	}

	public String getName() {
		return name;
	}

	public String getDrawingContext() {
		return drawingContext;
	}
}
