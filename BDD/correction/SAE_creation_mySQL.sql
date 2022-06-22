/*
Script de création de table

Basé sur le travail de :

NOEL Andy
LE NOE Hugo
WADOUX Félix
AUGER Matéo
GUILLOME Benjamin

Modifs de LNAERT
*/
DROP TABLE IF EXISTS Obs_Batracien;
DROP TABLE IF EXISTS ZoneHumide;
DROP TABLE IF EXISTS Vegetation;
DROP TABLE IF EXISTS Lieu_Vegetation;
DROP TABLE IF EXISTS Obs_Hippocampe;
DROP TABLE IF EXISTS Obs_GCI;
DROP TABLE IF EXISTS Nid_GCI;
DROP TABLE IF EXISTS Obs_Chouette;
DROP TABLE IF EXISTS Chouette;
DROP TABLE IF EXISTS Obs_Loutre;
DROP TABLE IF EXISTS AObserve;
DROP TABLE IF EXISTS Observation;
DROP TABLE IF EXISTS Observateur;
DROP TABLE IF EXISTS Lieu;


CREATE TABLE Lieu
(
    coord_Lambert_X DECIMAL(18, 8),
    coord_Lambert_Y DECIMAL(18, 8),
    CONSTRAINT pk_Lieu PRIMARY KEY (coord_Lambert_X, coord_Lambert_Y)
);

/*
Ajouter les coordonnées GPS
CREATE OR REPLACE VIEW vue_Lieu AS
SELECT coord_Lambert_X, coord_Lambert_Y,
... conversion Lambert93 à WGS84
*/


CREATE TABLE Observateur
(
    idObservateur INTEGER,
    nom           VARCHAR(50),
    prenom        VARCHAR(50),

    CONSTRAINT pk_Observateur PRIMARY KEY (idObservateur)
);

CREATE TABLE Observation
(
    idObs          INTEGER,
    dateObs        DATE,
    heureObs       TIME,
    lieu_Lambert_X DECIMAL(18, 8),
    lieu_Lambert_Y DECIMAL(18, 8),

    CONSTRAINT pk_Observation PRIMARY KEY (idObs),
    CONSTRAINT fk_Observation_Lieu FOREIGN KEY (lieu_Lambert_X, lieu_Lambert_Y) REFERENCES Lieu (coord_Lambert_X, coord_Lambert_Y)
);

CREATE TABLE AObserve
(
    lobservateur INTEGER,
    lobservation INTEGER,

    CONSTRAINT fk_AObserver_Observateur FOREIGN KEY (lobservateur) REFERENCES Observateur (idObservateur),
    CONSTRAINT fk_AObserver_Observation FOREIGN KEY (lobservation) REFERENCES Observation (idObs),
    CONSTRAINT pk_AObserve PRIMARY KEY (lobservateur, lobservation)
);

CREATE TABLE Obs_Loutre
(
    ObsL    INTEGER,
    commune VARCHAR(50),
    lieuDit VARCHAR(50),
    indice  VARCHAR(50),

    CONSTRAINT fk_ObsLoutre_Observation FOREIGN KEY (ObsL) REFERENCES Observation (idObs),
    CONSTRAINT pk_ObsLoutre PRIMARY KEY (ObsL),
    CONSTRAINT dom_indice CHECK (indice in ('Positif', 'Negatif', 'Non prospection'))
);

CREATE TABLE Chouette
(
    numIndividu VARCHAR(50),
    espece      VARCHAR(50),
    sexe        VARCHAR(50) NOT NULL,

    CONSTRAINT pk_Chouette PRIMARY KEY (numIndividu),
    CONSTRAINT ck_domEspeceChouette CHECK (UPPER(espece) IN ('EFFRAIE', 'CHEVECHE', 'HULOTTE')),
    CONSTRAINT ck_domSexeChouette CHECK (UPPER(sexe) IN ('MALE', 'FEMELLE', 'INCONNU'))
);

CREATE TABLE Obs_Chouette
(
    protocole     INTEGER,
    typeObs       VARCHAR(50),
    leNumIndividu VARCHAR(50) NOT NULL,
    numObs        INTEGER,

    CONSTRAINT ck_booleanprotocole CHECK (protocole IN (0, 1)),
    CONSTRAINT ck_domTypeObs CHECK (UPPER(typeObs) IN ('SONORE', 'VISUEL', 'SONORE ET VISUEL')),
    CONSTRAINT fk_concerneChouette FOREIGN KEY (leNumIndividu) REFERENCES Chouette (numIndividu),
    CONSTRAINT fk_observationC FOREIGN KEY (numObs) REFERENCES Observation (idObs)
);

CREATE TABLE Nid_GCI
(
    idNid                  INTEGER,
    nomPlage               VARCHAR(50),
    raisonArretObservation VARCHAR(50),
    nbEnvol                INTEGER,
    protection             INTEGER(1),
    bagueMale              VARCHAR(50),
    bagueFemelle           VARCHAR(50),

    CONSTRAINT pk_NidGCI PRIMARY KEY (idNid),
    CONSTRAINT dom_raisonArretObservation CHECK (raisonArretObservation IN
                                                 ('Envol', 'Inconnu', 'Maree', 'Pietinement', 'Prédation'))
);

CREATE TABLE Obs_GCI
(
    obsG              INTEGER,
    nature            VARCHAR(50),
    nombre            INTEGER,
    presentMaisNonObs INTEGER(1),
    leNid             INTEGER,

    CONSTRAINT dom_nature CHECK (nature in ('Oeuf', 'Poussin', 'Nid')),
    CONSTRAINT fk_ObsGCI_Observation FOREIGN KEY (obsG) REFERENCES Observation (idObs),
    CONSTRAINT pk_ObsGCI PRIMARY KEY (obsG),
    CONSTRAINT fk_obsGCI_NidGCI FOREIGN KEY (leNid) REFERENCES Nid_GCI (idNid)
);


/*CREATE OR REPLACE VIEW vue_dateDecouverte
as SELECT Obs1.dateObs
    FROM Nid_GCI, Obs_GCI, Observation Obs1, Observation Obs2
    WHERE (idNid = leNid)
    AND (Obs1.dateObs <= Obs2.dateObs)
    AND (Obs1.idObs = ObsG)
    AND (Obs2.idObs = ObsG)
    GROUP BY Obs1.dateObs
;

CREATE OR REPLACE VIEW vue_dateArretObservation
as SELECT Obs1.dateObs
    FROM Nid_GCI, Obs_GCI, Observation Obs1, Observation Obs2
    WHERE (idNid = leNid)
    AND (Obs1.dateObs >= Obs2.dateObs)
    AND (Obs1.idObs = ObsG)
    AND (Obs2.idObs = ObsG)
    GROUP BY Obs1.dateObs
;*/


CREATE TABLE Obs_Hippocampe
(
    obsH           INTEGER,
    espece         VARCHAR(50),
    sexe           VARCHAR(50),
    temperatureEau INTEGER,
    typePeche      VARCHAR(20),
    taille         DECIMAL,
    gestant        INTEGER(1),

    CONSTRAINT dom_especeHippocampe CHECK (espece IN
                                           ('Syngnathus acus', 'Hippocampus guttulatus', 'Hippocampus Hippocampus',
                                            'Entelurus aequoreus')),
    CONSTRAINT dom_sexeHippocampe CHECK (sexe IN ('Male', 'Femelle', 'Inconnu')),
    CONSTRAINT dom_typePeche CHECK (typePeche IN
                                    ('casierCrevettes', 'casierMorgates', 'PetitFilet', 'verveuxAnguilles')),
    CONSTRAINT fk_ObsHippocampe_Observation FOREIGN KEY (obsH) REFERENCES Observation (idObs),
    CONSTRAINT pk_ObsHippocampe PRIMARY KEY (obsH)
);

CREATE TABLE Lieu_Vegetation
(
    idVegeLieu INTEGER,
    CONSTRAINT pk_Lieu_Vegetation PRIMARY KEY (idVegeLieu)
);


CREATE TABLE Vegetation
(
    idVege          INTEGER,
    natureVege      VARCHAR(20),
    vegetation      VARCHAR(70),
    decrit_LieuVege INTEGER,
    CONSTRAINT dom_natureVege CHECK ( natureVege IN ('environnement', 'bordure', 'ripisyle') ),
    CONSTRAINT pk_vegetation PRIMARY KEY (idVege),
    CONSTRAINT fk_Vegetation_LieuVegetation FOREIGN KEY (decrit_LieuVege) REFERENCES Lieu_Vegetation (idVegeLieu)
);


CREATE TABLE ZoneHumide
(
    zh_id         INTEGER,
    zh_temporaire INTEGER(1),
    zh_profondeur DECIMAL,
    zh_surface    DECIMAL,
    zh_typeMare   VARCHAR(50),
    zh_pente      VARCHAR(50),
    zh_ouverture  VARCHAR(50),

    CONSTRAINT dom_zhPente CHECK (zh_pente IN ('Raide', 'Abrupte', 'Douce')),
    CONSTRAINT dom_zhOuverture CHECK (zh_ouverture IN ('Abritee', 'Semi-Abritee', 'Ouverte')),
    CONSTRAINT dom_zhTypeMare CHECK (zh_typeMare IN ('Prairie', 'Etang', 'Marais', 'Mare')),
    CONSTRAINT pk_ZoneHumide PRIMARY KEY (zh_id)
);

CREATE TABLE Obs_Batracien
(
    obsB           INTEGER,
    espece         VARCHAR(50),
    nombreAdultes  INTEGER,
    nombreAmplexus INTEGER,
    nombrePonte    INTEGER,
    nombreTetard   INTEGER,
    temperature    DECIMAL,
    meteo_ciel     VARCHAR(50),
    meteo_temp     VARCHAR(50),
    meteo_vent     VARCHAR(50),
    meteo_pluie    VARCHAR(50),
    concerne_ZH    INTEGER,
    concernes_vege INTEGER,

    CONSTRAINT dom_meteoCiel CHECK (meteo_ciel IN ('dégagé', 'semi-dégagé', 'nuageux')),
    CONSTRAINT dom_meteoTemp CHECK (meteo_temp IN ('froid', 'moyen', 'chaud')),
    CONSTRAINT dom_meteoVent CHECK (meteo_vent IN ('non', 'léger', 'moyen', 'fort')),
    CONSTRAINT dom_meteoPluie CHECK (meteo_pluie IN ('non', 'légère', 'moyenne', 'forte')),
    CONSTRAINT fk_ObsBatracien_ZoneHumide FOREIGN KEY (concerne_ZH) REFERENCES ZoneHumide (zh_id),
    CONSTRAINT fk_ObsBatracien_Vegetation FOREIGN KEY (concernes_vege) REFERENCES Vegetation (idVege),
    CONSTRAINT dom_especeBatracien CHECK (espece IN ('calamite', 'pelodyte')),
    CONSTRAINT fk_ObsBatracien_Observation FOREIGN KEY (obsB) REFERENCES Observation (idObs),
    CONSTRAINT pk_ObsBatracien PRIMARY KEY (obsB, espece)
);

