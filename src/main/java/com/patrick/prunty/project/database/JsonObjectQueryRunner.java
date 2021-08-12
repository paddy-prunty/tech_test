package com.patrick.prunty.project.database;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.HashMap;
import java.util.Map;


public class JsonObjectQueryRunner implements QueryInterface<JSONObject>{


    String query;
    String[] parameters;
    public JsonObjectQueryRunner(String jsonString) {
        JSONObject object = new JSONObject(jsonString);
        query=object.getString(QueryInterface.QUERY_TAG);
        JSONArray jsonArray = object.getJSONArray(QueryInterface.PARAMETERS_TAG);
        parameters=new String[jsonArray.length()];
        for (int i = 0; i < jsonArray.length(); i++) {
            parameters[i]=jsonArray.getString(i);
        }
    }

    public JSONObject runQuery(JSONObject parametersObject,NamedParameterJdbcTemplate namedParameterJdbcTemplate) throws Exception{
        Map<String,Object> parametersMap=getParametersMap(parametersObject,parameters);
        JSONArray result;
        result = namedParameterJdbcTemplate.query(this.query, parametersMap, new JsonResultSetExtractor());
        return result.getJSONObject(0);
    }

}