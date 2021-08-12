package com.patrick.prunty.project.controller;

import com.patrick.prunty.project.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AddressRestController {
    @Autowired
    AddressService addressService;

    @PutMapping("/address/create" )
    public @ResponseBody
    ResponseEntity<String> createAddress(@RequestBody String requestBody){
        return addressService.createAddress(requestBody);
    }

    @PostMapping("/address/update" )
    public @ResponseBody
    ResponseEntity<String> updateAddress(@RequestBody String requestBody){
        return addressService.updateAddress(requestBody);
    }

    @GetMapping("/address/get/{Id}" )
    public @ResponseBody
    ResponseEntity<String> getAddress(@PathVariable("Id") Integer id){
        return addressService.getAddress(id);
    }

    @DeleteMapping("/address/delete/{Id}")
    public @ResponseBody
    ResponseEntity<String> deleteAddress(@PathVariable("Id") Integer id){
        return addressService.deleteAddress(id);
    }
}
