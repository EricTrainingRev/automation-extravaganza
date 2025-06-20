package com.revature;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

@Suite
@SuiteDisplayName("Converter Test Suite")
@SelectClasses({PositiveConverterTest.class, NegativeConverterTest.class})
public class TestSuite {

}
