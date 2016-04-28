package application.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by ApolZ on 2016/4/28.
 */

@Entity
@Table(name="t_onsite")
@Data
@NoArgsConstructor

public class Onsite {
    @Id
    @GeneratedValue
    private Integer id;
    private String toolkeeperID;
    private String toolboxID;
    private String engineerID;
    private String toolID;
    private String address;
    private String lendingTime;
    private String returnTime;
    private String returnStatus;
    private String remarks;
    private String orderID;
    @Transient
    private String result;

}
