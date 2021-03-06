<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>

<html lang="ja">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<title>Login</title>
<link href="${f:url('/css/bootstrap.min.css')}" rel="stylesheet"><!-- Bootstrap core CSS -->
<!--[if lt IE 9]>
<script src="${f:url('/assets/js/ie8-responsive-file-warning.js')}"></script>
<![endif]-->
<script src="${f:url('/assets/js/ie-emulation-modes-warning.js')}"></script>
<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<script src="${f:url('/assets/js/ie10-viewport-bug-workaround.js')}"></script>
<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
<![endif]-->
<tiles:insert attribute="head" />
</head>

<body>
<!-- >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Start -->
<tiles:insert attribute="body" />
<!-- >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> End -->
<script src="//ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<script>window.jQuery || document.write('<script src="${f:url('/plugin/jquery/jquery-2.1.1.min.js')}"><\/script>');</script>
<tiles:insert attribute="foot" />
  </body>
</html>
