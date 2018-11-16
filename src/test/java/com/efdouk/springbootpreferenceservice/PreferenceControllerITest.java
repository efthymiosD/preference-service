package com.efdouk.springbootpreferenceservice;

import com.efdouk.springbootpreferenceservice.components.PreferenceService;
import com.efdouk.springbootpreferenceservice.shared.PreferenceDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PreferenceControllerITest {

    private static final long APPLICATION_ID = 1L;
    private static final long CLIENT_ID = 3L;
    private static final long EXISTING_APPLICATION_ID = 1L;
    private static final long EXISTING_CLIENT_ID = 2L;
    private static final String PROPERTY_NAME = "test";

    @Autowired
    private PreferenceService preferenceService;

    @Test
    public void addNewPreference_shouldAddPropertyWithNewCompositeId() {
        // given
        PreferenceDTO mockPreferenceDTO = getMockPreferenceDTO(APPLICATION_ID, CLIENT_ID);
        // when
        boolean response = preferenceService.addNewPreference(mockPreferenceDTO);
        // then
        assertTrue(response);
    }

    @Test
    public void addNewPreference_shouldNotAddPropertyWithExistingCompositeId() {
        // given
        PreferenceDTO mockPreferenceDTO = getMockPreferenceDTO(EXISTING_APPLICATION_ID,
                EXISTING_CLIENT_ID);
        // when
        boolean response = preferenceService.addNewPreference(mockPreferenceDTO);
        // then
        assertFalse(response);
    }

    private PreferenceDTO getMockPreferenceDTO(Long applicationId, Long clientId) {
        PreferenceDTO dto = new PreferenceDTO();
        dto.setApplicationId(applicationId);
        dto.setClientId(clientId);
        dto.setPropertyName(PROPERTY_NAME);
        return dto;
    }

}