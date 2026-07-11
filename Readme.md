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