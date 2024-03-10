# SQLAlchemy

## 2.0 Style
ORM実行時のquery.(ORM).findからcoreの呼び出しと同様の</br>
session.execute(select(ORM))などに統一された
https://docs.sqlalchemy.org/en/20/changelog/migration_20.html

## async
### 接続
以下のように２パターンあり、前者はコミットまで行うが後者は自身でコミットする必要がある
select updateなどの時に使うと良い
```
async with async_session() as session:
  async with session.begin():

async with async_session() as session:
```
### テーブル作成
以下のような流れで非同期ではテーブル作製を行える
```
class Base(DeclarativeBase):
    pass

class A(Base):
    __tablename__ = "a"
    id: Mapped[int] = mapped_column(primary_key=True)
    data: Mapped[str]

async with engine.begin() as conn:
    await conn.run_sync(Base.metadata.create_all)
```

## Expression
### SQLAlchemyError
|  |  |
---|---
.orig | SQLAlchemyErrorの元となるエラー
