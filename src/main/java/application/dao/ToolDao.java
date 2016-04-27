package application.dao;

import application.entity.Tool;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by ApolZ on 2016/4/27.
 */

@Repository
public interface ToolDao extends CrudRepository<Tool, Integer> {

    @Query("select t from Tool t where t.name like %?1% or t.toolID like %?1% ")
    List<Tool> keyQuery(String key);

    Tool findByToolID(String toolID);
}
