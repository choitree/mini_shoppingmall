# 상품들의 다양한 최저가를 조회하는 서비스
1. 상품의 카테고리별 최저가를, 모든 카테고리에서 합산하여 파악하는 기능
2. 각각 브랜드에서 카테고리별로 한 가지씩 상품을 선택하여 가장 낮은 금액을 조합할 수 있는 브랜드와 금액을 파악하는 기능 
3. 특정 카테고리의 최저가, 최고가 및 브랜드의 정보를 조회하는 기능
4. 상품 추가 기능
5. 상품 수정 기능
6. 상품 삭제 기능

---
## 사용한 기술
- Spring Boot 2.6.4
- Java 11
- JPA 
- QueryDSL 5.0.0
- QLRM 1.7.1
- Swagger API 3.0.0
- Lombok 1.8.22
- MySQL 8.0.28
- Testcontainer 1.17.3

---
## 실행 방법
### 1. Docker-compose
- Docker 및 Docker-compose가 설치되어 있어야 합니다.
```
clone https://github.com/choitree/mileage.git
cd mission
./gradlew assemble
docker-compose up -d --build
```
### 2. Local
- MySQL 8.0.29 이상 버전이 설치되어 있어야 합니다.
- URL 및 user 정보는 applicaion.yml 파일에서 확인 가능합니다.
```
clone https://github.com/choitree/mileage.git
cd mission
./gradlew bootrun
```
---
## 고려 사항 
- 1+n 쿼리가 실행되지 않도록, querydsl과 native query를 혼용하여 사용했습니다.
- 데이터를 우선 추출하고, 요구사항에 맞춰 서비스단에서 가공했습니다.
- API 응답에 실패할 경우, 간단한 메시지와 상태값을 전달합니다.
- 테스트 코드 작성 시
  - SERVICE에서 요구사항 내 API은 단순 조회이기 때문에 MockBean을 사용했습니다.
  - 생성, 수정, 삭제 API의 경우 연관관계로 인해서, 빈을 주입받아 사용했습니다.