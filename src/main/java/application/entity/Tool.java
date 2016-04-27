package application.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by ApolZ on 2016/4/27.
 */

@Entity
@Table(name="t_tool")
@Data
@NoArgsConstructor
public class Tool {
    @Id
    @GeneratedValue
    private Integer id;
    private String toolID;
    private String name;
    private String purchaseDate;
    private String serviceLife;
    private String picture;
    private String status;
    private String description;
    private String toolcenterID;
    private String minQuant;
    private String price;
    private String brokenOrLostDate;


    @Transient
    private String result;

}
