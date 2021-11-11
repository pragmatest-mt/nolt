FROM maven:3.6.3-jdk-11

#WORKDIR /app
#
#COPY ./pom.xml ./pom.xml

WORKDIR /app/nolt-gateway

COPY ./nolt-gateway/pom.xml ./pom.xml
RUN mvn dependency:go-offline

COPY ./nolt-gateway/src/ ./src/
CMD ["mvn","spring-boot:run"]