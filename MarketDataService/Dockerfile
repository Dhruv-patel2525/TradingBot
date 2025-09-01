# ---------- build stage ----------
FROM maven:3.9.9-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn -B -DskipTests dependency:go-offline
COPY src ./src
RUN mvn -B -DskipTests package

# Normalize the jar name so the next stage has a stable path
RUN JAR="$(ls target/*.jar | grep -v '\.original$' | head -n1)" && \
    echo "Built: $JAR" && cp "$JAR" target/app.jar && ls -l target

# ---------- runtime stage ----------
FROM eclipse-temurin:17-jre-jammy
WORKDIR /app
RUN useradd -r -u 10001 spring
USER spring
COPY --from=build /app/target/app.jar /app/app.jar
EXPOSE 6002
ENV JAVA_OPTS="-XX:MaxRAMPercentage=75 -XX:+UseContainerSupport"
ENTRYPOINT ["sh","-c","java $JAVA_OPTS -jar /app/app.jar"]
