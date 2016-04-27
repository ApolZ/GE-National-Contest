package application.dto;

import application.entity.Order;
import application.entity.Tool;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by ApolZ on 2016/4/27.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ToolDto {
    private String result;
    private List<Tool> tools;
}
