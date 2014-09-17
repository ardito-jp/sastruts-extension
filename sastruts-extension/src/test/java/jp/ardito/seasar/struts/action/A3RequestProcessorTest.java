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

package jp.ardito.seasar.struts.action;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionMapping;
import org.easymock.EasyMock;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.seasar.struts.config.S2ActionMapping;

/**
 * @author Yohji Nihonyanagi
 */
public class A3RequestProcessorTest {

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

	private A3RequestProcessor processor;

	/**
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		this.processor = new A3RequestProcessor();
	}

	/**
	 * @throws Exception
	 */
	@After
	public void tearDown() throws Exception {
		this.processor = null;
	}

	/**
	 * Test of {@link A3RequestProcessor#processActionCreate(HttpServletRequest, HttpServletResponse, ActionMapping)}.
	 */
	@Test
	@Ignore
	public final void testProcessActionCreateHttpServletRequestHttpServletResponseActionMapping() {

		HttpServletRequest request = EasyMock.createMock(HttpServletRequest.class);
		HttpServletResponse response = EasyMock.createMock(HttpServletResponse.class);
		ActionMapping mapping = new S2ActionMapping();

		try {
			Action action = this.processor.processActionCreate(request, response, mapping);
			assertNotNull(action);
			// 要実装・・・
		} catch (IOException ioe) {
			fail();
		}

	}

}
