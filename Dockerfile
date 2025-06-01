FROM amazoncorretto:17

WORKDIR /app

COPY target/Germes-Plus-Manager-1.0.jar germes-plus-manager.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "germes-plus-manager.jar"]