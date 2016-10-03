/**
 * Project Name : spring-test-unitils <br>
 * File Name : MySqlDbunitModule.java <br>
 * Package Name : com.lee.junit <br>
 * Create Time : 2016-10-02 <br>
 * Create by : jimmyblylee@126.com <br>
 * Copyright © 2006, 2016, Jimmybly Lee. All rights reserved.
 */
package com.lee.junit;

import org.dbunit.database.DatabaseConfig;
import org.dbunit.ext.mysql.MySqlDataTypeFactory;
import org.dbunit.ext.mysql.MySqlMetadataHandler;
import org.unitils.dbunit.DbUnitModule;
import org.unitils.dbunit.util.DbUnitDatabaseConnection;

/**
 * ClassName : MySqlDbunitModule <br>
 * Description : DbUnit Module of Unitils for MySQL <br>
 * {@link DbUnitModule} can not find tables by default for MySQL, this is a fix implementation for mysql module <br>
 * Create Time : 2016-10-02 <br>
 * Create by : jimmyblylee@126.com
 */
public class MySqlDbunitModule extends DbUnitModule {

    /*
     * (non-Javadoc)
     * @see org.unitils.dbunit.DbUnitModule#getDbUnitDatabaseConnection(java.lang.String)
     */
    @Override
    public DbUnitDatabaseConnection getDbUnitDatabaseConnection(final String schemaName) {
        DbUnitDatabaseConnection result = dbUnitDatabaseConnections.get(schemaName);
        if (null != result) { return result; }
        result = super.getDbUnitDatabaseConnection(schemaName);
        result.getConfig().setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new MySqlDataTypeFactory());
        result.getConfig().setProperty(DatabaseConfig.PROPERTY_METADATA_HANDLER, new MySqlMetadataHandler());
        return result;
    }
}
