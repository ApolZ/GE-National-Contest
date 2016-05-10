package application.dao;

import application.entity.Parameter;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by ApolZ on 2016/5/10.
 */

@Repository
public interface ParameterDao extends CrudRepository<Parameter,Integer> {

}
