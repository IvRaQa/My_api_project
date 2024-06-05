package service.post;

import model.postModel.PostDto;
import model.postModel.PostRequestDto;

import static io.restassured.RestAssured.given;

public class PostService {

    PostRequestDto postRequestDto;
    protected static final String CREATE_POST= "post/create";
    protected static final String POST_BY_ID= "/post/{id}";

    public PostDto create(PostRequestDto postRequestDto){
        return given().body(postRequestDto)
                .when().post(CREATE_POST).getBody().as(PostDto.class);
    }



}
