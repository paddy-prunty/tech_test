package com.patrick.prunty.project.controller;

import com.patrick.prunty.project.service.PersonService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PersonRestController {

    @Autowired
    private PersonService personService;

    @PostMapping("/person/update" )
    public @ResponseBody
    ResponseEntity<String> updatePerson(@RequestBody String requestBody){
        return personService.updatePerson(requestBody);
    }
    @GetMapping("/person/count" )
    public @ResponseBody
    ResponseEntity<String> getPersonCount(){
        return personService.getPersonCount();
    }

    @GetMapping("/person/getlist" )
    public @ResponseBody
    ResponseEntity<String> getPeople(){
        return personService.getPersonList();
    }

    @PutMapping("/person/create" )
    public @ResponseBody
    ResponseEntity<String> createPerson(@RequestBody String requestBody){
        return personService.createPerson(requestBody);
    }



    @DeleteMapping("/person/delete/{id}" )
    public @ResponseBody
    ResponseEntity<String> deletePerson(@PathVariable("id") Integer id){
        return personService.deletePerson(id);
    }

}
