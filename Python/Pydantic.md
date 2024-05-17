# Pydantic

## [Validator](https://docs.pydantic.dev/latest/concepts/validators/#field-validators)

### [必須チェック](https://docs.pydantic.dev/latest/concepts/models/#required-fields)
以下のように定義すると、必須チェックが行われる。
Noneは許容されない。
```
class MyModel(BaseModel):
    name: str
```
以下はNoneを許容する。
```
class MyModel(BaseModel):
    name: str = Field(...)
```
以下はフィールドが存在しなくても良いパターン
```
class MyModel(BaseModel):
    name: str | None = None
```

### Annotations
|annotation|説明|
|-|-|
|@field_validator("field_name")|引数に渡されたフィールドに対してアノテーションを付けた関数で検証を行います。フィールドは複数設定可能です。使用する関数に@classmethodをつけることが推奨されています。|
|@model_validator("before OR after OR wrap")|モデル全体の検証に使用します。"before"はモデルクラスがインスタンス化される際に呼び出され、pydanticデフォルトの検証前の生データが渡されます。"after"はモデルのインスタンス後(デフォルト検証後)に呼び出されます|



## imuutable
以下のように定義すると、新しくオブジェクトを生成しないかぎり変更不可になる。
lrucacheで定義しておけば、同じオブジェクトが返されるので擬似的に変更不可にできる。
```
class MyModel(BaseModel, frozen=True):
    name: str
```

## instance

以下のような定義をすると、インスタンスを作成することができる。
```
class MyModel(BaseModel):
    id: int | None = None
    name: str | None = None


schema = MyModel(name="foo")
```

仮に以下のような定義をすると、属性にNoneが設定されずエラーになる。
```
schema = MyModel
setattr(schema, "name", "foo")

schema.id
  ---> AttributeError
```