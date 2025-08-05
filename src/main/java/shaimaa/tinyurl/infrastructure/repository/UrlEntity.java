package shaimaa.tinyurl.infrastructure.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@DynamoDBTable(tableName = "URL")
public class UrlEntity {
    @DynamoDBHashKey(attributeName = "tinyUrl")
    private String tinyUrl;

    @DynamoDBAttribute(attributeName = "OriginalUrl")
    private String originalUrl;

    @DynamoDBAttribute
    private long ttl;
}
