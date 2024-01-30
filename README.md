<h1 align="center">Github info</h1>

A simple application that displays information about public repositories for a GitHub user. The application displays a list of repository branches and the latest branch commit.

Compilation

```sh
mvn install
```
Run

```sh
java -jar target/*.jar
```
Example of use

```sh
curl http://localhost:8080/repos/marczakx
```

or using a browser and the link below

http://localhost:8080/repos/marczakx

OpenAPI (swagger)

http://localhost:8080/swagger-ui/index.html

Run using the image from DockerHub:

```sh
docker run -p 8080:8080 marczakx/github-info:3
```

Without a token there are low rate limits.
To use the token set the GITHUB_TOKEN environment variable.

```sh 
docker run -p 8080:8080 -e GITHUB_TOKEN=token marczakx/github-info:3
```

