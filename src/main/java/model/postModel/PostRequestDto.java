package model.postModel;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.javafaker.Faker;
import lombok.*;
import model.ownerModel.OwnerRequest;
import service.owner.PostOwner;

import java.util.Locale;

import static io.restassured.RestAssured.given;

@With
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostRequestDto {

    private String image;
    private String likes;
    private String [] tags= new String[3];
    private String text;
    @JsonProperty("owner")
    private String ownerId;

    public static PostRequestDto createPost(){

        Faker faker = new Faker(new Locale("en-Us"));

        OwnerRequest ownerRequest = OwnerRequest.createOwner();

        return PostRequestDto.builder()
                .image(faker.internet().avatar())
                .likes("7")
                .tags(new String[]{faker.lorem().word(), faker.lorem().word(),faker.lorem().word()})
                .text(faker.lorem().sentence(4))
                .ownerId(new PostOwner().createOwner(ownerRequest).getId())
                .build();

    }
}
