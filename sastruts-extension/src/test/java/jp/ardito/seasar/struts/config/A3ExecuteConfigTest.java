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

package jp.ardito.seasar.struts.config;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import jp.ardito.seasar.struts.proxy.ProxyType;
import jp.ardito.seasar.struts.test.action.IndexAction;
import jp.ardito.seasar.struts.test.proxy.Application1stProxy;
import jp.ardito.seasar.struts.test.proxy.Application2ndProxy;
import jp.ardito.seasar.struts.test.proxy.Application3rdProxy;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.seasar.framework.unit.Seasar2;
import org.seasar.struts.annotation.Execute;
import org.seasar.struts.config.S2ExecuteConfig;
import org.seasar.struts.config.S2ValidationConfig;
import org.seasar.struts.enums.SaveType;
import org.seasar.struts.exception.IllegalUrlPatternRuntimeException;

/**
 * <p>Test class for {@link A3ExecuteConfig}.</p>
 *
 * @author Yohji Nihonyanagi
 */
@RunWith(Seasar2.class)
public class A3ExecuteConfigTest {

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

	private Method index;

	private Method hello;

	private S2ExecuteConfig original;

	private A3ExecuteConfig config;

	/**
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		this.index = IndexAction.class.getMethod("index");
		this.hello = IndexAction.class.getMethod("hello");
		this.original = new S2ExecuteConfig();
		this.original.setMethod(this.index);
		this.config = new A3ExecuteConfig(this.original);
	}

	/**
	 * @throws Exception
	 */
	@After
	public void tearDown() throws Exception {
		this.index = null;
		this.hello = null;
		this.config = null;
		this.original = null;
	}

	/**
	 * Test method for {@link A3ExecuteConfig#A3ExecuteConfig(S2ExecuteConfig)}.
	 * @throws Exception
	 */
	@Test
	public final void test_A3ExecuteConfig_Constructor() throws Exception {

		S2ExecuteConfig s2ExecuteConfig = new S2ExecuteConfig();
		Method method = IndexAction.class.getMethod("index");
		s2ExecuteConfig.setMethod(method);
		A3ExecuteConfig a3ExecuteConfig = new A3ExecuteConfig(s2ExecuteConfig);

		assertSame(method, a3ExecuteConfig.getMethod());
		assertSame(method.getAnnotation(Execute.class), a3ExecuteConfig.getMethod().getAnnotation(Execute.class));

		Field field = A3ExecuteConfig.class.getDeclaredField("executeConfig");
		S2ExecuteConfig s2ExecuteConfigInternal = (S2ExecuteConfig) field.get(a3ExecuteConfig);
		assertNotNull(s2ExecuteConfigInternal);
		assertSame(s2ExecuteConfig, s2ExecuteConfigInternal);
	}

	/**
	 * Test method for {@link A3ExecuteConfig#getMethod()}.<br />
	 * Test method for {@link A3ExecuteConfig#getMethod()}.
	 */
	@Test
	public final void test_getMethod_setMethod() {

		assertSame(this.index, this.original.getMethod());
		assertSame(this.original.getMethod(), this.config.getMethod());

		this.config.setMethod(this.hello);
		assertSame(this.hello, this.original.getMethod());
		assertSame(this.original.getMethod(), this.config.getMethod());

		try {
			this.config.setMethod(null);
			fail(); // S2ExecuteConfig#setMethod(null) => NullPointerException!!
		} catch (Exception e) {
			assertEquals(NullPointerException.class, e.getClass());
		}
	}

	/**
	 * Test method for {@link A3ExecuteConfig#getValidationConfigs()}.<br />
	 * Test method for {@link A3ExecuteConfig#setValidationConfigs(List)}.
	 * @throws Exception
	 */
	@Test
	public final void test_getValidationConfigs_setValidationConfigs_isValidator() throws Exception {

		assertNull(this.config.getValidationConfigs());

		List<S2ValidationConfig> validationConfigs = new ArrayList<S2ValidationConfig>();
		validationConfigs.add(new S2ValidationConfig(IndexAction.class.getMethod("validate1st")));
		validationConfigs.add(new S2ValidationConfig(IndexAction.class.getMethod("validate2nd")));
		validationConfigs.add(new S2ValidationConfig(IndexAction.class.getMethod("validate3rd")));
		validationConfigs.add(new S2ValidationConfig(IndexAction.class.getMethod("validate4th")));
		this.config.setValidationConfigs(validationConfigs);
		assertSame(validationConfigs, this.config.getValidationConfigs());
		assertSame(this.original.getValidationConfigs(), this.config.getValidationConfigs());

		assertEquals(4, this.config.getValidationConfigs().size());
		assertEquals(IndexAction.class.getMethod("validate1st"), this.config.getValidationConfigs().get(0).getValidateMethod());
		assertEquals(IndexAction.class.getMethod("validate2nd"), this.config.getValidationConfigs().get(1).getValidateMethod());
		assertEquals(IndexAction.class.getMethod("validate3rd"), this.config.getValidationConfigs().get(2).getValidateMethod());
		assertEquals(IndexAction.class.getMethod("validate4th"), this.config.getValidationConfigs().get(3).getValidateMethod());

		assertFalse(this.config.isValidator());
		validationConfigs.add(new S2ValidationConfig());
		assertEquals(5, this.config.getValidationConfigs().size());
		assertTrue(this.config.isValidator());
	}

	/**
	 * Test method for {@link A3ExecuteConfig#getSaveErrors()}.<br />
	 * Test method for {@link A3ExecuteConfig#setSaveErrors(SaveType)}.
	 */
	@Test
	public final void test_getSaveErrors_setSaveErrors() {

		assertEquals(SaveType.REQUEST, this.config.getSaveErrors()); // Default value is REQUEST.
		assertSame(this.original.getSaveErrors(), this.config.getSaveErrors());

		this.config.setSaveErrors(SaveType.SESSION);
		assertEquals(SaveType.SESSION, this.config.getSaveErrors());
		assertSame(this.original.getSaveErrors(), this.config.getSaveErrors());

		this.config.setSaveErrors(null);
		assertNull(this.config.getSaveErrors());
		assertNull(this.original.getSaveErrors());
	}

	/**
	 * Test method for {@link A3ExecuteConfig#getInput()}.<br />
	 * Test method for {@link A3ExecuteConfig#setInput(String)}.
	 */
	@Test
	public final void test_getInput_setInput() {

		assertNull(this.config.getInput()); // Default value is NULL.
		assertNull(this.original.getInput());

		this.config.setInput("/error");
		assertEquals("/error", this.config.getInput());
		assertSame(this.original.getInput(), this.config.getInput());

		this.config.setInput(null);
		assertNull(this.config.getInput());
		assertNull(this.original.getInput());
	}

	/**
	 * Test method for {@link A3ExecuteConfig#isRedirect()}.<br />
	 * Test method for {@link A3ExecuteConfig#setRedirect(boolean)}.
	 *
	 */
	@Test
	public final void test_isRedirect_setRedirect() {

		assertFalse(this.config.isRedirect()); // Default value is FALSE.
		assertFalse(this.original.isRedirect());

		this.config.setRedirect(true);
		assertTrue(this.config.isRedirect());
		assertTrue(this.original.isRedirect());
	}

	/**
	 * Test method for {@link A3ExecuteConfig#getRoles()}.<br />
	 * Test method for {@link A3ExecuteConfig#setRoles(String[])}.
	 */
	@Test
	public final void test_getRoles_setRoles() {

		assertNull(this.config.getRoles()); // Default value is NULL.
		assertNull(this.original.getRoles());

		this.config.setRoles(new String[] {"guest", "admin", "manager"});
		assertEquals(3, this.config.getRoles().length);
		assertEquals("guest", this.config.getRoles()[0]);
		assertEquals("admin", this.config.getRoles()[1]);
		assertEquals("manager", this.config.getRoles()[2]);
		assertSame(this.original.getRoles(), this.config.getRoles());

		this.config.setRoles(null);
		assertNull(this.config.getRoles());
		assertNull(this.original.getRoles());
	}

	/**
	 * Test method for {@link A3ExecuteConfig#getResetMethod()}.<br />
	 * Test method for {@link A3ExecuteConfig#setResetMethod(Method)}.
	 * @throws Exception
	 */
	@Test
	public final void test_getResetMethod_setResetMethod() throws Exception {

		assertNull(this.config.getResetMethod()); // Default value is NULL.
		assertNull(this.original.getResetMethod());

		this.config.setResetMethod(IndexAction.class.getMethod("hello"));
		assertEquals(IndexAction.class.getMethod("hello"), this.config.getResetMethod());
		assertSame(this.original.getResetMethod(), this.config.getResetMethod());

		this.config.setResetMethod(null);
		assertNull(this.config.getResetMethod());
		assertNull(this.original.getResetMethod());
	}

	/**
	 * Test method for {@link A3ExecuteConfig#isStopOnValidationError()}.<br />
	 * Test method for {@link A3ExecuteConfig#setStopOnValidationError(boolean)}.
	 */
	@Test
	public final void test_isStopOnValidationError_setStopOnValidationError() {

		assertTrue(this.config.isStopOnValidationError()); // Default value is TRUE.
		assertTrue(this.original.isStopOnValidationError());

		this.config.setStopOnValidationError(false);
		assertFalse(this.config.isStopOnValidationError());
		assertFalse(this.original.isStopOnValidationError());
	}

	/**
	 * Test method for {@link A3ExecuteConfig#isUrlPatternAllSelected()}.<br />
	 * Test method for {@link A3ExecuteConfig#getUrlPattern()}.<br />
	 * Test method for {@link A3ExecuteConfig#setUrlPattern(String)}.
	 */
	@Test
	public final void test_isUrlPatternAllSelected_getUrlPattern_setUrlPattern() {

		assertEquals(this.index.getName(), this.config.getUrlPattern());
		assertEquals(this.index.getName(), this.original.getUrlPattern());
		assertFalse(this.config.isUrlPatternAllSelected());
		assertFalse(this.original.isUrlPatternAllSelected());

		this.config.setUrlPattern("");
		assertEquals(this.index.getName(), this.config.getUrlPattern());
		assertEquals(this.index.getName(), this.original.getUrlPattern());
		assertFalse(this.config.isUrlPatternAllSelected());
		assertFalse(this.original.isUrlPatternAllSelected());

		this.config.setUrlPattern("{employeeId}");
		assertEquals("{employeeId}", this.config.getUrlPattern());
		assertEquals("{employeeId}", this.original.getUrlPattern());
		assertTrue(this.config.isUrlPatternAllSelected());
		assertTrue(this.original.isUrlPatternAllSelected());

		try {
			this.config.setUrlPattern("}{");
			fail();
		} catch (Exception e) {
			assertEquals(IllegalUrlPatternRuntimeException.class, e.getClass());
		}
	}

	/**
	 * Test method for {@link A3ExecuteConfig#isRemoveActionForm()}.<br />
	 * Test method for {@link A3ExecuteConfig#setRemoveActionForm(boolean)}.
	 */
	@Test
	public final void test_isRemoveActionForm_setRemoveActionForm() {

		assertFalse(this.config.isRemoveActionForm()); // Default value is FALSE.
		assertFalse(this.original.isRemoveActionForm());

		this.config.setRemoveActionForm(true);
		assertTrue(this.config.isRemoveActionForm());
		assertTrue(this.original.isRemoveActionForm());
	}

	/**
	 * Test method for {@link A3ExecuteConfig#resolveInput(org.seasar.struts.config.S2ActionMapping)}.
	 */
	@Test
	public final void testResolveInputS2ActionMapping() {
		// TODO: 要 テストコード実装！
	}

	/**
	 * Test method for {@link A3ExecuteConfig#isTarget(java.lang.String)}.
	 */
	@Test
	public final void testIsTargetString() {
		// TODO: 要 テストコード実装！
	}

	/**
	 * Test method for {@link A3ExecuteConfig#isTarget(javax.servlet.http.HttpServletRequest)}.
	 */
	@Test
	public final void testIsTargetHttpServletRequest() {
		// TODO: 要 テストコード実装！
	}

	/**
	 * Test method for {@link A3ExecuteConfig#getQueryString(String)}.
	 */
	@Test
	public final void test_getQueryString() {

		assertEquals(this.original.getQueryString(null), this.config.getQueryString(null));
		assertEquals(this.original.getQueryString(""), this.config.getQueryString(""));
		assertEquals(this.original.getQueryString("aaa"), this.config.getQueryString("aaa"));
	}

	/**
	 * Test method for {@link A3ExecuteConfig#getProxyType()}.<br />
	 * Test method for {@link A3ExecuteConfig#setProxyType(ProxyType)}.
	 */
	@Test
	public final void test_getProxyType_setProxyType() {

		assertNull(this.config.getProxyType());

		this.config.setProxyType(ProxyType.APPEND);
		assertEquals(ProxyType.APPEND, this.config.getProxyType());

		this.config.setProxyType(ProxyType.DEFAULT);
		assertEquals(ProxyType.DEFAULT, this.config.getProxyType());

		this.config.setProxyType(ProxyType.NONE);
		assertEquals(ProxyType.NONE, this.config.getProxyType());

		this.config.setProxyType(ProxyType.OVERRIDE);
		assertEquals(ProxyType.OVERRIDE, this.config.getProxyType());

		this.config.setProxyType(null);
		assertNull(this.config.getProxyType());
	}

	/**
	 * Test method for {@link A3ExecuteConfig#getLocalProxyClassList()}.<br />
	 * Test method for {@link A3ExecuteConfig#setLocalProxyClassList(List)}.
	 */
	@Test
	public final void test_getLocalProxyClassList_setLocalProxyClassList() {

		assertNull(this.config.getLocalProxyClassList());

		List<Class<?>> localProxyClassList = new ArrayList<Class<?>>();
		localProxyClassList.add(Application1stProxy.class);
		localProxyClassList.add(Application2ndProxy.class);
		localProxyClassList.add(Application3rdProxy.class);
		this.config.setLocalProxyClassList(localProxyClassList);
		assertSame(localProxyClassList, this.config.getLocalProxyClassList());
		assertEquals(3, this.config.getLocalProxyClassList().size());
		assertEquals(Application1stProxy.class, this.config.getLocalProxyClassList().get(0));
		assertEquals(Application2ndProxy.class, this.config.getLocalProxyClassList().get(1));
		assertEquals(Application3rdProxy.class, this.config.getLocalProxyClassList().get(2));

		this.config.setLocalProxyClassList(null);
		assertNull(this.config.getLocalProxyClassList());
	}

	/**
	 * Test method for {@link A3ExecuteConfig#getGlobalProxyClassList()}.<br />
	 * Test method for {@link A3ExecuteConfig#setGlobalProxyClassList(List)}.
	 */
	@Test
	public final void test_getGlobalProxyClassList_setGlobalProxyClassList() {

		assertNull(this.config.getGlobalProxyClassList());

		List<Class<?>> globalProxyClassList = new ArrayList<Class<?>>();
		globalProxyClassList.add(Application1stProxy.class);
		globalProxyClassList.add(Application2ndProxy.class);
		globalProxyClassList.add(Application3rdProxy.class);
		this.config.setGlobalProxyClassList(globalProxyClassList);
		assertSame(globalProxyClassList, this.config.getGlobalProxyClassList());
		assertEquals(3, this.config.getGlobalProxyClassList().size());
		assertEquals(Application1stProxy.class, this.config.getGlobalProxyClassList().get(0));
		assertEquals(Application2ndProxy.class, this.config.getGlobalProxyClassList().get(1));
		assertEquals(Application3rdProxy.class, this.config.getGlobalProxyClassList().get(2));

		this.config.setGlobalProxyClassList(null);
		assertNull(this.config.getGlobalProxyClassList());
	}

}
