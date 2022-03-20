-- S2.01 - Groupe 1D1

-- LE NY, Liam
-- MALIVET, Ervan
-- OZANNE, Colin
-- PASQUIER, Augustin
-- TABOR, Samuel

-- Supression de tables

DROP TABLE Obs_Chouette; -- x1 dépendant / Chouette /
DROP TABLE Chouette; -- indépendant

DROP TABLE Obs_GCI; -- x1 dépendant / Nid_GCI /
DROP TABLE Nid_GCI; -- indépendant

DROP TABLE Obs_Hippocampe; -- indépendant
DROP TABLE Obs_Loutre; -- indépendant

DROP TABLE Obs_Batracien; -- x2 dépendant / Végétation, ZoneHumide /
DROP TABLE Vegetation; -- indépendant
DROP TABLE ZoneHumide; -- indépendant

DROP TABLE QuiObserve; -- x2 dépendant / Observation, Observateur /
DROP TABLE Observateur; -- indépendant
DROP TABLE Observation; -- x1 dépendant / Lieu /
DROP TABLE Lieu; -- indépendant

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
    heureOBS TIMESTAMP,
    laCoord_Lambert_X NUMBER,
    laCoord_Lambert_Y NUMBER,
    CONSTRAINT fk_Observation_Lieu 
        FOREIGN KEY (laCoord_Lambert_X, laCoord_Lambert_Y)
            REFERENCES Lieu(coord_Lambert_X, coord_Lambert_Y)

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

CREATE TABLE Vegetation (

    vege_id NUMBER
        CONSTRAINT pk_Vegetation PRIMARY KEY,
    vege_environ VARCHAR(50),
    vege_bordure VARCHAR(50),
    vege_ripisyles VARCHAR(50)

);

CREATE TABLE ZoneHumide (

    zh_id NUMBER
        CONSTRAINT pk_ZoneHumide PRIMARY KEY,
    zh_temporaire NUMBER
        CONSTRAINT ck_ZoneHumide_zh_temporaire
            CHECK (zh_temporaire IN (0, 1)),
    zh_profondeur NUMBER,
    zh_surface NUMBER,
    zh_typeMare VARCHAR(10)
        CONSTRAINT ck_ZoneHumide_zh_typeMare
            CHECK (zh_typeMare IN ('prairie','etang','marais','mare')),
    zh_pente VARCHAR(10)
        CONSTRAINT ck_ZoneHumide_zh_pente 
            CHECK (zh_pente IN ('raide','abrupte','douce')),
    zh_ouverture VARCHAR(20)
        CONSTRAINT ck_ZoneHumide_zh_ouverture 
            CHECK (zh_ouverture IN ('abritee','semi-abritee','ouverte'))

);

CREATE TABLE Obs_Batracien (

    idObsBatracien NUMBER
        CONSTRAINT pk_Obs_Batracien PRIMARY KEY
        CONSTRAINT fk_Obs_Batracien_Observation REFERENCES Observation(idObs),
    espece VARCHAR(20)
        CONSTRAINT ck_Obs_Batracien_espece 
            CHECK (espece IN ('calamite','pelodyte')),
    nombreAdultes NUMBER,
    nombreAmplexus NUMBER,
    nombrePonte NUMBER,
    nombreTetard NUMBER,
    temperature NUMBER,
    meteo_ciel VARCHAR(15)
        CONSTRAINT ck_Obs_Batracien_meteo_ciel 
            CHECK (meteo_ciel IN ('degage','semi-degage','nuageux')),
    meteo_temp VARCHAR(10),
        CONSTRAINT ck_Obs_Batracien_meteo_temp 
            CHECK (meteo_temp IN ('froid','moyen','chaud')),
    meteo_vent VARCHAR(20)
        CONSTRAINT ck_Obs_Batracien_meteo_vent 
            CHECK (meteo_vent IN ('non','leger','moyen','fort')),
    meteo_pluie VARCHAR(10)
        CONSTRAINT ck_Obs_Batracien_meteo_pluie 
            CHECK (meteo_pluie IN ('non','leger','moyenne','forte')),
    uneVege_id NUMBER
        CONSTRAINT fk_Obs_Batracien_Vegetation REFERENCES Vegetation(vege_id)
        CONSTRAINT nn_uneVege_id NOT NULL,
    uneZh_id NUMBER
        CONSTRAINT fk_Obs_Batracien_ZoneHumide REFERENCES ZoneHumide(zh_id)
        CONSTRAINT nn_uneZh_id NOT NULL

);

CREATE TABLE Obs_Loutre (

    idObsLoutre NUMBER
        CONSTRAINT pk_Obs_Loutre PRIMARY KEY
        CONSTRAINT fk_Obs_Loutre_Observation REFERENCES Observation(idObs),
    commune VARCHAR(50),
    lieuDit VARCHAR(50),
    indice VARCHAR(20)
        CONSTRAINT ck_Obs_Loutre_indice 
            CHECK (indice IN ('positif','negatif','non prospection'))

);

CREATE TABLE Obs_Hippocampe (

    idObsHippocampe NUMBER
        CONSTRAINT pk_Obs_Hippocampe PRIMARY KEY
        CONSTRAINT fk_Obs_Hippocampe_Observation REFERENCES Observation(idObs),
    espece VARCHAR(30)
        CONSTRAINT ck_Obs_Hippocampe_espece 
            CHECK (espece IN ('Syngnathus acus','Hippocampus guttulatus','Hippocampus hippocampus','Entelurus aequoreus')),
    sexe VARCHAR(10)
        CONSTRAINT ck_Obs_Hippocampe_sexe 
            CHECK (sexe IN ('mâle','femelle','inconnu')),
    temperatureEau NUMBER,
    typePeche VARCHAR(20) 
        CONSTRAINT ck_Obs_Hippocampe_typePEche 
            CHECK (typePeche IN ('casierCrevette','casierMorgates','petitFilet','verveuxAnguilles')),
    taille NUMBER,
    gestant NUMBER
        CONSTRAINT ck_Obs_Hippocampe_gestant
            CHECK (gestant IN (0, 1))

);

CREATE TABLE Nid_GCI (

    idNid NUMBER
        CONSTRAINT pk_Nid_GCI PRIMARY KEY,
    nomPlage VARCHAR(50),
    raisonArretObservation VARCHAR(15)
        CONSTRAINT ck_Nid_GCI_raisonArretObs 
            CHECK (raisonArretObservation IN ('envol','inconnu','maree','pietinement','predation')),
    nbEnvol NUMBER,
    protection NUMBER
        CONSTRAINT ck_Nid_GCI_protection
            CHECK (protection IN (0, 1)),
    bagueMale VARCHAR(50),
    bagueFemelle VARCHAR(50)

    -- attribut dérivé /dateDecouverte : date
    -- attribut dérivé /dateArretObservation : date

);

CREATE TABLE  Obs_GCI (

    idObsGCI NUMBER
        CONSTRAINT pk_Obs_GCI PRIMARY KEY
        CONSTRAINT fk_Obs_GCI_Observation REFERENCES Observation(idObs),
    nature VARCHAR(10)
        CONSTRAINT ck_Obs_GCI_nature 
            CHECK (nature IN ('oeuf','poussin','nid')),
    nombre NUMBER,
    presentMaisNonObs NUMBER
        CONSTRAINT ck_Obs_GCI_presentMaisNonObs
             CHECK (presentMaisNonObs IN (0, 1)),
    unIdNid NUMBER
        CONSTRAINT fk_Obs_GCI_unIdNid REFERENCES Nid_GCI(idNid)
        CONSTRAINT nn_Obs_GCI_unIdNod NOT NULL

);

CREATE TABLE Chouette (

    numIndividu NUMBER 
        CONSTRAINT pk_Chouette PRIMARY KEY,
    espece VARCHAR(10)
        CONSTRAINT ck_Chouette_espece
            CHECK (espece IN ('Effraie','Cheveche','Hulotte')),
    sexe VARCHAR(10)
        CONSTRAINT ck_Chouette_sexe 
            CHECK (sexe IN ('mâle','femelle','inconnu'))

);

CREATE TABLE Obs_Chouette (

    idObsChouette NUMBER
        CONSTRAINT pk_Obs_Chouette PRIMARY KEY
        CONSTRAINT fk_Obs_Chouette_Observation REFERENCES Observation(idObs),
    protocole NUMBER
        CONSTRAINT ck_Obs_Chouette_protocole
            CHECK (protocole IN (0, 1)),
    typeObs VARCHAR(20)
        CONSTRAINT ck_Obs_Chouette_typeObs 
            CHECK (typeObs IN ('Sonore','Visuel','Sonore et Visuel')),
    unNumIndividu NUMBER
        CONSTRAINT fk_ObsChouette_unNumIndividu REFERENCES Chouette(numIndividu)
        CONSTRAINT nn_Obs_Chouette_unNumIndividu NOT NULL

);
