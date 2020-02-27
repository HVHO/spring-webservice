REPOSITORY=/home/ec2-user/app/step2
PROJECT_NAME=spring-webservice

echo "> COPY BUILD FILE"

cp $REPOSITORY/zip/*.jar $REPOSITORY/

echo "> CHECK CURRENT RUNNING APP PID"

CURRENT_PID=$(pgrep -fl spring-webservice | grep jar | awk '{print $1}')

echo "> CURRENT RUNNING APP PID : $CURRENT_PID"

if [ -z "$CURRENT_PID" ]; then
  echo "> THERE INS NO RUNNING APP"
else
  echo "> KILL -15 $CURRENT_PID"
  kill -15 "$CURRENT_PID"
  sleep 5
fi

echo "> DEPLOY NEW APPLICATION"

JAR_NAME=$(ls -tr $REPOSITORY/*.jar | tail -n 1)

echo "> JAR NAME: $JAR_NAME"

echo "> ADD AUTHORITY OF EXECUTE FOR $JAR_NAME"

chmod +x $JAR_NAME

echo "> EXECUTE $JAR_NAME"

nohup java -jar \
  -Dspring.config.location=classpath:/application.properties,\
classpath:/application-real.properties,\
/home/ec2-user/app/application-real-db.properties \
  -Dspring.profiles.active=real \
  $JAR_NAME > $REPOSITORY/nohup.out 2>&1 &
