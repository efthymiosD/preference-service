package com.efdouk.springbootpreferenceservice.shared;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;
import java.util.StringJoiner;

/**
 * Preference DTO object encapsulates the request body and implements bean validation
 *
 * @author Efthymios Doukas
 */
public class PreferenceDTO implements Serializable {

    @NotNull(message = "Application id must be provided.")
    @Positive
    private Long applicationId;
    @NotNull(message = "Client id must be provided.")
    @Positive
    private Long clientId;
    @NotBlank(message = "Property name must be provided.")
    @Size(min = 3, message = "Property name should be 3 characters long at least.")
    private String propertyName;

    public Long getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Long applicationId) {
        this.applicationId = applicationId;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PreferenceDTO that = (PreferenceDTO) o;
        return Objects.equals(applicationId, that.applicationId) &&
                Objects.equals(clientId, that.clientId) &&
                Objects.equals(propertyName, that.propertyName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(applicationId, clientId, propertyName);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", PreferenceDTO.class.getSimpleName() + "[", "]")
                .add("applicationId=" + applicationId)
                .add("clientId=" + clientId)
                .add("propertyName='" + propertyName + "'")
                .toString();
    }

}
