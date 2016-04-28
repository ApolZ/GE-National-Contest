package application.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by ApolZ on 2016/4/28.
 */

@Entity
@Table(name="t_toolbox")
@Data
@NoArgsConstructor
public class Toolbox {
    @Id
    @GeneratedValue
    private Integer id;
    private String toolboxID;
    private String boxModel;
    private String purchaseDate;
    private String serviceLife;
    private String picture;
    private String status;
    private String description;
    private String brokenOrLostDate;

    @Transient
    private String result;
}
