## 🇬🇧 OpSec New for Minecraft v1.1.9-fork

**Download:** pick the jar for your Minecraft version below (for 26.3: `opsec-26.3+v1.1.9-fork.jar`) and put it into `.minecraft/mods` together with Fabric API. Remove older OpSec jars first.

**Highlights**
- **Works perfectly together with ExploitPreventer** (tested with EP 1.2.1): each protection is handled by exactly one mod and both share OpSec's mod whitelist.
  - Fixed: server resource pack language keys were not resolved when both mods were installed (servers could detect this).
  - Fixed: *Spoof as Vanilla*, channel spoofing, known-pack filtering, *Bypass Server Pack Requirement*, the whitelist and *Fake Default Keybinds* were switched off with EP installed — they now stay active.
- Improvements merged from OpSec Community Edition (tufkan1): better attribution of channels and language files to their mods, longer session tokens and launcher-JSON paste in the account manager.
- Every push is now built automatically, and releases are published by GitHub Actions.

**Requirements:** Fabric Loader 0.19.3+ (26.3), Fabric API for your version, Java 25 for 26.x.

Full list of changes: see "Fork changes" in the README.

---

## 🇩🇪 OpSec New for Minecraft v1.1.9-fork

**Download:** Lade unten die JAR für deine Minecraft-Version herunter (für 26.3: `opsec-26.3+v1.1.9-fork.jar`) und lege sie zusammen mit der Fabric API in `.minecraft/mods`. Ältere OpSec-Dateien vorher löschen.

**Neuerungen**
- **Läuft perfekt zusammen mit ExploitPreventer** (getestet mit EP 1.2.1): Jede Schutzfunktion übernimmt genau eine Mod, und beide nutzen OpSecs Mod-Whitelist.
  - Behoben: Sprachschlüssel von Server-Resourcepacks wurden mit beiden Mods nicht aufgelöst (Server konnten das erkennen).
  - Behoben: *Spoof as Vanilla*, Channel Spoofing, Known-Pack Filtering, *Bypass Server Pack Requirement*, die Whitelist und *Fake Default Keybinds* waren mit EP abgeschaltet – sie bleiben jetzt aktiv.
- Verbesserungen aus OpSec Community Edition (tufkan1) übernommen: bessere Zuordnung von Kanälen und Sprachdateien zu ihren Mods, längere Session-Tokens und Einfügen von Launcher-JSON im Account-Manager.
- Jeder Push wird jetzt automatisch gebaut, und Releases veröffentlicht GitHub Actions.

**Voraussetzungen:** Fabric Loader 0.19.3+ (26.3), Fabric API für deine Version, Java 25 für 26.x.

Alle Änderungen: siehe „Änderungen im Fork“ in der README.
