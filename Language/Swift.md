# Swift

## 基本的な文法
### protocol
swiftにおけるinterfaceのようなもの
```swift
protocol ProtocolName {
    // setter/getterの宣言も可能
    var name: String { get set }
    // Itemの型をここでは決めず、実装するクラスで決める
    associatedtype Item

    func method() -> String
}

struct StructName: ProtocolName {
    var name: String = "name"
    // Itemの型をIntで決める
    typealias Item = Int

    func method() -> String {
        return "method"
    }
}

// extensionでの実装も可能
extension StructName {
    // methodの拡張
    func method2() -> String {
        return "method2"
    }
}
```

### key-path式
プロパティにアクセスするための式、プロパティの値を取得するだけでなく、値を設定することもできる。
また以下のようにプロパティの呼び出しをハードコーディングすることを防ぐことができる

```swift
struct User {
    var name: String
    var age: Int
}

var user = User(name: "John", age: 20)

// keyPathを使ってプロパティにアクセス
// 今後プロパティ名が変わっても、このkeyPathを使えば変更箇所が少なくなる
let nameKeyPath = \User.name

if user.age > 18 {
    user[keyPath: nameKeyPath] = "Jane"
}else {
    user[keyPath: nameKeyPath] = "John"
}

```

### extension
既存のクラスや構造体を継承した新しいクラスを作成することなく
既存のクラスや構造体に新しい機能を追加することができる


### Attribute
|attribute名|説明|
|-|-|
|@inlinable|関数をインラインとして扱うようにできる。インラインとは、関数を呼び出すのではなく呼び出し元の処理の埋め込むこと|
|@frozen|構造体・enumにメンバを追加することを禁止する|
|@State|これをつけたプロパティがSwiftUIの管理下に置かれ、値が変更されたときにビューを更新する。ローカルな状態を保持するのに向いている|
|@Binding|呼び出し元のプロパティに接続しその状態を共有する、＠Stateとセットで使われることが多い|
|[@Environment](https://developer.apple.com/documentation/swiftui/environment)|ダークモード、フォントなどのSwiftUIに事前に登録されている環境変数の読み込みに使用する|
|@EnvironmentObject|アプリ全体で共有されるカスタムオブジェクトの呼び出しに使用される|
|@MainActor|定義することでメインスレッドで実行されることが保障される、メソッド、プロパティ、クラス、構造体に適応することができる。適応したコードは、非同期で呼び出す必要があり、理由としてはメインスレッドがプロックされるのを防ぐため|

#### Attributeの使い方
```swift
**@ObservedObjectと@Published**</br>
複数のViewで共有の必要のあるデータを管理するために使用される
```swift
class User: ObservableObject {
    @Published var name: String
}

struct ContentView: View {
    @ObservedObject var user: User

    var body: some View {
        Text("Hello, \(user.name)!")
    }
}
```

**@ObservedObjectと@EnvironmentObject**</br>
アプリ全体で共有されるカスタムオブジェクトの呼び出しに使用される</br>
カスタムオブジェクトの初期呼び出しはアプリケーションのルートにて、.environmentObject()で設定する
```swift
struct ContentView: View {
    @EnvironmentObject var user: User

    var body: some View {
        Text("Hello, \(user.name)!")
    }
}

@main
struct MyApp: App {
    var body: some Scene {
        WindowGroup {
            ContentView()
                .environmentObject(User())
        }
    }
}
```

### インスタンスメソッド
|method名|説明|
|-|-|
|.font|フォントのタイプを設定できる、.titleで大文字など|
|.frame|width:10などで明示的にテキスト表示の際の、表示位置を設定できる|
|.lineLimit|表示するテキストを何行まで表示するか設定します|
|.sheet|モーダルを作成することができる、引数のisPresentedにtrueを設定することで表示される</br>引数をitemで指定した場合はそのアイテムが存在した場合表示|


## standard library
### View

画面表示の入れ物となる</br>
ビューの更新はすべてメインスレッドで行われる
```swift
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

### [Button](https://developer.apple.com/documentation/swiftui/button)
ボタンを表示するビュー
```swift
Button(action: {
    // ボタンが押された時の処理
}) {
    // ボタンに表示するテキスト
    Text("Button")
}

```

### [Group](https://developer.apple.com/documentation/swiftui/group)
viewのグループ化を行うビュー、VStackのように要素に影響を与えることなくビューをまとめることができる

### [Text](https://developer.apple.com/documentation/swiftui/text)　
読み取り専用のテキストを表示する組込みビュー


### [Image](https://developer.apple.com/documentation/swiftui/image)
画像を表示する組込みビュー
|method名|説明|
|-|-|
|.resizable|画面に合わせて大きさを調整できる状態にします|

### [Spacer](https://developer.apple.com/documentation/swiftui/spacer)
親ビューの範囲内で他のビューが使用していない範囲めいいっぱいにスペースを取ります
スペースの方向は、親ビューの方向に合わせます(HStackの場合は水平、VStackの場合は垂直など)

### [Divider](https://developer.apple.com/documentation/swiftui/divider)
Viewの間に線を引くことができるビュー

### [Task](https://developer.apple.com/documentation/swift/task)
非同期処理を行うためのもの

### [NavigationLink](https://developer.apple.com/documentation/swiftui/navigationlink)
画面遷移を行うためのビュー、これ単体で使うと遷移しないのでNavigationStackと組み合わせて使う
```swift
// 公式ドキュメントの例----------
NavigationLink {
    // 遷移先のビュー
    MyView()
}label: {
    // 画面遷移を行うビューのリンク文字列
    Text("Next")
}
// ---------------------------

// 以下のようにも書ける
NavigationLink(destination: MyView()) {
    // 画面遷移を行うビューのリンク文字列
    Text("Next")
}

import SwiftUI

struct MenuView: View {
    var body: some View {
        Text("Menu")
        NavigationStack {
            VStack(alignment: .center){
                NavigationLink(destination: LabelView(label_id: 1)) {
                    Text("Home")
                }
            }
        }
        .frame(width: 250)
    }
}
```

### [NavigetionStack](https://developer.apple.com/documentation/swiftui/navigationstack)
基底となるビューの上に新しいビューを表示するために使用されるビュー</br>
NavigationViewから移行された

### [CGFloat](https://developer.apple.com/documentation/coregraphics/cgfloat)
数値を座標指定用の型に変換するためのもの

### [dismiss](https://developer.apple.com/documentation/swiftui/environmentvalues/dismiss)
dismissを呼び出したビューを閉じるためのもの、呼び出し方は以下の通り
```swift
struct ContentView: View {
    @Environment(\.dismiss) private var dismiss

    var body: some View {
        Button("Dismiss") {
            dismiss()
        }
    }
}
```


## プロトコル

### Codable
エンコード、デコードを行うためのプロトコル
以下二つのプロトコルのどちらにも対応できる

### CaseIterable
列挙型(enum)のすべてのケースをリストとして取得することができるようになる

### Identifiable
識別子(Id)を持つことが保障されるプロトコル
```swift
// 以下のようなケースの場合、List内の要素を識別できずにエラーとなる
struct ContentView: View {
    let animals = ["犬","猫","猿"]
    var body: some View {
        List(animals){ animal in
            Text(animal)
         }
     }
 }

// 以下のようにIdentifiableプロトコルを使用することで、エラーを回避できる
struct ContentView: View {
    let animals = [Animal(id: 1, name: "犬"), Animal(id: 2, name: "猫"), 
       Animal(id: 3, name: "猿")]
    var body: some View {
        List(animals){ animal in
            Text(animal.name)
        }
    }
}

struct Animal: Identifiable {
    let id: Int
    var name: String
}
```

### [ObservableObject](https://developer.apple.com/documentation/combine/observableobject)
このプロトコルに準拠したクラス内の@Publishedプロパティが変更されたときに、</br>
自動的にビューが更新されるようになる

```swift
class User: ObservableObject {
    @Published var name: String

    init(name: String) {
        self.name = name
    }
}

struct ContentView: View {
    // @Stateと似たようなもの
    @ObservedObject var user: User

    var body: some View {
        Text("Hello, \(user.name)!")
    }
}
```

## タイプ
### Some
プロパティやメソッドの返り値の型を抽象化する(Opaque Result Type)のに用いられる

### Opaque Result Type
パフォーマンスを保ちつつ、真の型を隠蔽する新機能


## Error resolution

### @Codable: で発生したエラー
`原因`</br>
structデフォルトのイニシャライザが呼ばれその際に、</br>
値が設定されていないためにエラーが発生している

`解決策`</br>
値をnil許容にする 
```
Missing argument for parameter 'from' in call
'init(from:)' declared here
```

### Linker command failed with exit code 1 (use -v to see invocation)
`原因`</br>
ビルド対象のパッケージに含まれていないことが原因</br>
(明確に断言できないので、ビルド関連などを確認する)

`解決策`</br>
Xcodeの右側サイドメニューのファイルアイコンをクリックし、</br>
Target Membershipにチェックを入れる(アプリ、テストどちらにも使用するものは)


