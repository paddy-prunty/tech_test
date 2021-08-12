package com.patrick.prunty.project.database;

import com.patrick.prunty.project.exception.MissingParameterException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.HashMap;
import java.util.Map;

public interface QueryInterface<T> {
     static String QUERY_TAG="query";
     static String PARAMETERS_TAG="params";
     static String JSON_NAME_TAG="jsonName";

    T runQuery(JSONObject parametersObject, NamedParameterJdbcTemplate namedParameterJdbcTemplate) throws Exception;

    //method for retrieving query parameters from input json object
    default Map<String,Object> getParametersMap(JSONObject parametersObject,String[] parameterNames) throws MissingParameterException{
        Map<String,Object> result=new HashMap<>();
        for (int i = 0; i < parameterNames.length; i++) {
            if(parametersObject.has(parameterNames[i])){
                result.put(parameterNames[i],parametersObject.get(parameterNames[i]));
            }
            else {
                throw new MissingParameterException("could not find parameter "+parameterNames[i]);
            }

        }
        return result;
    }

}
