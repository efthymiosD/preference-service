package com.ubs.exercise.springbootpreferenceservice.shared;

import com.ubs.exercise.springbootpreferenceservice.domain.Preference;
import com.ubs.exercise.springbootpreferenceservice.domain.PreferenceId;
import org.springframework.stereotype.Component;

@Component
public class PreferenceDTOMapper implements ModelMapper<PreferenceDTO, Preference> {

    @Override
    public Preference mapDtoToEntity(PreferenceDTO dto) {
        Preference newPreference = new Preference();
        newPreference.setId(new PreferenceId(dto.getApplicationId(), dto.getClientId()));
        newPreference.setPropertyName(dto.getPropertyName());
        return newPreference;
    }

}
