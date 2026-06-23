# Raport Końcowy z Ostatniego Sprintu

## 1. Podsumowanie Sprintu
Niniejszy dokument stanowi podsumowanie prac zrealizowanych w ramach ostatniego sprintu projektu systemu wypożyczalni. Głównym celem tego etapu było sfinalizowanie prac programistycznych nad warstwą backendową, zapewnienie wysokiej jakości kodu poprzez testy jednostkowe, a także przygotowanie kompletnej dokumentacji oraz prezentacji końcowej projektu. Wszystkie zaplanowane zadania zostały pomyślnie ukończone, co pozwala na zamknięcie bieżącej iteracji z pełnym sukcesem.

## 2. Status Realizacji Zadań (Zrobiono)
W trakcie trwania sprintu zespół skoncentrował się na trzech kluczowych obszarach:
* **Implementacja projektu** – pełne wdrożenie logiki biznesowej po stronie backendu wraz z pakietem testów jednostkowych.
* **Przygotowanie prezentacji projektu** – opracowanie materiałów wizualnych i merytorycznych na potrzeby demonstracji końcowej dla interesariuszy.
* **Skompletowanie dokumentacji końcowej** – zebranie, zredagowanie i sfinalizowanie wszystkich dokumentów technicznych oraz użytkownika.

## 3. Szczegółowy Opis Implementacji i Testów
Zgodnie z wymaganiami technologicznymi i architektonicznymi, prace nad kodem źródłowym oraz weryfikacją jego poprawności zaowocowały następującymi elementami:
* **Implementacja klas z diagramu architektonicznego:**
  * Odzwierciedlenie struktury obiektowej w języku Java dla wszystkich komponentów systemu.
  * Zaimplementowanie pełnej logiki biznesowej, pól, metod, getterów, setterów oraz relacji między obiektami zgodnie z zatwierdzonym diagramem klas.
  * Zapewnienie czystości kodu (Clean Code) oraz zgodności ze standardami programowania obiektowego.
* **Opracowanie testów jednostkowych (Unit Tests):**
  * Stworzenie dedykowanych klas testowych dla każdej zaimplementowanej klasy biznesowej.
  * Wykorzystanie popularnych bibliotek testowych (np. JUnit / AssertJ) w celu weryfikacji poprawności działania metod, obsługi wyjątków oraz przypadków brzegowych.
  * Uzyskanie wysokiego poziomu pokrycia kodu testami (Code Coverage), co minimalizuje ryzyko wystąpienia regresji w przyszłości.
* **Utworzenie pliku demonstracyjnego `DemoWypozyczalnia.java`:**
  * Przygotowanie wykonywalnej klasy z metodą `main`, służącej jako prezentacja scenariuszy uruchomieniowych.
  * Zasymulowanie pełnego cyklu życia obiektów w systemie wypożyczalni (np. rejestracja użytkownika, dodanie zasobu do bazy, proces wypożyczenia, zwrot oraz kalkulacja kosztów).
  * Zapewnienie czytelnych komunikatów na konsoli, ilustrujących poprawne działanie poszczególnych funkcjonalności backendu.
* **Konfiguracja i strukturyzacja projektu w systemie Maven:**
  * Oparcie architektury budowania projektu o standard narzędzia Apache Maven.
  * Skonfigurowanie pliku `pom.xml` z uwzględnieniem wszystkich niezbędnych zależności (dependencies), w tym bibliotek do testowania.
  * Zdefiniowanie odpowiednich wtyczek (plugins) odpowiedzialnych za kompilację kodu (Java Compiler) oraz automatyczne uruchamianie testów (Surefire Plugin).
  * Zapewnienie standardowej struktury katalogów (`src/main/java`, `src/test/java`), co ułatwia zarządzanie kodem i automatyzację procesu budowania (Build Automation).

## 4. Podział Prac i Odpowiedzialność Zespołu
Prace w tym sprincie zostały rozdzielone pomiędzy członków zespołu w następujący sposób:

| Pracownik | Obszar odpowiedzialności | Szczegóły zadań |
| :--- | :--- | :--- |
| **Szymon Kodura** | Implementacja, Prezentacja, Dokumentacja | Kodowanie backendu, testy jednostkowe, współtworzenie slajdów prezentacji oraz kompletowanie dokumentacji końcowej. |
| **Albert Starosta** | Implementacja, Prezentacja | Kodowanie backendu, pisanie testów jednostkowych, przygotowanie demonstracji działania aplikacji na prezentację. |
| **Marcin Będkowski** | Prezentacja, Dokumentacja | Opracowanie struktury prezentacji projektu, agregacja dokumentów technicznych i przygotowanie końcowych wniosków. |

## 5. Wnioski i Podsumowanie Końcowe
Wszystkie postawione cele sprinterskie zostały zrealizowane w stu procentach. Dzięki pełnemu wykorzystaniu systemu Maven automatyzacja budowania i testowania przebiega bezproblemowo i powtarzalnie. Przygotowany plik `DemoWypozyczalnia.java` w jasny sposób dowodzi, że zaimplementowane klasy działają spójnie i w pełni realizują założone funkcje biznesowe. Całość materiałów (kod, testy, prezentacja, dokumentacja) jest gotowa do oficjalnego oddania.
