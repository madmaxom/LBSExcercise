package at.fhooe.mcm.cas.contexttype;

public class ContextFuel {
    private int fuelStatus = 0;

    public ContextFuel(int fuelStatus) {
        this.fuelStatus = fuelStatus;
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
}
