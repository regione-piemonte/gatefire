# Prodotto

GATEFIRE

## Descrizione del prodotto

Il “Gateway di Firma Digitale” è una soluzione open source orientata all’orchestrazione dei processi di gestione della firma digitale e di conferimento dei documenti digitali nei repository aziendali/regionali, installata sulla Piattaforma regionale Cloud Nivola.
Si inserisce nell’ecosistema sanitario digitale della Regione Piemonte.


**Elenco componenti**

Il prodotto si compone di un'unica componente maven  -> ***gatefiremaster*** 
Dal build di questa componente vengono generate quattro librerie e le due unità di installazione che ne fanno uso

Le unita di installazione sono
 - GATEFIRESRV -> insieme dei servizi di firma e di conferimento nei repository clinici documentali delle aziende sanitare. Per la completa realizzazione della firma digitale si rende necessaria l'integrazione con il componente server specifico della singola CA (es: ARSS di ARUBA, ProxySIGN di Infocert, etc)
 - GATEFIREBOWEB ->  cruscotto web che consente la configurazione dei parametri necessari al funzionamento dei servizi di firma e di conferimento e di monitorare le attività effettuate sul gateway stesso. 
                 

Le quattro librerie sono:

- common.jar: contiene oggetti di modello comuni e utility

- db-helper.jar: contiene i mapper mybatis, DAO e servizi di accesso alla base dati

- ca-interface: contiene le logiche di comunicazione e interfaccia con le varie CA

- repo-interface: contiene le logiche di comunicazione e interfaccia verso i repository 



## Configurazioni iniziali

La firma digitale remota è una firma digitale che si basa sull’uso di servizi telematici remoti messi a disposizione dalle Certication Authority (CA) 

Gatefire nella soluzione di Regione Piemonte si integra per la firma digitale remota con ArubaSign (ARSS) e Infocert (ProxySign) 


## Prerequisiti di sistema

Server Web: Apache 2.4.54

Application Server: Wildfly 17

Tipo di database: PostgreSQL 12.4

ARSS Aruba

ProxySign Infocert

Eventuali altre dipendenze elencate nella cartella docs/wsdl


## Build, installazione e deployment

lanciare il comando maven clean package -P ${MVN_PROFILE} -Dskip-unit-test=true

`MVN_PROFILE può valere dev, test, prod `

Le due unità di installazione generate si troveranno sotto:

```
gatefiremaster\gatefiresrv-ear\target
gatefiremaster\gatefireboweb-ear\target
```



Inserire il file ear generato durante l'installazione sotto la cartella deployments del Jboss


## Versioning (Obbligatorio)

Per il versionamento del software si usa la tecnica Semantic Versioning (http://semver.org).
1.0.0

## Authors

* Paola Mantovani
* Raffaela Montuori

## Copyrights

“© Copyright Regione Piemonte – 2022”

## License

SPDX-License-Identifier: EUPL-1.2
