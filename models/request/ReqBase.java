package org.rehab.app.models.request;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.rehab.app.constants.AppConstants;


public class ReqBase {



    @JsonProperty("service_access_key")
    private String userAccessToken = AppConstants.APP_KEY;
    @JsonProperty("service_access_key")
    public String getuserAccessToken() {
        return userAccessToken;
    }

    @JsonIgnore
    public void setuserAccessToken(String userAccessToken) {
        this.userAccessToken = userAccessToken;
    }




}
