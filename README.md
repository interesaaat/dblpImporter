dblpImporter
============

Java application for translating in relational the dblp database + author information importer from arnetminer.

#### Requirements: 

* Maven

#### Usage:

* run dblpImoprter.Parser.main passing as input the dblp.xml file;
* create the db using the schema contained in resources (working only for MySQL); and
* import in the db the generated csv files contained in the output directory.

arnetminerImporter
==================

Utility to add authors information to the dblp database by retrieveing data from arnetminer.

#### Usage:

* configure hibernate by modifing the file resources/hibernate.cfg.xml;
* launch arnetminerImporter.Importer.main passing as input the hibernate configuration file hibernate.cfg.xml.
