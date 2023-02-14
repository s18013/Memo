# **Oracle**

# **DDL**

# **DML**

## **SELECT**
データの取り出しを行う</br>
取り出しかたは大きく分けて「射影」、「選択」、「結合」の３つ
 - 射影 : 検索対象の表から特定の列を取り出すこと 
 - 選択 : 検索対象の表から特定の行を取り出すこと
 - 結合 : 複数の表関連付けて取り出すこと

 ```
 SELECT A, B, C -> ここで選択した順番で表示(*の場合は表に定義されている順番)
 FROM table
 ```

 ## **FOR UPDATE**
行レベルでデータが更新・削除されることを防ぐ
```
SELECT ~ FROM table 
WHERE ~
FOR UPDATE
```
## **DESCRIBE**
表の列名の一覧が取得できる
```
DESCRIBE table;
```

## **TRIM**
空白文字の削除

```
SELECT TRIM(string) FROM table
```

## **DISTINCT**
重複の削除

```
SELECT TRIME(col_name) FROM table
```


## **SUBSTR**
特定の文字を切り出す
```
SELECT SUBSTR(string, int(開始位置), int(終了位置,default最後まで))

```

# Deta Type
余計なデータが入らないようにするために定義する

</br>

## **VARCHAR2**
sizeに指定したバイトまでの</br>
可変長の文字を格納することができる</br>
```
VARCHAR(size)
```

## **CHAR**
sizeに指定したバイトまでの</br>
固定長の文字列を格納することが出来る</br>
(sizeに10を格納し、3バイトの文字列を格納した場合、残りは"_"で穴埋めされる)
```
CHAR(size)
```


## **NUMBER**
sizeに指定した桁までの</br>
数値を格納することが出来る、カンマ区切りで小数点以下も設定可能
```
NUMBER(size, size)
```

## **DATE**
日付を格納することが出来る
```
DATE
```