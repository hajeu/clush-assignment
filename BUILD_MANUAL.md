# 소스 빌드 및 실행 방법
소스 빌드 및 실행 방법에 대한 메뉴얼입니다.

## 1. 소스 코드 클론

프로젝트의 소스 코드를 로컬 환경에 클론합니다.
```bash
git clone https://github.com/hajeu/clush-assignment
cd clush-assignment
```

## 3. 데이터 베이스 설정
`src/main/resources/application.yml` 파일을 열어 데이터베이스 연결 정보를 설정합니다.
```yml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/your_database
    username: your_username
    password: your_password
```
### DB 스키마
```sql
CREATE TABLE Schedule (
    dtype VARCHAR(31) NOT NULL,
    id BIGINT NOT NULL AUTO_INCREMENT,
    due_date_time DATETIME(6) NOT NULL,
    title VARCHAR(20) NOT NULL,
    description VARCHAR(300),
    completed BIT(1),
    PRIMARY KEY (id)
);
```

## 2. 의존성 설치 및 빌드
Gradle을 사용하여 의존성을 설치하고 프로젝트를 빌드합니다.
```bash
./gradlew build
```


## 4. 애플리케이션 실행
```bash
./gradlew bootRun
```


