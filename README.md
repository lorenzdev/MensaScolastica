# Mensa scolastica

## Verifica TePSIT

### Gruppo 3

Nome e cognome  
Nome e cognome  
Nome e cognome  

### Docente

prof. Vitale Lorenzo

### Descrizione
Obiettivo minimo (80 punti)  
Realizzare un programma Client-Server che implementi un servizio di mensa scolastica in cui viene
gestita la banca dati degli studenti e dei piatti del giorno.
Ogni studente ha un account composto dalla propria e-mail e da una password in aggiunta ad una
serie di informazioni personali come nome,cognome, numero di cell, data di nascita, indirizzo e
città di residenza, classe e sezione. I piatti hanno un nome, numero di calorie, tipologia (primo,
secondo, contorno, frutta). Ogni piatto è inserito in un menu che è identificato dal giorno (lunedì,
martedì, mercoledì, giovedì, venerdì, sabato), un nome e una etichetta che indica se è a base di
pesce o carne.
Il Server offre tre servizi ai Client:
1. Permette ad un nuovo studente di iscriversi inviando i propri dati al Server che procede a
memorizzarli nella propria banca dati.
2. Gli studenti iscritti, dopo essersi loggati inviando la propria e-mail e password di accesso,
possono richiedere l’elenco dei piatti di ogni menu indicando il giorno;
3. Gli studenti iscritti possono prenotarsi alla mensa del giorno indicato;  
Obiettivo avanzato (20 punti)  
Implementare un ulteriore servizio di multicasting che prevede che i Client si mettano in ascolto.
Il Server, quando riceve una nuova prenotazione da parte di un Client, prima di salvarla nel
database invia a tutti gli altri Client connessi e in ascolto il nome del Client che si è prenotato alla
mensa e a quale menu si è prenotato.  
N.B. Per semplificare la risoluzione di questo punto, prevedere che i Client, una volta in modalità
di ricezione multicasting, non ricevano comandi di input dall’utente ma restano esclusivamente in
ascolto.


### Usage

Prima operazione da effettuare è ottenere il progetto dal repository lanciando il seguente comando dalla bash di git
```bash
git clone https://github.com/lorenzdev/MensaScolastica.git
```
Successivamente lanciare i seguenti comandi per ottenere le modifiche:

```bash
git pull origin master
```

e per pubblicare le proprie modifiche:

```bash
git add .
git commit -m "descrizione del commit"
git push origin master
```

### Consegna
31/05/2019