import chouettes

chouette = chouettes.parse(('Suivi_chouettes/Chouettes_Point_Ecoute_2019.dbf', 'Suivi_chouettes/Chouettes_Point_Individus_2019.dbf'))
f = open("insert.sql", "w")

f.write("-- Insertions Chouette\n")
for dic in chouette:

    a = dic['numIndividu']
    b = dic['espece']
    c = dic['sexe']
        
    if a != 'NULL':
        a = "\'" + a + "\'"

    if b != 'NULL':
        b = "\'" + b + "\'"
    
    if c != 'NULL':
        c = "\'" + c + "\'"

    f.write(f"INSERT INTO Chouette VALUES({a},{b},{c});\n")

f.write("\n-- Insertions Obs_Chouette\n")
for dic in chouette:

    a = dic['protocole']
    c = dic['typeObs']
    d = dic['numIndividu']

    if c != 'NULL':
        c = "\'" + c + "\'"

    if d != 'NULL':
        d = "\'" + d + "\'"

    f.write(f"INSERT INTO Obs_Chouette VALUES({a},{c},{d});\n")

