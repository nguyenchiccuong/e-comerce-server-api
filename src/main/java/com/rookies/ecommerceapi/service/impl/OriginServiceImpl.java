package com.rookies.ecommerceapi.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

import com.rookies.ecommerceapi.constant.SuccessCode;
import com.rookies.ecommerceapi.dto.OriginDto;
import com.rookies.ecommerceapi.dto.ResponseDto;
import com.rookies.ecommerceapi.entity.Origin;
import com.rookies.ecommerceapi.repository.OriginRepository;
import com.rookies.ecommerceapi.service.OriginService;

@Service
public class OriginServiceImpl implements OriginService {
    private final OriginRepository originRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public OriginServiceImpl(OriginRepository originRepository, ModelMapper modelMapper) {
        this.originRepository = originRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ResponseDto retrieveOrigins() {
        ResponseDto responseDto = new ResponseDto();
        List<Origin> origins = originRepository.findAll();
        List<OriginDto> originsDto = origins.stream().map(origin -> modelMapper.map(origin, OriginDto.class))
                .collect(Collectors.toList());
        responseDto.setData(originsDto);
        responseDto.setSuccessCode(SuccessCode.SUCCESS);
        return responseDto;
    }

}
