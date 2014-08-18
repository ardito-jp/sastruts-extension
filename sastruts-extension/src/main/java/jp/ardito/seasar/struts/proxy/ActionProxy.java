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
 * <p>Action に前後処理を組み込むためのプロキシ・インターフェースです。</p>
 *
 * <p>Action クラスまたはメソッドごとのアクセス制御 (認証, 認可) や後処理を実装することができます。</p>
 *
 * @author Yohji Nihonyanagi
 */
public interface ActionProxy {

	/**
	 * <p>Action クラスまたはメソッドごとのアクセス制御 (認証, 認可) や後処理を実装してください。</p>
	 *
	 * <p>事前判定が OK (Action の実行を許可) であれば {@link ProxyChain#invoke()} を <code>return</code> して下さい。<br />
	 * 事前判定が NG (Action の実行を禁止) であれば強制遷移させるべき path を <code>return</code> して下さい。</p>
	 *
	 * <pre>
	 * public String execute(ProxyChain proxyChain) {
	 *
	 *     // Action を実行して良いか否かの事前判定処理・・・
	 *
	 *     // 事前判定が OK であれば・・・
	 *     if (....) {
	 *
	 *         // 後続の {@link ActionProxy} があればそいつを実行・・・
	 *         // すべての {@link ActionProxy} が完了すれば Validator 実行後に Action のメソッドを実行・・・
	 *         return proxyChain.invoke();
	 *
	 *     // 事前判定が NG であれば・・・
	 *     } else {
	 *
	 *         // 後続の {@link ActionProxy} や Action を実行せずに処理を中断・・・
	 *         return "login.jsp";
	 *     }
	 * }
	 * </pre>
	 *
	 * @param proxyChain プロキシチェーン
	 * @return 遷移情報
	 * @throws Exception
	 */
	String execute(ProxyChain proxyChain) throws Exception;

}
