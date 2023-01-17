# PostgreSQL

## Command

    Start : brew services start postgresql@14
    Stop  : brew services stop postgresql@14
    Login : psql postgres
            以下詳細(省略可能)
            psql -h ホスト名(ipアドレス) -p ポート番号 -U ユーザー名 -d データベース名

    Docker
    start  : docker run --name some-postgres -e POSTGRES_PASSWORD=mysecretpassword -d arm64v8/postgres