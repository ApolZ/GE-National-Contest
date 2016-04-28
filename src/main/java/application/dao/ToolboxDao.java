package application.dao;

import application.entity.Toolbox;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by ApolZ on 2016/4/28.
 */

@Repository
public interface ToolboxDao extends CrudRepository<Toolbox, Integer> {

    Toolbox findByToolboxID(String toolboxID);
}
