package de.marco1223.cherry.interfaces;

import java.sql.DatabaseMetaData;
import java.util.List;

public interface IDatabaseHandler {
    void connect(String url, String username, String password);

    void disconnect();

    void query(String sql);

    List<String> fetchData(String sql);

    DatabaseMetaData getPostgresMetadata();

}
