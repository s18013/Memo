# Swift

## standard library

### View
画面表示の入れ物となる
```
使用例

struct MyView: View {
    var body: some View {
        Text("Hello")
    }
}

```

### VStack, HStack, ZStack
それぞれ以下の方向にビューを表示します</br>
VStack: Vertical(垂直)</br>
HStack: Horizontal(水平)</br>
ZStack: Z-axis(z軸方向、重ねる表示)


### [Text](https://developer.apple.com/documentation/swiftui/text)　
読み取り専用のテキストを表示する組込みビュー
|method名|説明|
|-|-|
|.font|フォントのタイプを設定できる、.titleで大文字など|
|.frame|width:10などで明示的にテキスト表示の際の、表示位置を設定できる|
|.lineLimit|表示するテキストを何行まで表示するか設定します|

### [Image](https://developer.apple.com/documentation/swiftui/image)
画像を表示する組込みビュー
|method名|説明|
|-|-|
|.resizable|画面に合わせて大きさを調整できる状態にします|

## Protocl

### Codable
エンコード、デコードを行うためのプロトコル
以下二つのプロトコルのどちらにも対応できる



## Some
プロパティやメソッドの返り値の型を抽象化する(Opaque Result Type)のに用いられる
https://techblog.recochoku.jp/7754

## Opaque Result Type
パフォーマンスを保ちつつ、真の型を隠蔽する新機能

## PreviewProvider
Xcode上のプレビュー画面専用の表示

## Annotation
- `@State` : プロパティの値が変更されたときにビューを更新する


## Error resolution

### @Codable: で発生したエラー
`原因`:structデフォルトのイニシャライザが呼ばれその際に、</br>
値が設定されていないためにエラーが発生している

`解決策`: 値をnil許容にする 
```
Missing argument for parameter 'from' in call
'init(from:)' declared here
```




