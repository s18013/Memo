# System

## file
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


# Docker

## command
    コンテナ作成: docker run -d -it --name fedora1 -p 8080:80 fedora:latest
    コンテナ起動: docker start
    環境に入る: docker exec -it [コンナID] /bin/bash
    環境抜ける: exit(終了してしまうので注意)
               Ctrl + p Ctrl + q(こちらは終了しないで抜けれる)


# AWS

## EC2

## ECS

## ECR

##