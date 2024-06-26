package lv.venta;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import lv.venta.model.Dalibnieks;
import lv.venta.model.UzlabojumaTips;
import lv.venta.model.Uzlabojumi;
import lv.venta.repo.IDalibnieksRepo;
import lv.venta.repo.IKoksRepo;
import lv.venta.repo.IPirkumiRepo;
import lv.venta.repo.ISasniegumiRepo;
import lv.venta.repo.IUzlabojumiRepo;
import lv.venta.service.impl.DalibnieksCRUDService;
import lv.venta.service.impl.KoksService;
import lv.venta.service.impl.PirkumiService;
import lv.venta.service.impl.SasniegumiService;
import lv.venta.service.impl.UzlabojumiService;

@SpringBootApplication
public class KursaDarbsApplication {

	public static void main(String[] args) {
		SpringApplication.run(KursaDarbsApplication.class, args);
	}

	@Bean
	public CommandLineRunner testDatabaseLayer(IDalibnieksRepo dalibnieksRepo, IKoksRepo koksRepo, IPirkumiRepo pirkumiRepo, ISasniegumiRepo sasniegumiRepo, IUzlabojumiRepo uzlabojumiRepo,
			DalibnieksCRUDService dalibnieksService, KoksService koksService, PirkumiService pirkumiService, SasniegumiService sasniegumiService, UzlabojumiService uzlabojumiService)
	{
		return new CommandLineRunner() {
			
			@Override
			public void run(String... args) throws Exception {
				PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
				Dalibnieks da1 = new Dalibnieks("USER", "Anatolijs", encoder.encode("Asdfghjkl123!"));
				Dalibnieks da2 = new Dalibnieks("ADMIN", "Kintija", encoder.encode("Asdfghjkl123!"));
				dalibnieksRepo.save(da1);
				dalibnieksRepo.save(da2);
				
				for (int i = 0; i < 200; i++) {
					uzlabojumiService.jaunsUzlabojums("Ūdens", (10 + 10 * i), UzlabojumaTips.udens);
				}
				for (int i = 0; i < 100; i++) {
					uzlabojumiService.jaunsUzlabojums("Ātrums", (10 + 5 * i), UzlabojumaTips.atrums);
				}
				for (int i = 0; i < 50; i++) {
					uzlabojumiService.jaunsUzlabojums("Autonoms", (10 + 50 * i), UzlabojumaTips.autonoms);
				}
				
				sasniegumiService.izmainitPupinuSkaitu(1, 100000);
				sasniegumiService.tiekUzspiestsUzKoka(1);//101
				uzlabojumiService.nopirktUzlabojumu(1, 1);//91
				uzlabojumiService.nopirktUzlabojumu(2, 1);//71
				uzlabojumiService.nopirktUzlabojumu(201, 1);//61
				
				sasniegumiService.tiekUzspiestsUzKoka(1);//63
				sasniegumiService.tiekUzspiestsUzKoka(1);//65
				sasniegumiService.tiekUzspiestsUzKoka(1);//67
				
				uzlabojumiService.nopirktUzlabojumu(301, 1);//57
			}
		};
	}
}
