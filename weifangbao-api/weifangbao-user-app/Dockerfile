FROM frolvlad/alpine-oraclejre8:slim
EXPOSE 9400

VOLUME /tmp
ADD target/*.jar /app.jar
#RUN bash -c 'touch /app.jar'
ENTRYPOINT ["java","-jar","/app.jar"]

