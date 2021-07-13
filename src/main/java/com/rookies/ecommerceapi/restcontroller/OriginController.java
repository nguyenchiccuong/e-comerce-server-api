package com.rookies.ecommerceapi.restcontroller;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.stream.Collectors;
import java.util.Collection;
import java.util.List;

import com.rookies.ecommerceapi.dto.OriginDto;
import com.rookies.ecommerceapi.dto.ResponseDto;
import com.rookies.ecommerceapi.service.OriginService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/public/origin")
public class OriginController {

    private static final Logger LOGGER = LoggerFactory.getLogger(OriginController.class);

    private final OriginService originService;

    private final ModelMapper modelMapper;

    @Autowired
    public OriginController(OriginService originService, ModelMapper modelMapper) {
        this.originService = originService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public ResponseEntity<ResponseDto> retrieveOrigins() {
        return ResponseEntity.ok(originService.retrieveOrigins());
    }

}
