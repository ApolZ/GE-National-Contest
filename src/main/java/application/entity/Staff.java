package application.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by ApolZ on 2016/4/27.
 */

@Entity
@Table(name="t_staff")
@Data
public class Staff {
    @Id
    @GeneratedValue
    private Integer id;
    private String staffID;
    private String name;
    private String hireDate;
    private String password;
    private String picture;
    private String position;
    private String remarks;
    @Column(name="phone")
    private String phoneNumber;

    @Transient
    private String result;

}
