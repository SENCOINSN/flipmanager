package com.feature.flipmanagement.web;

import com.feature.flipmanagement.constants.ApiPath;
import com.feature.flipmanagement.dto.FeatureDTO;
import com.feature.flipmanagement.dto.FeatureRequest;
import com.feature.flipmanagement.service.IFeatureFlip;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


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
    public String createFeature(@Valid FeatureRequest featureRequest, BindingResult result,
                                RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "feature";
        }
        featureService.createFeature(featureRequest);
        redirectAttributes.addFlashAttribute("message", "Feature created successfully!");
        return "redirect:/feature/console";
    }

    @GetMapping("/list")
    public ResponseEntity<List<FeatureDTO>> listAllFeatures() {
        return ResponseEntity.ok(featureService.listAllFeatures());
    }

    @RequestMapping(value = "/getFeature",method = RequestMethod.GET)
    @ResponseBody
    public FeatureDTO getFeature(String uuid) {
        return featureService.getFeatureByUuid(uuid);
    }


    @RequestMapping(value = "/changeStatus",method = RequestMethod.GET)
    public String changeStatus(@RequestParam(name="uuid")String uuid,RedirectAttributes redirectAttributes) {
        boolean status = featureService.changeStatus(uuid);
        redirectAttributes.addFlashAttribute("message", "flip is "+ (status ? "Activated !" : "Deactivated !"));
        return "redirect:/feature/console";
    }

    @GetMapping("/context")
    public String featureContext(Model model){
        return "featureContext";
    }

}
