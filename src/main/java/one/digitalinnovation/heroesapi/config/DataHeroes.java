
// esta classe vai ser responsável por popular os dados da tabela criada em TableHeroes

package one.digitalinnovation.heroesapi.config;

import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.PutItemOutcome;

import static one.digitalinnovation.heroesapi.constants.HeroesConstants.ENDPOINT_DYNAMO;
import static one.digitalinnovation.heroesapi.constants.HeroesConstants.REGION_DYNAMO;

// o fato de ter um método main aqui quer dizer que essa classe é executavel, ou seja, ela vai ser iniciada na hora
// que o programa rodar junto como todos os outros métodos que contem o main

public class DataHeroes {
    public static void main(String[] args) throws  Exception{

        // criado objeto client do tipo AmazonDynamoDB que vai ser construido no modo padrão(standard)
        // com as configurações passadas como parametros e depois criado um objeto do tipo DynamoDB
        // que recebe como paramatro o objeto client.
        //Depois a tabela criada em TableHeroes é chamada aqui e populada com um dado atraves do método ITEM
        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(ENDPOINT_DYNAMO,REGION_DYNAMO))
                .build();

        DynamoDB dynamoDB = new DynamoDB(client);

        Table table = dynamoDB.getTable("TB_Herois_Api");

        Item heroi = new Item()
                .withPrimaryKey("id","1")
                .withString("name", "Wesley")
                .withString("universo", "DC Comics")
                .withInt("filmes", 11);

        PutItemOutcome gravaDado = table.putItem(heroi);
    }

}
