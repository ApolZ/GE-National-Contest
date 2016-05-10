package application.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by ApolZ on 2016/5/10.
 */

@Entity
@Table(name="t_parameter")
@Data
@NoArgsConstructor
public class Parameter {

    @Id
    @GeneratedValue
    private Integer id;
    private String threshold;
}
