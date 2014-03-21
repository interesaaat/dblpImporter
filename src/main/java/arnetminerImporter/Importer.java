package arnetminerImporter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;

import org.apache.log4j.PropertyConfigurator;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import schema.Person;
import utils.HibernateUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Importer 
{
	public void importer(String configFile_) {
		importer(configFile_, 1);
	}
	@SuppressWarnings("unchecked")
	public void importer(String configFile_, int id_) {
		try {
			Logger logger = LoggerFactory.getLogger(Importer.class);
			PropertyConfigurator.configure("log4j.properties");
			HibernateUtil.buildSessionFactory(configFile_);
			Session session = HibernateUtil.getSessionFactory().openSession();

			Person p = null;
			long numberOfRows = 0;

			logger.info("Retrieving the number of rows...");
			numberOfRows = (Long)session.createCriteria("schema.Person").setProjection(Projections.rowCount()).uniqueResult();
			for (int i = id_; i <= numberOfRows; i++) {

				if (session.getTransaction().isActive() == false) {
	        		session.beginTransaction();
	        	}
				logger.info("Retrieving the person with id " + i + "...");
				p = (Person)session.createQuery("from Person where id = " + i).uniqueResult();
				if(p != null) {
					JSONObject jsonObject = null;

					logger.info("Getting information on " + p.getId() + ", " + p.getName() + " from Arnetminer.org");
					System.out.println("Getting information on " + p.getId() + ", " + p.getName() + " from Arnetminer.org");
	
					jsonObject = getPersonFromArnetminer(p.getName(), logger);

					if (jsonObject != null) {
						if (!jsonObject.isEmpty()) {
							p.setPhone((String)jsonObject.get("Phone"));
							p.setEmail((String)jsonObject.get("Email"));
							p.setHomepage((String)jsonObject.get("Homepage"));
							p.setPosition((String)jsonObject.get("Position"));
							p.setAffiliation((String)jsonObject.get("Affiliation"));
							p.setAddress((String)jsonObject.get("Address"));
							p.setBio((String)jsonObject.get("Bio"));
							p.setPosition((String)jsonObject.get("Position"));
							p.setPhdUniv((String)jsonObject.get("Phduniv"));
							p.setPhdMajor((String)jsonObject.get("Phdmajor"));
							p.setPhdDate((String)jsonObject.get("Phddate"));
							p.setMsUniv((String)jsonObject.get("Msuniv"));
							p.setMsMajor((String)jsonObject.get("Msmajor"));
							p.setMsDate((String)jsonObject.get("Msdate"));
							p.setBsUniv((String)jsonObject.get("Bsuniv"));
							p.setBsMajor((String)jsonObject.get("Bsmajor"));
							p.setBsDate((String)jsonObject.get("Bsddate"));
		
							System.out.println("Saving " + p.getName());
							System.out.println("...");
							session.save(p);
							session.getTransaction().commit();
						}
					} else {
						i--;
					}
				}
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws Exception {
	    if (args.length < 1) {
	       System.out.println("Usage importer: java ArnetminerImporter [input]");
   	    } else if (args.length < 2) { 
	       String fileName = args[0];
	       System.out.println("Starting Arnetminer importer");
	       new Importer().importer(fileName);
	    } else {
		    String fileName = args[0];
		    int startingId = Integer.parseInt(args[1]);
	       System.out.println("Starting Arnetminer importer");
	       new Importer().importer(fileName, startingId);
	    }
	}

	private JSONObject getPersonFromArnetminer(String name_, Logger logger_) {
		String name;
		JSONArray tmp = null;

		name = name_.replace(" ", "%20").toLowerCase();
		if (!name.matches("((ö|é|ó|ø|ü|è|ä|å|â|ú|ò|ð|ì).*|(.*(ß|í|à|ç|á|]|\").*))")) {
			logger_.debug("GetPersonFromArnetminer: name is ok, contatting arnetminer..");
			try {
				URL url = null;
				HttpURLConnection conn = null;
				String output = "";
				String arnetPerson = "";
				BufferedReader br = null;
				JSONParser parser = new JSONParser();
				int responseCode = 0;

				url = new URL("http://arnetminer.org/services/person/" + name
					+ "?u=oyster&o=ttf");
				logger_.debug("GetPersonFromArnetminer: url is set up");
				conn = (HttpURLConnection)url.openConnection();
				logger_.debug("GetPersonFromArnetminer: connection opened");

				conn.setInstanceFollowRedirects(true);
				conn.setRequestMethod("GET");
				conn.setRequestProperty("Accept", "application/json");
				conn.setRequestProperty("User-agent", "java");
				conn.setConnectTimeout(30000);
				conn.setReadTimeout(30000);

				conn.connect();
				logger_.debug("GetPersonFromArnetminer: connected..");
				responseCode = conn.getResponseCode();
				logger_.debug("GetPersonFromArnetminer: response code " + responseCode);
				if (responseCode != HttpURLConnection.HTTP_OK) {
					logger_.debug("GetPersonFromArnetminer: failed with HTTP error code : "
							+ responseCode);
					return null;
				}
				logger_.debug("GetPersonFromArnetminer: connection established..");
		 
				br = new BufferedReader(new InputStreamReader(
					(conn.getInputStream())));
				logger_.debug("GetPersonFromArnetminer: getting data from arnetminer..");
				while ((output = br.readLine()) != null) {
					arnetPerson += output;
				}
				conn.disconnect();

				logger_.debug("GetPersonFromArnetminer:going to parse the data..");
				tmp = (JSONArray)parser.parse(arnetPerson);
			} catch (SocketTimeoutException e) {
				System.out.println("GetPersonFromArnetminer: connection timed out. Retrying..");
				logger_.debug("GetPersonFromArnetminer: exception occurred..", e);
				return null;
			} catch (IOException e) {
				System.out.println("GetPersonFromArnetminer: an IOException occurred. Retrying..");
				logger_.debug("GetPersonFromArnetminer: an IOException occurred.", e);
				return null;
			} catch (ParseException e) {
				System.out.println("GetPersonFromArnetminer: a ParserException occurred");
				logger_.debug("GetPersonFromArnetminer: a ParserException occurred..", e);
				return null;
			}

			return (JSONObject)tmp.get(0);
		}
		return new JSONObject();
	}
}
