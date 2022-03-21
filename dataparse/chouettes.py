#! /bin/env python

from dbf_light import Dbf
from decimal import Decimal


with Dbf.open('../Suivi_chouettes/Chouettes_Point_Ecoute_2013.dbf') as dbf:

    print('Fields: ', end="")
    print(*[field for field in dbf.fields], sep=", ")

    print(f'Number of rows: {dbf.prolog.records_count}')
    
    dic=[]
    for row in dbf:
        d = {}
        tmp = row._asdict()
        
        # Passage de protocole en boolean
        d['protocole'] = tmp['protocole'] == 'O'
        #d['']
        print(tmp)
        dic.append(d)

    print()
    for row in dic:
        print('\n', row) 
