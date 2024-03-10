# Docker

restart: osascript -e 'quit app "Docker"'

## Command

|コマンド|概要|
|----|------|
|docker run --name ubuntu_java -v /Users/ryunosuke/app/java:/java -it amd64/ubuntu /bin/bash|ホストOSのディレクトリをマウントしながら起動|
|docker inspect postgres\|grep IPAddress|コンテナに割り当てられているIPアドレスが確認できる|


## docker-compose
- docker-compose build でイメージの作成
-  docker-compose up -d で起動(イメージ作らなくても起動可能、-dはバックグラウンドの起動のこと)

|yaml|概要|
|----|------|
|extra_host|ホスト名のマッピングを行う、DNSの様なもの|
|depends_on|依存性の定義、起動の順番を定義できる|
|tty|コンテナを起動しっぱなしにできる、テレタイプライターの略|

## command
    コンテナ作成  : docker run -d -it --name fedora1 -p 8080:80 fedora:latest
    コンテナ起動  : docker start
    環境に入る    : docker exec -it [コンナID] /bin/bash     /bin/bashは対話形式のbashシェル起動
    環境抜ける    : exit(終了してしまうので注意)
                   Ctrl + p Ctrl + q(こちらは終了しないで抜けれる)




## Docker Swqrm
    オートレーションサービスで、名前の通り複数のクラスターに対して操作できるもの

## Docker Compose 
YAMLで定義された複数のコンテナを定義し実行するもの、複数起動したい時に便利   
Dockerfileとの違いは、  
Dockerfile -> イメージの作成(どちらかと言うと一つ起動するのに向いてる)  
Docker Compose -> 複数のコンテナ起動・停止

|    |      |
|----|------|
|docker-compose up -d|Start|
|docker-compose stop| Stop|  
|docker-compose down|Close|

> **Warning**  
上記のコマンド以外で終了させると、docker networkに不具合が出るため  
以下のコマンドで作り直す必要あり
docker-compose up --force-recreate