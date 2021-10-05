# rest-api-servlets
This is the output for a REST API with Java & MySQL tutorial using Tomcat. The responses are converted from the original tutorial's HTML into JSON responses.

Tutorial link: https://medium.com/@calvinqc/build-a-simple-rest-apis-with-java-8-tomcat-jsp-mysql-intellij-on-mac-3308f4e59a03

API Documentation
Base URL for API = http://localhost:8080/app

Say Hello (GET Request) - Outputs "Hello World" message in JSON format.

/sayhello

Query Book (GET Request) - Outputs books by the author in the parameter.

HTML Response:
/query?author={{author}}

JSON Response:
/jsonquery?author={{author}}


