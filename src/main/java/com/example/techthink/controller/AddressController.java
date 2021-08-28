package com.example.techthink.controller;

import com.example.techthink.controller.model.AddressRequest;
import com.example.techthink.controller.model.AddressResponse;
import com.example.techthink.facade.AddressFacade;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AddressController {

    private final AddressFacade facade;

    public AddressController(AddressFacade facade) {
        this.facade = facade;
    }

    @PostMapping(value = "/admin/address/create")
    public ResponseEntity<AddressResponse> create(@RequestBody AddressRequest request){
        ResponseEntity<AddressResponse> body = ResponseEntity.status(HttpStatus.CREATED).body(facade.create(request));
        return body;
    }

    @GetMapping(value = "/all/address/{id}")
    public ResponseEntity<AddressResponse> readById(@PathVariable Integer id){
        ResponseEntity<AddressResponse> body = ResponseEntity.status(HttpStatus.CREATED).body(facade.readById(id));
        return body;
    }

    @GetMapping(value = "/all/address/getAll")
    public ResponseEntity<List<AddressResponse>> readAll(){
        ResponseEntity<List<AddressResponse>> body = ResponseEntity.status(HttpStatus.CREATED).body(facade.readAll());
        return body;
    }

    @PutMapping(value = "/admin/address/update/{id}")
    public ResponseEntity<AddressResponse> update(@RequestBody AddressRequest addressRequest, @PathVariable Integer id){
        ResponseEntity<AddressResponse> body = ResponseEntity.status(HttpStatus.CREATED).body(facade.update(id, addressRequest));
        return body;
    }

    @DeleteMapping(value = "/admin/address/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Integer id){
        ResponseEntity<Boolean> body = ResponseEntity.status(HttpStatus.CREATED).body(facade.delete(id));
        return body;
    }
}
