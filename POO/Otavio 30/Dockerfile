# Imagem oficial do Java 25
FROM eclipse-temurin:25-jdk

# Diretório de trabalho
WORKDIR /app

# Copia o pom.xml
COPY pom.xml .

# Copia o código-fonte
COPY src ./src

# Instala o Maven
RUN apt-get update && apt-get install -y maven

# Compila e gera o JAR
RUN mvn clean package -DskipTests

# Copia o JAR gerado
RUN cp target/*.jar app.jar

# Porta padrão
EXPOSE 8080

# Inicialização
ENTRYPOINT ["java","-jar","app.jar"]