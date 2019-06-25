## Environment
1. Java version: 1.8.0+
2. Default Encoding: UTF-8
3. Default File System: Linux

## IDE
1. IntelliJ
    * `Settings -> Editor -> Code Style -> Manage -> Import -> IntelliJ IDEA code style XML` 설정에서 `config/idea/intellij-codestyle.xml` file 추가
    * `Settings -> Editor -> Inspections -> Manage -> Import` 설정에서 `config/idea/intellij-inspections.xml` file 추가
2. Checkstyle Plugin
    * `Settings -> Other Settings -> Checkstyle` 설정에서 `config/checkstyle/checkstyle.xml` file 추가

## Git Branch 전략
> [git flow](https://danielkummer.github.io/git-flow-cheatsheet/index.ko_KR.html) 을 따릅니다

## Comment Log Convention
* 패스워드나 보안 관련 파일은 반드시 커밋하지 말고 암호화
* 커밋시 아래 포맷 사용
```plain
{이슈URL} {제목}
```
