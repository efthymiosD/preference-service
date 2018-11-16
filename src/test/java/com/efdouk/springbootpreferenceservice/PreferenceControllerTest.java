package com.efdouk.springbootpreferenceservice;


import com.efdouk.springbootpreferenceservice.components.PreferenceService;
import com.efdouk.springbootpreferenceservice.shared.PreferenceDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class PreferenceControllerTest {

    private static final long VALID_APPLICATION_ID = 1L;
    private static final long VALID_CLIENT_ID = 2L;
    private static final String VALID_PROPERTY_NAME = "test";
    private static final long INVALID_APPLICATION_ID = -1L;
    private static final String SHORT_PROPERTY_NAME = "te";
    private static final String EMPTY_PROPERTY_NAME = " ";

    @Mock
    private PreferenceService preferenceService;
    private MockMvc mockMvc;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        PreferenceController controller = new PreferenceController(preferenceService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        given(preferenceService.addNewPreference(any(PreferenceDTO.class))).willReturn(true);
    }

    @Test
    public void addNewPreference_shouldReturnOkStatusAndTrueResponse() throws Exception {
        // given
        PreferenceDTO dto = getMockPreferenceDTO(VALID_APPLICATION_ID, VALID_CLIENT_ID, VALID_PROPERTY_NAME);

        // when & then
        mockMvc.perform(post(PreferenceController.BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(dto)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(containsString("true")));
        verify(preferenceService, times(1)).addNewPreference(any(PreferenceDTO.class));
    }

    @Test
    public void addNewPreference_shouldReturnOkStatusAndFalseResponse() throws Exception {
        // given
        PreferenceDTO dto = getMockPreferenceDTO(VALID_APPLICATION_ID, VALID_CLIENT_ID, VALID_PROPERTY_NAME);
        given(preferenceService.addNewPreference(any(PreferenceDTO.class))).willReturn(false);

        // when & then
        mockMvc.perform(post(PreferenceController.BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(dto)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(containsString("false")));
        verify(preferenceService, times(1)).addNewPreference(any(PreferenceDTO.class));
    }

    @Test
    public void addNewPreference_shouldReturnBadRequestForInvalidApplicationId() throws Exception {
        // given
        PreferenceDTO dto = getMockPreferenceDTO(INVALID_APPLICATION_ID, VALID_CLIENT_ID, VALID_PROPERTY_NAME);

        // when & then
        postRequestAndExpectBadRequest(dto);
    }

    @Test
    public void addNewPreference_shouldReturnBadRequestForNullId() throws Exception {
        // given
        PreferenceDTO dto = getMockPreferenceDTO(VALID_APPLICATION_ID, null, VALID_PROPERTY_NAME);

        // when & then
        postRequestAndExpectBadRequest(dto);
    }

    @Test
    public void addNewPreference_shouldReturnBadRequestForShortPropertyName() throws Exception {
        // given
        PreferenceDTO dto = getMockPreferenceDTO(VALID_APPLICATION_ID, VALID_CLIENT_ID, SHORT_PROPERTY_NAME);

        // when & then
        postRequestAndExpectBadRequest(dto);
    }

    @Test
    public void addNewPreference_shouldReturnBadRequestForEmptyPropertyName() throws Exception {
        // given
        PreferenceDTO dto = getMockPreferenceDTO(VALID_APPLICATION_ID, VALID_CLIENT_ID, EMPTY_PROPERTY_NAME);

        // when & then
        postRequestAndExpectBadRequest(dto);
    }

    private void postRequestAndExpectBadRequest(PreferenceDTO dto) throws Exception {
        mockMvc.perform(post(PreferenceController.BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(dto)))
                .andExpect(status().isBadRequest());
    }

    private PreferenceDTO getMockPreferenceDTO(Long applicationId, Long clientId, String propertyName) {
        PreferenceDTO dto = new PreferenceDTO();
        dto.setApplicationId(applicationId);
        dto.setClientId(clientId);
        dto.setPropertyName(propertyName);
        return dto;
    }

    private String asJsonString(Object object) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(object);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}