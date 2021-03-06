package com.efdouk.springbootpreferenceservice.components;

import com.efdouk.springbootpreferenceservice.domain.Preference;
import com.efdouk.springbootpreferenceservice.domain.PreferenceId;
import com.efdouk.springbootpreferenceservice.shared.ModelMapper;
import com.efdouk.springbootpreferenceservice.shared.PreferenceDTO;
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
        given(preferenceDTOMapper.mapDtoToEntity(any(PreferenceDTO.class))).willReturn(mockPreference);
    }

    @Test
    public void addNewPreference_shouldNotAddPreferenceWithExistingId() {
        // given
        given(preferenceRepository.existsById(any(PreferenceId.class))).willReturn(true);

        // when
        boolean preferenceAdded = service.addNewPreference(mockPreferenceDTO);

        // then
        assertFalse(preferenceAdded);
    }

    @Test
    public void addNewPreference_shouldAddPreferenceWithNonExistingId() {
        // given
        given(preferenceRepository.existsById(any(PreferenceId.class))).willReturn(false);

        // when
        boolean preferenceAdded = service.addNewPreference(mockPreferenceDTO);

        // then
        verify(preferenceRepository, times(1)).save(mockPreference);
        assertTrue(preferenceAdded);
    }

}