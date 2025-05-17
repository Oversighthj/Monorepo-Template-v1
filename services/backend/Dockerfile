# services/backend/Dockerfile
# ---------- Build stage ----------
FROM eclipse-temurin:17-jdk AS build
WORKDIR /app

# نسخ ملفات الـ Maven تدريجياً للاستفادة من الكاش
COPY mvnw pom.xml ./
COPY .mvn .mvn
RUN chmod +x mvnw && ./mvnw -q dependency:go-offline

# نسخ السورس ثم البناء
COPY src src
RUN ./mvnw -q -DskipTests package

# ---------- Run stage ----------
FROM eclipse-temurin:17-jre
WORKDIR /opt/app
COPY --from=build /app/target/template-service-*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/opt/app/app.jar"]
