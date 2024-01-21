package com.allocated.smartmair.enums;

/**
 * @author Nick Hobbelink
 */
public enum OperationMode {
    AUTO(0), COOL(1), HEAT(2), FAN(3), DRY(4);
    
    private int value;
    
    OperationMode(int value){
        this.value = value;
    }
    
    public int getValue() {
        return value;
    }
}
