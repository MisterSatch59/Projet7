package org.biblioService.consumer.impl.dao;

import java.util.List;

import javax.inject.Named;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.biblioService.consumer.contrat.dao.LangueDao;
import org.springframework.jdbc.core.JdbcTemplate;

@Named("langueDao")
public class LangueDaoImpl extends AbstractDaoImpl implements LangueDao {
	private static final Logger LOGGER = LogManager.getLogger(LangueDaoImpl.class);

	@Override
	public List<String> getLangues() {
		// Recherche dans la base de données
		String vSQL = "SELECT * FROM public.langue";
		
		JdbcTemplate vJdbcTemplate = new JdbcTemplate(getDataSource());

		List<String> vListLangue = vJdbcTemplate.queryForList(vSQL, String.class);

		LOGGER.traceExit("vListLangue = " + vListLangue);
		return vListLangue;
	}

}
