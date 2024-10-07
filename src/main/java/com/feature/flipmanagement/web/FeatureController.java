package com.feature.flipmanagement.web;

import com.feature.flipmanagement.constants.ApiPath;
import com.feature.flipmanagement.dto.FeatureContextDTO;
import com.feature.flipmanagement.dto.FeatureContextRequest;
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
    @ResponseBody
    public List<FeatureDTO> listAllFeatures() {
        return featureService.listAllFeatures();
    }

    @RequestMapping(value = "/getFeature",method = RequestMethod.GET)
    @ResponseBody
    public FeatureDTO getFeature(String uuid) {
        return featureService.getFeatureByUuid(uuid);
    }

    @RequestMapping(value = "/feature-by-name",method = RequestMethod.GET)
    @ResponseBody
    public FeatureDTO getFeatureName(String name) {
        return featureService.getFeatureByName(name);
    }


    @RequestMapping(value = "/changeStatus",method = RequestMethod.GET)
    public String changeStatus(@RequestParam(name="uuid")String uuid,RedirectAttributes redirectAttributes) {
        boolean status = featureService.changeStatus(uuid);
        redirectAttributes.addFlashAttribute("message", "flip is "+ (status ? "Activated !" : "Deactivated !"));
        return "redirect:/feature/console";
    }

    @GetMapping("/context")
    public String featureContext(Model model){
        model.addAttribute("features", featureService.listAllFeatures());
        model.addAttribute("featureContexts", featureService.listAllFeatureContext());
        return "featureContext";
    }

    @PostMapping("/create-context")
    public String createFeature(@Valid FeatureContextRequest featureRequest, BindingResult result,
                                RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "featureContext";
        }
        featureService.createFeatureContext(featureRequest);
        redirectAttributes.addFlashAttribute("message", "Feature Context created successfully!");
        return "redirect:/feature/context";
    }


    @RequestMapping(value = "/getFeatureContext",method = RequestMethod.GET)
    @ResponseBody
    public FeatureContextDTO getFeatureContext(String uuid) {
        return featureService.getFeatureContextByUuid(uuid);
    }

    @RequestMapping(value = "/feature-context-by-name",method = RequestMethod.GET)
    @ResponseBody
    public FeatureContextDTO getfeatureContextByName(String name){
        return featureService.getFeatureContextByFeatureName(name);
    }



    @GetMapping("all-context")
    @ResponseBody
    public List<FeatureContextDTO> allFeaturesContext(){
        return featureService.listAllFeatureContext();
    }

    @GetMapping("/deleteFeature")
    public String deleteFeature(String  uuid) {
        featureService.deleteFeature(uuid);
        return "redirect:/feature/console";
    }

    @GetMapping("/deleteContext")
    public String deleteFeatureContext(String  uuid,Model model,RedirectAttributes redirectAttributes) {
        boolean result = featureService.deleteFeatureContext(uuid);
        redirectAttributes.addFlashAttribute("message",(result) ? "Feature Context deleted successfully!":"Can't delete Feature Context!");
        return "redirect:/feature/context";
    }

    @GetMapping("/connect")
    @ResponseBody
    public String connect() {
        return "OK";
    }

   

}
