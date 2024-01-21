package com.allocated.smartmair.models;

/**
 * @author Nick Hobbelink
 */
public class DeviceInfoResponse extends AirconResponse {
    private Contents contents;
    
    public Contents getContents() {
        return contents;
    }
    
    public static class Contents{
        private String airconId;
        private String macAddress;
        private Integer apMode;
        
        public String getAirconId() {
            return airconId;
        }
        
        public String getMacAddress() {
            return macAddress;
        }
        
        public Integer getApMode() {
            return apMode;
        }
    }
}
