FROM java:8
EXPOSE 8095
ADD /target/Timesheet-spring-boot-core-data-jpa-mvc-REST-1-2.0.war Timesheet.war
ENTRYPOINT ["java", "-jar", "Timesheet.war"]
