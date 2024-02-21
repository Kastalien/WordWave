package com.example.wordwave.data

import android.util.Log
import com.example.wordwave.data.RetrofitInstance.api
import com.google.gson.annotations.SerializedName
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

data class Quote (
    @SerializedName("id") var id: Int? = null,
    @SerializedName("dialogue") var dialogue : Boolean? = null,
    @SerializedName("private") var private : Boolean? = null,
    @SerializedName("tags") var tags : ArrayList<String> = arrayListOf(),
    @SerializedName("url") var url : String? = null,
    @SerializedName("favorites_count") var favoritesCount  : Int? = null,
    @SerializedName("upvotes_count") var upvotesCount    : Int? = null,
    @SerializedName("downvotes_count") var downvotesCount  : Int? = null,
    @SerializedName("author") var author : String? = null,
    @SerializedName("author_permalink") var authorPermalink : String? = null,
    @SerializedName("body") var body : String? = null,
    var isLiked : Boolean? = null,
    var isFav : Boolean? = null
)

data class QuoteData (
    @SerializedName("qotd_date") var qotdDate : String? = null,
    @SerializedName("quote") var quote : Quote? = Quote()
)

data class QuoteList (
    @SerializedName("page") var page: Int? = null,
    @SerializedName("last_page") var lastPage : Boolean? = null,
    @SerializedName("quotes") var quotes : ArrayList<Quote> = arrayListOf()
    )

data class UserRequest (
    @SerializedName("user") var user : User? = User()
)

data class UserResponse (
    @SerializedName("User-Token") var userToken : String? = null,
    @SerializedName("login") var login:String? = null,
    @SerializedName("email") var email:String? = null
)

data class User (
    @SerializedName("login") var login : String? = null,
    @SerializedName("password") var password : String? = null
)

data class UserDetails (
    @SerializedName("favorite" ) var favorite : Boolean? = null,
    @SerializedName("upvote" ) var upVote : Boolean? = null,
    @SerializedName("downvote" ) var downVote : Boolean? = null
)

data class FavResponse (
    @SerializedName("id"           ) var id          : Int?         = null,
    @SerializedName("author"       ) var author      : String?      = null,
    @SerializedName("body"         ) var body        : String?      = null,
    @SerializedName("user_details" ) var userDetails : UserDetails? = UserDetails()
)

interface ApiService {
    @GET("/api/qotd")
    suspend fun getQotd(): QuoteData
    @GET("/api/quotes")
    suspend fun getQuotes(): QuoteList
    @GET("/api/quotes")
    suspend fun getFavQuotes(@Query("filter") userName: String, @Query("type") type: String, @Query("page") page: Int): QuoteList //filter=gose&type=user
    @POST("/api/session")
    fun getSession(@Body userRequest: UserRequest): Call<UserResponse>
    @PUT("/api/quotes/{quote_id}/fav")
    fun makeFav(@Header("User-Token") token: String, @Path("quote_id") quotaId: Int): Call<FavResponse>
    @PUT("/api/quotes/{quote_id}/upvote")
    fun like(@Header("User-Token") token: String, @Path("quote_id") quotaId: Int): Call<FavResponse>
}
object favQuotes {
    var likeCount=0

    suspend fun getAllQuotas(): List<Quote> {
        var page = 1
        var allQuotes: MutableList<Quote> = emptyList<Quote>().toMutableList()
        do {
            val apiService = RetrofitInstance.api
            val response = apiService.getFavQuotes(Credentials.favqsUsername, "user", page)
            allQuotes.addAll(response.quotes)
            Log.d("quote", "page ${response.page} is last ${response.lastPage}")
            page++
        } while (response.lastPage == false)
        return allQuotes
    }
}