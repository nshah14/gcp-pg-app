

To Setup gcp infrasturture...

go to teraform/project directory and run
terraform init ( terraform .12.19 is been used)
terraform plan -out out.plan
terraform apply 

It will create GCP project/SA with service role and PG database with one user and password.

Use that in  spring app (application.properties) and with SA run command below to to mvn springboot deployment on App Engine. 



![Flow_APP_GCP](https://github.com/nshah14/gcp-pg-app/blob/master/Flow_APP_GCP.png)


#Please add DB creds in application.properties and run.
mvn package appengine:deploy

#To insert/update db please use 

curl -X PUT   https://rev-pg-app-dot-nav-rev-task.appspot.com/hello/nvwed   -H 'cache-control: no-cache'   -H 'content-type: application/json'   -d '{  "dateOfBirth":"2020-01-18" }'

#To Retrieved Date of Birth use.
Example URL : https://rev-pg-app-dot-nav-rev-task.appspot.com/hello/nvwed



#To Run local :
./mvnw spring-boot:run


#To Clean and repackage :
mvn clean package spring-boot:repackage

#To Build Docker image and upload it to google cloud
./mvnw com.google.cloud.tools:jib-maven-plugin:build

#To Run Docker image in google cloud console.
docker run -p 8080:8080 -t gcr.io/project-id/rev-pg-app

#To Deploy image in google cloud app engine.
gcloud app deploy --image-url gcr.io/project-id/rev-pg-app
