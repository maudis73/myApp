FROM registry.access.redhat.com/ubi8/openjdk-17

COPY target/my-app-1.0-SNAPSHOT-jar-with-dependencies.jar /deployments/my-app-1.0-SNAPSHOT-jar-with-dependencies.jar
