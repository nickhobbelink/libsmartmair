package com.allocated.smartmair.models.request;

/**
 * @author Nick Hobbelink
 */
public class SetAirconStatRequest extends AirconRequest {
    private String airconId;
    private Contents contents;
    
    public SetAirconStatRequest() {
        setCommand("setAirconStat");
    }
    
    public SetAirconStatRequest(String airconId) {
        this();
        this.airconId = airconId;
    }

    public String getAirconId() {
        return airconId;
    }
    
    public void setAirconId(String airconId) {
        this.airconId = airconId;
    }
    
    public Contents getContents() {
        return contents;
    }
    
    public void setContents(Contents contents) {
        this.contents = contents;
    }
    
    public static class Contents {
        private String airconId;
        private String airconStat;
        private Integer autoHeating;
        
        public String getAirconId() {
            return airconId;
        }

        public void setAirconId(String airconId) {
            this.airconId = airconId;
        }
        
        public String getAirconStat() {
            return airconStat;
        }
        
        public void setAirconStat(String airconStat) {
            this.airconStat = airconStat;
        }
        
        public Integer getAutoHeating() {
            return autoHeating;
        }

        public void setAutoHeating(Integer autoHeating) {
            this.autoHeating = autoHeating;
        }
        
    }
}
