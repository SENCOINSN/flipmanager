package com.feature.flipmanagement.web;

import com.feature.flipmanagement.constants.ApiPath;
import com.feature.flipmanagement.service.IFeatureFlip;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping(ApiPath.URL_ROOT)
@RequiredArgsConstructor
public class FeatureController {

    private final IFeatureFlip featureService;

  @GetMapping("/console")
    public String home() {
        return "feature";
    }

}
