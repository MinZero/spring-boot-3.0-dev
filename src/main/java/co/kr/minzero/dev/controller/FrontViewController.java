package co.kr.minzero.dev.controller;

import co.kr.minzero.dev.repository.ComplexRepository;
import co.kr.minzero.dev.repository.MorphInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.spring6.context.webflux.IReactiveDataDriverContextVariable;
import org.thymeleaf.spring6.context.webflux.ReactiveDataDriverContextVariable;

import java.time.Duration;

@Controller
public class FrontViewController {

    @Autowired
    private MorphInfoRepository morphRepository;

    @Autowired
    private ComplexRepository complexRepository;


    @GetMapping("/")
    public String getMain(Model model) {
        model.addAttribute("msg", "hello world~!!!");
        return "index";
    }

    @GetMapping("/morph")
    public String morphList(Model model) {
        IReactiveDataDriverContextVariable reactiveDataDrivenMode =
                new ReactiveDataDriverContextVariable(morphRepository.findAll().delayElements(Duration.ofMillis(50)), 1);

        model.addAttribute("morphList", reactiveDataDrivenMode);

        return "morph-list";
    }

    @GetMapping("/complex")
    public String complexList(Model model) {
        IReactiveDataDriverContextVariable reactiveDataDrivenMode =
                new ReactiveDataDriverContextVariable(complexRepository.findAll().delayElements(Duration.ofSeconds(1)), 1);

        model.addAttribute("complexList", reactiveDataDrivenMode);

        return "complex-list";
    }
}
