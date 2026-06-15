# Klient

## Jako klient chcę móc się zarejestrować do systemu aby móc korzystać z usług wypożyczalni

Kryteria funkcjonalne:
- System udostępnia formularz rejestracji klienta
- Formularz rejestracji zawiera obligatoryjne pola:
  - imię
  - nazwisko
  - unikalny identyfikator (PESEL/Nr innego dokumentu potwierdzającego tożsamość)
  - adres email
  - hasło oraz jego potwierdzenie
- Formularz zawiera nieobligatoryjne pola:
  - numer kontaktowy
  - adres do korespondencji pocztowej
- Pola akceptują tylko poprawne formaty danych, pola z hasłem są zakropkowane
- System weryfikuje e-mail poprzez wysłanie na niego wiadomości z kodem weryfikacyjnym
- Po zweryfikowaniu adresu e-mail system zapisuje dane klienta w bazie danych
- Przy zapisie system generuje unikalny indentyfiaktor klienta w bazie danych
- Po zweryfikowaniu adresu e-mail możliwe jest zalogowanie, użytkownik jest przekierowany na stronę logowania

Kryteria pozafunkcjonalne:
- Dane użytkownika są szyfrowane
- E-mail do użytkownika jest wysyłany w ciągu 3 s od kliknięcia "Załóż konto" (w 95% przypadków)
- Zapis klienta do bazy danych odbywa się w ciągu co najwyżej 2 sekund (w 95% przypadków)

### Scenariusze testowe
**Poprawna rejestrcja**

**Given:** Urzytkownik nie posiada konta

**And:** Urzytkownik wprowadził dane zgodnie z formatami

**And:** Urzytkownik wprowadził hasło oraz je potwierdziła

**When:** Urzytkownik naciska przycisk zweryfikuj e-mail

**Then:** System wysyła e-mail weryfikujący na adres użytkownika, a urzytkownik jest przekierowany na stronę z kodem

**Weryfikacja e-mail**

**Given:** Urzytkownik otrzymał e-mail weryfikacyjny

**When:** Urzytkownik wprowadza kod na stronie z weryfikacją

**Then:** Konto urzytkownika zostaje zapisane w bazie, a urzytkownik zostaje przekierowany na stronę z logowaniem

**Rejestracja z niepypełnionymi polami**

**Given:** Urzytkownik wprowadził nie wprowadził jakichś obligarotyjnych danych

**When:** Urzytkownik naciska przycisk zweryfikuj e-mail

**Then:** System zaznacza obligatoryjne pola i wyświetla kimunikat o tym, że aby kontynuować należy je wypelnić

**Rejestracja z różnymi hasami**

**Given:** Urzytkownik wprowadził dane zgodnie z formatami

**And:** Urzytkownik wprowadził dwa różne hasła

**When:** Urzytkownik naciska przycisk zweryfikuj e-mail

**Then:** System informuje użytkownika o tym, że hasła są różne

**Rejestracja mimo posidania konta**

**Given:** Urzytkownik posiada już konto na dany e-mail

**And:** Urzytkownik wprowadził dane zgodnie z formatami

**And:** Urzytkownik wprowadził hasło oraz je potwierdziła

**When:** Urzytkownik naciska przycisk zweryfikuj e-mail

**Then:** System wyświetla komunikat o tym, że konto na dany e-mail już istnieje i przekierowuje na stronę logowania

  

## Jako klient chcę móc się zalogować do systemu aby móc korzystać z usług wypożyczalni

Kryteria funkcjonalne: 
- Strona logowania wyświetla pola na e-mail oraz hasło
- Pole e-mail akceptuje tylko poprawne formaty, a pole hasła jest zakropkowane
- Strona logowania ma opcję "Zapominałem hasła", która wysyła do użytkownika e-mail z numerem weryfikacyjnym, po jego wprowadzeniu możliwe jest ponowne wybranie hasła
- Po zalogowaniu użytkownik przekierowywany jest do punktu nawigacyjnego
- Po 5 nieprawidłowych próbach logowania urzytkownik musi odczekać 5 min przed koleją próbą logowania (przed każdą następną też)

Kryteria pozafunkcjonalne:
- E-mail i hasło są szyfrowane
- E-mail do użytkownika jest wysyłany w ciągu 3 s od kliknięcia "Zapomniałem hasła" (w 95% przypadków)

### Scenariusze testowe

**Poprawne logowanie**

**Given** Urzytkownik wprowadził istniejący e-mail oraz poprawne hasło

**When** Urzytkownik naciska przycisk zaloguj

**Then** Urzytkownik zostaje zalogowany i przekierowany do punktu nawigacyjnego

**Próba logogowania z nieprawidłowym hasłem (próby < 5)**

**Given** Urzytkownik wprowadził istniejący e-mail oraz niepoprawne hasło

**And:** Liczba prób wprowadzenia hasła wynosi mniej niż 5

**When** Urzytkownik naciska przycisk zaloguj

**Then** Urzytkownik zostaje powiadomiony, że hasło jest nieprawidłowe

**Próba logogowania z nieprawidłowym hasłem (próby > 5)**

**Given** Urzytkownik wprowadził istniejący e-mail oraz niepoprawne hasło

**And:** Liczba prób wprowadzenia hasła wynosi więcej niż 5

**When** Urzytkownik naciska przycisk zaloguj

**Then** Urzytkownik zostaje powiadomiony, że hasło jest nieprawidłowe, oraz że przekroczył dopuszczalną ilość prób i musi odczekać 5 min

**Próba logogowania z nieistniejącym e-mailem**

**Given** Urzytkownik wprowadził nieistniejący e-mail oraz hasło

**When** Urzytkownik naciska przycisk zaloguj

**Then** Urzytkownik zostaje powiadomiony, że dany login nie istnieje

**Próba logogowania z nieistniejącym e-mailem**

**Given** Urzytkownik wprowadził nieistniejący e-mail oraz hasło

**When** Urzytkownik naciska przycisk zaloguj

**Then** Urzytkownik zostaje powiadomiony, że nie ma konta na taki e-mail i sugeruje rejestrację

## Jako klient chcę przeglądać listę wypożyczonego przeze mnie sprzętu wraz z informacją o terminie zwrotu, aby umiejętnie zagospodarować czas przeznaczony na wykorzystanie sprzętu

Kryteria funkcjonalne:
- System wyświetla listę wypożyczonego sprzętu posortowaną według czasu pozostałego do terminu zwrotu
- W przypadku przekroczenia terminu wyświetlana jest o tym informacja
- Istnieje możliwość zmiany sortowania, np. alfabetyczne
- System umożliwia filtrowanie sprzętu według według daty wypożyczenia oraz terminu zwrotu
- System umożliwia filtrowanie sprzętu według kategorii (np. sporty zimowe, wodne itd.)
- System udostępnia następujące klasy kategorii i podkategorii:
  - tematyczne:
    - sporty zimowe
      - narciarstwo
      - snowboarding
      - ...
    - sporty wodne
      - kajakarstwo
      - wędkowanie
      - nurkowanie
      - ...
    - kolarstwo
    - ...
  - dostępność:
    - dostępny
    - wypożyczony
    - w naprawie
- System wyświetla dodatkowe informacje o sprzęcie (nazwa, marka, opis, data wypożyczenia, termin oddania, ...)
- W przypadku przekroczenia terminu wyświetlana jest również informacjo o dodatkowej opłacie

Kryteria pozafunkcjonalne:
- Opcja przeglądania sprzętu jest dostępna 24/7, czas niedostępności nie przekracza łącznie 1 dnia miesięcznie
- Po wydaniu albo zdaniu sprzętu lista wypożyczonego sprzętu jest aktualizowana w ciągu 10 s od wprowadzenia informacji do systemu (w 95% przypadków)

### Scenariusze testowe

**Sortowanie według wybranych kryteriów**

**Given:** Lista wyporzyczonych sprzętów jest otwarta

**When:** Urzytkowenik wybiera sortowanie, np. wedlug daty wypożyczenia malejąco

**Then:** System wyświetla wypożyczone sprzęty w wybranej kolejności

**Filtrowanie według wybranych krytriów**

**Given:** Lista wyporzyczonych sprzętów jest otwarta

**When:** Urzytkowenik wybiera filtrowanie sprzętów do sportów wodnych 

**Then:** System wyświetla sprzęty pasujące do kryteriów

**Wyszukiwanie**

**Given:** Lista wyporzyczonych sprzętów jest otwarta

**When:** Urzytkowenik wyszukuje frazę kajak 

**Then:** System wyświetla sprzęty w których nazwie znaleziono "kajak"

**Sprawdzanie dodatkowych informacji o sprzęcie**

**Given:** Lista wyporzyczonych sprzętów jest otwarta

**When:** Urzytkowenik wybiera sprzęt z listy 

**Then:** System wyświetla dodatkowe informacje o sprzęcie (nazwa, marka, opis, data wyporzyczenia, termin oddania, ew. opłata wynikajaca z przekroczenia terminu)

## Jako klient chcę wnioskować o przedłużenie wypożyczenia, aby móc elastycznie dostosowywać jego czas do zachodzących potrzeb oraz okazji wykorzystania

Kryteria funkcjonalne:
- System wyświetla listę wypożyczonych sprzętów i umożliwia zaznaczanie ich
- System umożliwia filtrowanie, wyszukiwanie i sortowanie tak samo jak w przypadku listy wypożyczonego sprzętu
- Po zaznaczeniu co najmniej jednego sprzętu i kliknięciu przycisku "Dalej" system umożliwia wybranie nowej daty zwrotu sprzętu
- Po wybraniu nowej daty, jeżeli sprzęt nie jest zarezerwowany, system umożliwia naciśnięcie przycisku "Wyślij wniosek", w przeciwnym razie wyświetlany jest odpowiedni komunikat
- Po kliknięciu "Wyślij wniosek" do operatora wypożyczeń wysyłany jest komunikat
- Jeżeli wniosek został zatwierdzony klient otrzymuje komunikat z potwierdzeniem oraz jest zobligowany do zapłaty
- Płatność można zrealizować od razu przy odebraniu potwierdzenia albo w dowolnym czasie przed nowym okresem.
- Jeżeli płatność nie zostanie zrealizowana za nieopłacony czas jest naliczania opłata tak jak za opóźnienie w oddaniu

Kryteria pozafunkcjonalne:
- Po wysłaniu wniosku komunikat trafia do operatora w ciągu 10 s (w 95% przypadków)
- Odpowiedź trafia do klienta w ciągu 10 s od wysłania (w 95% przypadków)

### Scenariusze testowe

**Poprawne wysłanie wniosku**

**Given:** System wyświetla dodatkowe informacje o sprzęcie (scenariusze testowe do "Jako klient chcę przeglądać listę wypożyczonego przeze mnie sprzętu wraz z informacją o terminie zwrotu, aby umiejętnie zagospodarować czas przeznaczony na wykorzystanie sprzętu")

**And** Dany sprzęt nie jest zarezerwowany

**When:** Użytkownik naciska przycisk przedłuż termin oddania

**Then** Do operatora wysyłane jest powiadonienie o wniosku czekającym na rozpatrzenie

**Próba złorzenia wniosku zakończona niepowodzeniem**

**Given:** System wyświetla dodatkowe informacje o sprzęcie

**And** Dany sprzęt jest zarezerwowany

**When:** Użytkownik naciska przycisk przedłuż termin oddania

**Then** System wyświetla informację o tym, że w danym terminie sprzęt jest zarezerwowany i sugeruje wybranie innej daty

**Wniosek rozpatrzony pozytywnie**

**Given:** Do operatora został wysłany wniosek o przedłużenie terminu oddania sptrzętu

**When:** Operator pozytywnie rozpatruje wniosek

**Then** Do użytkownika wysłany zostaje e-mail z informacją o pozytywnym rozparzeniu wniosku, oraz dodatkowych opłatach. W punkcie nawigacyjnym systemu pojawia się opcja dokonania płatności

**Wniosek rozpatrzony negatywnie**

**Given:** Do operatora został wysłany wniosek o przedłużenie terminu oddania sptrzętu

**When:** Operator negatywnie rozpatruje wniosek

**Then** Do użytkownika wysłany zostaje e-mail z informacją o negetywnym zozpatrzeniu wniosku oraz przypomnienie o terminie oddania


## Jako klient chcę dokonać płatności za wypożyczenie sprzętu za pośrednictwem platformy udostępnianej przez wypożyczalnię, aby zachować historię płatności w ramach tej platformy

Kryteria funkcjonalne:
- Podczas etapu dokonywania płatności przy wypożyczaniu sprzętu jedną z opcji jest płatność kontem Rental-aid
- Konto Rental-aid można doładowywać w aplikacji albo na stronie internetowej
- W opcjach konta użytkownika można zobaczyć historię płatności za wypożyczone sprzęty

Kryteria pozafunkcjonalne:
- Dla każdego użytkownika generowane jest konto w banku \<Nazwa Banku\>
- Poprawnością i bezpieczeństwem przelewów z i na konto zajmuje się \<Nazwa Banku\>

### Scenariusze testowe

**Przelew na konto Rental-aid**

**Given** Użytkownik posiada konto w systemie

**When** Użytkownik przelewa środki na konto Renta-aid

**Then** Środki są widoczne na stronie oraz w aplikacji mobilnej

**Zapłata za wyporzyczenie sprzętu przy użyciu konta Renta-aid**

**Given** Użytkownik posiada konto w systemie

**And** Użytkownik wybrał konto rental-aid jako opcję płatności

**And** Użytkownik posiada odpowidnią ilość środków na koncie

**When** Naciska przycisk zapłać

**Then** Wyporzyczenie zostaje opłacone, na koncie Rental-aid jest mniej środków

**Próba zapłaty za wyporzyczenie sprzętu przy użyciu konta Renta-aid baz odpowiednich środków**

**Given** Użytkownik posiada konto w systemie

**And** Użytkownik wybrał konto rental-aid jako opcję płatności

**And** Użytkownik nie posiada odpowidniej ilości środków na koncie

**When** Naciska przycisk zapłać

**Then** Wyporzyczenie pozostaje nieopłacone, system wyświetla informację o braku odpowiednich środków na koncie

## Jako klient chcę przeglądać dostępne w magazynie wyposażenie do wypożyczenia, aby móc w wyprzedzeniem określić swoje zamiary w kwestii wypożyczenia lub zarezerwować sprzęt <!--lub wypożyczyć--> (z wydaniem sprzętu w punkcie stacjonarnym) 
<!-- Jaka jest różnica miedzy wypożyczeniem a rezerwowaniem? -->

Kryteria funkcjonalne:
- System wyświetla listę dostępnego sprzętu
- System umożliwia filtrowanie, wyszukiwanie i sortowanie tak samo jak w przypadku listy wypożyczonego sprzętu
- Dodatkowo system umożliwia sortowanie sprzętu wg ceny wypożycznia na jeden dzień, popularności danego modelu sprzętu
- System wyświetla dodatkowe informacje o sprzęcie (nazwa, marka, opis, ilość dostępnych sztuk)
- Istnieje możliwość zaznaczenia sprzętu, jego ilości oraz wybrania "Zarezerwuj"
- System udostępnia interfejs kalendarza pozwalający na wybranie okresu wypożyczenia
- Jeżeli w tym czasie sprzęt nie jest zarezerwowany system udostępnia przycisk "Potwierdź", który przekierowuje do płatności
- Po dokonaniu płatności do klienta wysyłany jest e-mail z potwierdzeniem, a do operatora wypożyczeń komunikat z informacją o nowym zamówieniu

Kryteria pozafunkcjonalne:
- System obsługuje do 1000 jednoczesnych wypożyczeń bez zmiany wydajności
- Funkcja wypożyczania jest dostępna 24/7, dopuszczalny czas niedostępności nie przekracza 1% czasu miesięcznie
- e-mail do klienta oraz komunikat do operatora wysyłane są w ciągu 10 s od dokonania płatności (w 95% przypadków)

<!--
## Jako klient chcę rezerwować sprzęt na wybrany okres, aby uniknąć sytuacji, w której na miejscu dowiem się o braku szukanego przeze mnie wyposażenia
-->

## Jako klient chcę anulować rezerwację sprzętu, aby zwolnić go dla innych osób w sytuacji zmiany zdania

Kryteria funkcjonalne:
- System wyświetla listę zarezerwowanych sprzętów posortowana według czasu do odbioru
- System umożliwia filtrowanie, wyszukiwanie i sortowanie tak samo jak w przypadku listy wypożyczonego sprzętu
- System wyświetla dodatkowe informacje o sprzęcie (nazwa, marka, opis, data odbioru, data zdania)
- Istnieje możliwość anulowania rezerwacji, środki powracają na konto Rental-aid użytkownika, do operatora wysyłany jest komunikat


Kryteria pozafunkcjonalne:
- Komunikat do operatora wysyłany jest w ciągu 10 s od anulowania (w 95% przypadków)
- Lista zarezerwowanych sprzętów jest dostępna 24/7, dopuszczalny czas niedostępności nie przekracza 1 dnia miesięcznie
 
## Jako klient chciałbym otrzymać powiadomienie z przypomnieniem o zbliżającym się terminie zwrotu sprzętu oraz w razie opóźnienia, aby nie zapomnieć o oddaniu sprzętu

Kryteria funkcjonalne:
- System wysyła do użytkownika e-mail oraz powiadomienie w aplikacji z przypomnieniem o zbliżającym się terminem oddania sprzętu
- e-mail wysyłany jest na trzy dni przed terminem zdania oraz na jeden dzień przed terminem
- System wysyła do użytkownika e-mail oraz powiadomienie w aplikacji z informacją o przekroczeniu  terminu oddania sprzętu oraz informację o dodatkowych opłatach przy zdaniu
- e-mail z informacją o przekroczeniu terminu wysyłany jest jeden dzień po przekroczeniu terminu, oraz trzy dni po przekroczeniu terminu

## Jako klient chciałbym mieć dostęp do historii wypożyczonych sprzętów, aby wiedzieć z jakich dokładnie sprzętów już korzystałem

Kryteria funkcjonalne:
- System wyświetla listę dawnych wypożyczonych sprzętów posortowaną od najnowszego do najstarszego
- System umożliwia filtrowanie, wyszukiwanie i sortowanie tak samo jak w przypadku listy wypożyczonego sprzętu
- System wyświetla nazwę, markę, opis, okres wypożyczenia sprzętu

Kryteria pozafunkcjonalne:
- Historia wypożyczonych sprzętów jest dostępna 24/7, dopuszczalny czas niedostępności nie przekracza 1 dnia miesięcznie

<!--
## Jako klient chciałbym sprawdzić jakie jest moje opóźnienie w oddaniu sprzętu oraz jakie wiążą się z tym dodatkowe opłaty, aby znać całkowite koszty wynikające z wypożyczenia
-->

# Administrator

## Jako administrator chcę definiować cenniki wypożyczeń, aby dostosować ofertę do rynku

Kryteria funkcjonalne:
- System udostępnia przegląd sprzętów z bazy danych na zasadach filtracji i sortowania dostępnych w głównym przeglądzie sprzętu
- Istnieje możliwość dodania cennika sprzętu
  - Istnieje możliwość dodania ceny za wyporzyczenie
  - Istnieje możliwość dodania ceny za każdy dzień wypożyczenia
- System udostępnia przegląd kategorii sprzętów
- Istnieje możliwość dodania cennika dla kategorii
  - Istnieje możliwość dodania ceny za wyporzyczenie
  - Istnieje możliwość dodania ceny za każdy dzień wypożyczenia
- Istnieje możliwość dodania domyślnego cennika
  - Istnieje możliwość dodania ceny za wyporzyczenie
  - Istnieje możliwość dodania ceny za każdy dzień wypożyczenia

## Jako administrator chcę edytować bazę sprzętu, aby odwzorowywała rzeczywisty stan własnościowy wypożyczalni

Kryteria funkcjonalne
- System udostępnia przegląd sprzętów z bazy danych na zasadach filtracji i sortowania dostępnych w głównym przeglądzie sprzętu
- System umożliwia zaznaczeie sprzętu i udostępnia opcję usuń
  - Można wybrać ilość usuwanego sorzętu
- System udostępnia opcję dodaj
- Formularz dodawania zawiera obligatoryjne pola:
  - nazwa
  - marka
  - kategoria
  - ilość
- Formulaż zawiera nieobligatoryjne pola:
  - cena za wyporzyczenie
  - cena za dzień wyporzyczenia
  - opis
- System zapisuje sprzęty w bazie danych po zatwierdzeniu przez administratora
- System generuje unikalne identyfikatory dla dodanych sprętów
- Jeżeli istnieje już sprzęt o danym identyfikatorze to do jego ilości dodawana jest nowa ilość
- Jeżeli obligatoryjne pole nie zostało uzupełnione, w momencie zatwierdzenia formularza system go odrzuci i wskaże nieuzupełnione pola
- Jeżeli pole zostało wypełnione niezgodnie z formatem (np. błędny zapis adresu email) system w momencie zatwierdzania odrzuci formularz i wskaże błędne dane

## Jako administrator chcę edytować bazę pracowników, aby przyznawać odpowiednie uprawnienia personelowi w dostępie do systemu

Kryteria funkcjonalne:
- System udostępnia formularz rejestracji pracownika
- Formularz rejestracji zawiera obligatoryjne pola:
  - imię
  - nazwisko
  - numer kontaktowy na telefon służbowy
  - unikalny identyfikator (PESEL/Nr innego dokumentu potwierdzającego tożsamość)
  - poziom uprawnień
- System zapisuje dane pracownika w bazie danych po zatwierdzeniu przez operatora wypożyczeń
- Przy zapisie system generuje unikalny indentyfiaktor klienta w bazie danych
- Jeżeli w bazie istnieje już pracownik o tym samym unikalnym identyfikatorze, system odrzuca zapis nowego prcownika do bazy i informuje operatora o występującym duplikacie
- Jeżeli obligatoryjne pole nie zostało uzupełnione, w momencie zatwierdzenia formularza system go odrzuci i wskaże nieuzupełnione pola
- Jeżeli pole zostało wypełnione niezgodnie z formatem (np. błędny zapis adresu email) system w momencie zatwierdzania odrzuci formularz i wskaże błędnie wypełnione pola
- Jeżeli pola zostały wypełnione poprwnie to system generuje jednorazowe hasło dla pracownika i wsyła go w SMS

## Jako administrator chcę zarządzać wysokością kar za opóźnienie w zwrocie sprzętu, aby egzekwować regulamin wypożyczalni.

Kryteria funkcjonalne:
- System udostępnia przegląd sprzętów z bazy danych na zasadach filtracji i sortowania dostępnych w głównym przeglądzie sprzętu
- Istnieje możliwość dodania reguł dotyczących opóźnień w zwrocie sprzętu
  - Istnieje możliwość dodania ceny za przekroczenie terminu zwrotu
  - Istnieje możliwość dodania ceny za każdy dzień opóźnienia
- System udostępnia przegląd kategorii sprzętów
- Istnieje możliwość dodania ogólnych reguł dotyczących opóźnień w zwrocie sprzętu
  - Istnieje możliwość dodania ceny za przekroczenie terminu zwrotu
  - Istnieje możliwość dodania ceny za każdy dzień opóźnienia
- Istnieje możliwość dodania domyślnych reguł dotyczących opóźnień w zwrocie sprzętu
  - Istnieje możliwość dodania ceny za przekroczenie terminu zwrotu
  - Istnieje możliwość dodania ceny za każdy dzień opóźnienia

## Jako administrator chcę blokować konta klientów naruszających regulamin, aby ograniczać ryzyko strat w przyszłości

Kryteria funkcjonalne:
- System udostępnia listę klientów z bazy danych
- Istnieje możliwość filtrowania oraz wyszukiwania użytkowników po danych osobowych
- Istnieje możliwość blokowania konta użytkownika, należy podać uzasadnienie oraz odnośnik do punktu regulaminu
- Do zablokowanego użytkownika wysłany zostaje e-mail,
- W aplikacji oraz podczas próby zalogowania na stronie internetowej wyświetlany jest odpowiedni komunikat

Kryteria pozafunkcjonalne:
- E-mail do użytkownika zostaje wysłany w ciągu 10 s od zablokowania konta (w 95% przypadków)

# Operator wypożyczeń

## Jako operator wypożyczeń chcę zarejestrować klienta w bazie klientów, aby móc powiązać go z wypożyczeniem sprzętu

Kryteria funkcjonalne:
- System udostępnia formularz rejestracji klienta
- Formularz rejestracji zawiera obligatoryjne pola:
  - imię
  - nazwisko
  - unikalny identyfikator (PESEL/Nr innego dokumentu potwierdzającego tożsamość)
  - numer kontaktowy
  - adres do korespondencji pocztowej
- Formularz zawiera nieobligatoryjne pola:
  - adres email
- System zapisuje dane klienta w bazie danych po zatwierdzeniu przez operatora wypożyczeń
- Przy zapisie system generuje unikalny indentyfiaktor klienta w bazie danych
- Jeżeli w bazie istnieje już klient o tym samym unikalnym identyfikatorze, system odrzuca zapis nowego klienta do bazy i informuje operatora o występującym duplikacie
- Jeżeli obligatoryjne pole nie zostało uzupełnione, w momencie zatwierdzenia formularza system go odrzuci i wskaże nieuzupełnione pola
- Jeżeli pole zostało wypełnione niezgodnie z formatem (np. błędny zapis adresu email) system w momencie zatwierdzania odrzuci formularz i wskaże błędnie wypełnione pola

Kryteria pozafunkcjonalne:
- Zapis klienta do bazy danych odbywa się w ciągu co najwyżej 2 sekund (w 95% przypadków)

### Scenariusze testowe

**Zatwierdzenie poprawnego formularza**

**Given:** Formularz jest poprawnie wypełniony

**When:** Operator zatwierdza formularz

**Then:** System generuje unikalny identyfikator klienta i zapisuje go w bazie danych

---

**Brakujące pole obligatoryjne**

**Given:** Formularz zawiera niewypełnione pole obligatoryjne

**When:** Operator zatwierdza formularz

**Then:** System odrzuca formularz i podświetla brakujące pole

---

**Niepoprawnie wypełnione pole**

**Given:** Formularz zawiera pole wypełnione niezgodnie z formatem

**When:** Operator zatwierdza formularz

**Then:** System odrzuca formularz i podświetla błędnie wypełnione pole, wystosowując komunikat dotyczący prawidłowego formatu

---

**Klient już istnieje w systemie**

**Given:** Nr dokumentu/PESEL klienta pokrywa się ze wpisem już istniejącym w bazie klientów

**When:** Operator zatwierdza formularz

**Then:** System odrzuca formularz i wskazuje na istnienie klienta w bazie

## Jako operator wypożyczeń chcę zarejestrować wydanie sprzętu klientowi, aby utrzymywać aktualny stan magazynowy i egzekwować zwrot wypożyczonego sprzętu 

Kryteria funkcjonalne:
- System udostępnia listę klientów obecnych w bazie danych
- System umożliwia dodanie klienta z listy klientów
- System udostępnia skrót do dodania nowego klienta, który automatycznie zostanie podpięty pod aktualnie rejestrowane wydanie sprzętu
- System umożliwia dodanie wyposażenia z przeglądu sprzętu do listy wszystkich wypożyczanych sprzętów w ramach danego formularza wydania
- Przegląd sprzętu jest ograniczony do sprzętów o statusie 'dostępny'
- System udostępnia możliwość wybrania okresu wypożyczenia dla każdej pozycji z listy wypożyczanych sprzętów
- Po wybraniu daty końcowej system oblicza i wyświetla liczbę dni do zwrotu liczoną od dnia wydania sprzętu
- System umożliwia wybranie sprzętu z przeglądu wyposażenia zawężonego do sprzętów dostępnych
- System udostępnia mechanizm powiadamiania magazynierów o konieczności wydania sprzętu z magazynu na stanowisko operatora wypożyczeń, wyzwalany w momencie potwierdzenia wyboru sprzętu w formularzu wydania
- System umożliwia zatwierdzenie wydania sprzętu

Kryteria pozafunkcjonalne:
- Wyszukanie sprzętu do wydania zajmuje nie dłużej niż 3 sekundy (w 95% przypadków)

### Scenariusze testowe

**Dodanie klienta**

**Given:** Formularz wydania sprzętu jest otwarty

**When:** Operator wybiera klienta z listy

**Then:** Klient jest dodawany do formularza

---

**Given:** Formularz wydania sprzętu jest otwarty

**When:** Operator dodaje nowego klienta do bazy klientów

**Then:** Klient jest dodawany do formularza

---

**Wybranie okresu wypożyczenia**

**Given:** Formularz wydania sprzętu jest otwarty

**When:** Operator wybiera datę końcową wypożyczenia

**Then:** W polu data końcowa widnieje wybrana data

---

**Zatwierdzenie wyboru sprzętów**

**Given:** Formularz wydania sprzętu jest poprawnie wypełniony

**When:** Operator zatwierdza wybór sprzętów

**Then:** System zapisuje formularz i generuje zlecenie wydania sprzętu dla magazynierów, ustawiając status sprzętu na 'zlecony do wydania operatorowi'

---

**Zatwierdzenie wydania sprzętu**

**Given:** Formularz wydania sprzętu jest poprawnie wypełniony

**When:** Operator zatwierdza wydanie sprzętu

**Then:** System zapisuje formularz i zmienia status wypożyczonych sprzętów z 'czeka na wydanie klientowi' na 'wypożyczony'

## Jako operator wypożyczeń chcę zarejestrować zwrot sprzętu przez klienta, aby uregulować ewentualne należności z tytułu uszkodzeń sprzętu lub opóźnionego zwrotu oraz uaktualnić stan magazynowy

Kryteria funkcjonalne:
- System umożliwa wyszukanie klienta w bazie danych
- System umożliwia wybranie klienta z bazy danych
- System udostępnia listę sprzętu wypożyczonego przez wybranego klienta
- System umożliwia wybranie jednego lub wielu sprzętów z listy sprzętów klienta celem przejścia do formularza zwrotu
- System umożliwia odnotowywanie zaistniałych w wyniku eksploatacji przez klienta usterek w polu tekstowym
- System automatycznie wypełnia pola 'data wypożycznia' i 'data zwrotu' zgodnie z zegarem systemowym
- System automatycznie oblicza wysokość należności z tytułu opóźnień w zwrocie sprzętu
- System pozwala odnotować, czy ewentualne należności wynikające z opóźnień są uregulowane w momencie zwrotu

Kryteria pozafunkcjonalne:
- Wyszukiwanie klienta zajmuje nie dłużej niż 3 sekundy od podania danych go indentyfikujących (w 99% przypadków)

### Scenariusze testowe

**Wybór sprzętu klienta do zwrotu**

**Given:** Lista sprzętów klienta jest otwarta

**When:** Operator wybiera zwracane wyposażenie z listy

**Then:** Otwiera się formularz zwrotu z przypisanymi doń wybranymi sprzętami klienta

---

**Odnotowanie usterek sprzętu**

**Given:** Formularz zwrotu jest otwarty

**When:** Operator wybiera sprzęt z listy zwracanych sprzętów i dodaje do niego komentarz w polu dotyczącym usterek

**Then:** System zapisuje komentarz i powraca do pierwotnego widoku formularza

---

**Odnotowanie uiszczenia należności za opóźnienie w zwrocie**

**Given:** Formularz zwrotu jest otwarty i klient zapłacił za opóźnienie

**When:** Operator wybiera opcję 'Uregulowano należności za opóźniony zwrot'

**Then:** Opcja 'Uregulowano należności za opóźniony zwrot' widnieje w formularzu jako zaznaczona

## Jako operator wypożyczeń chcę przeglądać całe wyposażenie należące do wypożyczalni

Kryteria funkcjonalne:
- System udostępnia listę sprzętu sportowego
- System pozwala na wyszukiwanie sprzętu po nazwie lub unikalnym identyfikatorze sprzętu
- System umożliwia filtrowanie sprzętu według kategorii (np. sporty zimowe, wodne itd.)
- System udostępnia następujące klasy kategorii i podkategorii:
  - tematyczne:
    - sporty zimowe
      - narciarstwo
      - snowboarding
      - ...
    - sporty wodne
      - kajakarstwo
      - wędkowanie
      - nurkowanie
      - ...
    - kolarstwo
    - ...
  - dostępność:
    - dostępny
    - wypożyczony
    - w naprawie
- System umożliwia sortowanie sprzętu wg ceny wypożycznia na jeden dzień, liczby dni eksploatacji, popularności danego modelu sprzętu
- Sytem umożliwia sortowanie wg wielu kryteriów jednocześnie, wg wybranej hierarchi priorytetowości poszczególnych kryteriów
- System umożliwia rozpoczęcie procedury rejestracji wydania sprzętu z poziomu przeglądu szczegółów konkretnego sprzętu, pod warunkiem, że sprzęt jest dostępny
- System udostępnia historię sprzętu z poziomu przeglądu szczegółów sprzętu

Kryteria pozafunkcjonalne:
- System pobiera listę sprzętu w czasie niedłużśzym niż 5 sekund (w 95% przypadków)

### Scenariusze testowe

**Sortowanie wg ceny wypożyczenia**

**Given:** Lista sprzętu jest otwarta

**When:** Operator wybiera sortowanie malejąco wg ceny wypożyczenia

**Then:** Sprzęty na liście wyświetlane są od góry do dołu w kolejności od najniższej ceny wypożyczenia do najwyższej

---

**Filtorowanie po kategorii**

**Given:** Lista sprzętu jest otwarta

**When:** Operator wybiera filtrowanie wg kategorii 'sporty zimowe'

**Then:** Na liście wyświetlane są tylko sprzęty przypisane do kategorii 'sprzęty zimowe'

---

**Wyszukiwanie po frazie**

**Given:** Lista sprzętu jest otwarta

**When:** Operator wyszukuje po frazie 'kajak'

**Then:** Na liście wyświetlane są tylko sprzęty zawierające w nazwie frazę 'kajak'

---

**Wyszukiwanie po identyfikatorze**

**Given:** Lista sprzętu jest otwarta

**When:** Operator wpisuje cały lub fragment indentyfikatora

**Then:** Na liście wyświetlane są tylko sprzęty, dla których wpisany ciąg znaków stanowi początkowy fragment identyfikatora

## Jako operator wypożyczeń chcę rozpatrywać wnioski klientów o przedłużenie wypożyczenia, aby równoważyć potrzeby klientów posiadających wypożyczony sprzęt oraz chcących wypożyczyć

Kryteria funkcjonalne:
- System powiadamia operatorów o wnioskoach klientów poprzez SMS na nr służbowy
- System na głównym ekranie aplikacji wyświetla listę wniosków klientów
- System umożliwia przejście do szczegółów wniosku
- System umożliwia zatwierdzenie lub odrzucenie wniosku
- System udostępnia opcję zatwierdzenia wniosku na krótszy niż wskazany we wniosku okres z opcjonalną zgodą klienta na skrócony okres przedłużenia (brak zgody klienta jest równoznaczny z odrzuceniem wniosku)

### Scenariusze testowe

**Rozpatrzenie wniosku o przedłużenie wypożyczenia**

**Given:** Operator wybrał wniosek do rozpatrzenia

**When:** Operator zatwierdza wniosek

**Then:** Następuje zmiana daty zwrotu o wnioskowany okres i poinformowanie klienta przez SMS oraz powiadomienie z aplikacji

---

**Given:** Operator wybrał wniosek do rozpatrzenia

**When:** Operator odrzuca wniosek

**Then:** Klient jest informowany o odrzuceniu wniosku przez SMS oraz powiadomienie z aplikacji

---

**Given:** Operator wybrał wniosek do rozpatrzenia

**When:** Operator proponuję wydłużenie o okres krótszy niż wnioskowany

**Then:** Klient otrzymuje propozycję do akceptacji lub odrzucenia

## Jako operator wypożyczeń chciałbym mieć dostęp do historii wypożyczonych sprzętów klienta, aby móc lepiej doradzić klientowi w wyborze sprzętu

Kryteria funkcjonalne:
- System udostępnia listę klientów z bazy danych
- System udostępnia przegląd sprzętów wypożyczonych w przeszłości i aktualnie przez klienta na zasadach filtracji i sortowania dostępnych w głównym przeglądzie sprzętu
- System umożliwia filtrowanie sprzętów klienta na aktualnie wypożyczone i wypożyczone w przeszłości

## Jako operator wypożyczeń chciałbym mieć dostęp do historii danego sprzętu, aby móc prowadzić statystyki dotyczące wypożyczanego sprzętu oraz klientów
Kryteria funkcjonalne:
- System udostępnia przegląd okresów wypożyczeń sprzętu i okresów napraw
- System umożliwia przegladanie szczegółów poszczególnych okresów
- Szczegółowy opis okres wypożyczenia zawiera:
  - datę początkową i końcową wypożyczenia,
  - pierwotnie planowaną datę zwrotu i wszystkie planowane daty końcowe zaistniałe w wyniku przedłużeń
  - dane klienta wypożyczającego sprzęt
  - ewentualny komentarz dotyczący usterek
  - koszt wypożyczenia (z uwzglęnieniem kosztu opoźnienia zwrotu oraz nie)
  - koszt opoźnienia w zwrocie
  - cenę wypożyczenia na jeden dzień
  - koszt opóźnienia w zwrocie o jeden dzień



# Magazynier
---
## Jako magazynier chcę móc wykonać zlecenie wydania sprzętu operatorowi wypożyczeń, aby zapewnić sprawną obsługę klientów
Kryteria funkcjonalne:
- System udostępnia listę zleceń wydania sprzętu
- System umożliwia magazynierowi wybranie zlecenia
- Wybrane zlecenie znika z listy zleceń i zmienia status na 'w realizacji'
- System umożliwia potwierdzenie wydania sprzętu operatorowi wypożyczeń
- W wyniku potwierdzenia wydania sprzęt zmienia status z 'w drodze do operatora' na 'czeka na wydanie klientowi'

Kryteria pozafunkcjonalne:
- Zmiana statusu następuje w czasie nie dłuższym niż 3 sekundy (w 99% przypadków)

### Scenariusze testowe

**Potwierdzenie wydania sprzętu operatorowi wypożyczeń**

**Given:** Magazynier przyjął zlecenie wydania sprzętu operatorowi wypożyczeń

**When:** Magazynier zatwierdził wydanie sprzętu

**Then:** Sprzęt zmienia status z 'w drodze do operatora' na 'czeka na wydanie klientowi'

## Jako magazynier chcę móc wykonać zlecenie odbioru sprzętu od operatora wypożyczeń i jego odstawienie do magazynu, aby zapewnić sprawną obsługę klientów
Kryteria funkcjonalne:
- System udostępnia listę zleceń odbioru sprzętu
- System umożliwia magazynierowi wybranie zlecenia
- Wybrane zlecenie znika z listy zleceń i zmienia status na 'w realizacji'
- Sprzęty objęte zleceniem po jego przyjęciu przez magazyniera zmieniają status z 'zlecony do odbioru' na 'czeka na wydanie magazynierowi'
- System umożliwia potwierdzenie odbioru sprzętu od operatora wypożyczeń
- W wyniku potwierdzenia odbioru sprzęt zmienia status z 'czeka na wydanie magazynierowi' na 'w drodze do magazynu'
- W wyniku potwierdzenia zwrotu sprzętu do magazynu sprzęt zmienia status z 'w drodze do magazynu' na dostępny'

Kryteria pozafunkcjonalne:
- Zmiana statusu następuje w czasie nie dłuższym niż 3 sekundy (w 99% przypadków)

### Scenariusze testowe

**Potwierdzenie odbioru sprzętu od operatora wypożyczeń**

**Given:** Magazynier przyjął zlecenie odbioru sprzętu od operatora wypożyczeń

**When:** Magazynier zatwierdził odbiór sprzętu

**Then:** Sprzęt zmienia status z 'czeka na wydanie magazynierowi' na 'w drodze do magazynu'

---

**Potwierdzenie odstawienia sprzętu do magazynu**

**Given:** Magazynier potwierdził odebranie sprzętu od operatora wypożyczeń

**When:** Magazynier zatwierdził odstawienie sprzętu do magazynu

**Then:** Sprzęt zmienia status z 'w drodze do magazynu' na 'dostępny'

## Jako magazynier chcę zgłaszać sprzęt do naprawy, aby utrzymać jego przydatność do użycia
Kryteria funkcjonalne
- System udostępnia formularz naprawy
- Formularz naprawy zawiera:
  - pole na identyfikator sprzętu
  - pole na komentarz dotyczący wymaganych napraw
- Po zatwierdzeniu formularza system oznacza sprzęt statusem 'do naprawy'
- System zapisuje czas utworzenia formularza
- W przypadku podania błędnego identyfikatora (brak sprzętu o takim id w magazynie) lub zostawienia pola komentarza pustym, system odrzuca formularz i podświetla niewłaściwie zapełnione pola

Kryteria pozafunkcjonalne:
- Zmiana statusu następuje w czasie nie dłuższym niż 3 sekundy (w 99% przypadków)

### Scenariusze testowe

**Zgłoszenie sprzętu do naprawy**

**Given:** Formularz naprawy jest otwarty i poprawnie wypełniony

**When:** Magazynier zatwierdził formularz

**Then:** Sprzęt zmienia status z 'dostępny' na 'do naprawy', system zapisuje czas utworzenia formularza

---

**Given:** Formularz naprawy jest otwarty i pole identyfikatora nie odpowiada żadnemu sprzętowi spośród tych o statusie 'dostępny'

**When:** Magazynier zatwierdził formularz

**Then:** Podświetlone zostaje pole identyfikatora ze wskazaniem, że nie ma dostępnego sprzętu o takim identyfikatorze
---

**Given:** Formularz naprawy jest otwarty i pole komentarza jest puste'

**When:** Magazynier zatwierdził formularz

**Then:** Podświetlone zostaje pole komentarza ze wskazaniem, że to pole nie może być puste


## Jako magazynier chcę móc wykonać zlecenie oddania sprzętu do naprawy, aby pomóc zachować sprawność sprzętu
Kryteria funkcjonalne:
- System udostępnia listę zleceń wydania sprzętu do naprawy
- System umożliwia magazynierowi wybranie zlecenia
- Wybrane zlecenie znika z listy zleceń i zmienia status na 'w realizacji'
- System umożliwia potwierdzenie wydania sprzętu podmiotowi realizującemu naprawę
- W wyniku potwierdzenia oddania sprzęt zmienia status z 'do naprawy' na 'w naprawie'

Kryteria pozafunkcjonalne:
- Zmiana statusu następuje w czasie nie dłuższym niż 3 sekundy (w 99% przypadków)

### Scenariusze testowe

**Potwierdzenie oddania sprzętu do naprawy**

**Given:** Magazynier przyjął zlecenie przekazania kajaka do naprawy

**When:** Magazynier potwierdził oddanie sprzętu

**Then:** Sprzęt zmienia status z 'do naprawy' na 'w naprawie'
  
## Jako magazynier chcę móc wykonać zlecenie przyjęcia sprzętu z naprawy, aby zapewnić sprawną obsługę podmiotu naprawiającego
Kryteria funkcjonalne:
- System udostępnia listę zleceń odbioru sprzętu z naprawy
- System umożliwia magazynierowi wybranie zlecenia
- Wybrane zlecenie znika z listy zleceń i zmienia status na 'w realizacji'
- System umożliwia potwierdzenie odbioru sprzętu od podmiotu realizującego naprawę
- W wyniku potwierdzenia odbioru sprzęt zmienia status z 'w naprawie' na 'dostępny'

Kryteria pozafunkcjonalne:
- Zmiana statusu następuje w czasie nie dłuższym niż 3 sekundy (w 99% przypadków)

### Scenariusze testowe

**Potwierdzenie odbioru sprzętu z naprawy**

**Given:** Magazynier przyjął zlecenie odbioru kajaka z naprawy

**When:** Magazynier zatwierdził odbiór sprzętu

**Then:** Sprzęt zmienia status z 'w naprawie' na 'dostępny'
