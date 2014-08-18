# What is SAStruts Extension ?  

A Few, Useful Extensions for [SAStruts](http://sastruts.seasar.org/) (Seasar.Org)  
  

[SAStruts](http://sastruts.seasar.org/) (Seasar.Org) の拡張コンポーネントです。  
といっても大袈裟な機能はありません。  
SAStruts で開発する際の良い意味で「おまけ的な存在」でありたいと思います。(2008/08/31)  



# Postscript  

Seasar および SAStruts の機能開発 (進化) も止まり、  SAStruts Extension も 2年以上メンテナンスせずに放置していたのですが、  
引き続き利用してくださっている方もいらっしゃるので細々とメンテナンスを続けていきます。  
ホスティングサービスを Google Code から [GitHub](https://github.com/ardito-jp/sastruts-extension) に移行しました。(2014/07/07)  



# Overview

+ Action Proxying  
認証や認可といった事前処理 (アクセス制御) や事後処理の仕組みを「アノテーション・ベースで」提供します。  
ActionProxy を実装したプロキシをAction のクラスやメソッドに対して @Proxy アノテーションで割り当てます。  

+ Action Field Protecting  
リクエストパラメーターによる Action フィールドの上書きを禁止するモードを提供します。  
SAStruts 標準仕様では  
  ActionForm を定義するとパラメータの保存対象は @ActionForm を用いて指定した ActionForm's field になります。  
  ActionForm を定義しないとパラメータの保存対象は Action's field になります。  
　後者の場合に意図しないパラメータによる上書きを防止できます。   



# Quickstart  

	<repositories>
		<repository><!-- for Seasar Products -->
			<id>seasar.org.repo</id>
			<url>http://maven.seasar.org/maven2</url>
		</repository>
		<repository><!-- for SAStruts Extension -->
			<id>ardito.publish.repo</id>
			<url>http://ardito-jp.github.io/ardito/maven/repo</url>
		</repository>
	</repositories>

	<dependencies>
	  ・
	  ・
		<dependency>
			<groupId>org.seasar.sastruts</groupId>
			<artifactId>sa-struts</artifactId>
			<version>1.0.4-sp9</version>
		</dependency>
		<dependency><!-- SAStruts Extension -->
			<groupId>jp.ardito.seasar</groupId>
			<artifactId>sastruts-extension</artifactId>
			<version>1.0.0</version>
		</dependency>
	</dependencies>  
  
  


# License  

Copyright 2009-2014 [Ardito Co.,Ltd.](http://www.ardito.jp/) and the Others.  

Licensed under [the Apache License, Version 2.0](http://www.apache.org/licenses/LICENSE-2.0).  



# About Us  

y_nihonyanagi@ardito.jp  
  

