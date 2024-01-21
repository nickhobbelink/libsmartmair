package com.allocated.smartmair.models.request;

/**
 * @author Nick Hobbelink
 */
public class AirconRequest {
    private String apiVer = "1.0";
    private String command;
    private String deviceId;
    private String operatorId;
    private long timestamp;
    
    protected AirconRequest() {
        timestamp = System.currentTimeMillis() / 1000L;
    }
    
    public String getApiVer() {
        return apiVer;
    }
    
    public void setApiVer(String apiVer) {
        this.apiVer = apiVer;
    }
    
    public String getCommand() {
        return command;
    }
    
    protected void setCommand(String command) {
        this.command = command;
    }
    
    public String getDeviceId() {
        return deviceId;
    }
    
    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }
    
    public String getOperatorId() {
        return operatorId;
    }
    
    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }
    
    public long getTimestamp() {
        return timestamp;
    }
    
    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
