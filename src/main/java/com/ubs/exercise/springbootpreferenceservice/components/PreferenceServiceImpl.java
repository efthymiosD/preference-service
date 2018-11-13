package com.ubs.exercise.springbootpreferenceservice.components;

import com.ubs.exercise.springbootpreferenceservice.domain.Preference;
import com.ubs.exercise.springbootpreferenceservice.shared.ModelMapper;
import com.ubs.exercise.springbootpreferenceservice.shared.PreferenceDTO;
import org.springframework.stereotype.Service;

@Service
public class PreferenceServiceImpl implements PreferenceService {

    private final PreferenceRepository preferenceRepository;
    private final ModelMapper<PreferenceDTO, Preference> preferenceDTOMapper;

    public PreferenceServiceImpl(PreferenceRepository preferenceRepository,
                                 ModelMapper<PreferenceDTO, Preference> preferenceDTOMapper) {
        this.preferenceRepository = preferenceRepository;
        this.preferenceDTOMapper = preferenceDTOMapper;
    }

    @Override
    public boolean addNewPreference(PreferenceDTO dto) {
        Preference preference = preferenceDTOMapper.mapDtoToEntity(dto);
        if (preferenceRepository.existsById(preference.getId())) {
            return false;
        }
        preferenceRepository.save(preference);
        return true;
    }

}
