package dao;

import meserreurs.MonException;
import java.util.*;

import metier.*;
import persistance.*;

public class Service {

	// Mise à jour des caractéristiques d'un adhérent
	// Le booleen indique s'il s'agit d'un nouvel adhérent, auquel cas on fait
	// une création

	public void insertAdherent(Adherent unAdherent) throws MonException {
		String mysql;

		DialogueBd unDialogueBd = DialogueBd.getInstance();
		try {
			mysql = "insert into adherent  (nom_adherent,prenom_adherent,ville_adherent)  " + "values ('"
					+ unAdherent.getNomAdherent();
			mysql += "'" + ",'" + unAdherent.getPrenomAdherent() + "','" + unAdherent.getVilleAdherent() + "')";

			unDialogueBd.insertionBD(mysql);
		} catch (MonException e) {
			throw e;
		}
		catch (Exception exc) {
			throw new MonException(exc.getMessage(), "systeme");
		}
	}
	public void majAdherent(Adherent unAdherent) throws MonException {
		String mysql;

		DialogueBd unDialogueBd = DialogueBd.getInstance();
		try {
			mysql = "insert into adherent  (nom_adherent,prenom_adherent,ville_adherent)  " + "values ('"
					+ unAdherent.getNomAdherent();
			mysql += "'" + ",'" + unAdherent.getPrenomAdherent() + "','" + unAdherent.getVilleAdherent() + "')";

			unDialogueBd.insertionBD(mysql);
		} catch (MonException e) {
			throw e;
		}
		catch (Exception exc) {
			throw new MonException(exc.getMessage(), "systeme");
		}
	}

	// gestion des adherents
	// Consultation d'un adhérent par son numéro
	// Fabrique et renvoie un objet adhérent contenant le résultat de la requête
	// BDD
	public Adherent consulterAdherent(int numero) throws MonException {
		
		 Map mParams = new HashMap();
	     Map mParam;
	  try
	  {
		String mysql = "select * from adherent where numero_adherent=?";
		 mParam = new HashMap();
	     mParam.put(1, numero);
	     mParams.put(0, mParam);
		List<Adherent> mesAdh = consulterListeAdherents(mysql);
		if (mesAdh.isEmpty())
			return null;
		else {
			return mesAdh.get(0);
		}
	  } catch (MonException e)
		{
			throw e;
		}
	}

	// Consultation des adhérents
	// Fabrique et renvoie une liste d'objets adhérent contenant le résultat de
	// la requête BDD
	public List<Adherent> consulterListeAdherents() throws MonException {
		String mysql = "select * from adherent order by nom_adherent";
		return consulterListeAdherents(mysql);
	}

	private List<Adherent> consulterListeAdherents(String mysql) throws MonException {
		List<Object> rs;
		List<Adherent> mesAdherents = new ArrayList<Adherent>();
		int index = 0;
		try {
			DialogueBd unDialogueBd = DialogueBd.getInstance();
			rs =unDialogueBd.lecture(mysql);
			while (index < rs.size()) {
				// On crée un stage
				Adherent unA = new Adherent();
				// il faut redecouper la liste pour retrouver les lignes
				unA.setIdAdherent(Integer.parseInt(rs.get(index + 0).toString()));
				unA.setNomAdherent(rs.get(index + 1).toString());
				unA.setPrenomAdherent(rs.get(index + 2).toString());
				unA.setVilleAdherent(rs.get(index + 3).toString());
				// On incrémente tous les 3 champs
				index = index + 4;
				mesAdherents.add(unA);
			}

			return mesAdherents;
		} catch (MonException e) {
			throw e;
		}
		catch (Exception exc) {
			throw new MonException(exc.getMessage(), "systeme");
		}
	}

public Utilisateur getUtilistateur(String nom) throws MonException
{
	List<Object> rs;
	Utilisateur unUti = null;
	String mysql = "SELECT numUtil,nomUtil, MotPasse, role  FROM utilisateur  " +
			" where nomUtil = " + "'" + nom +"'";
	int index = 0;
	try {
		DialogueBd unDialogueBd = DialogueBd.getInstance();
		rs =unDialogueBd.lecture(mysql);
		while (index < rs.size()) {
			unUti = new Utilisateur();
			// il faut redecouper la liste pour retrouver les lignes
			unUti.setNumUtil(Integer.parseInt(rs.get(index + 0).toString()));
			unUti.setNomUtil(rs.get(index + 1).toString());
			unUti.setMotPasse(rs.get(index + 2).toString());
			unUti.setRole(rs.get(index + 3).toString());
			// On incrémente tous les 3 champs
			index = index + 4;
		}
		return unUti;
	} catch (MonException e) {
		throw e;
	}
	catch (Exception exc) {
		throw new MonException(exc.getMessage(), "systeme");
	}
}

/*
	Gestion des oeuvres
 */

	public List<Oeuvrevente> consulterListeOeuvres() throws MonException {
		String mysql = "select * from oeuvrevente order by titre_oeuvrevente";
		return consulterListeOeuvres(mysql);
	}

	private List<Oeuvrevente> consulterListeOeuvres(String mysql) throws MonException {
		List<Object> rs;
		List<Oeuvrevente> mesOeuvres = new ArrayList<Oeuvrevente>();
		int index = 0;
		try {
			DialogueBd unDialogueBd = DialogueBd.getInstance();
			rs =unDialogueBd.lecture(mysql);
			while (index < rs.size()) {
				System.out.println(rs.get(index + 1).toString());
				// On crée un stage
				Oeuvrevente oeuvre = new Oeuvrevente();
				// il faut redecouper la liste pour retrouver les lignes
				oeuvre.setIdOeuvrevente(Integer.parseInt(rs.get(index + 0).toString()));
				oeuvre.setTitreOeuvrevente(rs.get(index + 1).toString());
				oeuvre.setEtatOeuvrevente(rs.get(index + 2).toString());
				oeuvre.setPrixOeuvrevente((Float) rs.get(index + 3));
				oeuvre.setProprietaire(getProprietaire((Long) rs.get(index + 4)));
				// On incrémente tous les 3 champs
				index = index + 5;
				mesOeuvres.add(oeuvre);
			}

			return mesOeuvres;
		} catch (MonException e) {
			throw e;
		}
		catch (Exception exc) {
			throw new MonException(exc.getMessage(), "systeme");
		}
	}

	public Proprietaire getProprietaire(Long idProp) throws MonException
	{
		List<Object> rs;
		Proprietaire prop = null;
		String mysql = "SELECT id_proprietaire,nom_proprietaire, prenom_proprietaire FROM proprietaire  " +
				" where id_proprietaire = " + "'" + idProp +"'";
		int index = 0;
		try {
			DialogueBd unDialogueBd = DialogueBd.getInstance();
			rs =unDialogueBd.lecture(mysql);
			while (index < rs.size()) {
				prop = new Proprietaire();
				// il faut redecouper la liste pour retrouver les lignes
				prop.setIdProprietaire(Integer.parseInt(rs.get(index + 0).toString()));
				prop.setNomProprietaire(rs.get(index + 1).toString());
				prop.setPrenomProprietaire(rs.get(index + 2).toString());
				// On incrémente tous les 3 champs
				index = index + 3;
			}
			return prop;
		} catch (MonException e) {
			throw e;
		}
		catch (Exception exc) {
			throw new MonException(exc.getMessage(), "systeme");
		}
	}

	public Oeuvrevente consulterOeuvre(int numero) throws MonException {

		Map mParams = new HashMap();
		Map mParam;
		try
		{
			System.out.println(numero);
			String mysql = "select * from oeuvrevente where id_oeuvrevente="+numero+"";
			mParam = new HashMap();
			mParam.put(1, numero);
			mParams.put(0, mParam);
			List<Oeuvrevente> mesOeuvres = consulterListeOeuvres(mysql);
			if (mesOeuvres.isEmpty())
				return null;
			else {
				return mesOeuvres.get(0);
			}
		} catch (MonException e)
		{
			throw e;
		}
	}

	public List<Proprietaire> consulterListeProprietaires() throws MonException {
		String mysql = "select * from proprietaire order by nom_proprietaire";
		List<Object> rs;
		List<Proprietaire> mesProprietaires = new ArrayList<Proprietaire>();
		int index = 0;
		try {
			DialogueBd unDialogueBd = DialogueBd.getInstance();
			rs =unDialogueBd.lecture(mysql);
			while (index < rs.size()) {
				// On crée un stage
				Proprietaire prop = new Proprietaire();
				// il faut redecouper la liste pour retrouver les lignes
				prop.setIdProprietaire(Integer.parseInt(rs.get(index + 0).toString()));
				prop.setNomProprietaire(rs.get(index + 1).toString());
				prop.setPrenomProprietaire(rs.get(index + 2).toString());
				index = index + 3;
				mesProprietaires.add(prop);
			}

			return mesProprietaires;
		} catch (MonException e) {
			throw e;
		}
		catch (Exception exc) {
			throw new MonException(exc.getMessage(), "systeme");
		}
	}
}
