### Programmeringsoppgavene

#### Oppgave 1 - GPS data innlesing/utskrift

I denne oppgaven skal dere se på klassen [GPSDataReaderWriter.java](https://github.com/dat100hib/dat100-prosjekt/blob/master/src/no/hvl/dat100/prosjekt/GPSDataReaderWriter.java) som inneholder Java-kode for å lese inn en CVS-datafil med GPS punkter i formatet forklart tidligere.

I klassen brukes fire tabeller med tekststrenger (typen String) til å representere GPS datapunktene:

```java
String[] times;
String[] latitudes;
String[] longitudes;
String[] elevations;
```

Ideen er at times-tabellen brukes til å lagre tidspunktene fra GPS datapunktene, latitudes-tabellen brukes til å lagre breddegradene, longitudes-tabellen brukes til å lagre lengdegrader, og elevations-tabellen brukes til å lagre høyde. De resterende data i et GPS datapunkt skal vi ikke bruke.

Metoden:

```java
public static GPSData readGPSFile(String filename)  
```

leser linje for linje i GPS datafilen og lagrer data i tabellene ovenfor. Ser vi på eksemplet fra tidligere der vi hadde fem datapunkter i filen da vil tabellene ha følgende innhold etter innlesing:

```java
times = { "2017-08-13T08:52:26.000Z", "2017-08-13T08:53:00.000Z",
          "2017-08-13T08:53:57.000Z", "2017-08-13T08:55:55.000Z",
          "2017-08-13T08:57:57.000Z" }

latitudes = { "60.385390", "60.385588", "60.385398", "60.383428", "60.376988" }

longitudes = { "5.217217", , "5.217857","5.216950", "5.219823", "5.227082" }

elevations = { "61.9", "56.2", "56.1","57.0", "105.5" }

```

Dvs. informasjonen fra første GPS datapunkt står på indeks (posision) 0 i tabellen ovenfor, informasjon fra andre GPS datapunkt finnes på indeks 1 osv.

##### Oppgave 1a)

Se på koden for readGPSFile-metoden for å forstå hvordan den virker.

I klassen finnes starten på en metode:

```java
public static void printGPSData(GPSData gpsdata)
```

som skal skrive det innleste GPS data ut på skjermen (Eclipse Console-vinduet). Metoden bruker en for-løkke for å gå igjennom alle elementene i times-tabellen for å skrive disse ut på skjermen.

Hvis du kjører main-metoden i GPSDataReaderWriter-klassen, vil du se utskriften i Console-vinduet.

##### Oppgave 1b)

Utvid implementasjonen av printGPSData-metoden slik at også innhold fra de tre andre tabellene også skrives ut.

For den ferdige implementasjonen skal begynnelsen på utskriften i Console-vinduet se slik ut:

```
19
time,latitude,longitude,elevation
2017-08-13T08:52:26.000Z,60.385390,5.217217,61.9
2017-08-13T08:53:00.000Z,60.385588,5.217857,56.2
2017-08-13T08:53:57.000Z,60.385398,5.216950,56.1
2017-08-13T08:55:55.000Z,60.383428,5.219823,57.0
2017-08-13T08:57:57.000Z,60.376988,5.227082,105.5
```

#### Oppgave 2 - Konvertering av GPS data fra strenger til tall

I oppgave 1 har vi sett at GPS datafilen kan leses inn og GPS datapunkter kan representeres ved å bruke fire tabeller med strenger. For å kunne gjøre beregninger på GPS dataene må vi konvertere strengene med data til tall.

Formålet med klassen [GPSDataConverter.java](https://github.com/dat100hib/dat100-prosjekt/blob/master/src/no/hvl/dat100/prosjekt/GPSDataConverter.java) er å implementere metoder som kan gjøre denne konverteringen.

I klassen finnes fire tabeller:

```java
private String[] timesstr, latitudesstr, longitudesstr, elevationsstr;
```

som inneholder streng-representasjon av GPS data (som forklart i oppgave 1) og vi skal nå konvertere disse data og lagre informasjonen i fire nye tabeller:

```java
public int[] times;
public double[] latitudes, longitudes, elevations;
```

Ser vi på et eksempel GPS datapunkt skal vi altså konvertere:

- Strengen `"2017-08-13T08:52:26.000Z"` til heltallet (int) `31946` som angir antall sekunder fra midnatt.
- Strengen `"60.385390"` til flyttallet (double) `60.385390`
-	Strengen `"5.217217"` til flyttallet (double) `5.217217`
-	Strengen `"61.9"` til flyttallet `61.9`

Dette skal gjøres for alle elementer i tabellene. Ser vi eksempelvis på latitudesstr-tabellen med strenger:

```java
latitudesstr = { "60.385390", "60.385588", "60.385398", "60.383428", "60.376988" }
```

da skal denne konverters til en latitude-tabellen av tal som ser slik ut:

```java
latitudes = { 60.385390, 60.385588, 60.385398, 60.383428, 60.376988}
```

Metoden ```public void convert()``` i klassen GPSDataConverter inneholder starten på kode som kan gjøre denne konverteringen for breddegrader.

##### Oppgave 2a)

Utvid convert-metoden slik den også konverterer lengdegrader og høyder.

##### Oppgave 2b)

Gjør ferdig implementasjon av toSeconds-metoden som omregner tidsdata til antall sekunder og bruk den i convert-metoden slik at tidsinformasjonen også blir konvertert.

Klassen [GPSDataConverterTester.java](https://github.com/dat100hib/dat100-prosjekt/blob/master/src/no/hvl/dat100/prosjekt/test/GPSDataConverterTester.java) inneholder en rekke enhetstest som du kan bruke til å teste implementasjonen din.

##### Oppgave 2c)

Til slutt skal dere implementere metoden ```print()``` som skal skrive ut det konverterte data. Denne metoden vil også bli kjørt av enhetstestene og begynnelsen av utskriften skal se slik ut:

```
long
Konvertert GPS Data
31946 (60.38539,5.217217) 61.9
31980 (60.385588,5.217857) 56.2
32037 (60.385398,5.21695) 56.1
32155 (60.383428,5.219823) 57.0
32277 (60.376988,5.227082) 105.5
32406 (60.370383,5.23205) 49.7
32527 (60.359813,5.237472) 40.2
32651 (60.361153,5.243403) 70.4
```

##### Oppgave 2d)

Sjekk at første delen av utskriften stemmer overens med innholdet i log-datafilen som blev lest inn.

#### Oppgave 3 - GPS hjelpemetoder

Nå kan vi lese inn GPS data og konvertere det det tall som vi kan gjøre beregninger på. I første om-gang skal vi implementere noen hjelpe-metoder i klassen [GPSUtils.java](https://github.com/dat100hib/dat100-prosjekt/blob/master/src/no/hvl/dat100/prosjekt/GPSUtils.java) som vi skal bruke seinere i prosjektet.


Gjør ferdig implementasjonen av følgende metoder i klassen og bruk enhetstestene i klassen GPSUtils.java til løpende å teste koden.

```java
double findMin(double[] da)
```

som finner minste tall i en tabell med flyttall. Hint: se på implementasjonen av metoden findMax i klassen.

```java
double distance(double latitude1, double longitude1,
          double latitude2, double longitude2)
```

som bruker Math-klassen: https://docs.oracle.com/javase/8/docs/api/java/lang/Math.html

til å beregne avstanden *d* i meter mellom to punkter på jordkloden ved bruk av Haversine-formlen

![](assets/markdown-img-paste-20180909113408842.png)

der *R = 6371000* meter er jordens gjennomsnittsradius.

```java
double speed(int secs,
             double latitude1, double longitude1,        
             double latitude2, double longitude2)
```

som beregninger gjennomsnittshastighet i km/t om man beveger seg fra punktet gitt ved (latitude1,longitude1) til punktet (latitude2,longitude2) på det antall sekunder som er gitt med parameteren secs.

```java
String printDouble(double d)
```

som runder av et flyttall til to desimaler, setter resultat inn i en streng og fyller på med mellomrom foran i strengen slik at lengden på strengen blir 10 (se eksempel i koden).

```java
public static String printTime(int secs)
```

som returnerer en streng der tiden i sekunder fra midnatt gitt av parameteren secs på formatet `hh:mm:ss` der `hh` er antall timer, `mm` er antall minutter og `ss` er antall sekunder.
