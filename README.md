# Spent-API

Gerencie seu gastos com **Spent-Api** e tenha um controle total de suas finanças.

## Tecnologias utilizadas
* Java 17
* Spring Boot
* PostgreSQL
* Docker

## Como rodar o projeto
Com Java 17 na sua máquina, execute o comando abaixo:
```sh
./gradlew clean build
./gradlew bootBuildImage
docker-compose up
```

### Cadastrar um gasto
POST

    localhost:8080/api/v1/expense

#### Body enviado

```json
{
  "namePerson": "Hyoga de Cisne",
  "description": "Pastel Folheado",
  "dateTime": "2022-12-29T21:11:00",
  "amount": 12.50,
  "tagName": [
    "food",
    "other"
  ]
}
```
Mais informações sobre a API, acesse o link abaixo:
http://localhost:8080/swagger-ui.html
