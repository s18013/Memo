# Design Pattern

## Repository Pattern 
データにアクセスロジックをCRUDとして抽象化するパターン。</br>
メリットとしては、DBからストレージへの切り替えなどが容易になる。

## Service Locator Pattern
サービスへのアクセスを一箇所で管理するパターン。</br>
Service Locatorにサービスを登録しておき、使用箇所でそれを取得することで、</br>
サービス間の依存関係を疎結合に保つことができる。