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

package jp.ardito.seasar.struts.proxy;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.ardito.seasar.struts.action.A3ActionMapping;
import jp.ardito.seasar.struts.action.A3ActionWrapper;
import jp.ardito.seasar.struts.config.A3ExecuteConfig;
import org.apache.struts.action.ActionForward;
import org.seasar.framework.log.Logger;

/**
 * <p>Action に割り当てられたプロキシの実行制御を担当します。</p>
 *
 * @author Yohji Nihonyanagi
 */
public class ProxyChain {

	private static final Logger LOGGER = Logger.getLogger(ProxyChain.class);

	protected HttpServletRequest request;

	protected HttpServletResponse response;

	protected ActionForward actionForward;

	protected A3ActionMapping actionMapping;

	protected A3ExecuteConfig executeConfig;

	protected A3ActionWrapper actionWrapper;

	protected List<ActionProxy> proxyInstanceList;

	protected boolean closed;

	/**
	 * @param request
	 * @param response
	 * @param actionMapping
	 * @param executeConfig
	 * @param actionWrapper
	 * @param proxyInstanceList
	 */
	public ProxyChain(
		HttpServletRequest request,
		HttpServletResponse response,
		A3ActionMapping actionMapping,
		A3ExecuteConfig executeConfig,
		A3ActionWrapper actionWrapper,
		List<ActionProxy> proxyInstanceList) {

		this.request = request;
		this.response = response;
		this.actionMapping = actionMapping;
		this.executeConfig = executeConfig;
		this.actionWrapper = actionWrapper;
		this.proxyInstanceList = proxyInstanceList;

		LOGGER.log("DSAX0011", new Object[] {getInvocationName(), this.actionMapping.getPath()});
	}

	/**
	 * @return {@link ActionProxy} または Action の実行結果(遷移情報)
	 */
	public ActionForward getActionForward() {

		return this.actionForward;
	}

	/**
	 * @return NULL
	 * @throws Exception
	 */
	public String invoke() throws Exception {

		if (this.proxyInstanceList == null || this.proxyInstanceList.isEmpty()) {
			this.actionForward = this.actionWrapper.execute(this.request, this.executeConfig);
			this.closed = true;
			LOGGER.log("DSAX0012", new Object[] {
			    getInvocationName(), getForwardName()
			});
		} else {
			ActionProxy proxy = this.proxyInstanceList.remove(0);
			String result = proxy.execute(this);
			if (!this.closed) {
				this.actionForward = this.actionMapping.createForward(result, false);
				this.closed = true;
				LOGGER.log("DSAX0013", new Object[] {
				    getInvocationName(), getForwardName(), getProxyName(proxy)
				});
			}
		}
		return null;
	}

	protected String getInvocationName() {

		String actionClass = this.actionMapping.getComponentDef().getComponentClass().getSimpleName();
		String executeMethod = this.executeConfig.getMethod().getName();
		return actionClass + "#" + executeMethod + "()";
	}

	protected String getForwardName() {

		return (this.actionForward != null) ? this.actionForward.getPath() : "null";
	}

	protected String getProxyName(ActionProxy proxy) {

		return proxy.getClass().getSimpleName();
	}

}
