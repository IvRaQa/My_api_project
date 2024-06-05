package model.ownerModel;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@With
public class Location {
    private String street;
    private String city;
    private String state;
    private String country;
    private String timezone;
}
