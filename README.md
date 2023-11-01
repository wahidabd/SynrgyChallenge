### 1. tuliskan permission untuk mengakses internet, dan dimana kita harus meletakkan permission tersebut sampai aplikasi bisa mengakses internet?

AndroidManifest.xml
```xml
    <uses-permission android:name="android.permission.INTERNET" />
```

### 2. carilah dan tentukan Open/Free API yg bisa teman-teman gunakan sebagai data provider untuk challenge (jika ragu boleh konsultasikan di grup tele ya)
https://jikan.moe/

### 3. tuliskan cara mengakses REST API dengan library Retrofit untuk masing-masing method
- a. GET dengan parameter query "search" bertipe data string
```kotlin
    @GET("search/anime")
    fun searchAnime(
        @Query("q") query: String
    ): Call<SearchResponse>
```

- b. GET dengan parameter path "id" bertipe data Int
```kotlin
    @GET("anime/{id}")
    fun getAnime(
        @Path("id") id: Int
    ): Call<AnimeResponse>
```

- c. POST dengan parameter data berbentuk json dengan dua key "email" dengan tipe data string dan "password" dengan tipe data string
```kotlin
    data class LoginRequest(
        @SerializedName("email")
        val email: String,
        @SerializedName("password")
        val password: String
    )
```

```kotlin
    @POST("login")
    fun login(
        @Body body: LoginRequest
    ): Call<LoginResponse>
```

- d. PUT dengan parameter data berbentuk json dengan satu key "data" dengan tipe data string dan satu header authorization bearer 
```kotlin
    data class UpdateRequest(
        @SerializedName("data")
        val data: String
    )
```

```kotlin
    @PUT("update")
    fun update(
        @Header("Authorization") token: String,
        @Body body: UpdateRequest
    ): Call<UpdateResponse>
```

- e. DELETE dengan parameter path bernama "id" dengan tipe data integer
```kotlin
    @DELETE("delete/{id}")
    fun delete(
        @Path("id") id: Int
    ): Call<DeleteResponse>
```