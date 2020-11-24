# Technical Test

### Requirements
* spring boot
* java 8
* unit testing with junit

#### How to run unit test
* `./gradlew clean test --info`

#### How to build app
* `./gradlew build`

#### How to run app (alternatives)
* `./gradlew bootRun`
* `java -jar build/libs/restaurante-0.0.1.jar`
* Run through IDE

## API
* To register a new sale
```http request
POST http://localhost:8081/api/v1/sales
{
	"commerce": 123,
	"date": "2020-11-24",
	"terminal": 456,
	"amount": 11.22
}
```
* To get all sales registered on a specific date
```http request
GET http://localhost:8081/api/v1/sales/{{year}}/{{month}}/{{day}}}}
```