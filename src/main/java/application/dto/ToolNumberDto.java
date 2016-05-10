package application.dto;

import application.entity.Tool;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by ApolZ on 2016/5/10.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ToolNumberDto {
    private String toolNumber;
    private String toolID;
    private String name;
    private String picture;
    private String description;
}
