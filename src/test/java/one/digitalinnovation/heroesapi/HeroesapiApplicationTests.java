package one.digitalinnovation.heroesapi;
import org.springframework.boot.test.context.SpringBootTest;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import one.digitalinnovation.heroesapi.model.Herois;
import one .digitalinnovation.heroesapi.repository.RepositoryHeroes;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import  static one.digitalinnovation.heroesapi.constants.HeroesConstants.HEROES_ENDPOINT_LOCAL;

import java.util.List;

@RunWith(SpringRunner.class)
@DirtiesContext
@AutoConfigureWebTestClient
@SpringBootTest

public class HeroesapiApplicationTests {

	@Autowired
	WebTestClient webTestClient;

	@Autowired
	RepositoryHeroes repositoryHeroes;

	@Test
	public void testOneHeroById(){
		webTestClient.get().uri(HEROES_ENDPOINT_LOCAL.concat("/{id}"),"2")
				.exchange()
				.expectStatus().isOk()
				.expectBody();
	}

	@Test
	public  void heroNotFound (){
		webTestClient.get().uri(HEROES_ENDPOINT_LOCAL.concat("/{id}"),"100")
				.exchange()
				.expectStatus().isNotFound();
	}
}
