- Git link https://github.com/sugganabalaji/aws-rds-java-connect
- git clone to your favorite JAVA supported IDE, Intellij, STS, Eclipse, VS code
- Expand aws-rds-java-connect
- Expand src > main > resources.
- Look for application-postgres.yaml
- Look for application-mysql.yaml
- Change DB url, username, password as per your AWS RDS Endpoint configuration
-  Go to application.yaml file 
   - if you use postgres DB, change to --> active: postgres
   - if you use MySQL DB, change to --> active: mysql
- Once all done, then run 
- Then open file [AwsRdsJavaConnectApplication.java](src/main/java/com/app/awsrdsjavaconnect/AwsRdsJavaConnectApplication.java)
- Right click any where Run.
- Application will started in Tomcat started on port 8080 (http) with context path '/'
- Open swagger URL in browser http://localhost:8080/swagger-ui/index.html
- Expand GET /api/employees > Try it > Execute > Scroll down to check response from DB. 
- 
- Check Database. Employee table will be created. 50 records will be inserted.
- in Swagger, Explore any Rest API, GET, POST, PUT, DELETE > Try it > Execute

- You can Check, Rest APIs from Postman, Insomnia
----------------------------------------------------
AWS RDS  postgres DB configuration
username: postgres
password: root123456

- enable security group: PostGre SQL - 5432 -IPv4 or MyIP
- Endpoints > Publicly accessible > No
- change to YES
- Modify > Connectivity > Additional Configuration > Public Access > radio button : Publicly Accessible > continue 
- Scheduled Modifications > Apply Immediately > Modify > status to be Available.

- Perfect

Run Application.

Open Swagger Url ttp://192.168.31.1:8080/swagger-ui/index.html

PG Admin:

- Servers > Right click
- General > Name > aws-rds-postgres-instance
- Copy Endpoint from AWS
- Connection > Hostname/address > past here > aws-rds-postgres-instance.cdkw246sgazm.ap-south-1.rds.amazonaws.com
- port: 5432
- username: postgres
- Password: root123456
- Sava

- expand > aws-rds-postgres-instance > Databases > postgres > Schemas > public > Tables

- Perform CRUD operations from swagger.

-Delete RDS instance, replica, Manual snapshot, any snapshot in S3 and cloud watch logs