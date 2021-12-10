// classe responsaǘel pela configuração de acesso ao banco de dados DynamoDB
// importante fazer imports das bibliotecas de validação de login, criação do database Dynamo  e outras
// anotações necessárias

package one.digitalinnovation.heroesapi.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

@Configuration
@EnableDynamoDBRepositories
public class dynamoConfig {

// as anotações abaixo servem para passar os dados que vierem do application properties
    @Value("${amazon.dynamodb.endpoint}")
    private String amazonDynamoDbEndpoint;

    @Value("${aws_access_key_id}")
    private String aws_access_key_id;

    @Value("${aws_secret_access_key}")
    private  String aws_access_secret_key;

// as anotações abaixo vão servir para injetar uma dependencia do banco de dados dynamo, onde é criado um método que retorna um
// objeto do tipo AmazonDynamoDB e depois esse método  recebe uma instância do objeto AmazonClientDb e nessa instância
// é passado o login do database. Esse login é obtido através de um método(amazonAWSCredentials())
// Se o StringUtils for diferente de vazio ele configura a variavel amazonDynamoDb com o valor que está passado no parametro do
// próprio StringUtils. e depois retorna esse valor transformando em um objeto amazonDynamoDB.
    @Bean
    public AmazonDynamoDB amazonDynamoDB(){
        AmazonDynamoDB amazonDynamoDB = new AmazonDynamoDBClient(amazonAWSCredentials());
        if(!StringUtils.isEmpty(amazonDynamoDbEndpoint)){
                amazonDynamoDB.setEndpoint(amazonDynamoDbEndpoint);
        }
        return amazonDynamoDB;
    }

// criado outro Bean com um método que vai passar o valor de parametro de AWSCredentials do metodo instanciado acima;
    @Bean
    public AWSCredentials amazonAWSCredentials(){
        return new BasicAWSCredentials(
                aws_access_key_id, aws_access_secret_key );
    }

}
