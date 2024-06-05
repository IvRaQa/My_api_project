package service.post;

import io.restassured.response.Response;
import model.postModel.PostDto;
import model.postModel.PostRequestDto;

import java.util.List;

import static io.restassured.RestAssured.given;

public class GetService {
    PostRequestDto postRequestDto;
    protected static final String GET_ALL_POSTS = "post";
    protected static final String GET_POSTS_BY_USER_ID = "/user/{id}/post";
    protected static final String GET_POSTS_BY_TAG = "/tag/{id}/post";
    protected static final String GET_POST_BY_ID = "/post/{id}";

    public List<PostDto> get() {
        return given()
                .when().get(GET_ALL_POSTS).jsonPath().getList("data", PostDto.class);
    }

    public List<PostDto> get(String ownerId) {
        return given()
                .pathParam("id", ownerId)
                .when().get(GET_POSTS_BY_USER_ID).jsonPath().getList("data", PostDto.class);
    }

    public Response getPostById(String postId) {
        return given()
                .pathParam("id", postId)
                .when().get(GET_POST_BY_ID);
    }

    public Response getPostByTag(String postId) {
        return given()
                .pathParam("id", postId)
                .when().get(GET_POSTS_BY_TAG);
    }
}
