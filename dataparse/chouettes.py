#! /bin/env python

from dbf_light import Dbf


def parseDBF(dbf):
    info = []
    for row in dbf:
        tmp = {}
        dic = row._asdict()

        # Passage de protocole en boolean
        tmp['protocole'] = dic['protocole'] == 'O'

        # Passage espèce en str complète
        if dic['especes'] == 'E':
            tmp['especes'] = 'Effraie'
        elif dic['especes'] == 'H':
            tmp['especes'] = 'Hulotte'
        elif dic['especes'] == 'C':
            tmp['especes'] = 'Cheveche'
        else:
            tmp['especes'] = ''

        # Passage sexe en str complète
        if dic['sexe'] == 1:
            tmp['sexe'] = 'male'
        elif dic['sexe'] == 2:
            tmp['sexe'] = 'femelle'
        else:
            tmp['sexe'] = 'inconnu'

        # Ajout des autres attributs nécessaires
        tmp['num_indiv'] = dic['num_indiv']

        print(dic)
        info.append(tmp)

    return info

informations = []
with Dbf.open('../Suivi_chouettes/Chouettes_Point_Ecoute_2019.dbf') as dbf:
    print('Fields: ', end="")
    print(*[field for field in dbf.fields], sep=", ")
    print(f'Number of rows: {dbf.prolog.records_count}')

    informations.extend(parseDBF(dbf))

with Dbf.open('../Suivi_chouettes/Chouettes_Point_Individus_2019.dbf') as dbf:
    print('Fields: ', end="")
    print(*[field for field in dbf.fields], sep=", ")
    print(f'Number of rows: {dbf.prolog.records_count}')

    informations.extend(parseDBF(dbf))


print()
for row in informations:
    print('\n', row)
