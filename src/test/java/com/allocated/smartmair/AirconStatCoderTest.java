package com.allocated.smartmair;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.allocated.smartmair.models.AirconState;
import com.allocated.smartmair.models.HomeLeaveMode;

class AirconStatCoderTest {
    
    @Test
    void test2() {
        String input = "AACzuqT/AAAAAAASCgAAAAAAAf////9JvIAEEDIkaAAAiAAAAgAAAAAAAAOAIGj/gBBg/5QQCwAY1Q==";
        AirconState stat = new AirconStateCoder().fromBase64(new AirconState(), input);
        
        assertEquals(stat.getIndoorTemp(), 10.6d, 0.001);
        assertEquals(stat.getOutdoorTemp(), 1.6d, 0.001);
        
        input = "AACzuqT/AAAAAAASCgAAAAAAAf////9JvIAEEDAkWwAAiAAAAgAAAAAAAAOAIFv/gBBa/5QQAQDgzg==";
        stat = new AirconStateCoder().fromBase64(new AirconState(), input);
        
        assertEquals(stat.getIndoorTemp(), 7.0d, 0.001);
        assertEquals(stat.getOutdoorTemp(), 0.0d, 0.001);
    }
    
    @Test
    void testEncoding() {
        AirconState op0 = new AirconState().setOperation(0);
        assertEquals("AADij4D/AAAAAAAQCwAAAAAAAf////+IVAAAQAcA/wAAAAAAAAEAAAAAAAH/////9KQ=", new AirconStateCoder().toBase64(op0, op0));

        AirconState op1 = new AirconState().setOperation(1);
        assertEquals("AADjj4D/AAAAAAAQCwAAAAAAAf////9+gQAAQQcA/wAAAAAAAAEAAAAAAAH/////AnE=", new AirconStateCoder().toBase64(op1, op1));

        AirconState om0 = new AirconState().setOperationMode(0);
        assertEquals("AADij4D/AAAAAAAQCwAAAAAAAf////+IVAAAQAcA/wAAAAAAAAEAAAAAAAH/////9KQ=", new AirconStateCoder().toBase64(om0, om0));
        
        AirconState om1 = new AirconState().setOperationMode(1);
        assertEquals("AADqj4D/AAAAAAAQCwAAAAAAAf/////+mwAASAcA/wAAAAAAAAEAAAAAAAH/////gms=", new AirconStateCoder().toBase64(om1, om1));
        
        AirconState om2 = new AirconState().setOperationMode(2);
        assertEquals("AADyj4D/AAAAAAAQCwAAAAAAAf////9F2gAAUAcA/wAAAAAAAAEAAAAAAAH/////OSo=", new AirconStateCoder().toBase64(om2, om2));
        
        AirconState om3 = new AirconState().setOperationMode(3);
        assertEquals("AADuj7L/AAAAAAAQCwAAAAAAAf////+9AAAATAcy/wAAAAAAAAEAAAAAAAH/////wfA=", new AirconStateCoder().toBase64(om3, om3));
        
        AirconState om4 = new AirconState().setOperationMode(4);
        assertEquals("AADmj4D/AAAAAAAQCwAAAAAAAf////8zMwAARAcA/wAAAAAAAAEAAAAAAAH/////T8M=", new AirconStateCoder().toBase64(om4, om4));
        
        AirconState af0 = new AirconState().setAirFlow(0);
        assertEquals("AADij4D/AAAAAAAQCwAAAAAAAf////+IVAAAQAcA/wAAAAAAAAEAAAAAAAH/////9KQ=", new AirconStateCoder().toBase64(af0, af0));
        
        AirconState af1 = new AirconState().setAirFlow(1);
        assertEquals("AADiiID/AAAAAAAQCwAAAAAAAf////+R3AAAQAAA/wAAAAAAAAEAAAAAAAH/////7Sw=", new AirconStateCoder().toBase64(af1, af1));
        
        AirconState af2 = new AirconState().setAirFlow(2);
        assertEquals("AADiiYD/AAAAAAAQCwAAAAAAAf////8i6QAAQAEA/wAAAAAAAAEAAAAAAAH/////Xhk=", new AirconStateCoder().toBase64(af2, af2));
        
        AirconState af3 = new AirconState().setAirFlow(3);
        assertEquals("AADiioD/AAAAAAAQCwAAAAAAAf/////3twAAQAIA/wAAAAAAAAEAAAAAAAH/////i0c=", new AirconStateCoder().toBase64(af3, af3));
        
        AirconState af4 = new AirconState().setAirFlow(4);
        assertEquals("AADijoD/AAAAAAAQCwAAAAAAAf////87YQAAQAYA/wAAAAAAAAEAAAAAAAH/////R5E=", new AirconStateCoder().toBase64(af4, af4));
        
        AirconState wu0 = new AirconState().setWindDirectionUD(0);
        assertEquals("AADij4D/AAAAAAAQCwAAAAAAAf////+IVAAAQAcA/wAAAAAAAAEAAAAAAAH/////9KQ=", new AirconStateCoder().toBase64(wu0, wu0));
        
        AirconState wu1 = new AirconState().setWindDirectionUD(1);
        assertEquals("AACij4D/AAAAAAAQCwAAAAAAAf/////+TwAAAAcA/wAAAAAAAAEAAAAAAAH/////gr8=", new AirconStateCoder().toBase64(wu1, wu1));
        
        AirconState wu2 = new AirconState().setWindDirectionUD(2);
        assertEquals("AACin4D/AAAAAAAQCwAAAAAAAf////+tJAAAABcA/wAAAAAAAAEAAAAAAAH/////0dQ=", new AirconStateCoder().toBase64(wu2, wu2));
        
        AirconState wu3 = new AirconState().setWindDirectionUD(3);
        assertEquals("AACir4D/AAAAAAAQCwAAAAAAAf////9YmQAAACcA/wAAAAAAAAEAAAAAAAH/////JGk=", new AirconStateCoder().toBase64(wu3, wu3));
        
        AirconState wu4 = new AirconState().setWindDirectionUD(4);
        assertEquals("AACiv4D/AAAAAAAQCwAAAAAAAf////8L8gAAADcA/wAAAAAAAAEAAAAAAAH/////dwI=", new AirconStateCoder().toBase64(wu4, wu4));
        
        AirconState wl0 = new AirconState().setWindDirectionLR(0);
        assertEquals("AADij4D/AAAAAAAQCwAAAAAAAf////+IVAAAQAcA/wAAAAAAAAEAAAAAAAH/////9KQ=", new AirconStateCoder().toBase64(wl0, wl0));
        
        AirconState wl1 = new AirconState().setWindDirectionLR(1);
        assertEquals("AADij4D/AAAAAAAQCgAAAAAAAf/////BjAAAQAcA/wAAAAAAAAAAAAAAAAH/////vXw=", new AirconStateCoder().toBase64(wl1, wl1));
        
        AirconState wl2 = new AirconState().setWindDirectionLR(2);
        assertEquals("AADij4D/AAAAAAARCgAAAAAAAf////+0jwAAQAcA/wAAAAAAAQAAAAAAAAH/////yH8=", new AirconStateCoder().toBase64(wl2, wl2));
        
        AirconState wl3 = new AirconState().setWindDirectionLR(3);
        assertEquals("AADij4D/AAAAAAASCgAAAAAAAf////8rigAAQAcA/wAAAAAAAgAAAAAAAAH/////V3o=", new AirconStateCoder().toBase64(wl3, wl3));
        
        AirconState wl4 = new AirconState().setWindDirectionLR(4);
        assertEquals("AADij4D/AAAAAAATCgAAAAAAAf////9eiQAAQAcA/wAAAAAAAwAAAAAAAAH/////Ink=", new AirconStateCoder().toBase64(wl4, wl4));
        
        AirconState wl5 = new AirconState().setWindDirectionLR(5);
        assertEquals("AADij4D/AAAAAAAUCgAAAAAAAf////8VgQAAQAcA/wAAAAAABAAAAAAAAAH/////aXE=", new AirconStateCoder().toBase64(wl5, wl5));
        
        AirconState wl6 = new AirconState().setWindDirectionLR(6);
        assertEquals("AADij4D/AAAAAAAVCgAAAAAAAf////9gggAAQAcA/wAAAAAABQAAAAAAAAH/////HHI=", new AirconStateCoder().toBase64(wl6, wl6));
        
        AirconState wl7 = new AirconState().setWindDirectionLR(7);
        assertEquals("AADij4D/AAAAAAAWCgAAAAAAAf//////hwAAQAcA/wAAAAAABgAAAAAAAAH/////g3c=", new AirconStateCoder().toBase64(wl7, wl7));
        
        AirconState e0 = new AirconState().setEntrust(0);
        assertEquals("AADij4D/AAAAAAAQCwAAAAAAAf////+IVAAAQAcA/wAAAAAAAAEAAAAAAAH/////9KQ=", new AirconStateCoder().toBase64(e0, e0));
        
        AirconState e1 = new AirconState().setEntrust(1);
        assertEquals("AADij4D/AAAAAAAQDwAAAAAAAf/////PBQAAQAcA/wAAAAAAAAUAAAAAAAH/////s/U=", new AirconStateCoder().toBase64(e1, e1));
        
        AirconState m0 = new AirconState().setModelNo(0);
        assertEquals("AADij4D/AAAAAAAQCwAAAAAAAf////+IVAAAQAcA/wAAAAAAAAEAAAAAAAH/////9KQ=", new AirconStateCoder().toBase64(m0, m0));
        
        AirconState m1 = new AirconState().setModelNo(1);
        assertEquals("AADij4D/AAAAAAAQiwAAAAAAAf////8i3QEAQAcA/wAAAAAAAAEAAAAAAAH//////8E=", new AirconStateCoder().toBase64(m1, m1));
        
        AirconState m2 = new AirconState().setModelNo(2);
        assertEquals("AADij4D/AAAAAAAQiwAAAAAAAf////8i3QIAQAcA/wAAAAAAAAEAAAAAAAH/////4m4=", new AirconStateCoder().toBase64(m2, m2));
        
        AirconState v0 = new AirconState().setModelNo(1).setVacantProperty(false);
        assertEquals("AADij4D/AAAAAAAQiwAAAAAAAf////8i3QEAQAcA/wAAAAAAAAEAAAAAAAH//////8E=", new AirconStateCoder().toBase64(v0, v0));
        
        AirconState v1 = new AirconState().setModelNo(1).setVacantProperty(true);
        assertEquals("AADij4D/AAAAAAEQiwAAAAAAAf////9BmAEAQAcA/wAAAAABAAEAAAAAAAH/////nIQ=", new AirconStateCoder().toBase64(v1, v1));
        
        AirconState sc1_0 = new AirconState().setModelNo(1).setSelfCleanReset(false);
        assertEquals("AADij4D/AAAAAAAQiwAAAAAAAf////8i3QEAQAcA/wAAAAAAAAEAAAAAAAH//////8E=", new AirconStateCoder().toBase64(sc1_0, sc1_0));
        
        AirconState sc2_0 = new AirconState().setModelNo(2).setSelfCleanReset(false);
        assertEquals("AADij4D/AAAAAAAQiwAAAAAAAf////8i3QIAQAcA/wAAAAAAAAEAAAAAAAH/////4m4=", new AirconStateCoder().toBase64(sc2_0, sc2_0));
        
        AirconState sc1_1 = new AirconState().setModelNo(1).setSelfCleanReset(true);
        assertEquals("AADij4D/AAAAAAQQiwAAAAAAAf////+P2AEAQAcA/wAAAAAAAAEAAAAAAAH//////8E=", new AirconStateCoder().toBase64(sc1_1, sc1_1));
        
        AirconState sc2_1 = new AirconState().setModelNo(2).setSelfCleanReset(true);
        assertEquals("AADij4D/AAAAAAQQiwAAAAAAAf////+P2AIAQAcA/wAAAAAAAAEAAAAAAAH/////4m4=", new AirconStateCoder().toBase64(sc2_1, sc2_1));
        
        AirconState so1_0 = new AirconState().setModelNo(1).setSelfCleanOperation(false);
        assertEquals("AADij4D/AAAAAAAQiwAAAAAAAf////8i3QEAQAcA/wAAAAAAAAEAAAAAAAH//////8E=", new AirconStateCoder().toBase64(so1_0, so1_0));
        
        AirconState so2_0 = new AirconState().setModelNo(2).setSelfCleanOperation(false);
        assertEquals("AADij4D/AAAAAAAQiwAAAAAAAf////8i3QIAQAcA/wAAAAAAAAEAAAAAAAH/////4m4=", new AirconStateCoder().toBase64(so2_0, so2_0));
        
        AirconState so1_1 = new AirconState().setModelNo(1).setSelfCleanOperation(true);
        assertEquals("AADij4D/AAAAAAAQmwAAAAAAAf////8fiAEAQAcA/wAAAAAAAAEAAAEAAAH/////LIY=", new AirconStateCoder().toBase64(so1_1, so1_1));
        
        AirconState so2_1 = new AirconState().setModelNo(2).setSelfCleanOperation(true);
        assertEquals("AADij4D/AAAAAAAQmwAAAAAAAf////8fiAIAQAcA/wAAAAAAAAEAAAEAAAH/////MSk=", new AirconStateCoder().toBase64(so2_1, so2_1));
        
    }

    @Test
    public void testOperation() {
        testOperation(0);
        testOperation(1);
    }
    
    @Test
    public void testOperationMode() {
        testOperationMode(0);
        testOperationMode(1);
        testOperationMode(2);
        testOperationMode(3);
        testOperationMode(4);
    }
    
    @Test
    public void testAirFlow() {
        testAirFlow(0);
        testAirFlow(1);
        testAirFlow(2);
        testAirFlow(3);
        testAirFlow(4);
    }
    
    @Test
    public void testWindDirectionUD() {
        testWindDirectionUD(0);
        testWindDirectionUD(1);
        testWindDirectionUD(2);
        testWindDirectionUD(3);
        testWindDirectionUD(4);
    }
    
    @Test
    public void testWindDirectionLR() {
        testWindDirectionLR(0);
        testWindDirectionLR(1);
        testWindDirectionLR(2);
        testWindDirectionLR(3);
        testWindDirectionLR(4);
        testWindDirectionLR(5);
        testWindDirectionLR(6);
        testWindDirectionLR(7);
    }
    
    @Test
    public void testEntrust() {
        testEntrust(0);
        testEntrust(1);
    }
    
    @Test
    public void testModelNo() {
        testModelNo(0);
        testModelNo(1);
        testModelNo(2);
    }
    
    @Test
    public void testVacantProperty() {
        testVacantProperty(false);
        testVacantProperty(true);
    }
    
    @Test
    public void testSelfCleanOperation() {
        testSelfCleanOperation(1, false);
        testSelfCleanOperation(1, true);
        testSelfCleanOperation(2, false);
        testSelfCleanOperation(2, true);
    }
    
    @Test
    public void testPresetTemp() {
        testPresetTemp(5);
    }
    
    @Test
    void testHomeLeaveCoolingHeating() {
        testHomeLeaveCoolingHeating(1, 2, 3, 4, 5, 6);
    }
    
    private void testPresetTemp(double presetTemp) {
        AirconState output = new AirconState();
        output.setPresetTemp(-1);
        
        AirconState stat = new AirconState();
        stat.setPresetTemp(presetTemp);
        testStat(stat, output);
    }

    private void testHomeLeaveCoolingHeating(int airFlow, double tempRule, double tempSetting, int airFlow2, double tempRule2, double tempSetting2) {
        HomeLeaveMode cooling = new HomeLeaveMode();
        cooling.setAirFlow(airFlow);
        cooling.setTempRule(tempRule);
        cooling.setTempSetting(tempSetting);
        
        HomeLeaveMode heating = new HomeLeaveMode();
        heating.setAirFlow(airFlow2);
        heating.setTempRule(tempRule2);
        heating.setTempSetting(tempSetting2);
        
        AirconState stat = new AirconState();
        stat.setModelNo(1);
        stat.setHomeLeaveModeForCooling(cooling);
        stat.setHomeLeaveModeForHeating(heating);
        testStat(stat, new AirconState());
    }
    
    private void testSelfCleanOperation(int modelNo, boolean selfCleanOperation) {
        AirconState stat = new AirconState();
        stat.setModelNo(modelNo);
        stat.setSelfCleanOperation(selfCleanOperation);
        

        AirconState output = new AirconState();
        output.setModelNo(-1);
        output.setSelfCleanOperation(!selfCleanOperation);
        
        testStat(stat, output);
    }

    private void testModelNo(int modelNo) {
        AirconState output = new AirconState();
        output.setModelNo(-1);
        
        AirconState stat = new AirconState();
        stat.setModelNo(modelNo);
        testStat(stat, output);
    }

    private void testVacantProperty(boolean vacantProperty) {
        AirconState output = new AirconState();
        output.setModelNo(-1);
        output.setVacantProperty(!vacantProperty);
        
        AirconState stat = new AirconState();
        stat.setModelNo(1);
        stat.setVacantProperty(vacantProperty);
        testStat(stat, output);
    }

    private void testEntrust(int entrust) {
        AirconState output = new AirconState();
        output.setEntrust(-1);
        
        AirconState stat = new AirconState();
        stat.setEntrust(entrust);
        testStat(stat, output);
    }

    private void testWindDirectionLR(int windDirectionLR) {
        AirconState output = new AirconState();
        output.setWindDirectionLR(-1);
        
        AirconState stat = new AirconState();
        stat.setWindDirectionLR(windDirectionLR);
        testStat(stat, output);
    }
    
    private void testWindDirectionUD(int windDirectionUD) {
        AirconState output = new AirconState();
        output.setWindDirectionUD(-1);
        
        AirconState stat = new AirconState();
        stat.setWindDirectionUD(windDirectionUD);
        testStat(stat, output);
    }

    private void testAirFlow(int airFlow) {
        AirconState output = new AirconState();
        output.setAirFlow(-1);
        
        AirconState stat = new AirconState();
        stat.setAirFlow(airFlow);
        testStat(stat, output);
    }

    private void testOperationMode(int operationMode) {
        AirconState output = new AirconState();
        output.setOperationMode(-1);
        
        AirconState stat = new AirconState();
        stat.setOperationMode(operationMode);
        testStat(stat, output);
    }

    private void testOperation(int operation) {
        AirconState output = new AirconState();
        output.setOperation(-1);
        
        AirconState stat = new AirconState();
        stat.setOperation(operation);
        testStat(stat, output);
    }
    
    private void testStat(AirconState stat, AirconState output) {
        String base64 = new AirconStateCoder().toBase64(stat, stat);
        
        AirconState decoded = new AirconStateCoder().fromBase64(output, base64);
        assertEquals(stat.getOperation(), decoded.getOperation());
        assertEquals(stat.getOperationMode(), decoded.getOperationMode());
        assertEquals(stat.getAirFlow(), decoded.getAirFlow());
        assertEquals(stat.getWindDirectionUD(), decoded.getWindDirectionUD());
        assertEquals(stat.getWindDirectionLR(), decoded.getWindDirectionLR());
        assertEquals(stat.getEntrust(), decoded.getEntrust());
        assertEquals(stat.getModelNo(), decoded.getModelNo());
        assertEquals(stat.isVacantProperty(), decoded.isVacantProperty());
        assertEquals(stat.isSelfCleanOperation(), decoded.isSelfCleanOperation());
        if(stat.getOperationMode() != 3) {
            assertEquals(stat.getPresetTemp(), decoded.getPresetTemp(), 0.01);
        }
        if(stat.getHomeLeaveModeForCooling() != null) {
            assertEquals(stat.getHomeLeaveModeForCooling(), decoded.getHomeLeaveModeForCooling());
        }
        if(stat.getHomeLeaveModeForHeating() != null) {
            assertEquals(stat.getHomeLeaveModeForHeating(), decoded.getHomeLeaveModeForHeating());
        }
    }

}
