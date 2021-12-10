# [ SpringBoot & JPA 를 활용한 사진첩 구현 ]

## API

### 폴더 생성

- 요청

	`POST /folder`

		{
			"name" : "folder-name"
		}
		
	지정된 이름의 폴더를 생성합니다. 폴더 이름은 중복 될 수 없습니다.
	
- 응답

	 성공 시 생성된 폴더의 ID(Long 타입 )를 응답합니다.

### 폴더 조회

- 요청

	`GET /folder`
	
	아무런 변수를 지정하지 않으면 모든 폴더를 조회합니다.

- 응답

		[{
			"id" : folder-id,
			"name" : "folder-name",
			"regDate" : "date-registered"
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

	 성공 시 해당 id를 응답합니다.

### 폻더 삭제

- 요청
	
	`DELETE /folder/{folder-id}`

	{folder-id}를 id로 갖는 폴더를 삭제합니다. 폴더에 포함되어있는 사진은 일괄 삭제됩니다.
	
- 응답

	 성공 시 해당 id를 응답합니다.

### 사진 저장

- 요청

	`POST /photo`

		{
			"name" : "photo-name",
			"memo" :
			"folder" : 1
		}
		
	작성된 정보로 사진을 저장합니다. 사진의 이름은 중복 될 수 없으며, 존재하지 않는 폴더에는 사진을 저장 할 수 없습니다.
	
- 응답

	 성공 시 생성된 사진의 ID를 응답합니다.

### 사진 조회

- 요청

	`GET /photo`
	
	아무런 변수를 지정하지 않으면 모든 사진을 조회합니다.

- 응답

		[{
			"id" : photo-id,
			"name" : "photo-name",
			"memo" : "photo-memo",
			"regDate" : "date-registered".
			"folder" : 1
		},
		{
			...
		}]

- 요청
	
	`GET /folder?name=photo-name`

	name 변수를 지정하면 해당 이름과 일치하는 사진을 조회합니다.
	
- 응답

		{
			"id" : photo-id,
			"name" : "photo-name-to-find",
			"memo" : "photo-memo",
			"regDate" : "date-registered",
			"folder" : 1	
		}
		
- 요청

	`GET /photo?folder=1`
	
	folder 변수를 지정하면 해당 숫자를 폴더 ID로 갖는 폴더에 포함된 모든 사진을 조회합니다.

- 응답

		[{
			"id" : photo-id,
			"name" : "photo-name",
			"memo" : "photo-memo",
			"regDate" : "date-registered".
			"folder" : 1
		},
		{
			...
		}]

### 사진 수정

- 요청
	
	`PUT /photo/{photo-id}`

		{
			"name" : "photo-name-to-change",
			"memo" : "photo-memo-to-change",
			"folder" : 2
		}
	
	{photo-id}를 id로 갖는 사진의 정보를 변경합니다.
	
- 응답

	 성공 시 해당 사진의 ID를 응답합니다.

### 폻더 삭제

- 요청
	
	`DELETE /photo/{photo-id}`

	{photo-id}를 ID로 갖는 폴더를 삭제합니다. 폴더에 포함되어있는 사진은 일괄 삭제됩니다.
	
- 응답

	 성공 시 해당 id를 응답합니다.
	 
