# spring-dynamodb-range-key
## About
This is sample of spring boot connected to DynamoDB that has hash key (partition key) and range key (sort key).
## How to run 
This is sample to run on EC2.
### prepare DynamoDB
1. create table

| setting                  | value                   |
|--------------------------|-------------------------|
| table name               | dev-sample-table        |
| hash key (partition key) | key (String)            |

2. create record

| key             | value           |
|-----------------|-----------------|
| key             | env             | 
| value           | dev             |

3. create EC2
* Instance profile must have IAM policy to access DynamoDB (ex. AmazonDynamoDBFullAccess) and accept SSM (ex.
  AmazonSSMManagedInstanceCore)
* Enable metadata(V1).
* Subnet must be public and has Internet Gateway. 

### run spring boot
1. make JAR

`mvn install`
2. transfer jar to EC2

`scp -o  ProxyCommand="aws ssm start-session --target %h --document-name AWS-StartSSHSession --parameters 'portNumber=%p'" -i [EC2 private key].pem  target/application.jar ec2-user@i-xxxxxxxxxxxxxxxxx:/home/ssm-user/application.jar`
3. SSH and run

`aws ssm start-session --target i-xxxxxxxxxxxxxxxxx`
`cd ~`
`java -jar ~/application.jar`
4. access from browser

`http://[EC2 hostname]:8080/dynamo/env`