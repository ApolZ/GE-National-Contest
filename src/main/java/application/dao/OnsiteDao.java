package application.dao;

import application.entity.Onsite;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by ApolZ on 2016/4/28.
 */

@Repository
public interface OnsiteDao extends CrudRepository<Onsite, Integer> {

    List<Onsite> findByEngineerID (String engineerID);

    @Query("select o from Onsite o where o.toolID = ?1 and o.returnTime is null ")
    Onsite unreturnedRowByToolID (String toolID);

    List<Onsite> findByOrderID (String orderID);
}
