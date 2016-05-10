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

    @Query("select t from Tool t where t.name like %?1% or t.toolID like %?1%")
    List<Tool> keyQuery(String key);

    @Query("select t from Tool t where t.name like %?1% or t.toolID like %?1% group by t.name")
    List<Tool> keyQueryWithSingleName(String key);

    Tool findByToolID(String toolID);

    List<Tool> findByName(String name);

    @Query("select count(t) from Tool t")
    Integer countTotalNumber();

    /*Good Broken Lost OnUsing*/
    @Query("select count(t) from Tool t where t.status = ?1")
    Integer countExactStatus(String status);

    @Query("select count(t) from Tool t where t.name = ?1")
    Integer countTotalNumber(String name);

    /*Good Broken Lost*/
    @Query("select count(t) from Tool t where t.status = ?1 and t.name = ?2 ")
    Integer countExactStatus(String status, String name);

    @Query("select t from Tool t where t.status = 'Broken' and t.brokenOrLostDate like %?1%")
    List<Tool> findbrokenToolForGivenYear(String year);

    @Query("select t from Tool t where t.status = 'Lost' and t.brokenOrLostDate like %?1%")
    List<Tool> findLostToolForGivenYear(String year);


}
