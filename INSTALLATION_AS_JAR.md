## Installation der Urlaubsverwaltung ab Version 2.12.0

* [Installation](#installation)
* [Konfiguration](#konfiguration)
    * [Umgebungen](#umgebungen)
    * [Authentifizierung](#authentifizierung)
    * [Konfiguration ab Version 2.7.0](#konfiguration-ab-version-270)
    * [Konfiguration bis Version 2.6.4](#konfiguration-bis-version-264)

Die folgende Anleitung beschreibt die Installation der Urlaubsverwaltung als
[Spring Boot](http://projects.spring.io/spring-boot/) Anwendung.

---

## Installation

#### Systemvoraussetzungen

* JDK 8
* MySQL Datenbank

#### Download

Die Anwendung steht auf Github bereits als deploybare JAR-Datei zum Download zur Verfügung.
Einfach die JAR-Datei der aktuellsten Version [hier](https://github.com/synyx/urlaubsverwaltung/releases/latest)
downloaden.

#### Starten der Anwendung

Damit man die Anwendung möglichst schnell ausprobieren kann, bietet es sich an die Anwendung mit den
Standardeinstellungen zu starten:

<pre>java -jar urlaubsverwaltung.jar</pre>

#### Aufrufen der Anwendung

Die Anwendung ist nun erreichbar unter

`<servername>:8080/urlaubsverwaltung`

#### Anwendung als Service

Die Spring Boot Dokumentation enthält Anleitungen, wie man Spring Boot Anwendungen als Services installieren kann:
* [Linux Service](http://docs.spring.io/spring-boot/docs/current/reference/html/deployment-install.html#deployment-service)
* [Windows Service](http://docs.spring.io/spring-boot/docs/current/reference/html/deployment-windows.html)

---

## Konfiguration

#### Umgebungen

Die Anwendung verfügt über **drei** verschiedene Umgebungsmöglichkeiten:

* `dev`
    * zum lokalen Entwickeln
    * nutzt eine H2-Datenbank
    * legt Testdaten an
* `test`
    * zum Testen der Anwendung
    * nutzt eine MySQL-Datenbank
    * legt keine Testdaten an
* `prod`
    * zum Ausführen der produktiven Anwendung
    * nutzt eine MySQL-Datenbank
    * legt keine Testdaten an

##### Umgebung aktivieren

Damit die Anwendung in einer Umgebung gestartet wird, muss die System-Property `env` auf `dev`, `test` oder `prod`
gesetzt werden:

<pre>java -jar urlaubsverwaltung.jar -Denv=UMGEBUNG</pre>

#### Authentifizierung

Die Anwendung verfügt über **drei** verschiedene Authentifizierungsmöglichkeiten:

* `default`
    * für lokale Entwicklungsumgebung
* `ldap`
    * Authentifizierung via LDAP
    * Es müssen die LDAP URL, die LDAP Base und LDAP User DN Patterns konfiguriert sein, damit eine Authentifizierung via LDAP möglich ist.
* `activeDirectory`
    * Authentifizierung via Active Directory
    * Es müssen die Active Directory Domain und LDAP URL konfiguriert sein, damit eine Authentifizierung via Active Directory möglich ist.

Der erste Benutzer, der sich erfolgreich im System einloggt, wird in der Urlaubsverwaltung mit der Rolle Office angelegt.
Dies ermöglicht Benutzer- und Rechteverwaltung innerhalb der Anwendung und das Pflegen der Einstellungen für die Anwendung.

##### Überschreiben der Properties

Die Anwendung besitzt im Verzeichnis `src/main/resources` jeweils eine `.properties` Datei zur Konfiguration der
jeweiligen [Umgebung](#umgebungen).

* eine standardmäßige `application.properties`
* `application-test.properties`
* `application-prod.properties`

Diese beinhalten gewisse Grundeinstellungen und Standardwerte. Diese allein reichen für die Produktivnahme der
Anwendung allerdings noch nicht aus. Spezifische Konfigurationen wie z.B. die Datenbank Einstellungen müssen durch eine
eigene Properties-Datei hinterlegt werden. Welche Konfigurationen überschrieben werden können/müssen, sind in der
standardmäßigen `application.properties` des Projekts einsehbar.

Welche Möglichkeiten es bei Spring Boot gibt, damit die eigene Konfigurationsdatei genutzt wird, kann
[hier](http://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-external-config.html#boot-features-external-config-application-property-files)
nachgelesen werden.

##### Produktivumgebung aktivieren

Die Anwendung mit dem Parameter `-Denv=prod` starten:

<pre>java -jar urlaubsverwaltung.jar -Denv=prod</pre>

###### LDAP

Um LDAP zur Authentifizierung zu nutzen, muss die Property `auth` in der eigenen Konfigurationsdatei auf `ldap` gesetzt
werden:

<pre>auth=ldap</pre>

###### Active Directory

Um Active Directory zur Authentifizierung zu nutzen, muss die Property `auth` in der eigenen Konfigurationsdatei auf
`activeDirectory` gesetzt werden:

<pre>auth=activeDirectory</pre>

###### Datenbank

Hinweis: Die in der Konfigurationsdatei konfigurierte Datenbank muss existieren.