package site.easy.to.build.crm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import site.easy.to.build.crm.service.FakeDataService;

@Controller
public class FakeDataController {

    @Autowired
    private FakeDataService fakeDataService;

    @GetMapping("/fake-data")
    public String getFakeData(Model model) {
        model.addAttribute("name", fakeDataService.generateFakeName());
        model.addAttribute("email", fakeDataService.generateFakeEmail());
        model.addAttribute("address", fakeDataService.generateFakeAddress());
        return "fake-data";
    }
}
