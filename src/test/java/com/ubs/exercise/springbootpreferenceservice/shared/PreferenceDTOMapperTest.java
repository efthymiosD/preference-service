package com.ubs.exercise.springbootpreferenceservice.shared;

import com.ubs.exercise.springbootpreferenceservice.domain.Preference;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PreferenceDTOMapperTest {

    private static final Long APPLICATION_ID = 1L;
    private static final Long CLIENT_ID = 3L;
    private static final String PROPERTY_NAME = "testProperty";

    private ModelMapper<PreferenceDTO, Preference> mapper = new PreferenceDTOMapper();

    @Test
    public void mapDtoToEntity() {
        // given
        PreferenceDTO dto = new PreferenceDTO();
        dto.setApplicationId(APPLICATION_ID);
        dto.setClientId(CLIENT_ID);
        dto.setPropertyName(PROPERTY_NAME);

        // when
        Preference preference = mapper.mapDtoToEntity(dto);

        // then
        assertEquals(APPLICATION_ID, preference.getId().getApplicationId());
        assertEquals(CLIENT_ID, preference.getId().getClientId());
        assertEquals(PROPERTY_NAME, preference.getPropertyName());
    }

}