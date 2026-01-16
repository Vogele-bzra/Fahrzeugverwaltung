# 🚗 IdealCar4You - Fahrzeug- & Kundenverwaltung

Eine Java-Desktop-Anwendung zur Verwaltung eines Fahrzeugbestands (Autos & Transporter) und eines Kundenstamms. Das Projekt wurde im Rahmen des Moduls "OO Entwerfen und Implementieren" erstellt.

## 📋 Projektbeschreibung

Dieses System ermöglicht Autohäusern die digitale Erfassung und Verwaltung ihres Bestands. Es basiert auf der **MVC-Architektur** (Model-View-Controller) und speichert Daten persistent in JSON-Dateien. 

### ✨ Hauptfunktionen

* **Fahrzeugverwaltung (CRUD):**
    * Erfassen von **Autos** (inkl. Ausstattungsmerkmale wie Navi, Aufbau).
    * Erfassen von **Transportern** (inkl. max. Zuladung).
    * Bearbeiten und Löschen von bestehenden Fahrzeugen.
    * Automatische Unterscheidung der Fahrzeugtypen in der GUI.
* **Kundenverwaltung:**
    * Anlegen, Bearbeiten und Löschen von Kundendaten.
    * Suchfunktion für Kunden und Fahrzeuge.
* **Datenpersistenz:**
    * Alle Daten werden automatisch in `fahrzeuge.json` und `kunden.json` gespeichert.
    * Daten bleiben nach Programmneustart erhalten.
* **Qualitätssicherung:**
    * Eingabevalidierung (z.B. Buchstaben im Preisfeld werden abgefangen).
    * Automatisierte Unit-Tests mit JUnit 5.

## 🛠️ Technologie-Stack

* **Sprache:** Java 21
* **GUI Framework:** Java Swing (JPanel, JFrame, LayoutManagers)
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
    Stelle sicher, dass folgende Bibliotheken im Classpath sind:
    * `jackson-databind`
    * `jackson-core`
    * `jackson-annotations`
    * `junit-jupiter` (für Tests)
4.  **Starten:**
    Führe die Klasse `src/view/App.java` (oder `MainGUI.java`) aus.

## 📂 Projektstruktur (MVC)

Das Projekt ist sauber in Schichten unterteilt:

```text
src/
├── controller/       # Steuert die Logik (FahrzeugVerwaltung, KundenVerwaltung)
├── model/            # Datenklassen (Auto, Transporter, Kunde, Fahrzeug)
├── view/             # Benutzeroberfläche (MainGUI, FahrzeugPanel, KundenPanel)
├── Service/          # Datenzugriff (JsonSpeicherservice)
└── test/             # Unit Tests (VerwaltungTest)
