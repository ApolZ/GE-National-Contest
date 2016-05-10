package application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by ApolZ on 2016/5/10.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ToolStorageDto {

    private String result;
    private String totalNumber;
    private String goodNumber;
    private String brokenNumber;
    private String lostNumber;
    private String picture;
    private String description;
    private String price;
}
