package org.biblioService.consumer.contrat.dao;

/**
 * DaoFactory
 * 
 * @author Oltenos
 *
 */
public interface DaoFactory {

	/**
	 * Retourne le UtilisateurDao
	 * 
	 * @return UtilisateurDao
	 */
	public UtilisateurDao getUtilisateurDao();

	/**
	 * Retourne le LivreDao
	 * 
	 * @return LivreDao
	 */
	public LivreDao getLivreDao();

	/**
	 * Retourne l'ExemplaireDao
	 * @return ExemplaireDao
	 */
	public ExemplaireDao getExemplaireDao();

	/**
	 * Retourne le PretDao
	 * @return PretDao
	 */
	public PretDao getPretDao();

	/**
	 * Retourne le EditeurDao
	 * @return EditeurDao
	 */
	public EditeurDao getEditeurDao();

	/**
	 * Retourne le DescriptionDao
	 * @return DescriptionDao
	 */
	public DescriptionDao getDescriptionDao();

	/**
	 * Retourne le AuteurDao
	 * @return AuteurDao
	 */
	public AuteurDao getAuteurDao();

	/**
	 * Retourne le GenreDao
	 * @return GenreDao
	 */
	public GenreDao getGenreDao();

	/**
	 * Retourne le ParagrapheDao
	 * @return ParagrapheDao
	 */
	public ParagrapheDao getParagrapheDao();

}
