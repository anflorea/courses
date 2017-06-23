package ro.ubb.movies.core.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;
import ro.ubb.movies.core.model.BaseEntity;


import java.io.Serializable;

/**
 * Created by andrapop on 2017-04-10.
 */
@NoRepositoryBean
@Transactional
public interface PizzaOrderRepository<T extends BaseEntity<ID>, ID extends Serializable> extends JpaRepository<T, ID> {

}
