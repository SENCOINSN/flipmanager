package com.feature.flipmanagement.repository;

import com.feature.flipmanagement.model.FeatureFlip;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FeatureFlipRepository  extends JpaRepository<FeatureFlip, String> {
    Optional<FeatureFlip> findByUuid(String uuid);
    Optional<FeatureFlip> findByNameFeature(String nameFeature);

}
