# [ SpringBoot & JPA 를 활용한 사진첩 구현 ]

## API

### 폴더 생성

- 요청

	`POST /folder`

		{
			"name" : "folder-name"
		}
		
	name 항목에 지정된 이름의 폴더를 생성합니다.
	
- 응답

	생성된 폴더의 ID(Long)를 응답합니다.
	
		1

### 폴더 조회

- 요청

	`GET /folder`
	
	아무런 변수를 지정하지 않으면 모든 폴더를 조회합니다.

- 응답

		[{
			"id" : folder-id,
			"name" : "folder-name",
			"regDate" : "date-registered
		},
		{
			...
		}]

- 요청
	
	`GET /folder?name=folder-name-to-find`

	name 변수를 지정하면 해당 이름과 일치하는 폴더를 조회합니다.
	
- 응답

		{
			"id" : folder-id,
			"name" : "folder-name-to-find",
			"regDate" : "date-registered"
		}
		
### 폴더 수정

- 요청
	
	`PUT /folder/{folder-id}`

		{
			"name" : "folder-name-to-change"
		}
	
	{folder-id}를 id로 갖는 폴더의 이름을 변경합니다.
	
- 응답

	

### 폻더 삭제
### 사진 저장
### 사진 정보 수정
### 사진 삭제
