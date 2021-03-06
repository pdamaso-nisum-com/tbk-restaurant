# Technical Test

### Requirements
* spring boot
* java 8
* unit testing with junit
* rabbitmq

#### How to run unit test
* `./gradlew clean test --info`

#### How to build app
* `./gradlew build`

#### Prerequisites
* Run followings commands to install and run RabbitMQ locally
* `brew update`
* `brew install rabbitmq`
* `export PATH=$PATH:/usr/local/sbin`
* `rabbitmq-server`

#### How to run app (alternatives)
* `./gradlew bootRun`
* `java -jar build/libs/restaurant-0.0.1.jar`
* Run through IDE

## API
* To get a JWT token, use username as `username` and password as `password`, to match mock user persisted in DB. Response will contain JWT token.
```http request
POST http://localhost:8081/login
{
	"username": "username",
	"password": "password"
}
```
* To register a new sale
```http request
POST http://localhost:8081/api/v1/sales
{
	"commerce": 123,
	"date": "27-11-2020",
	"terminal": 456,
	"amount": 11.22
}
```
* To get all sales registered on a specific date
```http request
GET http://localhost:8081/api/v1/sales/{{year}}/{{month}}/{{day}}
```

## API Docs
* Access to `http://localhost:8081/swagger-ui.html`
* Authorize requests with `Bearer <token>`

## Mock sales loading
* There is a scheduler configured to publish a mock sale every 10 seconds, to simulate some sales load.
