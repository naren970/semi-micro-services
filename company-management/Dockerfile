FROM openjdk:17-alpine
EXPOSE 8085
EXPOSE 8761

ADD target/company-management-1.0.jar  company-management-1.0.jar
ENTRYPOINT ["java","-jar","/company-management-1.0.jar"]

