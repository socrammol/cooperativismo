package br.com.cooperativismo.repository;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.cooperativismo.domain.Pauta;
import br.com.cooperativismo.service.RetornoPautaDTO;

@Repository
public class VotoRepositoryCustom {
	@Autowired
	PautaRepository pautaRepository;
	@PersistenceContext
	private EntityManager em;

	public List<RetornoPautaDTO> buscas(Long id) {
		StringBuilder sql = new StringBuilder();

		sql.append(
				"SELECT p.id,p.nome,p.ativo,p.aberto ,(select count(voto.voto) from voto where voto.voto = 'Sim' and voto.pauta_id = p.id) as sim, (select count(voto.voto) from voto where voto.voto = 'não' and voto.pauta_id = p.id) as nao FROM PAUTA  as p");
		sql.append(" inner join pauta_voto as pt on pt.pauta_id = p.id ");
		sql.append(" inner join voto as v on v.id = pt.voto_id  ");
		if (id != null) {
			sql.append(" where p.id = " + id);
		}
		sql.append(" group by p.nome");

		Query query = em.createNativeQuery(sql.toString());

		List<Object[]> listResultado = query.getResultList();
		em.close();

		return listResultado.stream().map(pauta -> {
			RetornoPautaDTO retorno = new RetornoPautaDTO();
			Long idPauta = ((BigInteger) pauta[0]).longValue();
			Optional<Pauta> p = pautaRepository.findById(idPauta);
			retorno.setPauta(p);
			if ((Boolean) pauta[3]) {
				retorno.setAberto("Sim");
			} else {
				retorno.setAberto("Não");
			}
			if ((Boolean) pauta[2]) {
				retorno.setAtivo("Sim");
			} else {
				retorno.setAtivo("Não");
			}
			retorno.setVotosSim(((BigInteger) pauta[4]).longValue());
			retorno.setVotosNao(((BigInteger) pauta[5]).longValue());
			return retorno;
		}).collect(Collectors.toList());

	}

}
