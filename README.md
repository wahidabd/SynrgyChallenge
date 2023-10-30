### 1. Buatlah json list example dari representasi data class berikut minimal 10 data

```kotlin
data class Student(
    val name: String,
    val age: Int,
    val image: String,
    val course: String,
    val createdAt: String
)
```

```json
{
  "students": [
    {
      "name": "Abd. Wahid",
      "age": 18,
      "image": "http://www.listercarterhomes.com/wp-content/uploads/2013/11/dummy-image-square.jpg",
      "course": "android",
      "createdAt": "2023-09-11T09:00:00.000Z"
    },
    {
      "name": "Andi",
      "age": 20,
      "image": "http://www.listercarterhomes.com/wp-content/uploads/2013/11/dummy-image-square.jpg",
      "course": "web",
      "createdAt": "2023-09-11T09:00:00.000Z"
    },
    {
      "name": "Imatus",
      "age": 20,
      "image": "http://www.listercarterhomes.com/wp-content/uploads/2013/11/dummy-image-square.jpg",
      "course": "backend",
      "createdAt": "2023-10-30T09:00:00.000Z"
    },
    {
      "name": "Yeni",
      "age": 30,
      "image": "http://www.listercarterhomes.com/wp-content/uploads/2013/11/dummy-image-square.jpg",
      "course": "web",
      "createdAt": "2023-10-12T09:00:00.000Z"
    },
    {
      "name": "Tono",
      "age": 21,
      "image": "http://www.listercarterhomes.com/wp-content/uploads/2013/11/dummy-image-square.jpg",
      "course": "android",
      "createdAt": "2023-09-15T09:00:00.000Z"
    },
    {
      "name": "Yono",
      "age": 23,
      "image": "http://www.listercarterhomes.com/wp-content/uploads/2013/11/dummy-image-square.jpg",
      "course": "android",
      "createdAt": "2023-05-02T09:00:00.000Z"
    },
    {
      "name": "Abdul",
      "age": 20,
      "image": "http://www.listercarterhomes.com/wp-content/uploads/2013/11/dummy-image-square.jpg",
      "course": "backend",
      "createdAt": "2023-03-30T09:00:00.000Z"
    },
    {
      "name": "Munif",
      "age": 32,
      "image": "http://www.listercarterhomes.com/wp-content/uploads/2013/11/dummy-image-square.jpg",
      "course": "web",
      "createdAt": "2023-05-14T09:00:00.000Z"
    },
    {
      "name": "Ari",
      "age": 21,
      "image": "http://www.listercarterhomes.com/wp-content/uploads/2013/11/dummy-image-square.jpg",
      "course": "web",
      "createdAt": "2023-09-12T09:00:00.000Z"
    },
    {
      "name": "Fajar",
      "age": 27,
      "image": "http://www.listercarterhomes.com/wp-content/uploads/2013/11/dummy-image-square.jpg",
      "course": "backend",
      "createdAt": "2023-06-12T09:00:00.000Z"
    }
  ]
}
```

### 2. Tanpa melihat slide ataupun referensi lain sebutkan maksud dari error code berikut: 100, 200, 300, 400, 500, dan 418

- 200 OK
- 400 Bad Request
- 500 Internal Server Error