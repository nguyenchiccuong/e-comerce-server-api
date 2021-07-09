package com.rookies.ecommerceapi.restcontroller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.stream.Collectors;
import java.util.List;

import com.rookies.ecommerceapi.dto.OriginDto;
import com.rookies.ecommerceapi.entity.Origin;
import com.rookies.ecommerceapi.service.OriginService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/public/origin")
public class OriginController {
    private final OriginService originService;

    private final ModelMapper modelMapper;

    @Autowired
    public OriginController(OriginService originService, ModelMapper modelMapper) {
        this.originService = originService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public List<OriginDto> retrieveOrigins() {
        return originService.retrieveOrigins().stream().map(origin -> modelMapper.map(origin, OriginDto.class))
                .collect(Collectors.toList());
    }

}
