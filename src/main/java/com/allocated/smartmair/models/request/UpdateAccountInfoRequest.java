package com.allocated.smartmair.models.request;

/**
 * @author Nick Hobbelink
 */
public class UpdateAccountInfoRequest extends AirconRequest {
    private Contents contents;
    
    public UpdateAccountInfoRequest(Contents contents) {
        setCommand("updateAccountInfo");
        setContents(contents);
    }
    
    public Contents getContents() {
        return contents;
    }
    
    public void setContents(Contents contents) {
        this.contents = contents;
    }
    
    public static class Contents {
        private String airconId;
        private String accountId;
        private Integer remote;
        private String timezone;
        
        public Contents() {
            
        }
        
        public Contents(String airconId, String accountId, Integer remote, String timezone) {
            super();
            this.airconId = airconId;
            this.accountId = accountId;
            this.remote = remote;
            this.timezone = timezone;
        }

        public String getAirconId() {
            return airconId;
        }
        
        public void setAirconId(String airconId) {
            this.airconId = airconId;
        }
        
        public String getAccountId() {
            return accountId;
        }
        
        public void setAccountId(String accountId) {
            this.accountId = accountId;
        }
        
        public Integer getRemote() {
            return remote;
        }
        
        public void setRemote(Integer remote) {
            this.remote = remote;
        }
        
        public String getTimezone() {
            return timezone;
        }
        
        public void setTimezone(String timezone) {
            this.timezone = timezone;
        }
        
    }

}
