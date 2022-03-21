#! /bin/env python

from dbf_light import Dbf


def parseDBF(dbf):
    global i
    info = []
    for row in dbf:
        tmp = {}
        dic = row._asdict()

        # Passage de protocole en boolean
        tmp['protocole'] = 1 if dic['protocole'] == 'O' else 0

        # Passage espèce en str complète
        if dic['especes'] == 'E':
            tmp['espece'] = 'Effraie'
        elif dic['especes'] == 'H':
            tmp['espece'] = 'Hulotte'
        elif dic['especes'] == 'C':
            tmp['espece'] = 'Cheveche'
        else:
            tmp['espece'] = 'NULL'

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
        else:
            tmp['typeObs'] = 'NULL'

        # Parsage des coordonnées
        if 'l93_x' in dic:
            tmp['coord_Lambert_X'] = dic['l93_x']
        elif 'x' in dic:
            tmp['coord_Lambert_X'] = dic['x']
        else:
            tmp['coord_Lambert_X'] = 'NULL'

        if 'l93_y' in dic:
            tmp['coord_Lambert_Y'] = dic['l93_y']
        elif 'x' in dic:
            tmp['coord_Lambert_Y'] = dic['y']
        else:
            tmp['coord_Lambert_Y'] = 'NULL'

        # Ajout des autres attributs nécessaires
        tmp['numIndividu'] = str(dic['num_indiv'])  # Forçage de string
        tmp['date'] = dic['periode']
        tmp['idObs'] = i

        print(dic)
        info.append(tmp)
        i += 1

    return info


def parse(files):
    informations = []
    global i
    i = 0
    for file in files:
        with Dbf.open(file) as dbf:
            print('Fields: ', end="")
            print(*[field for field in dbf.fields], sep=", ")
            print(f'Number of rows: {dbf.prolog.records_count}')

            informations.extend(parseDBF(dbf))

    print()
    # Tri et suppression de doublons
    informations = sorted([i for n, i in enumerate(informations) if i not in informations[n + 1:]],
                          key=lambda x: x['idObs'])
    return informations


if __name__ == '__main__':
    # import chouettes
    # chouettes.parse()
    d = parse(('Suivi_chouettes/Chouettes_Point_Ecoute_2019.dbf', 'Suivi_chouettes/Chouettes_Point_Individus_2019.dbf'))
    for row in d:
        print('\n', row)
