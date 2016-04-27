package application.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CollectionId;

import javax.persistence.*;

/**
 * Created by ApolZ on 2016/4/27.
 */

@Entity
@Table(name="t_order")
@Data
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue
    private Integer id;
    private String orderID;
    private String onsiteTime;
    private String location;
    private String title;
    private String description;
    private String reservationTime;
    private String clientID;
    private String requestAcceptTime;
    private String managerID;
    private String engineerConfirmTime;
    private String dutyEngineerID;
    private String predictedReturnTime;
    private String whetherExpress;
    private String otherEngineer;
    private String toolsReservationTime;
    private String toolsReservationID;
    private String value;
    private String keeperConfirmTime;
    private String toolkeeperID;
    private String taskBeginTime;
    private String taskEndTime;
    private String taskStatus;
    private String clientConfirmTime;
    private String clientStatus;
    private String managerConfirmTime;
    private String managerStatus;

    @Transient
    private String result;
}
