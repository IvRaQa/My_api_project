package service.post;

import io.restassured.response.Response;
import model.postModel.PostDto;
import model.postModel.PostRequestDto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class GetService {
    PostRequestDto postRequestDto;
    protected static final String GET_ALL_POSTS = "post";
    protected static final String GET_POSTS_BY_USER_ID = "/user/{id}/post";
    protected static final String GET_POSTS_BY_TAG = "/tag/{tag}/post";
    protected static final String GET_POST_BY_ID = "/post/{id}";
    protected static final Map<String,String> map = new HashMap<>();

    public static void setPage(String page) {
        map.put("page", page);
    }
    public static void setLimit(String limit) {
        map.put("limit", limit);
    }

    public List<PostDto> get() {
        return given()
                .queryParams(map)
                .when().get(GET_ALL_POSTS).jsonPath().getList("data", PostDto.class);
    }

    public List<PostDto> get(String ownerId) {
        return given()
                .queryParams(map)
                .pathParam("id", ownerId)
                .when().get(GET_POSTS_BY_USER_ID).jsonPath().getList("data", PostDto.class);
    }

    public Response getPostById(String postId) {
        return given()
                .pathParam("id", postId)
                .when().get(GET_POST_BY_ID);
    }

    public Response getPostsByTag(String tag) {
        return given()
                .queryParams(map)
                .pathParam("tag", tag)
                .when().get(GET_POSTS_BY_TAG);
    }
}
