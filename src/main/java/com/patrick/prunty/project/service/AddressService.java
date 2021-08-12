package com.patrick.prunty.project.service;

import com.patrick.prunty.project.database.JsonObjectQueryRunner;
import com.patrick.prunty.project.database.UpdateQueryRunner;
import com.patrick.prunty.project.exception.MissingParameterException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class AddressService {
    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    UpdateQueryRunner deleteQuery;
    UpdateQueryRunner createQuery;
    UpdateQueryRunner updateQuery;
    JsonObjectQueryRunner getQuery;

    public AddressService(@Value("${sql.create.address}") String createString,
                         @Value("${sql.update.address}") String updateString,
                          @Value("${sql.get.address}") String getString,
                         @Value("${sql.delete.address}") String deleteString){
        deleteQuery=new UpdateQueryRunner(deleteString);
        createQuery=new UpdateQueryRunner(createString);
        updateQuery=new UpdateQueryRunner(updateString);
        getQuery=new JsonObjectQueryRunner(getString);
    }

    public ResponseEntity<String> createAddress(String request){
        return ServiceUtils.requestForRowUpdate(namedParameterJdbcTemplate,createQuery,request,"Successfully created address","Failed to created address");
    }

    public ResponseEntity<String> deleteAddress(Integer id){
        JSONObject request = new JSONObject();
        request.put("Id", id);
        return ServiceUtils.requestForRowUpdate(namedParameterJdbcTemplate,deleteQuery, request.toString(), "Successfully deleted address", "Failed to delete address");
    }

    public ResponseEntity<String> updateAddress(String request){
        return ServiceUtils.requestForRowUpdate(namedParameterJdbcTemplate,updateQuery, request.toString(), "Successfully updated address", "Failed to update address");
    }
    public ResponseEntity<String> getAddress(int Id){
        JSONObject request = new JSONObject();
        request.put("Id", Id);
        return ServiceUtils.requestForResponse(namedParameterJdbcTemplate,getQuery, request.toString(),  "Failed to get address");
    }

    //getters and setters

    public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate() {
        return namedParameterJdbcTemplate;
    }

    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public UpdateQueryRunner getDeleteQuery() {
        return deleteQuery;
    }

    public void setDeleteQuery(UpdateQueryRunner deleteQuery) {
        this.deleteQuery = deleteQuery;
    }

    public UpdateQueryRunner getCreateQuery() {
        return createQuery;
    }

    public void setCreateQuery(UpdateQueryRunner createQuery) {
        this.createQuery = createQuery;
    }

    public UpdateQueryRunner getUpdateQuery() {
        return updateQuery;
    }

    public void setUpdateQuery(UpdateQueryRunner updateQuery) {
        this.updateQuery = updateQuery;
    }

    public JsonObjectQueryRunner getGetQuery() {
        return getQuery;
    }

    public void setGetQuery(JsonObjectQueryRunner getQuery) {
        this.getQuery = getQuery;
    }
}
