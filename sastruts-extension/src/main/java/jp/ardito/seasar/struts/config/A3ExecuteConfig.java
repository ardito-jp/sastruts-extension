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

package jp.ardito.seasar.struts.config;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import jp.ardito.seasar.struts.proxy.ProxyType;
import org.seasar.struts.config.S2ActionMapping;
import org.seasar.struts.config.S2ExecuteConfig;
import org.seasar.struts.config.S2ValidationConfig;
import org.seasar.struts.enums.SaveType;

/**
 * <p>{@link S2ExecuteConfig} の拡張クラスです。</p>
 *
 * <p>Action 実行情報を表現します。<br />
 * Action に割り当てられたプロキシ情報を保持します。</p>
 *
 * @author Yohji Nihonyanagi
 */
public class A3ExecuteConfig extends S2ExecuteConfig implements Serializable {

	private static final long serialVersionUID = 1L;

	protected S2ExecuteConfig executeConfig;

	protected ProxyType proxyType;

	protected List<Class<?>> localProxyClassList;

	protected List<Class<?>> globalProxyClassList;

	/**
	 * @param executeConfig Action 実行情報 (SAStruts Original)
	 */
	public A3ExecuteConfig(S2ExecuteConfig executeConfig) {

		this.executeConfig = executeConfig;
		this.method = executeConfig.getMethod(); // S2ActionMapping にて直接参照されているために設定しておく！
	}

	/*
	 * @see S2ExecuteConfig#getMethod()
	 */
	@Override
	public Method getMethod() {

		return this.executeConfig.getMethod();
	}

	/*
	 * @see S2ExecuteConfig#setMethod(Method)
	 */
	@Override
	public void setMethod(Method method) {

		this.executeConfig.setMethod(method);
	}

	/*
	 * @see S2ExecuteConfig#isValidator()
	 */
	@Override
	public boolean isValidator() {

		return this.executeConfig.isValidator();
	}

	/*
	 * @see S2ExecuteConfig#getValidationConfigs()
	 */
	@Override
	public List<S2ValidationConfig> getValidationConfigs() {

		return this.executeConfig.getValidationConfigs();
	}

	/*
	 * @see S2ExecuteConfig#setValidationConfigs(List)
	 */
	@Override
	public void setValidationConfigs(List<S2ValidationConfig> validationConfigs) {

		this.executeConfig.setValidationConfigs(validationConfigs);
	}

	/*
	 * @see S2ExecuteConfig#getSaveErrors()
	 */
	@Override
	public SaveType getSaveErrors() {

		return this.executeConfig.getSaveErrors();
	}

	/*
	 * @see S2ExecuteConfig#setSaveErrors(SaveType)
	 */
	@Override
	public void setSaveErrors(SaveType saveErrors) {

		this.executeConfig.setSaveErrors(saveErrors);
	}

	/*
	 * @see S2ExecuteConfig#getInput()
	 */
	@Override
	public String getInput() {

		return this.executeConfig.getInput();
	}

	/*
	 * @see S2ExecuteConfig#setInput(String)
	 */
	@Override
	public void setInput(String input) {

		this.executeConfig.setInput(input);
	}

	/*
	 * @see S2ExecuteConfig#resolveInput(S2ActionMapping)
	 */
	@Override
	public String resolveInput(S2ActionMapping actionMapping) {

		return this.executeConfig.resolveInput(actionMapping);
	}

	/*
	 * @see S2ExecuteConfig#getUrlPattern()
	 */
	@Override
	public String getUrlPattern() {

		return this.executeConfig.getUrlPattern();
	}

	/*
	 * @see S2ExecuteConfig#setUrlPattern(String)
	 */
	@Override
	public void setUrlPattern(String urlPattern) {

		this.executeConfig.setUrlPattern(urlPattern);
	}

	/*
	 * @see S2ExecuteConfig#isUrlPatternAllSelected()
	 */
	@Override
	public boolean isUrlPatternAllSelected() {

		return this.executeConfig.isUrlPatternAllSelected();
	}

	/*
	 * @see S2ExecuteConfig#getRoles()
	 */
	@Override
	public String[] getRoles() {

		return this.executeConfig.getRoles();
	}

	/*
	 * @see S2ExecuteConfig#setRoles(String[])
	 */
	@Override
	public void setRoles(String[] roles) {

		this.executeConfig.setRoles(roles);
	}

	/*
	 * @see S2ExecuteConfig#isStopOnValidationError()
	 */
	@Override
	public boolean isStopOnValidationError() {

		return this.executeConfig.isStopOnValidationError();
	}

	/*
	 * @see S2ExecuteConfig#setStopOnValidationError(boolean)
	 */
	@Override
	public void setStopOnValidationError(boolean stopOnValidationError) {

		this.executeConfig.setStopOnValidationError(stopOnValidationError);
	}

	/*
	 * @see S2ExecuteConfig#isRemoveActionForm()
	 */
	@Override
	public boolean isRemoveActionForm() {

		return this.executeConfig.isRemoveActionForm();
	}

	/*
	 * @see S2ExecuteConfig#setRemoveActionForm(boolean)
	 */
	@Override
	public void setRemoveActionForm(boolean removeActionForm) {

		this.executeConfig.setRemoveActionForm(removeActionForm);
	}

	/*
	 * @see S2ExecuteConfig#getResetMethod()
	 */
	@Override
	public Method getResetMethod() {

		return this.executeConfig.getResetMethod();
	}

	/*
	 * @see S2ExecuteConfig#setResetMethod(Method)
	 */
	@Override
	public void setResetMethod(Method resetMethod) {

		this.executeConfig.setResetMethod(resetMethod);
	}

	/*
	 * @see S2ExecuteConfig#isRedirect()
	 */
	@Override
	public boolean isRedirect() {

		return this.executeConfig.isRedirect();
	}

	/*
	 * @see S2ExecuteConfig#setRedirect(boolean)
	 */
	@Override
	public void setRedirect(boolean redirect) {

		this.executeConfig.setRedirect(redirect);
	}

	/*
	 * @see S2ExecuteConfig#isTarget(String)
	 */
	@Override
	public boolean isTarget(String paramPath) {

		return this.executeConfig.isTarget(paramPath);
	}

	/*
	 * @see S2ExecuteConfig#isTarget(HttpServletRequest)
	 */
	@Override
	public boolean isTarget(HttpServletRequest request) {

		return this.executeConfig.isTarget(request);
	}

	/*
	 * @see S2ExecuteConfig#getQueryString(String)
	 */
	@Override
	public String getQueryString(String paramPath) {

		return this.executeConfig.getQueryString(paramPath);
	}

	/**
	 * @return 実行メソッドに適用されたプロキシ種別
	 */
	public ProxyType getProxyType() {

		return this.proxyType;
	}

	/**
	 * @param proxyType 実行メソッドに適用されたプロキシ種別
	 */
	public void setProxyType(ProxyType proxyType) {

		this.proxyType = proxyType;
	}

	/**
	 * @return 割り当てられたプロキシ型リスト
	 */
	public List<Class<?>> getLocalProxyClassList() {

		return this.localProxyClassList;
	}

	/**
	 * @param localProxyClassList 割り当てられたプロキシ型リスト
	 */
	public void setLocalProxyClassList(List<Class<?>> localProxyClassList) {

		this.localProxyClassList = localProxyClassList;
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

}
