# Python

## Class
- selfとはそのクラス自身を指すもの
- クラス内の関数には、selfが引数として渡される</br>
  なので、クラス内の関数にはselfを引数として受け取る様に作る必要がある。</br>
  そうしなかった場合、以下のように引数の違うんだけどで怒られる</br>
  takes 0 positional arguments but 1 was given

## Magic Method
### **\_\_init__**
- コンストラクター

### **\_\_call__**
- クラスをメソッドの様に使用できる様にするためのもの
- こうすることでクラスインスタンスを呼び出し可能オブジェクト(callable)にすることができる
```
class Sample():
  def __call__(self, str):
    print(str)

Sample("Hello")
-> Hello
```

### **\_\_aiter__**
- 非同期イテレータオブジェクト(このメソッドが定義されているクラス自身)を返す
- このメソッドが定義されているクラスは、async for文で使用できる

### **\_\_anext__**
- 非同期イテレータオブジェクトから次の要素を取得するためのメソッド
- このメソッドが定義されているクラスは、async for文で使用できる



## 組込み関数
### @lru_cache
- メモ化を行うデコレーター
- メモ化とは、関数の結果をキャッシュしておき、同じ引数で呼び出された場合はキャッシュから結果を返すことで高速化を図る手法

### inspect
object(モジュール、クラス、メソッドなど)の情報を取得するためのライブラリ</br>
詳細は[こちら](https://docs.python.org/ja/3/library/inspect.html)

### str
#### str.isidentifier()
- 

## pip
- 以下のコマンドでパッケージの機能をまとめてインストール可能
```
元環境のパッケージエクスポート
pip freeze > requirements.txt

インポート
pip install -r requirements.txt

requirements.txtは以下のような形式
sniffio==1.3.0
SQLAlchemy==2.0.4
starlette==0.25.0
```

## Pytest
---

</br>

## Error
---

</br>

### UnboundLocalError
- 関数内でローカル変数が宣言される前にプログラムによって参照されたことが原因

### 可変長引数
- "**" 引数でもらった内容を一つの辞書データとしてまとめる
```
def create_dict(**args):
  print(args)

create_dict(first="A", second="B", third="C")
-> {"first":"A", "second":"B", "third":"C")
```

</br>

## CPython
---
- C言語を使って書かれたPythonのことで、一般的にPythonと呼ばれるものはこのCPythonのことを指す

## Comment
---
- Pythonのライブラリを見る限り以下のformatで作成されていた
```
def sample(arg1, arg2):
  """この関数の目的
  Args:
      arg1 (type): 引数の説明
      arg2 (type): 辞書型の場合は以下のようにする
          default = {
            'a': 値
            'b': 値
          }

  Returns:
      type: returnする値の説明

  Raise:
      error: オリジナルで例外を定義していた場合にどういった時に上がるものかを説明
  
  Example:
      この関数の使用例
  """ 
```


## Typing
---
### **Callable**
- 呼び出し可能なオブジェクト(関数、クラスなど)を表してる
```
# Callable[[引数の型], 戻り値の型]
def apply_operation(operation: Callable[[int, int], int], a: int, b: int) -> int:
    return operation(a, b)

def add(x: int, y: int) -> int:
    return x + y

result1 = apply_operation(add, 3, 4)  # add関数を呼び出して結果を取得
```
</br>

## \_\_init__.py

### 役割
- パッケージとして認識: \_\_init__.pyが存在するフォルダはパッケージとして認識される(python3.3からなくても認識されるようになった)
- パッケージの初期化: パッケージ内の共通の初期化処理の定義
- インポートの制御: パッケージ内のモジュールの公開・非公開・名前のカスタマイズなど
- パッケージ全体の設定: パッケージ全体で使用されるグローバル変数や定数を定義し、初期設定を読み込む

[参考](https://ya6mablog.com/how-to-use-init-py/)


## async/await

### coroutines
- 明確に定義されていないので、asyncで定義された中断可能な処理と考えておけば良いはず

### 基本的な実装例
- 基本的に以下のように非同期処理(a())を取りまとめるマッパー(main())
  を用意してasyncio.runなどで実行していく感じ
```
async def a():
  # b(): ioバウンドな処理
  result = awaite b()
  return result

async def main():
  # a()のを同時に３つ呼び出す処理 
  result = asyncio.gather(a(), a(), a())
  return result
  
if __name__=="__main__":
  asyncio.run(main())
```

```
古い定義として以下もある
@asyncio.coroutine
def a():
```

### async with
- 定義のある関数を呼び出す際にasyncio.runしなくとも実行される
```
async with aiohttp.ClientSession(headers=headers) as session:
  async with session.get(url) as response:
    return await response.text()
```

