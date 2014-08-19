/*
 * Copyright 2009-2014 Ardito Co.,Ltd. and the Others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and limitations under the License.
 */

package jp.ardito.seasar.struts.quickstart.action;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.seasar.framework.log.Logger;
import org.seasar.framework.unit.Seasar2;
import org.seasar.framework.unit.TestContext;
import org.seasar.framework.unit.annotation.RootDicon;

/**
 * @author Yohji Nihonyanagi
 */
@RunWith(Seasar2.class)
@RootDicon("app.dicon")
public class IndexActionTest {

	private static final Logger LOGGER = Logger.getLogger(IndexActionTest.class);

	/**
	 * @throws Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception { }

	/**
	 * @throws Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception { }

	@Resource
	private TestContext testContext;

	@Resource
	private IndexAction indexAction;

	/**
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {

		LOGGER.log("DTST0000", new Object[] {
			this.testContext.getTestClassShortName(),
			this.testContext.getTestMethodName(),
			this.testContext.getClass().getSimpleName()}
		);
	}

	/**
	 * @throws Exception
	 */
	@After
	public void tearDown() throws Exception { }

	/**
	 * Test of {@link IndexAction#index()}.
	 */
	@Test
	public final void test_index() {

		assertNotNull(this.indexAction);
		assertEquals("/index.jsp", this.indexAction.index());
	}

	/**
	 * Test of {@link IndexAction#hello()}.
	 */
	@Test
	public final void test_hello() {

		assertNotNull(this.indexAction);
		assertEquals("/hello.jsp", this.indexAction.hello());
	}

}
