FROM openjdk:19-alpine
ADD target/RegisterLoginApp-0.0.1-SNAPSHOT.jar registerlogin.jar
ENTRYPOINT ["java","-jar","/registerlogin.jar","-web -webAllowOthers -tcp -tcpAllowOthers -browser"]
EXPOSE 3333