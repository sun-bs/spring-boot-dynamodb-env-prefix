package com.example.demo;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@DynamoDBTable(tableName = "sample-table")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Entity {

    /**
     * partition key (hash key)
     */
    @Id
    @DynamoDBHashKey
    private String key;

    private String value;

}
