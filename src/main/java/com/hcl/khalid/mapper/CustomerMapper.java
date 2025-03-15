package com.hcl.khalid.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.hcl.khalid.dto.CustomerDto;
import com.hcl.khalid.entities.Customer;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
	CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);
    
//	@Mapping(target = "")
    Customer toEntity(CustomerDto dto);
    
//	@Mapping(target = "")
    CustomerDto toDTO(Customer entity);
}
