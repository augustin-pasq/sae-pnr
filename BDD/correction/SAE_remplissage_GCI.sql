
-- Basé sur le travail du groupe 3C1, modifs de LNAERT

DELETE FROM Obs_GCI;
DELETE FROM Nid_GCI;


-- GCI SuiviNiD 2020 + GCI SuiviNid 2013-2020 (juste la partie 2020)
-- Mesure GCI
INSERT INTO Nid_GCI (idNid, nomPlage, raisonArretObservation, nbEnvol, protection, bagueMale, bagueFemelle) VALUES(20, 'PENVINS', 'inconnu', 0, 0, null, 'FBV/MtW#');
INSERT INTO Nid_GCI (idNid, nomPlage, raisonArretObservation, nbEnvol, protection, bagueMale, bagueFemelle) VALUES(8, 'PENVINS', 'inconnu', 0, 0, null, 'FBV/MtWh#');
INSERT INTO Nid_GCI (idNid, nomPlage, raisonArretObservation, nbEnvol, protection, bagueMale, bagueFemelle) VALUES(19, 'PENVINS', 'inconnu', 0, 0, null, null);
INSERT INTO Nid_GCI (idNid, nomPlage, raisonArretObservation, nbEnvol, protection, bagueMale, bagueFemelle) VALUES(18, 'PENVINS', null, 2, 1, null, null);
INSERT INTO Nid_GCI (idNid, nomPlage, raisonArretObservation, nbEnvol, protection, bagueMale, bagueFemelle) VALUES(10, 'LANDREZAC', 'inconnu', 0, 0, null, null);
INSERT INTO Nid_GCI (idNid, nomPlage, raisonArretObservation, nbEnvol, protection, bagueMale, bagueFemelle) VALUES(9, 'LANDREZAC', null, 3, 0, null, null);
INSERT INTO Nid_GCI (idNid, nomPlage, raisonArretObservation, nbEnvol, protection, bagueMale, bagueFemelle) VALUES(21, 'BEG LANN', null, 0, 1, null, null);
INSERT INTO Nid_GCI (idNid, nomPlage, raisonArretObservation, nbEnvol, protection, bagueMale, bagueFemelle) VALUES(12, 'GOH VELIN', 'inconnu', 0, 0, null, null);
INSERT INTO Nid_GCI (idNid, nomPlage, raisonArretObservation, nbEnvol, protection, bagueMale, bagueFemelle) VALUES(2, 'BEG LAnn', 'inconnu', 0, 0, null, null);
INSERT INTO Nid_GCI (idNid, nomPlage, raisonArretObservation, nbEnvol, protection, bagueMale, bagueFemelle) VALUES(1, 'LANDREZAC', 'inconnu', 0, 0, null, null);
INSERT INTO Nid_GCI (idNid, nomPlage, raisonArretObservation, nbEnvol, protection, bagueMale, bagueFemelle) VALUES(4, 'PENVINS', null, 2, 0, null, null);
INSERT INTO Nid_GCI (idNid, nomPlage, raisonArretObservation, nbEnvol, protection, bagueMale, bagueFemelle) VALUES(5, 'PENVINS', null, 0, 0, null, null);
INSERT INTO Nid_GCI (idNid, nomPlage, raisonArretObservation, nbEnvol, protection, bagueMale, bagueFemelle) VALUES(7, 'BRENEGUY', null, 2, 1, null, null);
INSERT INTO Nid_GCI (idNid, nomPlage, raisonArretObservation, nbEnvol, protection, bagueMale, bagueFemelle) VALUES(6, 'BRENEGUY', 'inconnu', 0, 1, null, null);
INSERT INTO Nid_GCI (idNid, nomPlage, raisonArretObservation, nbEnvol, protection, bagueMale, bagueFemelle) VALUES(13, 'SALINE EXPLOITEE', null, 2, 0, null, null);
INSERT INTO Nid_GCI (idNid, nomPlage, raisonArretObservation, nbEnvol, protection, bagueMale, bagueFemelle) VALUES(3, 'SALINE', null, 2, 0, null, 'FBV/MtWhV');
INSERT INTO Nid_GCI (idNid, nomPlage, raisonArretObservation, nbEnvol, protection, bagueMale, bagueFemelle) VALUES(14, 'BEDUME', 'inconnu', 0, 0, null, null);
INSERT INTO Nid_GCI (idNid, nomPlage, raisonArretObservation, nbEnvol, protection, bagueMale, bagueFemelle) VALUES(16, 'BRENEGUY', 'inconnu', 0, 1, null, null);
INSERT INTO Nid_GCI (idNid, nomPlage, raisonArretObservation, nbEnvol, protection, bagueMale, bagueFemelle) VALUES(17, 'BRENEGUY', 'inconnu', 0, 1, null, null);
INSERT INTO Nid_GCI (idNid, nomPlage, raisonArretObservation, nbEnvol, protection, bagueMale, bagueFemelle) VALUES(27, 'BANASTERE', 'inconnu', 0, 0, null, null);
INSERT INTO Nid_GCI (idNid, nomPlage, raisonArretObservation, nbEnvol, protection, bagueMale, bagueFemelle) VALUES(24, 'KERVER', 'inconnu', 0, 0, null, null);
INSERT INTO Nid_GCI (idNid, nomPlage, raisonArretObservation, nbEnvol, protection, bagueMale, bagueFemelle) VALUES(23, 'BRENEGUY', 'inconnu', 0, 1, null, null);
INSERT INTO Nid_GCI (idNid, nomPlage, raisonArretObservation, nbEnvol, protection, bagueMale, bagueFemelle) VALUES(25, 'KERPENHIR', 'inconnu', 1, 1, null, null);
INSERT INTO Nid_GCI (idNid, nomPlage, raisonArretObservation, nbEnvol, protection, bagueMale, bagueFemelle) VALUES(26, 'KERPENHIR', null, 3, 1, null, 'FBB/MtJV');
INSERT INTO Nid_GCI (idNid, nomPlage, raisonArretObservation, nbEnvol, protection, bagueMale, bagueFemelle) VALUES(15, 'KERPHENIR', null, 3, 1, null, null);
INSERT INTO Nid_GCI (idNid, nomPlage, raisonArretObservation, nbEnvol, protection, bagueMale, bagueFemelle) VALUES(22, 'LANDREZAC', null, 1, 0, null, null);
INSERT INTO Nid_GCI (idNid, nomPlage, raisonArretObservation, nbEnvol, protection, bagueMale, bagueFemelle) VALUES(11, 'GOH VELIN', null, 2, 0, null, null);




INSERT INTO Lieu VALUES(273096.2165054050, 6725605.6810743900);
INSERT INTO Lieu VALUES(273078.6611603060, 6725578.0691511800);
INSERT INTO Lieu VALUES(273074.1008592430, 6725561.4521396100);
INSERT INTO Lieu VALUES(272907.9076095320, 6725581.6954353200);
INSERT INTO Lieu VALUES(271694.8417928950, 6726555.1834496900);
INSERT INTO Lieu VALUES(271554.7462436810, 6726624.3993029900);
INSERT INTO Lieu VALUES(268360.5427639650, 6727245.5533518000);
INSERT INTO Lieu VALUES(260014.9920878820, 6729208.6592708500);
INSERT INTO Lieu VALUES(268978.2109384420, 6727439.0063310300);
INSERT INTO Lieu VALUES(270341.4538534440, 6727211.9575110400);
INSERT INTO Lieu VALUES(272971.5151845810, 6725556.5911142500);
INSERT INTO Lieu VALUES(272938.2653573860, 6725570.8410401900);
INSERT INTO Lieu VALUES(252535.9487015880, 6734779.3364062200);
INSERT INTO Lieu VALUES(252534.5237089940, 6734771.4989469600);
INSERT INTO Lieu VALUES(266847.5023072490, 6731016.6346514300);
INSERT INTO Lieu VALUES(266888.7656044050, 6731018.8531082700);
INSERT INTO Lieu VALUES(285269.4857815990, 6728523.5232218400);
INSERT INTO Lieu VALUES(252533.9382169120, 6734761.5217581100);
INSERT INTO Lieu VALUES(252534.3586616570, 6734793.1952622500);
INSERT INTO Lieu VALUES(273770.3570374720, 6727835.9208781300);
INSERT INTO Lieu VALUES(258768.0476392940, 6730917.9910828000);
INSERT INTO Lieu VALUES(252536.0054035870, 6734786.2929610100);
INSERT INTO Lieu VALUES(254052.0597037880, 6733953.3872538200);
INSERT INTO Lieu VALUES(254008.7362955270, 6733941.3668862100);
INSERT INTO Lieu VALUES(253966.9611283740, 6733921.3629276100);
INSERT INTO Lieu VALUES(271632.3581535670, 6726603.0946741100);
INSERT INTO Lieu VALUES(260101.5897311960, 6729104.8985842900);

-- Nid n°1
INSERT INTO Observation VALUES(1+811, '2020-04-23', null, 270341.4538534440, 6727211.9575110400);
INSERT INTO Obs_GCI VALUES(1+811, 'oeuf', 3, null, 1);
INSERT INTO Observation VALUES(2+811, '2020-05-06', null, 270341.4538534440, 6727211.9575110400);
INSERT INTO Obs_GCI VALUES(2+811, 'nid', 0, null, 1);
-- Nid n°2
INSERT INTO Observation VALUES(3+811, '2020-04-23', null, 268978.2109384420, 6727439.0063310300);
INSERT INTO Obs_GCI VALUES(3+811, 'oeuf', 2, null, 2);
INSERT INTO Observation VALUES(4+811, '2020-05-06', null, 268978.2109384420, 6727439.0063310300);
INSERT INTO Obs_GCI VALUES(4+811, 'nid', 0, null, 2);
-- Nid n°3
INSERT INTO Observation VALUES(5+811, '2020-05-04', null, 266888.7656044050, 6731018.8531082700);
INSERT INTO Obs_GCI VALUES(5+811, 'oeuf', 3, null, 3);
INSERT INTO Observation VALUES(6+811, '2020-06-09', null, 266888.7656044050, 6731018.8531082700);
INSERT INTO Obs_GCI VALUES(6+811, 'poussin', 2, null, 3);
-- Nid n°4
INSERT INTO Observation VALUES(7+811, '2020-05-06', null, 272971.5151845810, 6725556.5911142500);
INSERT INTO Obs_GCI VALUES(7+811, 'oeuf', 1, null, 4);
INSERT INTO Observation VALUES(8+811, '2020-05-21', null, 272971.5151845810, 6725556.5911142500);
INSERT INTO Obs_GCI VALUES(8+811, 'nid', 0, null, 4);
-- Nid n°5
INSERT INTO Observation VALUES(9+811, '2020-05-06', null, 272938.2653573860, 6725570.8410401900);
INSERT INTO Obs_GCI VALUES(9+811, 'nid', 1, null, 5);
INSERT INTO Observation VALUES(10+811, '2020-06-01', null, 272938.2653573860, 6725570.8410401900);
INSERT INTO Obs_GCI VALUES(10+811, 'poussin', 2, null, 5);
INSERT INTO Observation VALUES(11+811, '2020-06-16', null, 272938.2653573860, 6725570.8410401900);
INSERT INTO Obs_GCI VALUES(11+811, 'poussin', 2, null, 5);
INSERT INTO Observation VALUES(12+811, '2020-07-14', null, 272938.2653573860, 6725570.8410401900);
INSERT INTO Obs_GCI VALUES(12+811, 'poussin', 2, null, 5);
-- Nid n°6
INSERT INTO Observation VALUES(13+811, '2020-05-14', null, 252534.3586616570, 6734793.1952622500);
INSERT INTO Obs_GCI VALUES(13+811, 'oeuf', 3, null, 6);
INSERT INTO Observation VALUES(14+811, '2020-05-25', null, 252534.3586616570, 6734793.1952622500);
INSERT INTO Obs_GCI VALUES(14+811, 'nid', 0, null, 6);
-- Nid n°7
INSERT INTO Observation VALUES(15+811, '2020-05-25', null, 252535.9487015880, 6734779.3364062200);
INSERT INTO Obs_GCI VALUES(15+811, 'oeuf', 3, null, 7);
INSERT INTO Observation VALUES(16+811, '2020-06-25', null, 252535.9487015880, 6734779.3364062200);
INSERT INTO Obs_GCI VALUES(16+811, 'poussin', 2, null, 7);
INSERT INTO Observation VALUES(17+811, '2020-07-09', null, 252535.9487015880, 6734779.3364062200);
INSERT INTO Obs_GCI VALUES(17+811, 'poussin', 2, null, 7);
-- Nid n°8
INSERT INTO Observation VALUES(18+811, '2020-06-08', null, 273078.6611603060, 6725578.0691511800);
INSERT INTO Obs_GCI VALUES(18+811, 'nid', 1, null, 8);
INSERT INTO Observation VALUES(19+811, '2020-06-11', null, 273078.6611603060, 6725578.0691511800);
INSERT INTO Obs_GCI VALUES(19+811, 'oeuf', 2, null, 8);
INSERT INTO Observation VALUES(20+811, '2020-06-18', null, 273078.6611603060, 6725578.0691511800);
INSERT INTO Obs_GCI VALUES(20+811, 'nid', 0, null, 8);
-- Nid n°9
INSERT INTO Observation VALUES(21+811, '2020-06-09', null, 271554.7462436810, 6726624.3993029900);
INSERT INTO Obs_GCI VALUES(21+811, 'oeuf', 3, null, 9);
INSERT INTO Observation VALUES(22+811, '2020-06-24', null, 271554.7462436810, 6726624.3993029900);
INSERT INTO Obs_GCI VALUES(22+811, 'poussin', 3, null, 9);
INSERT INTO Observation VALUES(23+811, '2020-07-08', null, 271554.7462436810, 6726624.3993029900);
INSERT INTO Obs_GCI VALUES(23+811, 'poussin', 3, null, 9);
-- Nid n°10
INSERT INTO Observation VALUES(24+811, '2020-06-09', null, 271694.8417928950, 6726555.1834496900);
INSERT INTO Obs_GCI VALUES(24+811, 'oeuf', 2, null, 10);
INSERT INTO Observation VALUES(25+811, '2020-06-24', null, 271694.8417928950, 6726555.1834496900);
INSERT INTO Obs_GCI VALUES(25+811, 'oeuf', 3, null, 10);
INSERT INTO Observation VALUES(26+811, '2020-07-08', null, 271694.8417928950, 6726555.1834496900);
INSERT INTO Obs_GCI VALUES(26+811, 'nid', 0, null, 10);
-- Nid n°11
INSERT INTO Observation VALUES(27+811, '2020-06-09', null, 260101.5897311960, 6729104.8985842900);
INSERT INTO Obs_GCI VALUES(27+811, 'poussin', 2, null, 11);
INSERT INTO Observation VALUES(28+811, '2020-06-24', null, 260101.5897311960, 6729104.8985842900);
INSERT INTO Obs_GCI VALUES(28+811, 'poussin', 3, null, 11);
INSERT INTO Observation VALUES(29+811, '2020-07-02', null, 260101.5897311960, 6729104.8985842900);
INSERT INTO Obs_GCI VALUES(29+811, 'poussin', 2, null, 11);
-- Nid n°12
INSERT INTO Observation VALUES(30+811, '2020-06-09', null, 260014.9920878820, 6729208.6592708500);
INSERT INTO Obs_GCI VALUES(30+811, 'oeuf', 2, null, 12);
INSERT INTO Observation VALUES(31+811, '2020-06-24', null, 260014.9920878820, 6729208.6592708500);
INSERT INTO Obs_GCI VALUES(31+811, 'nid', 0, null, 12);
-- Nid n°13
INSERT INTO Observation VALUES(33+811, '2020-06-09', null, 266847.5023072490, 6731016.6346514300);
INSERT INTO Obs_GCI VALUES(33+811, 'poussin', 2, null, 13);
-- Nid n°14
INSERT INTO Observation VALUES(34+811, '2020-06-10', null, 285269.4857815990, 6728523.5232218400);
INSERT INTO Obs_GCI VALUES(34+811, 'oeuf', 2, null, 14);
INSERT INTO Observation VALUES(35+811, '2020-06-16', null, 285269.4857815990, 6728523.5232218400);
INSERT INTO Obs_GCI VALUES(35+811, 'oeuf', 3, null, 14);
INSERT INTO Observation VALUES(36+811, '2020-06-17', null, 285269.4857815990, 6728523.5232218400);
INSERT INTO Obs_GCI VALUES(36+811, 'oeuf', 3, null, 14);
INSERT INTO Observation VALUES(37+811, '2020-06-18', null, 285269.4857815990, 6728523.5232218400);
INSERT INTO Obs_GCI VALUES(37+811, 'oeuf', 3, null, 14);
INSERT INTO Observation VALUES(38+811, '2020-06-19', null, 285269.4857815990, 6728523.5232218400);
INSERT INTO Obs_GCI VALUES(38+811, 'oeuf', 3, null, 14);
INSERT INTO Observation VALUES(39+811, '2020-06-20', null, 285269.4857815990, 6728523.5232218400);
INSERT INTO Obs_GCI VALUES(39+811, 'oeuf', 3, null, 14);
INSERT INTO Observation VALUES(40+811, '2020-06-21', null, 285269.4857815990, 6728523.5232218400);
INSERT INTO Obs_GCI VALUES(40+811, 'oeuf', 3, null, 14);
INSERT INTO Observation VALUES(41+811, '2020-06-22', null, 285269.4857815990, 6728523.5232218400);
INSERT INTO Obs_GCI VALUES(41+811, 'oeuf', 3, null, 14);
INSERT INTO Observation VALUES(42+811, '2020-06-23', null, 285269.4857815990, 6728523.5232218400);
INSERT INTO Obs_GCI VALUES(42+811, 'oeuf', 3, null, 14);
INSERT INTO Observation VALUES(43+811, '2020-07-08', null, 285269.4857815990, 6728523.5232218400);
INSERT INTO Obs_GCI VALUES(43+811, 'nid', 0, null, 14);
-- Nid n°15
INSERT INTO Observation VALUES(44+811, '2020-06-11', null, 253966.9611283740, 6733921.3629276100);
INSERT INTO Obs_GCI VALUES(44+811, 'poussin', 3, null, 15);
INSERT INTO Observation VALUES(45+811, '2020-06-25', null, 253966.9611283740, 6733921.3629276100);
INSERT INTO Obs_GCI VALUES(45+811, 'poussin', 3, null, 15);
-- Nid n°16
INSERT INTO Observation VALUES(46+811, '2020-06-11', null, 252533.9382169120, 6734761.5217581100);
INSERT INTO Obs_GCI VALUES(46+811, 'nid', 1, null, 16);
INSERT INTO Observation VALUES(47+811, '2020-07-09', null, 252533.9382169120, 6734761.5217581100);
INSERT INTO Obs_GCI VALUES(47+811, 'nid', 0, null, 16);
-- Nid n°17
INSERT INTO Observation VALUES(48+811, '2020-06-11', null, 252534.3586616570, 6734793.1952622500);
INSERT INTO Obs_GCI VALUES(48+811, 'nid', 1, null, 17);
INSERT INTO Observation VALUES(49+811, '2020-07-09', null, 252534.3586616570, 6734793.1952622500);
INSERT INTO Obs_GCI VALUES(49+811, 'nid', 0, null, 17);
-- Nid n°18
INSERT INTO Observation VALUES(50+811, '2020-06-14', null, 272907.9076095320, 6725581.6954353200);
INSERT INTO Obs_GCI VALUES(50+811, 'oeuf', 1, null, 18);
INSERT INTO Observation VALUES(51+811, '2020-06-15', null, 272907.9076095320, 6725581.6954353200);
INSERT INTO Obs_GCI VALUES(51+811, 'oeuf', 2, null, 18);
INSERT INTO Observation VALUES(52+811, '2020-06-24', null, 272907.9076095320, 6725581.6954353200);
INSERT INTO Obs_GCI VALUES(52+811, 'oeuf', 3, null, 18);
INSERT INTO Observation VALUES(53+811, '2020-07-08', null, 272907.9076095320, 6725581.6954353200);
INSERT INTO Obs_GCI VALUES(53+811, 'oeuf', 2, null, 18);
INSERT INTO Observation VALUES(54+811, '2020-07-11', null, 272907.9076095320, 6725581.6954353200);
INSERT INTO Obs_GCI VALUES(54+811, 'poussin', 2, null, 18);
INSERT INTO Observation VALUES(55+811, '2020-07-22', null, 272907.9076095320, 6725581.6954353200);
INSERT INTO Obs_GCI VALUES(55+811, 'poussin', 2, null, 18);
INSERT INTO Observation VALUES(56+811, '2020-08-07', null, 272907.9076095320, 6725581.6954353200);
INSERT INTO Obs_GCI VALUES(56+811, 'poussin', 2, null, 18);
-- Nid n°19
INSERT INTO Observation VALUES(57+811, '2020-06-15', null, 273074.1008592430, 6725561.4521396100);
INSERT INTO Obs_GCI VALUES(57+811, 'oeuf', 1, null, 19);
INSERT INTO Observation VALUES(58+811, '2020-06-18', null, 273074.1008592430, 6725561.4521396100);
INSERT INTO Obs_GCI VALUES(58+811, 'nid', 0, null, 19);
-- Nid n°20
INSERT INTO Observation VALUES(59+811, '2020-06-24', null, 273096.2165054050, 6725605.6810743900);
INSERT INTO Obs_GCI VALUES(59+811, 'oeuf', 1, null, 20);
INSERT INTO Observation VALUES(60+811, '2020-06-28', null, 273096.2165054050, 6725605.6810743900);
INSERT INTO Obs_GCI VALUES(60+811, 'nid', 0, null, 20);
-- Nid n°21
INSERT INTO Observation VALUES(61+811, '2020-06-24', null, 268360.5427639650, 6727245.5533518000);
INSERT INTO Obs_GCI VALUES(61+811, 'oeuf', 3, null, 21);
INSERT INTO Observation VALUES(62+811, '2020-07-08', null, 268360.5427639650, 6727245.5533518000);
INSERT INTO Obs_GCI VALUES(62+811, 'nid', 0, null, 21);
-- Nid n°22
INSERT INTO Observation VALUES(63+811, '2020-06-24', null, 271632.3581535670, 6726603.0946741100);
INSERT INTO Obs_GCI VALUES(63+811, 'poussin', 1, null, 22);
INSERT INTO Observation VALUES(64+811, '2020-07-06', null, 271632.3581535670, 6726603.0946741100);
INSERT INTO Obs_GCI VALUES(64+811, 'poussin', 1, null, 22);
INSERT INTO Observation VALUES(65+811, '2020-07-11', null, 271632.3581535670, 6726603.0946741100);
INSERT INTO Obs_GCI VALUES(65+811, 'poussin', 1, null, 22);
-- Nid n°23
INSERT INTO Observation VALUES(66+811, '2020-06-25', null, 252536.0054035870, 6734786.2929610100);
INSERT INTO Obs_GCI VALUES(66+811, 'nid', 1, null, 23);
INSERT INTO Observation VALUES(67+811, '2020-07-09', null, 252536.0054035870, 6734786.2929610100);
INSERT INTO Obs_GCI VALUES(67+811, 'nid', 0, null, 23);
-- Nid n°24
INSERT INTO Observation VALUES(68+811, '2020-06-25', null, 258768.0476392940, 6730917.9910828000);
INSERT INTO Obs_GCI VALUES(68+811, 'oeuf', 2, null, 24);
INSERT INTO Observation VALUES(69+811, '2020-07-08', null, 258768.0476392940, 6730917.9910828000);
INSERT INTO Obs_GCI VALUES(69+811, 'nid', 0, null, 24);
-- Nid n°25
INSERT INTO Observation VALUES(70+811, '2020-06-25', null, 254052.0597037880, 6733953.3872538200);
INSERT INTO Obs_GCI VALUES(70+811, 'oeuf', 3, null, 25);
INSERT INTO Observation VALUES(71+811, '2020-07-09', null, 254052.0597037880, 6733953.3872538200);
INSERT INTO Obs_GCI VALUES(71+811, 'poussin', 2, null, 25);
INSERT INTO Observation VALUES(72+811, '2020-07-23', null, 254052.0597037880, 6733953.3872538200);
INSERT INTO Obs_GCI VALUES(72+811, 'poussin', 1, null, 25);
-- Nid n°26
INSERT INTO Observation VALUES(73+811, '2020-06-25', null, 254008.7362955270, 6733941.3668862100);
INSERT INTO Obs_GCI VALUES(73+811, 'poussin', 3, null, 26);
INSERT INTO Observation VALUES(74+811, '2020-07-09', null, 254008.7362955270, 6733941.3668862100);
INSERT INTO Obs_GCI VALUES(74+811, 'poussin', 3, null, 26);
INSERT INTO Observation VALUES(75+811, '2020-07-23', null, 254008.7362955270, 6733941.3668862100);
INSERT INTO Obs_GCI VALUES(75+811, 'poussin', 3, null, 26);
-- Nid n°27
INSERT INTO Observation VALUES(76+811, '2020-07-08', null, 273770.3570374720, 6727835.9208781300);
INSERT INTO Obs_GCI VALUES(76+811, 'oeuf', 2, null, 27);
INSERT INTO Observation VALUES(77+811, '2020-07-22', null, 273770.3570374720, 6727835.9208781300);
INSERT INTO Obs_GCI VALUES(77+811, 'oeuf', 1, null, 27);
INSERT INTO Observation VALUES(78+811, '2020-07-31', null, 273770.3570374720, 6727835.9208781300);
INSERT INTO Obs_GCI VALUES(78+811, 'nid', 0, null, 27);





INSERT INTO Observateur VALUES(73, 'DUHAMEL', 'Benoit');
INSERT INTO Observateur VALUES(74, 'LEDAN', 'David');
INSERT INTO Observateur VALUES(75, 'HOCHET', 'Anne-Sophie');

INSERT INTO AObserve (lobservateur, lobservation) VALUES(1+73,1+811);
INSERT INTO AObserve (lobservateur, lobservation) VALUES(1+73,2+811);
INSERT INTO AObserve (lobservateur, lobservation) VALUES(1+73,3+811);
INSERT INTO AObserve (lobservateur, lobservation) VALUES(1+73,4+811);
INSERT INTO AObserve (lobservateur, lobservation) VALUES(0+73,7+811);
INSERT INTO AObserve (lobservateur, lobservation) VALUES(1+73,7+811);
INSERT INTO AObserve (lobservateur, lobservation) VALUES(0+73,8+811);
INSERT INTO AObserve (lobservateur, lobservation) VALUES(1+73,8+811);
INSERT INTO AObserve (lobservateur, lobservation) VALUES(1+73,9+811);
INSERT INTO AObserve (lobservateur, lobservation) VALUES(1+73,10+811);
INSERT INTO AObserve (lobservateur, lobservation) VALUES(1+73,11+811);
INSERT INTO AObserve (lobservateur, lobservation) VALUES(1+73,12+811);
INSERT INTO AObserve (lobservateur, lobservation) VALUES(1+73,13+811);
INSERT INTO AObserve (lobservateur, lobservation) VALUES(1+73,14+811);
INSERT INTO AObserve (lobservateur, lobservation) VALUES(0+73,15+811);
INSERT INTO AObserve (lobservateur, lobservation) VALUES(1+73,15+811);
INSERT INTO AObserve (lobservateur, lobservation) VALUES(0+73,16+811);
INSERT INTO AObserve (lobservateur, lobservation) VALUES(1+73,16+811);
INSERT INTO AObserve (lobservateur, lobservation) VALUES(0+73,17+811);
INSERT INTO AObserve (lobservateur, lobservation) VALUES(1+73,17+811);
INSERT INTO AObserve (lobservateur, lobservation) VALUES(2+73,18+811);
INSERT INTO AObserve (lobservateur, lobservation) VALUES(2+73,19+811);
INSERT INTO AObserve (lobservateur, lobservation) VALUES(2+73,20+811);
INSERT INTO AObserve (lobservateur, lobservation) VALUES(0+73,21+811);
INSERT INTO AObserve (lobservateur, lobservation) VALUES(1+73,21+811);
INSERT INTO AObserve (lobservateur, lobservation) VALUES(0+73,22+811);
INSERT INTO AObserve (lobservateur, lobservation) VALUES(1+73,22+811);
INSERT INTO AObserve (lobservateur, lobservation) VALUES(0+73,23+811);
INSERT INTO AObserve (lobservateur, lobservation) VALUES(1+73,23+811);
INSERT INTO AObserve (lobservateur, lobservation) VALUES(0+73,24+811);
INSERT INTO AObserve (lobservateur, lobservation) VALUES(1+73,24+811);
INSERT INTO AObserve (lobservateur, lobservation) VALUES(0+73,25+811);
INSERT INTO AObserve (lobservateur, lobservation) VALUES(1+73,25+811);
INSERT INTO AObserve (lobservateur, lobservation) VALUES(0+73,26+811);
INSERT INTO AObserve (lobservateur, lobservation) VALUES(1+73,26+811);
INSERT INTO AObserve (lobservateur, lobservation) VALUES(0+73,27+811);
INSERT INTO AObserve (lobservateur, lobservation) VALUES(1+73,27+811);
INSERT INTO AObserve (lobservateur, lobservation) VALUES(0+73,28+811);
INSERT INTO AObserve (lobservateur, lobservation) VALUES(1+73,28+811);
INSERT INTO AObserve (lobservateur, lobservation) VALUES(0+73,29+811);
INSERT INTO AObserve (lobservateur, lobservation) VALUES(1+73,29+811);
INSERT INTO AObserve (lobservateur, lobservation) VALUES(0+73,30+811);
INSERT INTO AObserve (lobservateur, lobservation) VALUES(1+73,30+811);
INSERT INTO AObserve (lobservateur, lobservation) VALUES(0+73,31+811);
INSERT INTO AObserve (lobservateur, lobservation) VALUES(1+73,31+811);
INSERT INTO AObserve (lobservateur, lobservation) VALUES(0+73,33+811);
INSERT INTO AObserve (lobservateur, lobservation) VALUES(1+73,33+811);
INSERT INTO AObserve (lobservateur, lobservation) VALUES(1+73,34+811);
INSERT INTO AObserve (lobservateur, lobservation) VALUES(1+73,35+811);
INSERT INTO AObserve (lobservateur, lobservation) VALUES(1+73,36+811);
INSERT INTO AObserve (lobservateur, lobservation) VALUES(1+73,37+811);
INSERT INTO AObserve (lobservateur, lobservation) VALUES(1+73,38+811);
INSERT INTO AObserve (lobservateur, lobservation) VALUES(1+73,39+811);
INSERT INTO AObserve (lobservateur, lobservation) VALUES(1+73,40+811);
INSERT INTO AObserve (lobservateur, lobservation) VALUES(1+73,41+811);
INSERT INTO AObserve (lobservateur, lobservation) VALUES(1+73,42+811);
INSERT INTO AObserve (lobservateur, lobservation) VALUES(1+73,43+811);
INSERT INTO AObserve (lobservateur, lobservation) VALUES(0+73,44+811);
INSERT INTO AObserve (lobservateur, lobservation) VALUES(1+73,44+811);
INSERT INTO AObserve (lobservateur, lobservation) VALUES(0+73,45+811);
INSERT INTO AObserve (lobservateur, lobservation) VALUES(1+73,45+811);
INSERT INTO AObserve (lobservateur, lobservation) VALUES(0+73,46+811);
INSERT INTO AObserve (lobservateur, lobservation) VALUES(1+73,46+811);
INSERT INTO AObserve (lobservateur, lobservation) VALUES(0+73,47+811);
INSERT INTO AObserve (lobservateur, lobservation) VALUES(1+73,47+811);
INSERT INTO AObserve (lobservateur, lobservation) VALUES(0+73,48+811);
INSERT INTO AObserve (lobservateur, lobservation) VALUES(1+73,48+811);
INSERT INTO AObserve (lobservateur, lobservation) VALUES(0+73,49+811);
INSERT INTO AObserve (lobservateur, lobservation) VALUES(1+73,49+811);
INSERT INTO AObserve (lobservateur, lobservation) VALUES(0+73,50+811);
INSERT INTO AObserve (lobservateur, lobservation) VALUES(0+73,51+811);
INSERT INTO AObserve (lobservateur, lobservation) VALUES(0+73,52+811);
INSERT INTO AObserve (lobservateur, lobservation) VALUES(0+73,53+811);
INSERT INTO AObserve (lobservateur, lobservation) VALUES(0+73,54+811);
INSERT INTO AObserve (lobservateur, lobservation) VALUES(0+73,55+811);
INSERT INTO AObserve (lobservateur, lobservation) VALUES(0+73,56+811);
INSERT INTO AObserve (lobservateur, lobservation) VALUES(0+73,57+811);
INSERT INTO AObserve (lobservateur, lobservation) VALUES(0+73,58+811);
INSERT INTO AObserve (lobservateur, lobservation) VALUES(0+73,59+811);
INSERT INTO AObserve (lobservateur, lobservation) VALUES(0+73,60+811);
INSERT INTO AObserve (lobservateur, lobservation) VALUES(0+73,61+811);
INSERT INTO AObserve (lobservateur, lobservation) VALUES(0+73,62+811);
INSERT INTO AObserve (lobservateur, lobservation) VALUES(0+73,63+811);
INSERT INTO AObserve (lobservateur, lobservation) VALUES(0+73,64+811);
INSERT INTO AObserve (lobservateur, lobservation) VALUES(0+73,65+811);
INSERT INTO AObserve (lobservateur, lobservation) VALUES(0+73,66+811);
INSERT INTO AObserve (lobservateur, lobservation) VALUES(1+73,66+811);
INSERT INTO AObserve (lobservateur, lobservation) VALUES(0+73,67+811);
INSERT INTO AObserve (lobservateur, lobservation) VALUES(1+73,67+811);
INSERT INTO AObserve (lobservateur, lobservation) VALUES(0+73,68+811);
INSERT INTO AObserve (lobservateur, lobservation) VALUES(0+73,69+811);
INSERT INTO AObserve (lobservateur, lobservation) VALUES(0+73,70+811);
INSERT INTO AObserve (lobservateur, lobservation) VALUES(0+73,71+811);
INSERT INTO AObserve (lobservateur, lobservation) VALUES(0+73,72+811);
INSERT INTO AObserve (lobservateur, lobservation) VALUES(0+73,73+811);
INSERT INTO AObserve (lobservateur, lobservation) VALUES(0+73,74+811);
INSERT INTO AObserve (lobservateur, lobservation) VALUES(0+73,75+811);
INSERT INTO AObserve (lobservateur, lobservation) VALUES(0+73,76+811);
INSERT INTO AObserve (lobservateur, lobservation) VALUES(0+73,77+811);
INSERT INTO AObserve (lobservateur, lobservation) VALUES(0+73,78+811);