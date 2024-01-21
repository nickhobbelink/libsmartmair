package com.allocated.smartmair.models.request;

/**
 * @author Nick Hobbelink
 */
public class GetDeviceInfoRequest extends AirconRequest {
    public GetDeviceInfoRequest() {
        setCommand("getDeviceInfo");
    }
}
