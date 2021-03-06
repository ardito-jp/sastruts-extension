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

import javax.annotation.Resource;
import jp.ardito.seasar.struts.test.form.AppleForm;
import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

/**
 * @author Yohji Nihonyanagi
 */
public class AppleAction {

	@ActionForm
	@Resource
	protected AppleForm appleForm;

	/**
	 * @return 遷移情報
	 */
	@Execute(validator = false)
	public String index() {

		return "apple.jsp";
	}

	/**
	 * @return 遷移情報
	 */
	@Execute(validator = true, input = "apple.jsp")
	public String submit() {

		return "apple.jsp";
	}

}
