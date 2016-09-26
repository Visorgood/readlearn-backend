FROM java:8
VOLUME /tmp
ADD backend-0.1.jar target/rlbes.jar
RUN sh -c 'touch target/rlbes.jar'
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=local","target/rlbes.jar"]