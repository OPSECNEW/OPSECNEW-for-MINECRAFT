<p align="center">
<img src="https://github.com/user-attachments/assets/9adba640-2570-4c22-9355-4f13aa5d4507" alt="OpSec-Logo" width="15%"/>
</p>
<h1 align="center">OpSec New for Minecraft</h1>

<p align="center"><a href="README.md">🇬🇧 English</a> · <b>🇩🇪 Deutsch</b></p>

<p align="center">Eine clientseitige Minecraft-Mod, die dich vor Client-Fingerprinting, Mod-Erkennung, Tracking-Exploits und mehr schützt – weitergeführt als Community-Fork.</p>

<p align="center">
<img alt="Minecraft" src="https://img.shields.io/badge/Minecraft-1.20%20%E2%80%93%2026.3-3C8527">
<img alt="Loader" src="https://img.shields.io/badge/Loader-Fabric-DBD0B4">
<img alt="Version" src="https://img.shields.io/badge/Version-1.1.8--fork-6e5494">
<img alt="Lizenz" src="https://img.shields.io/badge/Lizenz-GPL--3.0-blue">
</p>

## Über diesen Fork

Das ursprüngliche **OpSec** von Aurick ([aurickk/OpSec](https://github.com/aurickk/OpSec)) wurde am 10. August 2026 von seinem Autor archiviert. Die letzte Version, v1.1.7.1, unterstützt Minecraft 1.20 – 26.2.

**Ich halte das Projekt am Leben.** Dieser Fork führt OpSec unter derselben GPL-3.0-Lizenz weiter und bringt es auf die neuesten Minecraft-Versionen – angefangen mit **Minecraft 26.3**. Probiert es gerne aus und meldet alles, was nicht funktioniert, damit es behoben werden kann.

- ✅ Unterstützt **Minecraft 26.3** (dazu alle Versionen, die das Original unterstützt hat)
- ✅ **Läuft zusammen mit [ExploitPreventer](https://github.com/NikOverflow/ExploitPreventer)** – siehe [ExploitPreventer-Kompatibilität](#exploitpreventer-kompatibilität)
- ✅ Alle Funktionen des Originals, plus Korrekturen für die Teile, die Minecraft 26.3 geändert hat

Alle Anerkennung für die ursprüngliche Mod gebührt Aurick. Builds dieses Forks sind Community-Builds und **keine** offiziellen Versionen des Originalprojekts.

> [!WARNING]
> Dies ist ein Hobbyprojekt, das mit Hilfe von **KI** entwickelt und gepflegt wird.

> [!IMPORTANT]
> Gefälschte Websites und Discord-Server haben **mit Trojanern verseuchte** OpSec-Versionen verbreitet. Lade diesen Fork nur von der [**Releases**](../../releases)-Seite dieses Repositorys herunter. Das Original (bis MC 26.2) gibt es auf [Modrinth](https://modrinth.com/mod/opsec) und [GitHub](https://github.com/aurickk/OpSec/releases). Downloads von anderen Seiten können Schadsoftware enthalten.

## Was die Mod macht

- **[Spoof as Vanilla](#spoof-as-vanilla)** – meldet einen Vanilla-Client und blockiert jede Mod-Erkennung
- **[Channel Spoofing](#channel-spoofing)** – versteckt Netzwerkkanäle deiner Mods gezielt vor dem Server
- **[Known-Pack Filtering](#known-pack-filtering)** – entfernt Mod-Pack-Kennungen aus dem Verbindungsaufbau
- **[Isolate Pack Cache](#isolate-pack-cache)** – eigener Resourcepack-Speicher pro Account gegen Tracking
- **[Block Local URLs](#block-local-urls)** – blockiert Resourcepack-Adressen, die in dein Heimnetz zeigen
- **[Bypass Server Pack Requirement](#bypass-server-pack-requirement)** – Pflicht-Resourcepacks wie normale Packs ein- und ausschalten
- **[Strip Mod Shader Overrides](#strip-mod-shader-overrides)** – entfernt Server-Shader, die auf deine Mods zielen
- **[Key Resolution Protection](#key-resolution-protection)** – verhindert Mod-Erkennung über Übersetzungsschlüssel und Tastenbelegungen
- **[Meteor Fix](#meteor-fix)** – deaktiviert den fehlerhaften Schutz von Meteor Client
- **[Mod Whitelist](#mod-whitelist)** – Mods, die den Server brauchen, automatisch oder manuell ausnehmen
- **[Chat Signing Control](#chat-signing-control)** – entscheide selbst, ob deine Chatnachrichten signiert werden
- **[Account Manager](#account-manager)** – zwischen Minecraft-Accounts wechseln, ohne neu zu starten
- **[Telemetry Blocking](#telemetry-blocking)** – stoppt die Datensammlung an Mojang

> Du willst wissen, welche Server oder Plugins Tracking-Exploits einsetzen? Schau in die [Hall of Shame](https://github.com/NikOverflow/ExploitPreventer/blob/master/HALL_OF_SHAME.md).

## Voraussetzungen

- **Minecraft** 1.20 – 26.3
- **Fabric Loader** 0.16.0+ (0.18.5+ für MC 26.1.x, 0.19.3+ für MC 26.3)
- **Fabric API** passend zu deiner Minecraft-Version
- **Java** 17 / 21 / 25 je nach Minecraft-Version (das macht der offizielle Launcher automatisch)

### Installation

1. Installiere den [Fabric Loader](https://fabricmc.net/use/) für deine Minecraft-Version.
2. Lade die [Fabric API](https://modrinth.com/mod/fabric-api) für deine Minecraft-Version herunter.
3. Lade die passende `opsec-<minecraft_version>+v<version>.jar` von der [Releases](../../releases)-Seite herunter.
4. Lege beide Dateien in deinen Ordner `.minecraft/mods` (eine ältere OpSec-Datei vorher löschen).
5. Starte Minecraft.

## Einstellungen

Die Einstellungen öffnest du über den **OpSec**-Knopf oben in der Mehrspieler-Serverliste oder über [Mod Menu](https://modrinth.com/mod/modmenu).

<img width="1465" height="820" alt="OpSec-Einstellungen" src="https://github.com/user-attachments/assets/c69a768b-60ac-4f78-9705-184f6c4e4495" />

Wenn du Einstellungen änderst, während du mit einem Server verbunden bist, verbinde dich neu, damit sie sicher greifen.

#### Tab „Protection“ (Schutz)

| Einstellung | Beschreibung |
|-------------|--------------|
| **Spoof as Vanilla** | [Spoof as Vanilla](#spoof-as-vanilla) ein/aus |
| **Isolate Pack Cache** | [Getrennten Pack-Speicher](#isolate-pack-cache) ein/aus |
| **Block Local Pack URLs** | [Blockieren lokaler URLs](#block-local-urls) ein/aus |
| **Bypass Server Pack Requirement** | Verhalten bei [Server-Packs](#bypass-server-pack-requirement):<br/>• **MANUAL** (Standard): Vanilla-Verhalten, du kannst jedes Server-Pack trotzdem ein-/ausschalten<br/>• **ASK**: Das Pack wird nicht angewendet, ein Dialog fragt, ob es geladen werden soll<br/>• **ALWAYS ON**: Server-Packs werden standardmäßig nicht angewendet, du kannst sie aber einschalten |
| **Strip Mod Shader Overrides** | [Entfernen von Mod-Shadern](#strip-mod-shader-overrides) ein/aus |
| **Clear Cache** | Alle gespeicherten Server-Resourcepacks löschen |
| **Key Resolution Spoofing** | [Key Resolution Protection](#key-resolution-protection) ein/aus |
| **Fake Default Keybinds** | Meldet die Vanilla-Standardbelegung statt deiner echten Tastenbelegung |
| **Meteor Fix** | Deaktiviert den fehlerhaften Schutz von Meteor Client (nur sichtbar, wenn Meteor installiert ist) |
| **Chat Signing** | [Chat-Signatur](#chat-signing-control): **ON** (Vanilla-Verhalten) oder **OFF** (maximale Privatsphäre) |
| **Disable Telemetry** | [Telemetrie-Blockierung](#telemetry-blocking) ein/aus |

#### Tab „Whitelist“

| Einstellung | Beschreibung |
|-------------|--------------|
| **Whitelist Mode** | • **AUTO** (Standard): Mods mit Netzwerkkanälen werden automatisch freigegeben<br/>• **CUSTOM**: Du wählst die freigegebenen Mods selbst aus<br/>• **BLOCK ALL**: wird automatisch gesetzt, solange *Spoof as Vanilla* aktiv ist |
| **Installed Mods** | Einzelne Mods EIN/AUS schalten, um sie vom Schutz auszunehmen (nur im CUSTOM-Modus) |

#### Tab „Misc“ (Sonstiges)

| Einstellung | Beschreibung |
|-------------|--------------|
| **Show Alerts** | Chatnachricht anzeigen, wenn Tracking erkannt wird |
| **Show Toasts** | Popup-Hinweise bei wichtigen Ereignissen anzeigen |
| **Log Detections** | Jede Erkennung zur Nachvollziehbarkeit ins Spiel-Log schreiben |
| **Debug Alerts** | Warnungen für jeden abgefragten Schlüssel anzeigen, auch für unveränderte |
| **Debug Command** | Den Befehl `/opsec` aktivieren (standardmäßig aus) |

#### Tab „Accounts“

| Einstellung | Beschreibung |
|-------------|--------------|
| **Saved Accounts** | Deine Accounts mit Anmelde-/Abmelde- und Entfernen-Knopf |
| **Refresh All** | Alle Account-Tokens erneut prüfen (ungültige werden rot angezeigt) |
| **Add Session Token** | Account über einen Session-(Access-)Token hinzufügen |
| **Import** | Accounts aus einer JSON-Datei importieren |
| **Export** | Accounts in eine JSON-Datei exportieren |

### Debug-Befehle

Der Befehl `/opsec` ist **standardmäßig aus** (einschalten unter Misc → Debug Command).

| Befehl | Beschreibung |
|--------|--------------|
| `/opsec` | Verfügbare Befehle anzeigen |
| `/opsec info` | Übersicht über alle erfassten Mods |
| `/opsec info <mod>` | Details zu einer Mod (Übersetzungsschlüssel, Tastenbelegungen, Kanäle, Known Packs, Shader) |
| `/opsec channels` | Alle erfassten Netzwerkkanäle mit Whitelist-Status |

### Warnmeldungen verstehen

- **Key Resolution Exploit Detected** – der Server fragt deine Schlüssel ab
- **Resource Pack Fingerprinting Detected** – eine verdächtige Resourcepack-Adresse wurde erkannt
- **Local URL Scan Detected** – ein Resourcepack zielt auf eine lokale/private Adresse

## Funktionen im Detail

### Spoof as Vanilla

Server können die „Brand“ deines Clients abfragen und so sehen, ob du einen modifizierten Client nutzt. OpSec tarnt den Client vollständig als Vanilla: Mod-Schlüssel, Netzwerkkanäle und Known-Pack-Kennungen werden blockiert, die Vanilla-Werte bleiben erhalten.

- **ON** – erscheint als unveränderter Minecraft-Client
- **OFF** – erscheint als normaler Fabric-Client (Standard)

Standardmäßig ist es aus, damit die Mod-Whitelist Mods mit Netzwerkkanälen automatisch freigeben kann.

---

### Isolate Pack Cache

Basiert auf [LiquidBounce](https://github.com/CCBlueX/LiquidBounce/blob/nextgen/src/main/java/net/ccbluex/liquidbounce/injection/mixins/minecraft/util/MixinDownloadQueue.java).

Über Pflicht-Resourcepacks können Server denselben Computer über verschiedene Accounts hinweg wiedererkennen ([Hintergrund](https://alaggydev.github.io/posts/cytooxien/)). Statt eines gemeinsamen Speichers (`~/.minecraft/downloads/`) legt OpSec für jede Account-UUID einen eigenen Ordner an.

---

### Block Local URLs

Abgeleitet von [ExploitPreventer](https://github.com/NikOverflow/ExploitPreventer) von [NikOverflow](https://github.com/NikOverflow).

Bösartige Server können Resourcepack-Adressen schicken, die in dein Heimnetz umleiten, um dort Geräte und Dienste abzufragen ([Hintergrund](https://alaggydev.github.io/posts/cytooxien/)). OpSec prüft, ob eine Anfrage oder Weiterleitung auf eine lokale Adresse zielt, und blockiert sie – außer der Server, auf dem du spielst, ist selbst lokal.

---

### Bypass Server Pack Requirement

Server können dir Resourcepacks aufzwingen. Ein Vanilla-Client kann ein Pflicht-Pack weder ablehnen noch ausschalten, und ein nur *vorgetäuschtes* Annehmen lässt sich über die Übersetzungsschlüssel des Packs erkennen.

Minecraft nimmt das Pack weiterhin ganz normal an und lädt es herunter, OpSec lässt dich aber dessen Texturen auf deiner Seite abschalten. Die Sprachdatei des Packs bleibt immer geladen, denn Server können deren Schlüssel abfragen (z. B. `{"translate": "some.pack.key"}`), um zu prüfen, ob das Pack wirklich aktiv ist – und ein Vanilla-Client mit geladenem Pack würde sie auflösen.

Mit OpSec erscheinen Server-Packs als ganz normale Einträge im Resourcepack-Menü, sodass du jederzeit zwischen „gestrippt“ und „voll geladen“ wechseln kannst.

**Modi:**
- **MANUAL** (Standard): Pflicht-Packs werden wie bei Vanilla vollständig geladen. Du kannst ein Server-Pack trotzdem im Pack-Menü abwählen, um es zu strippen (die Sprachdatei bleibt geladen).
- **ASK**: Pflicht-Packs werden beim Laden gestrippt, und ein Dialog fragt `[Continue]` / `[Load Pack For Real]`.
- **ALWAYS ON**: Alle Server-Packs werden beim Laden gestrippt, ohne Dialog. Du kannst sie trotzdem wieder einschalten.

---

### Strip Mod Shader Overrides

Manche Mods (z. B. [Meteor Client](https://github.com/MeteorDevelopment/meteor-client)) zeichnen ihre Oberfläche mit eigenen Shadern, die über Minecrafts Ressourcensystem geladen werden. Ein erzwungenes Server-Pack kann diese Shader überschreiben, um die Oberfläche der Mod unsichtbar zu machen, den Client mit kaputten Shadern abstürzen zu lassen oder die Grafikkarte zu überlasten – und verrät dabei gleichzeitig, dass die Mod installiert ist.

OpSec entfernt Shader unter `assets/<mod>/shaders/` aus Server-Packs für jede installierte Mod, die nicht freigegeben ist, sodass die Mod ihre eigenen Shader verwendet. Der Rest des Packs wird trotzdem geladen; das funktioniert also auch dann, wenn ein Server das Pack nicht umgehbar macht. Vanilla-Shader (`minecraft`) werden nie angefasst, und eine freigegebene Mod lässt die Server-Shader durch.

---

### Key Resolution Protection

Server können in jedem Paket übersetzbaren Text mit Schlüsseln wie `key.attack` oder `key.hide_icons` schicken, um herauszufinden, welche Tasten du belegt hast oder welche Mod-Texte dein Client kennt. Damit lassen sich deine installierten Mods erkennen ([Hintergrund](https://wurst.wiki/sign_translation_vulnerability)).

OpSec verfolgt, wann Übersetzungsschlüssel während der Verarbeitung eines Server-Pakets aufgelöst werden, und blockiert sie je nach Einstellung:

- **Spoof as Vanilla ON** – blockiert alle Mod-Schlüssel und meldet Vanilla-Tastenbelegungen mit ihren Standardwerten
- **Spoof as Vanilla OFF** – erlaubt Schlüssel von Fabric API und freigegebenen Mods, blockiert alles andere

Ist **Fake Default Keybinds** aus, werden Vanilla-Tastenbelegungen mit ihren echten Werten aufgelöst.

**Beispiele**

Mod-Tastenbelegungen (statt der Belegung wird der rohe Schlüssel bzw. der Fallback zurückgegeben):
```
[key.meteor-client.open-commands] '.'→'key.meteor-client.open-commands'
[key.meteor-client.open-gui] 'Right Shift'→'key.meteor-client.open-gui'
```

Vanilla-Tastenbelegungen mit aktivem **Fake Default Keybinds** (es werden die Standardwerte zurückgegeben):
```
[key.hotbar.6] 'Q'→'6'
[key.hotbar.7] 'E'→'7'
[key.hotbar.8] 'R'→'8'
```

---

### Meteor Fix

Ältere Versionen von Meteor Client haben einen eigenen Schutz, der sie zuverlässig erkennbar macht: Ein Server kann eine Abfrage mit Fallback-Wert schicken. Ein Vanilla-Client antwortet mit dem Fallback, Meteor dagegen mit dem rohen Schlüssel.

Was Meteor antwortet:
```
'key.meteor-client.open-gui' 'Right Shift'→'key.meteor-client.open-gui'
```

<img width="847" height="107" alt="Antwort von Meteor" src="https://github.com/user-attachments/assets/e157ae3f-6beb-4823-aca0-9c61573264e2" />

Was ein Vanilla-Client antworten würde:
```
'key.meteor-client.open-gui' '⟦FALLBACK⟧'→'⟦FALLBACK⟧'
```

OpSec deaktiviert Meteors `AbstractSignEditScreenMixin`, sodass der Schutz von OpSec übernimmt und – genau wie Vanilla – mit dem Fallback antwortet.

<img width="901" height="107" alt="Antwort von OpSec" src="https://github.com/user-attachments/assets/506b9c73-6747-40f8-9a56-52c0353034b4" />

---

### ExploitPreventer-Kompatibilität

OpSec und [ExploitPreventer](https://github.com/NikOverflow/ExploitPreventer) können zusammen installiert werden. Ist ExploitPreventer vorhanden, überlässt OpSec ihm die Schutzfunktionen, die beide haben:

- [Spoof as Vanilla](#spoof-as-vanilla) (Brand-Tarnung)
- [Channel Spoofing](#channel-spoofing)
- [Known-Pack Filtering](#known-pack-filtering)
- [Isolate Pack Cache](#isolate-pack-cache)
- [Block Local URLs](#block-local-urls)
- [Key Resolution Protection](#key-resolution-protection)
- [Mod Whitelist](#mod-whitelist)

Diese Einstellungen werden ausgegraut, deine Auswahl bleibt aber gespeichert; entfernst du ExploitPreventer später, sind sie automatisch wieder da. Alles andere bleibt voll aktiv: Warnmeldungen, Chat-Signatur, Account-Manager, Telemetrie-Blockierung, [Strip Mod Shader Overrides](#strip-mod-shader-overrides) und der [Meteor Fix](#meteor-fix). Beachte, dass einige reine OpSec-Details (z. B. das Channel Spoofing von OpSec) nicht genutzt werden, solange ExploitPreventer diesen Bereich übernimmt.

---

### Channel Spoofing

Server können die Netzwerkkanäle auslesen, die deine Mods anmelden, und so erkennen, welche Mods du nutzt. OpSec kann diese Kanäle vor dem Server verstecken. Was versteckt wird, steuern [Spoof as Vanilla](#spoof-as-vanilla) und die [Mod Whitelist](#mod-whitelist).

---

### Known-Pack Filtering

Manche Mods fügen eigene Pack-Kennungen hinzu, über die Server einen modifizierten Client oder bestimmte Mods erkennen können. OpSec fängt die ausgehende Antwort `ServerboundSelectKnownPacks` ab und entfernt Einträge von nicht freigegebenen Mods; Vanilla- und freigegebene Packs bleiben erhalten.

- **Spoof as Vanilla ON** – entfernt alle Mod-Packs
- **Spoof as Vanilla OFF** – behält freigegebene Mod-Packs, entfernt den Rest

> [!NOTE]
> Nur aktiv, wo Fabrics Known-Packs-Schnittstelle vorhanden ist (MC 1.21.11+ mit aktueller Fabric API).

---

### Mod Whitelist

Manche Mods müssen mit dem Server kommunizieren, um zu funktionieren (z. B. Voice-Chat oder die Schnellreise von Xaero's Minimap). Die Whitelist nimmt sie vom Channel Spoofing, der Key Resolution Protection, dem Known-Pack Filtering und dem Entfernen von Shadern aus.

<img width="853" height="478" alt="Whitelist-Einstellungen" src="https://github.com/user-attachments/assets/6ae423de-dd98-47c1-a617-f6df747c9293" />

**Modi:**
- **AUTO** (Standard): Mods, die Netzwerkkanäle anmelden, werden automatisch freigegeben, da sie am wahrscheinlichsten den Server brauchen.
- **CUSTOM**: Du wählst die Mods selbst aus der Liste aller installierten Mods aus.
- **BLOCK ALL**: Alles wird blockiert. Wird automatisch gesetzt, solange [Spoof as Vanilla](#spoof-as-vanilla) aktiv ist, denn ein Vanilla-Client darf keine Mods verraten.

---

### Chat Signing Control

Basiert auf [No Chat Reports](https://modrinth.com/mod/no-chat-reports).

Standardmäßig trägt jede Chatnachricht eine kryptografische Signatur, die sie mit deinem Minecraft-Client und damit mit deinem Microsoft-Account verknüpft.

- **ON** (Standard): Vanilla-Verhalten, jede Nachricht wird signiert.
- **OFF**: Signaturen werden für maximale Privatsphäre entfernt. Auf Servern, die sicheren Chat erzwingen, kannst du dann nicht chatten.

---

### Account Manager

Basiert auf [Meteor Client](https://github.com/MeteorDevelopment/meteor-client).

Füge deine Minecraft-Accounts hinzu und wechsle zwischen ihnen, ohne das Spiel neu zu starten.

- **Session-Token-Login** – Accounts über Access-Tokens hinzufügen
- **Token erneuern** – neuen Session-Token für abgelaufene Accounts holen
- **Offline-Account** – Account nur mit Benutzernamen, ohne Anmeldung
- **Wechseln** – Account anklicken zum Anmelden, erneut klicken, um zum ursprünglichen Account zurückzukehren
- **Prüfen** – über „Refresh“ prüfen, ob Tokens noch gültig sind (abgelaufene werden rot angezeigt)
- **Import/Export** – Accounts als JSON sichern und wiederherstellen

> [!NOTE]
> Session-Tokens laufen nach einiger Zeit ab. Prüfe sie mit **Refresh**.

---

### Telemetry Blocking

Aus [No Chat Reports](https://modrinth.com/mod/no-chat-reports).

Minecraft sendet Telemetriedaten an Mojang, etwa Spielereignisse, Leistungswerte, Client-Einstellungen und Nutzungsstatistiken. Mit aktiver Telemetrie-Blockierung unterbindet OpSec das. Auf das Spielen hat das keine Auswirkung.

## Änderungen im Fork (v1.1.8-fork)

Minecraft 26.3 hat mehrere interne Teile ersetzt, auf die OpSec angewiesen ist. Ältere Minecraft-Versionen werden weiterhin genau wie vorher gebaut (über Stonecutter-Bedingungen).

- **Neues Build-Ziel `26.3`** (Fabric API `0.165.0+26.3`, Mod Menu `21.0.0`, Java 25).
- **authlib 10**: Aus `com.mojang.authlib.yggdrasil` wurde `com.mojang.authlib.services`. Die Telemetrie-Blockierung greift jetzt bei `MinecraftServicesUserApiService`; der Account-Manager erzeugt seine Dienste über `MinecraftServicesDiscoveryService`, so wie Vanilla.
- **GLFW → SDL3**: 26.3 enthält weder GLFW noch LWJGL TinyFD.
  - Account-Import/-Export nutzt auf 26.3 die nativen Dateidialoge von SDL3 (`util/FileDialogs`).
  - Mausabfrage und das Öffnen von Links laufen über `util/PlatformCompat` (auf 26.3 über Minecrafts `MouseHandler` / `Blaze3D.openUri`).
- **Server-Resourcepacks**: `Pack.ResourcesSupplier` nutzt jetzt `openMetadata`/`openResources`; der Pack-Strip-Wrapper wurde angepasst, damit *Bypass Server Pack Requirement* weiter funktioniert.
- **Chat-Signatur**: `ServerboundChatPacket` speichert jetzt ein `Optional<MessageSignature>`; *Chat Signing: OFF* wurde angepasst (sonst ließe sich die Mod nicht laden).
- **Vanilla-Übersetzungsschlüssel**: Auf 26.3 implementiert `VanillaPackResources` nicht mehr `PackResources` (Vanilla-Inhalte kommen jetzt aus `FixedPathPackResources`-Ebenen). Ohne diese Korrektur hätte OpSec Vanilla-Schlüssel unbemerkt nicht mehr erkannt und in Server-Nachrichten blockiert.
- **Update-Prüfung**: Meldet nur noch eine *neuere* Version des Originals, damit der Fork nicht zum „Update“ zurück auf 1.1.7.1 aufgefordert wird.

**Getestet:** Auf 26.3, in einem Client ohne Bildschirm gegen einen lokalen Vanilla-Server 26.3. Alle 31 Mixin-Ziele greifen, und Serverbeitritt, Server-Resourcepacks (MANUAL und ALWAYS ON), Chat mit abgeschalteter Signatur, die Vanilla-Tarnung sowie Schlüssel-Abfragen (Vanilla-, Mod-, Tastenbelegungs- und Server-Pack-Schlüssel) verhalten sich wie erwartet.

## Aus dem Quellcode bauen

### Voraussetzungen

- **Java 25** (damit lassen sich alle Versionen bauen); alternativ Java 17 (1.20.1 – 1.20.4), Java 21 (1.20.6 – 1.21.11), Java 25 (26.1+)
- **Gradle** (über den Wrapper enthalten)

### Bauen

1. **Dieses Repository klonen** (grüner **Code**-Knopf → URL kopieren)
   ```bash
   git clone <repository-url>
   cd <repository-ordner>
   ```

2. **Alle Versionen bauen**
   ```bash
   # Windows
   .\gradlew.bat buildAll

   # Linux/Mac
   ./gradlew buildAll
   ```

3. **Eine einzelne Version bauen**
   ```bash
   ./gradlew :26.3:build
   ```
   Verfügbare Ziele: `1.20.1`, `1.20.2`, `1.20.4`, `1.20.6`, `1.21.1`, `1.21.4`, `1.21.6`, `1.21.9`, `1.21.11`, `26.1`, `26.2`, `26.3`

Die fertigen JAR-Dateien liegen in `versions/<ziel>/build/libs/`:

| Ziel | Unterstützt |
|------|-------------|
| 1.20.1 | 1.20 – 1.20.1 |
| 1.20.2 | 1.20.2 |
| 1.20.4 | 1.20.3 – 1.20.4 |
| 1.20.6 | 1.20.5 – 1.20.6 |
| 1.21.1 | 1.21 – 1.21.1 |
| 1.21.4 | 1.21.2 – 1.21.5 |
| 1.21.6 | 1.21.6 – 1.21.8 |
| 1.21.9 | 1.21.9 – 1.21.10 |
| 1.21.11 | 1.21.11 |
| 26.1 | 26.1 – 26.1.2 |
| 26.2 | 26.2 |
| 26.3 | 26.3 |

## Danksagungen & Quellen

- **[Aurick](https://github.com/aurickk/OpSec)** – Entwickler des ursprünglichen OpSec
- [ExploitPreventer](https://github.com/NikOverflow/ExploitPreventer) – Blockieren lokaler URLs und Key Resolution Protection
- [LiquidBounce](https://github.com/CCBlueX/LiquidBounce/blob/nextgen/src/main/java/net/ccbluex/liquidbounce/injection/mixins/minecraft/util/MixinDownloadQueue.java) – getrennter Speicher für Server-Resourcepacks
- [Meteor Client](https://github.com/MeteorDevelopment/meteor-client) – Session-Token-Login
- [No Chat Reports](https://modrinth.com/mod/no-chat-reports) – Chat-Signatur und Telemetrie-Blockierung
- [No Prying Eyes](https://github.com/Daxanius/NoPryingEyes) – Erkennung erzwungenen sicheren Chats
- [MixinSquared](https://github.com/Bawnorton/MixinSquared) – Abschalten von Mixins für den Meteor Fix
- [Stonecutter](https://stonecutter.kikugie.dev/) – Build-System für mehrere Versionen
- [Fabric API](https://github.com/FabricMC/fabric-api) – Fabric-Übersetzungs- und Tastenbelegungsschlüssel

## Lizenz

GPL-3.0 – siehe [LICENSE](LICENSE). Dieser Fork ist eine veränderte Version des ursprünglichen OpSec; die Änderungen stehen unter [Änderungen im Fork](#änderungen-im-fork-v118-fork).

## Haftungsausschluss

OpSec ist ein Werkzeug für die Privatsphäre, das Spieler vor unerwünschtem Client-Fingerprinting und Tracking schützt. Es ist nicht dafür gedacht, Serverregeln zu umgehen, Banns zu entgehen oder sich unfaire Vorteile zu verschaffen. Du bist selbst dafür verantwortlich, die Regeln und Nutzungsbedingungen jedes Servers einzuhalten, auf dem du spielst.
