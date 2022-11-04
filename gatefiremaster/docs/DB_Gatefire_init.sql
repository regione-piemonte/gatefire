/* ---------------------------------------------------------------------- */
/* Add sequences                                                          */
/* ---------------------------------------------------------------------- */

CREATE SEQUENCE gatefire.gatefire_l_evento_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 1 CACHE 1;

CREATE SEQUENCE gatefire.gatefire_r_ca_funzione_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 1 CACHE 1;

CREATE SEQUENCE gatefire.gatefire_r_repository_funzione_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 1 CACHE 1;

CREATE SEQUENCE gatefire.gatefire_r_funzione_applicazione_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 1 CACHE 1;

CREATE SEQUENCE gatefire.gatefire_d_messaggio_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 1 CACHE 1;

CREATE SEQUENCE gatefire.gatefire_l_evento_step_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 1 CACHE 1;

CREATE SEQUENCE gatefire.gatefire_t_amministratore_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 2147483647 START 1 CACHE 1;

CREATE SEQUENCE gatefire.gatefire_t_parametro_operativo_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 2147483647 START 1 CACHE 1;

CREATE SEQUENCE gatefire.version_control_change_number_seq INCREMENT 1 MINVALUE 1 MAXVALUE 2147483647 START 1 CACHE 1;

CREATE SEQUENCE gatefire.gatefire_r_repository_account_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 1 CACHE 1;

CREATE SEQUENCE gatefire.gatefire_r_repository_uri_id_seq INCREMENT 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 1 CACHE 1;

/* ---------------------------------------------------------------------- */
/* Add tables                                                             */
/* ---------------------------------------------------------------------- */

/* ---------------------------------------------------------------------- */
/* Add table "gatefire.gatefire_d_applicazione"                           */
/* ---------------------------------------------------------------------- */

CREATE TABLE gatefire.gatefire_d_applicazione (
    codice TEXT  NOT NULL,
    nome TEXT  NOT NULL,
    descrizione TEXT  NOT NULL,
    repository_url TEXT  NOT NULL,
    data_inizio_validita TIMESTAMP DEFAULT CURRENT_TIMESTAMP  NOT NULL,
    data_fine_validita TIMESTAMP,
    data_creazione TIMESTAMP DEFAULT CURRENT_TIMESTAMP  NOT NULL,
    data_aggiornamento TIMESTAMP DEFAULT CURRENT_TIMESTAMP  NOT NULL,
    data_cancellazione TIMESTAMP,
    sender_institution TEXT,
    folder_identification_code TEXT,
    id_sistema_regionale TEXT,
    CONSTRAINT gatefire_d_applicazione_pk PRIMARY KEY (codice)
);

COMMENT ON TABLE gatefire.gatefire_d_applicazione IS 'Anagrafica applicazioni';

COMMENT ON COLUMN gatefire.gatefire_d_applicazione.codice IS 'Codice applicazione';

COMMENT ON COLUMN gatefire.gatefire_d_applicazione.nome IS 'Nome applicazione';

COMMENT ON COLUMN gatefire.gatefire_d_applicazione.descrizione IS 'Descrizione applicazione';

COMMENT ON COLUMN gatefire.gatefire_d_applicazione.repository_url IS 'URL repository applicazione';

COMMENT ON COLUMN gatefire.gatefire_d_applicazione.data_inizio_validita IS 'Data di inizio validita applicazione';

COMMENT ON COLUMN gatefire.gatefire_d_applicazione.data_fine_validita IS 'Data di fine validita applicazione';

COMMENT ON COLUMN gatefire.gatefire_d_applicazione.data_creazione IS 'Data di creazione applicazione';

COMMENT ON COLUMN gatefire.gatefire_d_applicazione.data_aggiornamento IS 'Data di aggiornamento applicazione';

COMMENT ON COLUMN gatefire.gatefire_d_applicazione.data_cancellazione IS 'Data di cancellazione applicazione';

/* ---------------------------------------------------------------------- */
/* Add table "gatefire.gatefire_d_funzione"                               */
/* ---------------------------------------------------------------------- */

CREATE TABLE gatefire.gatefire_d_funzione (
    codice TEXT  NOT NULL,
    descrizione TEXT  NOT NULL,
    data_inizio_validita TIMESTAMP DEFAULT CURRENT_TIMESTAMP  NOT NULL,
    data_fine_validita TIMESTAMP,
    data_creazione TIMESTAMP DEFAULT CURRENT_TIMESTAMP  NOT NULL,
    data_aggiornamento TIMESTAMP DEFAULT CURRENT_TIMESTAMP  NOT NULL,
    data_cancellazione TIMESTAMP,
    CONSTRAINT gatefire_d_funzione_pk PRIMARY KEY (codice)
);

COMMENT ON TABLE gatefire.gatefire_d_funzione IS 'Anagrafica funzioni supportate dal Gateway';

COMMENT ON COLUMN gatefire.gatefire_d_funzione.codice IS 'Identificativo univoco della funzione';

COMMENT ON COLUMN gatefire.gatefire_d_funzione.descrizione IS 'Descrizione della funzione';

COMMENT ON COLUMN gatefire.gatefire_d_funzione.data_inizio_validita IS 'Data di inizio validita della funzione';

COMMENT ON COLUMN gatefire.gatefire_d_funzione.data_fine_validita IS 'Data di fine validita della funzione';

COMMENT ON COLUMN gatefire.gatefire_d_funzione.data_creazione IS 'Data di creazione della funzione';

COMMENT ON COLUMN gatefire.gatefire_d_funzione.data_aggiornamento IS 'Data di aggiornamento della funzione';

COMMENT ON COLUMN gatefire.gatefire_d_funzione.data_cancellazione IS 'Data di cancellazione della funzione';

/* ---------------------------------------------------------------------- */
/* Add table "gatefire.gatefire_d_livello_messaggio"                      */
/* ---------------------------------------------------------------------- */

CREATE TABLE gatefire.gatefire_d_livello_messaggio (
    tipo TEXT  NOT NULL,
    descrizione TEXT  NOT NULL,
    data_creazione TIMESTAMP DEFAULT CURRENT_TIMESTAMP  NOT NULL,
    data_aggiornamento TIMESTAMP DEFAULT CURRENT_TIMESTAMP  NOT NULL,
    CONSTRAINT gatefire_d_tipo_messaggio_pk PRIMARY KEY (tipo)
);

COMMENT ON TABLE gatefire.gatefire_d_livello_messaggio IS 'Anagrafica dei livelli di messaggi dell''applicazione';

COMMENT ON COLUMN gatefire.gatefire_d_livello_messaggio.tipo IS 'Tipo del messaggio';

COMMENT ON COLUMN gatefire.gatefire_d_livello_messaggio.descrizione IS 'Descrizione del tipo del messaggio';

COMMENT ON COLUMN gatefire.gatefire_d_livello_messaggio.data_creazione IS 'Data di creazione del tipo del messaggio';

COMMENT ON COLUMN gatefire.gatefire_d_livello_messaggio.data_aggiornamento IS 'Data di aggiornamento del tipo del messaggio';

/* ---------------------------------------------------------------------- */
/* Add table "gatefire.gatefire_d_messaggio"                              */
/* ---------------------------------------------------------------------- */

CREATE TABLE gatefire.gatefire_d_messaggio (
    id BIGINT  NOT NULL,
    codice TEXT,
    livello TEXT  NOT NULL,
    descrizione_sintetica TEXT  NOT NULL,
    descrizione_estesa TEXT  NOT NULL,
    data_inizio_validita TIMESTAMP DEFAULT CURRENT_TIMESTAMP  NOT NULL,
    data_fine_validita TIMESTAMP,
    data_creazione TIMESTAMP DEFAULT CURRENT_TIMESTAMP  NOT NULL,
    data_aggiornamento TIMESTAMP DEFAULT CURRENT_TIMESTAMP  NOT NULL,
    data_cancellazione TIMESTAMP,
    CONSTRAINT gatefire_d_messaggio_pk PRIMARY KEY (id)
);

CREATE INDEX gatefire_d_messaggio_codice_uk ON gatefire.gatefire_d_messaggio (codice);

COMMENT ON TABLE gatefire.gatefire_d_messaggio IS 'Anagrafica dei messaggi che compaiono all''utente in GUI';

COMMENT ON COLUMN gatefire.gatefire_d_messaggio.id IS 'Identificativo univoco del messaggio';

COMMENT ON COLUMN gatefire.gatefire_d_messaggio.descrizione_sintetica IS 'Descrizione sintetica del messaggio';

COMMENT ON COLUMN gatefire.gatefire_d_messaggio.descrizione_estesa IS 'Messaggio completo';

COMMENT ON COLUMN gatefire.gatefire_d_messaggio.data_inizio_validita IS 'Data di inizio validita del messaggio';

COMMENT ON COLUMN gatefire.gatefire_d_messaggio.data_fine_validita IS 'Data di fine validita del messaggio';

COMMENT ON COLUMN gatefire.gatefire_d_messaggio.data_creazione IS 'Data di creazione del messaggio';

COMMENT ON COLUMN gatefire.gatefire_d_messaggio.data_aggiornamento IS 'Data di aggiornamento del messaggio';

COMMENT ON COLUMN gatefire.gatefire_d_messaggio.data_cancellazione IS 'Data di cancellazione del messaggio';

/* ---------------------------------------------------------------------- */
/* Add table "gatefire.gatefire_d_operazione_evento"                      */
/* ---------------------------------------------------------------------- */

CREATE TABLE gatefire.gatefire_d_operazione_evento (
    codice TEXT  NOT NULL,
    descrizione TEXT  NOT NULL,
    data_inizio_validita TIMESTAMP DEFAULT CURRENT_TIMESTAMP  NOT NULL,
    data_fine_validita TIMESTAMP,
    data_creazione TIMESTAMP DEFAULT CURRENT_TIMESTAMP  NOT NULL,
    data_aggiornamento TIMESTAMP DEFAULT CURRENT_TIMESTAMP  NOT NULL,
    data_cancellazione TIMESTAMP,
    CONSTRAINT gatefire_d_operazione_evento_pk PRIMARY KEY (codice)
);

COMMENT ON TABLE gatefire.gatefire_d_operazione_evento IS 'Anagrafica tipologia ambito evento';

COMMENT ON COLUMN gatefire.gatefire_d_operazione_evento.codice IS 'Codice della tipologia ambito evento';

COMMENT ON COLUMN gatefire.gatefire_d_operazione_evento.descrizione IS 'Descrizione della tipologia ambito evento';

COMMENT ON COLUMN gatefire.gatefire_d_operazione_evento.data_inizio_validita IS 'Data di inizio validita della tipologia ambito evento';

COMMENT ON COLUMN gatefire.gatefire_d_operazione_evento.data_fine_validita IS 'Data di fine validita della tipologia ambito evento';

COMMENT ON COLUMN gatefire.gatefire_d_operazione_evento.data_creazione IS 'Data di creazione della tipologia ambito evento';

COMMENT ON COLUMN gatefire.gatefire_d_operazione_evento.data_aggiornamento IS 'Data di aggiornamento della tipologia ambito evento';

COMMENT ON COLUMN gatefire.gatefire_d_operazione_evento.data_cancellazione IS 'Data di cancellazione della tipologia ambito evento';

/* ---------------------------------------------------------------------- */
/* Add table "gatefire.gatefire_d_repository"                             */
/* ---------------------------------------------------------------------- */

CREATE TABLE gatefire.gatefire_d_repository (
    codice TEXT  NOT NULL,
    nome TEXT  NOT NULL,
    descrizione TEXT  NOT NULL,
    data_inizio_validita TIMESTAMP DEFAULT CURRENT_TIMESTAMP  NOT NULL,
    data_fine_validita TIMESTAMP,
    data_creazione TIMESTAMP DEFAULT CURRENT_TIMESTAMP  NOT NULL,
    data_aggiornamento TIMESTAMP DEFAULT CURRENT_TIMESTAMP  NOT NULL,
    data_cancellazione TIMESTAMP,
    collocazione TEXT,
    authentication_required BOOLEAN DEFAULT true  NOT NULL,
    gestione_consensi CHARACTER VARYING(1),
    gestione_id_episodio BOOLEAN DEFAULT false,
    tipo_repository TEXT,
    CONSTRAINT gatefire_d_repository_pk PRIMARY KEY (codice)
);

COMMENT ON TABLE gatefire.gatefire_d_repository IS 'Anagrafica repository';

COMMENT ON COLUMN gatefire.gatefire_d_repository.codice IS 'Identificativo univoco del repository';

COMMENT ON COLUMN gatefire.gatefire_d_repository.nome IS 'Nome del repository';

COMMENT ON COLUMN gatefire.gatefire_d_repository.descrizione IS 'Descrizione del repository';

COMMENT ON COLUMN gatefire.gatefire_d_repository.data_inizio_validita IS 'Data di inizio validita del repository';

COMMENT ON COLUMN gatefire.gatefire_d_repository.data_fine_validita IS 'Data di fine validita del repository';

COMMENT ON COLUMN gatefire.gatefire_d_repository.data_creazione IS 'Data di creazione del repository';

COMMENT ON COLUMN gatefire.gatefire_d_repository.data_aggiornamento IS 'Data di aggiornamento del repository';

COMMENT ON COLUMN gatefire.gatefire_d_repository.data_cancellazione IS 'Data di cancellazione del repository';

COMMENT ON COLUMN gatefire.gatefire_d_repository.collocazione IS 'Utente firmatario';

/* ---------------------------------------------------------------------- */
/* Add table "gatefire.gatefire_d_stato_evento"                           */
/* ---------------------------------------------------------------------- */

CREATE TABLE gatefire.gatefire_d_stato_evento (
    codice TEXT  NOT NULL,
    descrizione TEXT,
    data_inizio_validita TIMESTAMP DEFAULT CURRENT_TIMESTAMP  NOT NULL,
    data_fine_validita TIMESTAMP,
    data_creazione TIMESTAMP DEFAULT CURRENT_TIMESTAMP  NOT NULL,
    data_aggiornamento TIMESTAMP DEFAULT CURRENT_TIMESTAMP  NOT NULL,
    data_cancellazione TIMESTAMP,
    CONSTRAINT gatefire_d_stato_evento_pk PRIMARY KEY (codice)
);

COMMENT ON TABLE gatefire.gatefire_d_stato_evento IS 'Anagrafica stati evento';

COMMENT ON COLUMN gatefire.gatefire_d_stato_evento.codice IS 'Codice stato evento';

COMMENT ON COLUMN gatefire.gatefire_d_stato_evento.descrizione IS 'Descrizione stato evento';

COMMENT ON COLUMN gatefire.gatefire_d_stato_evento.data_inizio_validita IS 'Data di inizio validita stato evento';

COMMENT ON COLUMN gatefire.gatefire_d_stato_evento.data_fine_validita IS 'Data di fine validita stato evento';

COMMENT ON COLUMN gatefire.gatefire_d_stato_evento.data_creazione IS 'Data di creazione stato evento';

COMMENT ON COLUMN gatefire.gatefire_d_stato_evento.data_aggiornamento IS 'Data di aggiornamento stato evento';

COMMENT ON COLUMN gatefire.gatefire_d_stato_evento.data_cancellazione IS 'Data di cancellazione stato evento';

/* ---------------------------------------------------------------------- */
/* Add table "gatefire.gatefire_d_stato_evento_step"                      */
/* ---------------------------------------------------------------------- */

CREATE TABLE gatefire.gatefire_d_stato_evento_step (
    codice TEXT  NOT NULL,
    descrizione TEXT  NOT NULL,
    data_inizio_validita TIMESTAMP DEFAULT CURRENT_TIMESTAMP  NOT NULL,
    data_fine_validita TIMESTAMP,
    data_creazione TIMESTAMP DEFAULT CURRENT_TIMESTAMP  NOT NULL,
    data_aggiornamento TIMESTAMP DEFAULT CURRENT_TIMESTAMP  NOT NULL,
    data_cancellazione TIMESTAMP,
    CONSTRAINT gatefire_d_stato_evento_step_pk PRIMARY KEY (codice)
);

COMMENT ON TABLE gatefire.gatefire_d_stato_evento_step IS 'Anagrafica degli stati degli step degli eventi';

COMMENT ON COLUMN gatefire.gatefire_d_stato_evento_step.codice IS 'Codice dello stato dello step di un evento';

COMMENT ON COLUMN gatefire.gatefire_d_stato_evento_step.descrizione IS 'Descrizione dello stato dello step di un evento';

COMMENT ON COLUMN gatefire.gatefire_d_stato_evento_step.data_inizio_validita IS 'Data di inizio validita dello stato dello step di un evento';

COMMENT ON COLUMN gatefire.gatefire_d_stato_evento_step.data_fine_validita IS 'Data di fine validita dello stato dello step di un evento';

COMMENT ON COLUMN gatefire.gatefire_d_stato_evento_step.data_creazione IS 'Data di creazione dello stato dello step di un evento';

COMMENT ON COLUMN gatefire.gatefire_d_stato_evento_step.data_aggiornamento IS 'Data di aggiornamento dello stato dello step di un evento';

COMMENT ON COLUMN gatefire.gatefire_d_stato_evento_step.data_cancellazione IS 'Data di cancellazione dello stato dello step di un evento';

/* ---------------------------------------------------------------------- */
/* Add table "gatefire.gatefire_d_tag_ca"                                 */
/* ---------------------------------------------------------------------- */

CREATE TABLE gatefire.gatefire_d_tag_ca (
    nome TEXT  NOT NULL,
    descrizione TEXT  NOT NULL,
    data_inizio_validita TIMESTAMP DEFAULT CURRENT_TIMESTAMP  NOT NULL,
    data_fine_validita TIMESTAMP,
    data_creazione TIMESTAMP DEFAULT CURRENT_TIMESTAMP  NOT NULL,
    data_aggiornamento TIMESTAMP DEFAULT CURRENT_TIMESTAMP  NOT NULL,
    data_cancellazione TIMESTAMP,
    CONSTRAINT gatefire_d_tag_ca_pk PRIMARY KEY (nome)
);

COMMENT ON TABLE gatefire.gatefire_d_tag_ca IS 'Elenco dei tag delle C.A. (da inserire nella richiesta dei servizi)';

COMMENT ON COLUMN gatefire.gatefire_d_tag_ca.nome IS 'Tag della C.A.';

COMMENT ON COLUMN gatefire.gatefire_d_tag_ca.descrizione IS 'Descrizione del tag della C.A.';

COMMENT ON COLUMN gatefire.gatefire_d_tag_ca.data_inizio_validita IS 'Data di inizio validita del tag della C.A.';

COMMENT ON COLUMN gatefire.gatefire_d_tag_ca.data_fine_validita IS 'Data di fine validita del tag della C.A.';

COMMENT ON COLUMN gatefire.gatefire_d_tag_ca.data_creazione IS 'Data di creazione del tag della C.A.';

COMMENT ON COLUMN gatefire.gatefire_d_tag_ca.data_aggiornamento IS 'Data di creazione del tag della C.A.';

COMMENT ON COLUMN gatefire.gatefire_d_tag_ca.data_cancellazione IS 'Data di creazione del tag della C.A.';

/* ---------------------------------------------------------------------- */
/* Add table "gatefire.gatefire_d_tipo_evento"                            */
/* ---------------------------------------------------------------------- */

CREATE TABLE gatefire.gatefire_d_tipo_evento (
    codice TEXT  NOT NULL,
    descrizione TEXT  NOT NULL,
    data_inizio_validita TIMESTAMP DEFAULT CURRENT_TIMESTAMP  NOT NULL,
    data_fine_validita TIMESTAMP,
    data_creazione TIMESTAMP DEFAULT CURRENT_TIMESTAMP  NOT NULL,
    data_aggiornamento TIMESTAMP DEFAULT CURRENT_TIMESTAMP  NOT NULL,
    data_cancellazione TIMESTAMP,
    CONSTRAINT gatefire_d_tipo_evento_pk PRIMARY KEY (codice)
);

COMMENT ON TABLE gatefire.gatefire_d_tipo_evento IS 'Anagrafica dei tipi evento';

COMMENT ON COLUMN gatefire.gatefire_d_tipo_evento.codice IS 'Codice tipo evento';

COMMENT ON COLUMN gatefire.gatefire_d_tipo_evento.descrizione IS 'Descrizione tipo evento';

COMMENT ON COLUMN gatefire.gatefire_d_tipo_evento.data_inizio_validita IS 'Data di inizio validita del tipo evento';

COMMENT ON COLUMN gatefire.gatefire_d_tipo_evento.data_fine_validita IS 'Data di fine validita del tipo evento';

COMMENT ON COLUMN gatefire.gatefire_d_tipo_evento.data_creazione IS 'Data di creazione del tipo evento';

COMMENT ON COLUMN gatefire.gatefire_d_tipo_evento.data_aggiornamento IS 'Data di aggiornamento del tipo evento';

COMMENT ON COLUMN gatefire.gatefire_d_tipo_evento.data_cancellazione IS 'Data di cancellazione del tipo evento';

/* ---------------------------------------------------------------------- */
/* Add table "gatefire.gatefire_d_tipo_parametro"                         */
/* ---------------------------------------------------------------------- */

CREATE TABLE gatefire.gatefire_d_tipo_parametro (
    tipo TEXT  NOT NULL,
    descrizione TEXT  NOT NULL,
    data_creazione TIMESTAMP DEFAULT CURRENT_TIMESTAMP  NOT NULL,
    data_aggiornamento TIMESTAMP DEFAULT CURRENT_TIMESTAMP  NOT NULL,
    data_cancellazione TIMESTAMP,
    CONSTRAINT gatefire_d_tipo_parametro_pk PRIMARY KEY (tipo)
);

COMMENT ON TABLE gatefire.gatefire_d_tipo_parametro IS 'Anagrafica dei tipi di parametri dell''applicazione';

COMMENT ON COLUMN gatefire.gatefire_d_tipo_parametro.tipo IS 'Tipo di parametro dell''applicazione';

COMMENT ON COLUMN gatefire.gatefire_d_tipo_parametro.descrizione IS 'Descrizione del tipo di parametro dell''applicazione';

COMMENT ON COLUMN gatefire.gatefire_d_tipo_parametro.data_creazione IS 'Data di creazione del tipo di parametro dell''applicazione';

COMMENT ON COLUMN gatefire.gatefire_d_tipo_parametro.data_aggiornamento IS 'Data di aggiornamento del tipo di parametro dell''applicazione';

COMMENT ON COLUMN gatefire.gatefire_d_tipo_parametro.data_cancellazione IS 'Data di cancellazione del tipo di parametro dell''applicazione';

/* ---------------------------------------------------------------------- */
/* Add table "gatefire.gatefire_d_tipo_passo_evento_step"                 */
/* ---------------------------------------------------------------------- */

CREATE TABLE gatefire.gatefire_d_tipo_passo_evento_step (
    codice TEXT  NOT NULL,
    descrizione TEXT  NOT NULL,
    data_inizio_validita TIMESTAMP DEFAULT CURRENT_TIMESTAMP  NOT NULL,
    data_fine_validita TIMESTAMP,
    data_creazione TIMESTAMP DEFAULT CURRENT_TIMESTAMP  NOT NULL,
    data_aggiornamento TIMESTAMP DEFAULT CURRENT_TIMESTAMP  NOT NULL,
    data_cancellazione TIMESTAMP,
    CONSTRAINT gatefire_d_tipo_passo_evento_step_pk PRIMARY KEY (codice)
);

COMMENT ON TABLE gatefire.gatefire_d_tipo_passo_evento_step IS 'Anagrafica tipo passo step evento';

COMMENT ON COLUMN gatefire.gatefire_d_tipo_passo_evento_step.codice IS 'Codice step evento';

COMMENT ON COLUMN gatefire.gatefire_d_tipo_passo_evento_step.descrizione IS 'Descrizione step evento';

COMMENT ON COLUMN gatefire.gatefire_d_tipo_passo_evento_step.data_inizio_validita IS 'Data di inizio validita step evento';

COMMENT ON COLUMN gatefire.gatefire_d_tipo_passo_evento_step.data_fine_validita IS 'Data di fine validita step evento';

COMMENT ON COLUMN gatefire.gatefire_d_tipo_passo_evento_step.data_creazione IS 'Data di creazione step evento';

COMMENT ON COLUMN gatefire.gatefire_d_tipo_passo_evento_step.data_aggiornamento IS 'Data di aggiornamento step evento';

COMMENT ON COLUMN gatefire.gatefire_d_tipo_passo_evento_step.data_cancellazione IS 'Data di cancellazione step evento';

/* ---------------------------------------------------------------------- */
/* Add table "gatefire.gatefire_d_uso"                                    */
/* ---------------------------------------------------------------------- */

CREATE TABLE gatefire.gatefire_d_uso (
    uso TEXT  NOT NULL,
    descrizione TEXT  NOT NULL,
    data_creazione TIMESTAMP DEFAULT CURRENT_TIMESTAMP  NOT NULL,
    data_aggiornamento TIMESTAMP DEFAULT CURRENT_TIMESTAMP  NOT NULL,
    data_cancellazione TIMESTAMP,
    CONSTRAINT gatefire_d_uso_pk PRIMARY KEY (uso)
);

COMMENT ON TABLE gatefire.gatefire_d_uso IS 'Anagrafica degli usi di un parametro';

COMMENT ON COLUMN gatefire.gatefire_d_uso.uso IS 'Uso di un parametro';

COMMENT ON COLUMN gatefire.gatefire_d_uso.descrizione IS 'Descrizione dell''uso di un parametro';

COMMENT ON COLUMN gatefire.gatefire_d_uso.data_creazione IS 'Data di creazione uso di un parametro';

COMMENT ON COLUMN gatefire.gatefire_d_uso.data_aggiornamento IS 'Data di aggiornamento uso di un parametro';

COMMENT ON COLUMN gatefire.gatefire_d_uso.data_cancellazione IS 'Data di cancellazione uso di un parametro';

/* ---------------------------------------------------------------------- */
/* Add table "gatefire.gatefire_d_xdscode"                                */
/* ---------------------------------------------------------------------- */

CREATE TABLE gatefire.gatefire_d_xdscode (
    code_type CHARACTER VARYING(50)  NOT NULL,
    code CHARACTER VARYING(50)  NOT NULL,
    coding_scheme CHARACTER VARYING(50)  NOT NULL,
    display_name CHARACTER VARYING(250)  NOT NULL,
    CONSTRAINT gatefire_d_xdscode_pk PRIMARY KEY (code_type, code)
);

COMMENT ON TABLE gatefire.gatefire_d_xdscode IS 'Tabella contenente i dati di tabelle di lookup dell’Affinity Domain specificato da AGID per il fascicolo sanitario elettronico';

COMMENT ON COLUMN gatefire.gatefire_d_xdscode.code_type IS 'Tipo di codice';

COMMENT ON COLUMN gatefire.gatefire_d_xdscode.code IS 'Identificativo del nodo';

COMMENT ON COLUMN gatefire.gatefire_d_xdscode.coding_scheme IS 'Schema di codifica';

COMMENT ON COLUMN gatefire.gatefire_d_xdscode.display_name IS 'Stringa descrittiva del nodo che viene visualizzata';

/* ---------------------------------------------------------------------- */
/* Add table "gatefire.gatefire_r_ca_funzione"                            */
/* ---------------------------------------------------------------------- */

CREATE TABLE gatefire.gatefire_r_ca_funzione (
    id BIGINT  NOT NULL,
    codice_ca TEXT  NOT NULL,
    codice_funzione TEXT  NOT NULL,
    data_inizio_validita TIMESTAMP DEFAULT CURRENT_TIMESTAMP  NOT NULL,
    data_fine_validita TIMESTAMP,
    data_creazione TIMESTAMP DEFAULT CURRENT_TIMESTAMP  NOT NULL,
    data_aggiornamento TIMESTAMP DEFAULT CURRENT_TIMESTAMP  NOT NULL,
    data_cancellazione TIMESTAMP,
    CONSTRAINT gatefire_r_ca_funzione_pk PRIMARY KEY (id)
);

CREATE UNIQUE INDEX gatefire_r_ca_funzione_ca_funzione_uk ON gatefire.gatefire_r_ca_funzione (codice_ca,codice_funzione);

COMMENT ON TABLE gatefire.gatefire_r_ca_funzione IS 'Tabella di relazione che censisce le combinazioni tra Certification Autorithies e funzioni';

COMMENT ON COLUMN gatefire.gatefire_r_ca_funzione.id IS 'Codice univoco del record';

COMMENT ON COLUMN gatefire.gatefire_r_ca_funzione.data_inizio_validita IS 'Data di inizio validita della funzione associata alla  Certification Authority';

COMMENT ON COLUMN gatefire.gatefire_r_ca_funzione.data_fine_validita IS 'Data di fine validita della funzione associata alla  Certification Authority';

COMMENT ON COLUMN gatefire.gatefire_r_ca_funzione.data_creazione IS 'Data di creazione della funzione associata alla  Certification Authority';

COMMENT ON COLUMN gatefire.gatefire_r_ca_funzione.data_aggiornamento IS 'Data di aggiornamento della funzione associata alla  Certification Authority';

COMMENT ON COLUMN gatefire.gatefire_r_ca_funzione.data_cancellazione IS 'Data di cancellazione della funzione associata alla  Certification Authority';

/* ---------------------------------------------------------------------- */
/* Add table "gatefire.gatefire_r_funzione_applicazione"                  */
/* ---------------------------------------------------------------------- */

CREATE TABLE gatefire.gatefire_r_funzione_applicazione (
    id BIGINT  NOT NULL,
    codice_applicazione TEXT  NOT NULL,
    codice_funzione TEXT,
    data_inizio_validita TIMESTAMP DEFAULT CURRENT_TIMESTAMP  NOT NULL,
    data_fine_validita TIMESTAMP,
    data_creazione TIMESTAMP DEFAULT CURRENT_TIMESTAMP  NOT NULL,
    data_aggiornamento TIMESTAMP DEFAULT CURRENT_TIMESTAMP  NOT NULL,
    data_cancellazione TIMESTAMP,
    CONSTRAINT gatefire_r_funzione_applicazione_pk PRIMARY KEY (id)
);

CREATE UNIQUE INDEX gatefire_r_funzione_applicazione_funzione_applicazione_uk ON gatefire.gatefire_r_funzione_applicazione (codice_applicazione,codice_funzione);

COMMENT ON TABLE gatefire.gatefire_r_funzione_applicazione IS 'Tabella di relazione che censisce le combinazioni tra funzioni e applicazioni';

COMMENT ON COLUMN gatefire.gatefire_r_funzione_applicazione.id IS 'Codice univoco associazione tra applicazione e funzione';

COMMENT ON COLUMN gatefire.gatefire_r_funzione_applicazione.data_inizio_validita IS 'Data di inizio validita associazione tra applicazione e funzione';

COMMENT ON COLUMN gatefire.gatefire_r_funzione_applicazione.data_fine_validita IS 'Data di fine validita associazione tra applicazione e funzione';

COMMENT ON COLUMN gatefire.gatefire_r_funzione_applicazione.data_creazione IS 'Data di creazione associazione tra applicazione e funzione';

COMMENT ON COLUMN gatefire.gatefire_r_funzione_applicazione.data_aggiornamento IS 'Data di aggiornamento associazione tra applicazione e funzione';

COMMENT ON COLUMN gatefire.gatefire_r_funzione_applicazione.data_cancellazione IS 'Data di cancellazione associazione tra applicazione e funzione';

/* ---------------------------------------------------------------------- */
/* Add table "gatefire.gatefire_r_repository_account"                     */
/* ---------------------------------------------------------------------- */

CREATE TABLE gatefire.gatefire_r_repository_account (
    id BIGINT  NOT NULL,
    codice_repository TEXT  NOT NULL,
    codice_applicazione TEXT  NOT NULL,
    username TEXT  NOT NULL,
    hashed_password TEXT  NOT NULL,
    salt TEXT  NOT NULL,
    data_inizio_validita TIMESTAMP DEFAULT CURRENT_TIMESTAMP  NOT NULL,
    data_fine_validita TIMESTAMP,
    data_creazione TIMESTAMP DEFAULT CURRENT_TIMESTAMP  NOT NULL,
    data_aggiornamento TIMESTAMP DEFAULT CURRENT_TIMESTAMP  NOT NULL,
    data_cancellazione TIMESTAMP,
    CONSTRAINT gatefire_r_repository_account_pk PRIMARY KEY (id)
);

COMMENT ON TABLE gatefire.gatefire_r_repository_account IS 'Tabella di relazione che elenca le credenziali per verticale e collocazione sui vari repository';

/* ---------------------------------------------------------------------- */
/* Add table "gatefire.gatefire_r_repository_funzione"                    */
/* ---------------------------------------------------------------------- */

CREATE TABLE gatefire.gatefire_r_repository_funzione (
    id BIGINT  NOT NULL,
    codice_repository TEXT  NOT NULL,
    codice_funzione TEXT  NOT NULL,
    data_inizio_validita TIMESTAMP DEFAULT CURRENT_TIMESTAMP  NOT NULL,
    data_fine_validita TIMESTAMP,
    data_creazione TIMESTAMP DEFAULT CURRENT_TIMESTAMP  NOT NULL,
    data_aggiornamento TIMESTAMP DEFAULT CURRENT_TIMESTAMP  NOT NULL,
    data_cancellazione TIMESTAMP,
    CONSTRAINT gatefire_r_repository_funzione_pk PRIMARY KEY (id)
);

CREATE UNIQUE INDEX gatefire_r_repository_funzione_repository_funzione_uk ON gatefire.gatefire_r_repository_funzione (codice_repository,codice_funzione);

COMMENT ON TABLE gatefire.gatefire_r_repository_funzione IS 'Tabella di relazione che censisce le combinazioni tra Certification Autorithies e funzioni';

COMMENT ON COLUMN gatefire.gatefire_r_repository_funzione.id IS 'Codice univoco del record';

COMMENT ON COLUMN gatefire.gatefire_r_repository_funzione.data_inizio_validita IS 'Data di inizio validita del repository associato alla funzione';

COMMENT ON COLUMN gatefire.gatefire_r_repository_funzione.data_fine_validita IS 'Data di fine validita del repository associato alla funzione';

COMMENT ON COLUMN gatefire.gatefire_r_repository_funzione.data_creazione IS 'Data di creazione del repository associato alla funzione';

COMMENT ON COLUMN gatefire.gatefire_r_repository_funzione.data_aggiornamento IS 'Data di aggiornamento del repository associato alla funzione';

COMMENT ON COLUMN gatefire.gatefire_r_repository_funzione.data_cancellazione IS 'Data di cancellazione del repository associato alla funzione';

/* ---------------------------------------------------------------------- */
/* Add table "gatefire.gatefire_r_repository_uri"                         */
/* ---------------------------------------------------------------------- */

CREATE TABLE gatefire.gatefire_r_repository_uri (
    id BIGINT  NOT NULL,
    codice_repository TEXT  NOT NULL,
    transazione TEXT  NOT NULL,
    uri TEXT  NOT NULL,
    data_inizio_validita TIMESTAMP DEFAULT CURRENT_TIMESTAMP  NOT NULL,
    data_fine_validita TIMESTAMP,
    data_creazione TIMESTAMP DEFAULT CURRENT_TIMESTAMP  NOT NULL,
    data_aggiornamento TIMESTAMP DEFAULT CURRENT_TIMESTAMP  NOT NULL,
    data_cancellazione TIMESTAMP,
    CONSTRAINT gatefire_r_repository_uri_pkey PRIMARY KEY (id)
);

COMMENT ON TABLE gatefire.gatefire_r_repository_uri IS 'Tabella di relazione che elenca le uri dei repository per le fvarie transazioni';

/* ---------------------------------------------------------------------- */
/* Add table "gatefire.gatefire_t_amministratore"                         */
/* ---------------------------------------------------------------------- */

CREATE TABLE gatefire.gatefire_t_amministratore (
    id INTEGER  NOT NULL,
    cognome TEXT  NOT NULL,
    nome TEXT  NOT NULL,
    email TEXT  NOT NULL,
    password TEXT  NOT NULL,
    note TEXT,
    data_fine_validita TIMESTAMP,
    data_creazione TIMESTAMP DEFAULT CURRENT_TIMESTAMP  NOT NULL,
    data_aggiornamento TIMESTAMP DEFAULT CURRENT_TIMESTAMP  NOT NULL,
    CONSTRAINT gatefire_t_amministratore_pk PRIMARY KEY (id)
);

CREATE UNIQUE INDEX gatefire_t_amministratore_email_uk ON gatefire.gatefire_t_amministratore (email);

COMMENT ON TABLE gatefire.gatefire_t_amministratore IS 'La tabella contiene l''elenco degli utenti amministratori, ovvero di coloro che possono accedere alla Web Application di Amministrazione';

/* ---------------------------------------------------------------------- */
/* Add table "gatefire.gatefire_t_ruolo"                                  */
/* ---------------------------------------------------------------------- */

CREATE TABLE gatefire.gatefire_t_ruolo (
    codice CHARACTER VARYING(40)  NOT NULL,
    descrizione TEXT  NOT NULL,
    data_creazione TIMESTAMP DEFAULT CURRENT_TIMESTAMP  NOT NULL,
    data_aggiornamento TIMESTAMP DEFAULT CURRENT_TIMESTAMP  NOT NULL,
    CONSTRAINT gatefire_t_ruolo_pk PRIMARY KEY (codice)
);

/* ---------------------------------------------------------------------- */
/* Add table "gatefire.version_control"                                   */
/* ---------------------------------------------------------------------- */

CREATE TABLE gatefire.version_control (
    change_number INTEGER DEFAULT nextval('version_control_change_number_seq')  NOT NULL,
    release_version CHARACTER VARYING(11),
    description CHARACTER VARYING(200)  NOT NULL,
    release_type CHARACTER VARYING(20)  NOT NULL,
    script_name CHARACTER VARYING(1000)  NOT NULL,
    checksum UUID,
    installed_by CHARACTER VARYING(100)  NOT NULL,
    installed_on TIMESTAMP DEFAULT now()  NOT NULL,
    execution_time INTEGER  NOT NULL,
    success BOOLEAN  NOT NULL,
    CONSTRAINT version_control_pk PRIMARY KEY (change_number)
);

COMMENT ON TABLE gatefire.version_control IS 'Anagrafica delle versioni GATEFIRE';

COMMENT ON COLUMN gatefire.version_control.change_number IS 'Codice versione';

COMMENT ON COLUMN gatefire.version_control.release_version IS 'Numero di rilascio';

COMMENT ON COLUMN gatefire.version_control.description IS 'Descrizione del rilascio';

COMMENT ON COLUMN gatefire.version_control.release_type IS 'Tipo di rilascio';

COMMENT ON COLUMN gatefire.version_control.script_name IS 'Nome dello script di rilascio';

COMMENT ON COLUMN gatefire.version_control.checksum IS 'Checksum';

COMMENT ON COLUMN gatefire.version_control.installed_by IS 'Identificativo di chi ha installato la versione';

COMMENT ON COLUMN gatefire.version_control.installed_on IS 'Data di installazione';

COMMENT ON COLUMN gatefire.version_control.execution_time IS 'Tempo di esecuzione dell''installazione';

COMMENT ON COLUMN gatefire.version_control.success IS 'Esito dell''installazione';

/* ---------------------------------------------------------------------- */
/* Add table "gatefire.gatefire_d_certification_authority"                */
/* ---------------------------------------------------------------------- */

CREATE TABLE gatefire.gatefire_d_certification_authority (
    codice TEXT  NOT NULL,
    ragione_sociale TEXT  NOT NULL,
    url TEXT  NOT NULL,
    tag_ca TEXT  NOT NULL,
    data_inizio_validita TIMESTAMP DEFAULT CURRENT_TIMESTAMP  NOT NULL,
    data_fine_validita TIMESTAMP,
    data_creazione TIMESTAMP DEFAULT CURRENT_TIMESTAMP  NOT NULL,
    data_aggiornamento TIMESTAMP DEFAULT CURRENT_TIMESTAMP  NOT NULL,
    data_cancellazione TIMESTAMP,
    CONSTRAINT gatefire_d_certification_authority_pk PRIMARY KEY (codice)
);

COMMENT ON TABLE gatefire.gatefire_d_certification_authority IS 'Anagrafica Certification Authority';

COMMENT ON COLUMN gatefire.gatefire_d_certification_authority.codice IS 'Codice C.A.';

COMMENT ON COLUMN gatefire.gatefire_d_certification_authority.ragione_sociale IS 'Ragione sociale C.A.';

COMMENT ON COLUMN gatefire.gatefire_d_certification_authority.url IS 'URL C.A.';

COMMENT ON COLUMN gatefire.gatefire_d_certification_authority.data_inizio_validita IS 'Data di inizio validita della C.A.';

COMMENT ON COLUMN gatefire.gatefire_d_certification_authority.data_fine_validita IS 'Data di fine validita della C.A.';

COMMENT ON COLUMN gatefire.gatefire_d_certification_authority.data_creazione IS 'Data di creazione della C.A.';

COMMENT ON COLUMN gatefire.gatefire_d_certification_authority.data_aggiornamento IS 'Data di aggiornamento della C.A.';

COMMENT ON COLUMN gatefire.gatefire_d_certification_authority.data_cancellazione IS 'Data di cancellazione della C.A.';

/* ---------------------------------------------------------------------- */
/* Add table "gatefire.gatefire_d_parametro"                              */
/* ---------------------------------------------------------------------- */

CREATE TABLE gatefire.gatefire_d_parametro (
    parametro TEXT  NOT NULL,
    descrizione TEXT  NOT NULL,
    tipo_parametro TEXT  NOT NULL,
    uso_parametro TEXT,
    tag_ca TEXT,
    data_inizio_validita TIMESTAMP DEFAULT CURRENT_TIMESTAMP  NOT NULL,
    data_fine_validita TIMESTAMP,
    data_creazione TIMESTAMP DEFAULT CURRENT_TIMESTAMP  NOT NULL,
    data_aggiornamento TIMESTAMP DEFAULT CURRENT_TIMESTAMP  NOT NULL,
    data_cancellazione TIMESTAMP,
    CONSTRAINT gatefire_d_parametro_pk PRIMARY KEY (parametro)
);

COMMENT ON TABLE gatefire.gatefire_d_parametro IS 'Anagrafica dei parametri dell''applicazione';

COMMENT ON COLUMN gatefire.gatefire_d_parametro.parametro IS 'Parametro applicazione';

COMMENT ON COLUMN gatefire.gatefire_d_parametro.descrizione IS 'Descrizione del parametro dell''applicazione';

COMMENT ON COLUMN gatefire.gatefire_d_parametro.data_inizio_validita IS 'Data di inizio validita del parametro dell''applicazione';

COMMENT ON COLUMN gatefire.gatefire_d_parametro.data_fine_validita IS 'Data di fine validita del parametro dell''applicazione';

COMMENT ON COLUMN gatefire.gatefire_d_parametro.data_creazione IS 'Data di creazione del parametro dell''applicazione';

COMMENT ON COLUMN gatefire.gatefire_d_parametro.data_aggiornamento IS 'Data di aggiornamento del parametro dell''applicazione';

COMMENT ON COLUMN gatefire.gatefire_d_parametro.data_cancellazione IS 'Data di cancellazione del parametro dell''applicazione';

/* ---------------------------------------------------------------------- */
/* Add table "gatefire.gatefire_l_evento"                                 */
/* ---------------------------------------------------------------------- */

CREATE TABLE gatefire.gatefire_l_evento (
    id BIGINT  NOT NULL,
    data_inizio_elab TIMESTAMP DEFAULT CURRENT_TIMESTAMP  NOT NULL,
    data_fine_elab TIMESTAMP,
    data_agg_stato TIMESTAMP,
    data_creazione TIMESTAMP DEFAULT CURRENT_TIMESTAMP  NOT NULL,
    id_utente TEXT,
    id_sessione TEXT,
    codice_fiscale TEXT,
    ip_applicazione TEXT  NOT NULL,
    descrizione TEXT,
    nome_file TEXT,
    codice_stato_evento TEXT  NOT NULL,
    codice_certification_authority TEXT,
    codice_applicazione TEXT,
    codice_repository TEXT,
    codice_operazione TEXT  NOT NULL,
    codice_gatefire_d_tipo_evento TEXT  NOT NULL,
    data_cancellazione TIMESTAMP,
    collocazione TEXT,
    CONSTRAINT gatefire_l_evento_pk PRIMARY KEY (id)
);

CREATE INDEX gatefire_l_evento_codice_applicazione_idx ON gatefire.gatefire_l_evento (codice_applicazione);

CREATE INDEX gatefire_l_evento_codice_ca_idx ON gatefire.gatefire_l_evento (codice_certification_authority);

CREATE INDEX gatefire_l_evento_codice_fiscale_idx ON gatefire.gatefire_l_evento (codice_fiscale);

CREATE INDEX gatefire_l_evento_codice_repository_idx ON gatefire.gatefire_l_evento (codice_repository);

CREATE INDEX gatefire_l_evento_data_inizio_elab_idx ON gatefire.gatefire_l_evento (data_inizio_elab);

CREATE INDEX gatefire_l_evento_id_sessione_idx ON gatefire.gatefire_l_evento (id_sessione);

CREATE INDEX gatefire_l_evento_id_utente_idx ON gatefire.gatefire_l_evento (id_utente);

COMMENT ON TABLE gatefire.gatefire_l_evento IS 'Anagrafica eventi';

COMMENT ON COLUMN gatefire.gatefire_l_evento.id IS 'Identificativo univoco evento';

COMMENT ON COLUMN gatefire.gatefire_l_evento.data_inizio_elab IS 'Data di inizio elaborazione evento';

COMMENT ON COLUMN gatefire.gatefire_l_evento.data_fine_elab IS 'Data di fine elaborazione evento';

COMMENT ON COLUMN gatefire.gatefire_l_evento.data_agg_stato IS 'Data di aggiornamento di stato dell''evento';

COMMENT ON COLUMN gatefire.gatefire_l_evento.data_creazione IS 'Data di aggiornamento di stato dell''evento';

COMMENT ON COLUMN gatefire.gatefire_l_evento.id_utente IS 'Identificativo dell''utente che ha richiesto il servizio';

COMMENT ON COLUMN gatefire.gatefire_l_evento.id_sessione IS 'Identificativo della sessione di svolgimento del servizio';

COMMENT ON COLUMN gatefire.gatefire_l_evento.codice_fiscale IS 'Codice fiscale di chi ha richiesto il servizio';

COMMENT ON COLUMN gatefire.gatefire_l_evento.ip_applicazione IS 'IP dell''applicazione che ha richiesto il servizio';

COMMENT ON COLUMN gatefire.gatefire_l_evento.descrizione IS 'Descrizione della richiesta';

COMMENT ON COLUMN gatefire.gatefire_l_evento.nome_file IS 'Nome del file su cui e'' richiesto il servizio';

COMMENT ON COLUMN gatefire.gatefire_l_evento.data_cancellazione IS 'Data di cancellazione dell''evento';

COMMENT ON COLUMN gatefire.gatefire_l_evento.collocazione IS 'Collocazione utente firmatario';

/* ---------------------------------------------------------------------- */
/* Add table "gatefire.gatefire_l_evento_step"                            */
/* ---------------------------------------------------------------------- */

CREATE TABLE gatefire.gatefire_l_evento_step (
    id_evento BIGINT  NOT NULL,
    id BIGINT  NOT NULL,
    data_creazione TIMESTAMP DEFAULT CURRENT_TIMESTAMP  NOT NULL,
    descrizione TEXT,
    return_code TEXT,
    return_code_descrizione TEXT,
    codice_stato_evento_log TEXT  NOT NULL,
    codice_tipo_passo_evento_log TEXT  NOT NULL,
    data_cancellazione TIMESTAMP,
    CONSTRAINT gatefire_l_evento_step_pk PRIMARY KEY (id_evento, id)
);

COMMENT ON TABLE gatefire.gatefire_l_evento_step IS 'Anagrafica degli step degli eventi. Per un evento si possono avere più step associati';

COMMENT ON COLUMN gatefire.gatefire_l_evento_step.id IS 'Identificativo univoco dello step di un evento';

COMMENT ON COLUMN gatefire.gatefire_l_evento_step.data_creazione IS 'Data di creazione dello step di un evento';

COMMENT ON COLUMN gatefire.gatefire_l_evento_step.descrizione IS 'Descrizione dello step di un evento';

COMMENT ON COLUMN gatefire.gatefire_l_evento_step.return_code IS 'Codice di ritorno dello step di un evento';

COMMENT ON COLUMN gatefire.gatefire_l_evento_step.return_code_descrizione IS 'Descrizione del codice di ritorno dello step di un evento';

COMMENT ON COLUMN gatefire.gatefire_l_evento_step.data_cancellazione IS 'Data di cancellazione dello step di un evento';

/* ---------------------------------------------------------------------- */
/* Add table "gatefire.gatefire_r_amministratore_ruolo"                   */
/* ---------------------------------------------------------------------- */

CREATE TABLE gatefire.gatefire_r_amministratore_ruolo (
    id_amministratore INTEGER  NOT NULL,
    codice_ruolo CHARACTER VARYING(40)  NOT NULL,
    data_fine_validita TIMESTAMP,
    data_creazione TIMESTAMP DEFAULT CURRENT_TIMESTAMP  NOT NULL,
    data_aggiornamento TIMESTAMP DEFAULT CURRENT_TIMESTAMP  NOT NULL,
    CONSTRAINT gatefire_r_amministratore_ruolo_pk PRIMARY KEY (id_amministratore, codice_ruolo)
);

/* ---------------------------------------------------------------------- */
/* Add table "gatefire.gatefire_r_evento_evento_padre"                    */
/* ---------------------------------------------------------------------- */

CREATE TABLE gatefire.gatefire_r_evento_evento_padre (
    i_evento BIGINT  NOT NULL,
    id_evento_padre BIGINT  NOT NULL,
    data_creazione TIMESTAMP DEFAULT CURRENT_TIMESTAMP  NOT NULL,
    data_aggiornamento TIMESTAMP DEFAULT CURRENT_TIMESTAMP  NOT NULL,
    data_cancellazione TIMESTAMP,
    CONSTRAINT gatefire_r_evento_evento_padre_pk PRIMARY KEY (i_evento, id_evento_padre)
);

COMMENT ON TABLE gatefire.gatefire_r_evento_evento_padre IS 'Anagrafica delle relazioni tra evento ed evento padre';

COMMENT ON COLUMN gatefire.gatefire_r_evento_evento_padre.data_creazione IS 'Data di creazione della relazione evento - evento padre';

COMMENT ON COLUMN gatefire.gatefire_r_evento_evento_padre.data_aggiornamento IS 'Data di aggiornamento della relazione evento - evento padre';

COMMENT ON COLUMN gatefire.gatefire_r_evento_evento_padre.data_cancellazione IS 'Data di cancellazione della relazione evento - evento padre';

/* ---------------------------------------------------------------------- */
/* Add table "gatefire.gatefire_t_parametro_operativo"                    */
/* ---------------------------------------------------------------------- */

CREATE TABLE gatefire.gatefire_t_parametro_operativo (
    id INTEGER  NOT NULL,
    parametro TEXT  NOT NULL,
    codice_ca TEXT,
    codice_funzione TEXT,
    codice_applicazione TEXT,
    valore TEXT  NOT NULL,
    descrizione TEXT  NOT NULL,
    data_inizio_validita TIMESTAMP DEFAULT CURRENT_TIMESTAMP  NOT NULL,
    data_fine_validita TIMESTAMP,
    data_creazione TIMESTAMP DEFAULT CURRENT_TIMESTAMP  NOT NULL,
    data_aggiornamento TIMESTAMP DEFAULT CURRENT_TIMESTAMP  NOT NULL,
    data_cancellazione TIMESTAMP,
    CONSTRAINT gatefire_t_parametro_operativo_pk PRIMARY KEY (id)
);

CREATE INDEX gatefire_t_parametro_operativo_ca_funzione_applicazione_uk ON gatefire.gatefire_t_parametro_operativo (parametro,codice_ca,codice_funzione,codice_applicazione);

COMMENT ON TABLE gatefire.gatefire_t_parametro_operativo IS 'Anagrafica dei valori dei parametri dell''applicazione. La tabella permette la gestione dei parametri di default per tutta l''applicazione oppure per una combinazione di CA-Applicazione-Funzione (valori non obbligatori)';

COMMENT ON COLUMN gatefire.gatefire_t_parametro_operativo.id IS 'Identificativo univoco del valore del parametro dell''applicazione';

COMMENT ON COLUMN gatefire.gatefire_t_parametro_operativo.valore IS 'Valore del parametro dell''applicazione';

COMMENT ON COLUMN gatefire.gatefire_t_parametro_operativo.descrizione IS 'Descrizione del parametro dell''applicazione';

COMMENT ON COLUMN gatefire.gatefire_t_parametro_operativo.data_inizio_validita IS 'Data di inizio validita del parametro dell''applicazione';

COMMENT ON COLUMN gatefire.gatefire_t_parametro_operativo.data_fine_validita IS 'Data di fine validita del parametro dell''applicazione';

COMMENT ON COLUMN gatefire.gatefire_t_parametro_operativo.data_creazione IS 'Data di creazione del parametro dell''applicazione';

COMMENT ON COLUMN gatefire.gatefire_t_parametro_operativo.data_aggiornamento IS 'Data di aggiornamento del parametro dell''applicazione';

COMMENT ON COLUMN gatefire.gatefire_t_parametro_operativo.data_cancellazione IS 'Data di cancellazione del parametro dell''applicazione';

/* ---------------------------------------------------------------------- */
/* Add table "gatefire.gatefire_d_ca_collocazione_dominio"                */
/* ---------------------------------------------------------------------- */

CREATE TABLE gatefire.gatefire_d_ca_collocazione_dominio (
    codice_ca TEXT  NOT NULL,
    tipo_funzione TEXT  NOT NULL,
    collocazione TEXT  NOT NULL,
    dominio TEXT  NOT NULL,
    configurazione TEXT  NOT NULL,
    data_inizio_validita TIMESTAMP DEFAULT CURRENT_TIMESTAMP  NOT NULL,
    data_fine_validita TIMESTAMP,
    data_creazione TIMESTAMP DEFAULT CURRENT_TIMESTAMP  NOT NULL,
    data_aggiornamento TIMESTAMP DEFAULT CURRENT_TIMESTAMP  NOT NULL,
    data_cancellazione TIMESTAMP,
    CONSTRAINT gatefire_d_ca_collocazione_dominio_pk PRIMARY KEY (codice_ca, tipo_funzione, collocazione, dominio),
    CONSTRAINT gatefire_d_ca_collocazione_dominio_ca_ck CHECK (CHECK ((tipo_funzione = ANY (ARRAY['FIRMA_AUTOMATICA', 'FIRMA_REMOTA']))))
);

COMMENT ON TABLE gatefire.gatefire_d_ca_collocazione_dominio IS 'Tabella di trascodifica tra utente firmatario (collocazione) e dominio';

COMMENT ON COLUMN gatefire.gatefire_d_ca_collocazione_dominio.codice_ca IS 'Codice identificativo della C.A.';

COMMENT ON COLUMN gatefire.gatefire_d_ca_collocazione_dominio.tipo_funzione IS 'Tipo di funzione (es. FIRMA_AUTOMATICA, FIRMA_REMOTA)';

COMMENT ON COLUMN gatefire.gatefire_d_ca_collocazione_dominio.collocazione IS 'Utente firmatario';

COMMENT ON COLUMN gatefire.gatefire_d_ca_collocazione_dominio.dominio IS 'Dominio associato all''''utente firmatario';

COMMENT ON COLUMN gatefire.gatefire_d_ca_collocazione_dominio.configurazione IS 'Stringa di configurazione della coppia utente firmatario - dominio';

COMMENT ON COLUMN gatefire.gatefire_d_ca_collocazione_dominio.data_inizio_validita IS 'Data di inizio validita';

COMMENT ON COLUMN gatefire.gatefire_d_ca_collocazione_dominio.data_fine_validita IS 'Data di fine validita';

COMMENT ON COLUMN gatefire.gatefire_d_ca_collocazione_dominio.data_creazione IS 'Data di creazione';

COMMENT ON COLUMN gatefire.gatefire_d_ca_collocazione_dominio.data_aggiornamento IS 'Data di aggiornamento';

COMMENT ON COLUMN gatefire.gatefire_d_ca_collocazione_dominio.data_cancellazione IS 'Data di cancellazione';

/* ---------------------------------------------------------------------- */
/* Add foreign key constraints                                            */
/* ---------------------------------------------------------------------- */

ALTER TABLE gatefire.gatefire_d_ca_collocazione_dominio ADD CONSTRAINT gatefire_d_ca_collocazione_dominio_ca_fk 
    FOREIGN KEY (codice_ca) REFERENCES gatefire.gatefire_d_certification_authority (codice) ON DELETE RESTRICT ON UPDATE CASCADE;

ALTER TABLE gatefire.gatefire_d_certification_authority ADD CONSTRAINT gatefire_d_tag_ca_fk 
    FOREIGN KEY (tag_ca) REFERENCES gatefire.gatefire_d_tag_ca (nome) ON DELETE RESTRICT ON UPDATE CASCADE;

ALTER TABLE gatefire.gatefire_d_messaggio ADD CONSTRAINT gatefire_d_livello_messaggio_fk 
    FOREIGN KEY (livello) REFERENCES gatefire.gatefire_d_livello_messaggio (tipo) ON DELETE RESTRICT ON UPDATE CASCADE;

ALTER TABLE gatefire.gatefire_d_parametro ADD CONSTRAINT gatefire_d_tipo_parametro_fk 
    FOREIGN KEY (tipo_parametro) REFERENCES gatefire.gatefire_d_tipo_parametro (tipo) ON DELETE RESTRICT ON UPDATE CASCADE;

ALTER TABLE gatefire.gatefire_d_parametro ADD CONSTRAINT gatefire_d_uso_fk 
    FOREIGN KEY (uso_parametro) REFERENCES gatefire.gatefire_d_uso (uso) ON DELETE SET NULL ON UPDATE CASCADE;

ALTER TABLE gatefire.gatefire_l_evento ADD CONSTRAINT gatefire_d_applicazione_fk 
    FOREIGN KEY (codice_applicazione) REFERENCES gatefire.gatefire_d_applicazione (codice) ON DELETE SET NULL ON UPDATE CASCADE;

ALTER TABLE gatefire.gatefire_l_evento ADD CONSTRAINT gatefire_d_certification_authority_fk 
    FOREIGN KEY (codice_certification_authority) REFERENCES gatefire.gatefire_d_certification_authority (codice) ON DELETE SET NULL ON UPDATE CASCADE;

ALTER TABLE gatefire.gatefire_l_evento ADD CONSTRAINT gatefire_d_operazione_evento_fk 
    FOREIGN KEY (codice_operazione) REFERENCES gatefire.gatefire_d_operazione_evento (codice) ON DELETE RESTRICT ON UPDATE CASCADE;

ALTER TABLE gatefire.gatefire_l_evento ADD CONSTRAINT gatefire_d_repository_fk 
    FOREIGN KEY (codice_repository) REFERENCES gatefire.gatefire_d_repository (codice) ON DELETE SET NULL ON UPDATE CASCADE;

ALTER TABLE gatefire.gatefire_l_evento ADD CONSTRAINT gatefire_d_stato_evento_fk 
    FOREIGN KEY (codice_stato_evento) REFERENCES gatefire.gatefire_d_stato_evento (codice) ON DELETE RESTRICT ON UPDATE CASCADE;

ALTER TABLE gatefire.gatefire_l_evento ADD CONSTRAINT gatefire_d_tipo_evento_fk 
    FOREIGN KEY (codice_gatefire_d_tipo_evento) REFERENCES gatefire.gatefire_d_tipo_evento (codice) ON DELETE RESTRICT ON UPDATE CASCADE;

ALTER TABLE gatefire.gatefire_l_evento_step ADD CONSTRAINT gatefire_d_stato_evento_step_fk 
    FOREIGN KEY (codice_stato_evento_log) REFERENCES gatefire.gatefire_d_stato_evento_step (codice) ON DELETE RESTRICT ON UPDATE CASCADE;

ALTER TABLE gatefire.gatefire_l_evento_step ADD CONSTRAINT gatefire_d_tipo_passo_evento_step_fk 
    FOREIGN KEY (codice_tipo_passo_evento_log) REFERENCES gatefire.gatefire_d_tipo_passo_evento_step (codice) ON DELETE RESTRICT ON UPDATE CASCADE;

ALTER TABLE gatefire.gatefire_l_evento_step ADD CONSTRAINT gatefire_l_evento_fk 
    FOREIGN KEY (id_evento) REFERENCES gatefire.gatefire_l_evento (id) ON DELETE RESTRICT ON UPDATE CASCADE;

ALTER TABLE gatefire.gatefire_r_amministratore_ruolo ADD CONSTRAINT gatefire_t_amministratore_fk 
    FOREIGN KEY (id_amministratore) REFERENCES gatefire.gatefire_t_amministratore (id) ON DELETE RESTRICT ON UPDATE CASCADE;

ALTER TABLE gatefire.gatefire_r_amministratore_ruolo ADD CONSTRAINT gatefire_t_ruolo_fk 
    FOREIGN KEY (codice_ruolo) REFERENCES gatefire.gatefire_t_ruolo (codice) ON DELETE RESTRICT ON UPDATE CASCADE;

ALTER TABLE gatefire.gatefire_r_evento_evento_padre ADD CONSTRAINT gatefire_l_evento_fk1 
    FOREIGN KEY (id_evento_padre) REFERENCES gatefire.gatefire_l_evento (id) ON DELETE RESTRICT ON UPDATE CASCADE;

ALTER TABLE gatefire.gatefire_r_funzione_applicazione ADD CONSTRAINT gatefire_d_funzione_fk 
    FOREIGN KEY (codice_funzione) REFERENCES gatefire.gatefire_d_funzione (codice) ON DELETE SET NULL ON UPDATE CASCADE;

ALTER TABLE gatefire.gatefire_t_parametro_operativo ADD CONSTRAINT gatefire_d_parametro_fk 
    FOREIGN KEY (parametro) REFERENCES gatefire.gatefire_d_parametro (parametro) ON DELETE RESTRICT ON UPDATE CASCADE;

/* ---------------------------------------------------------------------- */
/* Add procedures                                                         */
/* ---------------------------------------------------------------------- */

CREATE OR REPLACE FUNCTION gatefire.data_valida (data_da_controllare timestamp without time zone) RETURNS boolean AS
$BODY$
BEGIN

	IF data_da_controllare IS NULL THEN
		RETURN TRUE;
	ELSIF data_da_controllare < current_timestamp THEN
		RETURN FALSE;
	ELSE
		RETURN TRUE;
	END IF;

	RETURN TRUE;

END;
$BODY$
LANGUAGE 'plpgsql' VOLATILE;
