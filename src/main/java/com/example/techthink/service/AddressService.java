package com.example.techthink.service;

import com.example.techthink.persistence.Address;
import com.example.techthink.service.DTO.AddressDTO;

import java.util.List;

public interface AddressService {
    Address create(AddressDTO addressDTO);
    Address readById(Integer id);
    List<Address> readAll();
    Address update(Integer id, AddressDTO addressDTO);
    Boolean delete(Integer id);
}
