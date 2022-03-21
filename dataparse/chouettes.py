#! /bin/env python

from decimal import Decimal
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
            tmp['espece'] = 'Effraie'
        elif dic['especes'] == 'H':
            tmp['espece'] = 'Hulotte'
        elif dic['especes'] == 'C':
            tmp['espece'] = 'Cheveche'
        else:
            tmp['especes'] = ''

        # Passage sexe en str complète
        if dic['sexe'] == 1:
            tmp['sexe'] = 'male'
        elif dic['sexe'] == 2:
            tmp['sexe'] = 'femelle'
        else:
            tmp['sexe'] = 'inconnu'

        # Passage de type_cont en str complète
        if dic['type_cont'] == 'V':
            tmp['typeObs'] = 'Visuel'
        elif dic['type_cont'] == 'S':
            tmp['typeObs'] = 'Sonore'
        elif dic['type_cont'] == 'VS':
            tmp['typeObs'] = 'Sonore et Visuel'

        # Ajout des autres attributs nécessaires
        tmp['numIndividu'] = str(dic['id_numero']) # Forçage de string

        print(dic)
        info.append(tmp)

    return info

informations = []
with Dbf.open('Suivi_chouettes/Chouettes_Point_Ecoute_2019.dbf') as dbf:
    print('Fields: ', end="")
    print(*[field for field in dbf.fields], sep=", ")
    print(f'Number of rows: {dbf.prolog.records_count}')

    informations.extend(parseDBF(dbf))

with Dbf.open('Suivi_chouettes/Chouettes_Point_Individus_2019.dbf') as dbf:
    print('Fields: ', end="")
    print(*[field for field in dbf.fields], sep=", ")
    print(f'Number of rows: {dbf.prolog.records_count}')

    informations.extend(parseDBF(dbf))


print()
for row in sorted(informations, key=lambda x: x['numIndividu']):
    print('\n', row)
