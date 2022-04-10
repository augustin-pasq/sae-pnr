import chouettes

chouette = chouettes.parse(
    (r'Suivi_chouettes/Chouettes_Point_Ecoute_2019.dbf', r'Suivi_chouettes/Chouettes_Point_Individus_2019.dbf'))

f = open("insertChouette.sql", "w")

f.write("-- Insertions Lieu\n")
for i in range(len(chouette[0])):
    dic = chouette[0][i]
    d = dic['coord_Lambert_X']
    e = dic['coord_Lambert_Y']
    if d == 0:
        d = 'NULL'
    if e == 0:
        e = 'NULL'
    f.write(f"INSERT INTO Lieu VALUES({d},{e});\n")

f.write("\n-- Insertions Observation\n")
for i in range(len(chouette[0])):
    dic = chouette[0][i]
    a = i + 1
    b = "TO_DATE(\'01/01/" + str(dic['date']) + "\', \'dd/mm/yyyy')" if dic['date'] != 0 else "NULL"
    c = 'NULL'
    d = dic['coord_Lambert_X']
    e = dic['coord_Lambert_Y']
    if b == 0:
        b = 'NULL'
    if d == 0:
        d = 'NULL'
    if e == 0:
        e = 'NULL'
    f.write(f"INSERT INTO Observation VALUES({a},{b},{c},{d},{e});\n")

f.write("\n-- Insertions Chouette\n")
for dic in chouette[0]:
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
for i in range(len(chouette[0])):
    dic = chouette[0][i]
    a = i + 1
    b = dic['protocole']
    c = dic['typeObs']
    d = dic['numIndividu']
    if c != 'NULL':
        c = "\'" + c + "\'"
    if d != 'NULL':
        d = "\'" + d + "\'"
    f.write(f"INSERT INTO Obs_Chouette VALUES({a},{b},{c},{d});\n")


f.write("\n-- Insertions QuiObserveChouette\n")
for i in range(len(chouette[0])):
    dic = chouette[0][i]
    a = dic['idObs']
    b = dic['observateur']
    if a != 'NULL' and b != 'NULL':
        f.write(f"INSERT INTO QuiObserveChouette VALUES({a},{b});\n")
