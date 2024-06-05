package service.post;

import io.restassured.response.Response;
import model.postModel.PostRequestDto;

import static io.restassured.RestAssured.given;

public class DeleteService {
    PostRequestDto postRequestDto;
    protected static final String DELETE_POST_BY_ID= "/post/{id}";
    public Response delete(PostRequestDto postRequestDto, String postId){
        return given()
                .pathParam("id", postId)
                .when().delete(DELETE_POST_BY_ID);
    }
}
