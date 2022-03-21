gradlew viene utilizzato per distribuire la versione di gradle utilizzata nel creare.
gradlew contiene quindi info sulla versione utilizzata per la creazione.
se non è presente quella versione andrà quindi a scaricarla.
ciò garantisce una distribuzione clean.

in .gitignore metto i file ignora i file nel push


# 08/03
la pipe non va bene perchè solo unix-like. Nelle specifiche viene invece detto che in un futuro potrebbe essere usato su sistemi diversi.

"opinionated" ---> un software improntato in una certa logica di business e costruito attorno ad essa.

business logic --> il principio è la RESPONSABILITA'. Ogni pezzo ha una sua responsabilità. Quella del led, di accendersi. Quella del sonar di rilevare le posizione. 

todo: creare nuovo progetto vuoto con gradle. it.unibo.radarSystem22.domain e cominciare a scrivere le interfaccie. Analisi del problema. 
Un interfaccia è come un contratto, agli occhi della macchina un led è semplice oggetto che dovrà implementare. Con delle proprietà e metodi per accenderlo e spegnerlo e verificarne lo stato. Queste operazioni sono considerabili primitive.
Il sonar sarà una qualsiasi entità che implementa ISonar, con relativi metodi accessori. Notare che getDistance non torna un valore, ma un'altra entità IDistance. 

# 10/03

il codice dominio, anche a livello di project, va ben separato dal software dell'applicazione. Questo perchè, a partire dallo stesso dominio si potrebbe sviluppare tutt'altra applicazione

Nella modelizzazione il "dettaglio" del requisito di sistema distribuito non è rilevante. E' del tutto ignorabile per il momento. 
Nell'architettura logica si evince subito chi vede chi. Per come è stata costruita intanto nessun dispositivo ne vede un altro, ma si interefacciano solo con il controller.

Il piano di test dovrebbe testare il comportamento del controller. Il controller dovrebbe, ad una certa distanza rilevata, accendere il led. 
Questo piano di test è "fuffa", è utile solo come descrizione.

Siccome stiamo usando delle interfacce, quindi diciamo solo COSA, non COME. Il COME verrà determinato nell'implementazione. Ma utilizzando un certo design pattern possiamo sostituire gli oggetti reali con altri. Questo è ciò che rende le interfacce una componente essenziale del sistema.

Se ci sono due unità che vogliono comunicare ho bisogno di un oggetto che ne gestica le interazioni implementando un' interfaccia che specifica le operazioni da fare. 

Il system designer diminuisce l'abstraction gap per l'application designer.
Il system designer dei domini non sa niente. Non dipendono da loro. Il loro scopo non è di mettere in comunicazione le entità di dominio, ma di fornire supporto per qualsiasi dominio.
Più il system designer è bravo meno l'application designer deve lavorare, colmando maggiormente l'abstraction gap. (Vengono pagati di più). La loro competenza è più ampia.

#todo: sonarMock thread attivo che ogni 250ms diminuisca distanza e fare il test

aboutThreads() farà notare come in Kotlin si useranno meno processi e più leggeri rispetto a JAva

# 15/03
Da recuperare il Sonar model e file configurazione domain.

Poi Controller nel main project, direttamente come classe senza interfaccia perchè è strettamente collegato all'applicazione.
Il Sonar è produttore, il Controller consumatore. Il metodo getDistance() operazione NON atomica ritornando la distanza gestita da un thread, ha un problema di consistenza.
Questo potrebbe portare non solo a portare alla lettura di un dato vecchio, ma propriamente errato poichè colto nell'aggiornamento della variabile.

Nel radarsystemsprintmain abbiamo una prima parte di setup delle impostazioni modificando DomainSystemConfig. Successivamente si configura creando gli oggetti utilizzando le Factory. Poi crea il controller con create(..) passandogli tutti i dispositivi.
doJob prepara una lambda function che contiene una callback. 

            ActionFunction endFun = (n) -> {
                System.out.println(n);
                terminate();
            }

Il metodo terminate verrà eseguito dal chiamante della funzione (il controller nel caso).
L'applicazione è un'implementazione di un interfaccia IApplication che definisce due semplici metodi doJob e getName, a scopo organizzativo. 


# 17/03

Occorre definire un "contratto" che leghi il server con il codice applicativo. Ciò nasce da un vincolo: il TCP Server non deve avere NIENTE legato alla logica applicativa. Non posso scrivere del codice legato alla business logic all'interno del server. Sempre per il principio della singola responsabilità. Il TCP Server fa il TCP Server, period. 

Il TCP Server, o quel che sarà, avrà un nome, una porta se necessaria, e l'handler che gestisce le comunicazioni per la logica applicativa.

Il TCP Server quando crea una nuova connnessione crea un thread dedicato a gestirla e gli passa l'handler. 

Una classe astratta implementa l'interfaccia IAppMsgHandler.  Realizza l'invio di messaggi ai clienti ma delega alle specializzazione di definire il metodo elaborate per la gestione dei messaggi. 

Per ora discrimina con due differenti metodi la richiesta dalla risposta. Successivamente lo si ricaverà direttamente dal contenuto del messaggio.

La parte tratteggiata sarà un'infrastruttura che serve per dare il supporto al client nella comunicazione. 

# todo: seguente
Il Rasp avrà una proxy di IRadarDisplay che attraverso la rete comunicherà con IRadarDisplay
del PC.

#plustodo
Un'altra architettura, più complessa, vede il Controller sul PC. Quindi le due proxy sarebbero ISonar ed Iled. La complessità è aumentata perchè necessita di più interazioni.
Nel passaggio non deve cambiare il codice di Controller. Si incapsula nel Rasp ISonar in un server (così come il led). Il Controller interagisce con dei Mock.
I due proxy lato PC devono connettersi al rispettivi Server. 

..
Alla semplicità lato client si aggiunge complessità lato server, e viceversa. 
Il TCP Server deve seguire le regole fissate dal livello applicativo. Fa parte dei principi di 

    The Clean Architecture Cone

L'idea è che in cima alla piramide vi sono le Entities. Non hanno nessun tipo di dipendenza da nessuno. Le dipendenza vanno sempre dai livelli esteriori a quello più interiore (cima piramide, cono). Gli Use Cases  (Application business) dipendono solo dalle Entities. A seguire dipendono i Controllers e per ultimo, dipendende da questo le External Interface, layer successivi (web gui, etc.).

# todo test proxy e deployment

nel new RadarGuiProxyAsClient è possibile che connessione non vada con il Rasp per i proxy. Testare intanto con local host.

..

Nell'elaborate controlla il contenuto del messaggio per poter rispondere alla richiesta. Per esempio con l'if equal getCurDistance (hardcoded, br).

Sprint2 sysonRasp
    Il RadarOnPC si metterà semplicemente in attesa di aggiornamenti creando il server. Il Controller On Rasp.
    Nella controparte Ros il radar è creato passanto il proxy. 

Distribuzione discorso main class nel build.gradle






