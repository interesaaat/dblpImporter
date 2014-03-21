CREATE  TABLE IF NOT EXISTS article ( id INT(11) NULL DEFAULT NULL , article_id VARCHAR(80) NULL DEFAULT NULL , title TEXT NULL DEFAULT NULL , volume TEXT NULL DEFAULT NULL , number TEXT NULL DEFAULT NULL ,  ee TEXT NULL DEFAULT NULL ,  year VARCHAR(11) NULL DEFAULT NULL , journal TEXT NULL DEFAULT NULL ,  pages TEXT NULL DEFAULT NULL ,  month TEXT NULL DEFAULT NULL ,  cdrom TEXT NULL DEFAULT NULL ,  url TEXT NULL DEFAULT NULL ,  publisher TEXT NULL DEFAULT NULL ,  crossref TEXT NULL DEFAULT NULL ,  booktitle TEXT NULL DEFAULT NULL ,  cite TEXT NULL DEFAULT NULL ,  note TEXT NULL DEFAULT NULL , PRIMARY KEY (`id`)) ENGINE = MyISAM DEFAULT CHARACTER SET = utf8;

CREATE INDEX id ON article (id ASC) ;

CREATE INDEX article_id ON article (article_id ASC) ;

CREATE FULLTEXT INDEX ft_index_article_article_id ON article (article_id ASC) ;

CREATE FULLTEXT INDEX ft_index_article_title ON article (title ASC) ;

CREATE FULLTEXT INDEX ft_index_article_volume ON article (volume ASC) ;

CREATE FULLTEXT INDEX ft_index_article_number ON article (number ASC) ;

CREATE FULLTEXT INDEX ft_index_article_ee ON article (ee ASC) ;

CREATE FULLTEXT INDEX ft_index_article_journal ON article (journal ASC) ;

CREATE FULLTEXT INDEX ft_index_article_pages ON article (pages ASC) ;

CREATE FULLTEXT INDEX ft_index_article_month ON article (month ASC) ;

CREATE FULLTEXT INDEX ft_index_article_cdrom ON article (cdrom ASC) ;

CREATE FULLTEXT INDEX ft_index_article_url ON article (url ASC) ;

CREATE FULLTEXT INDEX ft_index_article_publisher ON article (publisher ASC) ;

CREATE FULLTEXT INDEX ft_index_article_crossref ON article (crossref ASC) ;

CREATE FULLTEXT INDEX ft_index_article_booktitle ON article (booktitle ASC) ;

CREATE FULLTEXT INDEX ft_index_article_cite ON article (cite ASC) ;

CREATE FULLTEXT INDEX ft_index_article_note ON article (note ASC) ;

CREATE FULLTEXT INDEX ft_article_id ON article (year ASC) ;


CREATE  TABLE IF NOT EXISTS author_a (  person_id INT(11)  NULL DEFAULT NULL ,  article_id INT(11) NULL DEFAULT NULL , PRIMARY KEY (`person_id`,`article_id`)) ENGINE = MyISAM DEFAULT CHARACTER SET = utf8;

CREATE INDEX person ON author_a (person_id ASC) ;

CREATE INDEX article ON author_a (article_id ASC) ;


CREATE  TABLE IF NOT EXISTS author_b (  person_id INT(11)  NULL DEFAULT NULL ,  book_id INT(11)  NULL DEFAULT NULL , PRIMARY KEY (`person_id`,`book_id`)) ENGINE = MyISAM DEFAULT CHARACTER SET = utf8;

CREATE INDEX author ON author_b (person_id ASC) ;

CREATE INDEX book ON author_b (book_id ASC) ;


CREATE  TABLE IF NOT EXISTS author_c (  person_id INT(11) NULL DEFAULT NULL ,  in_coll_id INT(11)  NULL DEFAULT NULL , PRIMARY KEY (`person_id`,`in_coll_id`)) ENGINE = MyISAM DEFAULT CHARACTER SET = utf8;

CREATE INDEX author ON author_c (person_id ASC) ;

CREATE INDEX inc9 ON author_c (in_coll_id ASC) ;


CREATE  TABLE IF NOT EXISTS author_i (  person_id INT(11)  NULL DEFAULT NULL ,  in_proc_id INT(11)  NULL DEFAULT NULL , PRIMARY KEY (`person_id`,`in_proc_id`)) ENGINE = MyISAM DEFAULT CHARACTER SET = utf8;

CREATE INDEX author ON author_i (person_id ASC) ;

CREATE INDEX inproc ON author_i (in_proc_id ASC) ;


CREATE  TABLE IF NOT EXISTS author_w (  person_id INT(11)  NULL DEFAULT NULL ,  www_id INT(11)  NULL DEFAULT NULL , PRIMARY KEY (`person_id`,`www_id`)) ENGINE = MyISAM DEFAULT CHARACTER SET = utf8;

CREATE INDEX author3 ON author_w (person_id ASC) ;

CREATE INDEX www3 ON author_w (www_id ASC) ;


CREATE  TABLE IF NOT EXISTS book (  id INT(11) NULL DEFAULT NULL ,  book_id VARCHAR(50)  NULL DEFAULT NULL ,  title TEXT NULL DEFAULT NULL ,  volume TEXT NULL DEFAULT NULL ,  ee TEXT NULL DEFAULT NULL ,  year VARCHAR(11) NULL DEFAULT NULL ,  pages TEXT NULL DEFAULT NULL ,  url TEXT NULL DEFAULT NULL ,  publisher TEXT NULL DEFAULT NULL ,  booktitle TEXT NULL DEFAULT NULL ,  cite TEXT NULL DEFAULT NULL ,  ISBN TEXT NULL DEFAULT NULL ,  cdrom TEXT NULL DEFAULT NULL ,  month TEXT NULL DEFAULT NULL , PRIMARY KEY (`id`)) ENGINE = MyISAM DEFAULT CHARACTER SET = utf8;

CREATE INDEX id ON book (id ASC) ;

CREATE UNIQUE INDEX book_id_UNIQUE ON book (book_id ASC) ;

CREATE FULLTEXT INDEX ft_index_book_book_id ON book (book_id ASC) ;

CREATE FULLTEXT INDEX ft_index_book_title ON book (title ASC) ;

CREATE FULLTEXT INDEX ft_index_book_volume ON book (volume ASC) ;

CREATE FULLTEXT INDEX ft_index_book_ee ON book (ee ASC) ;

CREATE FULLTEXT INDEX ft_index_book_pages ON book (pages ASC) ;

CREATE FULLTEXT INDEX ft_index_book_url ON book (url ASC) ;

CREATE FULLTEXT INDEX ft_index_book_publisher ON book (publisher ASC) ;

CREATE FULLTEXT INDEX ft_index_book_booktitle ON book (booktitle ASC) ;

CREATE FULLTEXT INDEX ft_index_book_cite ON book (cite ASC) ;

CREATE FULLTEXT INDEX ft_index_book_ISBN ON book (ISBN ASC) ;

CREATE FULLTEXT INDEX ft_index_book_cdrom ON book (cdrom ASC) ;

CREATE FULLTEXT INDEX ft_index_book_month ON book (month ASC) ;

CREATE FULLTEXT INDEX ft_index_book_year ON book (year ASC) ;


CREATE  TABLE IF NOT EXISTS editor_p (  person_id INT(11)  NULL DEFAULT NULL ,  proc_id INT(11) NULL DEFAULT NULL , PRIMARY KEY (`person_id`,`proc_id`)) ENGINE = MyISAM DEFAULT CHARACTER SET = utf8;

CREATE INDEX author4 ON editor_p (person_id ASC) ;

CREATE INDEX proc4 ON editor_p (proc_id ASC) ;


CREATE  TABLE IF NOT EXISTS in_collection (  id INT(11)  NULL DEFAULT NULL ,  in_coll_id TEXT NULL DEFAULT NULL ,  title TEXT NULL DEFAULT NULL ,  ee TEXT NULL DEFAULT NULL ,  year VARCHAR(11) NULL DEFAULT NULL ,  pages TEXT NULL DEFAULT NULL ,  url TEXT NULL DEFAULT NULL ,  publisher TEXT NULL DEFAULT NULL ,  crossref VARCHAR(50) NULL DEFAULT NULL ,  booktitle TEXT NULL DEFAULT NULL ,  cite TEXT NULL DEFAULT NULL ,  chapter VARCHAR(11) NULL DEFAULT NULL ,  cdrom TEXT NULL DEFAULT NULL , PRIMARY KEY (`id`)) ENGINE = MyISAM DEFAULT CHARACTER SET = utf8;

CREATE INDEX id ON in_collection (id ASC) ;

CREATE INDEX in_coll_id ON in_collection (in_coll_id ASC) ;

CREATE INDEX book7 ON in_collection (crossref ASC) ;

CREATE FULLTEXT INDEX ft_index_in_collection_in_coll_id ON in_collection (in_coll_id ASC) ;

CREATE FULLTEXT INDEX ft_index_in_collection_title ON in_collection (title ASC) ;

CREATE FULLTEXT INDEX ft_index_in_collection_ee ON in_collection (ee ASC) ;

CREATE FULLTEXT INDEX ft_index_in_collection_pages ON in_collection (pages ASC) ;

CREATE FULLTEXT INDEX ft_index_in_collection_url ON in_collection (url ASC) ;

CREATE FULLTEXT INDEX ft_index_in_collection_publisher ON in_collection (publisher ASC) ;

CREATE FULLTEXT INDEX ft_index_in_collection_crossref ON in_collection (crossref ASC) ;

CREATE FULLTEXT INDEX ft_index_in_collection_booktitle ON in_collection (booktitle ASC) ;

CREATE FULLTEXT INDEX ft_index_in_collection_cite ON in_collection (cite ASC) ;

CREATE FULLTEXT INDEX ft_index_in_collection_cdrom ON in_collection (cdrom ASC) ;

CREATE FULLTEXT INDEX fk_index_in_coll_year ON in_collection (year ASC) ;

CREATE FULLTEXT INDEX fk_index_in_coll_chapter ON in_collection (chapter ASC) ;


CREATE  TABLE IF NOT EXISTS in_proceeding (  id INT(11) NULL DEFAULT NULL ,  in_proc_id TEXT NULL DEFAULT NULL ,  title TEXT NULL DEFAULT NULL ,  number TEXT NULL DEFAULT NULL ,  ee TEXT NULL DEFAULT NULL ,  year VARCHAR(11) NULL DEFAULT NULL ,  pages TEXT NULL DEFAULT NULL ,  url TEXT NULL DEFAULT NULL ,  crossref VARCHAR(50) NULL DEFAULT NULL ,  booktitle TEXT NULL DEFAULT NULL ,  cite TEXT NULL DEFAULT NULL ,  note TEXT NULL DEFAULT NULL ,  cdrom TEXT NULL DEFAULT NULL , PRIMARY KEY (`id`)) ENGINE = MyISAM DEFAULT CHARACTER SET = utf8;

CREATE INDEX id ON in_proceeding (id ASC) ;

CREATE INDEX in_proc_id ON in_proceeding (in_proc_id ASC) ;

CREATE INDEX proc8 ON in_proceeding (crossref ASC) ;

CREATE FULLTEXT INDEX ft_index_in_proceeding_in_proc_id ON in_proceeding (in_proc_id ASC) ;

CREATE FULLTEXT INDEX ft_index_in_proceeding_title ON in_proceeding (title ASC) ;

CREATE FULLTEXT INDEX ft_index_in_proceeding_number ON in_proceeding (number ASC) ;

CREATE FULLTEXT INDEX ft_index_in_proceeding_ee ON in_proceeding (ee ASC) ;

CREATE FULLTEXT INDEX ft_index_in_proceeding_pages ON in_proceeding (pages ASC) ;

CREATE FULLTEXT INDEX ft_index_in_proceeding_url ON in_proceeding (url ASC) ;

CREATE FULLTEXT INDEX ft_index_in_proceeding_crossref ON in_proceeding (crossref ASC) ;

CREATE FULLTEXT INDEX ft_index_in_proceeding_booktitle ON in_proceeding (booktitle ASC) ;

CREATE FULLTEXT INDEX ft_index_in_proceeding_cite ON in_proceeding (cite ASC) ;

CREATE FULLTEXT INDEX ft_index_in_proceeding_note ON in_proceeding (note ASC) ;

CREATE FULLTEXT INDEX ft_index_in_proceeding_cdrom ON in_proceeding (cdrom ASC) ;

CREATE FULLTEXT INDEX ft_index_in_proceeing_year ON in_proceeding (year ASC) ;


CREATE  TABLE IF NOT EXISTS master_thesis (  id INT(11)  NULL DEFAULT NULL ,  thesis_id VARCHAR(50) NULL DEFAULT NULL ,  title TEXT NULL DEFAULT NULL ,  ee TEXT NULL DEFAULT NULL ,  year VARCHAR(11) NULL DEFAULT NULL ,  url TEXT NULL DEFAULT NULL ,  author_id INT(11) NULL DEFAULT NULL , PRIMARY KEY (`id`)) ENGINE = MyISAM DEFAULT CHARACTER SET = utf8;

CREATE INDEX persa ON master_thesis (author_id ASC) ;

CREATE INDEX author5 ON master_thesis (id ASC) ;

CREATE INDEX id ON master_thesis (thesis_id ASC) ;

CREATE FULLTEXT INDEX ft_index_master_thesis_thesis_id ON master_thesis (thesis_id ASC) ;

CREATE FULLTEXT INDEX ft_index_master_thesis_title ON master_thesis (title ASC) ;

CREATE FULLTEXT INDEX ft_index_master_thesis_ee ON master_thesis (ee ASC) ;

CREATE FULLTEXT INDEX ft_index_master_thesis_url ON master_thesis (url ASC) ;

CREATE FULLTEXT INDEX ft_index_master_thesis_year ON master_thesis (year ASC) ;


CREATE  TABLE IF NOT EXISTS person (  id INT(11) NULL DEFAULT NULL ,  name TEXT CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NULL DEFAULT NULL ,  phone TEXT CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NULL DEFAULT NULL ,  email TEXT CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NULL DEFAULT NULL ,  homepage TEXT CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NULL DEFAULT NULL ,  affiliation TEXT CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NULL DEFAULT NULL ,  address TEXT CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NULL DEFAULT NULL ,  bio TEXT CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NULL DEFAULT NULL ,  position TEXT CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NULL DEFAULT NULL ,  phd_univ TEXT CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NULL DEFAULT NULL ,  phd_major TEXT CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NULL DEFAULT NULL ,  phd_date TEXT CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NULL DEFAULT NULL ,  ms_univ TEXT CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NULL DEFAULT NULL ,  ms_major TEXT CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NULL DEFAULT NULL ,  ms_date TEXT CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NULL DEFAULT NULL ,  bs_univ TEXT CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NULL DEFAULT NULL ,  bs_major TEXT CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NULL DEFAULT NULL ,  bs_date TEXT CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NULL DEFAULT NULL  , PRIMARY KEY (`id`)) ENGINE = MyISAM DEFAULT CHARACTER SET = utf8 COLLATE = utf8_unicode_ci;

CREATE INDEX id ON person (id ASC) ;

CREATE FULLTEXT INDEX ft_index_person_name ON person (name ASC) ;

CREATE FULLTEXT INDEX ft_index_person_phone ON person (phone ASC) ;

CREATE FULLTEXT INDEX ft_index_person_email ON person (email ASC) ;

CREATE FULLTEXT INDEX ft_index_person_homepage ON person (homepage ASC) ;

CREATE FULLTEXT INDEX ft_index_person_affiliation ON person (affiliation ASC) ;

CREATE FULLTEXT INDEX ft_index_person_address ON person (address ASC) ;

CREATE FULLTEXT INDEX ft_index_person_bio ON person (bio ASC) ;

CREATE FULLTEXT INDEX ft_index_person_position ON person (position ASC) ;

CREATE FULLTEXT INDEX ft_index_person_phd_univ ON person (phd_univ ASC) ;

CREATE FULLTEXT INDEX ft_index_person_phd_major ON person (phd_major ASC) ;

CREATE FULLTEXT INDEX ft_index_person_ms_univ ON person (ms_univ ASC) ;

CREATE FULLTEXT INDEX ft_index_person_ms_major ON person (ms_major ASC) ;

CREATE FULLTEXT INDEX ft_index_person_bs_univ ON person (bs_univ ASC) ;

CREATE FULLTEXT INDEX ft_index_person_bs_major ON person (bs_major ASC) ;

CREATE FULLTEXT INDEX ft_person_phd_date ON person (phd_date ASC) ;

CREATE FULLTEXT INDEX ft_person_ms_date ON person (ms_date ASC) ;

CREATE FULLTEXT INDEX ft_person_bs_date ON person (bs_date ASC) ;


CREATE  TABLE IF NOT EXISTS phd_thesis (  id INT(11)  NULL DEFAULT NULL ,  thesis_id VARCHAR(50) NULL DEFAULT NULL ,  title TEXT NULL DEFAULT NULL ,  volume TEXT NULL DEFAULT NULL ,  number TEXT NULL DEFAULT NULL ,  ee TEXT NULL DEFAULT NULL ,  year VARCHAR(11) NULL DEFAULT NULL ,  url TEXT NULL DEFAULT NULL ,  publisher TEXT NULL DEFAULT NULL ,  school TEXT NULL DEFAULT NULL ,  month TEXT NULL DEFAULT NULL ,  note TEXT NULL DEFAULT NULL ,  isbn TEXT NULL DEFAULT NULL ,  author_id INT(11) NULL DEFAULT NULL , PRIMARY KEY (`id`)) ENGINE = MyISAM DEFAULT CHARACTER SET = utf8;

CREATE INDEX authb ON phd_thesis (author_id ASC) ;

CREATE INDEX id ON phd_thesis (id ASC) ;

CREATE INDEX thesis_id ON phd_thesis (thesis_id ASC) ;

CREATE FULLTEXT INDEX phd_thesis_thesis_id ON phd_thesis (thesis_id ASC) ;

CREATE FULLTEXT INDEX phd_thesis_title ON phd_thesis (title ASC) ;

CREATE FULLTEXT INDEX phd_thesis_volume ON phd_thesis (volume ASC) ;

CREATE FULLTEXT INDEX phd_thesis_number ON phd_thesis (number ASC) ;

CREATE FULLTEXT INDEX phd_thesis_ee ON phd_thesis (ee ASC) ;

CREATE FULLTEXT INDEX phd_thesis_url ON phd_thesis (url ASC) ;

CREATE FULLTEXT INDEX phd_thesis_publisher ON phd_thesis (publisher ASC) ;

CREATE FULLTEXT INDEX phd_thesis_school ON phd_thesis (school ASC) ;

CREATE FULLTEXT INDEX phd_thesis_month ON phd_thesis (month ASC) ;

CREATE FULLTEXT INDEX phd_thesis_note ON phd_thesis (note ASC) ;

CREATE FULLTEXT INDEX phd_thesis_isbn ON phd_thesis (isbn ASC) ;

CREATE FULLTEXT INDEX ft_index_phd_index_year ON phd_thesis (year ASC) ;


CREATE  TABLE IF NOT EXISTS proceeding (  id INT(11)  NULL DEFAULT NULL ,  proc_id VARCHAR(50)  NULL DEFAULT NULL ,  title TEXT NULL DEFAULT NULL ,  volume TEXT NULL DEFAULT NULL ,  number TEXT NULL DEFAULT NULL ,  ee TEXT NULL DEFAULT NULL ,  year TEXT NULL DEFAULT NULL ,  journal TEXT NULL DEFAULT NULL ,  pages TEXT NULL DEFAULT NULL ,  url TEXT NULL DEFAULT NULL ,  publisher TEXT NULL DEFAULT NULL ,  crossref TEXT NULL DEFAULT NULL ,  booktitle TEXT NULL DEFAULT NULL ,  note TEXT NULL DEFAULT NULL ,  isbn TEXT NULL DEFAULT NULL ,  series TEXT NULL DEFAULT NULL ,  address TEXT NULL DEFAULT NULL , PRIMARY KEY (`id`)) ENGINE = MyISAM DEFAULT CHARACTER SET = utf8;

CREATE UNIQUE INDEX proc_id_UNIQUE ON proceeding (proc_id ASC) ;

CREATE INDEX id ON proceeding (id ASC) ;

CREATE FULLTEXT INDEX ft_index_proceeding_proc_id ON proceeding (proc_id ASC) ;

CREATE FULLTEXT INDEX ft_index_proceeding_title ON proceeding (title ASC) ;

CREATE FULLTEXT INDEX ft_index_proceeding_volume ON proceeding (volume ASC) ;

CREATE FULLTEXT INDEX ft_index_proceeding_number ON proceeding (number ASC) ;

CREATE FULLTEXT INDEX ft_index_proceeding_ee ON proceeding (ee ASC) ;

CREATE FULLTEXT INDEX ft_index_proceeding_journal ON proceeding (journal ASC) ;

CREATE FULLTEXT INDEX ft_index_proceeding_pages ON proceeding (pages ASC) ;

CREATE FULLTEXT INDEX ft_index_proceeding_url ON proceeding (url ASC) ;

CREATE FULLTEXT INDEX ft_index_proceeding_publisher ON proceeding (publisher ASC) ;

CREATE FULLTEXT INDEX ft_index_proceeding_crossref ON proceeding (crossref ASC) ;

CREATE FULLTEXT INDEX ft_index_proceeding_booktitle ON proceeding (booktitle ASC) ;

CREATE FULLTEXT INDEX ft_index_proceeding_note ON proceeding (note ASC) ;

CREATE FULLTEXT INDEX ft_index_proceeding_isbn ON proceeding (isbn ASC) ;

CREATE FULLTEXT INDEX ft_index_proceeding_series ON proceeding (series ASC) ;

CREATE FULLTEXT INDEX ft_index_proceeding_address ON proceeding (address ASC) ;

CREATE FULLTEXT INDEX ft_index_proceeding_year ON proceeding (year ASC) ;


CREATE  TABLE IF NOT EXISTS www (  id INT(11) NULL DEFAULT NULL ,  www_id VARCHAR(50) NULL DEFAULT NULL ,  title TEXT NULL DEFAULT NULL ,  ee TEXT NULL DEFAULT NULL ,  year VARCHAR(11) NULL DEFAULT NULL ,  url TEXT NULL DEFAULT NULL ,  crossref TEXT NULL DEFAULT NULL ,  booktitle TEXT NULL DEFAULT NULL ,  cite TEXT NULL DEFAULT NULL ,  note TEXT NULL DEFAULT NULL , PRIMARY KEY (`id`)) ENGINE = MyISAM DEFAULT CHARACTER SET = utf8;

CREATE INDEX id ON www (id ASC) ;

CREATE INDEX www_id ON www (www_id ASC) ;

CREATE FULLTEXT INDEX ft_index_www_www_id ON www (www_id ASC) ;

CREATE FULLTEXT INDEX ft_index_www_title ON www (title ASC) ;

CREATE FULLTEXT INDEX ft_index_www_ee ON www (ee ASC) ;

CREATE FULLTEXT INDEX ft_index_www_url ON www (url ASC) ;

CREATE FULLTEXT INDEX ft_index_www_crossref ON www (crossref ASC) ;

CREATE FULLTEXT INDEX ft_index_www_booktitle ON www (booktitle ASC) ;

CREATE FULLTEXT INDEX ft_index_www_cite ON www (cite ASC) ;

CREATE FULLTEXT INDEX ft_index_www_note ON www (note ASC) ;

CREATE FULLTEXT INDEX ft_index_www_year ON www (year ASC) ;