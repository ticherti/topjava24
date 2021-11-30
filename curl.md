curl --location --request GET 'localhost:8080/topjava_war_exploded/rest/meals'

curl --location --request GET 'localhost:8080/topjava_war_exploded/rest/meals/filter?startDate=2020-01-30&endDate=2020-01-31&startTime=10:00:00&endTime=20:00:00'

curl --location --request GET 'localhost:8080/topjava_war_exploded/rest/meals/100003'

curl --location --request POST 'localhost:8080/topjava_war_exploded/rest/meals' \
--header 'Content-Type: application/json' \
--data-raw '{
"id": null,
"dateTime": "2022-01-31T13:30:00",
"description": "Новый Обед",
"calories": 1000,
"user": null
}'

curl --location --request PUT 'localhost:8080/topjava_war_exploded/rest/meals/100004' \
--header 'Content-Type: application/json' \
--data-raw '{
"id": 100004,
"dateTime": "2020-01-31T13:00:02",
"description": "Обновленнный Обед",
"calories": 1000,
"user": null
}'

curl --location --request DELETE 'localhost:8080/topjava_war_exploded/rest/meals/100003'