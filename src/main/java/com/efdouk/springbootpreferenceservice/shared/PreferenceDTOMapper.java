package com.efdouk.springbootpreferenceservice.shared;

import com.efdouk.springbootpreferenceservice.domain.Preference;
import com.efdouk.springbootpreferenceservice.domain.PreferenceId;
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
