package com.efdouk.springbootpreferenceservice;

import com.efdouk.springbootpreferenceservice.components.PreferenceService;
import com.efdouk.springbootpreferenceservice.shared.PreferenceDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(PreferenceController.BASE_URL)
public class PreferenceController {

    public static final String BASE_URL = "/api/v1/preferences";
    private final PreferenceService preferenceService;

    public PreferenceController(PreferenceService preferenceService) {
        this.preferenceService = preferenceService;
    }

    @PostMapping
    public ResponseEntity<Boolean> addNewPreference(@Valid @RequestBody PreferenceDTO preferenceDTO) {
        return ResponseEntity.ok(preferenceService.addNewPreference(preferenceDTO));
    }

}
