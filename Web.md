## **CORS（Cross-origin resource sharing）**
---
```
https://  www.gooogle.com  :443  /search ?q=youtube&xyz=22222
--------  ---------------  ----  ----------------------------
Protocol       Host        Port             Query

上記のProtocol, Host, Port, Queryを合わせたものをOriginという
```
- same-origin: originが３つ一緒
- cross-origin: originが一つでも違う
- CORSは異なるオリジン(cross-origin)間でのリソースの共有を制限するもの


## **JWT(Json Web Token)**
---
- json形式の情報を安全に送るための仕組み</br>

encode
  1. ヘッダ＝とペイローをbase64でエンコード
  1. ヘッダー.ペイロード.ヘッダとペイロードをRSA-SHA256などで暗号化した物 の形式で完成

decode
  1. ヘッダー.ペイロードをbasse64でデコード
  1. ヘッダとペイロードをRSA-SHA256したものを複合化しbase64でデコード
  1. 1と2 を比較して一致していればデコード成功