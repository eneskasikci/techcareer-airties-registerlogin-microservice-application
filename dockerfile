FROM eclipse-temurin:19-jdk-alpine as build
WORKDIR /app
COPY mvnw .
COPY .mvn  ./.mvn
COPY pom.xml .
COPY src src
RUN ./mvnw clean install -DskipTests

FROM eclipse-temurin:19-jre-alpine as prod
COPY --from=build /app/target/*.jar /app/registerlogin.jar
ENTRYPOINT ["java","-jar","/app/registerlogin.jar","-web -webAllowOthers -tcp -tcpAllowOthers -browser"]
EXPOSE 3333