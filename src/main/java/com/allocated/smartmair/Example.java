package com.allocated.smartmair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.allocated.smartmair.enums.Model;
import com.allocated.smartmair.enums.Operation;
import com.allocated.smartmair.enums.OperationMode;
import com.allocated.smartmair.models.AirconState;
import com.allocated.smartmair.models.AirconStatResponse;
import com.allocated.smartmair.models.request.GetAirconStatRequest;
import com.allocated.smartmair.models.request.SetAirconStatRequest;
import com.allocated.smartmair.models.request.UpdateAccountInfoRequest;
import com.allocated.smartmair.util.UrlConnectionHttpClient;

/**
 * @author Nick Hobbelink
 */
public class Example {
    private static final String ACCOUNT_ID = "ead9f351-4f96-4e1d-a5d8-98d9ddd3e26c";
    private static final String DEVICE_ID = "73d4c7b6-204d-4a3c-a43f-aae42faea64a";
    private static final AirconStateCoder STAT_CODER = new AirconStateCoder();

    private final UrlConnectionHttpClient httpClient;
    private final BufferedReader reader;
    private SmartMAirClient client;
    private boolean valid;
    private boolean accountUpdated;
    
    public static void main(String[] args) throws IOException {
        new Example().runExample();
    }
    
    public Example() {
        httpClient = new UrlConnectionHttpClient();
       
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    private void runExample() throws IOException {
        System.out.print("Aircon ip: ");
        
        String airconIp = reader.readLine();
        client = new SmartMAirClient(httpClient, airconIp);
        
        prompt();
    }
    
    private void prompt() throws IOException {
        valid = true;
        while(valid) {
            System.out.println("Commands: stat,mode,temp,operation,exit");
            System.out.print("Command: ");
            String command = reader.readLine();
            switch(command) {
                case "exit":
                    System.out.println("Bye 👋👋👋");
                    valid = false;
                break;
                case "stat":
                    printAirconStat();
                break;
                case "temp":
                    changeTemp();
                break;
                case "operation":
                    changeOperation();
                break;
                case "mode":
                    changeMode();
                break;
            }
            System.out.println();
        }
    }
    
    private void changeOperation() throws IOException {
        print("Operation states: %s", describeOperation());
        try {
            System.out.print("New operation state: ");
            int operation = readInteger(0, OperationMode.values().length - 1);
            
            print("Requesting current state... ⚙️⚙️⚙️");
            AirconStatResponse statResponse = requestStat();
            AirconState stat = parseStat(statResponse);
            
            stat.setOperation(operation);
            String airconId = statResponse.getContents().getAirconId();
            
            if(requestSetAirconStat(airconId, stat).getResult() == 0) {
                print("Successfully changed aircon operation state");
            } else {
                error("Failed to change aircon operation state");
            }
        } catch(ParseException e) {
            return;
        }
    }
    
    private void changeMode() throws IOException {
        print("Modes: %s", describeModes());
        try {
            System.out.print("New mode: ");
            int mode = readInteger(0, OperationMode.values().length - 1);
            
            print("Requesting current state... ⚙️⚙️⚙️");
            AirconStatResponse statResponse = requestStat();
            AirconState stat = parseStat(statResponse);
            
            stat.setOperationMode(mode);
            String airconId = statResponse.getContents().getAirconId();
            
            if(requestSetAirconStat(airconId, stat).getResult() == 0) {
                print("Successfully changed aircon mode");
            } else {
                error("Failed to change aircon mode");
            }
        } catch(ParseException e) {
            return;
        }
    }
    
    private void changeTemp() throws IOException {
        System.out.print("New temperature: ");
        
        double temp;
        try {
            temp = readDouble();
            
            print("Requesting current state... ⚙️⚙️⚙️");
            AirconStatResponse statResponse = requestStat();
            AirconState stat = parseStat(statResponse);
            
            stat.setPresetTemp(temp);
            String airconId = statResponse.getContents().getAirconId();
            
            AirconStatResponse response = requestSetAirconStat(airconId, stat);
            AirconState responseStat = parseStat(response);
            if(response.getResult() == 0) {
                print("Successfully changed aircon temperature, new temperature is %.1f", responseStat.getPresetTemp());
            } else {
                error("Failed to change aircon temperature");
            }
        } catch (ParseException e) {
            return;
        }
        
    }

    private AirconStatResponse requestSetAirconStat(String airconId, AirconState stat) throws IOException {
        checkAccount(airconId);
        
        print("Changing state... ⚙️⚙️⚙️");
        
        SetAirconStatRequest.Contents contents = new SetAirconStatRequest.Contents();
        contents.setAirconStat(new AirconStateCoder().toBase64(stat, stat));
        contents.setAirconId(airconId);
        contents.setAutoHeating(0);
        
        SetAirconStatRequest setAirconStat = new SetAirconStatRequest();
        setAirconStat.setOperatorId(ACCOUNT_ID);
        setAirconStat.setDeviceId(DEVICE_ID);
        setAirconStat.setContents(contents);
        
        return client.setAirconStat(setAirconStat);
    }
    
    private void checkAccount(String airconId) throws IOException {
        if(!accountUpdated) {
            print("Updating account... ⚙️⚙️⚙️");
            
            UpdateAccountInfoRequest.Contents accountInfo = new UpdateAccountInfoRequest.Contents(airconId, ACCOUNT_ID, 0, "Europe/Amsterdam");
            
            UpdateAccountInfoRequest updateAccountInfo = new UpdateAccountInfoRequest(accountInfo);
            updateAccountInfo.setOperatorId(ACCOUNT_ID);
            updateAccountInfo.setDeviceId(DEVICE_ID);
            updateAccountInfo.setContents(accountInfo);
            
            client.updateAccountInfo(updateAccountInfo);
            
            accountUpdated = true;
        }
    }

    private int readInteger(int min, int max) throws IOException, ParseException {
        String line = reader.readLine();
        try {
            int value = Integer.parseInt(line);
            if(value < min || value > max) {
                error("Invalid input %s", line);
            }
            return value;
        } catch(NumberFormatException e) {
            error("Error parsing %s", line);
            throw new ParseException();
        }
    }
    
    private double readDouble() throws IOException, ParseException {
        String line = reader.readLine();
        try {
            return Double.parseDouble(line);
        } catch(NumberFormatException e) {
            error("Error parsing %s", line);
            throw new ParseException();
        }
    }
    
    private class ParseException extends Exception{
        private static final long serialVersionUID = 1L;
    }

    private Object describeModes() {
        List<String> modes = Arrays.asList(OperationMode.values())
                .stream()
                .map(m -> String.format("%d = %s", m.getValue(), m.toString()))
                .collect(Collectors.toList());
        return String.join(", ", modes);
    }
    
    private Object describeOperation() {
        List<String> modes = Arrays.asList(Operation.values())
                .stream()
                .map(m -> String.format("%d = %s", m.getValue(), m.toString()))
                .collect(Collectors.toList());
        return String.join(", ", modes);
    }

    private void printAirconStat() throws IOException {
        System.out.println("Requesting stats... ⚙️⚙️⚙️");
        
        AirconStatResponse response = requestStat();
        
        AirconState stat = parseStat(response);
        printAirconStat(response, stat);
    }
    
    private AirconState parseStat(AirconStatResponse response) {
        return STAT_CODER.fromBase64(new AirconState(), response.getContents().getAirconStat());
    }
    
    private AirconStatResponse requestStat() throws IOException {
        GetAirconStatRequest request = new GetAirconStatRequest();
        request.setOperatorId(ACCOUNT_ID);
        request.setDeviceId(DEVICE_ID);
        return client.getAirconStat(request);
    }

    private void printAirconStat(AirconStatResponse response, AirconState stat) {
        print("Aircon ID is %s", response.getContents().getAirconId());
        print("Auto heating: %d", response.getContents().getAutoHeating());
        print("LED: %d", response.getContents().getLedStat());
        print("Operation: %d (%s)", stat.getOperation(), Operation.values()[stat.getOperation()]);
        print("Operation mode: %d (%s)", stat.getOperationMode(), OperationMode.values()[stat.getOperationMode()]);
        print("Airflow: %d", stat.getAirFlow());
        print("LR wind direction: %d", stat.getWindDirectionLR());
        print("UD wind direction: %d", stat.getWindDirectionUD());
        print("Model No: %d (%s)", stat.getModelNo(), Model.values()[stat.getModelNo()]);
        print("Error code: %s", stat.getErrorCode());
        print("Preset temperature: %.1f °C", stat.getPresetTemp());
        print("Indoor temperature: %.1f °C", stat.getIndoorTemp());
        print("Outdoor temperature: %.1f °C", stat.getOutdoorTemp());
        
    }

    private void print(String format, Object... args) {
        System.out.println(String.format(format, args));
    }

    private void error(String format, Object... args) {
        System.err.println(String.format(format, args));
    }

}
