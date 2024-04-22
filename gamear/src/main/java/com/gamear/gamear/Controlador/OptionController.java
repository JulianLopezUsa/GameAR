package com.gamear.gamear.Controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gamear.gamear.entity.Option;
import com.gamear.gamear.service.OptionService;

@RestController
@RequestMapping("/option")
public class OptionController {
    @Autowired
    private OptionService optionService;

    @GetMapping("/{id}")
    public Option getOptionById(@PathVariable Long id) {
        return optionService.getOptionById(id);
    }

    @PostMapping("/create")
    public Option createOption(@RequestBody Option option) {
        return optionService.createOption(option);
    }

}
