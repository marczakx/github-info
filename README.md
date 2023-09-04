Github info

run<br>
mvn install<br>
java -jar target/*.jar

example<br>
http://localhost:8080/repos/marczakx

OpenAPI (swagger)<br>
http://localhost:8080/swagger-ui/index.html

<br>
docker
<br>
docker run -p 3434:8080 marczakx/gitbhub-info:1
<br>
with a token
<br>
docker run -p 3434:8080 -e GITHUB_TOKEN=token marczakx/gitbhub-info:1

