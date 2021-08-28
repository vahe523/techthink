package com.example.techthink.controller;

import com.example.techthink.controller.model.SubCategoryRequest;
import com.example.techthink.controller.model.SubCategoryResponse;
import com.example.techthink.facade.SubCategoryFacade;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SubCategoryController {

    private final SubCategoryFacade facade;

    public SubCategoryController(SubCategoryFacade facade) {
        this.facade = facade;
    }


    @PostMapping(value = "/admin/createSubCategory")
    public ResponseEntity<SubCategoryResponse> create(@RequestBody SubCategoryRequest request){
        ResponseEntity<SubCategoryResponse> body = ResponseEntity.status(HttpStatus.CREATED).body(facade.create(request));
        return body;
    }

    @GetMapping(value = "/all/subCategory/{id}")
    public ResponseEntity<SubCategoryResponse> readById(@PathVariable Integer id){
        ResponseEntity<SubCategoryResponse> body = ResponseEntity.status(HttpStatus.CREATED).body(facade.readById(id));
        return body;
    }

    @GetMapping(value = "/all/subCategory/getAll")
    public ResponseEntity<List<SubCategoryResponse>> readAll(){
        ResponseEntity<List<SubCategoryResponse>> body = ResponseEntity.status(HttpStatus.CREATED).body(facade.readAll());
        return body;
    }

    @PutMapping(value = "/admin/update/subCategory/{id}")
    public ResponseEntity<SubCategoryResponse> update(@RequestBody SubCategoryRequest request, @PathVariable Integer id){
        ResponseEntity<SubCategoryResponse> body = ResponseEntity.status(HttpStatus.CREATED).body(facade.update(id, request));
        return body;
    }

    @DeleteMapping(value = "/admin/delete/subCategory/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Integer id){
        ResponseEntity<Boolean> body = ResponseEntity.status(HttpStatus.CREATED).body(facade.delete(id));
        return body;
    }
}
