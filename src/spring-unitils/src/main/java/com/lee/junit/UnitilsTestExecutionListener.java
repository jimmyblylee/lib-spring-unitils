/**
 * Project Name : spring-test-unitils <br>
 * File Name : UnitilsTestExecutionListener.java <br>
 * Package Name : com.lee.junit <br>
 * Create Time : 2016-10-02 <br>
 * Create by : jimmyblylee@126.com <br>
 * Copyright © 2006, 2016, Jimmybly Lee. All rights reserved.
 */
package com.lee.junit;

import java.lang.reflect.Method;

import org.springframework.core.Ordered;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.TestContextManager;
import org.springframework.test.context.support.AbstractTestExecutionListener;
import org.unitils.core.TestListener;
import org.unitils.core.Unitils;

/**
 * ClassName : UnitilsTestExecutionListener <br>
 * Description : merge the test event listener order from Unitils into Spring-Test <br>
 * Order:
 * <ol>
 * <li style="padding-bottom: 10px"><b>[Spring-Test]</b> {@link TestContextManager#beforeTestClass() before test class
 * execution}<br>
 * <b>[Unitils]</b> {@link TestListener#beforeTestClass(Class) before test class execution} <br>
 * prior to any <em>before class callbacks</em> of a particular testing framework (e.g., JUnit 4's
 * {@link org.junit.BeforeClass @BeforeClass})</li>
 * <li style="padding-bottom: 10px"><b>[Spring-Test]</b> {@link #prepareTestInstance(Object) test instance
 * preparation}:<br>
 * <b>[Unitils]</b> {@link TestListener#afterCreateTestObject(Object) after create test object} <br>
 * immediately following instantiation of the test instance</li>
 * <li style="padding-bottom: 10px"><b>[Spring-Test]</b> {@link #beforeTestMethod(Object, Method) before test method
 * execution}<br>
 * <b>[Unitils]</b> {@link TestListener#beforeTestSetUp(Object, Method) before test setup}<br>
 * prior to any <em>before method callbacks</em> of a particular testing framework (e.g., JUnit 4's
 * {@link org.junit.Before @Before})</li>
 * <li style="padding-bottom: 10px"><b>[Spring-Test]</b> {@link #beforeTestMethod(Object, Method) before test method
 * execution}<br>
 * <b>[Unitils]</b> {@link TestListener#beforeTestMethod(Object, Method) before test method}<br>
 * prior to any <em>before method callbacks</em> of a particular testing framework (e.g., JUnit 4's
 * {@link org.junit.Before @Before})</li>
 * <li style="padding-bottom: 10px"><b>[Spring-Test]</b> {@link #afterTestMethod(Object, Method, Throwable) after test
 * method execution}<br>
 * <b>[Unitils]</b> {@link TestListener#afterTestMethod(Object, Method, Throwable) after test method}<br>
 * after any <em>after method callbacks</em> of a particular testing framework (e.g., JUnit 4's
 * {@link org.junit.After @After})</li>
 * <li style="padding-bottom: 10px"><b>[Spring-Test]</b> {@link #afterTestMethod(Object, Method, Throwable) after test
 * method execution}<br>
 * <b>[Unitils]</b> {@link TestListener#afterTestTearDown(Object, Method) after test teardown}<br>
 * after any <em>after method callbacks</em> of a particular testing framework (e.g., JUnit 4's
 * {@link org.junit.After @After})</li>
 * <li style="padding-bottom: 10px"><b>[Spring-Test]</b> {@link #afterTestClass() after test class execution}<br>
 * <b>[Unitils]</b> <b>none</b><br>
 * after any <em>after class callbacks</em> of a particular testing framework (e.g., JUnit 4's
 * {@link org.junit.AfterClass @AfterClass})</li>
 * </ol>
 * Create Time : 2016-10-02 <br>
 * Create by : jimmyblylee@126.com
 * 
 * @see TestContextManager
 * @see TestListener
 */
public class UnitilsTestExecutionListener extends AbstractTestExecutionListener {

    public void prepareTestInstance(TestContext testContext) throws Exception {
        getTestListener().afterCreateTestObject(testContext.getTestInstance());
    }

    public void beforeTestMethod(TestContext testContext) throws Exception {
        getTestListener().beforeTestSetUp(testContext.getTestInstance(), testContext.getTestMethod());
        getTestListener().beforeTestMethod(testContext.getTestInstance(), testContext.getTestMethod());
    }

    public void afterTestMethod(TestContext testContext) throws Exception {
        getTestListener().afterTestMethod(testContext.getTestInstance(), testContext.getTestMethod(),
                testContext.getTestException());
        getTestListener().afterTestTearDown(testContext.getTestInstance(), testContext.getTestMethod());
    }

    /**
     * @return The Unitils test listener
     */
    protected TestListener getTestListener() {
        TestListener listener = getUnitils().getTestListener();
        return listener;
    }

    /**
     * Returns the default singleton instance of Unitils
     *
     * @return the Unitils instance, not null
     */
    protected Unitils getUnitils() {
        return Unitils.getInstance();
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }

    @Override
    public void beforeTestClass(TestContext testContext) throws Exception {
        getTestListener().beforeTestClass(testContext.getTestClass());
    }
}
