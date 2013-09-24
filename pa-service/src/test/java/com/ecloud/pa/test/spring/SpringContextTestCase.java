package com.ecloud.pa.test.spring;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

@ContextConfiguration(locations = { "/applicationContext-test.xml" })
public abstract class SpringContextTestCase extends AbstractJUnit4SpringContextTests {
}
