package com.allocated.smartmair.models;

/**
 * @author Nick Hobbelink
 * 
 * Aircon state going through the getAirconStat/setAirconStat endpoints
 */
public class AirconState {
    private int operation;
    private double presetTemp;
    private int operationMode;
    private int airFlow;
    private int windDirectionUD;
    private int windDirectionLR;
    private int entrust;
    private int modelNo;
    private boolean selfCleanOperation;
    private boolean selfCleanReset;
    private boolean vacantProperty;
    private boolean canHomeLeaveModeStatusRequest;
    private HomeLeaveMode homeLeaveModeForCooling;
    private HomeLeaveMode homeLeaveModeForHeating;
    private String errorCode;
    private int coolHotJudge;
    private double indoorTemp;
    private double outdoorTemp;

    public int getOperation() {
        return operation;
    }
    
    public AirconState setOperation(int operation) {
        this.operation = operation;
        return this;
    }
    
    public double getPresetTemp() {
        return presetTemp;
    }

    public void setPresetTemp(double presetTemp) {
        this.presetTemp = presetTemp;
    }
    
    public int getOperationMode() {
        return operationMode;
    }

    public AirconState setOperationMode(int operationMode) {
        this.operationMode = operationMode;
        return this;
    }
    
    public int getAirFlow() {
        return airFlow;
    }

    public AirconState setAirFlow(int airFlow) {
        this.airFlow = airFlow;
        return this;
    }
    
    public int getWindDirectionUD() {
        return windDirectionUD;
    }

    public AirconState setWindDirectionUD(int windDirectionUD) {
        this.windDirectionUD = windDirectionUD;
        return this;
    }
    
    public int getWindDirectionLR() {
        return windDirectionLR;
    }
    
    public AirconState setWindDirectionLR(int windDirectionLR) {
        this.windDirectionLR = windDirectionLR;
        return this;
    }
    
    public int getEntrust() {
        return entrust;
    }
    
    public AirconState setEntrust(int entrust) {
        this.entrust = entrust;
        return this;
    }
    
    public int getModelNo() {
        return modelNo;
    }
    
    public AirconState setModelNo(int modelNo) {
        this.modelNo = modelNo;
        return this;
    }

    public boolean isSelfCleanOperation() {
        return selfCleanOperation;
    }
    
    public AirconState setSelfCleanOperation(boolean isSelfCleanOperation) {
        this.selfCleanOperation = isSelfCleanOperation;
        return this;
    }
    
    public boolean isSelfCleanReset() {
        return selfCleanReset;
    }
    
    public AirconState setSelfCleanReset(boolean isSelfCleanReset) {
        this.selfCleanReset = isSelfCleanReset;
        return this;
    }
    
    public boolean isVacantProperty() {
        return vacantProperty;
    }
    
    public AirconState setVacantProperty(boolean isVacantProperty) {
        this.vacantProperty = isVacantProperty;
        return this;
    }
    
    public boolean isCanHomeLeaveModeStatusRequest() {
        return canHomeLeaveModeStatusRequest;
    }
    
    public void setCanHomeLeaveModeStatusRequest(boolean canHomeLeaveModeStatusRequest) {
        this.canHomeLeaveModeStatusRequest = canHomeLeaveModeStatusRequest;
    }
    
    public HomeLeaveMode getHomeLeaveModeForCooling() {
        return homeLeaveModeForCooling;
    }
    
    public void setHomeLeaveModeForCooling(HomeLeaveMode homeLeaveModeForCooling) {
        this.homeLeaveModeForCooling = homeLeaveModeForCooling;
    }
    
    public HomeLeaveMode getHomeLeaveModeForHeating() {
        return homeLeaveModeForHeating;
    }
    
    public void setHomeLeaveModeForHeating(HomeLeaveMode homeLeaveModeForHeating) {
        this.homeLeaveModeForHeating = homeLeaveModeForHeating;
    }
    
    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
    
    public int getCoolHotJudge() {
        return coolHotJudge;
    }

    public void setCoolHotJudge(int coolHotJudge) {
        this.coolHotJudge = coolHotJudge;
    }
    
    public double getIndoorTemp() {
        return indoorTemp;
    }
    
    public void setIndoorTemp(double indoorTemp) {
        this.indoorTemp = indoorTemp;
    }
    
    public double getOutdoorTemp() {
        return outdoorTemp;
    }
    
    public void setOutdoorTemp(double outdoorTemp) {
        this.outdoorTemp = outdoorTemp;
    }

    private HomeLeaveMode getOrCreateHomeLeaveModeForCooling() {
        if(homeLeaveModeForCooling == null) {
            homeLeaveModeForCooling = new HomeLeaveMode();
        }
        return homeLeaveModeForCooling;
    }
    
    private HomeLeaveMode getOrCreateHomeLeaveModeForHeating() {
        if(homeLeaveModeForHeating == null) {
            homeLeaveModeForHeating = new HomeLeaveMode();
        }
        return homeLeaveModeForHeating;
    }

    public void setHomeLeaveModeForCoolingTempRule(double tempRule) {
        getOrCreateHomeLeaveModeForCooling().setTempRule(tempRule);
    }

    public void setHomeLeaveModeForHeatingTempRule(double tempRule) {
        getOrCreateHomeLeaveModeForHeating().setTempRule(tempRule);
    }

    public void setHomeLeaveModeForCoolingTempSetting(double tempSetting) {
        getOrCreateHomeLeaveModeForCooling().setTempSetting(tempSetting);
    }

    public void setHomeLeaveModeForHeatingTempSetting(double tempSetting) {
        getOrCreateHomeLeaveModeForHeating().setTempSetting(tempSetting);
    }

    public void setHomeLeaveModeForCoolingAirflow(int airFlow) {
        getOrCreateHomeLeaveModeForCooling().setAirFlow(airFlow);
    }

    public void setHomeLeaveModeForHeatingAirflow(int airFlow) {
        getOrCreateHomeLeaveModeForHeating().setAirFlow(airFlow);
    }
}
