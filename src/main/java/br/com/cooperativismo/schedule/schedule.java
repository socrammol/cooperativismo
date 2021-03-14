package br.com.cooperativismo.schedule;

import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.com.cooperativismo.domain.Pauta;
import br.com.cooperativismo.repository.PautaRepository;

@Component
public class schedule {
	private static final Logger logger = LoggerFactory.getLogger(schedule.class);
	@Autowired
	PautaRepository pautaRepository;

	@Scheduled(cron = "0 * * * * ?")
	public void inativaPauta() {
		List<Pauta> pautas = pautaRepository.findByAtivo(true);
		for (Pauta pauta : pautas) {
			if (pauta.getAberto() && pauta.getFim().isBefore(LocalDateTime.now())) {
				pauta.setAtivo(false);
				pautaRepository.save(pauta);
				logger.info("a pauta:" + pauta.getNome() + " com id:" + pauta.getId() + " foi fializadaO");
			}
		}
	}

}
