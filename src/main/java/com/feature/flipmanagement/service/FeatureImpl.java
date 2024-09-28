package com.feature.flipmanagement.service;

import com.feature.flipmanagement.dto.FeatureDTO;
import com.feature.flipmanagement.dto.FeatureRequest;
import com.feature.flipmanagement.mapper.FeatureMapper;
import com.feature.flipmanagement.model.FeatureFlip;
import com.feature.flipmanagement.repository.FeatureFlipRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@Slf4j
@RequiredArgsConstructor
public class FeatureImpl implements IFeatureFlip {

    private final FeatureFlipRepository featureFlipRepository;
    ;

    @Override
    public List<FeatureDTO> listAllFeatures() {
        return featureFlipRepository.findAll()
                .stream()
                .map(FeatureMapper::featureToFeatureDTO)
                .toList();
    }

    @Override
    public FeatureDTO getFeatureByUuid(String uuid) {
        return featureFlipRepository.findByUuid(uuid)
                .isPresent()
                ? FeatureMapper.featureToFeatureDTO(featureFlipRepository.findByUuid(uuid).get())
                : null;

    }

    @Override
    public FeatureDTO createFeature(FeatureRequest request) {
        log.info("createFeature: {}", request);
        Optional<FeatureFlip> optionalFeatureFlip = featureFlipRepository.findByUuid(request.getUuid());
        if (optionalFeatureFlip.isPresent()) {
            FeatureFlip featureFlip = optionalFeatureFlip.get();
            featureFlip.setNameFeature(request.getNameFeature());
            featureFlip.setDescriptionFeature(request.getDescriptionFeature());
           return processCreate(featureFlip);
        }
        else{
            FeatureFlip feature = FeatureMapper.featureRequestToFeature(request);
            return processCreate(feature);
        }
    }

    private FeatureDTO processCreate(FeatureFlip featureFlip){
        featureFlipRepository.save(featureFlip);
        return FeatureMapper.featureToFeatureDTO(featureFlip);
    }

    @Override
    public FeatureDTO updateFeature(FeatureRequest featureDTO) {
        log.info("updateFeature: {}", featureDTO);
        Optional<FeatureFlip> optionalFeatureFlip = featureFlipRepository.findByNameFeature(featureDTO.getNameFeature());
        if (optionalFeatureFlip.isPresent()) {
            FeatureFlip featureFlip = optionalFeatureFlip.get();
            featureFlip.setNameFeature(featureDTO.getNameFeature());
            featureFlip.setDescriptionFeature(featureDTO.getDescriptionFeature());
            featureFlipRepository.save(featureFlip);
            return FeatureMapper.featureToFeatureDTO(featureFlip);
        }
        throw new RuntimeException("Feature not found");

    }

    @Override
    public void deleteFeature(String uuid) {
        FeatureFlip featureFlip = featureFlipRepository.findByUuid(uuid)
                .orElseThrow(() -> new RuntimeException("Feature not found"));
        featureFlipRepository.delete(featureFlip);

    }

    @Override
    public boolean changeStatus(String uuid) {
        log.info("changeStatus flip: {}", uuid);
        FeatureFlip featureFlip = featureFlipRepository.findByUuid(uuid)
                .orElseThrow(() -> new RuntimeException("Feature not found"));
        featureFlip.setActivate(!featureFlip.isActivate());
        featureFlipRepository.save(featureFlip);
        log.info("changeStatus flip changed to  : {}", featureFlip.isActivate());
        return featureFlip.isActivate();
    }
}
