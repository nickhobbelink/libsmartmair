package com.allocated.smartmair.enums;

/**
 * @author Nick Hobbelink
 */
public enum Operation {
    OFF(0), ON(1);
    
    private int value;
    
    Operation(int value){
        this.value = value;
    }
    
    public int getValue() {
        return value;
    }
}
