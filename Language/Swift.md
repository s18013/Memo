# Swift

## Some
プロパティやメソッドの返り値の型を抽象化する(Opaque Result Type)のに用いられる
https://techblog.recochoku.jp/7754

## Opaque Result Type
パフォーマンスを保ちつつ、真の型を隠蔽する新機能

## View
カスタムビューの作成ができる

## PreviewProvider
Xcode上のプレビュー画面専用の表示

## Annotation
- `@State` : プロパティの値が変更されたときにビューを更新する

## Codable
エンコード、デコードを行うためのプロトコル


## Error resolution

### @Codable: で発生したエラー
`原因`:structデフォルトのイニシャライザが呼ばれその際に、</br>
値が設定されていないためにエラーが発生している

`解決策`: 
```
Missing argument for parameter 'from' in call
'init(from:)' declared here
```




