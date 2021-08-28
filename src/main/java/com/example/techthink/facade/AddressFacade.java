package com.example.techthink.facade;

import com.example.techthink.annotation.Facade;
import com.example.techthink.controller.model.AddressRequest;
import com.example.techthink.controller.model.AddressResponse;
import com.example.techthink.converter.Converter;
import com.example.techthink.persistence.Address;
import com.example.techthink.service.AddressService;
import com.example.techthink.service.DTO.AddressDTO;

import java.util.List;

@Facade
public class AddressFacade {
    private final AddressService addressService;
    private final Converter converter;

    public AddressFacade(AddressService addressService, Converter converter) {
        this.addressService = addressService;
        this.converter = converter;
    }

    public AddressResponse create(AddressRequest request){
        AddressDTO addressDTO = convertToDTO(request);
        Address address = addressService.create(addressDTO);
        AddressResponse addressResponse = converter.fromAddressToResponse(address);
        return addressResponse;
    }

    public AddressResponse readById(Integer id){
        Address address = addressService.readById(id);
        AddressResponse addressResponse = converter.fromAddressToResponse(address);
        return addressResponse;
    }

    public List<AddressResponse> readAll(){
        List<Address> addresses = addressService.readAll();
        List<AddressResponse> addressResponses = converter.fromAddressToResponseList(addresses);
        return addressResponses;
    }

    public AddressResponse update(Integer id, AddressRequest request){
        AddressDTO addressDTO = convertToDTO(request);
        Address updated = addressService.update(id, addressDTO);
        AddressResponse addressResponse = converter.fromAddressToResponse(updated);
        return addressResponse;
    }

    public boolean delete(Integer id){
        return addressService.delete(id);
    }

    private AddressDTO convertToDTO(AddressRequest request){
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setCountry(request.getCountry());
        addressDTO.setCity(request.getCity());
        addressDTO.setStreet(request.getStreet());
        return addressDTO;

    }
}
