package com.ubs.exercise.springbootpreferenceservice.components;

import com.ubs.exercise.springbootpreferenceservice.domain.Preference;
import com.ubs.exercise.springbootpreferenceservice.domain.PreferenceId;
import com.ubs.exercise.springbootpreferenceservice.shared.ModelMapper;
import com.ubs.exercise.springbootpreferenceservice.shared.PreferenceDTO;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class PreferenceServiceImplTest {

    @Mock
    private PreferenceRepository preferenceRepository;
    @Mock
    private ModelMapper<PreferenceDTO, Preference> preferenceDTOMapper;

    private PreferenceService service;
    private Preference mockPreference;
    private PreferenceDTO mockPreferenceDTO;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        service = new PreferenceServiceImpl(preferenceRepository, preferenceDTOMapper);
        mockPreference = new Preference();
        mockPreference.setId(new PreferenceId());
        mockPreferenceDTO = new PreferenceDTO();
    }

    @Test
    public void addNewPreference_shouldNotAddPreferenceWithExistingId() {
        // given
        given(preferenceDTOMapper.mapDtoToEntity(any(PreferenceDTO.class))).willReturn(mockPreference);
        given(preferenceRepository.existsById(any(PreferenceId.class))).willReturn(true);

        // when
        boolean preferenceAdded = service.addNewPreference(mockPreferenceDTO);

        // then
        assertFalse(preferenceAdded);
    }

    @Test
    public void addNewPreference_shouldAddPreferenceWithNonExistingId() {
        // given
        given(preferenceDTOMapper.mapDtoToEntity(any(PreferenceDTO.class))).willReturn(mockPreference);
        given(preferenceRepository.existsById(any(PreferenceId.class))).willReturn(false);

        // when
        boolean preferenceAdded = service.addNewPreference(mockPreferenceDTO);

        // then
        verify(preferenceRepository, times(1)).save(mockPreference);
        assertTrue(preferenceAdded);
    }

}