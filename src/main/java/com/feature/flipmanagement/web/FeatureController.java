package com.feature.flipmanagement.web;

import com.feature.flipmanagement.constants.ApiPath;
import com.feature.flipmanagement.dto.FeatureRequest;
import com.feature.flipmanagement.service.IFeatureFlip;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping(ApiPath.URL_ROOT)
@RequiredArgsConstructor
public class FeatureController {

    private final IFeatureFlip featureService;

    @GetMapping("/console")
    public String home(Model model) {
        model.addAttribute("features", featureService.listAllFeatures());
        return "feature";
    }

    @PostMapping("/create")
    public String createFeature(@Valid FeatureRequest request, BindingResult result,
                                RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "feature";
        }
        featureService.createFeature(request);
        redirectAttributes.addFlashAttribute("message", "Feature created successfully!");
        return "redirect:/feature/console";
    }

}
