package com.ubs.exercise.springbootpreferenceservice.domain;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;
import java.util.StringJoiner;

@Embeddable
public class PreferenceId implements Serializable {

    private Long applicationId;
    private Long clientId;

    public PreferenceId() {
    }

    public PreferenceId(Long applicationId, Long clientId) {
        this.applicationId = applicationId;
        this.clientId = clientId;
    }

    public Long getApplicationId() {
        return applicationId;
    }

    public Long getClientId() {
        return clientId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PreferenceId that = (PreferenceId) o;
        return Objects.equals(applicationId, that.applicationId) &&
                Objects.equals(clientId, that.clientId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(applicationId, clientId);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", PreferenceId.class.getSimpleName() + "[", "]")
                .add("applicationId=" + applicationId)
                .add("clientId=" + clientId)
                .toString();
    }

}
