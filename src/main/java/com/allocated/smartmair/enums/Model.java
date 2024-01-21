package com.allocated.smartmair.enums;

/**
 * @author Nick Hobbelink
 */
public enum Model {
    SEPARATE_2021(0), GLOBAL_2022(1), HIGH_END_FOR_JAPANESE_2023(2);
    
    private int value;
    
    Model(int value){
        this.value = value;
    }
    
    public int getValue() {
        return value;
    }
}
