package service.post;

import model.postModel.PostDto;
import model.postModel.PostRequestDto;

import static io.restassured.RestAssured.given;

public class UpdateService {
    PostRequestDto postRequestDto;
    protected static final String UPDATE_POST_BY_ID= "/post/{id}";
    public PostDto update(PostRequestDto postRequestDto, String postId){
        return given()
                .body(postRequestDto)
                .pathParam("id", postId)
                .when().put(UPDATE_POST_BY_ID).getBody().as(PostDto.class);
    }
}
