<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<tiles:insert template="/WEB-INF/jsp/layout.jsp" flush="true">
<tiles:put name="title" value="Quickstart - Sign In" />
<tiles:put name="head" type="string">
<link href="${f:url('/css/app.css')}" rel="stylesheet"><!-- Custom styles for this template -->
</tiles:put>
<tiles:put name="body" type="string">
  <div class="container">
<s:form method="post" action="/auth/login" styleClass="form-signin" >
    <h2 class="form-signin-heading">Please sign in</h2>
    <input type="text" name="account" class="form-control" placeholder="Account" required autofocus>
    <input type="password" name="password" class="form-control" placeholder="Password" required>
    <div class="checkbox">
      <label>
        <input type="checkbox" name="rememberMe" value="On"> Remember me
      </label>
    </div>
    <input type="submit" class="btn btn-lg btn-primary btn-block" value="Sign in" />
</s:form>
  </div><!-- /container -->
</tiles:put>
<tiles:put name="foot" type="string" />
</tiles:insert>
