# **Java**


## **Class**
---
- javaのクラスは全てObjectクラスを親に持つ(extendsが定義されていない場合自動でObjectがextendsされる)
- 特定の機能をまとめた塊</br>
- ファイル名と一致している必要がある</br>
- この中に含まれる要素(メソッドなど)をメンバーと呼ぶ

### **Object**
- 以下のメソッドが定義されており、Overrideして使うこともできる

|   |   |
|---|---|
|Object clone()|objectのコピーの作成|
|String toString()|クラスのメンバ変数の紹介を目的とする|
|void finalize()|デストラクター、動作が保証されていないので基本使わない|
|boolean equals(Object obj)|オブジェクト同士が等しいか判別できる|
|Class<?> getClass()|どのクラスのインスタンスなのかを取得できる|

### **instanceof**
- 変数に格納されているオブジェクトの型が指定された型にキャスト可能か調べ、可能ならtrue
  ダウンキャストの際には必ず行う
```
if (変数　instanceof 型)
```

### **定義クラス**
- 以下のようにクラス内で再代入不可の値を定義しまとめたクラスのこと
```
public class Diffinition{
  public static final String BOOK_NAME = "ONE";
}
```

### **abstract**
- abstractクラスでしかabstractメソッドは定義できない
- @overrideして実装してやらないとインスタンス化できない
- 必ずoverrideする必要があるので中身を持たない
- javaではabstract関係なく多重継承できないので、したい場合はインターフェイスを使用する


### **interface**
- implementsで実装する、implements IHoge, IFugeのように複数定義可能
- 定義できるのはabstract, default, strictfp
```
public interface Sample{
  // 変数はpublic static final扱い
  String HELLO = "Hello"

  //　インターフェース内では以下でpublic扱い
  double getArea();    

  // defaultでデフォルト処理も作成可能、@Overrideで上書き可能
  default String hello(){
    return HELLO;
  }
}
```

### **不変クラス**
- コンストラクタ以外では外部から値を受け取らない
- setter など持たず内部の値を変更できない
- 不変クラスのコンストラクタに参照系の値(Listtとか)をそまま渡すと、渡し元の値に変更があった場合
  不変クラスの値にも変化が起き不変性が失われてしまうので注意が必要
　
### **コンストラクタ**
- クラス名と同名のパブリックメソッドで定義
- 継承で作られたクラスについては、親クラスのコンストラクタが引数なしの場合は暗黙的に呼び出されtる
  ありの場合は子クラスのコンストラクタでsuper(引数)で呼び出してやる必要がある

</br>



## **Method**
---
- 機能を表すもの<br>
- mainは特別なメソッドでアプリ起動した時探し出されて実行される
エントリーポイントとも呼ばれる<br>
- {}(ブロック)で囲われている
```
// 可変長引数の例、以下の様に一つ目は普通に定義してやると最低１つ以上の引数を表現できる
pubulic void hoge(int x, int... y)
```

### **ファクトリーメソッド**
- クラスの作成を目的としたクラス
- インスタンスを常に生成しなくて良くなり、好きな名前で呼び出すことができる(get~みたいな)

### **シングルトン**
- １度たけ生成されてそれを使い回す様にするデザインパターン、javaにおいては以下
  - クラス内でprivate staticでインスタンスを生成し保持する
  - コンストラクタをprivateで定義し呼び出されない様にする
  - public sataticで定義されたインスタンスを返すメソッドを定義する

### **Overload**
- 名前が同じで、引数の型・並びが違うメソッドを複数定義すること

### **関数の参照渡し**
```
public void walkArray(String value, Output output){
  output.execute(value);
}

# 以下のアノテーションをつけると参照私可能な関数が作れる(関数型インターフェース)
@FunctionalInterface
public interface Output{
  void execute(String str);
}
```

### **Lambda**
- 関数の参照渡しをするタイミングなどの短い利用しかない関数を
　いちいち定義するのがめんどくさくて生まれたもの

```
# Consumer はjavaのutilで定義されてる、引数なしの関数型インターフェース
public void hoge(String value, Consumer consumer){
  consumer.accept(value);
};

# ()内の型は省略可能、引数がない場合は()
hoge("hello", (value) -> {
  System.out.println(value);
});

# 別クラス内のメソッドを呼び出す場合
Other o = new Other();
hoge("hello", o::fuge);

```

### **Stream API**
**streamの生成**
```
ArrayList list = new ArrayList<String>();
list.stream();

HashMap map = new HashMap<String, String>();
map.entrySet().stream();
```
**中間処理**

**終端処理**


</br>



## **条件分岐**
---
- 条件に一致した最初のブロックのみ実行されるため、条件は範囲の狭い順でかく
- if分でelseのみ採用したい場合は、ifブロックに";"を記入する(";"は空を意味する)

### **switch**
- 以下の様な複数のケースをbreakしないまま処理することを**フォールスルー**という
  また以下の様な特殊なケースを除く場合は、フォールスルーを起こすべきでない
```
var val = "A"

switch(val) {
    case "A":
    case "B":
      System.out.println("XX");
      break;
    default "c":
      break;
}
```

</br>


## **繰り返し**
---
### **インクリメント演算子/デクリメント演算子**
- "++" インクリメント
- "--" デクリメント
```
var i = 3;
var j = ++i; -> iに1加算した後に代入
var j = i++; -> iに1加算する前に代入
```
### **for**
```
first:
for (int i = 0(初期化式); i < 6(継続条件式); i++(増減式)) {
    for(int j =0; i< 6; i++){
        break first; -> 単にbreakだけだとネストしたルーブの内側までしか抜け出せないため
                        外側のループにラベルをつけてやる必要がある
    }
}

拡張for
for (var value(仮変数): data(配列/コレクション)) {...}
```
<注意点>
- ループ内のオブジェクト生成はガベージコレクション頻発でオーバーヘッドになるため注意
- 例外処理などはそもそもオーバーヘッドがかかるものはループ内で処理しない
- for内で使用されているカウント関数はforがいからの参照不可
- 以下の様な外部の変数をカウンター変数として採用した場合</br>
  for文が外部の変数を参照できる様になってしまうので避ける
- カウンター変数に浮遊点小数を採用しない(インクリメントがうまくいかないため)

```
悪い例
double i;
for (i = 1; i < 6; i++) {...}
```

</br>

## **比較**
---
- a = b + cとなる時、"=","+"のことを演算子、それ以外の変数を非演算子(オペランド)という
- ショートカット演算子、"&&","||"の様に"&","|"を二つ並べることで左辺の変数が</br>
  条件を満たしていた場合右辺をスキップする</br>
  ```
  例　value != null && check(value)
    -> こうすることでvalueがnullだった場合右辺の計算が行われないためぬるぽ対策にもなる
  ```

|    |    |
|----|----|
|==|値の同一性を比較する(基本型の場合は同値比較)|
|?:|条件演算子、条件式 ? Trueの時 : Falseの時</br> オペランドが３つ必要なことから三項演算子ともいう|
|.equals|参照型(StringBuilderなど)の同値比較くをする場合に使う|
|.deepEquals|ネストした配列の同値比較を行う時に使う|

</br>

##  **アクセス修飾子**
---
### **protected**
- 同一パッケージ内もしくは、違うパッケージでも継承関係ならアクセスOKとなる

### **final**
- 再代入できない様にできる

```
以下のケースについては、参照型(list)をfinalで定義しているため
参照先のデータについては変更できてしまう

final int[] VALUE = {10, 20, 30}
VALUE = new int[] {10, 30, 50} --> 変更できない(参照先を変更しようとしてるため)
int[0] = 50 --> 変更できる(参照先のデータを変更しようとしてるため)


上記のケースが仮にStringだった場合は、変更できない(Stringは不変系)
なのでString＋finalで完全に変更できない値が作れる
```

</br>

## **例外処理**
---
```
 例外の階層

 Ofject -- Throwable -- Error                      -> 致命的なエラー例外処理はせずにアプリは停止
                      L Exception                  -> 検査例外。コンパイル時にチェックされる、例外処理は必須
                                L RuntimeException -> 非検査例外。コンパイル事にチェックされない、例外処理は任意 

```
- throws句の使いどころはそのメソッドで投げる可能性のあるエラーを定義する
- try文を使う、tryブロック内で確保されたリソースはtryブロックを抜けた段階で破棄される
```
try-with-resource構文

以下の様に定義することでpythonのwith分と同じ働きがある
対象となるクラス(以下の場合はClassImplementingAutoCloseable)はAutoCloseableインターフェースを実装しているクラスに限る

try(ClassImplementingAutoCloseable obj = new ClassImplementingAutoCloseable()){}
```

</br>


## **データ型**
---
|整数 |    |    |
|----|----|----|
|byte|1byte||
|short|2byte||
|int|4byte|特別な理由がない場合はこれ使う|
|long|8byte||

備考 : </br>
- "_"で桁を区切って読み易くすることが出来る
- intの値をlongに格納すると暗黙的な型変換(拡大変換)で格納することができる
- intからbyteへの縮小変換については、"(byte)"を変数名の前につけて行う明示的変換でのみ可能


|浮動小数点 |    |    |
|----|----|----|
|floot|4byte||
|double|8byte|特別な理由がない限りはこれ使う|

> 備考 : </br>
> 浮動少数点は、以下のように表現されている</br>
> 12.5(10進数)</br>
> = 1100.1000(2進数) </br>
> = 1.1001000 * 2^3</br>
> 上記を以下の様に格納することで表現されている</br>
> [1(符号"-")] [10000010(指数 3 + 127)] [100100(仮数 1は固定なので省略)]</br>
> 欠点として指数部の値が大きい時に値が飛び幅が大きくなる</br>
> 例　12.5 * 10^10 ~ 12.6 * 10^10 よりも　12.5 * 10^100 ~ 12.6 * 10^100の飛び幅はでかい

|真偽 |    |    |
|----|----|----|
|boolean|1bit|デフォルト値 : false|

|文字 |    |    |
|----|----|----|
|char|2byte|デフォルト値 : \u0000(null)</br>文字列ではなく文字を格納するイメージ|

### **var**
- 型推論で自動的に型を設定してくれる
- メソッド内の変数としてのみ定義可能(フィールド宣言できない)
- 必ず初期値を設定する必要がある
- 型を明示的にする必要がない場合は積極的に使う</br>
  (もう既に別の場所で型が明示的に宣言されている変数の受け渡しなど)

### **null**
- オブジェクトへの参照を持たない状態の変数の事</br>
- アクセスしようとするとNullPointerExceptionが発動する</br>
- オブジェクトが存在しない場合を明示的に示したい場合や、</br>
  オブジェクトを破棄したい場合に、限定して使用する</br>
-  Optionalを併用して使うとより安全
```
String str = Optional.ofNulable("Not Null");
// nullだった場合"Null"を返す
String str2 = str.orElse("Null");
```


## **配列**
---
```
int[] list = new int[5] 長さのみ指定パターン
int[] list = new int[]{1, 2, 3, 4, 5} 初期値を入れとくパターン
int list[] = {1, 2, 3} C言語の名残パターン

アクセスは以下
int[2]
-> 3が返される
```
- インデックスに要素を超える数を設定すると、ArrayIndexOutOfBoundsException例外が発生</br>
- new して作成していることからStringのようにクラスがある様に見えるが、</br>
  そうではなくあくまで言語に組み込まれた擬似的なクラス</br>
- 特殊な配列として**ジャグ配列**という2次元目の要素数が異なる配列も定義可能
```
var list = new int[3][];
list[0] = new int[2]
list[1] = new int[5]
list[2] = new int[6]
```

</br>

## **コレクション**
---
- 配列と同じで順番にデータが格納されているもの
- オブジェクトを扱う際には上記の配列ではなく、</br>
  ArrayListなどのコレクションを使う
```
ArrayList<String> ary = new ArrayList<>();

変更不可に変換
var aryUnmodify = Collection.unmodifiableList(ary);

配列　<-> コレクション(listとか)
String[] array = new String[5];
ArrayList arrayList = Arrays.asList(array); -> 配列からリストに変換
String[] array2 = new String[arrrayList.size()];
arrayList.toArray(array2); -> リストから配列に変換


LinkedList<String> linkedList = new LinkedList<>();
-> 要素の先頭・末尾に次の要素の情報を持たせている作りのため、値の追加・削除がArrayListよりも高速で行える
(前後の要素の先頭末尾情報を書き換えれば良いため)。インデックスを指定しての取得は遅い
```

</br>

## **セット**
---
- 重複を許さない集まりのこと
- 集合的側面を持ち合わせる

```
HashSet<String> hsA = new HashSet<>();
HashSet<String> hsB = new HashSet<>();
hsA.addAll(hsB); -> 差集合的削除(A & not B)
hsA.removeAll(hsB); -> 和集合的削除(A | B)
hsA.retainAll(hsB); -> 積集合的削除(A & B)

LinkedSet<Stirng> ls = new LinkedSet<>();
TreeSet<String> ts = new TreeSet<>();

```

## **マップ**
---
- key value型のデータでCollectionを継承して作られていない(拡張forがそのまま適応できない)
```
HashMap<String, String> hm = new HashMap<>();
hm.put("a", "1"); -> 値のセット
TreeMap<String, String> tm = new TreeMap<>(
  (x, y) -> x.length() - y.length();    -> ラムダ式で並び順の判定式をオーバーライドできる
);
LinkedMap<String, String> lm = new LinkedMap<>();
```

</br>

## **スタッタック・キュー**
---
- 基本的にはArrayDeque使ってればOK
- 操作ログからの操作巻き戻しの時などにスタック
```
ArrayDeque<Integer> ad = new ArrayDeque<>(); -> スタック・キューどっちも

LinkedList -> キュー
Stack -> 昔からある非推奨のスタック
```

### **Enum** 
    列挙型で自身で内部的にインスタンス化して呼出可能な状態になる insight/column_128) [Link](https://www.modis.co.jp/candidate/)</br>
    format: アクセス修飾子 enum 列挙名 {列挙子1, 列挙子2, ・・・}

### **ジェネリクス(ジェネリクス構文)**
- 汎用的なクラス・メソッド(Listとか)に\<String>のように型をつける文
- ジェネリクスを適応することで、格納時に型チェックを行い、取り出し時に自動でキャストしてくれる(タイプセーフ)
```
ArrayList<String> strList = new List<>();

<>部分は左辺で定義されているため省略(ダイアモンド構文という)
<>内のパラメータのことを型パラメーター(もしくは型引数)という
```
- <型パラメーター extends 境界型> の様に定義することで、コンパイル時に境界型に定義した型になる。(境界型を継承していないと使用できないメソッドなどをジェネリックで使えるようになる)
- ArrayList\<Object>のように定義したコレクションは不変(ArrayList\<String> などの代入を許さない)


### **ラッパークラス**
- 基本型のデータを包んで(ラップして)オブジェクトとしての機能を与えるもの
- 基本型に比べて処理効率も悪くヌルポの可能性が出てくるため限られた場面でのみ使用する
```
Character c = Character.valueOf("Z");
Integer i = new Integer(16); 
  ->非推奨valueOfを使うべき(コンストラクターからだとキャッシュが作成されず常に新しく作られてしまうため)
```
- 文字列からの変換は以下(変換不可の場合はNumberFormatException例外が発生)
```
int x = Integer.parseInt("2");

文字列に変換
String y = x.toString()
```

## **Thread**
---
- Thread
- Threadを継承して使用する

|   |   |
|---|---|
|currentTheread()|現在実行中のスレッドを取得|
|getID()|スレッドIDを取得|
|isAlive()|スレッドが生存しているか|
|join()|スレッドが終了するまでのmillis秒|
|setPriority|スレッドの優先度を定義|
|sleep()|スレッド実行の休止|



## **String**
---
|メソッド|説明|
|----|----|
|strA.compareTo(strB)|文字の比較(unicode値がどれだけ離れているか) strA>strBの時正, strB>strAの時負, strA=strBの時0|
|str.isEmpty()|文字が空("")であるかどうか|
|str.isBlank()|空文字(" ","\t"など)が含まれるかどうか|
|str.strip()|空文字の削除(java11)|
|str.indexOf(str,[int])|検索,対象の文字列のIndexを返す(存在しない場合は-1),intは開始位置|
|str.contains(str,[int])|文字を含んでいるかどうか,intは開始位置|
|str.substring(int bigin, int end)|開始位置から終了位置までの文字列を部分的抜き出す|
|str.sprit(str,[int])|文字列を特定の文字で分割,intは回数制限,正規表現での分割も可能|
|str.replaceAll(regex,str)|正規表現に一致した内容全てを置換、</br>最初だけ置換 str.replaceFirst()</br>正規表現使わない置換 str.replace()|
|String.join(CharSequence sep, str1,str2...)|文字列を結合(sepは区切り文字)|
|String.format(strA, strB)|strAで書式文字列(%d,%sとか)こみの文字列,strBで書式指定文字列に部分に埋め込まれる文字列|


### **オートボクシング(Autoboxing)**
- 基本型からラッパーオブジェクトに暗黙的に変換されることを**ボクシング(boxing)**
- ラッパーオブジェクトから基本型に暗黙的に変換されることを**アンボクシング(anboxing)**
- 上記を合わせて**オートボクシング(Autoboxing)**
```
ボクシング
Integer i = 108;

アンボクシング
int j = i;
```
<注意点>
- 以下の様なケースだとオートボクシングが頻繁に発生してオーバーヘッドが高まる
```
Long result = 10; -> 基本形のlongを使用したかったのにLong使ってる

for (long i = 1; i < 100; i++) {
  result += i; ->ここでオートボクシングが発生してしまう
}
```

</br>


## **組み込み**
---
### **StringBuilder**
- "+"で結合す結合すると結合元、結合先、結合されたものの３つ作成される(効率が良くない)</br>
  ->新しいjavaでは＋の際にStringuBuilderが呼ばれて同じの性能が出る
- こいつの場合はあらかじめ確保した領域で行なっている
- 似たものとしてStringBufferがあるがこちらは、String Builderの排他的制御版(遅い)
```
領域については以下の様に指定できる
var builder = new StringBuilder(1000) -> あらかじめサイズを決めておける
```

### **BigDecimal**
- 以下の様な小数点以下で起こる誤差を解消してくれるもの
- 誤差を生じさせないために、引数は文字で指定するように
```
Math.floor((0.1 + 0.7) * 10)
-> 0.7
内部的に0.1は２進数で表すと無限循環小数になっているため
7.999999の様になっており上記の様な結果が返ってきていしまう
```

### **Pattern**
- 正規表現で一致した部分を取り出す
```
String str = "XXXXXYYZZZ"
pattern = Pattern.compile("正規表現");
match = pattern.matcher(str)
match.find() -> マッチした結果を.find()するたびに順番に表示(while文で回すとかする)
```

### **Date Time API**
|   |   |
|---|---|
|LocalDateTime|ローカルの日時|
|OffsetDateTime|時差情報付きの日時|
|ZonedDateTiem|時差地域固有の情報つき日時(Azia/Tokyoとか)|

```
LocalDateTime, OffsetDateTime, ZonedDateTime
3つとも使い方は一緒で付加情報が増える感じ

LocalDateTime date = LocalDateTime.now() -> 現在の日時取得
ZonedDateTime.of(2019,10,1,Zoned.of("Asia/Tokyo")) -> 引数から日時作成

date.getYear() -> 年の取り出し
date.format(SimpleDateFormat("yy:mm:dd")) -> 日付の書式設定

```

### **BufferReader**

</br>

## **用語集**
---
### **完全修飾名**
- クラスを識別する際に、「パッケージ＋クラス名」で識別する。</br>
- 以下のようなフルで書いた状態のこと</br>
  java.time.LocalDateTime</br>
- 上記の様な状態を解決するために、importする(名前解決という)</br>
- 名前解決後のクラス名だけの状態を単純名という

### **静的型付け**
- 変数を宣言する際に設定した型以外の値を代入することが出来ないこと
- 型をプログラマが明示的に設定する必要がある
- そもそも静的の意味としては、最初に定義され変わることのないもの

### **動的型付け**
- 変数を宣言する際に型を明示的に設定しなくても予測して設定してくれること

### **基本型**
- 数値や文字そのものが入っている変数のこと</br>
  プリミティブ型と呼ばれる事もある(構造がシンプルで原始的)
  int, charなど

### **参照型**
- 数値や文字が格納されている場所を持っている変数のこと</br>
  list, objなど

### **規定値**
- 文字・数字・配列などを定義した際に、自動的に入る値(char \u0000, boolean falseなど)

### **データソース**
- DBに接続する際に必要な情報(ホスト名など)をまとめたもの</br>
- DNS(固有の識別名)で識別される

### **リテラル**
- 値を格納できる値、または値の表現方法</br>
  例 2進数(0b0100),16進数(0xff),文字('')、文字列("")

### **型サレフィックス**
- 末尾につけてデータ型を指定できるもの</br>
  long: L,l </br>
  float: F,f </br>
  double: D,d</br>

### **インスタンス/オブジェクト**
- クラスをnewした物</br>
  クラス内のフィールドはコピーされ、メソッドはクラスでの定義を参照

### **フィールド**
- クラス内で用意されているデータの入れ物

### **クラスフィールド/クラスメソッド**
- オブジェクトを生成せずともクラスから直接呼び出せる(静的フィールド/静的メソッドとも言う)</br>
- staticをつけることで生成
- 反対にオブジェクトの生成が必要なものを、**インスタンスフィールド/インスタンスメソッド**という

### **同一性/同値性**
- 同一性は値が同一のものかどうか、javaにおいては"=="で比較される(基本型はこれで比較しても値の比較が行われる)
- 同値性は値が同じ値かどうか、javaにおいては"equals"メソッドで比較する
```
例
String stringA = new StringBuilder("ABC").toString();
String stringB = new StringBuilder("ABC").toString();

stringA == stringB
-> false
上記については、stringAとstringBで別の番地のメモリに格納された
別物なのでfalse判定

stringA.equals(stringB)
-> true
この場合は値が同じかどうか(同値性)を見ているためtrue判定

```

### **順次/選択/繰り返し**
- 下記の処理を組み合わせて作成することを**構造化プログラミング**という</br>
  **順次(順接) :** 上から順に処理</br>
  **選択 :** 条件分岐など処理を分岐</br>
  **繰り返し :** 特定の処理を繰り返す </br>

### **ラベル(句)**
- switchぶんで用いられる、caseを表す"xxxx:"を指す

### **サロゲートペア**
- Unicodeの2byteでは表現しきれなくなってきた文字を、4byteで表現する様にしたこと

### **シグニチャ**
- メソッドの識別情報、名前・引数の型・引数の並びで判断する
```
  indexOf(String str, int index)とあった場合シグニチャは以下
  indexOf(String, int) -> 引数名は含まない
```

### **クラス変数/ローカル変数/ブロック変数**
- クラス変数(フィールド): クラス内で定義される変数、同一クラス内のメソッドから呼び出す時はthis.で呼び出す
- ローカル変数: メソッド内で定義されている変数
- ブロック変数: if{}とかのブロック内のみで有効な変数

### **振る舞い**
- 振る舞い = メソッド

### **Shallow Copy / Deep Copy**
- 参照をコピーする、浅いコピー
- 値そのものをコピーする、深いコピー

### **アップキャスト/ダウンキャスト**
- アップキャスト: 派生クラスから基底クラスに型変換
- ダウンキャスト: 基底クラスから派生クラスに(常にできるわけではない)

### **オブジェクトo型/変数の型**
- オブジェクト型は生成された時の実体、変数型がキャストされようと変化しない
- 変数型はラベルの様なものでオブジェクト型がどのように振る舞うかを決めるだけ

```
Person = new BusinessPerson(); -> BusinessPersonがオブジェクト型
                               -> Personが変数型
```

### **リスコフ置換原則**
- 派生クラスを作成する原則として、基本型の変数に派生クラスのインスタンスを入れても成り立つようなクラスを作るべきというもの

### **マーカーインターフェイス**
- メソッドを持たない、何かしらの機能を利用することを宣言するためのインターフェイス(Cloneableなど)

### **エンクロージング型**
- 内部にクラスを持つクラスのこと

### **イレイジャ(Erasure)**
- ジェネリックで定義された型をコンパイル時にObject型として、不要な型情報を消す性質のこと



## **パッケージ**
---
- ソースの先頭でpackageで全て小文字で宣言
- インターネットドメインを逆順にしたもの + アプリ・プロジェクト名で作成
- java, javaxはjava開発者用なので使用しない
- フォルダの階層構造もパッケージにあわせる必要あり
- java.*のようなオンデマンドインポートはわかりずらいので避ける
  - 上記の様にインポートしても名前解決の情報をインポートしてるだけなのでクラスファイルは別に肥大化しない
  - java.util.*でインポートしたからといってサブパッケージ(regexとか)までインポートされるわけではない、パッケージ名は階層構造的に定義できるか階層的な意味を持つわけではない

</br>

## Command Line 
---
```
コンパイル
javac -d 生成先フォルダ フォルダ/*.java
javac -cp　コンパイルしたいクラスファイルのパス(javacのデフォルトがカレント)

実行
java -cp クラスファイルフォルダ クラス

```



# Eclipse

## **Shortcut**
|    |    |
|----|----|
|Ctr + Space|ソースの補完|

## **コマンドライン引数**
mainメソッドの引数argsとして受け取れる</br>
実行マークのプルダウン>実行の構成>引数タブ>プログラムの引数


# jdk install 
sudo apt install openjdk-8-jdk


# **EJB**
ソフトウェア部品を組み合わせて開発できるようにしたもの  
インテターフェースの名前対応表  
https://itpfdoc.hitachi.co.jp/manuals/3020/30203M0360/EM030192.HTM


![Java Data Object](https://github.com/s18013/Memo/blob/main/img/java_data_object.png "Title")

