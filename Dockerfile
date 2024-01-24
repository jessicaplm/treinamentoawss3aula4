FROM openjdk:11
MAINTAINER github/jessicaplm
ADD target/treinamentoawss3aula4-0.0.1-SNAPSHOT.jar treinamentoawss3aula4-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "treinamentoawss3aula4-0.0.1-SNAPSHOT.jar"]
EXPOSE 8080
