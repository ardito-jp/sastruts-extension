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

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionMapping;
import org.seasar.struts.action.S2RequestProcessor;
import org.seasar.struts.config.S2ActionMapping;

/**
 * @author Yohji Nihonyanagi
 */
public class A3RequestProcessor extends S2RequestProcessor {

	/*
	 * @see S2RequestProcessor#processActionCreate(HttpServletRequest, HttpServletResponse, ActionMapping)
	 */
	@Override
	protected Action processActionCreate(
		HttpServletRequest request,
		HttpServletResponse response,
		ActionMapping mapping) throws IOException {

		Action action = null;

		try {
			action = new A3ActionWrapper(((S2ActionMapping) mapping));
			action.setServlet(this.servlet);
			return action;
		} catch (Exception e) {
			log.error(getInternal().getMessage("actionCreate", mapping.getPath()), e);
			response.sendError(
				HttpServletResponse.SC_INTERNAL_SERVER_ERROR, getInternal().getMessage("actionCreate", mapping.getPath()));
			return null;
		}
	}

}
