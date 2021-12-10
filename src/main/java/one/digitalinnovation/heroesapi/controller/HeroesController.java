package one.digitalinnovation.heroesapi.controller;

import com.amazonaws.Response;
import one.digitalinnovation.heroesapi.model.Herois;
import one.digitalinnovation.heroesapi.repository.RepositoryHeroes;
import one.digitalinnovation.heroesapi.services.ServiceHerois;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import lombok.extern.slf4j.Slf4j;

import static one.digitalinnovation.heroesapi.constants.HeroesConstants.ENDPOINT_DYNAMO;
import static  one.digitalinnovation.heroesapi.constants.HeroesConstants.HEROES_ENDPOINT_LOCAL;


@RestController
@Slf4j
public class HeroesController {

    ServiceHerois serviceHerois;
    RepositoryHeroes repositoryHeroes;

    private static final org.slf4j.Logger log =
            org.slf4j.LoggerFactory.getLogger(HeroesController.class);

    public HeroesController (ServiceHerois serviceHerois, RepositoryHeroes repositoryHeroes){
        this.repositoryHeroes = repositoryHeroes;
        this.serviceHerois = serviceHerois;
    }

    @GetMapping(HEROES_ENDPOINT_LOCAL)
    public  Flux <Herois> getAllItens (){
        log.info("Todos hérois, de todos universos");
        return serviceHerois.findAll();
    }

    @GetMapping(HEROES_ENDPOINT_LOCAL + "/{id}")
    public Mono <ResponseEntity<Herois>> getById (@PathVariable String id){
        log.info("O Héroi selecionado no id {} ",id);
        String notFound = "Não encontrado";
        return serviceHerois.findbyIdHero(id)
                .map((item)->new ResponseEntity<>(item, HttpStatus.OK))
                .defaultIfEmpty(  new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping(HEROES_ENDPOINT_LOCAL)
    @ResponseStatus(HttpStatus.CREATED)
    public Mono <Herois> createHero (@RequestBody Herois heroi){
        log.info("Herói  {} com id {} adicionado na tabela",heroi.getName(), heroi.getId());
        return  serviceHerois.save(heroi);
    }

    @DeleteMapping(HEROES_ENDPOINT_LOCAL + "/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public  Mono<HttpStatus> deleteById (@PathVariable String id){
        serviceHerois.deleteById(id);
        log.info("O Herói  com id {} foi deletado", id);
        return  Mono.just(HttpStatus.OK);
    }

}
