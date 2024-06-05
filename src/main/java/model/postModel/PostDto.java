package model.postModel;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import model.ownerModel.Owner;

import java.util.Date;

@With
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {

    private String id;
    private String image;
    private String likes;
    private String link;
    private String [] tags= new String[3];
    private String text;
    private Owner owner;

    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern = "YYYY-MM-DD")
    private Date publishDate;
    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern = "YYYY-MM-DD")
    private Date updatedDate;


}
