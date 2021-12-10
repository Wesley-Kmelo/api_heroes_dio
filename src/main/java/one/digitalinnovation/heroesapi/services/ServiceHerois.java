package one.digitalinnovation.heroesapi.services;

import one.digitalinnovation.heroesapi.model.Herois;
import one.digitalinnovation.heroesapi.repository.RepositoryHeroes;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
public class ServiceHerois {

    private final RepositoryHeroes repositoryHeroes;

    public ServiceHerois (RepositoryHeroes repositoryHeroes){
        this.repositoryHeroes = repositoryHeroes;
    }

    public Flux<Herois> findAll(){
        return  Flux.fromIterable(this.repositoryHeroes.findAll());
    }

    public Mono <Herois> findbyIdHero(String id){
        return Mono.justOrEmpty(this.repositoryHeroes.findById(id));
    }

    public Mono <Herois> save(Herois heroi){
        return Mono.justOrEmpty(this.repositoryHeroes.save(heroi));
    }

    public  Mono <Boolean> deleteById(String id){
        repositoryHeroes.deleteById(id);
        return Mono.justOrEmpty(true);
    }


}
