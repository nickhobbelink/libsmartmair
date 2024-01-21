package com.allocated.smartmair.models.request;

/**
 * @author Nick Hobbelink
 */
public class GetAirconStatRequest extends AirconRequest {
    private String airconId;
    
    public GetAirconStatRequest() {
        setCommand("getAirconStat");
    }
    
    public GetAirconStatRequest(String airconId) {
        this();
        this.airconId = airconId;
    }

    public String getAirconId() {
        return airconId;
    }
    
    public void setAirconId(String airconId) {
        this.airconId = airconId;
    }
}
