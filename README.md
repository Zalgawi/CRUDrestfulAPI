# Springboot CRUD restful API

A simple Java Springboot API that contains GET, POST, PUT & DELETE Methods.

## Examples

#### POST


```bash
{
    "url": "https://www.inserturl.com",
    "title": "-insert title here-",
    "thumbnail": "https://www.inserturl.com/img.jpg",
    "type": "manuals" | "videos"
}
```

#### GET

```
localhost:8040/admin-help-service/help-card/cards/05ef8c48-2b6e-4375-8b21-b0072f57fb06
```
```bash
{
    "id": "05ef8c48-2b6e-4375-8b21-b0072f57fb06",
    "url": "https://www.inserturl.com",
    "title": "first title example",
    "thumbnail": "https://www.inserturl.com/img.jpg",
    "type": "videos"
}
```
#### GET ALL

```
localhost:8040/admin-help-service/help-card/cards
```
```bash
{
    "id": "05ef8c48-2b6e-4375-8b21-b0072f57fb06",
    "url": "https://www.inserturl.com",
    "title": "first title example",
    "thumbnail": "https://www.inserturl.com/img.jpg",
    "type": "videos"
},
{
    "id": "05ef8c48-2b6e-4375-8b21-b0072f55f3rt",
    "url": "https://www.inserturl.com",
    "title": "first title example",
    "thumbnail": "https://www.inserturl.com/img.jpg",
    "type": "manuals"
}
```

#### PUT
```bash
{
        "id": "05ef8c48-2b6e-4375-8b21-b0072f57fb06",
        "url": "https://www.inserturl.com",
        "title": "first title example changed",
        "thumbnail": "https://www.firstimagurl.com/img.jpg",
        "type": "videos"
 }
```

#### DELETE
```
localhost:8040/admin-help-service/help-card/05ef8c48-2b6e-4375-8b21-b0072f57fb06
```
```bash
{
    (deleted)
}
```

## Usage

```bash
METHODS: GET = get current list of cards | POST = add new card | DELETE = delete card,  PUT = edit card
```

In order connect the frontend to the API, you will need to locally run this project, and then use the following API URL:

```bash
http://localhost:8040/admin-help-service/help-card/cards
```


## Links
More examples can be seen on [Postman](https://www.getpostman.com/collections/00b881cc92dcab81c360)
