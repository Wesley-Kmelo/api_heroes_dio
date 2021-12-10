
// classe responsável por configurar os atributos da tabela no banco de dados DynamoDB

package one.digitalinnovation.heroesapi.config;

import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;

// parte da configuração da tabela
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.model.ScalarAttributeType;
import  com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import  com.amazonaws.services.dynamodbv2.model.KeyType;
import com.amazonaws.services.dynamodbv2.model.KeySchemaElement;
import com.amazonaws.services.dynamodbv2.model.AttributeDefinition;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import  com.amazonaws.services.dynamodbv2.document.Table;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.context.annotation.Configuration;
import java.util.Arrays;

import static one.digitalinnovation.heroesapi.constants.HeroesConstants.ENDPOINT_DYNAMO;
import static one.digitalinnovation.heroesapi.constants.HeroesConstants.REGION_DYNAMO;


// o fato de ter um método main aqui quer dizer que essa classe é executavel, ou seja, ela vai ser iniciada na hora
// que o programa rodar junto como todos os outros métodos que contem o main

@Configuration
@EnableDynamoDBRepositories
public class TableHeroes {
        public static void main(String[] args) throws Exception{

            // criado objeto client do tipo AmazonDynamoDB que vai ser construido no modo padrão(standard)
            // com as configurações passadas como parametros e depois criado um objeto do tipo DynamoDB
            // que recebe como paramatro o objeto client.
            //Depois é criado uma tabela no bloco try com os atributos vindos do objeto DynamoDB client
            AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
                    .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(ENDPOINT_DYNAMO, REGION_DYNAMO))
                    .build();

            DynamoDB dynamoDB = new DynamoDB(client);
            String tableName = "TB_Herois_Api";

            try{
                Table table = dynamoDB.createTable(
                        tableName,
                        Arrays.asList(new KeySchemaElement("id", KeyType.HASH)),
                        Arrays.asList(new AttributeDefinition("id", ScalarAttributeType.S)),
                        new ProvisionedThroughput(5L,5L));

                table.waitForActive();
            }

            catch (Exception e){
                System.out.println(e.getMessage());
            }


    }
}

