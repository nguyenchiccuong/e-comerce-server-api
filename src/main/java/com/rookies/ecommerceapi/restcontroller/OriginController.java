package com.rookies.ecommerceapi.restcontroller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rookies.ecommerceapi.dto.ResponseDto;
import com.rookies.ecommerceapi.service.OriginService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/public/origin")
public class OriginController {

    private static final Logger LOGGER = LoggerFactory.getLogger(OriginController.class);

    private final OriginService originService;

    @Autowired
    public OriginController(OriginService originService) {
        this.originService = originService;
    }

    @GetMapping
    public ResponseEntity<ResponseDto> retrieveOrigins() {
        return ResponseEntity.ok(originService.retrieveOrigins());
    }

}
