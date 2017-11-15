package at.fhooe.mcm.cas.contexttype;

public class ContextPosition {
    private String coordinateType = "";
    private int xValue = 0;
    private int yValue = 0;

    public String getCoordinateType() {
        return coordinateType;
    }

    public void setCoordinateType(String coordinateType) {
        this.coordinateType = coordinateType;
    }

    public int getxValue() {
        return xValue;
    }

    public void setxValue(int xValue) {
        this.xValue = xValue;
    }

    public int getyValue() {
        return yValue;
    }

    public void setyValue(int yValue) {
        this.yValue = yValue;
    }

    public ContextPosition(String coordinateType, int xValue, int yValue) {

        this.coordinateType = coordinateType;
        this.xValue = xValue;
        this.yValue = yValue;
    }

    @Override
    public String toString() {
        return "ContextPosition{" +
                "coordinateType='" + coordinateType + '\'' +
                ", xValue=" + xValue +
                ", yValue=" + yValue +
                '}';
    }
}