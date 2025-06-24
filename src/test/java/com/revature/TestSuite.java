package com.revature;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

import com.revature.converter.NegativeConverterTest;
import com.revature.converter.PositiveConverterTest;

@Suite
@SuiteDisplayName("Converter Test Suite")
@SelectClasses({PositiveConverterTest.class, NegativeConverterTest.class})
public class TestSuite {

}
