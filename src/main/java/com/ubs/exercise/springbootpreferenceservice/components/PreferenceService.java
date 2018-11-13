package com.ubs.exercise.springbootpreferenceservice.components;

import com.ubs.exercise.springbootpreferenceservice.shared.PreferenceDTO;

/**
 * @author Efthymios Doukas
 */
public interface PreferenceService {

    /**
     * Creates a preference only for a new user(ApplicationId & ClientId) and returns feedback on creation
     *
     * @param preferenceDTO preference dto object encapsulates the client request parameters
     * @return whether the preference was created or not
     */
    boolean addNewPreference(PreferenceDTO preferenceDTO);

}
