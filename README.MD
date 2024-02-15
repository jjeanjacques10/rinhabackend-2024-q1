# Rinha de Backend - 2ª Edição

Repositório do código desenvolvido para a segunda edição da rinha de backend.

## Tecnologias

- Kotlin
- Spring Boot 3
- Postgresql

## Getting Started

Para começar a editar localmente, primeiro execute o container do banco de dados utilizando o seguinte comando:

``` shell
docker-compose -f docker-compose-database.yml up -d
```

E então rode em sua IDE o projeto ou execute os seguintes comandos:

``` shell
cd app
mvn clean install
```

Após a compilação bem-sucedida com o comando mvn clean install, você pode executar o projeto usando o arquivo JAR
gerado. Utilize o seguinte comando:

``` shell
java -jar app/target/rinhabackend-0.0.1-SNAPSHOT.jar
```

## Graalvm

Configurando o [Graalvm](https://www.youtube.com/watch?v=8umoZWj6UcU) para resolver o problema de memória.

``` shell
mvn -Pnative spring-boot:build-image
docker run --rm -p 8080:8080 docker.io/library/rinhabackend-0.0.1-SNAPSHOT
```

## Sobre a Rinha

A Rinha de Backend é uma competição onde desenvolvedores demonstram suas habilidades para melhorar a performance de
aplicações.

Para mais informações e regras, acesse o repositório oficial da Rinha de
Backend: [Rinha de Backend - GitHub](https://github.com/zanfranceschi/rinha-de-backend-2024-q1)