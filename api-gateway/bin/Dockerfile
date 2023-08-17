FROM openjdk:8
EXPOSE 8089
ADD  certs/keystore.p12 keystore.p12
ADD target/tracrat-email-1.0.jar  tracrat-email-1.0.jar
ENTRYPOINT ["java","-jar","/tracrat-email-1.0.jar"]
