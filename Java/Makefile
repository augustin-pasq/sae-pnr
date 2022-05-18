SHELL=/bin/bash -O extglob
ENUMS=src/modele/donnee/{ContenuNid,EspeceBatracien,EspeceChouette,EspeceHippocampe,EspeceObservee,IndiceLoutre,Peche,Sexe,TypeObservation}.java
CLASSES=src/modele/donnee/{Observation,ObsChouette,IObs,ObsGCI,*}.java

all: run

build:
	echo "Compiling everything"
	javac -Xlint:all -classpath class/ -d class/ $(ENUMS) $(CLASSES)

run: build
	echo "\nRunning ScenarioDonnee"
	java -classpath class/ ScenarioDonnee

%: build
	echo "Running " $@
	java -classpath class/ $@

javadoc:
	javadoc -charset UTF8 -private -author -classpath class/ -d doc src/**.java
