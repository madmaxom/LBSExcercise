package at.fhooe.mcm.cas.contexttype;

public class ContextPosition extends ContextElement {
    private String coordinateType = "";
    private double xValue = 0;
    private double yValue = 0;

    public String getCoordinateType() {
        return coordinateType;
    }

    public void setCoordinateType(String coordinateType) {
        this.coordinateType = coordinateType;
    }

    public double getxValue() {
        return xValue;
    }

    public void setxValue(double xValue) {
        this.xValue = xValue;
    }

    public double getyValue() {
        return yValue;
    }

    public void setyValue(double yValue) {
        this.yValue = yValue;
    }

    public ContextPosition() {
    	setType(ContextElementType.CONTEXT_ELEMENT_POSITION);
    }
    
    public ContextPosition(String coordinateType, double xValue, double yValue) {
        this.coordinateType = coordinateType;
        this.xValue = xValue;
        this.yValue = yValue;
        setType(ContextElementType.CONTEXT_ELEMENT_POSITION);
    }

    @Override
    public String toString() {
        return "ContextPosition{" +
                "coordinateType='" + coordinateType + '\'' +
                ", xValue=" + xValue +
                ", yValue=" + yValue +
                '}';
    }
    
    @Override
	public void setData(ContextElement ce) {
		this.setContextMetaData(ce.getContextMetaData());
		this.setId(ce.getId());
		this.setKey(ce.getKey());
		ContextPosition cp = (ContextPosition)ce;
		this.xValue = cp.xValue;
		this.yValue = cp.yValue;
		this.coordinateType = cp.coordinateType;
	}
}
