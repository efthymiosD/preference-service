package com.efdouk.springbootpreferenceservice.components;

import com.efdouk.springbootpreferenceservice.domain.Preference;
import com.efdouk.springbootpreferenceservice.domain.PreferenceId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PreferenceRepository extends JpaRepository<Preference, PreferenceId> {

}
