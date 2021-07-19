package com.rookies.ecommerceapi.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

import com.rookies.ecommerceapi.constant.SuccessCode;
import com.rookies.ecommerceapi.dto.BrandDto;
import com.rookies.ecommerceapi.dto.ResponseDto;
import com.rookies.ecommerceapi.entity.Brand;
import com.rookies.ecommerceapi.service.BrandService;
import com.rookies.ecommerceapi.repository.BrandRepository;

@Service
public class BrandServiceImpl implements BrandService {
    private final BrandRepository brandRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public BrandServiceImpl(BrandRepository brandRepository, ModelMapper modelMapper) {
        this.brandRepository = brandRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ResponseDto retrieveBrands() {
        ResponseDto responseDto = new ResponseDto();
        List<Brand> brands = brandRepository.findAll();
        List<BrandDto> brandsDto = brands.stream().map(brand -> modelMapper.map(brand, BrandDto.class))
                .collect(Collectors.toList());
        responseDto.setData(brandsDto);
        responseDto.setSuccessCode(SuccessCode.SUCCESS_GET_ALL_BRAND);
        return responseDto;
    }

}
