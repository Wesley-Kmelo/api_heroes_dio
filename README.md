# Api_heroes_dio
Projeto desenvolvido na plataforma DIO com as habilidades adquiridas ao longo do curso, de JAVA DEVELOPER

Na live oferecida pela developer back-end [Kamila Santos](https://github.com/Kamilahsantos).

Tecnologias utilizadas
* Java 11
* Maven 4.0.0
* Swagger
* Postman
* Linux Ubuntu 20.04

Dependências utilizadas
* Lombok
* Reactor Test
* Web Flux
* Dynamo DB
* Swagger do Springfox

O banco de dados DynamoDB é criado pelo própria API , devendo antes serem executados respectivamente, a classe que cria a tabela, a classe que popula a tabela 
e em seguida a classe principal que contem a anotação do Spring. O código segue comentado em português para facilitar entedimento.

Lembrando que para funcionar,  uma instância do DynamoDB deve estar iniciada pelo terminal através do comando :
```
 java -Djava.library.path=./DynamoDBLocal_lib -jar DynamoDBLocal.jar -sharedDb
 ```
 e que deve ser executado no diretório onde foi baixado o dynamo DB
 
 
 A documentação desta API está publicada em 
 
 [Documentação API](https://documenter.getpostman.com/view/17988217/UVR4PADU)
 
 e para verificar pelo Swagger 
 
 [Swagger](http://localhost:8080/webjars/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/)


