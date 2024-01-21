package com.allocated.smartmair.models;

/**
 * @author Nick Hobbelink
 */
public class HomeLeaveMode {
    private int airFlow;
    private double tempRule;
    private double tempSetting;
    
    public int getAirFlow() {
        return airFlow;
    }
    
    public void setAirFlow(int airFlow) {
        this.airFlow = airFlow;
    }
    
    public double getTempRule() {
        return tempRule;
    }
    
    public void setTempRule(double tempRule) {
        this.tempRule = tempRule;
    }
    
    public double getTempSetting() {
        return tempSetting;
    }
    
    public void setTempSetting(double tempSetting) {
        this.tempSetting = tempSetting;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + airFlow;
        long temp;
        temp = Double.doubleToLongBits(tempRule);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(tempSetting);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        HomeLeaveMode other = (HomeLeaveMode) obj;
        if (airFlow != other.airFlow)
            return false;
        if (Double.doubleToLongBits(tempRule) != Double.doubleToLongBits(other.tempRule))
            return false;
        if (Double.doubleToLongBits(tempSetting) != Double.doubleToLongBits(other.tempSetting))
            return false;
        return true;
    }
    
    @Override
    public String toString() {
        return "{airFlow=" + airFlow + ",tempRule=" + tempRule + ",tempSetting=" + tempSetting + "}";
    }
}
