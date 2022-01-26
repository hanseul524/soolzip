# :beer: sool.zip
:baby: 이태욱, 임진영, 장재웅, 정한슬, 최지현<br>
:calendar: 2021.08.16 ~ 2021.10.11

## Contents

- [개요](#개요)
- [개발환경](#개발환경)
- [DB 설계](#db-설계)
- [프로젝트 기능구현](#프로젝트-기능구현)

### 개요

> sool.zip은 정리되지 않고 오래된 레시피들을 관리해 기존의 정보보다 편리함과 다양함이 업그레이드된 정보를 제공하며, 
술에 취미가 있고 좋아하는 애주가들이 모여 서로 소통하고 혼자 알기 아까운 꿀팁을 공유하는 등의 긍정적인 역할을 기대하며, 프로젝트 방향성을 잡았다.

### 개발환경

<span><img src="https://img.shields.io/badge/Java-blue?style=flat-square&logo=Java&logoColor=white"/></span>&nbsp;
<span><img src="https://img.shields.io/badge/Visual Studio Code-9cf?style=flat-square&logo=Visual Studio Code&logoColor=white"/></span>&nbsp;
<span><img src="https://img.shields.io/badge/Eclipse IDE-blueviolet?style=flat-square&logo=Eclipse IDE&logoColor=white"/></span>&nbsp;
<span><img src="https://img.shields.io/badge/Html-red?style=flat-square&logo=HTML5&logoColor=white"/></span>&nbsp;
<span><img src="https://img.shields.io/badge/CSS-blue?style=flat-square&logo=CSS3&logoColor=white"/></span>&nbsp;
<span><img src="https://img.shields.io/badge/JavaScript-brightgreen?style=flat-square&logo=JavaScript&logoColor=white"/></span>&nbsp;
<span><img src="https://img.shields.io/badge/jQuery-lightgray?style=flat-square&logo=jQuery&logoColor=white"/></span>&nbsp;
<span><img src="https://img.shields.io/badge/Oracle-orange?style=flat-square&logo=Oracle&logoColor=white"/></span>&nbsp;
<span><img src="https://img.shields.io/badge/Apache Tomcat-red?style=flat-square&logo=Apache Tomcat&logoColor=white"/></span>&nbsp;
<span><img src="https://img.shields.io/badge/Bootstrap-blueviolet?style=flat-square&logo=Bootstrap&logoColor=white"/></span>&nbsp;
<span><img src="https://img.shields.io/badge/Github-black?style=flat-square&logo=GitHub&logoColor=white"/></span>&nbsp;

### DB 설계

![스크린샷(141)](https://user-images.githubusercontent.com/91312627/151177289-bc17012d-c582-47b3-aa04-fa564df96776.png)
https://www.erdcloud.com/d/uPmxAyJdK3Xsbur9X


### 프로젝트 기능구현

![image](https://user-images.githubusercontent.com/91312627/151177885-3cacb64c-ce85-4986-a18e-7e0848154b95.png)
![image](https://user-images.githubusercontent.com/91312627/151177946-fbd7d6bc-5607-449c-82c2-154f8436e520.png)
![image](https://user-images.githubusercontent.com/91312627/151178011-6f514bfa-c14c-463d-8651-5f2dff5daf90.png)


1. 회원 비밀번호찾기 
- 회원이 비밀번호찾기 클릭시 회원가입과정에서 등록한 이메일을 통해 임시빌밀번호를 발송하게하였다. 
	이후 비밀번호변경은 필수사항이아닐 선택사항으로 클라이언트의 번거로움을 줄이고 신속하게 재사용이 가능하다.  
2. 명예의전당
- 관리자페이지에서 조회수와 좋아요수가 가장 높은 레시피를 10개 선정해 투표를 시작하면 사용자들이 투표를 진행한다. 
	   그 중 가장 투표수가 많은 레시피는 명예의 전당 레시피로 등록되어, 사용자들이 직접뽑아 검증된 레시피의 정보를 받아볼 수 있다.   
3. 투표기능 
- 투표가 시작되면 사용자들이 메인페이지나 메뉴를 통해 투표현황 페이지에서 투표와 각 레시피의 득표수 확인 가능하다.  
	한사람당 한표를 행사할 수 있고, 투표기간내에는 투표 취소가 가능하다. 
4. 레시피 등록 
-  레시피 등록시에 재료과정 추가등록이 가능하다. 추가등록을 통해 원하는 레시피를 보기쉽고 디테일하게 등록할 수 있게 되었으며, 필수등록사항 미기재 시 전체공개가 불가능하다. 
		이를 통해서는 클라이언트가 원하는 정보를 신속하게 받아볼 수 있는 카테고리별 정보를 제공할 수 있게되었다. 
5. 쪽지기능 
- 회원페이지에서 쪽지 보내기를 클릭하면 쪽지 제목과 내용을 입력하여 쪽지를 보낼 수 있고, 마이페이지에서 받은 쪽지와 보낸 쪽지를 수신할 수 있다.
                  쪽지 기능을 통해 회원들간의 커뮤니케이션을 활성화 시킬 수 있다.
                  
