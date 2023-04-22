# Python

## Class
- selfとはそのクラス自身を指すもの
- クラス内の関数には、selfが引数として渡される</br>
  なので、クラス内の関数にはselfを引数として受け取る様に作る必要がある。</br>
  そうしなかった場合、以下のように引数の違うんだけどで怒られる</br>
  takes 0 positional arguments but 1 was given

### **\_\_init__**
- コンストラクター

### **\_\_call__**
- クラスをメソッドの様に使用できる様にするためのもの
```
class Sample():
  def __call__(self, str):
    print(str)

Sample("Hello")
-> Hello
```
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

## Error

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