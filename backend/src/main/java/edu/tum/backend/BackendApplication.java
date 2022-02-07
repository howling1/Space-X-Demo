package edu.tum.backend;

import com.mongodb.client.MongoClient;
import edu.tum.backend.Service.ShipService;
import edu.tum.backend.repository.ShipRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackageClasses = ShipRepository.class)
public class BackendApplication implements CommandLineRunner{

	private static final Logger log = LoggerFactory.getLogger(BackendApplication.class);

	@Autowired
	MongoClient mongoClient;

	@Autowired
	ShipService shipService;

	public static void main(String[] args) { SpringApplication.run(BackendApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("MongoClient = " + mongoClient.getClusterDescription());

		shipService.deleteAllShips();

	}
}
