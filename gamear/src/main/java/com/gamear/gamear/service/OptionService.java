package com.gamear.gamear.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gamear.gamear.entity.Option;
import com.gamear.gamear.repository.OptionRepository;

@Service
public class OptionService {
    @Autowired
    private OptionRepository optionRepository;

    public Option getOptionById(Long id) {
        return optionRepository.findById(id).orElse(null);
    }

    public Option createOption(Option option) {
        return optionRepository.save(option);
    }

}