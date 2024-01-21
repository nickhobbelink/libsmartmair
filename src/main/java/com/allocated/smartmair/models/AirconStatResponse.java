package com.allocated.smartmair.models;

import java.util.List;

/**
 * @author Nick Hobbelink
 */
public class AirconStatResponse extends AirconResponse {
    private Contents contents;
    
    public Contents getContents() {
        return contents;
    }
    
    public class Contents {
        private String airconId;
        private String airconStat;
        private Integer logStat;
        private String updatedBy;
        private Long expires;
        private Integer ledStat;
        private Integer autoHeating;
        private String highTemp;
        private String lowTemp;
        private String firmType;
        private String timezone;
        private List<String> remoteList;
        private int numOfAccount;
        
        public String getAirconId() {
            return airconId;
        }
        
        public String getAirconStat() {
            return airconStat;
        }
        
        public Integer getLogStat() {
            return logStat;
        }
        
        public String getUpdatedBy() {
            return updatedBy;
        }
        
        public Long getExpires() {
            return expires;
        }
        
        public Integer getLedStat() {
            return ledStat;
        }
        
        public Integer getAutoHeating() {
            return autoHeating;
        }
        
        public String getHighTemp() {
            return highTemp;
        }
        
        public String getLowTemp() {
            return lowTemp;
        }
        
        public String getFirmType() {
            return firmType;
        }
        
        public String getTimezone() {
            return timezone;
        }
        
        public List<String> getRemoteList() {
            return remoteList;
        }
        
        public int getNumOfAccount() {
            return numOfAccount;
        }
    }
}
