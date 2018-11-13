package com.ubs.exercise.springbootpreferenceservice;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.ubs.exercise.springbootpreferenceservice.components.PreferenceService;
import com.ubs.exercise.springbootpreferenceservice.shared.PreferenceDTO;
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

    @Mock
    private PreferenceService preferenceService;

    private PreferenceController controller;
    private MockMvc mockMvc;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        controller = new PreferenceController(preferenceService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void addNewPreference_shouldReturnOkAndTrue() throws Exception {
        // given
        PreferenceDTO dto = new PreferenceDTO();
        dto.setApplicationId(1L);
        dto.setClientId(2L);
        dto.setPropertyName("test");
        given(preferenceService.addNewPreference(any(PreferenceDTO.class))).willReturn(true);

        // when & then
        mockMvc.perform(post(PreferenceController.BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(dto)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(containsString("true")));
        verify(preferenceService, times(1)).addNewPreference(any(PreferenceDTO.class));
    }

    @Test
    public void addNewPreference_shouldReturnBadRequestForInvalidPropertyName() throws Exception {
        // given
        PreferenceDTO dto = new PreferenceDTO();
        dto.setApplicationId(1L);
        dto.setClientId(2L);
        dto.setPropertyName("te");
        given(preferenceService.addNewPreference(any(PreferenceDTO.class))).willReturn(true);

        // when & then
        mockMvc.perform(post(PreferenceController.BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(dto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void addNewPreference_shouldReturnBadRequestForInvalidApplicationId() throws Exception {
        // given
        PreferenceDTO dto = new PreferenceDTO();
        dto.setApplicationId(-1L);
        dto.setClientId(2L);
        dto.setPropertyName("test");
        given(preferenceService.addNewPreference(any(PreferenceDTO.class))).willReturn(true);

        // when & then
        mockMvc.perform(post(PreferenceController.BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(dto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void addNewPreference_shouldReturnBadRequestForNullProperty() throws Exception {
        // given
        PreferenceDTO dto = new PreferenceDTO();
        dto.setApplicationId(1L);
        dto.setClientId(null);
        dto.setPropertyName("test");
        given(preferenceService.addNewPreference(any(PreferenceDTO.class))).willReturn(true);

        // when & then
        mockMvc.perform(post(PreferenceController.BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(dto)))
                .andExpect(status().isBadRequest());
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