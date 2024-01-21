package com.allocated.smartmair;

import java.io.IOException;

import com.allocated.smartmair.models.AirconStatResponse;
import com.allocated.smartmair.models.DeviceInfoResponse;
import com.allocated.smartmair.models.UpdateAccountInfoResponse;
import com.allocated.smartmair.models.request.GetAirconStatRequest;
import com.allocated.smartmair.models.request.GetDeviceInfoRequest;
import com.allocated.smartmair.models.request.SetAirconStatRequest;
import com.allocated.smartmair.models.request.UpdateAccountInfoRequest;
import com.allocated.smartmair.util.HttpClient;
import com.allocated.smartmair.util.JsonUtil;

/**
 * @author Nick Hobbelink
 * 
 * Java client to control Mitsubishi air conditioners over a local network
 */
public class SmartMAirClient {
    private static final String PROTOCOL = "http://";
    private static final String BASE_PATH = ":51443/beaver/command";
    private final HttpClient httpClient;
    private final String ip;
    
    public SmartMAirClient(HttpClient httpClient, String ip) {
        super();
        this.httpClient = httpClient;
        this.ip = ip;
    }
    
    public AirconStatResponse getAirconStat(GetAirconStatRequest request) throws IOException {
        String url = PROTOCOL + ip + BASE_PATH + "/getAirconStat";
        String body = JsonUtil.toString(request);
        String json = httpClient.postJson(url, body);
        return JsonUtil.parse(json, AirconStatResponse.class);
    }

    public AirconStatResponse setAirconStat(SetAirconStatRequest request) throws IOException {
        String url = PROTOCOL + ip + BASE_PATH + "/setAirconStat";
        String body = JsonUtil.toString(request);
        String json = httpClient.postJson(url, body);
        return JsonUtil.parse(json, AirconStatResponse.class);
    }
    
    public UpdateAccountInfoResponse updateAccountInfo(UpdateAccountInfoRequest request) throws IOException {
        String url = PROTOCOL + ip + BASE_PATH + "/updateAccountInfo";
        String body = JsonUtil.toString(request);
        String json = httpClient.postJson(url, body);
        return JsonUtil.parse(json, UpdateAccountInfoResponse.class);
    }
    
    public DeviceInfoResponse getDeviceInfo(GetDeviceInfoRequest request) throws IOException {
        String url = PROTOCOL + ip + BASE_PATH + "/getDeviceInfo";
        String body = JsonUtil.toString(request);
        String json = httpClient.postJson(url, body);
        return JsonUtil.parse(json, DeviceInfoResponse.class);
    }
}
