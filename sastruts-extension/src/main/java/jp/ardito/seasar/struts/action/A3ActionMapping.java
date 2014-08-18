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

import org.seasar.framework.container.ComponentDef;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;
import org.seasar.struts.config.S2ActionMapping;

/**
 * <p>{@link S2ActionMapping} の拡張クラスです。 <br />
 *
 * <p>Action の フィールド に対するパラーメーターの上書きを防止するための {@link #actionReadOnly} というプロパティを備えています。 </p>
 *
 * @author Yohji Nihonyanagi
 */
public class A3ActionMapping extends S2ActionMapping {

	private static final long serialVersionUID = -1L;

	protected boolean actionReadOnly;

	/**
	 */
	public A3ActionMapping() {
		super();
	}

	/*
	 * @see S2ActionMapping#getActionForm()
	 */
	@Override
	public Object getActionForm() {
        // @ActionForm が定義されなければ ActionForm として NULL を返却する・・・
        // 本来であれば、このメソッドでなくて、
        // getActionFormComponentDef() と getActionFormBeanDesc() をオーバーロードすべきなんだけど、、
        // 上述のメソッドで null を返却してしまうとあちらこちらで NullPointerException が出てしまうので、
        // とりあえずの暫定処置です○
        return this.actionReadOnly ? getRealActionForm() : super.getActionForm();
    }

    /**
     * @return フィールドをパラメータで上書き禁止するか否か
     */
    public boolean getActionReadOnly() {
        return this.actionReadOnly;
    }

    /**
     * @param actionReadOnly フィールドをパラメータで上書き禁止するか否か
     */
    public void setActionReadOnly(boolean actionReadOnly) {
        this.actionReadOnly = actionReadOnly;
    }

    protected ComponentDef getRealActionFormComponentDef() {
        if (this.actionFormField == null) {
            return null;
        }
        if (this.actionFormComponentDef == null) {
            this.actionFormComponentDef =
                SingletonS2ContainerFactory.getContainer().getComponentDef(this.actionFormField.getType());
        }
        return this.actionFormComponentDef;
    }

    protected Object getRealActionForm() {
        return (getRealActionFormComponentDef() != null)
            ? getRealActionFormComponentDef().getComponent() : null;
    }

}
