package application.dao;

import application.entity.Staff;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by ApolZ on 2016/4/27.
 */

@Repository
public interface StaffDao extends CrudRepository<Staff, Integer> {

    Staff findByStaffID(String staffID);

    @Query("select s from Staff s where s.name like %?1% and s.position = 'Engineer' ")
    List<Staff> searchForEngineers (String name);
}
