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

package jp.ardito.seasar.struts.customizer;

import java.util.ArrayList;
import java.util.List;

import jp.ardito.seasar.struts.action.A3ActionMapping;
import jp.ardito.seasar.struts.config.A3ExecuteConfig;
import jp.ardito.seasar.struts.proxy.Proxy;
import jp.ardito.seasar.struts.proxy.ProxyType;
import org.seasar.framework.container.ComponentDef;
import org.seasar.struts.config.S2ActionMapping;
import org.seasar.struts.config.S2ExecuteConfig;
import org.seasar.struts.customizer.ActionCustomizer;
import org.seasar.struts.util.ActionUtil;

/**
 * <p>Actionに対するコンポーネントカスタマイザ―です.</p>
 *
 * <p>{@link org.seasar.struts.customizer.ActionCustomizer} を拡張します. <br />
 * Actionクラス毎、Action実行メソッド毎に指定された {@link Proxy} をもとにアクセスコントロール情報を初期化します.</p>
 *
 * @author Yohji Nihonyanagi
 */
public class A3ActionCustomizer extends ActionCustomizer {

	protected boolean actionReadOnly;

	protected List<Class<?>> globalProxyClassList;

	/**
	 * <p>デフォルトコンストラクタです.</p>
	 */
	public A3ActionCustomizer() {
		super();
	}

	/**
	 * @return パラメーターによる上書き禁止するか否か
	 */
	public boolean getActionReadOnly() {
		return this.actionReadOnly;
	}

	/**
	 * @param actionReadOnly パラメーターによる上書き禁止するか否か
	 */
	public void setActionReadOnly(boolean actionReadOnly) {
		this.actionReadOnly = actionReadOnly;
	}

	/**
	 * @return アプリケーション標準 プロキシ型リスト
	 */
	public List<Class<?>> getGlobalProxyClassList() {
		return this.globalProxyClassList;
	}

	/**
	 * @param globalProxyClassList アプリケーション標準 プロキシ型リスト
	 */
	public void setGlobalProxyClassList(List<Class<?>> globalProxyClassList) {
		this.globalProxyClassList = globalProxyClassList;
	}

	/**
	 * @param globalProxyClass アプリケーション標準 プロキシ型リスト
	 */
	public void addGlobalProxyClass(Class<?> globalProxyClass) {
		if (this.globalProxyClassList == null) {
			this.globalProxyClassList = new ArrayList<Class<?>>(4);
		}
		this.globalProxyClassList.add(globalProxyClass);
	}

	/*
	 * @see ActionCustomizer#createActionMapping(ComponentDef)
	 */
	@Override
	protected S2ActionMapping createActionMapping(ComponentDef componentDef) {

		A3ActionMapping actionMapping = new A3ActionMapping();
		actionMapping.setPath(ActionUtil.fromActionNameToPath(componentDef.getComponentName()));
		actionMapping.setComponentDef(componentDef);
		actionMapping.setName(componentDef.getComponentName() + "Form");
		actionMapping.setActionReadOnly(this.actionReadOnly);

		Class<?> actionClass = componentDef.getComponentClass();
		setupActionForm(actionMapping, actionClass);
		setupMethod(actionMapping, actionClass);

		for (String executeMethodName : actionMapping.getExecuteMethodNames()) {
			setupProxy(actionMapping, actionClass, executeMethodName);
		}

		return actionMapping;
	}

	/**
	 * @param actionMapping Actionマッピング情報
	 * @param actionClass Actionクラス情報
	 * @param executeMethodName Action実行メソッド
	 */
	protected void setupProxy(S2ActionMapping actionMapping, Class<?> actionClass, String executeMethodName) {

		Proxy actionProxy = actionClass.getAnnotation(Proxy.class);

		S2ExecuteConfig executeConfig = actionMapping.getExecuteConfig(executeMethodName);
		Proxy localProxy = executeConfig.getMethod().getAnnotation(Proxy.class);

		ProxyType proxyType = null;
		Class<?>[] proxyClasses;

		if (localProxy != null) {
			proxyType = localProxy.type();
			proxyClasses = localProxy.proxy();
		} else if (actionProxy != null) {
			proxyType = actionProxy.type();
			proxyClasses = actionProxy.proxy();
		} else {
			proxyType = ProxyType.DEFAULT;
			proxyClasses = null;
		}

		// A3ExecuteConfig wrapper = Beans.createAndCopy(A3ExecuteConfig.class, executeConfig).execute();
		A3ExecuteConfig wrapper = new A3ExecuteConfig(executeConfig);
		wrapper.setProxyType(proxyType);

		if (ProxyType.DEFAULT.equals(proxyType) || ProxyType.APPEND.equals(proxyType)) {
			wrapper.setGlobalProxyClassList(this.globalProxyClassList);
		} else {
			wrapper.setGlobalProxyClassList(null);
		}

		if (ProxyType.APPEND.equals(proxyType) || ProxyType.OVERRIDE.equals(proxyType)) {
			if (proxyClasses == null || proxyClasses.length < 1) {
				throw new IllegalStateException("@Auth にて AuthType.APPEND または AuthType.OVERRIDE を指定する場合､"
				    + "Proxy を1つ以上指定して下さい! ["
				    + actionMapping.getAction().getClass().getName()
				    + "#"
				    + executeMethodName
				    + "()]");
			}
			List<Class<?>> localProxyClassList = new ArrayList<Class<?>>();
			for (Class<?> proxyClass : proxyClasses) {
				localProxyClassList.add(proxyClass);
			}
			wrapper.setLocalProxyClassList(localProxyClassList);
		} else {
			wrapper.setLocalProxyClassList(null);
		}

		actionMapping.addExecuteConfig(wrapper);
	}

}
