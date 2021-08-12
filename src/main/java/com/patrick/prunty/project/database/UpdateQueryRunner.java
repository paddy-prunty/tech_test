package com.patrick.prunty.project.database;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.Map;

public class UpdateQueryRunner implements QueryInterface<Integer>{

    String query;
    String[] parameters;
    public UpdateQueryRunner(String jsonString) {
        JSONObject object = new JSONObject(jsonString);
        query=object.getString(QueryInterface.QUERY_TAG);
        JSONArray jsonArray = object.getJSONArray(QueryInterface.PARAMETERS_TAG);
        parameters=new String[jsonArray.length()];
        for (int i = 0; i < jsonArray.length(); i++) {
            parameters[i]=jsonArray.getString(i);
        }
    }
    @Override
    public Integer runQuery(JSONObject parametersObject, NamedParameterJdbcTemplate namedParameterJdbcTemplate) throws Exception {
        Map<String,Object> parametersMap=getParametersMap(parametersObject,parameters);
        Integer result = namedParameterJdbcTemplate.update(this.query, parametersMap);
        return result;
    }
}
