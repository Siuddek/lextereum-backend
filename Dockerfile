FROM openjdk:11-jre-slim
VOLUME /tmp
ARG JAR_FILE
COPY ${JAR_FILE} device-status.jar
RUN apt-get update
RUN apt-get update && apt-get -y install \
    tesseract-ocr \
    imagemagick
RUN mkdir -p /usr/share/tessdata
ADD https://github.com/tesseract-ocr/tessdata/raw/master/pol.traineddata /usr/share/tessdata/pol.traineddata
ENV APP_FILE lextereum-backend-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/device-status.jar"]
