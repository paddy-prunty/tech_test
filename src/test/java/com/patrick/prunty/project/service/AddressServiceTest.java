package com.patrick.prunty.project.service;

import com.patrick.prunty.project.database.JsonArrayQueryRunner;
import com.patrick.prunty.project.database.JsonObjectQueryRunner;
import com.patrick.prunty.project.database.UpdateQueryRunner;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

class AddressServiceTest {

    public static String dummyQueryString="{\"query\"=\" \",\"params\"=[\"\"]}";
    AddressService addressService;

    @BeforeEach
    void setup(){
        addressService=new AddressService(dummyQueryString,dummyQueryString,dummyQueryString,dummyQueryString);
        addressService.setCreateQuery(Mockito.mock(UpdateQueryRunner.class));
        addressService.setDeleteQuery(Mockito.mock(UpdateQueryRunner.class));
        addressService.setUpdateQuery(Mockito.mock(UpdateQueryRunner.class));
        addressService.setGetQuery(Mockito.mock(JsonObjectQueryRunner.class));
    }

    @Test
    void createAddressSuccess() throws Exception{
        UpdateQueryRunner createQuery =addressService.getCreateQuery();
        Mockito.when(createQuery.runQuery(any(),any())).thenReturn(1);
        ResponseEntity<String> address = addressService.updateAddress("{}");
        Assertions.assertEquals(HttpStatus.BAD_REQUEST,address.getStatusCode());
        Assertions.assertEquals("{\"error\":\"no rows found which apply\"}",address.getBody());
    }

    @Test
    void createAddressFailure() throws Exception{
        UpdateQueryRunner createQuery =addressService.getCreateQuery();
        Mockito.when(createQuery.runQuery(any(),any())).thenReturn(0);
        ResponseEntity<String> address = addressService.createAddress("{}");
        Assertions.assertEquals(HttpStatus.BAD_REQUEST,address.getStatusCode());
        Assertions.assertEquals("{\"error\":\"no rows found which apply\"}",address.getBody());
    }

    @Test
    void deleteAddressSuccess() throws Exception{
        UpdateQueryRunner deleteQuery =addressService.getDeleteQuery();
        Mockito.when(deleteQuery.runQuery(any(),any())).thenReturn(1);
        ResponseEntity<String> address = addressService.deleteAddress(1);
        Assertions.assertEquals(HttpStatus.OK,address.getStatusCode());
        Assertions.assertEquals("{\"success\":\"Successfully deleted address\"}",address.getBody());
    }

    @Test
    void deleteAddressFailure() throws Exception{
        UpdateQueryRunner deleteQuery =addressService.getDeleteQuery();
        Mockito.when(deleteQuery.runQuery(any(),any())).thenReturn(0);
        ResponseEntity<String> address = addressService.deleteAddress(1);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST,address.getStatusCode());
        Assertions.assertEquals("{\"error\":\"no rows found which apply\"}",address.getBody());
    }

    @Test
    void updateAddressSuccess() throws Exception{
        UpdateQueryRunner updateQuery =addressService.getUpdateQuery();
        Mockito.when(updateQuery.runQuery(any(),any())).thenReturn(1);
        ResponseEntity<String> address = addressService.updateAddress("{}");
        Assertions.assertEquals(HttpStatus.OK,address.getStatusCode());
        Assertions.assertEquals("{\"success\":\"Successfully updated address\"}",address.getBody());
    }

    @Test
    void updateAddressFailure() throws Exception{
        UpdateQueryRunner updateQuery =addressService.getUpdateQuery();
        Mockito.when(updateQuery.runQuery(any(),any())).thenReturn(0);
        ResponseEntity<String> address = addressService.updateAddress("{}");
        Assertions.assertEquals(HttpStatus.BAD_REQUEST,address.getStatusCode());
        Assertions.assertEquals("{\"error\":\"no rows found which apply\"}",address.getBody());
    }

    @Test
    void getAddressSuccess() throws Exception{
        JsonObjectQueryRunner getQuery =addressService.getGetQuery();
        Mockito.when(getQuery.runQuery(any(),any())).thenReturn(new JSONObject());
        ResponseEntity<String> address = addressService.getAddress(0);
        Assertions.assertEquals(HttpStatus.OK,address.getStatusCode());
    }

    @Test
    void getAddressFailure() throws Exception{
        JsonObjectQueryRunner getQuery =addressService.getGetQuery();
        Mockito.when(getQuery.runQuery(any(),any())).thenThrow(new NullPointerException());
        ResponseEntity<String> address = addressService.getAddress(0);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST,address.getStatusCode());
        Assertions.assertEquals("{\"error\":\"Failed to get address\"}",address.getBody());
    }
}