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

package jp.ardito.seasar.struts.creator;

import org.seasar.framework.container.ComponentCreator;
import org.seasar.framework.container.ComponentCustomizer;
import org.seasar.framework.container.creator.ComponentCreatorImpl;
import org.seasar.framework.container.deployer.InstanceDefFactory;
import org.seasar.framework.convention.NamingConvention;

/**
 * <p>プロキシのための {@link ComponentCreator} です。</p>
 *
 * @author Yohji Nihonyanagi
 */
public class ProxyCreator extends ComponentCreatorImpl implements ComponentCreator {

	/**
	 * @param namingConvention 命名規約
	 */
	public ProxyCreator(NamingConvention namingConvention) {

		super(namingConvention);

		setNameSuffix("Proxy");
		setInstanceDef(InstanceDefFactory.REQUEST);
	}

	/**
	 * <p>プロキシのための {@link ComponentCustomizer} を返します。</p>
	 *
	 * @return コンポーネント定義カスタマイザ
	 * @see #getCustomizer()
	 */
	public ComponentCustomizer getProxyCustomizer() {

		return getCustomizer();
	}

	/**
	 * <p>プロキシのための {@link ComponentCustomizer} を設定します。</p>
	 *
	 * @param customizer コンポーネント定義カスタマイザ
	 * @see #setCustomizer(ComponentCustomizer)
	 */
	public void setProxyCustomizer(ComponentCustomizer customizer) {

		setCustomizer(customizer);
	}

}
