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

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.ardito.seasar.struts.config.A3ExecuteConfig;
import jp.ardito.seasar.struts.proxy.ActionProxy;
import jp.ardito.seasar.struts.proxy.ProxyChain;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.seasar.framework.container.SingletonS2Container;
import org.seasar.struts.action.ActionWrapper;
import org.seasar.struts.config.S2ActionMapping;
import org.seasar.struts.config.S2ExecuteConfig;
import org.seasar.struts.util.S2ExecuteConfigUtil;

/**
 * <p>{@link ActionWrapper} の拡張クラスです。</p>
 *
 * @author Yohji Nihonyanagi
 */
public class A3ActionWrapper extends ActionWrapper {

	/**
	 * @param actionMapping Actionマッピング情報
	 */
	public A3ActionWrapper(S2ActionMapping actionMapping) {

		super(actionMapping);
	}

	/*
	 * @see ActionWrapper#execute(ActionMapping, ActionForm, HttpServletRequest, HttpServletResponse)
	 */
	@Override
	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response) throws Exception {

		S2ExecuteConfig executeConfig = S2ExecuteConfigUtil.getExecuteConfig();
		if (executeConfig != null) {
			if (executeConfig instanceof A3ExecuteConfig) {
				ProxyChain proxyChain = new ProxyChain(
					request,
					response,
					(A3ActionMapping) this.actionMapping,
					(A3ExecuteConfig) executeConfig,
					this,
					createProxyList((A3ExecuteConfig) executeConfig));
				proxyChain.invoke();
				return proxyChain.getActionForward();
			} else {
				return execute(request, executeConfig);
			}
		} else {
			return null;
		}
	}

	/*
	 * @see ActionWrapper#execute(HttpServletRequest, S2ExecuteConfig)
	 */
	@Override
	public ActionForward execute(HttpServletRequest request, S2ExecuteConfig executeConfig) {

		return super.execute(request, executeConfig);
	}

	protected List<ActionProxy> createProxyList(A3ExecuteConfig executeConfig) {

		List<ActionProxy> proxyInstanceList = new ArrayList<ActionProxy>(4);

		List<Class<?>> globalProxyClassList = executeConfig.getGlobalProxyClassList();
		if (globalProxyClassList != null && 0 < globalProxyClassList.size()) {
			for (Class<?> proxyClass : globalProxyClassList) {
				ActionProxy instance = (ActionProxy)SingletonS2Container.getComponent(proxyClass);
				proxyInstanceList.add(instance);
			}
		}

		List<Class<?>> localProxyClassList = executeConfig.getLocalProxyClassList();
		if (localProxyClassList != null && 0 < localProxyClassList.size()) {
			for (Class<?> proxyClass : localProxyClassList) {
				ActionProxy instance = (ActionProxy)SingletonS2Container.getComponent(proxyClass);
				proxyInstanceList.add(instance);
			}
		}

		return proxyInstanceList;
	}

}
