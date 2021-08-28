package com.example.techthink.service.impl;

import com.example.techthink.persistence.Format;
import com.example.techthink.persistence.repository.FormatRepository;
import com.example.techthink.service.FormatService;
import org.springframework.stereotype.Service;

@Service
public class FormatServiceImpl implements FormatService {

    private final FormatRepository formatRepository;

    public FormatServiceImpl(FormatRepository formatRepository) {
        this.formatRepository = formatRepository;
    }

    @Override
    public Format getFormatById(Integer id) {
        return formatRepository.getById(id);
    }
}
