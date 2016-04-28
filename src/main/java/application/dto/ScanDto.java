package application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by ApolZ on 2016/4/28.
 */

@Data
@NoArgsConstructor
public class ScanDto {
    private String result;
    private String name;
    private String position;
    private String picture;
}
