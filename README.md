# RentalAid 

## Zespół

*  **Szymon Kodura** - Team Leader
*  **Albert Starosta** - Specjalista
*  **Marcin Będkowski** - Specjalista

---

# Wizja systemu

RentalAid to system wspierający zarządzanie wypożyczalnią sprzętu sportowego.

Rozwiązanie składa się z aplikacji desktopowej wykorzystywanej przez pracowników wypożyczalni, oprogramowania serwerowego odpowiedzialnego za zarządzanie danymi oraz aplikacji webowej i mobilnej przeznaczonej dla klientów.

System umożliwia:

* zarządzanie procesem wydawania i zwrotu sprzętu,
* ewidencjonowanie klientów wypożyczalni,
* monitorowanie stanu magazynowego,
* przechowywanie historii wypożyczeń,
* obsługę rezerwacji sprzętu,
* zarządzanie pracownikami i administracją systemu,
* automatyczne powiadamianie klientów o terminach zwrotu.

## Dla kogo przeznaczony jest system?

System RentalAid został zaprojektowany dla:

* administratorów systemu,
* pracowników obsługi klienta,
* magazynierów,
* klientów wypożyczalni sprzętu sportowego.

## Jakie potrzeby zaspokaja?

System wspiera pełną obsługę procesu wypożyczeń poprzez:

* rejestrowanie danych klientów,
* zapisywanie dat wydania i zwrotu sprzętu,
* przechowywanie informacji o kosztach wypożyczenia,
* kontrolowanie terminów zwrotów,
* monitorowanie dostępności sprzętu,
* prowadzenie historii aktywnych i zakończonych wypożyczeń,
* przypominanie klientom o zbliżającym się terminie zwrotu,
* informowanie o opóźnieniach w oddaniu sprzętu.

## Jakie możliwości wykorzystuje?

System wykorzystuje:

* istniejącą infrastrukturę komputerową wypożyczalni,
* centralną bazę danych,
* dostęp do Internetu,
* powszechne wykorzystanie smartfonów przez klientów,
* komunikację za pomocą aplikacji mobilnej, aplikacji webowej oraz powiadomień.

## Jakie korzyści przynosi?

RentalAid zapewnia:

* sprawną dokumentację wypożyczeń,
* pełną kontrolę nad stanem magazynu,
* ograniczenie błędów wynikających z ręcznego prowadzenia ewidencji,
* szybszą obsługę klientów,
* usprawnienie procesu inwentaryzacji sprzętu,
* wygodniejszą komunikację pomiędzy wypożyczalnią a klientami,
* dostęp do historii wypożyczeń i płatności.

## Dlaczego ten system?

Wiele wypożyczalni nadal opiera swoją działalność na:

* dokumentacji papierowej,
* arkuszach kalkulacyjnych,
* komunikacji telefonicznej,
* ręcznym monitorowaniu stanów magazynowych.

Powoduje to:

* wysokie ryzyko błędów ludzkich,
* trudności w kontroli dostępności sprzętu,
* czasochłonną obsługę klientów,
* problemy z monitorowaniem terminów zwrotu,
* utrudnioną komunikację z klientami.

RentalAid eliminuje te problemy poprzez centralizację danych i automatyzację najważniejszych procesów związanych z obsługą wypożyczalni sprzętu sportowego.

## Jakie są alternatywy?

Alternatywą dla systemu RentalAid jest kontynuowanie prowadzenia dokumentacji papierowej lub korzystanie z prostych narzędzi biurowych, takich jak arkusze kalkulacyjne. Rozwiązania te są jednak bardziej podatne na błędy, wymagają większego nakładu pracy oraz utrudniają skuteczne zarządzanie magazynem i obsługę klientów.


## Dlaczego ten system?

Obecnie wiele wypożyczalni korzysta z:

* arkuszy kalkulacyjnych,
* dokumentacji papierowej,
* rozmów telefonicznych,
* wiadomości e-mail.

Powoduje to:

* trudności w kontroli dostępności sprzętu,
* błędy w ewidencji wypożyczeń,
* problemy z komunikacją z klientami,
* brak aktualnych informacji o stanie magazynu.

RentalAid eliminuje te problemy poprzez centralny system zarządzania wypożyczalnią.

---

# Część 1

## Analiza potrzeb użytkowników

Na podstawie przeprowadzonych wywiadów i analizy wymagań wyróżniono dwie główne grupy użytkowników:

* Klienci
* Pracownicy obsługi klienta

### Kluczowe wymagania użytkowników

#### Obsługa klienta

* dodawanie nowych klientów do systemu,
* potwierdzanie wydania oraz zwrotu sprzętu,
* przeglądanie bazy sprzętu według statusu dostępności,
* dodawanie notatek dotyczących uszkodzeń sprzętu,
* rozpatrywanie wniosków o przedłużenie wypożyczenia.

#### Magazyn

* potwierdzanie wydania i odbioru sprzętu,
* zgłaszanie potrzeby naprawy lub renowacji sprzętu.

#### Klienci

* rejestracja konta użytkownika,
* przeglądanie dostępnego sprzętu,
* rezerwacja sprzętu,
* zarządzanie własnymi rezerwacjami,
* składanie wniosków o przedłużenie wypożyczenia,
* przegląd historii wypożyczeń i płatności,
* otrzymywanie powiadomień o terminie zwrotu.

#### Administracja

* zarządzanie pracownikami,
* zarządzanie bazą sprzętu,
* edycja cenników usług,
* konfiguracja kar za opóźnienia,
* blokowanie kont klientów.

## Podsumowanie części 1

Na podstawie zebranych wymagań przygotowano historyjki użytkowników oraz podzielono funkcjonalności na epiki. Dla najważniejszych funkcji opracowano kryteria akceptacji.

---

# Część 2

W tej części projektu skupiono się na doprecyzowaniu wymagań funkcjonalnych oraz przygotowaniu scenariuszy testowych i modeli procesów biznesowych.

## Kryteria akceptacji i scenariusze testowe

Dla wszystkich kluczowych funkcjonalności przygotowano:

* kryteria akceptacji,
* scenariusze testowe w formacie Given–When–Then (GWT).

Zakres obejmuje między innymi:

* rejestrację klienta,
* rezerwację sprzętu,
* wydanie sprzętu,
* zwrot sprzętu,
* zgłaszanie uszkodzeń,
* przedłużanie wypożyczeń.

## Diagramy aktywności

Opracowano diagramy aktywności dla następujących procesów:

* proces rezerwacji sprzętu,
* proces wydania sprzętu klientowi,
* proces zwrotu sprzętu,
* obsługa wniosku o przedłużenie wypożyczenia,
* zgłaszanie sprzętu do naprawy.

## Interaktywny prototyp interfejsu

Przygotowano prototyp interfejsu w wersji low-fi obejmujący:

* logowanie użytkownika,
* rejestrację konta,
* przeglądanie katalogu sprzętu,
* składanie rezerwacji,
* zarządzanie wypożyczeniami,
* panel administracyjny.

## Podsumowanie części 2

Przygotowane scenariusze testowe oraz diagramy procesów umożliwiły weryfikację poprawności wymagań biznesowych. Dodatkowo wykonano prototyp interfejsu pozwalający na ocenę użyteczności systemu przez przyszłych użytkowników.

---

# Część 3

W tej części projektu przeprowadzono badania użyteczności interfejsu oraz opracowano docelowy projekt UI/UX. Powstała również dokumentacja architektoniczna systemu.

## Badania interfejsu

Podczas testów użytkownicy wskazali następujące problemy:

* trudności z odnalezieniem historii wypożyczeń,
* niewystarczającą widoczność informacji o terminach zwrotu,
* brak jasnych komunikatów po wykonaniu operacji,
* nieintuicyjne zarządzanie rezerwacjami,
* potrzebę wprowadzenia trybu ciemnego.

## Prototyp UI

Na podstawie zebranych opinii opracowano nową wersję interfejsu uwzględniającą:

* uproszczoną nawigację,
* bardziej czytelny katalog sprzętu,
* widoczne komunikaty systemowe,
* poprawiony proces rezerwacji,
* wsparcie dla trybu ciemnego.

## Diagramy sekwencji

Diagramy sekwencji obejmują:

* logowanie użytkownika,
* rejestrację klienta,
* rezerwację sprzętu,
* wydanie sprzętu,
* zwrot sprzętu,
* zgłoszenie uszkodzenia sprzętu,
* rozpatrywanie wniosku o przedłużenie wypożyczenia.

## Diagramy klas

Na podstawie wymagań oraz projektu interfejsu przygotowano diagramy klas obejmujące:

* użytkowników systemu,
* sprzęt,
* rezerwacje,
* wypożyczenia,
* płatności,
* powiadomienia,
* pracowników,
* administrację systemu.

Diagramy przedstawiają relacje pomiędzy obiektami oraz strukturę domenową aplikacji.

## Architektura systemu

Przygotowano schemat architektury obejmujący:

* warstwę prezentacji,
* warstwę logiki biznesowej,
* warstwę dostępu do danych,
* bazę danych,
* moduł powiadomień.

---

# Część 4

W tej części projektu rozpoczęto implementację systemu zgodnie z wcześniej przygotowaną dokumentacją projektową.

Implementacja wraz z testami znajduje się w folderze `backend`.

## Struktura projektu

backend/

│

├── src/

   ├── main/java/model
   
   ├── test/java/model

└── pom.xml

## Uruchomienie

### Wymagania

* Java 17+
* Maven 3.9+


### Maven

```bash
cd backend
mvn test
```

## Testy

Projekt zawiera testy jednostkowe


# Raport stanu prac

## Spotkanie 1

### DONE

* Strona tytułowa – *Szymon Kodura*
* Wizja systemu – *Szymon Kodura, Albert Starosta, Marcin Będkowski*

### TODO

* Zebranie wymagań użytkowników
* Opracowanie historyjek użytkownika
* Przygotowanie epików

## Spotkanie 2

### DONE

    * Wymagania użytkowników – *Szymon Kodura, Albert Starosta, Marcin Będkowski*
    * Historyjki – *Albert Starosta, Marcin Będkowski*

### TODO

* Badania użytkowników
* Kryteria akceptacji
* Scenariusze testowe GWT
* Diagramy aktywności
* Prototyp low-fi

## Spotkanie 3

### DONE
    * Raport z badania interfejsu w gronie użytkowników – *Szymon Kodura, Albert Starosta*
    * Kompletne kryteria akceptacji i scenariusze testowe – *Albert Starosta, Marcin Będkowski*
    * Flow użytkownika (diagramy aktywności) – *Szymon Kodura, Albert Starosta, Marcin Będkowski*
    * Interaktywny prototyp interfejsu (low-fi) – *Szymon Kodura*
### TODO

* Projekt UI
* Diagramy klas
* Diagramy sekwencji

## Spotkanie 4

### DONE
    * Finalizacja prototypu UI – *Albert Starosta*
    * Diagramy klas – *Marcin Będkowski*
    * Diagramy sekwencji – *Szymon Kodura*

### TODO

* Implementacja backendu
* Testy jednostkowe
* Prezentacja projektu
* Dokumentacja końcowa

## Spotkanie 5

### DONE

    * Implementacja projektu (backend wraz z testami jednostkowymi). - *Szymon Kodura, Albert Starosta*
    * Przygotowanie prezentacji projektu. - *Szymon Kodura, Albert Starosta, Marcin Będkowski*
    * Skompletowanie dokumentacji końcowej. - *Szymon Kodura, Marcin Będkowski*

### Status projektu

✅ Analiza wymagań zakończona

✅ Projekt UI/UX ukończony

✅ Diagramy stworzone

✅ Implementacja backendu i testów jednostkowych

✅ Projekt gotowy do dalszego rozwoju
