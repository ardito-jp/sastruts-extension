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

package jp.ardito.seasar.struts.quickstart.proxy;

import javax.annotation.Resource;

import jp.ardito.seasar.struts.proxy.ActionProxy;
import jp.ardito.seasar.struts.proxy.ProxyChain;
import jp.ardito.seasar.struts.quickstart.dto.LoginUserDto;

/**
 * @author Yohji Nihonyanagi
 */
public class AuthenticationProxy implements ActionProxy {

	@Resource
	private LoginUserDto loginUserDto;

	/*
	 * @see ActionProxy#execute(ProxyChain)
	 */
	public String execute(ProxyChain proxyChain) throws Exception {

		if (this.loginUserDto != null && this.loginUserDto.isAuthenticated()) {
			return proxyChain.invoke(); // Authenticated!!
		} else {
			return "/auth?redirect=true"; // Not authenticated...
		}
	}

}
