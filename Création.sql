-- premier

DROP TABLE Obs_Chouette; -- x1 dépendant / Chouette /
DROP TABLE Chouette; -- indépendant

DROP TABLE Obs_GCI; -- x1 dépendant / Nid_GCI /
DROP TABLE Nid_GCI; -- indépendant

DROP TABLE Obs_Hippocampe; -- indépendant
DROP TABLE Obs_Loutre; -- indépendant

DROP TABLE Obs_Batracien; -- x2 dépendant / Végétation, ZoneHumide /
DROP TABLE Végétation; -- indépendant
DROP TABLE ZoneHumide; -- indépendant

DROP TABLE QuiObserve; -- x2 dépendant / Observation, Observateur /
DROP TABLE Observateur; -- indépendant
DROP TABLE Observation; -- x1 dépendant / Lieu /
DROP TABLE Lieu; -- indépendant
-- dernier

-- Creation de tables

CREATE TABLE Lieu (

    coord_Lambert_X NUMBER,
    coord_Lambert_Y NUMBER,
        CONSTRAINT pk_Lieu PRIMARY KEY (coord_Lambert_X, coord_Lambert_Y)

    -- attribut derivé /coord_GPS_X : double
    -- attribut derivé /coord_GPS_Y : double

);

CREATE TABLE Observation (

    idObs NUMBER
        CONSTRAINT pk_Observation PRIMARY KEY,
    dateObs DATE,
    heureObs : TIME,
    laCoord_Lambert_X NUMBER
        CONSTRAINT fk_Observation_Lieu_L93X REFERENCES Lieu(coord_Lambert_X),
    laCoord_Lambert_Y NUMBER
        CONSTRAINT fk_Observation_Lieu_L93Y REFERENCES Lieu(coord_Lambert_Y)

);

CREATE TABLE Observateur (

    idObservateur NUMBER
        CONSTRAINT pk_Observateur PRIMARY KEY,
    nom VARCHAR(50),
    prenom VARCHAR(50)

);

CREATE TABLE QuiObserve (

    unIdObs NUMBER
        CONSTRAINT fk_QuiObserve_Observation REFERENCES Observation(idObs),
    unIdObservateur NUMBER
        CONSTRAINT fk_QuiObserve_Observateur REFERENCES Observateur(idObservateur),
    CONSTRAINT pk_QuiObserve PRIMARY KEY (unIdObs, unIdObservateur)

);

CREATE TABLE Obs_Batracien (

    idObsBatracien NUMBER
        CONSTRAINT pk_Obs_Batracien PRIMARY KEY
        CONSTRAINT fk_Obs_Batracien_Observation REFERENCES Observation(idObs),
    espece VARCHAR(20)
        CONSTRAINT ck_Obs_Batracien_espece 
            CHECK espece IN ('calamite','pelodyte'),
    nombreAdultes NUMBER,
    nombreAmplexus NUMBER,
    nombrePonte NUMBER,
    nombreTetard NUMBER,
    temperature NUMBER,
    meteo_ciel VARCHAR(13)
        CONSTRAINT ck_Obs_Batracien_meteo_ciel 
            CHECK meteo_ciel IN ('dégagé','semi-dégagé','nuageux'),
    meteo_temp VARCHAR(5),
        CONSTRAINT ck_Obs_Batracien_meteo_temp 
            CHECK meteo_temp IN ('froid','moyen','chaud'),
    meteo_vent VARCHAR(20)
        CONSTRAINT ck_Obs_Batracien_meteo_vent 
            CHECK meteo_vent IN ('non','léger','moyen','fort'),
    meteo_pluie VARCHAR(7)
        CONSTRAINT ck_Obs_Batracien_meteo_pluie 
            CHECK meteo_pluie IN ('non','léger','moyenne','forte'),
    uneVege_id NUMBER
        CONSTRAINT fk_Obs_Batracien_Végétation REFERENCES Végétation(vege_id)
        CONSTRAINT nn_uneVege_id NOT NULL,
    uneZh_id NUMBER
        CONSTRAINT fk_Obs_Batracien_ZoneHumide(zh_id)
        CONSTRAINT nn_uneZh_id NOT NULL

);

CREATE TABLE Végétation (

    vege_id NUMBER
        CONSTRAINT pk_Végétation PRIMARY KEY,
    vege_environ VARCHAR(50),
    vege_bordure VARCHAR(50),
    vege_ripisyles VARCHAR(50)

);

CREATE TABLE ZoneHumide (

    zh_id NUMBER
        CONSTRAINT pk_ZoneHumide PRIMARY KEY,
    zh_temporaire BOOLEAN,
    zh_profondeur NUMBER,
    zh_surface NUMBER,
    zh_typeMare VARCHAR(7)
        CONSTRAINT ck_ZoneHumide_zh_typeMare 
            CHECK zh_typeMare IN ('prairie','étang','marais','mare'),
    zh_pente VARCHAR(7)
        CONSTRAINT ck_ZoneHumide_zh_pente 
            CHECK zh_pente IN ('raide','abrupte','douce'),
    zh_ouverture VARCHAR(20)
        CONSTRAINT ck_ZoneHumide_zh_ouverture 
            CHECK zh_ouverture IN ('abritée','semi-abritée','ouverte')

);

CREATE TABLE Obs_Loutre (

    idObsLoutre NUMBER
        CONSTRAINT pk_Obs_Loutre PRIMARY KEY
        CONSTRAINT fk_Obs_Loutre_Observation REFERENCES Observation(idObs),
    commune VARCHAR(50),
    lieuDit VARCHAR(50),
    indice VARCHAR(20)
        CONSTRAINT ck_Obs_Loutre_indice 
            CHECK indice IN ('positif','négatif','non prospection')

);

CREATE TABLE Obs_Hippocampe (

    idObsHippocampe NUMBER
        CONSTRAINT pk_Obs_Hippocampe PRIMARY KEY
        CONSTRAINT fk_Obs_Hippocampe_Observation REFERENCES Observation(idObs),
    espece VARCHAR(30)
        CONSTRAINT ck_Obs_Hippocampe_espece 
            CHECK espece IN ('Syngnathus acus','Hippocampus guttulatus','Hippocampus hippocampus','Entelurus aequoreus'),
    sexe VARCHAR(7)
        CONSTRAINT ck_Obs_Hippocampe_sexe 
            CHECK sexe IN ('mâle','femelle','inconnu'),
    temperatureEau NUMBER,
    typePeche VARCHAR(20) 
        CONSTRAINT ck_Obs_Hippocampe_typePEche 
            CHECK typePeche IN ('casierCrevette','casierMorgates','petitFilet','verveuxAnguilles'),
    taille NUMBER,
    gestant BOOLEAN

);

CREATE TABLE  Obs_GCI (

    idObsGCI NUMBER
        CONSTRAINT pk_Obs_GCI PRIMARY KEY
        CONSTRAINT fk_Obs_GCI_Observation REFERENCES Observation(idObs),
    nature VARCHAR(7)
        CONSTRAINT ck_Obs_CGI_nature 
            CHECK nature IN ('oeuf','poussin','nid');
    nombre NUMBER,
    presentsMaisNonObs BOOLEAN,
    unIdNid : int REF Nid_CGI(idNid) (NN)
        CONSTRAINT fk_Obs_GCI_unIdNid REFERENCES Nid_GCI(idNid)
        CONSTRAINT nn_Obs_GCI_unIdNod NOT NULL

);

CREATE TABLE Nid_CGI (

    idNid NUMBER
        CONSTRAINT pk_Nid_GCI PRIMARY KEY,
    nomPlage VARCHAR(50),
    raisonArretObservation VARCHAR(12)
        CONSTRAINT ck_Nid_GCI_raisonArretObservation 
            CHECK raisonArretObservation IN ('envol','inconnu','maree','piétinement','prédation'),
    nbEnvol NUMBER,
    protection BOOLEAN,
    bagueMale VARCHAR(50),
    bagueFemelle VARCHAR(50)

    -- attribut dérivé /dateDecouverte : date
    -- attribut dérivé /dateArretObservation : date

);

CREATE TABLE Obs_Chouette (

    idObsChouette NUMBER
        CONSTRAINT pk_Obs_Chouette PRIMARY KEY
        CONSTRAINT fk_Obs_Chouette_Observation REFERENCES Observation(idObs),
    protocole BOOLEAN,
    typeObs VARCHAR(20)
        CONSTRAINT ck_Obs_Chouette_typeObs 
            CHECK typeObs IN ('Sonore','Visuel','Sonore et Visuel'),
    unNumIndividu NUMBER
        CONSTRAINT fk_ObsChouette_unNumIndividu REFERENCES Chouette(numIndividu)
        CONSTRAINT nn_Obs_Chouette_unNumIndividu NOT NULL

);

CREATE TABLE Chouette (

    numIndividu NUMBER 
        CONSTRAINT pk_Chouette PRIMARY KEY,
    espece VARCHAR(8)
        ck_Chouette_espece
            CHECK espece IN ('Effraie','Cheveche','Hulotte'),
    sexe VARCHAR(7)
        ck_Chouette_sexe 
            CHECK sexe IN ('mâle','femelle','inconnu')  

);
