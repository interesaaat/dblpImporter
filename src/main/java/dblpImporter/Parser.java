 package dblpImporter;
/*
       persons
        |
        +-authorA-----------article
        |
        +-editorP-----------proceedings
        |
        +-authorW-----------www
        |
        +-authorI-----------inProc
        |
        +-authorB-----------book
        |
        +-phdThesis
        |
        +-masterThesis
        |
        +-authorC-----------inColl

  */
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;

import javax.xml.parsers.*;

import org.xml.sax.*;
import org.xml.sax.helpers.*;

import schema.*;

public class Parser {
   private final String ADDRESS = "address";
   private final String ARTICLE = "article";
   private final String AUTHOR = "author";
   private final String BOOK = "book";
   private final String BOOKTITLE = "booktitle";
   private final String CDROM = "cdrom";
   private final String CHAPTER = "chapter";
   private final String CITE = "cite";
   private final String CROSSREF = "crossref";
   private final String EDITOR = "editor";
   private final String EE = "ee";
   private final String JOURNAL = "journal";
   private final String INPROCEEDINGS = "inproceedings";
   private final String INCOLLECTION = "incollection";
   private final String ISBN = "isbn";
   private final String MASTER = "mastersthesis";
   private final String MONTH = "month";
   private final String NOTE = "note";
   private final String NUMBER = "number";
   private final String PAGES = "pages";
   private final String PHD = "phdthesis";
   private final String PROCEEDINGS = "proceedings";
   private final String PUBLISHER = "publisher";
   private final String SCHOOL = "school";
   private final String SERIES = "series";
   private final String TITLE = "title";
   private final String YEAR = "year";
   private final String URL = "url";
   private final String VOLUME = "volume";
   private final String WWW = "www";

   private class DblpHandler extends DefaultHandler {
      private String _currentElement = null;
      private String _surroundingElement = null;
      private String _name = "";
	
      private HashMap<String, Integer> _mapPersons = 
    		 new HashMap<String, Integer>();
      private HashMap<String, Integer> _mapBooks = 
    	     new HashMap<String, Integer>();
      private HashMap<String, Integer> _mapProceedings = 
 	         new HashMap<String, Integer>();
      private HashMap<String, InProceeding> _mapInProceedings = 
    	      new HashMap<String, InProceeding>();
      private Hashtable<String, InCollection> _mapInCollections = 
    		  new Hashtable<String, InCollection>();
      private HashMap<String, HashMap<Integer, Integer>> _mapAuthorI = 
    		  new HashMap<String,  HashMap<Integer, Integer>>();
      private HashMap<String,  HashMap<Integer, Integer>> _mapAuthorC = 
    		  new HashMap<String,  HashMap<Integer, Integer>>();

      private int _counterArticles = 0;
      private int _counterPersons  = 0;
      private int _counterBook     = 0;
      private int _counterInColl   = 0;
      private int _counterInProc   = 0;
      private int _counterWww      = 0;
      private int _counterProceedings = 0;
      private int _counterPhdThesis = 0;
      private int _counterMasterThesis = 0;
      
      private FileOutputStream _fos_persons = null;
      private FileOutputStream _fos_authorA = null;
      private FileOutputStream _fos_article = null;
      private FileOutputStream _fos_authorB = null;
      private FileOutputStream _fos_book = null;
      private FileOutputStream _fos_authorC = null;
      private FileOutputStream _fos_inColl = null;
      private FileOutputStream _fos_authorI = null;
      private FileOutputStream _fos_inProc = null;
      private FileOutputStream _fos_authorW = null;
      private FileOutputStream _fos_www = null;
      private FileOutputStream _fos_editorP = null;
      private FileOutputStream _fos_proceedings = null;
      private FileOutputStream _fos_phdThesis = null;
      private FileOutputStream _fos_masterThesis = null;
      //-
      private Proceeding _proceeding = null;
      private Article _article = null;
      private Www _www = null;
      private InProceeding _inProc= null;
      private Book _book = null;
      private PhdThesis _phdThesis = null;
      private MasterThesis _masterThesis = null;
      private InCollection _inColl = null;
      
      public void openAllFis() throws SAXException {
         try {
        	 new File("output").mkdirs();
            _fos_persons = new FileOutputStream("output/persons.csv");
            _fos_authorA = new FileOutputStream("output/authorA.csv");
            _fos_article = new FileOutputStream("output/article.csv");
            _fos_book = new FileOutputStream("output/book.csv");
            _fos_authorB = new FileOutputStream("output/authorB.csv");
            _fos_authorC = new FileOutputStream("output/authorC.csv");
            _fos_inColl = new FileOutputStream("output/inColl.csv");
            _fos_authorI = new FileOutputStream("output/authorI.csv");
            _fos_inProc = new FileOutputStream("output/inProc.csv");
            _fos_authorW = new FileOutputStream("output/authorW.csv");
            _fos_www = new FileOutputStream("output/www.csv");
            _fos_editorP = new FileOutputStream("output/editorP.csv");
            _fos_proceedings = new FileOutputStream("output/proceedings.csv");
            _fos_phdThesis = new FileOutputStream("output/phdThesis.csv");
            _fos_masterThesis = new FileOutputStream("output/masterThesis.csv");
         } catch (Exception e ) {
            throw(new SAXException("error opening file",e));
         }
      }

      public void startElement(String namespaceURI
                               , String localName
                               , String rawName
                               , Attributes atts
                               ) throws SAXException {

         if (rawName == PROCEEDINGS) {
            _counterProceedings++;
            _currentElement = PROCEEDINGS;
            _surroundingElement = PROCEEDINGS;
            _proceeding = new Proceeding(_counterProceedings, atts.getValue("key"));
            _mapProceedings.put(atts.getValue("key"), 0);
            return;
         } else if (rawName == ARTICLE) {
            _counterArticles++;
            _currentElement = ARTICLE;
            _surroundingElement = ARTICLE;
            _article = new Article(_counterArticles, atts.getValue("key"));
            return;
         } else if (rawName == WWW) {
            _counterWww++;
            _currentElement = WWW;
            _surroundingElement = WWW;
            _www = new Www(_counterWww, atts.getValue("key"));
            return;
         } else if (rawName == INPROCEEDINGS) {
            _counterInProc++;
            _currentElement = INPROCEEDINGS;
            _surroundingElement = INPROCEEDINGS;
            _inProc = new InProceeding(_counterInProc, atts.getValue("key"));
         } else if (rawName == BOOK) {
            _counterBook++;
            _currentElement = BOOK;
            _surroundingElement = BOOK;
            _book = new Book(_counterBook, atts.getValue("key"));
            _mapBooks.put(atts.getValue("key"), 0);
            return;
         } else if (rawName == PHD) {
        	 _counterPhdThesis++;
            _currentElement = PHD;
            _surroundingElement = PHD;
            _phdThesis = new PhdThesis(_counterPhdThesis, atts.getValue("key"));
            return;
         } else if (rawName == MASTER) {
        	 _counterMasterThesis++;
            _currentElement = MASTER;
            _surroundingElement = MASTER;
            _masterThesis = new MasterThesis(_counterMasterThesis, atts.getValue("key"));
            return;
         } else if (rawName == INCOLLECTION) {
            _counterInColl++;
            _currentElement = INCOLLECTION;
            _surroundingElement = INCOLLECTION;
            _inColl = new InCollection(_counterInColl, atts.getValue("key"));
            return;
         } else if (rawName == AUTHOR) {
            _currentElement = AUTHOR;
            return;
         } else if (rawName == EDITOR) {
            _currentElement = EDITOR;
            return;
         } else if (rawName == TITLE) {
            _currentElement = TITLE;
            return;
         } else if (rawName == JOURNAL) {
            _currentElement = JOURNAL;
            return;
         } else if (rawName == VOLUME) {
            _currentElement = VOLUME;
            return;
         } else if (rawName == NUMBER) {
            _currentElement = NUMBER;
            return;
         } else if (rawName == YEAR) {
            _currentElement = YEAR;
            return;
         } else if (rawName == EE) {
            _currentElement = EE;
            return;
         } else if (rawName == PAGES) {
            _currentElement = PAGES;
            return;
         } else if (rawName == MONTH) {
            _currentElement = MONTH;
            return;
         } else if (rawName == CDROM) {
            _currentElement = CDROM;
            return;
         } else if (rawName == URL) {
            _currentElement = URL;
            return;
         } else if (rawName == BOOKTITLE) {
            _currentElement = BOOKTITLE;
            return;
         } else if (rawName == PUBLISHER) {
            _currentElement = PUBLISHER;
            return;
         } else if (rawName == ISBN) {
            _currentElement = ISBN;
            return;
         } else if (rawName == NOTE) {
            _currentElement = NOTE;
            return;
         } else if (rawName == SERIES) {
            _currentElement = SERIES;
            return;
         } else if (rawName == SCHOOL) {
            _currentElement = SCHOOL;
            return;
         } else if (rawName == CROSSREF) {
            _currentElement = CROSSREF;
            return;
         } else if (rawName == CHAPTER) {
            _currentElement = CHAPTER;
            return;
         } else if (rawName == ADDRESS) {
            _currentElement = ADDRESS;
            return;
         } else if (rawName == CITE) {
            _currentElement = CITE;
            return;
         }
      }

      public void endElement(String namespaceURI, String localName,
                             String rawName) throws SAXException {

         if (rawName == ARTICLE) {
            writeArticle(_article);
            _surroundingElement = null;
            return;
         } else if (rawName == PROCEEDINGS) {
            writeProceedings(_proceeding);
            _surroundingElement = null;
            return;
         } else if (rawName == WWW) {
            writeWwws(_www);
            _surroundingElement = null;
            return;
         } else if (rawName == INPROCEEDINGS) {
        	 _mapInProceedings.put(_inProc.getInProcId(), _inProc);
            _surroundingElement = null;
            return;
         } else if (rawName == BOOK) {
            writeBooks(_book);
            _surroundingElement = null;
            return;
         } else if (rawName == PHD) {
            writePhdThesis(_phdThesis);
            _surroundingElement = null;
            return;
         } else if (rawName == MASTER) {
            writeMasterThesis(_masterThesis);
            _surroundingElement = null;
            return;
         } else if (rawName == INCOLLECTION) {
        	 _mapInCollections.put(_inColl.getInCollId(), _inColl);
            _surroundingElement = null;
            return;
         } else if (rawName == AUTHOR) {
            int personId = getPersonId(_name);
            try {
               if (_surroundingElement == ARTICLE) {
                  _fos_authorA
                     .write((""+personId+"|"+_counterArticles+"\n").getBytes());
               } else if (_surroundingElement == INPROCEEDINGS) {
            	   HashMap<Integer, Integer> authorI = _mapAuthorI.get(_inProc.getInProcId());
            	   if(authorI == null) {
            	      authorI = new HashMap<Integer, Integer>();
            	   }
            	   authorI.put(personId, _counterInProc);
            	   _mapAuthorI.put(_inProc.getInProcId(), authorI);
               } else if (_surroundingElement == BOOK) {
                  _fos_authorB
                     .write((""+personId+"|"+_counterBook+"\n").getBytes());
               } else if (_surroundingElement == WWW) {
                  _fos_authorW
                     .write((""+personId+"|"+_counterWww+"\n").getBytes());
               } else if (_surroundingElement == PHD) {
                  _phdThesis.setAuthorId(personId);
               } else if (_surroundingElement == MASTER) {
                  _masterThesis.setAuthorId(personId);
               } else if (_surroundingElement == INCOLLECTION) {
            	   HashMap<Integer, Integer> authorC = _mapAuthorC.get(_inColl.getInCollId());
            	   if(authorC == null) {
            		   authorC = new HashMap<Integer, Integer>();
            	   }
            	   authorC.put(personId, _counterInColl);
            	   _mapAuthorC.put(_inColl.getInCollId(), authorC);
               }
            } catch (Exception e) {
               throw(new SAXException("error writing file", e));
            }
            _name = "";
            return;
         } else if (_currentElement == EDITOR) {
            int personId = getPersonId(_name);
            try {
               if (_surroundingElement == PROCEEDINGS) {
                  _fos_editorP
                     .write((""+personId+"|"+_counterProceedings+"\n").getBytes());
               }
            } catch (Exception e ) {
               throw(new SAXException("error writing file",e));
            }
            _name = "";
            return;
         }
      }

      public void characters(char[] ch, int start, int length)
         throws SAXException {

         try {
            if (_currentElement == AUTHOR) {
               _name += new String(ch, start, length);
               _name = _name.replace("'", " ");
               return;
            } else if (_currentElement == TITLE) {
            	String title = new String(ch, start, length);
               if (_surroundingElement == ARTICLE) {
                  _article.setTitle(title.replace("|", " "));
                  return;
               } else if (_surroundingElement == PROCEEDINGS) {
                  _proceeding.setTitle(title.replace("|", " "));
                  return;
               } else if (_surroundingElement == WWW) {
                  _www.setTitle(title.replace("|", " "));
                  return;
               } else if (_surroundingElement == INPROCEEDINGS) {
                  _inProc.setTitle(title.replace("|", " "));
                  return;
               } else if (_surroundingElement == BOOK) {
                  _book.setTitle(title.replace("|", " "));
                  return;
               } else if (_surroundingElement == PHD) {
                  _phdThesis.setTitle(title.replace("|", " "));
                  return;
               } else if (_surroundingElement == MASTER) {
                  _masterThesis.setTitle(title.replace("|", " "));
                  return;
               } else if (_surroundingElement == INCOLLECTION) {
                  _inColl.setTitle(title.replace("|", " "));
                  return;
               }
            } else if (_currentElement == JOURNAL) {
               if (_surroundingElement == ARTICLE) {
                  _article.setJournal(new String(ch, start, length));
                  return;
               } else if (_surroundingElement == PROCEEDINGS) {
                  _proceeding.setJournal(new String(ch, start, length));
                  return;
               }
            } else if (_currentElement == VOLUME) {
               if (_surroundingElement == ARTICLE) {
                  _article.setVolume(new String(ch, start, length));
                  return;
               } else if (_surroundingElement == PROCEEDINGS) {
                  _proceeding.setVolume(new String(ch, start, length));
                  return;
               } else if (_surroundingElement == PHD) {
                  _phdThesis.setVolume(new String(ch, start, length));
                  return;
               } else if (_surroundingElement == BOOK) {
                  _book.setVolume(new String(ch, start, length));
                  return;
               }
            } else if (_currentElement == NUMBER) {
               if (_surroundingElement == ARTICLE) {
                  _article.setNumber(new String(ch, start, length));
                  return;
               } else if (_surroundingElement == PHD) {
                  _phdThesis.setNumber(new String(ch, start, length));
                  return;
               } else if (_surroundingElement == INPROCEEDINGS) {
                  _inProc.setNumber(new String(ch, start, length));
                  return;
               } else if (_surroundingElement == PROCEEDINGS) {
                  _proceeding.setNumber(new String(ch, start, length));
                  return;
               }
            } else if (_currentElement == YEAR) {
               if (_surroundingElement == ARTICLE) {
                  _article.setYear(Integer.valueOf(Integer.parseInt(new String(ch, start, length))));
                  return;
               } else if (_surroundingElement == WWW) {
                  _www.setYear(Integer.valueOf(Integer.parseInt(new String(ch, start, length))));
                  return;
               } else if (_surroundingElement == INPROCEEDINGS) {
                  _inProc.setYear(Integer.valueOf(Integer.parseInt(new String(ch, start, length))));
                  return;
               } else if (_surroundingElement == BOOK) {
                  _book.setYear(Integer.valueOf(Integer.parseInt(new String(ch, start, length))));
                  return;
               } else if (_surroundingElement == PROCEEDINGS) {
                  _proceeding.setYear(Integer.valueOf(Integer.parseInt(new String(ch, start, length))));
                  return;
               } else if (_surroundingElement == PHD) {
                  _phdThesis.setYear(Integer.valueOf(Integer.parseInt(new String(ch, start, length))));
                  return;
               } else if (_surroundingElement == MASTER) {
                  _masterThesis.setYear(Integer.valueOf(Integer.parseInt(new String(ch, start, length))));
                  return;
               } else if (_surroundingElement == INCOLLECTION) {
                  _inColl.setYear(Integer.valueOf(Integer.parseInt(new String(ch, start, length))));
                  return;
               }
            } else if (_currentElement == EE) {
            	String ee = new String(ch, start, length);

               if (_surroundingElement == ARTICLE) {
                  _article.setEe(ee.replace("|", " "));
                  return;
               } else if (_surroundingElement == INPROCEEDINGS) {
                  _inProc.setEe(ee.replace("|", " "));
                  return;
               } else if (_surroundingElement == PHD) {
                  _phdThesis.setEe(ee.replace("|", " "));
                  return;
               } else if (_surroundingElement == INCOLLECTION) {
                  _inColl.setEe(ee.replace("|", " "));
                  return;
               } else if (_surroundingElement == PROCEEDINGS) {
                  _proceeding.setEe(ee.replace("|", " "));
                  return;
               } else if (_surroundingElement == WWW) {
                  _www.setEe(ee.replace("|", " "));
                  return;
               } else if (_surroundingElement == MASTER) {
                  _masterThesis.setEe(ee.replace("|", " "));
                  return;
               } else if (_surroundingElement == BOOK) {
                  _book.setEe(ee.replace("|", " "));
                  return;
               }
            } else if (_currentElement == PAGES) {
               if (_surroundingElement == ARTICLE) {
                  _article.setPages(new String(ch, start, length));
                  return;
               } else if (_surroundingElement == INPROCEEDINGS) {
                  _inProc.setPages(new String(ch, start, length));
                  return;
               } else if (_surroundingElement == PHD) {
                  _phdThesis.setPages(new String(ch, start, length));
                  return;
               } else if (_surroundingElement == INCOLLECTION) {
                  _inColl.setPages(new String(ch, start, length));
                  return;
               } else if (_surroundingElement == BOOK) {
                  _book.setPages(new String(ch, start, length));
                  return;
               } else if (_surroundingElement == PROCEEDINGS) {
                  _proceeding.setPages(new String(ch, start, length));
                  return;
               }
            } else if (_currentElement == MONTH) {
               if (_surroundingElement == ARTICLE) {
                  _article.setMonth(new String(ch, start, length));
                  return;
               } else if (_surroundingElement == INPROCEEDINGS) {
                  _inProc.setMonth(new String(ch, start, length));
                  return;
               } else if (_surroundingElement == PHD) {
                  _phdThesis.setMonth(new String(ch, start, length));
                  return;
               } else if (_surroundingElement == BOOK) {
                  _book.setMonth(new String(ch, start, length));
                  return;
               }
            } else if (_currentElement == CDROM) {
               if (_surroundingElement == ARTICLE) {
                  _article.setCdrom(new String(ch, start, length));
                  return;
               } else if (_surroundingElement == INPROCEEDINGS) {
                  _inProc.setCdrom(new String(ch, start, length));
                  return;
               } else if (_surroundingElement == BOOK) {
                  _book.setCdrom(new String(ch, start, length));
                  return;
               } else if (_surroundingElement == INCOLLECTION) {
                  _inColl.setCdrom(new String(ch, start, length));
                  return;
               }
            } else if (_currentElement == URL) {
               if (_surroundingElement == WWW) {
                  _www.setUrl(new String(ch, start, length));
                  return;
               } else if (_surroundingElement == BOOK) {
                  _book.setUrl(new String(ch, start, length));
                  return;
               } else if (_surroundingElement == ARTICLE) {
                  _article.setUrl(new String(ch, start, length));
                  return;
               } else if (_surroundingElement == INPROCEEDINGS) {
                  _inProc.setUrl(new String(ch, start, length));
                  return;
               } else if (_surroundingElement == PROCEEDINGS) {
                  _proceeding.setUrl(new String(ch, start, length));
                  return;
               } else if (_surroundingElement == PHD) {
                  _phdThesis.setUrl(new String(ch, start, length));
                  return;
               } else if (_surroundingElement == INCOLLECTION) {
                  _inColl.setUrl(new String(ch, start, length));
                  return;
               } else if (_surroundingElement == MASTER) {
                  _masterThesis.setUrl(new String(ch, start, length));
                  return;
               }
            } else if (_currentElement == BOOKTITLE) {
               if (_surroundingElement == INPROCEEDINGS) {
                  _inProc.setBooktitle(new String(ch, start, length));
                  return;
               } else if (_surroundingElement == PROCEEDINGS) {
                  _proceeding.setBooktitle(new String(ch, start, length));
                  return;
               } else if (_surroundingElement == WWW) {
                  _www.setBooktitle(new String(ch, start, length));
                  return;
               } else if (_surroundingElement == INCOLLECTION) {
                  _inColl.setBooktitle(new String(ch, start, length));
                  return;
               } else if (_surroundingElement == ARTICLE) {
                  _article.setBooktitle(new String(ch, start, length));
                  return;
               } else if (_surroundingElement == BOOK) {
                  _book.setBooktitle(new String(ch, start, length));
                  return;
               }
            } else if (_currentElement == PUBLISHER) {
               if (_surroundingElement == BOOK) {
                  _book.setPublisher(new String(ch, start, length));
                  return;
               } else if (_surroundingElement == ARTICLE) {
                  _article.setPublisher(new String(ch, start, length));
                  return;
               } else if (_surroundingElement == PHD) {
                  _phdThesis.setPublisher(new String(ch, start, length));
                  return;
               } else if (_surroundingElement == PROCEEDINGS) {
                  _proceeding.setPublisher(new String(ch, start, length));
                  return;
               } else if (_surroundingElement == INCOLLECTION) {
                  _inColl.setPublisher(new String(ch, start, length));
                  return;
               }
            } else if (_currentElement == ISBN) {
               if (_surroundingElement == BOOK) {
                  _book.setIsbn(new String(ch, start, length));
                  return;
               } else if (_surroundingElement == PHD) {
                  _phdThesis.setIsbn(new String(ch, start, length));
                  return;
               } else if (_surroundingElement == PROCEEDINGS) {
                  _proceeding.setIsbn(new String(ch, start, length));
                  return;
               } else if (_surroundingElement == INCOLLECTION) {
                  _inColl.setIsbn(new String(ch, start, length));
                  return;
               }
            } else if (_currentElement == NOTE) {
               if (_surroundingElement == INPROCEEDINGS) {
                  String notes = _inProc.getNote();
                  if (notes != null) {
                     notes += "\\n" + new String(ch, start, length);
                  } else {
                     notes = new String(ch, start, length);
                  }
                  _inProc.setNote(notes);
                  return;
               } else if (_surroundingElement == PROCEEDINGS) {
                  String notes = _proceeding.getNote();
                  if (notes != null) {
                     notes += "\\n" + new String(ch, start, length);
                  } else {
                     notes = new String(ch, start, length);
                  }
                  _proceeding.setNote(notes);
                  return;
               } else if (_surroundingElement == PHD) {
                  String notes = _phdThesis.getNote();
                  if (notes != null) {
                     notes += "\\n" + new String(ch, start, length);
                  } else {
                     notes = new String(ch, start, length);
                  }
                  _phdThesis.setNote(notes);
                  return;
               } else if (_surroundingElement == ARTICLE) {
                  String notes = _article.getNote();
                  if (notes != null) {
                     notes += "\\n" + new String(ch, start, length);
                  } else {
                     notes = new String(ch, start, length);
                  }
                  _article.setNote(notes);
                  return;
               } else if (_surroundingElement == WWW) {
                  String notes = _www.getNote();
                  if (notes != null) {
                     notes += "\\n" + new String(ch, start, length);
                  } else {
                     notes = new String(ch, start, length);
                  }
                  _www.setNote(notes);
                  return;
               }
            } else if (_currentElement == EDITOR) {
               _name += new String(ch, start, length);
               return;
            } else if (_currentElement == SERIES) {
               if (_surroundingElement == PROCEEDINGS) {
                  String series = _proceeding.getSeries();
                  if (series != null) {
                     series += new String(ch, start, length);
                  } else {
                     series = new String(ch, start, length);
                  }						
                  _proceeding.setSeries(series);
                  return;
               }
            } else if (_currentElement == SCHOOL) {
               if (_surroundingElement == PHD) {
                  _phdThesis.setSchool(new String(ch, start, length));
                  return;
               }
            } else if (_currentElement == CROSSREF) {
               if (_surroundingElement == INPROCEEDINGS) {
                  _inProc.setCrossref(new String(ch, start, length));
                  return;
               } else if (_surroundingElement == INCOLLECTION) {
                  _inColl.setCrossref(new String(ch, start, length));
                  return;
               } else if (_surroundingElement == ARTICLE) {
                  _article.setCrossref(new String(ch, start, length));
                  return;
               } else if (_surroundingElement == PROCEEDINGS) {
                  _proceeding.setCrossref(new String(ch, start, length));
                  return;
               } else if (_surroundingElement == WWW) {
                  _www.setCrossref(new String(ch, start, length));
                  return;
               }
            } else if (_currentElement == CHAPTER) {
               if (_surroundingElement == INCOLLECTION) {
                  _inColl.setChapter(Integer.valueOf(Integer.parseInt(new String(ch, start, length))));
                  return;
               }
            } else if (_currentElement == ADDRESS) {
               if (_surroundingElement == PROCEEDINGS) {
                  _proceeding.setAddress(new String(ch, start, length));
                  return;
               }
            } else if (_currentElement == CITE) {
               if (_surroundingElement == ARTICLE) {
                  _article.setCite(new String(ch, start, length));
                  return;
               } else if (_surroundingElement == INCOLLECTION) {
                  _inColl.setCite(new String(ch, start, length));
                  return;
               } else if (_surroundingElement == WWW) {
                  _www.setCite(new String(ch, start, length));
                  return;
               } else if (_surroundingElement == BOOK) {
                  _book.setCite(new String(ch, start, length));
                  return;
               } else if (_surroundingElement == INPROCEEDINGS) {
                  _inProc.setCite(new String(ch, start, length));
                  return;
               } else if (_surroundingElement == PROCEEDINGS) {
                  _proceeding.setCite(new String(ch, start, length));
                  return;
               }
            }
         } catch (Exception e) {
            throw new SAXException("Parser.characters: " + e.getMessage());
         }
      }

      public void endDocument() throws SAXException {
    	 Iterator<String> keys = _mapInCollections.keySet().iterator();
    	 String key = "";
    	 ArrayList<String> tmp = new ArrayList<String>();
    	 InCollection in_coll = null;
    	 InProceeding in_proc = null;

    	 System.out.println("Saving the files...");
    	 while(keys.hasNext()) {
    		 key = keys.next();
    		 in_coll = _mapInCollections.get(key);

    		 if (null != _mapBooks.get(in_coll.getCrossref())) {
    			 writeInCollections(in_coll);
    		 } else {
    			 tmp.add(key);
    		 }
    	 }

    	 keys = tmp.iterator();
    	 while(keys.hasNext()) {
    		 _mapInCollections.remove(keys.next());
    	 }
    	 tmp = new ArrayList<String>();
    	 
    	 keys = _mapInProceedings.keySet().iterator();
    	 while(keys.hasNext()) {
    		 key = keys.next();
    		 in_proc = _mapInProceedings.get(key);

    		 if (null != _mapProceedings.get(in_proc.getCrossref())) {
    			 writeInProceedings(in_proc);
    		 } else {
    			 tmp.add(key);
    		 }
    	 }

    	 keys = tmp.iterator();
    	 while(keys.hasNext()) {
    		 _mapInProceedings.remove(keys.next());
    	 }

    	 keys = _mapAuthorC.keySet().iterator();
    	 while(keys.hasNext()) {
    		 key = keys.next();

    		 if (null != _mapInCollections.get(key)) {
    			 HashMap<Integer, Integer> authorsC = _mapAuthorC.get(key);
    			 Iterator<Integer> authorsId = authorsC.keySet().iterator();

    			 while(authorsId.hasNext()) {
    				 int personId = authorsId.next();

    				 try {
						_fos_authorC
						 .write((personId+"|"+authorsC.get(personId)+"\n").getBytes());
					} catch (IOException e) {
						throw(new SAXException("error writing file",e));
					}
    			 }
    		 }
    	 }

    	 keys = _mapAuthorI.keySet().iterator();
    	 while(keys.hasNext()) {
    		 key = keys.next();

    		 if (null != _mapInProceedings.get(key)) {
    			 HashMap<Integer, Integer> authorsI = _mapAuthorI.get(key);
    			 Iterator<Integer> authorsId = authorsI.keySet().iterator();

    			 while(authorsId.hasNext()) {
    				 int personId = authorsId.next();

    				 try {
						_fos_authorI
						 .write((personId+"|"+authorsI.get(personId)+"\n").getBytes());
					} catch (IOException e) {
						throw(new SAXException("error writing file",e));
					}
    			 }
    		 } 
    	 }
         closeAllFos();
      }
      private void Message(String mode, SAXParseException exception) {
         System.out.println(mode + " Line: " + exception.getLineNumber()
                            + " URI: " + exception.getSystemId() + "\n" + " Message: "
                            + exception.getMessage());
      }
      public void warning(SAXParseException exception) throws SAXException {
         Message("**Parsing Warning**\n", exception);
         throw new SAXException("Warning encountered");
      }
      public void error(SAXParseException exception) throws SAXException {
         Message("**Parsing Error**\n", exception);
         throw new SAXException("Error encountered");
      }
      public void fatalError(SAXParseException exception) throws SAXException {
         Message("**Parsing Fatal Error**\n", exception);
         throw new SAXException("Fatal Error encountered");
      }

      private int getPersonId(String name) throws SAXException {
         Integer idO = _mapPersons.get(name);
         if (idO == null) {
            _counterPersons++;
            idO = new Integer(_counterPersons);
            _mapPersons.put(name, idO);
            // System.out.println(" persona: "+ name + " id: " + idO);
            try {
               _fos_persons.write((""+idO+"|"+name+"\n").getBytes());
            } catch (Exception e ) {
               throw(new SAXException("error writing file",e));
            }
         }
         return(idO.intValue());
      }

      public void closeAllFos() throws SAXException {
         try {
            _fos_persons.close();
            _fos_authorA.close();
            _fos_article.close();
            _fos_authorB.close();
            _fos_book.close();
            _fos_authorC.close();
            _fos_inColl.close();
            _fos_authorI.close();
            _fos_inProc.close();
            _fos_authorW.close();
            _fos_www.close();
            _fos_editorP.close();
            _fos_proceedings.close();
            _fos_phdThesis.close();
            _fos_masterThesis.close();
         } catch (Exception e ) {
            throw(new SAXException("error closing file",e));
         }
      }

      private String writeArticle(Article article_) throws SAXException {
         String result = ""
            + article_.getId()
            + "|" + article_.getArticleId()
            + "|" + writeValue(article_.getTitle())
            + "|" + writeValue(article_.getVolume())
            + "|" + writeValue(article_.getNumber())
            + "|" + writeValue(article_.getEe())
            + "|" + writeValue(article_.getYear())
            + "|" + writeValue(article_.getJournal())
            + "|" + writeValue(article_.getPages())
            + "|" + writeValue(article_.getMonth())
            + "|" + writeValue(article_.getCdrom())
            + "|" + writeValue(article_.getUrl())
            + "|" + writeValue(article_.getPublisher())
            + "|" + writeValue(article_.getCrossref())
            + "|" + writeValue(article_.getBooktitle())
            + "|" + writeValue(article_.getCite())
            + "|" + writeValue(article_.getNote()) 
            + "\n"
            ;
         try {
            _fos_article.write((result).getBytes());
         } catch (Exception e ) {
            throw(new SAXException("error writing file",e));
         }
         return result;
      }

      private String writeProceedings(Proceeding proc_) throws SAXException {
         String result = ""
            + proc_.getId()
            + "|" + proc_.getProcId()
            + "|" + writeValue(proc_.getTitle())
            + "|" + writeValue(proc_.getVolume())
            + "|" + writeValue(proc_.getNumber())
            + "|" + writeValue(proc_.getEe())
            + "|" + writeValue(proc_.getYear())
            + "|" + writeValue(proc_.getJournal())
            + "|" + writeValue(proc_.getPages())
            + "|" + writeValue(proc_.getUrl())
            + "|" + writeValue(proc_.getPublisher())
            + "|" + writeValue(proc_.getCrossref())
            + "|" + writeValue(proc_.getBooktitle())
            + "|" + writeValue(proc_.getCite())
            + "|" + writeValue(proc_.getNote())
            + "|" + writeValue(proc_.getIsbn())
            + "|" + writeValue(proc_.getSeries())
            + "|" + writeValue(proc_.getAddress()) 
            + "\n";
         try {
            _fos_proceedings.write((result).getBytes());
         } catch (Exception e ) {
            throw(new SAXException("error writing file",e));
         }
         return result;
      }

      private String writeWwws(Www www_) throws SAXException {
         String result = ""
            + www_.getId()
            + "|" + www_.getWwwId()
            + "|" + writeValue(www_.getTitle())
            + "|" + writeValue(www_.getEe())
            + "|" + writeValue(www_.getYear())
            + "|" + writeValue(www_.getUrl())
            + "|" + writeValue(www_.getCrossref())
            + "|" + writeValue(www_.getBooktitle())
            + "|" + writeValue(www_.getCite())
            + "|" + writeValue(www_.getNote())
            + "\n";
         try {
            _fos_www.write((result).getBytes());
         } catch (Exception e ) {
            throw(new SAXException("error writing file",e));
         }
         return result;
      }

      private String writeInProceedings(InProceeding inProc_
                                        ) throws SAXException {
         String result = ""
            + inProc_.getId()
            + "|" + inProc_.getInProcId()
            + "|" + writeValue(inProc_.getTitle())
            + "|" + writeValue(inProc_.getNumber())
            + "|" + writeValue(inProc_.getEe())
            + "|" + writeValue(inProc_.getYear())
            + "|" + writeValue(inProc_.getPages())
            + "|" + writeValue(inProc_.getUrl())
            + "|" + writeValue(inProc_.getCrossref())
            + "|" + writeValue(inProc_.getBooktitle())
            + "|" + writeValue(inProc_.getCite())
            + "|" + writeValue(inProc_.getNote())
            + "|" + writeValue(inProc_.getMonth())
            + "|" + writeValue(inProc_.getCdrom())
            + "\n";
         try {
            _fos_inProc.write((result).getBytes());
         } catch (Exception e ) {
            throw(new SAXException("error writing file",e));
         }
         return result;
      }

      private String writeBooks(Book book_) throws SAXException {
         String result = ""
            + book_.getId()
            + "|" + book_.getBookId()
            + "|" + writeValue(book_.getTitle())
            + "|" + writeValue(book_.getVolume())
            + "|" + writeValue(book_.getEe())
            + "|" + writeValue(book_.getYear())
            + "|" + writeValue(book_.getPages())
            + "|" + writeValue(book_.getUrl())
            + "|" + writeValue(book_.getPublisher())
            + "|" + writeValue(book_.getBooktitle())
            + "|" + writeValue(book_.getCite())
            + "|" + writeValue(book_.getIsbn())
            + "|" + writeValue(book_.getSeries())
            + "|" + writeValue(book_.getCdrom())
            + "|" + writeValue(book_.getMonth())
            + "\n";
         try {
            _fos_book.write((result).getBytes());
         } catch (Exception e ) {
            throw(new SAXException("error writing file",e));
         }
         return result;
      }

      private String writePhdThesis(PhdThesis phd_) throws SAXException {
         String result = ""
        	+ phd_.getId()
            + "|" + phd_.getThesisId()
            + "|" + writeValue(phd_.getAuthorId())
            + "|" + writeValue(phd_.getTitle())
            + "|" + writeValue(phd_.getVolume())
            + "|" + writeValue(phd_.getNumber())
            + "|" + writeValue(phd_.getEe())
            + "|" + writeValue(phd_.getYear())
            + "|" + writeValue(phd_.getPages())
            + "|" + writeValue(phd_.getUrl())
            + "|" + writeValue(phd_.getPublisher())
            + "|" + writeValue(phd_.getSchool())
            + "|" + writeValue(phd_.getMonth())
            + "|" + writeValue(phd_.getNote())
            + "|" + writeValue(phd_.getIsbn())
            + "\n";
         try {
            _fos_phdThesis.write((result).getBytes());
         } catch (Exception e ) {
            throw(new SAXException("error writing file",e));
         }
         return result;
      }

      private String writeMasterThesis(MasterThesis master_) 
    	 throws SAXException {
         String result = ""
            + master_.getId()
            + "|" + master_.getThesisId()
            + "|" + writeValue(master_.getAuthorId())
            + "|" + writeValue(master_.getTitle())
            + "|" + writeValue(master_.getEe())
            + "|" + writeValue(master_.getYear())
            + "|" + writeValue(master_.getUrl())
            + "\n";
         try {
            _fos_masterThesis.write((result).getBytes());
         } catch (Exception e ) {
            throw(new SAXException("error writing file",e));
         }
         return result;
      }

      private String writeInCollections(InCollection inColl_
                                        ) throws SAXException {
         String result = ""
            + inColl_.getId()
            + "|" + inColl_.getInCollId()
            + "|" + writeValue(inColl_.getTitle())
            + "|" + writeValue(inColl_.getEe())
            + "|" + writeValue(inColl_.getYear())
            + "|" + writeValue(inColl_.getPages())
            + "|" + writeValue(inColl_.getUrl())
            + "|" + writeValue(inColl_.getPublisher())
            + "|" + writeValue(inColl_.getCrossref())
            + "|" + writeValue(inColl_.getBooktitle())
            + "|" + writeValue(inColl_.getCite())
            + "|" + writeValue(inColl_.getChapter())
            + "|" + writeValue(inColl_.getCdrom())
            + "|" + writeValue(inColl_.getIsbn())
            + "\n";
         try {
            _fos_inColl.write((result).getBytes());
         } catch (Exception e ) {
            throw(new SAXException("error writing file",e));
         }
         return result;
      }


   }

   private Parser(String fileName) throws Exception {
      SAXParserFactory parserFactory = SAXParserFactory.newInstance();
      SAXParser parser = parserFactory.newSAXParser();
      DblpHandler handler = new DblpHandler();
      //-
      handler.openAllFis();
      //-
      parser.getXMLReader()
         .setFeature("http://xml.org/sax/features/validation", false);
      parser.parse(new File(fileName), handler);
   }

   public static void main(String[] args) throws Exception {
      if (args.length < 1) {
         System.out.println("Usage Parsing: java Parser [input]");
      } else { 
         String fileName = args[0];
         System.out.println("Starting the parsing...");
         new Parser(fileName);
      }
   }

   private String writeValue(String value_) {
	   return (value_ != null ? value_ : "\\N");
   }

   private String writeValue(Integer value_) {
	   return (value_ != null ? value_.toString() : "\\N");
   }
}

/*
load data local 

infile '/Users/Inter/Desktop/persons.csv' 

  into table dblp.person

character set utf8

fields terminated by '|'

lines terminated by '\n'

(     id ,

name,
phone,
email,
homepage,
affiliation,
address,
bio,
position,
phd_univ,
phd_major,
phd_date,
ms_univ,
ms_major,
ms_date,
bs_univ,
bs_major,
bs_date
);



load data local 

infile '/Users/Inter/Desktop/article.csv' 

  into table dblp.article

character set utf8

fields terminated by '|'

lines terminated by '\n'

(   id  ,

article_id  ,

title ,

volume ,

number  ,

ee  ,

year ,

journal  ,

pages  ,

month ,

cdrom  ,

url  ,

publisher  ,

crossref  ,

booktitle ,

cite,

note

);



load data local 

infile '/Users/Inter/Desktop/authorI.csv' 

  into table dblp.author_i

character set utf8

fields terminated by '|'

lines terminated by '\n'

(     person_id  ,

in_proc_id 



);









load data local 

infile '/Users/Inter/Desktop/authorA.csv' 

  into table dblp.author_a

character set utf8

fields terminated by '|'

lines terminated by '\n'

(     person_id  ,

article_id 



);



load data local 

infile '/Users/Inter/Desktop/inProc.csv' 

  into table dblp.in_proceeding 

character set utf8

fields terminated by '|'

lines terminated by '\n'

(     id  ,

in_proc_id ,

title  ,

number  ,

ee  ,

year  ,

pages  ,

url  ,

crossref  ,

booktitle  ,

cite  ,

note  ,

month  ,

cdrom 

);



load data local 

infile '/Users/Inter/Desktop/proceedings.csv' 

  into table dblp.proceeding 

character set utf8

fields terminated by '|'

lines terminated by '\n' (

id,

proc_id,

title,

volume,

number,

ee,

year,

journal,

pages,

url,

publisher,

crossref,

booktitle,

cite,

note,

isbn,

series,

address

);





load data local 

infile '/Users/Inter/Desktop/www.csv' 

  into table dblp.www

character set utf8

fields terminated by '|'

lines terminated by '\n' (

id,

www_id,

title,

ee,

year,

url,

crossref,

booktitle,

cite,

note

);



--

load data local 

infile '/Users/Inter/Desktop/book.csv' 

  into table dblp.book 

character set utf8

fields terminated by '|'

lines terminated by '\n'

(

id,

book_id,

title,

volume,

ee,

year,

pages,

url,

publisher,

booktitle,

cite,

ISBN,

series,

cdrom,

month

);

--



load data local 

infile '/Users/Inter/Desktop/editorP.csv' 

  into table dblp.editor_p 

character set utf8

fields terminated by '|'

lines terminated by '\n'

(

person_id,

proc_id

);







load data local 

infile '/Users/Inter/Desktop/authorB.csv' 

  into table dblp.author_b 

character set utf8

fields terminated by '|'

lines terminated by '\n'

(

person_id,

book_id

);



load data local 

infile '/Users/Inter/Desktop/authorW.csv' 

  into table dblp.author_w  

character set utf8

fields terminated by '|'

lines terminated by '\n'

(

person_id,

www_id

);



--

load data local 

infile '/Users/Inter/Desktop/phdThesis.csv' 

  into table dblp.phd_thesis 

character set utf8

fields terminated by '|'

lines terminated by '\n'



(

id,

thesis_id,

author_id,

title,

volume,

number,

ee,

year,

pages,

url,

publisher,

school,

month,

note,

isbn

);

--

load data local 

infile '/Users/Inter/Desktop/masterThesis.csv' 

  into table dblp.master_thesis  

character set utf8

fields terminated by '|'

lines terminated by '\n'



(

id,

thesis_id,

author_id,

title,

ee,

year,

url

);



--

load data local 

infile '/Users/Inter/Desktop/inColl.csv' 

  into table dblp.in_collection  

character set utf8

fields terminated by '|'

lines terminated by '\n'

(

id,

in_coll_id,

title,

ee,

year,

pages,

url,

publisher,

crossref,

booktitle,

cite,

chapter,

cdrom,

isbn

);



--

load data local 

infile '/Users/Inter/Desktop/authorC.csv' 

  into table dblp.author_c  

character set utf8

fields terminated by '|'

lines terminated by '\n'

(

person_id,

in_coll_id

);
*/