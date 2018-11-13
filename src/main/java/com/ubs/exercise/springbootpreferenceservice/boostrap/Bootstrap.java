package com.ubs.exercise.springbootpreferenceservice.boostrap;

import com.ubs.exercise.springbootpreferenceservice.components.PreferenceRepository;
import com.ubs.exercise.springbootpreferenceservice.domain.Preference;
import com.ubs.exercise.springbootpreferenceservice.domain.PreferenceId;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import static java.lang.String.format;

@Component
public class Bootstrap implements CommandLineRunner {

    private static final String PROPERTY_NAME = "show_one_time_confirmation_page";

    private final PreferenceRepository preferenceRepository;

    public Bootstrap(PreferenceRepository preferenceRepository) {
        this.preferenceRepository = preferenceRepository;
    }

    @Override
    public void run(String... args) {

        if (preferenceRepository.count() == 0) {
            System.out.println("Loading initial data on bootstrap");

            preferenceRepository.save(getPreference(1L, 1L));
            preferenceRepository.save(getPreference(2L, 2L));

            System.out.println(format("Loaded %d preferences.", preferenceRepository.count()));
        }
    }

    private Preference getPreference(Long applicationId, Long clientId) {
        Preference preference = new Preference();
        preference.setId(new PreferenceId(applicationId, clientId));
        preference.setPropertyName(PROPERTY_NAME);
        return preference;
    }

}
