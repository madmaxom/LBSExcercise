package at.fhooe.mcm.cas.contexttype;

public class ContextFuel extends ContextElement {
    private int fuelStatus = 0;

    public ContextFuel() {
    	setType(ContextElementType.CONTEXT_ELEMENT_FUEL);
    }
    
    public ContextFuel(int fuelStatus) {
        this.fuelStatus = fuelStatus;
        setType(ContextElementType.CONTEXT_ELEMENT_FUEL);
    }

    public int getFuelStatus() {
        return fuelStatus;
    }

    public void setFuelStatus(int fuelStatus) {
        this.fuelStatus = fuelStatus;
    }

    @Override
    public String toString() {
        return "ContextFuel{" +
                "fuelStatus=" + fuelStatus +
                '}';
    }

	@Override
	public void setData(ContextElement ce) {
		this.setContextMetaData(ce.getContextMetaData());
		this.setId(ce.getId());
		this.setKey(ce.getKey());
		this.fuelStatus = ((ContextFuel)ce).fuelStatus;
	}
}
