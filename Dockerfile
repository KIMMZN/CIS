# 1️⃣ Gradle 빌드 환경
FROM gradle:7.6.2-jdk17 AS builder

# 2️⃣ 작업 디렉토리 설정
WORKDIR /app

# 3️⃣ Gradle 설정 파일을 먼저 복사하여 캐싱 최적화
COPY build.gradle settings.gradle gradlew ./
COPY gradle gradle

# 4️⃣ Gradle 실행 권한 부여 및 의존성 다운로드
RUN chmod +x gradlew && ./gradlew dependencies --no-daemon

# 5️⃣ 전체 프로젝트 파일 복사
COPY . .

# 6️⃣ Gradle 빌드 실행 (테스트 제외)
RUN ./gradlew build -x test --no-daemon

# 7️⃣ 실행 환경 (가벼운 JDK 사용)
FROM openjdk:17-jdk-alpine

# 8️⃣ 작업 디렉토리 설정
WORKDIR /app

# 9️⃣ 빌드된 JAR 파일을 정확히 지정해서 복사
COPY --from=builder /app/build/libs/app.jar app.jar

# 🔟 컨테이너 실행 명령어
ENTRYPOINT ["java", "-jar", "app.jar"]
