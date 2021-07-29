FROM openjdk:11
EXPOSE 8080
ADD target/medicine-stock.jar medicine-stock.jar
ENTRYPOINT ["java","-jar","/medicine-stock.jar"]
