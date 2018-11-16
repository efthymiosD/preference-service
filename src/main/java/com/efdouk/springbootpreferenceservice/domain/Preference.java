package com.efdouk.springbootpreferenceservice.domain;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.util.Objects;
import java.util.StringJoiner;

@Entity
public class Preference {

    @EmbeddedId
    private PreferenceId id;
    private String propertyName;

    public PreferenceId getId() {
        return id;
    }

    public void setId(PreferenceId id) {
        this.id = id;
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
        Preference that = (Preference) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(propertyName, that.propertyName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, propertyName);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Preference.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("propertyName=" + propertyName)
                .toString();
    }

}
