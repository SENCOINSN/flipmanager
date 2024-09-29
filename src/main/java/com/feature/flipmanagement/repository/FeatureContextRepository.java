package com.feature.flipmanagement.repository;

import com.feature.flipmanagement.model.FeatureContext;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FeatureContextRepository extends JpaRepository<FeatureContext, String> {
    Optional<FeatureContext> findByNameFeature(String nameFeature);

    Optional<FeatureContext> findByUuid(String uuid);

}
