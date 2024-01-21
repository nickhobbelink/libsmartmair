package com.allocated.smartmair;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.Locale;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import com.allocated.smartmair.models.AirconState;

/**
 * @author Nick Hobbelink
 * 
 * Encodes/decodes aircon states going through the getAirconStat/setAirconStat endpoints
 */
public class AirconStateCoder {
    private static final int STATUS_EXTENSION_CODE_HOME_LEAVE_MODE = 248;
    
    private static final double[] OUTDOOR_TEMPS = {-50.0, -50.0, -50.0, -50.0, -50.0, -48.9, -46.0, -44.0, -42.0, -41.0, -39.0, -38.0, -37.0, -36.0, -35.0, -34.0, -33.0, -32.0, -31.0, -30.0, -29.0, -28.5, -28.0, -27.0, -26.0, -25.5, -25.0, -24.0, -23.5, -23.0, -22.5, -22.0, -21.5, -21.0, -20.5, -20.0, -19.5, -19.0, -18.5, -18.0, -17.5, -17.0, -16.5, -16.0, -15.5, -15.0, -14.6, -14.3, -14.0, -13.5, -13.0, -12.6, -12.3, -12.0, -11.5, -11.0, -10.6, -10.3, -10.0, -9.6, -9.3, -9.0, -8.6, -8.3, -8.0, -7.6, -7.3, -7.0, -6.6, -6.3, -6.0, -5.6, -5.3, -5.0, -4.6, -4.3, -4.0, -3.7, -3.5, -3.2, -3.0, -2.6, -2.3, -2.0, -1.7, -1.5, -1.2, -1.0, -0.6, -0.3, 0.0, 0.2, 0.5, 0.7, 1.0, 1.3, 1.6, 2.0, 2.2, 2.5, 2.7, 3.0, 3.2, 3.5, 3.7, 4.0, 4.2, 4.5, 4.7, 5.0, 5.2, 5.5, 5.7, 6.0, 6.2, 6.5, 6.7, 7.0, 7.2, 7.5, 7.7, 8.0, 8.2, 8.5, 8.7, 9.0, 9.2, 9.5, 9.7, 10.0, 10.2, 10.5, 10.7, 11.0, 11.2, 11.5, 11.7, 12.0, 12.2, 12.5, 12.7, 13.0, 13.2, 13.5, 13.7, 14.0, 14.2, 14.4, 14.6, 14.8, 15.0, 15.2, 15.5, 15.7, 16.0, 16.2, 16.5, 16.7, 17.0, 17.2, 17.5, 17.7, 18.0, 18.2, 18.5, 18.7, 19.0, 19.2, 19.4, 19.6, 19.8, 20.0, 20.2, 20.5, 20.7, 21.0, 21.2, 21.5, 21.7, 22.0, 22.2, 22.5, 22.7, 23.0, 23.2, 23.5, 23.7, 24.0, 24.2, 24.5, 24.7, 25.0, 25.2, 25.5, 25.7, 26.0, 26.2, 26.5, 26.7, 27.0, 27.2, 27.5, 27.7, 28.0, 28.2, 28.5, 28.7, 29.0, 29.2, 29.5, 29.7, 30.0, 30.2, 30.5, 30.7, 31.0, 31.3, 31.6, 32.0, 32.2, 32.5, 32.7, 33.0, 33.2, 33.5, 33.7, 34.0, 34.3, 34.6, 35.0, 35.2, 35.5, 35.7, 36.0, 36.3, 36.6, 37.0, 37.2, 37.5, 37.7, 38.0, 38.3, 38.6, 39.0, 39.3, 39.6, 40.0, 40.3, 40.6, 41.0, 41.3, 41.6, 42.0, 42.3, 42.6, 43.0};
    private static final double[] INDOOR_TEMPS = {-30.0, -30.0, -30.0, -30.0, -30.0, -30.0, -30.0, -30.0, -30.0, -30.0, -30.0, -30.0, -30.0, -30.0, -30.0, -30.0, -29.0, -28.0, -27.0, -26.0, -25.0, -24.0, -23.0, -22.5, -22.0, -21.0, -20.0, -19.5, -19.0, -18.0, -17.5, -17.0, -16.5, -16.0, -15.0, -14.5, -14.0, -13.5, -13.0, -12.5, -12.0, -11.5, -11.0, -10.5, -10.0, -9.5, -9.0, -8.6, -8.3, -8.0, -7.5, -7.0, -6.5, -6.0, -5.6, -5.3, -5.0, -4.5, -4.0, -3.6, -3.3, -3.0, -2.6, -2.3, -2.0, -1.6, -1.3, -1.0, -0.5, 0.0, 0.3, 0.6, 1.0, 1.3, 1.6, 2.0, 2.3, 2.6, 3.0, 3.2, 3.5, 3.7, 4.0, 4.3, 4.6, 5.0, 5.3, 5.6, 6.0, 6.3, 6.6, 7.0, 7.2, 7.5, 7.7, 8.0, 8.3, 8.6, 9.0, 9.2, 9.5, 9.7, 10.0, 10.3, 10.6, 11.0, 11.2, 11.5, 11.7, 12.0, 12.3, 12.6, 13.0, 13.2, 13.5, 13.7, 14.0, 14.2, 14.5, 14.7, 15.0, 15.3, 15.6, 16.0, 16.2, 16.5, 16.7, 17.0, 17.2, 17.5, 17.7, 18.0, 18.2, 18.5, 18.7, 19.0, 19.2, 19.5, 19.7, 20.0, 20.2, 20.5, 20.7, 21.0, 21.2, 21.5, 21.7, 22.0, 22.2, 22.5, 22.7, 23.0, 23.2, 23.5, 23.7, 24.0, 24.2, 24.5, 24.7, 25.0, 25.2, 25.5, 25.7, 26.0, 26.2, 26.5, 26.7, 27.0, 27.2, 27.5, 27.7, 28.0, 28.2, 28.5, 28.7, 29.0, 29.2, 29.5, 29.7, 30.0, 30.2, 30.5, 30.7, 31.0, 31.3, 31.6, 32.0, 32.2, 32.5, 32.7, 33.0, 33.2, 33.5, 33.7, 34.0, 34.2, 34.5, 34.7, 35.0, 35.3, 35.6, 36.0, 36.2, 36.5, 36.7, 37.0, 37.2, 37.5, 37.7, 38.0, 38.3, 38.6, 39.0, 39.2, 39.5, 39.7, 40.0, 40.3, 40.6, 41.0, 41.2, 41.5, 41.7, 42.0, 42.3, 42.6, 43.0, 43.2, 43.5, 43.7, 44.0, 44.3, 44.6, 45.0, 45.3, 45.6, 46.0, 46.2, 46.5, 46.7, 47.0, 47.3, 47.6, 48.0, 48.3, 48.6, 49.0, 49.3, 49.6, 50.0, 50.3, 50.6, 51.0, 51.3, 51.6, 52.0};

    public String toBase64(AirconState airconStat, AirconState airconStat2) {
        return Base64.getEncoder().encodeToString(concat(addCrc16(addCommandVariableData(commandToByte(airconStat), airconStat)).array(), addCrc16(addReceiveVariableData(receiveToByte(airconStat2))).array())).replace("\n", "");
    }

    public AirconState fromBase64(AirconState stat, String str) {
        return byteToStat(stat, Base64.getDecoder().decode(str.replace("\n", "")));
    }

    private AirconState byteToStat(AirconState stat, byte[] input) {
        int cmdVarsSize = (input[18] & 0xff) * 4;
        int dataStart = cmdVarsSize + 21;
        int dataEnd = dataStart + 18;
        
        byte[] data = Arrays.copyOfRange(input, dataStart, dataEnd);
        byte[] sensors = java.util.Arrays.copyOfRange(input, dataStart + 19, input.length - 2);
        byte[] cmdVars = Arrays.copyOfRange(input, 19, 19 + cmdVarsSize);
                
        stat.setOperation( ((data[2] & 3) == 1) ? 1 : 0);
        stat.setPresetTemp( ((double)(data[4] & 0xFF)) / 2.0d );
        
        readInteger(data[2] & 60, (v) -> stat.setOperationMode(v), 0, 8, 16, 12, 4);
        readInteger(data[3] & 15, (v) -> stat.setAirFlow(v), 7, 0, 1, 2, 6);
        readInteger(data[2] & 192, data[3] & 240, (v) -> stat.setWindDirectionUD(v), 64, 0, 16, 32, 48);
        readInteger(data[12] & 3, data[11] & 31, (v) -> stat.setWindDirectionLR(v), 1, 0, 1, 2, 3, 4, 5, 6);
        readInteger(data[12] & 12, (v) -> stat.setEntrust(v), 0, 4);

        int eCode = data[6] & 127;
        byte eFlags = data[7];
        
        if(eCode == 0) {
            stat.setErrorCode("00");
        } else if((eFlags & 128) <= 0) {
            stat.setErrorCode("E" + String.valueOf(eCode));
        } else {
            stat.setErrorCode("M" + String.format(Locale.US, "%02d", eCode));
        }
        
        stat.setCoolHotJudge(((data[8] & 8) <= 0) ? 0 : 1);
        readInteger(data[0] & 127, (v) -> stat.setModelNo(v), 0, 1, 2);
        readBoolean(data[10] & 1, (v) -> stat.setVacantProperty(v), 0, 1);
        readBoolean(data[15] & 15, (v) -> stat.setSelfCleanOperation(v), 0, 1);

        if(dataEnd >= input.length) {
            return stat;
        }
        
        for(int i = 0; i < sensors.length; i += 4) {
            byte[] b = copy(sensors, i, 4);
            if (b[0] == toByte(128) && b[1] == toByte(16)) {
                stat.setOutdoorTemp(OUTDOOR_TEMPS[b[2] & 0xFF]);
            } else if(b[0] == toByte(128) && b[1] == toByte(32)) {
                stat.setIndoorTemp(INDOOR_TEMPS[b[2] & 0xFF]);
            }
        }
        
        for(int i = 0; i < cmdVars.length; i += 4) {
            if((cmdVars[i] & 0xFF) != 248) {
                break;
            }
            int value = cmdVars[i + 3] & 0xFF;
            if(cmdVars[i + 2] == 27) {
                stat.setHomeLeaveModeForCoolingTempRule((double)value / 2.0);
            } else if(cmdVars[i + 2] == 28) {
                stat.setHomeLeaveModeForHeatingTempRule((double)value / 2.0);
            } else if(cmdVars[i + 2] == 29) {
                stat.setHomeLeaveModeForCoolingTempSetting((double)value / 2.0);
            } else if(cmdVars[i + 2] == 30) {
                stat.setHomeLeaveModeForHeatingTempSetting((double)value / 2.0);
            } else if(cmdVars[i + 2] == 31) {
                stat.setHomeLeaveModeForCoolingAirflow(getHomeLeaveModeAirFlow(value & 15));
            } else if(cmdVars[i + 2] == 32) {
                stat.setHomeLeaveModeForHeatingAirflow(getHomeLeaveModeAirFlow(value & 15));
            }
        }
        return stat; 
    }
    
    private ByteBuffer commandToByte(AirconState airconStat) {
        int[] buffer = {0, 0, 0, 0, 0, 255, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        
        buffer[2] |= (airconStat.getOperation() == 1) ? 3 : 2;

        write(buffer, 2, airconStat.getOperationMode(), 32, 40, 48, 44, 36);
        write(buffer, 3, airconStat.getAirFlow(), 15, 8, 9, 10, 14);
        
        write(buffer, 2, airconStat.getWindDirectionUD(), 192, 128, 128, 128, 128);
        write(buffer, 3, airconStat.getWindDirectionUD(), 128, 128, 144, 160, 176);
        
        write(buffer, 11, airconStat.getWindDirectionLR(), 16, 16, 17, 18, 19, 20, 21, 22);
        write(buffer, 12, airconStat.getWindDirectionLR(), 3, 2, 2, 2, 2, 2, 2, 2);

        double presetTemp = airconStat.getOperationMode() == 3 ? 25.0d : airconStat.getPresetTemp();

        buffer[4] |= ((int) (presetTemp / 0.5d)) + 128;
        buffer[12] |= (airconStat.getEntrust() == 0) ? 8 : 12;

        if (airconStat.getModelNo() == 1) {
            buffer[10] |= airconStat.isVacantProperty() ? 1 : 0;
        }
        
        int modelNo = airconStat.getModelNo();
        if (modelNo == 1 || modelNo == 2) {
            buffer[10] |= airconStat.isSelfCleanReset() ? 4 : 0;
            buffer[12] |= airconStat.isSelfCleanOperation() ? 144 : 128;
        }
        return toBytes(buffer);
    }

    private ByteBuffer receiveToByte(AirconState airconStat) {
        int[] buffer = {0, 0, 0, 0, 0, 255, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        
        buffer[2] |= (airconStat.getOperation() == 1) ? 1 : 0;

        write(buffer, 2, airconStat.getOperationMode(), 0, 8, 16, 12, 4);
        write(buffer, 3, airconStat.getAirFlow(), 7, 0, 1, 2, 6);
        
        write(buffer, 2, airconStat.getWindDirectionUD(), 64, 0, 0, 0, 0);
        write(buffer, 3, airconStat.getWindDirectionUD(), 0, 0, 16, 32, 48);
        
        write(buffer, 11, airconStat.getWindDirectionLR(), 0, 0, 1, 2, 3, 4, 5, 6);
        write(buffer, 12, airconStat.getWindDirectionLR(), 1, 0, 0, 0, 0, 0, 0, 0);

        double presetTemp = airconStat.getOperationMode() == 3 ? 25.0d : airconStat.getPresetTemp();

        buffer[4] |= (int) (presetTemp / 0.5d);
        buffer[12] |= (airconStat.getEntrust() == 0) ? 0 : 4;
        
        write(buffer, 0, airconStat.getModelNo(), 0, 1, 2);

        if (airconStat.getModelNo() == 1) {
            buffer[10] |= airconStat.isVacantProperty() ? 1 : 0;
        }

        int modelNo2 = airconStat.getModelNo();
        if (modelNo2 == 1 || modelNo2 == 2) {
            buffer[15] |= airconStat.isSelfCleanOperation() ? 1 : 0;
        }
        return toBytes(buffer);
    }
    
    private byte[] copy(byte[] src, int start, int length) {
        byte[] result = new byte[length];
        System.arraycopy(src, start, result, 0, length);
        return result;
    }
    
    private void readInteger(int value, Consumer<Integer> onValue, int... values) {
        for(int i = 0; i < values.length; ++i) {
            if(value == values[i]) {
                onValue.accept(i);
            }
        }
    }
    
    private void readBoolean(int value, Consumer<Boolean> onValue, int falseValue, int trueValue) {
        if(value == falseValue) {
            onValue.accept(false);
        }
        else if(value == trueValue) {
            onValue.accept(true);
        }
    }
    
    private void readInteger(int firstValue, int secondValue, Consumer<Integer> onValue, int valueZero, int... otherValues) {
        if(firstValue == valueZero) {
            onValue.accept(0);
            return;
        }
        for(int i = 0; i < otherValues.length; ++i) {
            if(secondValue == otherValues[i]) {
                onValue.accept(i + 1);
            }
        }
    }
    
    private void write(int[] buffer, int ofs, int value, int... values) {
        buffer[ofs] |= values[value];
    }
    
    private ByteBuffer addCommandVariableData(ByteBuffer byteBuffer, AirconState airconStat) {
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        if (airconStat.getModelNo() == 1) {
            int[] iArr = {STATUS_EXTENSION_CODE_HOME_LEAVE_MODE, 0};
            int[] iArr2 = {STATUS_EXTENSION_CODE_HOME_LEAVE_MODE, 255};
            int[] iArr3 = {27, 28, 29, 30, 31, 32};
            if (airconStat.isCanHomeLeaveModeStatusRequest()) {
                for (int i = 0; i < 6; i++) {
                    int i2 = iArr3[i];
                    putArrayToList(arrayList, iArr2);
                    putArrayToList(arrayList, new int[]{i2, 0});
                }
            } else if (airconStat.getHomeLeaveModeForCooling() != null && airconStat.getHomeLeaveModeForHeating() != null) {
                int[] iArr4 = {(int) (airconStat.getHomeLeaveModeForCooling().getTempRule() * 2.0d), (int) (airconStat.getHomeLeaveModeForHeating().getTempRule() * 2.0d), (int) (airconStat.getHomeLeaveModeForCooling().getTempSetting() * 2.0d), (int) (airconStat.getHomeLeaveModeForHeating().getTempSetting() * 2.0d), getHomeLeaveModeAirFlowByte(airconStat.getHomeLeaveModeForCooling().getAirFlow()), getHomeLeaveModeAirFlowByte(airconStat.getHomeLeaveModeForHeating().getAirFlow())};
                for (int i3 = 0; i3 < 6; i3++) {
                    putArrayToList(arrayList, iArr);
                    putArrayToList(arrayList, new int[]{iArr3[i3], iArr4[i3]});
                }
            }
        }
        if (arrayList.isEmpty()) {
            return ByteBuffer.wrap(concat(byteBuffer.array(), toBytes(new int[]{1, 255, 255, 255, 255}).array()));
        }
        arrayList.add(0, Integer.valueOf(arrayList.size() / 4));
        return ByteBuffer.wrap(concat(byteBuffer.array(), toBytes(arrayList.toArray(new Integer[arrayList.size()])).array()));
    }
    
    private ByteBuffer addReceiveVariableData(ByteBuffer byteBuffer) {
        return ByteBuffer.wrap(concat(byteBuffer.array(), toBytes(new int[]{1, 255, 255, 255, 255}).array()));
    }

    private void putArrayToList(List<Integer> list, int[] iArr) {
        list.addAll((List<Integer>) Arrays.stream(iArr).boxed().collect(Collectors.toList()));
    }
    
    private ByteBuffer toBytes(int[] iArr) {
        ByteBuffer allocate = ByteBuffer.allocate(iArr.length);
        for (int i : iArr) {
            allocate.put(toByte(i));
        }
        return allocate;
    }
    
    private ByteBuffer toBytes(Integer[] iArr) {
        ByteBuffer allocate = ByteBuffer.allocate(iArr.length);
        for (int i : iArr) {
            allocate.put(toByte(i));
        }
        return allocate;
    }

    private byte toByte(int i) {
        return (byte) i;
    }

    private ByteBuffer addCrc16(ByteBuffer byteBuffer) {
        int crc16ccitt = crc16ccitt(byteBuffer.array());
        return ByteBuffer.wrap(concat(byteBuffer.array(), new byte[]{toByte(crc16ccitt & 255), toByte((crc16ccitt >> 8) & 255)}));
    }

    private int crc16ccitt(byte[] bArr) {
        int i = 65535;
        for (byte b : bArr) {
            for (int i2 = 0; i2 < 8; i2++) {
                boolean z = ((b >> (7 - i2)) & 1) == 1;
                boolean z2 = ((i >> 15) & 1) == 1;
                i <<= 1;
                if (z ^ z2) {
                    i ^= 4129;
                }
            }
        }
        return i & 65535;
    }

    private byte[] concat(byte[] bArr, byte[] bArr2) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            byteArrayOutputStream.write(bArr);
            byteArrayOutputStream.write(bArr2);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return byteArrayOutputStream.toByteArray();
    }
    
    private int getHomeLeaveModeAirFlow(int i) {
        if (i != 3) {
            if (i != 5) {
                if (i != 7) {
                    return i != 14 ? 0 : 4;
                }
                return 3;
            }
            return 2;
        }
        return 1;
    }

    private int getHomeLeaveModeAirFlowByte(int i) {
        if (i != 1) {
            if (i != 2) {
                if (i != 3) {
                    return i != 4 ? 0 : 14;
                }
                return 7;
            }
            return 5;
        }
        return 3;
    }
}
