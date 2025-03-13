<div align="center">
  <img src="https://github.com/user-attachments/assets/6ba93bab-c9eb-48da-94d3-844ade8b44d8" alt="image" width="300">
  <br>
  <h2>Java기반 웹프로잭트 풀스택 수업 Final 프로젝트</h2>
  <br>
  <strong>"원활한 커뮤니케이션으로 향한 첫 걸음"</strong>
  <hr>
</div>


## 📜 프로젝트 소개
**개발기간**: 2024.11.14 ~ 2024.12.13 <br>
**프로젝트명**: Corporate Integrated System(CIS) <br>
<br>
Corporate Integrated System(CIS)은 **Spring Boot**를 기반으로 개발된 **기업용 통합환경 시스템**입니다.  
주요 기능으로는 회원 게시판, 사내 이메일, 근태 관리, 관리자 기능 등이 포함되어 있으며, 기업 내 소통과 관리 프로세스를 지원합니다.

---

## 🛠 담당한 주요 기능

### 1. 게시판 기능

- **CRUD 구현**: 등록(Write), 조회(Read), 수정(Update), 삭제(Delete)
- **파일 첨부(업로드/다운로드)**: 기능 구현
- **게시글 검색/페이징** 기능 구현

해당 기능들을 개발하면서 **MyBatis 매퍼 작성**, **Controller-Service-Repository 레이어** 간 데이터 흐름을 중점적으로 구현했습니다.

---

### 2. CI/CD 자동 배포 파이프라인

- **GitHub Actions**를 이용한 자동 빌드·배포 스크립트 작성
- **Gradle 빌드 → Docker 이미지 생성 → Docker Hub 푸시**로 이어지는 단계별 워크플로 작성
- **AWS EC2**에서 Docker 컨테이너 실행
  - `docker pull` 후 `docker run -d`로 최신 이미지를 배포
  - MySQL 컨테이너와의 네트워크 연결, 환경 변수 설정 등도 담당
- 서버 재시작 시 자동 재배포를 위한 스크립트 개선

이를 통해 **코드 변경 → push → 자동 빌드 & 배포** 흐름을 구축할 수 있었고, 개발 생산성이 크게 향상되었습니다.



## 💻 프로젝트 내 사용 기술 스택

### **Frontend**
- **HTML/CSS/JavaScript**: UI 디자인 및 상호작용 구현
- **Thymeleaf**: Spring Boot와의 템플릿 엔진 통합

### **Backend**
- **Java**: Spring Boot 기반 애플리케이션 개발
- **Spring Boot**: 애플리케이션의 핵심 프레임워크
- **MyBatis**: 데이터 매핑 및 ORM 지원
- **Gradle**: 프로젝트 빌드 및 의존성 관리

### **Database**
- **MySQL**: 데이터 저장 및 관리  
- **DBeaver**: 데이터베이스 관리 도구

### **Version Control**
- **Git/GitHub**: 코드 형상 관리 및 협업

### **Development Tools**
- **IntelliJ IDEA**: 통합 개발 환경(IDE)  
- **Visual Studio Code**: 코드 편집 및 프론트엔드 작업

### **CI/CD & Deployment**
- **GitHub Actions**: 자동 빌드·배포를 위한 CI/CD 파이프라인 구축
- **Docker**: 컨테이너 기반 배포 (어플리케이션 및 DB 컨테이너 관리)
- **AWS EC2**: Docker 컨테이너를 구동하는 서버 환경
- **Docker Hub**: 도커 이미지를 저장·배포

## 📌 프로젝트 주요 기능

1. **회원 게시판**: 사내 소통을 위한 게시판 기능(CRUD) 제공  
2. **사내 이메일 시스템**: 사내 이메일 전송 및 관리  
3. **근태 관리**: 직원 근태 정보 효율적으로 관리  
4. **관리자 기능**: 회원 정보 및 게시판 관리  

---

## 📑 프로젝트 결과물

- **발표 자료**: [프로젝트 발표자료](https://www.canva.com/design/DAGZhVj4ND8/a8uZV7mzYtYJbl1dd79_IA/edit)
- **GitHub Repository**: [CIS Repository](https://github.com/KIMMZN/CIS)

---

## 🏆 느낀 점 및 성과

- **문제 해결 경험**: 데이터 매핑 오류를 디버깅하며 백엔드와 프론트엔드 간 데이터 흐름의 중요성을 배웠습니다.
- **협업의 중요성**: Git을 활용한 코드 형상 관리와 팀원 간 협업을 통해 프로젝트를 효율적으로 완수할 수 있었습니다.
- **기술 숙련도**: Spring Boot, MyBatis, MySQL 등 주요 기술 스택에 대한 이해도를 높였습니다.
- **자동화 배포 환경 구축**: CI/CD 파이프라인과 컨테이너 기반 서버 운영으로 **개발→배포** 과정을 효율적으로 만들었음
---

## 📧 문의

- **Email**: [tkdlqhrm222@gmail.com](mailto:tkdlqhrm222@gmail.com)
- **GitHub**: [KIMMZN](https://github.com/KIMMZN)
