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

package jp.ardito.seasar.struts.quickstart.form;

import java.io.Serializable;

import org.seasar.framework.container.annotation.tiger.Component;
import org.seasar.framework.container.annotation.tiger.InstanceType;
import org.seasar.struts.annotation.Arg;
import org.seasar.struts.annotation.Mask;
import org.seasar.struts.annotation.Maxlength;
import org.seasar.struts.annotation.Minlength;
import org.seasar.struts.annotation.Required;

/**
 * @author Yohji Nihonyanagi
 */
@SuppressWarnings("javadoc")
@Component(instance = InstanceType.SESSION)
public class AuthForm implements Serializable {

	private static final long serialVersionUID = 1L;

    @Required(target = "login", arg0 = @Arg(key = "key.auth.account"))
	@Maxlength(maxlength = 32)
	@Minlength(minlength = 4)
	@Mask(mask = "^[a-zA-Z0-9_]+$")
	public String account;

	@Required(target = "login", arg0 = @Arg(key = "key.auth.password"))
	@Maxlength(maxlength = 64)
	@Minlength(minlength = 8)
	@Mask(mask = "^[a-zA-Z0-9_]+$")
	public String password;

}
