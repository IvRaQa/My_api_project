package service.owner;

import model.ownerModel.Owner;
import model.ownerModel.OwnerRequest;

import static io.restassured.RestAssured.given;

public class PostOwner {
    private static final String CREATE_OWNER = "/user/create";
    OwnerRequest ownerRequest;
    public Owner createOwner (OwnerRequest ownerRequest){
        return given()
                .body(ownerRequest)
                .when().post(CREATE_OWNER).getBody().as(Owner.class);
    }
}
