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
public class YearStatisticsDto {
    private String result;
    private String lossPieces;
    private String lossValue;
    private String brokenPieces;
    private String brokenValue;
    private String lostPieces;
    private String lostValue;
}
