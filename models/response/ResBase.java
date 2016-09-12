
package org.rehab.app.models.response;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)

public class ResBase {

    @JsonProperty("error_code")
    private int code=-1;
    @JsonProperty("response_string")
    private String message;
    @JsonProperty("status")
    private int status;

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();


    /**
     * @return The errorCode
     */
    @JsonProperty("error_code")
    public int getErrorCode() {
        return code;
    }

    /**
     * @param code The error_code
     */
    @JsonProperty("error_code")
    public void setErrorCode(int code) {
        this.code = code;
    }

    /**
     * @return The errorString
     */
    @JsonProperty("response_string")
    public String getMessage() {
        return message;
    }

    /**
     * @param message The error_string
     */
    @JsonProperty("response_string")
    public void setErrorString(String message) {
        this.message = message;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
    @JsonProperty("status")
    public int getStatus() {
        return status;
    }
    @JsonProperty("status")
    public void setStatus(int status) {
        this.status = status;
    }
}
