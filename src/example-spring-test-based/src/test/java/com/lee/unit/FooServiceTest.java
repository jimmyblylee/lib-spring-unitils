package com.lee.unit;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.unitils.dbunit.annotation.DataSet;
import org.unitils.dbunit.annotation.ExpectedDataSet;

import com.lee.junit.UnitilsTestExecutionListener;
import com.lee.junit.test.service.FooService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-context.xml" })
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, UnitilsTestExecutionListener.class })
public class FooServiceTest {

    @Resource
    private FooService service;

    @Test
    @DataSet({ "apple_prepared.xls", "pen_prepared.pen.xls" })
    @ExpectedDataSet({ "apple_expected.xls", "pen_expected.pen.xls" })
    public void testUpdate() {
        service.update();
    }
}
