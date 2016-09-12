package org.rehab.app.models.response;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 */
public class ResUpdateUserProfile extends ResBase{
    @JsonProperty("data")
    private UserData data;
    @JsonProperty("additionalData")
    private List<Object> additionalData = new ArrayList<Object>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     * The data
     */
    @JsonProperty("data")
    public UserData getData() {
        return data;
    }

    /**
     *
     * @param data
     * The data
     */
    @JsonProperty("data")
    public void setData(UserData data) {
        this.data = data;
    }

    /**
     *
     * @return
     * The additionalData
     */
    @JsonProperty("additionalData")
    public List<Object> getAdditionalData() {
        return additionalData;
    }

    /**
     *
     * @param additionalData
     * The additionalData
     */
    @JsonProperty("additionalData")
    public void setAdditionalData(List<Object> additionalData) {
        this.additionalData = additionalData;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public class UserData {

        @JsonProperty("user_full_name")
        private String userFullName;
        @JsonProperty("user_email_temp")
        private String userEmailTemp;
        @JsonProperty("user_dob")
        private String userDob;
        @JsonProperty("user_gender")
        private String userGender;
        @JsonProperty("user_company_name")
        private String userCompanyName;
        @JsonProperty("user_website")
        private String userWebsite;
        @JsonProperty("user_contact_number")
        private String userContactNumber;
        @JsonProperty("user_image")
        private String userImage;
        @JsonIgnore
        private Map<String, Object> additionalProperties = new HashMap<String, Object>();

        /**
         * @return The userFullName
         */
        @JsonProperty("user_full_name")
        public String getUserFullName() {
            return userFullName;
        }

        /**
         * @param userFullName The user_full_name
         */
        @JsonProperty("user_full_name")
        public void setUserFullName(String userFullName) {
            this.userFullName = userFullName;
        }

        /**
         * @return The userEmailTemp
         */
        @JsonProperty("user_email_temp")
        public String getUserEmailTemp() {
            return userEmailTemp;
        }

        /**
         * @param userEmailTemp The user_email_temp
         */
        @JsonProperty("user_email_temp")
        public void setUserEmailTemp(String userEmailTemp) {
            this.userEmailTemp = userEmailTemp;
        }

        /**
         * @return The userDob
         */
        @JsonProperty("user_dob")
        public String getUserDob() {
            return userDob;
        }

        /**
         * @param userDob The user_dob
         */
        @JsonProperty("user_dob")
        public void setUserDob(String userDob) {
            this.userDob = userDob;
        }

        /**
         * @return The userGender
         */
        @JsonProperty("user_gender")
        public String getUserGender() {
            return userGender;
        }

        /**
         * @param userGender The user_gender
         */
        @JsonProperty("user_gender")
        public void setUserGender(String userGender) {
            this.userGender = userGender;
        }

        /**
         * @return The userCompanyName
         */
        @JsonProperty("user_company_name")
        public String getUserCompanyName() {
            return userCompanyName;
        }

        /**
         * @param userCompanyName The user_company_name
         */
        @JsonProperty("user_company_name")
        public void setUserCompanyName(String userCompanyName) {
            this.userCompanyName = userCompanyName;
        }

        /**
         * @return The userWebsite
         */
        @JsonProperty("user_website")
        public String getUserWebsite() {
            return userWebsite;
        }

        /**
         * @param userWebsite The user_website
         */
        @JsonProperty("user_website")
        public void setUserWebsite(String userWebsite) {
            this.userWebsite = userWebsite;
        }

        /**
         * @return The userContactNumber
         */
        @JsonProperty("user_contact_number")
        public String getUserContactNumber() {
            return userContactNumber;
        }

        /**
         * @param userContactNumber The user_contact_number
         */
        @JsonProperty("user_contact_number")
        public void setUserContactNumber(String userContactNumber) {
            this.userContactNumber = userContactNumber;
        }

        /**
         * @return The userImage
         */
        @JsonProperty("user_image")
        public String getUserImage() {
            return userImage;
        }

        /**
         * @param userImage The user_image
         */
        @JsonProperty("user_image")
        public void setUserImage(String userImage) {
            this.userImage = userImage;
        }

        @JsonAnyGetter
        public Map<String, Object> getAdditionalProperties() {
            return this.additionalProperties;
        }

        @JsonAnySetter
        public void setAdditionalProperty(String name, Object value) {
            this.additionalProperties.put(name, value);
        }

    }

}
