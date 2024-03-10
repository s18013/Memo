# **DML**

## ALTER TABLE
テーブルに対して変更を加えます</br>

外部キー削除、制約の削除とkeyの削除を行う必要がある
```
ALTER TABLE テーブル名 DROP FOREIGN KEY 制約名;
ALTER TABLE テーブル名 DROP 制約名;
```

# update
## idのフリなおし
```
SET @i := 0;
UPDATE `labels` SET id = (@i := @i +1);
ALTER TABLE `labels` AUTO_INCREMENT = 1;
```