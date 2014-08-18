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

package jp.ardito.seasar.struts.test.action;

import org.seasar.struts.annotation.Execute;

/**
 * @author Yohji Nihonyanagi
 */
public class IndexAction {

	/**
	 * @return 遷移情報
	 */
	@Execute(validator = false)
	public String index() {
		return "index.jsp";
	}

	/**
	 * @return 遷移情報
	 */
	@Execute(validator = true, validate = "validate", input = "hello.jsp")
	public String hello() {
		return "hello.jsp";
	}

	/**
	 * @return 検証結果
	 */
	public boolean validate() {
		return true;
	}

	/** @return 検証結果 */
	public boolean validate1st() { return true; }
	/** @return 検証結果 */
	public boolean validate2nd() { return true; }
	/** @return 検証結果 */
	public boolean validate3rd() { return true; }
	/** @return 検証結果 */
	public boolean validate4th() { return true; }

}
