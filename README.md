# DBdump
테스트 데이터 관리용 덤프 패키지.

## H2 -> CSV -> MYSQL
## MYSQL -> CSV -> H2

#### 레파지토리

    <repositories>
        <repository>
            <id>next-mvn-repo</id>
            <url>https://raw.github.com/zerohouse/dbdump/mvn-repo/</url>
        </repository>
    </repositories>

#### 디펜던시

    <dependency>
        <groupId>org.next</groupId>
        <artifactId>DBdump</artifactId>
        <version>0.0.4-SNAPSHOT</version>
    </dependency>


### 설정

#### config.properties

dbdump_csvpath=[테스트데이터 위치]

    테스트데이터 위치
    상대경로는 {classpath}로 입력
    path를 입력하지 않으면 {classpath}/../../testdata로 설정됨.

dbdump_strategy=[설정 전략]

    raw, spring_datasource 중 하나를 선택
    [기본값 = spring_datasource]

#### raw 전략
 config.properties파일에 다음 4개의 변수를 입력

    dbdump_driver=[드라이버 클래스]
    dbdump_url=[데이터베이스 주소]
    dbdump_username=[DB접속유저]
    dbdump_password=[패스워드]

    ex)
    dbdump_strategy=raw
    dbdump_driver=org.h2.Driver
    dbdump_user=sa
    dbdump_url=jdbc:h2:tcp://localhost/~/NEXT-LectureManager
    dbdump_password=


#### spring_datasource 전략
 application.properties파일에서 4개의 값을 읽어옴

    spring.datasource.driverClassName=[드라이버 클래스]
    spring.datasource.url=[데이터베이스 주소]
    spring.datasource.username=[DB접속유저]
    spring.datasource.password=[패스워드]



