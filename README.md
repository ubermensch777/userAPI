# (Docker link)[https://github.com/ubermensch777/userAPI-docker]
# API endpoints
URL = `http://localhost:8000`

| Endpoint               | Method     | Notes                             | Example body                                                            |
|------------------------|------------|-----------------------------------|-------------------------------------------------------------------------|
| `/auth/authenticate`   | **POST**   |                                   | `{"username": "user1", "password": "password"}`                         |
| `/auth/register`       | **POST**   |                                   | `{"username": "user1", "password": "password"}`                         |
|                        |            |                                   | .                                                                       |
| `/user/`               | **GET**    | returns all users                 |                                                                         |
| `/user/{id}`           | **GET**    |                                   |                                                                         |
| `/user/`               | **PUT**    |                                   | `{"id": 1, "username": "user1", "password": "password", "age": 55}`     |
| `/user/{id}`           | **DELETE** | safe delete: `setIsActive(false)` |                                                                         |
|                        |            |                                   | .                                                                       |
| `/content/`            | **POST**   |                                   | `{"body": "This is some content!"}`                                     |
| `/content/`            | **GET**    | returns all Content               |                                                                         |
| `/content/{id}`        | **GET**    |                                   |                                                                         |
| `/content/author/{id}` | **GET**    |                                   |                                                                         |
| `/content/`            | **PUT**    |                                   | `{"id": 2, "body": "Here is an example of UPDATED Content with id 2."}` |
| `/content/{id}`        | **DELETE** |                                   |                                                                         |

# Example requests
## /user
### POST
```
http://localhost:8000/auth/register
```
```
{
    "username": "user1",
    "password": "password"
}
```
### POST
```
http://localhost:8000/auth/authenticate
```

```
{
    "username": "user1",
    "password": "password"
}
```
Возвращает AuthToken `eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyMSIsImlhdCI6MTcwMTA5MDc2MSwiZXhwIjoxNzAxMDkyMjAxfQ.zvU5VnjwKiMR6ew4dokujU2MF7YnR3GH_cIHlA2zRcE`


---

***Авторизация*** требуется везде, кроме `/register`, `/authenticate` В запросе во вкладке ***Authorization*** надо передать ***токен*** полученный в ***/authenticate***

---

### GET
Without ID returns all users.
```
http://localhost:8000/user/
```

```
[
    {
        "id": 1,
        "username": "user1",
        "password": "$2a$10$rdaffXC4RslJVWaFkl2LYuql1CZ/ejv8Q4BOEtsW3jZTQ0B/X.eeu",
        "age": 55,
        "isActive": false
    },
    {
        "id": 2,
        "username": "user2",
        "password": "$2a$10$ti.DNYcFDPP6ODYx6iuWA.tWvPPvZxiIM54ufXr6PFEbQmDqpBWfa",
        "age": null,
        "isActive": true
    }
]
```

### PUT
**ID** must be provided in **UserDto**
```
http://localhost:8000/user/
```
```
{
    "id":1,
    "username": "user1",
    "password": "password",
        "age": 55
}
```
### DELETE
```
http://localhost:8000/user/2
```
safe delete with *isActive=false*
```
{
        "id": 1,
        "username": "user1",
        "password": "$2a$10$rdaffXC4RslJVWaFkl2LYuql1CZ/ejv8Q4BOEtsW3jZTQ0B/X.eeu",
        "age": 55,
        "isActive": false
}
```
---

## /content
### POST
req
```
http://localhost:8000/content/
```
```
{
    "body": "This is some content3!"
}
```

res
```
{
    "id": 3,
    "body": "This is some content3!",
    "author": {
        "id": 1,
        "username": "user1",
        "password": "$2a$10$rdaffXC4RslJVWaFkl2LYuql1CZ/ejv8Q4BOEtsW3jZTQ0B/X.eeu",
        "age": 55,
        "isActive": true
    }
}
```


### GET
Without ID returns all content
```
http://localhost:8000/content/
```

```
[
    {
        "id": 1,
        "body": "This is some content1!",
        "author": {
            "id": 1,
            "username": "user1",
            "password": "$2a$10$rdaffXC4RslJVWaFkl2LYuql1CZ/ejv8Q4BOEtsW3jZTQ0B/X.eeu",
            "age": 55,
            "isActive": true
        }
    },
    {
        "id": 2,
        "body": "Here is an example of UPDATED Content with id 2.",
        "author": {
            "id": 1,
            "username": "user1",
            "password": "$2a$10$rdaffXC4RslJVWaFkl2LYuql1CZ/ejv8Q4BOEtsW3jZTQ0B/X.eeu",
            "age": 55,
            "isActive": true
        }
    }
]
```


### PUT
**ID** must be provided in **ContentDto**
req
```
http://localhost:8000/content/
```

```
{
    "id": 2,
    "body": "Here is an example of UPDATED Content with id 2."
}
```

res
```
{
    "id": 2,
    "body": "Here is an example of UPDATED Content with id 2.",
    "author": {
        "id": 1,
        "username": "user1",
        "password": "$2a$10$rdaffXC4RslJVWaFkl2LYuql1CZ/ejv8Q4BOEtsW3jZTQ0B/X.eeu",
        "age": 55,
        "isActive": true
    }
}
```
### DELETE
Only content author can delete.
```
http://localhost:8000/content/3
```

```
Successfully deleted!
```
