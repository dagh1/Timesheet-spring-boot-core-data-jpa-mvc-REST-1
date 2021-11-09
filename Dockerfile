FROM ninjamajewski/jdk-mvn-npm
EXPOSE 9101
ADD target/Timesheet-spring-boot-core-data-jpa-mvc-REST-1-2.0.war devops-docker-homework.war
ENTRYPOINT ["java", "-war", "spring-boot-devops-docker.war"]