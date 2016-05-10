package application.dto;

import application.entity.Onsite;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by ApolZ on 2016/5/10.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OnsiteInfoDto {
    private String result;
    private String orderID;
    private String toolkeeperID;
    private String toolboxID;
    private String address;
    private List<Onsite> list;
}
