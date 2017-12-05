package at.fhooe.mcm.cas.contexttype;

public class ContextPressure extends ContextElement {
	private int pressure = 0;

    public ContextPressure() {
    	setType(ContextElementType.CONTEXT_ELEMENT_PRESSURE);
    }
    
    public ContextPressure(int pressure) {
        this.pressure = pressure;
        setType(ContextElementType.CONTEXT_ELEMENT_PRESSURE);
    }

    public int getPressure() {
        return pressure;
    }

    public void setPressure(int pressure) {
        this.pressure = pressure;
    }

    @Override
    public String toString() {
        return "ContextHumidity{" +
                "pressure=" + pressure +
                '}';
    }
    
    @Override
	public void setData(ContextElement ce) {
		this.setContextMetaData(ce.getContextMetaData());
		this.setId(ce.getId());
		this.setKey(ce.getKey());
		this.pressure = ((ContextPressure)ce).pressure;
	}
}
