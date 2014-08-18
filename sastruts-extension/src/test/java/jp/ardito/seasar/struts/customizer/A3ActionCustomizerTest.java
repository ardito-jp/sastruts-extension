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

package jp.ardito.seasar.struts.customizer;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.seasar.framework.log.Logger;
import org.seasar.framework.unit.Seasar2;
import org.seasar.framework.unit.TestContext;
import org.seasar.framework.unit.annotation.PostBindFields;
import org.seasar.framework.unit.annotation.PreUnbindFields;
import org.seasar.framework.unit.annotation.RootDicon;

/**
 * <p>{@link A3ActionCustomizer} のユニットテストクラスです。</p>
 *
 * @author Yohji Nihonyanagi
 */
@RunWith(Seasar2.class)
@RootDicon("app.dicon")
public class A3ActionCustomizerTest {

	private static Logger LOGGER = Logger.getLogger(A3ActionCustomizerTest.class);

	private TestContext testContext;

	private A3ActionCustomizer actionCustomizer;

	/**
	 * @throws Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

		LOGGER.debug("Setting up test cases of A3ActionCustomizer .....");
	}

	/**
	 * @throws Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {

		LOGGER.debug("Tearing down test cases of A3ActionCustomizer .....");
	}

	/**
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {

		LOGGER.debug("Setting up test cases of A3ActionCustomizer <" + this.testContext.getTestMethodName() + "> .....");
		this.actionCustomizer = new A3ActionCustomizer();
	}

	/**
	 * @throws Exception
	 */
	@After
	public void tearDown() throws Exception {

		this.actionCustomizer = null;
	}

	/**
	 * @throws Exception
	 */
	@PostBindFields
	public void postBindFields() throws Exception {

	}

	/**
	 * @throws Exception
	 */
	@PreUnbindFields
	public void preUnbindFields() throws Exception {

	}

	/**
	 * {@link A3ActionCustomizer#getActionReadOnly()}.
	 *
	 * @throws Exception
	 */
	@Test
	public void test_getActionReadOnly() throws Exception {

		assertEquals(Boolean.FALSE, new Boolean(this.actionCustomizer.getActionReadOnly()));

		this.actionCustomizer.actionReadOnly = true;
		assertEquals(Boolean.TRUE, new Boolean(this.actionCustomizer.getActionReadOnly()));

		this.actionCustomizer.actionReadOnly = false;
		assertEquals(Boolean.FALSE, new Boolean(this.actionCustomizer.getActionReadOnly()));
	}

	/**
	 * {@link A3ActionCustomizer#setActionReadOnly(boolean)}.
	 *
	 * @throws Exception
	 */
	@Test
	public void test_setActionReadOnly() throws Exception {

		assertEquals(Boolean.FALSE, new Boolean(this.actionCustomizer.actionReadOnly));

		this.actionCustomizer.setActionReadOnly(true);
		assertEquals(Boolean.TRUE, new Boolean(this.actionCustomizer.actionReadOnly));

		this.actionCustomizer.setActionReadOnly(false);
		assertEquals(Boolean.FALSE, new Boolean(this.actionCustomizer.actionReadOnly));
	}

	/**

	 * {@link A3ActionCustomizer#getGlobalProxyClassList()}.
	 *
	 * @throws Exception
	 */
	@Test
	public void test_getGlobalProxyClassList() throws Exception {

	}

	/**
	 * {@link A3ActionCustomizer#setGlobalProxyClassList(java.util.List)}.
	 *
	 * @throws Exception
	 */
	@Test
	public void test_setGlobalProxyClassList() throws Exception {

	}

	/**
	 * {@link A3ActionCustomizer#addGlobalProxyClass(Class)}.
	 *
	 * @throws Exception
	 */
	@Test
	public void test_addGlobalProxyClass() throws Exception {

	}

	/**
	 * {@link A3ActionCustomizer#createActionMapping(org.seasar.framework.container.ComponentDef)}.
	 *
	 * @throws Exception
	 */
	@Test
	public void test_createActionMapping() throws Exception {

	}

	/**
	 * {@link A3ActionCustomizer#setupProxy(org.seasar.struts.config.S2ActionMapping, Class, String)}.
	 *
	 * @throws Exception
	 */
	@Test
	public void test_setupProxy() throws Exception {

	}

	/**
	 * {@link org.seasar.struts.customizer.ActionCustomizer#customize(org.seasar.framework.container.ComponentDef)}.
	 *
	 * @throws Exception
	 */
	@Test
	public void test_customize() throws Exception {

		// this.actionCustomizer.customize(this.testContext.getComponentDef("appleAction"));
	}

}
