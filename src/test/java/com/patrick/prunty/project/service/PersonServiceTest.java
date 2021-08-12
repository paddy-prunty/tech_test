package com.patrick.prunty.project.service;

import com.patrick.prunty.project.database.JsonArrayQueryRunner;
import com.patrick.prunty.project.database.JsonObjectQueryRunner;
import com.patrick.prunty.project.database.UpdateQueryRunner;
import org.aspectj.lang.annotation.Before;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

class PersonServiceTest {
    public static String dummyQueryString="{\"query\"=\" \",\"params\"=[\"\"]}";
    PersonService personService;

    @BeforeEach
    void setup(){
        personService=new PersonService(dummyQueryString,dummyQueryString,dummyQueryString,dummyQueryString,dummyQueryString,dummyQueryString);
        personService.setCreateQuery(Mockito.mock(UpdateQueryRunner.class));
        personService.setDeleteQuery(Mockito.mock(UpdateQueryRunner.class));
        personService.setUpdateQuery(Mockito.mock(UpdateQueryRunner.class));
        personService.setGetCountQuery(Mockito.mock(JsonObjectQueryRunner.class));
        personService.setGetListQuery(Mockito.mock(JsonArrayQueryRunner.class));
    }

    public ResultSet getMockResultset(String[] values) throws Exception{
        ResultSet mock = Mockito.mock(ResultSet.class);
        Mockito.when(mock.next()).thenReturn(true).thenReturn(false);
        ResultSetMetaData resultSetMetaData=Mockito.mock(ResultSetMetaData.class);
        for (int i = 0; i <values.length; i++) {
            String[] split = values[i].split(":");
            Mockito.when(mock.getObject(split[0])).thenReturn(split[1]);
            Mockito.when(resultSetMetaData.getColumnLabel(i)).thenReturn(split[0]);
        }
        Mockito.when(mock.getMetaData()).thenReturn(resultSetMetaData);
        return mock;
    }

    @Test
    void createPersonSuccess() throws Exception{
        UpdateQueryRunner createQuery =personService.getCreateQuery();
        Mockito.when(createQuery.runQuery(any(),any())).thenReturn(1);
        ResponseEntity<String> person = personService.createPerson("{}");
        Assertions.assertEquals(HttpStatus.OK,person.getStatusCode());
        Assertions.assertEquals("{\"success\":\"successfully created person\"}",person.getBody());
    }

    @Test
    void createPersonFailure() throws Exception{
        UpdateQueryRunner createQuery =personService.getCreateQuery();
        Mockito.when(createQuery.runQuery(any(),any())).thenReturn(0);
        ResponseEntity<String> person = personService.createPerson("{}");
        Assertions.assertEquals(HttpStatus.BAD_REQUEST,person.getStatusCode());
        Assertions.assertEquals("{\"error\":\"no rows found which apply\"}",person.getBody());
    }

    @Test
    void updatePersonSuccess() throws Exception{
        UpdateQueryRunner updateQuery =personService.getUpdateQuery();
        Mockito.when(updateQuery.runQuery(any(),any())).thenReturn(1);
        ResponseEntity<String> person = personService.updatePerson("{}");
        Assertions.assertEquals(HttpStatus.OK,person.getStatusCode());
        Assertions.assertEquals("{\"success\":\"successfully created person\"}",person.getBody());
    }

    @Test
    void updatePersonFailure() throws Exception{
        UpdateQueryRunner updateQuery =personService.getUpdateQuery();
        Mockito.when(updateQuery.runQuery(any(),any())).thenReturn(0);
        ResponseEntity<String> person = personService.updatePerson("{}");
        Assertions.assertEquals(HttpStatus.BAD_REQUEST,person.getStatusCode());
        Assertions.assertEquals("{\"error\":\"no rows found which apply\"}",person.getBody());
    }

    @Test
    void getPersonListSuccess() throws Exception{
        JsonArrayQueryRunner getListQuery = personService.getGetListQuery();
        Mockito.when(getListQuery.runQuery(any(),any())).thenReturn(new JSONArray("[{\"first_name\":\"a\",\"last_name\":\"b\"}]"));
        ResponseEntity<String> person = personService.getPersonList();
        Assertions.assertEquals(HttpStatus.OK,person.getStatusCode());
    }

    @Test
    void getPersonListFailure() throws Exception{
        JsonArrayQueryRunner getListQuery = personService.getGetListQuery();
        Mockito.when(getListQuery.runQuery(any(),any())).thenThrow(new NullPointerException());
        ResponseEntity<String> person = personService.getPersonList();
        Assertions.assertEquals(HttpStatus.BAD_REQUEST,person.getStatusCode());
        Assertions.assertEquals("{\"error\":\"failed to get persons list\"}",person.getBody());
    }

    @Test
    void getPersonCountSuccess() throws Exception{
        JsonObjectQueryRunner getCountQuery = personService.getGetCountQuery();
        Mockito.when(getCountQuery.runQuery(any(),any())).thenReturn(new JSONObject());
        ResponseEntity<String> person = personService.getPersonCount();
        Assertions.assertEquals(HttpStatus.OK,person.getStatusCode());
    }

    @Test
    void getPersonCountFailure() throws Exception{
        JsonObjectQueryRunner getCountQuery = personService.getGetCountQuery();
        Mockito.when(getCountQuery.runQuery(any(),any())).thenThrow(new NullPointerException());
        ResponseEntity<String> person = personService.getPersonCount();
        Assertions.assertEquals(HttpStatus.BAD_REQUEST,person.getStatusCode());
        Assertions.assertEquals("{\"error\":\"failed to get count from request\"}",person.getBody());
    }

    @Test
    void deletePersonSuccess() throws Exception{
        UpdateQueryRunner deleteQuery =personService.getDeleteQuery();
        Mockito.when(deleteQuery.runQuery(any(),any())).thenReturn(1);
        ResponseEntity<String> person = personService.deletePerson(1);
        Assertions.assertEquals(HttpStatus.OK,person.getStatusCode());
        Assertions.assertEquals("{\"success\":\"person successfully deleted\"}",person.getBody());
    }

    @Test
    void deletePersonFailure() throws Exception{
        UpdateQueryRunner deleteQuery =personService.getDeleteQuery();
        Mockito.when(deleteQuery.runQuery(any(),any())).thenReturn(0);
        ResponseEntity<String> person = personService.deletePerson(1);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST,person.getStatusCode());
        Assertions.assertEquals("{\"error\":\"no rows found which apply\"}",person.getBody());
    }
}