package com.patrick.prunty.project.service;

import com.patrick.prunty.project.database.QueryInterface;
import com.patrick.prunty.project.database.UpdateQueryRunner;
import com.patrick.prunty.project.exception.MissingParameterException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

public class ServiceUtils {
    public static ResponseEntity<String> requestForRowUpdate(NamedParameterJdbcTemplate namedParameterJdbcTemplate, UpdateQueryRunner updateQueryRunner, String request, String successMessage, String failureMessage) {
        try {
            JSONObject RequestJson=new JSONObject(request);
            int count = updateQueryRunner.runQuery(RequestJson, namedParameterJdbcTemplate);
            if (count > 0) {
                JSONObject resultMessage = new JSONObject().put("success", successMessage);
                return new ResponseEntity<>(resultMessage.toString(), HttpStatus.OK);
            } else {
                JSONObject resultMessage = new JSONObject().put("error", "no rows found which apply");
                return new ResponseEntity<>(resultMessage.toString(), HttpStatus.BAD_REQUEST);
            }
        } catch (MissingParameterException e) {
            JSONObject errorObject = new JSONObject();
            errorObject.put("error", e.getMessage());
            return new ResponseEntity<>(errorObject.toString(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            JSONObject errorObject = new JSONObject();
            errorObject.put("error", failureMessage);
            e.printStackTrace();
            return new ResponseEntity<>(errorObject.toString(), HttpStatus.BAD_REQUEST);
        }
    }

    public static ResponseEntity<String> requestForResponse(NamedParameterJdbcTemplate namedParameterJdbcTemplate,QueryInterface jsonArrayQueryRunner, String request, String failureMessage) {
        try {
            JSONObject RequestJson=new JSONObject(request);
            Object object = jsonArrayQueryRunner.runQuery(RequestJson, namedParameterJdbcTemplate);
            return new ResponseEntity<>(object.toString(), HttpStatus.OK);
        } catch (MissingParameterException e) {
            JSONObject errorObject = new JSONObject();
            errorObject.put("error", e.getMessage());
            return new ResponseEntity<>(errorObject.toString(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            JSONObject errorObject = new JSONObject();
            errorObject.put("error", failureMessage);
            e.printStackTrace();
            return new ResponseEntity<>(errorObject.toString(), HttpStatus.BAD_REQUEST);
        }
    }
}
