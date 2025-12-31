## A. 프로젝트 실행 방법

### 1. docker-compose 실행
```shell
## docker-compose.yml 파일이 있는 경로로 이동
> docker-compose up -d
```

### 2. docker container 실행 확인
```shell
> docker ps
```
```text
CONTAINER ID   IMAGE             COMMAND                  CREATED              STATUS              PORTS                      NAMES
04852caa7037   mongo:latest      "docker-entrypoint.s…"   About a minute ago   Up About a minute   0.0.0.0:27017->27017/tcp   mongo-db
d4151d43af92   postgres:latest   "docker-entrypoint.s…"   About a minute ago   Up About a minute   0.0.0.0:5432->5432/tcp     postgres-db
```

## B. 프로젝트 운영

### 1. 브랜치 관리
- main에서 develop 생성
- feature 브랜치는 무조건 develop에서 생성
  - 브랜치명 : feature/{기능명}
- 각 브랜치에서 작업 후 develop으로 먼저 merge 후 동작 확인
  - 병합 후 feature 브랜치는 삭제
- 하나의 큰 기능이 완성 되었을 때 최종적으로 main에 merge
