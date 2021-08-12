package com.patrick.prunty.project.database;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.json.*;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class JsonResultSetExtractor implements ResultSetExtractor<JSONArray> {
    @Override
    public JSONArray extractData(ResultSet resultSet) throws SQLException, DataAccessException {
        try {
            ResultSetMetaData metaData = resultSet.getMetaData();
            List<String> columns=new ArrayList<>();
            for (int i = 0; i <metaData.getColumnCount() ; i++) {
                columns.add(metaData.getColumnLabel(i+1));
            }
            JSONArray values=new JSONArray();
            while (resultSet.next()){
                JSONObject object=new JSONObject();
                for (int i = 0; i <columns.size() ; i++) {
                    object.put(columns.get(i),resultSet.getString(i+1));
                }
                values.put(object);
            }

            return values;
        } catch (Exception e) {
            throw  new SQLException(e.getMessage());
        }
    }
}
