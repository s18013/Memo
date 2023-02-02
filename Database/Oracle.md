# **Oracle**

# **DDL**

# **DML**

## **SELECT**
データの取り出しを行う</br>
取り出しかたは大きく分けて「射影」、「選択」、「結合」の３つ
 - 射影 : 検索対象の表から特定の列を取り出すこと 
 - 選択 : 検索対象の表から特定の行を取り出すこと
 - 結合 : 複数の表関連付けて取り出すこと
 </br>
 ```
 ```

 ## **FOR UPDATE**
行レベルでデータが更新・削除されることを防ぐ
```
SELECT ~ FROM table 
WHERE ~
FOR UPDATE
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
