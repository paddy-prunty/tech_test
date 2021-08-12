package com.patrick.prunty.project.service;

import com.patrick.prunty.project.database.JsonArrayQueryRunner;
import com.patrick.prunty.project.database.JsonObjectQueryRunner;
import com.patrick.prunty.project.database.UpdateQueryRunner;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class PersonService {
    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private JsonArrayQueryRunner getListQuery;
    private JsonObjectQueryRunner getCountQuery;
    private UpdateQueryRunner deleteQuery;
    private UpdateQueryRunner deleteAddressQuery;
    private UpdateQueryRunner createQuery;
    private UpdateQueryRunner updateQuery;

    public PersonService(@Value("${sql.create.person}") String createString,
                         @Value("${sql.update.person}") String updateString,
                         @Value("${sql.list.person}") String listString,
                         @Value("${sql.delete.person}") String deleteString,
                         @Value( "${sql.delete.person.address}") String deleteAddressForPerson,
                         @Value("${sql.count.person}") String countString) {
        getCountQuery = new JsonObjectQueryRunner(countString);
        getListQuery = new JsonArrayQueryRunner(listString);
        deleteQuery = new UpdateQueryRunner(deleteString);
        deleteAddressQuery = new UpdateQueryRunner(deleteAddressForPerson);
        createQuery = new UpdateQueryRunner(createString);
        updateQuery = new UpdateQueryRunner(updateString);
    }

    public ResponseEntity<String> createPerson(String request) {
        return ServiceUtils.requestForRowUpdate(namedParameterJdbcTemplate,createQuery,request,"successfully created person","failed to created person");
    }

    public ResponseEntity<String> updatePerson(String request) {
        return ServiceUtils.requestForRowUpdate(namedParameterJdbcTemplate,updateQuery,request,"successfully created person","failed to created person");
    }

    public ResponseEntity<String> getPersonList() {
       return ServiceUtils.requestForResponse(namedParameterJdbcTemplate,getListQuery,"{}","failed to get persons list");
    }

    public ResponseEntity<String> getPersonCount() {
        return ServiceUtils.requestForResponse(namedParameterJdbcTemplate,getCountQuery,"{}", "failed to get count from request");
    }

    public ResponseEntity<String> deletePerson(Integer id) {
        JSONObject request = new JSONObject();
        request.put("personId", id);
        ServiceUtils.requestForRowUpdate(namedParameterJdbcTemplate,deleteAddressQuery, request.toString(), "addresses successfully deleted", "failed to find any addresses");
        return ServiceUtils.requestForRowUpdate(namedParameterJdbcTemplate,deleteQuery, request.toString(), "person successfully deleted", "failed to delete person");
    }



    public JsonArrayQueryRunner getGetListQuery() {
        return getListQuery;
    }

    public void setGetListQuery(JsonArrayQueryRunner getListQuery) {
        this.getListQuery = getListQuery;
    }

    public JsonObjectQueryRunner getGetCountQuery() {
        return getCountQuery;
    }

    public void setGetCountQuery(JsonObjectQueryRunner getCountQuery) {
        this.getCountQuery = getCountQuery;
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

    public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate() {
        return namedParameterJdbcTemplate;
    }

    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }
}
