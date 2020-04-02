FROM adoptopenjdk/openjdk14-openj9
ARG WORKING_DIR=/var/opt/apps/expensemanager
CMD mkdir $WORKING_DIR
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} $WORKING_DIR/expenseManagerApp.jar
EXPOSE 1111 1111
#ENTRYPOINT ["ping","localhost"]
ENTRYPOINT ["java","-jar","/var/opt/apps/expensemanager/expenseManagerApp.jar"]
