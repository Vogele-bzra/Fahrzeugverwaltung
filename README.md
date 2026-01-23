# 🚗 IdealCar4You - Fahrzeug- & Kundenverwaltung

Eine Java-Desktop-Anwendung zur Verwaltung eines Fahrzeugbestands (Autos & Transporter) und eines Kundenstamms. Das Projekt wurde im Rahmen des Moduls "OO Entwerfen und Implementieren" realisiert.

## 📋 Projektbeschreibung

Dieses System ermöglicht Autohäusern die digitale Erfassung, Verwaltung und Persistierung ihres Bestands. Die Anwendung basiert auf der strikten **MVC-Architektur** (Model-View-Controller), um Logik und Benutzeroberfläche sauber zu trennen.

### ✨ Hauptfunktionen

* **Fahrzeugverwaltung (CRUD):**
    * Erfassen von **Autos** (inkl. spezifischer Attribute wie Navi, Aufbauform).
    * Erfassen von **Transportern** (inkl. max. Zuladung).
    * Bearbeiten und Löschen von bestehenden Fahrzeugen.
    * Dynamische GUI: Unterscheidung der Eingabefelder je nach Fahrzeugtyp.
* **Kundenverwaltung:**
    * Umfassende Pflege von Kundendaten (CRUD).
    * Integrierte Suchfunktion (Filterung nach Attributen).
* **Datenpersistenz:**
    * Automatische Speicherung aller Datensätze in JSON-Dateien (`fahrzeuge.json`, `kunden.json`).
    * Wiederherstellung des Zustands nach Programmneustart.
* **Sicherheit & Qualität:**
    * **Login-System** zum Schutz vor unbefugtem Zugriff.
    * Eingabevalidierung (z. B. Typensicherheit bei Preisen und Zahlen).
    * Unit-Tests mit JUnit 5 zur Absicherung der Geschäftslogik.

## 🔐 Login / Zugangsdaten

Beim Start der Anwendung wird ein Login-Fenster angezeigt. Nutzen Sie folgende Standard-Daten für den Zugriff:

| Rolle | Benutzername | Passwort |
| :--- | :--- | :--- |
| **Administrator** | `admin` | `1234` |

## 🛠️ Technologie-Stack

* **Sprache:** Java 21
* **GUI Framework:** Java Swing (JFrame, JPanel, EventHandling)
* **Architektur:** MVC (Model-View-Controller)
* **Datenhaltung:** JSON (via Jackson Library `com.fasterxml.jackson`)
* **Testing:** JUnit 5 (Jupiter)
* **IDE:** IntelliJ IDEA

## 🚀 Installation & Start

1.  **Repository klonen:**
    ```bash
    git clone [https://github.com/DEIN-USERNAME/IdealCar4You.git](https://github.com/DEIN-USERNAME/IdealCar4You.git)
    ```
2.  **In IntelliJ öffnen:**
    Öffne den Ordner als Projekt in IntelliJ IDEA.
3.  **Abhängigkeiten (Dependencies):**
    Stelle sicher, dass folgende Bibliotheken (JARs) im Classpath eingebunden sind:
    * `jackson-databind`
    * `jackson-core`
    * `jackson-annotations`
    * `junit-jupiter` (nur für Tests erforderlich)
4.  **Starten:**
    Führe die Klasse `src/view/App.java` aus.
    *(Hinweis: Starten Sie nicht direkt die MainGUI, sondern immer über App.java, damit der Login-Prozess initialisiert wird.)*

## 📂 Projektstruktur

Das Projekt ist modular nach dem MVC-Pattern aufgebaut:

```text
src/
├── controller/       # Steuerungslogik (Verbindet View und Model)
├── model/            # Datenklassen (Auto, Transporter, Kunde)
├── view/             # Benutzeroberfläche (LoginGUI, MainGUI, Panels)
├── service/          # Datenzugriffsschicht (JsonSpeicherservice)
└── test/             # Unit Tests (JUnit)
