package one.digitalinnovation.heroesapi.repository;

import one.digitalinnovation.heroesapi.model.Herois;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

@EnableScan
public interface RepositoryHeroes extends CrudRepository <Herois, String> {

}
