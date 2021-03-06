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
import javax.servlet.http.HttpServletRequest;

import jp.ardito.seasar.struts.proxy.Proxy;
import jp.ardito.seasar.struts.proxy.ProxyType;
import jp.ardito.seasar.struts.quickstart.dto.LoginUserDto;
import jp.ardito.seasar.struts.quickstart.form.AuthForm;
import org.seasar.framework.container.SingletonS2Container;
import org.seasar.framework.log.Logger;
import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;
import org.seasar.struts.util.ResponseUtil;

/**
 * @author Yohji Nihonyanagi
 */
@Proxy(type = ProxyType.NONE) // 一切のプロキシを適用しない → すべてのリクエストを受け付けるぞ！
public class AuthAction {

	private static final Logger LOGGER = Logger.getLogger(AuthAction.class);

	@ActionForm
	@Resource
	private AuthForm authForm;

	@Resource
	private HttpServletRequest request;

	/**
	 * <p>~/quickstart/auth</p>
	 * @return リクエスト転送先情報
	 */
	@Execute(validator = false)
	public String index() {

		if (this.request.getSession(false) != null) {
			this.request.getSession(false).invalidate();
		}
		return "/auth.jsp";
	}

	/**
	 * <p>~/quickstart/auth/login</p>
	 * @return リクエスト転送先情報
	 */
	@Execute(validator = true, input = "/auth.jsp")
	public String login() {

		if (!this.authForm.account.equals("taro") || !this.authForm.password.equals("tarotaro")) {
			LOGGER.log("IAPP0000", new Object[] {this.authForm.account, this.request.getRequestedSessionId()});
			return "/auth.jsp";
		}

		if (this.request.getSession(false) != null) {
			this.request.getSession(false).invalidate();
		}
		LoginUserDto loginUserDto = SingletonS2Container.getComponent(LoginUserDto.class);
		loginUserDto.setAccount(this.authForm.account);
		loginUserDto.setAuthenticated(true);
		LOGGER.log("IAPP0001", new Object[] {loginUserDto.getAccount(), this.request.getRequestedSessionId()});

		// Redirecting to Index ("/").
		return "/?redirect=true";
	}

	/**
	 * <p>~/quickstart/auth/logout</p>
	 * @return リクエスト転送先情報
	 */
	@Execute(validator = false)
	public String logout() {

		if (this.request.getSession(false) != null) {
			this.request.getSession(false).invalidate();
		}
		ResponseUtil.write("Bye!", "text/plain", "UTF-8");
		return null;
	}

}
