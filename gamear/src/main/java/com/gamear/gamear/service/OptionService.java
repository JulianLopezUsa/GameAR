package com.gamear.gamear.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gamear.gamear.entity.Option;
import com.gamear.gamear.repository.OptionRepository;

@Service
public class OptionService {
    @Autowired
    private OptionRepository optionRepository;

    public List<Option> getAllOptions() {
        return optionRepository.findAll();
    }

    public Optional<Option> getOptionById(Long id) {
        return optionRepository.findById(id);
    }

    public Option saveOption(Option option) {
        return optionRepository.save(option);
    }

    public void deleteOptionById(Long id) {
        optionRepository.deleteById(id);
    }
   

}