package com.example.demo;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverterFactory;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableDynamoDBRepositories(dynamoDBMapperConfigRef = "dynamoDBMapperConfig", basePackages = "com.example.demo.repository")
public class DynamoConfig {

    /**
     * set credential
     * @return AmazonDynamoDB
     */
    @Bean
    public AmazonDynamoDB amazonDynamoDB() {
        return AmazonDynamoDBClientBuilder.standard().build();
    }

    /**
     * DynamoDBMapperConfig
     * @param tableNameOverride
     * @return DynamoDBMapperConfig
     */
    @Bean
    public DynamoDBMapperConfig dynamoDBMapperConfig(DynamoDBMapperConfig.TableNameOverride tableNameOverride) {
        DynamoDBMapperConfig.Builder builder = new DynamoDBMapperConfig.Builder();
        builder.setTableNameOverride(tableNameOverride);
        builder.setTypeConverterFactory(DynamoDBTypeConverterFactory.standard());
        return builder.build();
    }

    /**
     * TableNameOverride
     * @param dynamoTablePrefix dynamoDB table name prefix specified at application-{env}.yml
     * @return DynamoDBMapperConfig.TableNameOverride
     */
    @Bean
    public DynamoDBMapperConfig.TableNameOverride getTableNameOverride(
            @Value("${dynamo.table-name-prefix}") String dynamoTablePrefix) {
        return DynamoDBMapperConfig.TableNameOverride.withTableNamePrefix(dynamoTablePrefix);
    }
}
