FROM maven:3.6.3-jdk-11

WORKDIR /app

COPY ./pom.xml ./pom.xml

WORKDIR /app/nolt-customer-orders

COPY ./nolt-customer-orders/pom.xml ./pom.xml
RUN mvn dependency:go-offline

COPY ./nolt-customer-orders/src/ ./src/

RUN mvn test

CMD ["mvn","spring-boot:run"]