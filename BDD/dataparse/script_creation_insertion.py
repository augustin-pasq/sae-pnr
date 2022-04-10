import chouettes

chouette = chouettes.parse((r'C:\Users\emali\OneDrive\Documents\Work\Bac+1\S2\S2.01\sql\SAE-PNR\BDD\dataparse\Suivi_chouettes\Chouettes_Point_Ecoute_2019.dbf', r'C:\Users\emali\OneDrive\Documents\Work\Bac+1\S2\S2.01\sql\SAE-PNR\BDD\dataparse\Suivi_chouettes\Chouettes_Point_Individus_2019.dbf'))

f = open("insert.sql", "w")

f.write("-- Insertions Chouette\n")
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
    a = i+1
    b = dic['protocole']
    c = dic['typeObs']
    d = dic['numIndividu']

    if c != 'NULL':
        c = "\'" + c + "\'"

    if d != 'NULL':
        d = "\'" + d + "\'"

    f.write(f"INSERT INTO Obs_Chouette VALUES({a},{b},{c},{d});\n")
