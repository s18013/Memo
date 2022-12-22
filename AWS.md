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