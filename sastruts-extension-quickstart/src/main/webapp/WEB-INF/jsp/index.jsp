<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<tiles:insert template="/WEB-INF/jsp/layout.jsp" flush="true">
<tiles:put name="title" value="Quickstart - Index" />
<tiles:put name="head" type="string">
<link href="${f:url('/css/app.css')}" rel="stylesheet"><!-- Custom styles for this template -->
</tiles:put>
<tiles:put name="body" type="string">
  <div class="container">
    <div class="form-signin">
      <h2 class="form-signin-heading">Welcome Quickstart !!</h2>
      <div>
        <p>ログイン認証済の方ですね！<br />
        おめでとうございます！！</p>
        <p>ログイン認証情報 ： ${loginUserDto.account}</p>
        <p>下記ログアウトボタンを押下後、<br />
        再度このURLにアクセスしてみてください。<br />
        このページにはもうアクセスできないはずです。</p>
      </div>
      <div>
        <button type="submit" id="signout" class="btn btn-lg btn-primary btn-block">Sign out</button>
      </div>
      <div>
        <p id="logout-succeed" class="notifyText">&nbsp;</p>
        <p id="logout-failed" class="errorText">&nbsp;</p>
        <p id="logout-suspended" class="errorText">&nbsp;</p>
      </div>
    </div>
  </div><!-- /container -->
</tiles:put>
<tiles:put name="foot" type="string">
<script type="text/javascript">
$(function() {
	$('#signout').click(function() {
		$.ajax({
			type: "GET",
			url: "${f:url('/')}auth/logout",
			cache: false,
			dataType: "text",
			data: {},
			success: function (data, textStatus) {
				$('#logout-succeed').text('Sign out !!');
			},
			failure: function (XMLHttpRequest, textStatus) {
				$('#logout-failed').text('Failed in ...');
			},
			error: function (XMLHttpRequest, textStatus) {
				$('#logout-suspended').text('Error ...');
			}
		});
	});
});
</script>
</tiles:put>
</tiles:insert>
