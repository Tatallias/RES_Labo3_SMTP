FROM java:8

RUN mkdir -p /app && cd /app && wget -q https://github.com/tweakers/MockMock/raw/master/release/MockMock.jar

EXPOSE 25 8282

CMD ["java", "-jar", "/app/MockMock.jar"]