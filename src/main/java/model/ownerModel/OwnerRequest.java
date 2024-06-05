package model.ownerModel;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.github.javafaker.Faker;
import lombok.*;

import java.util.Locale;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@With
public class OwnerRequest {

    private String firstName;
    private String lastName;
    private String email;
    private Location location;

    public static OwnerRequest createOwner() {
        Faker faker = new Faker(new Locale("en-US"));

        return OwnerRequest.builder()
                .email(faker.internet().emailAddress())
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .build();

    }

}
