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

import javax.annotation.Resource;

import jp.ardito.seasar.struts.proxy.Proxy;
import jp.ardito.seasar.struts.proxy.ProxyType;
import jp.ardito.seasar.struts.quickstart.dto.LoginUserDto;
import jp.ardito.seasar.struts.quickstart.proxy.AuthenticationProxy;
import org.seasar.struts.annotation.Execute;

/**
 * @author Yohji Nihonyanagi
 */
@Proxy(type = ProxyType.APPEND, proxy = AuthenticationProxy.class)
public class AuthAction {

	@Resource
	private LoginUserDto loginUserDto;

	/**
	 * <p>~/quickstart/auth</p>
	 * @return リクエスト転送先情報
	 */
	@Execute(validator = false)
	@Proxy(type = ProxyType.NONE) // 一切のプロキシを適用しない → すべてのリクエストを受け付けるぞ！
	public String index() {
		return "/auth.jsp";
	}

	/**
	 * <p>~/quickstart/auth/login</p>
	 * @return リクエスト転送先情報
	 */
	@Execute(validator = false)
	@Proxy(type = ProxyType.NONE) // 一切のプロキシを適用しない → すべてのリクエストを受け付けるぞ！
	public String login() {
		return "/?redirect=true";
	}

	/**
	 * <p>~/quickstart/auth/logout</p>
	 * @return リクエスト転送先情報
	 */
	@Execute(validator = false)
	public String logout() {
		return "/auth.jsp";
	}

}
