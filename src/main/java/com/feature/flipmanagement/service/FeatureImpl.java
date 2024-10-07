package com.feature.flipmanagement.service;

import com.feature.flipmanagement.dto.FeatureContextDTO;
import com.feature.flipmanagement.dto.FeatureContextRequest;
import com.feature.flipmanagement.dto.FeatureDTO;
import com.feature.flipmanagement.dto.FeatureRequest;
import com.feature.flipmanagement.mapper.FeatureMapper;
import com.feature.flipmanagement.model.FeatureContext;
import com.feature.flipmanagement.model.FeatureFlip;
import com.feature.flipmanagement.repository.FeatureContextRepository;
import com.feature.flipmanagement.repository.FeatureFlipRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@Service
@Slf4j
@RequiredArgsConstructor
public class FeatureImpl implements IFeatureFlip {

    private final FeatureFlipRepository featureFlipRepository;
    private final FeatureContextRepository featureContextRepository;
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
       FeatureFlip feature = featureFlipRepository.findByUuid(uuid)
                .orElseThrow(()-> new RuntimeException("Feature not found"));

        return FeatureMapper.featureToFeatureDTO(feature);

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
        featureContextRepository.findByNameFeature(featureFlip.getNameFeature())
                .ifPresent(featureContextRepository::delete);
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

    @Override
    public FeatureContextDTO createFeatureContext(FeatureContextRequest request) {
        log.info("Start creating context request --{}  ",request);
        FeatureContext featureContext = FeatureMapper.featureRequestToFeatureContext(request);
        List<String> groupUsers = new ArrayList<>();
        if(request.getUserGroups() !=null){
            String[] userIds = request.getUserGroups().split(",");
            Arrays.stream(userIds).forEach(id-> {
                assert false;
                groupUsers.add(id);
            });
            log.info("all users ids {} ",groupUsers);
            featureContext.getUserGroups().clear();
            featureContext.setUserGroups(groupUsers);
        }
        return FeatureMapper.featureContextToFeatureContextDTO(featureContextRepository.save(
                featureContext
        )
        );
    }

    @Override
    public List<FeatureContextDTO> listAllFeatureContext() {
        log.info("list all feature context");
        return featureContextRepository.findAll()
                .stream()
                .map(FeatureMapper::featureContextToFeatureContextDTO)
                .toList();
    }

    @Override
    public FeatureContextDTO getFeatureContextByUuid(String uuid) {
        log.info("getFeatureContextByUuid: with {}", uuid);
        FeatureContext featureContext= featureContextRepository.findByUuid(uuid)
                .orElseThrow(() -> new RuntimeException("FeatureContext not found"));

        return FeatureMapper.featureContextToFeatureContextDTO(featureContext);

    }

    @Override
    public FeatureContextDTO getFeatureContextByFeatureName(String nameFeature) {
        log.info("getFeatureContextByFeatureName: with {}", nameFeature);
       FeatureContext featureContext= featureContextRepository.findByNameFeature(nameFeature)
                .orElseThrow(() -> new RuntimeException("FeatureContext not found"));
        return FeatureMapper.featureContextToFeatureContextDTO(featureContext);
    }

    @Override
    public boolean deleteFeatureContext(String uuid) {
        log.info("deleteFeatureContext: with {}", uuid);
        FeatureContext featureContext = featureContextRepository.findByUuid(uuid)
                .orElseThrow(() -> new RuntimeException("FeatureContext not found"));
        featureContextRepository.delete(featureContext);
        return true;
    }

    @Override
    public FeatureDTO getFeatureByName(String name) {
        FeatureFlip feature = featureFlipRepository.findByNameFeature(name)
        .orElseThrow(()-> new RuntimeException("Feature not found"));

      return FeatureMapper.featureToFeatureDTO(feature);
    }

}
