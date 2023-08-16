package cn.bugstack.gateway.datasource;

import cn.bugstack.gateway.session.Configuration;


public interface DataSourceFactory {

    void setProperties(Configuration configuration, String uri);

    DataSource getDataSource();

}
