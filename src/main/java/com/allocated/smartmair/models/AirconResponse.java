package com.allocated.smartmair.models;

/**
 * @author Nick Hobbelink
 */
public abstract class AirconResponse {
    private String command;
    private String apiVer;
    private String operatorId;
    private String deviceId;
    private long timestamp;
    private int result;
    
    public String getCommand() {
        return command;
    }
    public String getApiVer() {
        return apiVer;
    }
    
    public String getOperatorId() {
        return operatorId;
    }
    
    public String getDeviceId() {
        return deviceId;
    }
    
    public long getTimestamp() {
        return timestamp;
    }
    
    public int getResult() {
        return result;
    }
}
