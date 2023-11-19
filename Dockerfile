FROM openjdk:17-slim

# Installez les paquets nécessaires pour X11 avec apt
RUN apt-get update && apt-get install -y xvfb x11-utils

ENV DISPLAY=:99

COPY JeuDeLaVie.jar .

WORKDIR .

CMD ["sh", "-c", "Xvfb :99 -screen 0 768x768x16  & java -jar JeuDeLaVie.jar "]
