package application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by ApolZ on 2016/4/27.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginDto {
    private String result;
    private String position;
}
