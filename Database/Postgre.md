# PostgreSQL

## Command

    Start : brew services start postgresql@14
    Stop  : brew services stop postgresql@14
    Login : psql postgres
            以下詳細(省略可能)
            psql -h ホスト名(ipアドレス) -p ポート番号 -U ユーザー名 -d データベース名

## Docker
### コンテナの作成
```
直起動バージョン
docker run --name some-postgres -e POSTGRES_PASSWORD=mysecretpassword -d arm64v8/postgres

docker-compose.yaml 使用バージョン
composeディレクトリにて
docker-compose -d up
docker-compose down
```
### コンテナ内でPostgresに接続
```
su postgres -> ルートユーザだとアクセス出来ないのでユーザ切り替え
psql        -> 接続
```