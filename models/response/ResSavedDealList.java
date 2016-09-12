
package org.rehab.app.models.response;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ResSavedDealList {

    @JsonProperty("status")
    private Integer status;
    @JsonProperty("error_code")
    private Integer errorCode;
    @JsonProperty("response_string")
    private String responseString;
    @JsonProperty("total_records")
    private Integer totalRecords;
    @JsonProperty("par_page_records")
    private Integer parPageRecords;
    @JsonProperty("total_page")
    private Integer totalPage;
    @JsonProperty("current_page")
    private String currentPage;
    @JsonProperty("data")
    private List<DealItem> data = new ArrayList<DealItem>();
    @JsonProperty("additionalData")
    private List<Object> additionalData = new ArrayList<Object>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The status
     */
    @JsonProperty("status")
    public Integer getStatus() {
        return status;
    }

    /**
     * 
     * @param status
     *     The status
     */
    @JsonProperty("status")
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 
     * @return
     *     The errorCode
     */
    @JsonProperty("error_code")
    public Integer getErrorCode() {
        return errorCode;
    }

    /**
     * 
     * @param errorCode
     *     The error_code
     */
    @JsonProperty("error_code")
    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * 
     * @return
     *     The responseString
     */
    @JsonProperty("response_string")
    public String getResponseString() {
        return responseString;
    }

    /**
     * 
     * @param responseString
     *     The response_string
     */
    @JsonProperty("response_string")
    public void setResponseString(String responseString) {
        this.responseString = responseString;
    }

    /**
     * 
     * @return
     *     The totalRecords
     */
    @JsonProperty("total_records")
    public Integer getTotalRecords() {
        return totalRecords;
    }

    /**
     * 
     * @param totalRecords
     *     The total_records
     */
    @JsonProperty("total_records")
    public void setTotalRecords(Integer totalRecords) {
        this.totalRecords = totalRecords;
    }

    /**
     * 
     * @return
     *     The parPageRecords
     */
    @JsonProperty("par_page_records")
    public Integer getParPageRecords() {
        return parPageRecords;
    }

    /**
     * 
     * @param parPageRecords
     *     The par_page_records
     */
    @JsonProperty("par_page_records")
    public void setParPageRecords(Integer parPageRecords) {
        this.parPageRecords = parPageRecords;
    }

    /**
     * 
     * @return
     *     The totalPage
     */
    @JsonProperty("total_page")
    public Integer getTotalPage() {
        return totalPage;
    }

    /**
     * 
     * @param totalPage
     *     The total_page
     */
    @JsonProperty("total_page")
    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    /**
     * 
     * @return
     *     The currentPage
     */
    @JsonProperty("current_page")
    public String getCurrentPage() {
        return currentPage;
    }

    /**
     * 
     * @param currentPage
     *     The current_page
     */
    @JsonProperty("current_page")
    public void setCurrentPage(String currentPage) {
        this.currentPage = currentPage;
    }

    /**
     * 
     * @return
     *     The data
     */
    @JsonProperty("data")
    public List<DealItem> getData() {
        return data;
    }

    /**
     * 
     * @param data
     *     The data
     */
    @JsonProperty("data")
    public void setData(List<DealItem> data) {
        this.data = data;
    }

    /**
     * 
     * @return
     *     The additionalData
     */
    @JsonProperty("additionalData")
    public List<Object> getAdditionalData() {
        return additionalData;
    }

    /**
     * 
     * @param additionalData
     *     The additionalData
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

}
