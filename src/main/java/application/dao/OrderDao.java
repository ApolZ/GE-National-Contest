package application.dao;

import application.entity.Order;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by ApolZ on 2016/4/27.
 */

@Repository
public interface OrderDao extends CrudRepository<Order, Integer> {

    List<Order> findByClientID(String clientID);

    Order findByOrderID(String orderID);

    @Query("select o from Order o where ( o.dutyEngineerID is null or o.dutyEngineerID = ?1 ) and  ( o.managerID is not null ) ")
    List<Order> engineerQuery(String engineerID);
}
