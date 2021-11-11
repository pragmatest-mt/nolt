FROM maven:3.6.3-jdk-11

WORKDIR /app

COPY ./pom.xml ./pom.xml

WORKDIR /app/nolt-restaurant-orders

COPY ./nolt-restaurant-orders/pom.xml ./pom.xml
RUN mvn dependency:go-offline

COPY ./nolt-restaurant-orders/src/ ./src/

RUN mvn test

CMD ["mvn","spring-boot:run"]