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

/**
 * @author Yohji Nihonyanagi
 */
public enum ProxyType {

	/**
	 * <p>プロキシを割り当てません。</p>
	 */
	NONE,

	/**
	 * <p>アプリケーション標準プロキシを割り当てます。</p>
	 *
	 * <p>この値を指定した場合・・・<br />
	 * <code>customizer.dicon</code> で指定されたアプリケーション標準の {@link ActionProxy} を実行します。</p>
	 */
	DEFAULT,

	/**
	 * <p>デフォルトの {@link ActionProxy} に加えて {@link Proxy} にて指定された {@link ActionProxy} も適用します。</p>
	 *
	 * <p>この値を指定した場合・・・<br />
	 * <code>customizer.dicon</code> で指定されたアプリケーション標準の {@link ActionProxy} に加えて、<br />
	 * Action クラス、またはメソッドに対して指定された {@link ActionProxy} を実行します。</p>
	 */
	APPEND,

	/**
	 * <p>デフォルトの {@link ActionProxy} を無視して {@link Proxy} にて指定された {@link ActionProxy} を適用します.</p>
	 *
	 * <p>この値を指定した場合・・・<br />
	 * <code>customizer.dicon</code> で指定されたアプリケーション標準の {@link ActionProxy} を無視して、<br />
	 * Action クラス、またはメソッドに対して指定された {@link ActionProxy} を実行します。</p>
	 */
	OVERRIDE;

}
