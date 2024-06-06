package tests;

import config.Config;
import io.restassured.response.Response;
import model.postModel.PostRequestDto;
import model.postModel.PostDto;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import service.post.DeleteService;
import service.post.GetService;
import service.post.PostService;
import service.post.UpdateService;
import testListener.TestListener;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
@Listeners(TestListener.class)
public class PostTests extends Config {
    //dodati paginaciju

    SoftAssert softAssert;


    @BeforeMethod
    public void setUp() {
        softAssert = new SoftAssert();
    }
    @Test
    public void getListOfAllPosts() {

        PostRequestDto postRequestDto = PostRequestDto.createPost();

        PostDto postDto = new PostService().create(postRequestDto);

        List<PostDto> response = new GetService().get();
        boolean isInTheList = false;

        for (int i = 0; i < response.size(); i++) {

            if (response.get(i).getId().equals(postDto.getId())) {
                isInTheList = true;
                System.out.println("Created post is in the list");
                break;
            }
        }
        softAssert.assertTrue(isInTheList);
    }
    @Test
    public void getListOfPostsByUser() {

        PostRequestDto postRequestDto = PostRequestDto.createPost();

        PostDto postDto = new PostService().create(postRequestDto);

        String ownerId = postDto.getOwner().getId();

        List<PostDto> response = new GetService().get(ownerId);

        boolean isInTheList = false;

        for (int i = 0; i < response.size(); i++) {

            if (response.get(i).getId().equals(postDto.getId())) {
                isInTheList = true;
                System.out.println("Created post is in the owner's list");
                break;
            }
        }
        softAssert.assertTrue(isInTheList);
        softAssert.assertEquals(postRequestDto.getOwnerId(),ownerId);//ovo zakomentarisati; trebalo je samo radi provere
        softAssert.assertAll();
    }
    @Test
    public void getListOfPostsByTag() {

        PostRequestDto postRequestDto = PostRequestDto.createPost();

        PostDto postDto = new PostService().create(postRequestDto);

        String[]dtoTags = postDto.getTags();

        Response response = new GetService().getPostsByTag(dtoTags[2]);

        String actual=(postRequestDto.getTags())[2];

        softAssert.assertEquals(response.getStatusCode(), 200);
        softAssert.assertEquals(actual,dtoTags[2]);
        softAssert.assertAll();
    }
    @Test
    public void getPostById() {

        PostRequestDto postRequestDto = PostRequestDto.createPost();

        PostDto postDto = new PostService().create(postRequestDto);

        String postId = postDto.getId();

        Response response = new GetService().getPostById(postId);

        String actualId = response.jsonPath().get("id");

        softAssert.assertEquals(actualId, postId);
    }
    @Test
    public void createPost() {

        PostRequestDto postRequestDto = PostRequestDto.createPost();

        PostDto postDto = new PostService().create(postRequestDto);

        softAssert.assertEquals(postDto.getText(), postRequestDto.getText());
        softAssert.assertEquals(postDto.getTags(), postRequestDto.getTags());
        softAssert.assertAll();
    }
    @Test
    public void updatePost() {

        PostRequestDto postRequestDto = PostRequestDto.createPost();

        PostDto postDto = new PostService().create(postRequestDto);

        String updatedText = "Updated text";
        String updatedLikes = "20";
        String[] updatedTags = {"Knjige", "Velike macke", "Lav"};

        PostRequestDto updatedPostRequestDto = postRequestDto
                .withLikes(updatedLikes)
                .withText(updatedText)
                .withTags(updatedTags);

        String postId = postDto.getId();

        PostDto updatedResponse = new UpdateService().update(updatedPostRequestDto, postId);

        softAssert.assertEquals(updatedResponse.getLikes(), updatedLikes);
        softAssert.assertEquals(updatedResponse.getText(), updatedText);
        softAssert.assertEquals(updatedResponse.getTags(), updatedTags);
        softAssert.assertAll();
    }
    @Test
    public void deletePost() {

        PostRequestDto postRequestDto = PostRequestDto.createPost();

        PostDto postDto = new PostService().create(postRequestDto);

        String postId = postDto.getId();

        Response response = new DeleteService().delete(postRequestDto, postId);

        softAssert.assertEquals(response.getStatusCode(), 200);
        softAssert.assertEquals(response.jsonPath().get("id"), postId);
        softAssert.assertAll();

        Response errorResponse = new DeleteService().delete(postRequestDto, postId);

        softAssert.assertEquals(errorResponse.getStatusCode(), 404, "RESOURCE_NOT_FOUND");

    }

}

