SHELL=/bin/bash -O extglob
ENUMS=src/modele/donnee/{ContenuNid,EspeceBatracien,EspeceChouette,EspeceHippocampe,EspeceObservee,IndiceLoutre,Peche,Sexe,TypeObservation}.java
CLASSES=src/modele/donnee/{Observation,ObsChouette,IObs,ObsGCI,*}.java
SCENARIOS=src/modele/{ScenarioDonnee,ScenarioTraitement}.java

all: run

build:
	javac -classpath class/ -d class/ $(ENUMS) $(CLASSES) $(SCENARIOS)

run: build
	@echo ""
	java -classpath class/ modele.ScenarioDonnee

javadoc:
	javadoc -charset UTF8 -private -author -classpath class/ -d doc src/**.java
