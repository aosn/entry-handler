entry-handler
=============

AWS Lambda で動く AOSN 読書会エントリー受付システムです。

## 機能

- エントリーを受け付け、受付担当者にメールを送る

## システム要件

- AWS API Gateway
- AWS Lambda (256MB 以上)
- Amazon Simple Email Service (US-EAST-1 リージョン)

API Gateway の Mapping Template 構成:

```
Browser --(application/x-www-form-urlencoded)-> API GW --(application/json)-> Lambda
                                                                                |
Browser <--------(text/html)------------------- API GW <-(application/json)-- Lambda
```

## 今後の課題

- 登録者に Discord 招待リンクを含むメールを送信する
- 登録者に Slack の招待を送る
- LDAP 登録とかも？

## ライセンス

[Apache License](LICENSE)
