package cn.bugstack.gateway.datasource;

import cn.bugstack.gateway.mapping.HttpStatement;
import cn.bugstack.gateway.session.Configuration;

import java.util.Properties;


public interface DataSourceFactory {

    void setProperties(Configuration configuration, String uri);

    DataSource getDataSource();

}
