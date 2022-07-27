FROM 960834471527.dkr.ecr.ap-southeast-1.amazonaws.com/briixrepo:0.0.2 as builder
WORKDIR  /usr/src/app/
COPY src /usr/src/app/src
COPY pom.xml /usr/src/app
RUN mvn dependency:go-offline -f /usr/src/app/pom.xml clean package -DskipTests
#build jar from base image and then put it to target
FROM builder
WORKDIR /app
COPY --from=builder /usr/src/app/target/*.jar /app/
ADD https://errorcodefiles.s3-ap-southeast-1.amazonaws.com/errorcode.properties /app/
EXPOSE 8087
ENTRYPOINT ["java","-jar","/app/notification-0.0.1.jar"]