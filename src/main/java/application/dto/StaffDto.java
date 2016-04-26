package application.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by AZ on 2016/4/26.
 */
@Data
@NoArgsConstructor
public class StaffDto {
    private String name;
    private String remarks;
    private String phoneNumber;
    private String picture;
}
