CREATE OR REPLACE VIEW vue_allFromChouette
AS
SELECT Obs_Chouette.*, espece, sexe, dateObs, heureObs, lieu_Lambert_X, lieu_Lambert_Y
FROM Obs_Chouette
         JOIN Chouette ON leNumIndividu = numIndividu
         JOIN Observation ON numObs = idObs
;

CREATE OR REPLACE VIEW vue_allFromGCI
AS
SELECT Obs_GCI.*,
       nomPlage,
       raisonArretObservation,
       nbEnvol,
       protection,
       bagueMale,
       bagueFemelle,
       dateObs,
       heureObs,
       lieu_Lambert_X,
       lieu_Lambert_Y
FROM Obs_GCI
         JOIN Nid_GCI ON leNid = idNid
         JOIN Observation ON obsG = idObs
;

CREATE OR REPLACE VIEW vue_allFromBatracien
AS
SELECT Obs_Batracien.*,
       zh_temporaire,
       zh_profondeur,
       zh_surface,
       zh_typeMare,
       zh_pente,
       zh_ouverture,
       natureVege,
       vegetation,
       decrit_LieuVege,
       dateObs,
       heureObs,
       lieu_Lambert_X,
       lieu_Lambert_Y
FROM Obs_Batracien
         JOIN ZoneHumide ON concerne_ZH = zh_id
         JOIN Vegetation ON concernes_vege = idVege
         JOIN Observation on obsB = idObs
;

CREATE OR REPLACE VIEW vue_allFromHippocampe
AS
SELECT Obs_Hippocampe.*, dateObs, heureObs, lieu_Lambert_X, lieu_Lambert_Y
FROM Obs_Hippocampe
         JOIN Observation ON obsH = idObs
;

CREATE OR REPLACE VIEW vue_allFromLoutre
AS
SELECT Obs_Loutre.*, dateObs, heureObs, lieu_Lambert_X, lieu_Lambert_Y
FROM Obs_Loutre
         JOIN Observation ON obsL = idObs
;