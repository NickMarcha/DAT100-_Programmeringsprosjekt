## Ekstraoppgaver

### Oppgave 4 - Ekstraoppgaver

Utvid klassen med en metode

```java
public void climbs()
```

som beregner stigningsprosent for de ulike deler av ruten og en metode

```java
public void maxClimb()
```

som finner den del av ruten som har den høyeste stigningsprosent.

### Oppgave 5 - Ekstraoppgaver

- Legg til tekststrenger som viser enheter på y-aksen.

- Utvid showHeightProfile-metoden slik kurven kan tegnes i skalert real-tid. Husk at times-tabellen gir tidspunkt for hvert datapunkt og vi kan utefra dette finne den reelle tiden mellom to punkter.For at det ikke skal ta for lang tid å tegne høydeprofilen skal brukeren kunne taste inn et tall som angir hvor mye tiden skal skaleres ned. Eksempelvis vil tallet 100 angi at hvert sekund i realtid skal tilsvare til 1/100 sekunder når datafilen visualiseres.

Skaleringsfaktoren kan leses inn ved å bruke `getText`-metoden i EasyGraphics og metoden `pause` (også fra EasyGraphics) kan brukes til å implementere en pause mellom tegning av hver søyle.

### Oppgave 7 - Ekstraoppgave

- Forbind punktene med linjer - se bildet i oppgave 7

- Få en blå sirkel til å flytte seg langs ruten der hastigheten som sirkelen flytter seg med mellom to punkter er relativt til den hastighet som det blev kjørt med på denne delen av sykkelturen. EasyGraphics-metoden `speed` kan brukes til å kontrollere hastigheten som grafiskobjekter blir flyttet med.

### Ekstra oppgave 8 - En samlet sykkelcomputer

Gjør ferdig implementasjonen av klassen [CycleComputer.java](https://github.com/dat100hib/dat100-prosjekt/blob/master/src/no/hvl/dat100/prosjekt/CycleComputer.java) som utvider og samler al visualisering fra de forrige oppgavene i et vindu. Videre skal programmet løpende (i skalert realtid) vise tid, aktuell hastighet, gjennomsnitt osv. (øverst til venstre) og visualisere høydeprofil og tegne ruten på et kart etter hvert vi kommer frem til de enkelte GPS posisjoner. Se figuren i [mål for prosjektet](https://github.com/dat100hib/dat100-prosjekt/blob/master/docs/introduksjon.md) for en skisse.

### Ekstra oppgave 9 - Flere samtidige sykkelryttere

Utvid programmet fra ekstraoppgave 8 slik det kan håndtere to (eller flere) samtidige sykkelryttere (represen-tert ved hver sin GPS datafil) som sykler samme ruten.

### Ekstra oppgave: Kom i mål før din faglærer

Denne oppgaven er vanskelig :-)

Last ned eksempelvis GPSLogger app’en til Android:

https://play.google.com/store/apps/details?id=com.mendhak.gpslogger&hl=en

eller tilsvarende applikasjon for iOS som kan logge og dele GPS data i CSV format.

Sett deg på sykkelen og kjør samme ruten som i GPS datafilen vm.log fra prosjektet - se ruten på http://doarama.com/view/1604599

Bruk programmet fra Oppgave 9 for å undersøke om du sykler raskere enn din faglærer.
