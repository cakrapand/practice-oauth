# REST API with OAuth2 (TaskList App) 

## Get List of Tasks
`GET api/v1/tasks`
`Authorization: Bearer <access-token>`
### Response: 
```json
{
  "success": true,
  "message": "OK",
  "data": [
    {
      "id": "234d3a62-2d8d-4618-910f-e4aa747d8e95",
      "userId": "cakrapand@gmail.com",
      "name": "Task 1",
      "description": "description 1",
      "status": "active",
      "startDate": "2023-10-26T00:00:00.000+00:00",
      "endDate": "2023-10-26T00:00:00.000+00:00"
    },
    {
      "id": "41acf95a-297a-43a6-8a77-0b68e276a7a7",
      "userId": "cakrapand@gmail.com",
      "name": "Task 2",
      "description": "description 2",
      "status": "active",
      "startDate": "2023-10-26T00:00:00.000+00:00",
      "endDate": "2023-10-26T00:00:00.000+00:00"
    }
  ]
}
```

## Get a specific Task
`GET api/v1/tasks/{id}`
`Authorization: Bearer <access-token>`
````json
{
    "success": true,
    "message": "OK",
    "data": {
        "id": "234d3a62-2d8d-4618-910f-e4aa747d8e95",
        "userId": "cakrapand@gmail.com",
        "name": "Task 1",
        "description": "description 1",
        "status": "active",
        "startDate": "2023-10-26T00:00:00.000+00:00",
        "endDate": "2023-10-26T00:00:00.000+00:00"
    }
}
````



## Create a new Task 
`POST api/v1/tasks`
`Authorization: Bearer <access-token>`
### Request:
```json
{
    "name": "Task 1",
    "description": "description 1",
    "status": "active",
    "startDate": "2023-10-26",
    "endDate": "2023-10-26"
}
```

### Response:
```json
{
    "success": true,
    "message": "OK",
    "data": {
        "id": "234d3a62-2d8d-4618-910f-e4aa747d8e95",
        "userId": "cakrapand@gmail.com",
        "name": "Task 1",
        "description": "description 1",
        "status": "active",
        "startDate": "2023-10-26T00:00:00.000+00:00",
        "endDate": "2023-10-26T00:00:00.000+00:00"
    }
}
```


## Edit a Task
`PATCH api/v1/tasks/{id}`
`Authorization: Bearer <access-token>`
### Request
```json
{
    "name": "Edit Task 1",
    "description": "Edit description 1",
    "status": "active",
    "startDate": "2023-10-26",
    "endDate": "2023-10-26"
}
```

### Response
```json
{
    "success": true,
    "message": "UPDATED",
    "data": {
        "id": "234d3a62-2d8d-4618-910f-e4aa747d8e95",
        "userId": "cakrapand@gmail.com",
        "name": "Edit Task 1",
        "description": "Edit description 1",
        "status": "active",
        "startDate": "2023-10-26T00:00:00.000+00:00",
        "endDate": "2023-10-26T00:00:00.000+00:00"
    }
}
```