package com.example.techthink.service.impl;

import com.example.techthink.persistence.Address;
import com.example.techthink.persistence.repository.AddressRepository;
import com.example.techthink.service.AddressService;
import com.example.techthink.service.DTO.AddressDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public Address create(AddressDTO requestAddress){
        Address address = buildAddress(requestAddress);
        Address savedAddress = addressRepository.save(address);
        return savedAddress;
    }

    public Address readById(Integer id){
        Address byId = addressRepository.getById(id);
        return byId;
    }

    public List<Address> readAll(){
        List<Address> all = addressRepository.findAll();
        return all;
    }

    public Address update(Integer id, AddressDTO request){
        Address byId = addressRepository.getById(id);
        Address address = updateGivenAddress(byId, request);
        Address updatedAddress = addressRepository.save(address);
        return updatedAddress;
    }

    public Boolean delete(Integer id){
        addressRepository.deleteById(id);
        return !addressRepository.existsById(id);
    }

    private Address buildAddress(AddressDTO request){
        Address address = new Address();
        address.setCountry(request.getCountry());
        address.setCity(request.getCity());
        address.setStreet(request.getStreet());
        return address;
    }

    private Address updateGivenAddress(Address address, AddressDTO request){
        address.setCountry(request.getCountry());
        address.setCity(request.getCity());
        address.setStreet(request.getStreet());
        return address;
    }
}
