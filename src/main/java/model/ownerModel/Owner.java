package model.ownerModel;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)

public class Owner {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private Location location;

    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd")
    private Date registerDate;

    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd")
    private Date updatedDate;
}
