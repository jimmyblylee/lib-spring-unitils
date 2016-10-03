/**
 * Project Name : spring-test-unitils <br>
 * File Name : MultiSchemaXlsDataSetFactory.java <br>
 * Package Name : com.lee.junit <br>
 * Create Time : 2016-10-02 <br>
 * Create by : jimmyblylee@126.com <br>
 * Copyright © 2006, 2016, Jimmybly Lee. All rights reserved.
 */
package com.lee.junit;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.DefaultDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.excel.XlsDataSet;
import org.unitils.core.UnitilsException;
import org.unitils.dbunit.datasetfactory.DataSetFactory;
import org.unitils.dbunit.util.FileHandler;
import org.unitils.dbunit.util.MultiSchemaDataSet;

/**
 * ClassName : MultiSchemaXlsDataSetFactory <br>
 * Description : multi schema XLS file DataSetFactory Implementation for unitils <br>
 * Notice: it will fetch the schema by the string between the last period and the penult period of the file name, if .
 * e.g. {@code defaultSchemaName} for "abc.txt" and "ghi" for "def.ghi.txt" <br>
 * Create Time : 2016-10-02 <br>
 * Create by : jimmyblylee@126.com
 */
public class MultiSchemaXlsDataSetFactory implements DataSetFactory {

    private String defaultSchemaName;

    /*
     * (non-Javadoc)
     * @see org.unitils.dbunit.datasetfactory.DataSetFactory#init(java.util.Properties, java.lang.String)
     */
    @Override
    public void init(Properties configuration, String defaultSchemaName) {
        this.defaultSchemaName = defaultSchemaName;
    }

    /*
     * (non-Javadoc)
     * @see org.unitils.dbunit.datasetfactory.DataSetFactory#createDataSet(java.io.File[])
     */
    @Override
    public MultiSchemaDataSet createDataSet(File... dataSetFiles) {
        MultiSchemaDataSet dataSet = new MultiSchemaDataSet();
        for (File file : dataSetFiles) {
            try {
                String schema = getSchema(file.getName());
                if (dataSet.getDataSetForSchema(schema) == null) {
                    dataSet.setDataSetForSchema(schema, new DefaultDataSet());
                }
                for (ITable table : new XlsDataSet(file).getTables()) {
                    ((DefaultDataSet) dataSet.getDataSetForSchema(schema)).addTable(table);
                }
            } catch (DataSetException | IOException e) {
                throw new UnitilsException("failed to create dataset", e);
            }
        }
        return dataSet;
    }

    /**
     * Description : the string between the last period and the penult period <br>
     * <b>Notice</b>: the file here is the file in temp folder, wich is named end with *.xml, but if it's a xls,<br>
     * it's still a XLS with temprory name {@link File#createTempFile(String, String)}
     * {@code createTempFile(filename + "-", ".xml")} see org.unitils.dbunit.util.FileHandler.createTempFile(String)<br>
     * Create Time: 2016-10-02 <br>
     * Create by : jimmyblylee@126.com <br>
     *
     * @param fileName the name of the file such as "abc.txt" "def.ghi.txt"
     * @return the schema string by logic of "the string between the last period and the penult period". e.g.
     *         {@code defaultSchemaName} for "abc.txt" and "ghi" for "def.ghi.txt"
     * @see FileHandler#createTempFile(String)
     */
    private String getSchema(String fileName) {
        String[] temp = fileName.split("\\.");
        String schema = (temp.length > 2) ? temp[temp.length - 2] : defaultSchemaName;
        schema = schema.contains("-") ? schema.substring(0, schema.indexOf("-")) : schema;
        return schema;
    }

    /*
     * (non-Javadoc)
     * @see org.unitils.dbunit.datasetfactory.DataSetFactory#getDataSetFileExtension()
     */
    @Override
    public String getDataSetFileExtension() {
        return "xls";
    }
}
