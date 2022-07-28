# System

## file
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

## directory

# Network
                                              WWW        
                                               |
                                              / \   

## command
    ping: 設定したIPアドレスにデータを送って疎通できたか確認するためのもの
          ping [IPアドレス or ホスト名]
                1                 2                                         3        4        5  
            64 bytes from kix07s03-in-f14.1e100.net (172.217.161.206): icmp_seq=1 ttl=52 time=34.3 ms
         
          1. 
## vpn
    local ---> vpn server ---> Internet
    local <--- vpn server <--- Internet

## ttl
    Time to Live の略で有効期限(回数)を定義、機器を経由するたびに回数が１減る
    情報が永遠にさまよわないように定義
    DNSサーバーの場合は、IPアドレス = ドメイン名のリストを保存する時間

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
    



# AWS

## EC2
### インスタンス作成
    vpc:
      Virtual Private Cloud 
      以下の表のIGWを含むものすべてをさす
      https://docs.aws.amazon.com/ja_jp/vpc/latest/userguide/how-it-works.html

                        internet
                           |
             vpc           v 
             +-----------|IGW|------------+
             |             |              |
             |           Roter            |
             |           /   \            |
             |  ---Subnet--  ---Subnet--  |
             |  | --EC2-- |  | --EC2-- |  |
             |  | |     | |  | |     | |  |            
             |  | |     | |  | |     | |  |
             |  | ------- |  | ------- |  |
             |  -----------  -----------  |
             +----------------------------+

    Subnet:
      IPアドレスの範囲 /16 ～ /28 で作成可能

             Subnet(192.168.1.0/24)
             +-----------Roter(192.168.1.1)---------+
             |                  |                   |
             |                 / \                  |
             |  ----EC2---------  ----EC2---------  |
             |  |192.168.1.55  |  |192.168.1.112 |  |
             |  |              |  |              |  |            
             |  |              |  |              |  |
             |  |              |  |              |  |
             |  ----------------  ----------------  |
             +--------------------------------------+

             以下のアドレスはAWSに予約されている
               10.0.0.0:   ネットワークアドレス
               10.0.0.1:   VPC ルーター用
               10.0.0.3:   将来利用
               10.0.0.255: ネットワークブロードキャストアドレス
 
    Security group:
      ファイアウォール
      https://docs.aws.amazon.com/ja_jp/vpc/latest/userguide/VPC_SecurityGroups.html 
                       
    ストレージ:
      EC2で使用しているデータを保存する所
      https://docs.aws.amazon.com/ja_jp/AWSEC2/latest/UserGuide/Storage.html?icmpid=docs_ec2_console
      EBS:
        Amazon Elastic BlockStore
        IOPS: Input/Output Per Second

    高度な設定:
      DNS ホスト名:
        Aレコードとは、DNSで定義されるそのドメインについての情報の種類の一つで、
        特定のホスト名に対応するIPアドレス（IPv4アドレス）を定義するもの。 
        IPv6の場合は「AAAAレコード」を用いる。
      https://docs.aws.amazon.com/ja_jp/AWSEC2/latest/UserGuide/ec2-instance-naming.html

      Stop - Hibernate behavior
        https://aws.amazon.com/jp/blogs/news/new-hibernate-your-ec2-instances/
      
## S3

## IAM
  AWS Identity and Access Management (IAM) は、AWS リソースへのアクセスを安全に管理するためのウェブサービスです。IAM により、誰を認証 (サインイン) し、誰にリソースの使用を承認する (アクセス権限を持たせる) かを制御します。
  https://docs.aws.amazon.com/ja_jp/IAM/latest/UserGuide/introduction.html#intro-features

## CodePipeline

## CloudFormation

## EBS

## ECS

## ECR

## KMS