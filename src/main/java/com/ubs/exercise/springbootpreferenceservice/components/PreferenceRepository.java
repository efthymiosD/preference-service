package com.ubs.exercise.springbootpreferenceservice.components;

import com.ubs.exercise.springbootpreferenceservice.domain.Preference;
import com.ubs.exercise.springbootpreferenceservice.domain.PreferenceId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PreferenceRepository extends JpaRepository<Preference, PreferenceId> {

}
