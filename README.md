# [ SpringBoot & JPA 를 활용한 사진첩 구현 ]

## API

1. 폴더 생성

	name 항목에 폴더 이름을 요청합니다.

	`POST /folder`

		{"name" : "folder name"}

	생성된 폴더의 ID(Long)를 응답합니다.

2. 폴더 조회
	
	아무런 변수를 지정하지 않으면 모든 폴더를 조회합니다.

	`GET /folder`

생성된 폴더의 ID(Long)를 응답합니다.


	4. 폴더 수정
	5. 폻더 삭제
2. 사진 관리
	1. 사진 저장
	2. 사진 정보 수정
	3. 사진 삭제
