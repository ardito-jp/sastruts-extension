# What is SAStruts Extension ?  

A Few, Useful Extensions for [SAStruts](http://sastruts.seasar.org/) (Seasar.Org)  
  

[SAStruts](http://sastruts.seasar.org/) (Seasar.Org) の拡張コンポーネントです。  
といっても大袈裟な機能はありません。  
SAStruts で開発する際の良い意味で「おまけ的な存在」でありたいと思います。(2009/07/31)  



# Postscript  

Seasar および SAStruts の機能開発 (進化) も止まりましたが、  
引き続き、SAStruts と SAStruts Extension を利用してくださっている方もいらっしゃるので、  
細々とメンテナンスを続けていきます。  
ホスティングサービスを Google Code から [GitHub](https://github.com/ardito-jp/sastruts-extension) に移行しました。(2014/07/07)  



# Overview

+ Action Proxying  
　Aciton クラスに対して「アノテーション・ベースでフィルタリング機能」提供します。  
ActionProxy を実装するプロキシクラスを準備して、  
Action のクラスやメソッドに対して @Proxy アノテーションによりプロキシクラスを割り当てます。  
　全ての Action に対してデフォルトで適用するプロキシクラスを指定することも可能です。(=デフォルト設定)  
さらにデフォルト設定を Action 個別でオーバーライドすることも出来ます。    

+ Action Field Protecting  
リクエストパラメータによる Action フィールドの上書きを禁止する仕組みを提供します。  
SAStruts 標準仕様では、  
　　@ActionForm を定義すると パラメータの保存対象は @ActionForm に指定した ActionForm のフィールドになりますが、  
　　@ActionForm を定義しないと パラメータの保存対象は Action のフィールドになります。  
後者の場合に対して意図しないパラメータによる上書き (や上書きによる例外発生) を防止します。  



# Maven  

``` xml
  <repositories>
    <repository><!-- for Seasar Products -->
      <id>seasar.org.repo</id>
      <url>http://maven.seasar.org/maven2</url>
    </repository>
    <repository><!-- for SAStruts Extension -->
      <id>ardito.published.repo</id>
      <url>http://ardito-jp.github.io/maven/repo</url>
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
      <version>1.0.1</version>
    </dependency>
  </dependencies>  
```

  

# Getting Started  

ご利用方法やクイックスタート・アプリケーションを Wiki で公開しています。  

[SAStruts Extension - Wiki](https://github.com/ardito-jp/sastruts-extension/wiki)
  



# License  

Copyright 2009-2014 [Ardito Co.,Ltd.](http://www.ardito.jp/) and the Others.  

Licensed under [the Apache License, Version 2.0](http://www.apache.org/licenses/LICENSE-2.0).  



# About  

oss@ardito.jp  

  

