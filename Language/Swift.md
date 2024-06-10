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

### struct
構造体、クラスと同じようにプロパティやメソッドを持つことができる
Viewも構造体で定義される
```swift
struct StructName {
    var name: String
    var age: Int

    // 読み取り専用のプロパティ
    private(set) var address: String

    // 同一ファイル内読み取り専用のプロパティ
    fileprivate(set) var tel: String

    func method() -> String {
        return "method"
    }
}
// 以下のようにする事でStructNameのメタタイプ(型の情報を表す型)を取得することができる
let structType: StructName.Type = StructName.self
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
```swift
func fetchData() throws -> Data {
    // データ取得処理
}

// 以下のようにgetterに使用することもできる
var data: Data {
    get async throws {
        try await fetchData()
    }
}

func fetchData(url: String) async throws -> Data {
    // データ取得処理
}

// 以下のように例外をパスすることもできる
func preFetchData(_ fetchData: (String) throws -> Data) rethrows {
    try fetchData("/test")
}
```

### subscript
クラス、構造体、リストに配列や辞書のように添え字を使ってアクセスすることができる
```swift
// UserクラスにSubscriptを追加し辞書のようにアクセスできるようにする
struct User {
    private var params: [String: Any] = [:]

    subscript(key: String) -> Any? {
        get {
            return params[key]
        }
        set(newValue) {
            params[key] = newValue
        }
    }
}

va user = User()
user["name"] = "John"
print(user["name"] ?? "Unknown") // John

```

### lazy
遅延変数と呼ばれ、これで定義された変数にアクセスが会った時に指定した関数を使用し生成される</br>
呼ばれるまでは生成されないため、メモリの節約になる
```swift
lazy var name: String = {
    let result = 2000 * 2000
    return result
}
```

### ラベルの省略
以下のように"_"を使用することでラベルを省略することができる
```swift

func method(_ name: String) {
    print(name)
}

method("John")
// なしの場合はmethod(name: "John")となる
```

### getter/setter
プロパティの値を取得するためのメソッド(getter)、プロパティの値を設定するためのメソッド(setter)を定義することができる
```swift
class User {
    private var name: String = "John"

    var getName: String {
        get {
            return name
        }
        set(newValue) {
            name = newValue
        }
        // 以下のように省略での定義も可能
        set {
            name = newValue
        }
    }
}
```

### actor
並列処理で呼び出されるようなメソッドをクラスのように保持し、</br>
呼び出しを順番で行うように制御してくれるため競合を防ぐことができる</br>
actorの呼び出しはawaitを使用することで行う

```swift
actor MyActor {
    var name: String = "John"

    func changeName(newName: String) {
        name = newName
    }

    func getName() -> String {
        return name
    }
}

let actor = MyActor()

Task {
    await actor.changeName(newName: "Jane")
    let currentName = await actor.getName()
    print(currentName) // Jane
}

```

### typealias
型に別名をつけることができる

```swift
typealias Name = String
let name: Name = "John"

// クロージャにも使用できる
typealias CompletionHandler = (String, Int) -> Void
func performTask(completion: CompletionHandler) {
    completion("Task completed", 200)
}
performTask { message, code in
    print("\(message) with code \(code)")
}

// ジェネリクスにも使用できる
typealias Item<Value> = Dictionary<String, Value>
let item: Item<Int> = ["name": 3, "age": 20]

// ネストされた型を簡素化することもできる
struct User {
    struct Job {
        var name: String
    }
}
typealias Job = User.Job
let job = Job(name: "Engineer")
```

### guard
条件が満たされない場合に処理を終了するための文
```swift
guard let name = name else {
    throw Error("name is nil")
}
```

### 範囲演算子
|演算子|説明|
|-|-|
|a...b|aからbまでの範囲|
|a..<b|aからbを含まないbまでの範囲(例1..<3 result: 1, 2)|


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

**Attributeの使い方**</br>
**@ObservedObjectと@Published**</br>
```swift
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
|millisecondsSince1970|JSONの日付けの値を1970年からのミリ秒にデコードする|


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

### [URLSession](https://developer.apple.com/documentation/foundation/urlsession)
httpリクエストを行うためのクラス</br>
URLSession.sharedはシングルトンで、アプリケーション全体で共有されることを表している</br>
```swift
// 書き方が以下のように２パターンある
func fetchData() {
    let url = URL(string: "https://example.com")!
    let task = URLSession.shared.dataTask(with: url) { data, response, error in
        if let data = data {
            print(data)
        }
    }
    task.resume()
}

func fetchData() async {
    let url = URL(string: "https://example.com")!
    let (data, _) = try await URLSession.shared.data(from: url)
    print(data)
}
```

### [NSCache](https://developer.apple.com/documentation/foundation/nscache)
key-valueペアでデータを保持するためのクラス、メモリ管理を行うために使用される

```swift
class DataCache {
    private let cache = NSCache<NSString, NSData>()

    private init() {}

    subscript(key: String) -> Data? {
        get {
            cache.object(forKey: key as NSString) as Data?
        }
        set {
            if let value = newValue {
                cache.setObject(value as NSData, forKey: key as NSString)
            } else {
                cache.removeObject(forKey: key as NSString)
            }
        }
    } 
}

let cache = DataCache()
cache["key"] = Data()
print(cache["key"] ?? "nil")
```

### [URLCache](https://developer.apple.com/documentation/foundation/urlcache)
httpリクエスト結果をキャッシュするためのクラス</br>
メモリとディスク両方にキャッシュすることができる</br>
HTTPヘッダーによるキャッシュ制御も可能</br>

キャッシュするポリシーは以下の通り
|ポリーシー名|説明|
|-|-|
|.useProtocolCachePolicy|デフォルトのポリシー、ヘッダーに設定されたポリシーに従う|
|.reloadIgnoringLocalCacheData|キャッシュを無視して常にネットワークから取ってくる|
|.returnCacheDataElseLoad|キャッシュがある場合はそれを返し、無い場合はネットワークからデータを取得|

```swift
// メモリキャッシュとディスクキャッシュのサイズを設定
let memoryCapacity = 20 * 1024 * 1024 // 20MB
let diskCapacity = 100 * 1024 * 1024 // 100MB
let cache = URLCache(memoryCapacity: memoryCapacity, diskCapacity: diskCapacity, diskPath: "myCache")

// デフォルトのURLCacheをカスタムキャッシュに置き換える
URLCache.shared = cache

guard let url = URL(string: "https://test.com/todos/1") else {
    print("Invalid URL")
    return
}

// URLRequestを作成し、キャッシュポリシーを設定
var request = URLRequest(url: url)
request.cachePolicy = .returnCacheDataElseLoad
```


## プロトコル

### decodable
JSONデータをデコードするためのプロトコル
```swift
struct User: Decodable {
    var name: String
    var age: Int

    // JSONデータからデコードする対象の要素を指定する
    enum CodingKeys: String, CodingKey {
        case name
        case age
    }

    // decodableプロトコルに準拠する場合は、イニシャライザでのデコーダーの指定が必要
    init(from decoder: Decoder) throws {
        let container = try decoder.container(keyedBy: CodingKeys.self)
        name = try container.decode(String.self, forKey: .name)
        age = try container.decode(Int.self, forKey: .age)
    }
}
```

### Codable
エンコード、デコードを行うためのプロトコル
以下二つのプロトコルのどちらにも対応できる

- **Encodable**: エンコードを行うためのプロトコル
- **Decodable**: デコードを行うためのプロトコル

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


## [ローカライズ](https://developer.apple.com/documentation/xcode/localizing-and-varying-text-with-a-string-catalog)
String Catalogを使用して、アプリ内のテキストをローカライズすることができる</br>
String Catalogは、用意しておけば勝手にTextを抽出してくれる</br>
デバック実行時の言語変更方法は、リンクから確認できる


## [フォーマット指定子](https://developer.apple.com/library/archive/documentation/Cocoa/Conceptual/Strings/Articles/formatSpecifiers.html)
"%@"などの特殊な文字列を使用して、文字列をフォーマットすることができる</br>
リンクにない指定子シリーズは以下の通り
|指定子|説明|
|-|-|
|%lld|Long LongのことでSwiftではInt64のこと|



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


