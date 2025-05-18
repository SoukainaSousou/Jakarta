# Utilise une image de base avec OpenJDK 17 (version slim, allégée pour réduire la taille)
FROM openjdk:17-jdk-slim

# Définit le répertoire de travail à l'intérieur du conteneur. Toutes les commandes suivantes se dérouleront ici.
WORKDIR /app

# Copie le fichier JAR généré (backend-0.0.1-SNAPSHOT.jar) depuis le répertoire target sur l'hôte vers le répertoire /app dans le conteneur,
# et le renomme en backend.jar.
COPY target/backend-0.0.1-SNAPSHOT.jar /app/backend.jar

# Expose le port 8080 du conteneur, ce qui permet de rendre l'application accessible depuis l'extérieur via ce port.
EXPOSE 8080

# Définit la commande à exécuter lors du démarrage du conteneur. Ici, on lance l'application Spring Boot en utilisant "java -jar"
# pour exécuter le fichier JAR backend.jar situé dans le répertoire /app.
ENTRYPOINT ["java", "-jar", "/app/backend.jar"]
