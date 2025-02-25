FROM maven:3.9.5-eclipse-temurin-21 as build

WORKDIR /app
COPY pom.xml ./
COPY src ./src

RUN mvn clean package -Dmaven.test.skip

FROM openjdk:21 as runner

WORKDIR /app
COPY --from=build /app/target/*.jar service.jar
COPY --from=build /app/pom.xml .
COPY --from=build /app/src ./src

RUN groupadd -r user && useradd -g user user
RUN chown -R user:user /app
USER user

ENTRYPOINT ["java", "-jar", "service.jar"]
