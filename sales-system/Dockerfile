FROM eclipse-temurin:21-jdk
ARG JAR_FILE=target/supermarketsystem-0.0.1.jar
COPY ${JAR_FILE} app_sales_system.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app_sales_system.jar"]