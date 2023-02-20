# Docker

restart: osascript -e 'quit app "Docker"'

## Command

|コマンド|概要|
|----|------|
|docker run --name ubuntu_java -v /Users/ryunosuke/app/java:/java -it amd64/ubuntu /bin/bash|ホストOSのディレクトリをマウントしながら起動|



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