package application.dto;

import application.entity.Staff;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by AZ on 2016/4/26.
 */
@Data
@NoArgsConstructor
public class SearchEngineerDto {
    private String result;
    private List<Staff> engineers;
}
