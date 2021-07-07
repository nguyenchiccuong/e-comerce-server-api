package com.rookies.ecommerceapi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

import com.rookies.ecommerceapi.entity.Origin;
import com.rookies.ecommerceapi.repository.OriginRepository;
import com.rookies.ecommerceapi.service.OriginService;

@Service
public class OriginServiceImpl implements OriginService {
    private final OriginRepository originRepository;

    @Autowired
    public OriginServiceImpl(OriginRepository originRepository) {
        this.originRepository = originRepository;
    }

    @Override
    public List<Origin> retrieveOrigins() {
        return originRepository.findAll();
    }

}
