package lv.venta;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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
				Dalibnieks da1 = new Dalibnieks("test", "Anatolijs", "Asdfghjkl123!");
				Dalibnieks da2 = new Dalibnieks("test", "Kintija", "Asdfghjkl123!");
				dalibnieksRepo.save(da1);
				dalibnieksRepo.save(da2);
				
				Uzlabojumi uzl1 = new Uzlabojumi("Ūdens", 10, UzlabojumaTips.udens);
				Uzlabojumi uzl2 = new Uzlabojumi("Ūdens", 15, UzlabojumaTips.udens);
				Uzlabojumi uzl3 = new Uzlabojumi("Ātrums", 10, UzlabojumaTips.atrums);
				uzlabojumiRepo.save(uzl1);
				uzlabojumiRepo.save(uzl2);
				uzlabojumiRepo.save(uzl3);
				
				sasniegumiService.izmainitPupinuSkaitu(1, 100);
				uzlabojumiService.nopirktUzlabojumu(1, 1, 10);
				uzlabojumiService.nopirktUzlabojumu(2, 1, 15);
				//uzlabojumiService.nopirktUzlabojumu(1, 1, 20);
				uzlabojumiService.nopirktUzlabojumu(3, 1, 10);
			}
		};
	}
}
