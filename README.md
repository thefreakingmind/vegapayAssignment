## VegaPay Coding Challening 


Below are the APIs for the Challening

* Create a Create User Account
```curl --location 'http://localhost:8082/api/v1/account/create' \
--header 'Content-Type: application/json' \
--data '{
   "accountLimit": 1000.0,
   "perTransactionLimit": 100.0,
   "lastAccountLimit": 800.0,
   "lastPerTransactionLimit": 90.0
}'
```

* Get Account By Id
```
curl --location 'http://localhost:8082/api/v1/account/get?accountId=4028818389c4b0db0189c4b0f7310000'
```

* Create a Limit Offer

```
curl --location 'http://localhost:8082/api/v1/offer/create' \
--header 'Content-Type: application/json' \
--data '{
   "accountId": "4028818389c4b0db0189c4b0f7310000",
   "limitType": "PER_TRANSACTION_LIMIT",
   "newLimit": 100000.0,
   "offerActivationTime": "2023-08-05T08:00:00",
   "offerExpiryTime": "2023-08-10T23:59:59"
}'
```

* Update the Offer 

```
curl --location 
--request POST 
'http://localhost:8082/api/v1/offer/update?limitOfferId=4028818389c52f0d0189c52f246a0000&status=ACCEPTED'

```


* Get the Limit Offer for Account Id and Date 
```
curl --location 'http://localhost:8082/api/v1/offer/get?accountId=4028818389c4b0db0189c4b0f7310000&activeDate=2023-08-07'
```

Postman Collection Link
https://drive.google.com/file/d/1ZhybniDKBoUYoBcxI0Qql_07Fl9o5muI/view?usp=sharing