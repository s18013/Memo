# XCode

## [Preview](https://developer.apple.com/documentation/swiftui/previews-in-xcode)
xcodeのプレビューマクロを使うことで、変更をリアルタイムで確認できる
```swift
struct MyView: View {
    var body: some View {
        Text("Hello")
    }
}

//　プレビューを表示
#Preview("MyView") {
    MyView()
}

// 複数ある場合は以下のように、プレビュー名に別名をつけて管理できる
#Preview("MyView2") {
    MyView()
        .background(Color.red)
}
```
