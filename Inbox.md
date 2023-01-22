# System

## file

```
  ls -l
      1   2   3   4  5   6    7    8        9       10
      d  rwx rwx rwt 2 root root 4096 May 14 04:36 tmp/
    
        1.  ファイルタイプ
          - : ファイル
          d : フォルダ
          l : シンボリックリンク

        2.  所有者(6)の権限
          r : 読み込み
          w : 書き込み
          x : 実行
          - : 権限なし

        3. グループ(7)の権限
        4. そのほかユーザーの権限
        5.  ハードリンク(ファイル名とinode番号のつながり)
        6.  所有しているユーザー
        7.  所有しているグループ
        8.  容量 (byte)
        9.  タイムスタンプ
        10. ファイル名
```

## directory

# Network
                                              WWW        
                                               |
                                              / \   

## command
    ping: 設定したIPアドレスにデータを送って疎通できたか確認するためのもの
          ping [IPアドレス or ホスト名]
         
## vpn
    local ---> vpn server ---> Internet
    local <--- vpn server <--- Internet

## ttl
    Time to Live の略で有効期限(回数)を定義、機器を経由するたびに回数が１減る
    情報が永遠にさまよわないように定義
    DNSサーバーの場合は、IPアドレス = ドメイン名のリストを保存する時間

## file server
   ファイル共有機能に特化したサーバでオンプレミス・クラウド版がある
   ファイルの共有・保存・バックアップなどが目的
   Port: 基本443
   構築の流れ

# Docker

## command
    コンテナ作成  : docker run -d -it --name fedora1 -p 8080:80 fedora:latest
    コンテナ起動  : docker start
    環境に入る    : docker exec -it [コンナID] /bin/bash
    環境抜ける    : exit(終了してしまうので注意)
                   Ctrl + p Ctrl + q(こちらは終了しないで抜けれる)

# YAML
    title : test
    ip: &ip 127.0.0.1    -> & は参照元を定義
    debug:               -> 入れ子でかける
      flag: true
      port: 80
    version: "1.0"
    test_ip: *ip         -> * は&で指定した参照先を指定できる(CloudFormationだと!Ref ip のように指定する)
    env_ariables:
      - A                -> - はリストを表す
      - B
      - C
    bash: |              -> | 1行ごとに改行が入るように指定できる(|-で核と最後の行は開業されない)
      #!/bin/bash
      echo "Hello"
      echo "World"

    Type: AWS::EC2::VPC  -> AWSのCloudFormationではリソースを定義する必要がある   
    

# Todoist

## Comand

### get complate task
    curl https://api.todoist.com/sync/v9/completed/get_all -H "Authorization: Bearer API TOKEN"

### get projects
     curl -X GET \\n  https://api.todoist.com/rest/v2/projects -H "Authorization: Bearer API TOKEN"

# 

# Merge
## 定義
- ルールに従って結合(1対1のイメージ)・統合(1対複数のイメージ)するの意味
- パージ(Purge 削除・切り離し)との対義語

## 使用例
- gitの場合だと、マージしたい親ブランチ(masterとか)に移動後、git merge BRANCH NAMEで取り込まれる
- SVNだとtrunk(ブランチの大本)に統合するのに使われ、右クリックからのメニューで出来る

## Pros & Cons
 - 調べてもGitの内容しか出てこないため、Gitの内容をまとめる時に纏める


# サンドボックス
プログラムやファイルを安全に実行するために、隔離した空間のこと(SVNだったらローカルに落としてくるとか)