from dbfread import DBF

for record in DBF('Suivi_chouettes/Chouettes_Point_Ecoute_2013.dbf'):
    print(record)
