FROM azul/zulu-openjdk-alpine:11 as build
WORKDIR /workspace/app
ARG DEFAULT_MODULE=sample-cqrs-events
# Copy the m2 directory if it exists to re-use an external cache.
# The `mvnw` file is also copied so that if an `.m2` directory isn't present
# the command won't fail.
COPY mvnw .m2* /root/.m2/repository/

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src

RUN ./mvnw clean --quiet\
  && ./mvnw dependency:go-offline --quiet\
	&& ./mvnw install -Dmaven.default-test.skip=true -Dmaven.test.skip=true -DskipTests --no-transfer-progress --quiet \
  && mkdir -p extracted && java -Djarmode=layertools -jar target/*.jar extract --destination extracted

RUN ls -lrt extracted

FROM azul/zulu-openjdk-alpine:11-jre

VOLUME /tmp

ARG DEPENDENCY=/workspace/app/extracted
# The version of OpenTelemetry used (https://opentelemetry.io), set by the build.
ARG OPEN_TELEMETRY_VERSION

COPY --from=build ${DEPENDENCY}/dependencies/ ./
RUN true
COPY --from=build ${DEPENDENCY}/spring-boot-loader/ ./
RUN true
COPY --from=build ${DEPENDENCY}/snapshot-dependencies/ ./
RUN true
COPY --from=build ${DEPENDENCY}/application/ ./

RUN if [ -e "/BOOT-INF/classes/emulatorcert.crt" ]; then cd $JAVA_HOME/lib/security && keytool -importcert -cacerts -storepass changeit -noprompt -alias documentdbemulator -file /BOOT-INF/classes/emulatorcert.crt; fi

ADD https://github.com/open-telemetry/opentelemetry-java-instrumentation/releases/download/${OPEN_TELEMETRY_VERSION}/opentelemetry-javaagent.jar ./

ENTRYPOINT ["java", "-javaagent:opentelemetry-javaagent.jar", "org.springframework.boot.loader.JarLauncher"]

EXPOSE 9000
