package org.rehab.app.models.response;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResLogin extends ResBase {
    @JsonProperty("data")
    private List<Data> data = new ArrayList<>();
    @JsonProperty("additionalData")
    private List<Object> additionalData = new ArrayList<Object>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();


    /**
     * @return The data
     */
    @JsonProperty("data")
    public List<Data> getData() {
        return data;
    }

    /**
     * @param data The data
     */
    @JsonProperty("data")
    public void setData(List<Data> data) {
        this.data = data;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }


    public static class Data {

        @JsonProperty("user_id")
        private Integer userId;
        @JsonProperty("user_full_name")
        private String userFullName;
        @JsonProperty("user_contact_number")
        private Object userContactNumber;
        @JsonProperty("user_email")
        private String userEmail;
        @JsonProperty("user_password")
        private String userPassword;
        @JsonProperty("user_dob")
        private String userDob;
        @JsonProperty("user_gender")
        private Integer userGender;
        @JsonProperty("user_image")
        private String userImage;
        @JsonProperty("user_company_name")
        private Object userCompanyName;
        @JsonProperty("user_website")
        private Object userWebsite;
        @JsonProperty("user_ip_address")
        private String userIpAddress;
        @JsonProperty("user_lat")
        private String userLat;
        @JsonProperty("user_long")
        private String userLong;
        @JsonProperty("remember_token")
        private Object rememberToken;
        @JsonProperty("user_access_token")
        private String userAccessToken;
        @JsonProperty("user_device_type")
        private String userDeviceType;
        @JsonProperty("user_device_token")
        private String userDeviceToken;
        @JsonProperty("created_at")
        private String createdAt;
        @JsonProperty("updated_at")
        private String updatedAt;
        @JsonProperty("status")
        private Integer status;
        @JsonIgnore
        private Map<String, Object> additionalProperties = new HashMap<String, Object>();

        /**
         * @return The userId
         */
        @JsonProperty("user_id")
        public Integer getUserId() {
            return userId;
        }

        /**
         * @param userId The user_id
         */
        @JsonProperty("user_id")
        public void setUserId(Integer userId) {
            this.userId = userId;
        }

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
         * @return The userContactNumber
         */
        @JsonProperty("user_contact_number")
        public Object getUserContactNumber() {
            return userContactNumber;
        }

        /**
         * @param userContactNumber The user_contact_number
         */
        @JsonProperty("user_contact_number")
        public void setUserContactNumber(Object userContactNumber) {
            this.userContactNumber = userContactNumber;
        }

        /**
         * @return The userEmail
         */
        @JsonProperty("user_email")
        public String getUserEmail() {
            return userEmail;
        }

        /**
         * @param userEmail The user_email
         */
        @JsonProperty("user_email")
        public void setUserEmail(String userEmail) {
            this.userEmail = userEmail;
        }

        /**
         * @return The userPassword
         */
        @JsonProperty("user_password")
        public String getUserPassword() {
            return userPassword;
        }

        /**
         * @param userPassword The user_password
         */
        @JsonProperty("user_password")
        public void setUserPassword(String userPassword) {
            this.userPassword = userPassword;
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
        public Integer getUserGender() {
            return userGender;
        }

        /**
         * @param userGender The user_gender
         */
        @JsonProperty("user_gender")
        public void setUserGender(Integer userGender) {
            this.userGender = userGender;
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

        /**
         * @return The userCompanyName
         */
        @JsonProperty("user_company_name")
        public Object getUserCompanyName() {
            return userCompanyName;
        }

        /**
         * @param userCompanyName The user_company_name
         */
        @JsonProperty("user_company_name")
        public void setUserCompanyName(Object userCompanyName) {
            this.userCompanyName = userCompanyName;
        }

        /**
         * @return The userWebsite
         */
        @JsonProperty("user_website")
        public Object getUserWebsite() {
            return userWebsite;
        }

        /**
         * @param userWebsite The user_website
         */
        @JsonProperty("user_website")
        public void setUserWebsite(Object userWebsite) {
            this.userWebsite = userWebsite;
        }

        /**
         * @return The userIpAddress
         */
        @JsonProperty("user_ip_address")
        public String getUserIpAddress() {
            return userIpAddress;
        }

        /**
         * @param userIpAddress The user_ip_address
         */
        @JsonProperty("user_ip_address")
        public void setUserIpAddress(String userIpAddress) {
            this.userIpAddress = userIpAddress;
        }

        /**
         * @return The userLat
         */
        @JsonProperty("user_lat")
        public String getUserLat() {
            return userLat;
        }

        /**
         * @param userLat The user_lat
         */
        @JsonProperty("user_lat")
        public void setUserLat(String userLat) {
            this.userLat = userLat;
        }

        /**
         * @return The userLong
         */
        @JsonProperty("user_long")
        public String getUserLong() {
            return userLong;
        }

        /**
         * @param userLong The user_long
         */
        @JsonProperty("user_long")
        public void setUserLong(String userLong) {
            this.userLong = userLong;
        }

        /**
         * @return The rememberToken
         */
        @JsonProperty("remember_token")
        public Object getRememberToken() {
            return rememberToken;
        }

        /**
         * @param rememberToken The remember_token
         */
        @JsonProperty("remember_token")
        public void setRememberToken(Object rememberToken) {
            this.rememberToken = rememberToken;
        }

        /**
         * @return The userAccessToken
         */
        @JsonProperty("user_access_token")
        public String getUserAccessToken() {
            return userAccessToken;
        }

        /**
         * @param userAccessToken The user_access_token
         */
        @JsonProperty("user_access_token")
        public void setUserAccessToken(String userAccessToken) {
            this.userAccessToken = userAccessToken;
        }

        /**
         * @return The userDeviceType
         */
        @JsonProperty("user_device_type")
        public String getUserDeviceType() {
            return userDeviceType;
        }

        /**
         * @param userDeviceType The user_device_type
         */
        @JsonProperty("user_device_type")
        public void setUserDeviceType(String userDeviceType) {
            this.userDeviceType = userDeviceType;
        }

        /**
         * @return The userDeviceToken
         */
        @JsonProperty("user_device_token")
        public String getUserDeviceToken() {
            return userDeviceToken;
        }

        /**
         * @param userDeviceToken The user_device_token
         */
        @JsonProperty("user_device_token")
        public void setUserDeviceToken(String userDeviceToken) {
            this.userDeviceToken = userDeviceToken;
        }

        /**
         * @return The createdAt
         */
        @JsonProperty("created_at")
        public String getCreatedAt() {
            return createdAt;
        }

        /**
         * @param createdAt The created_at
         */
        @JsonProperty("created_at")
        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        /**
         * @return The updatedAt
         */
        @JsonProperty("updated_at")
        public String getUpdatedAt() {
            return updatedAt;
        }

        /**
         * @param updatedAt The updated_at
         */
        @JsonProperty("updated_at")
        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }

        /**
         * @return The status
         */
        @JsonProperty("status")
        public Integer getStatus() {
            return status;
        }

        /**
         * @param status The status
         */
        @JsonProperty("status")
        public void setStatus(Integer status) {
            this.status = status;
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
