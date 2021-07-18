# airlines-backend
Praca magisterska / Portfolio - Backend Monolit
Praca magisterska ma na celu ukazanie różnic między architekturą monolitu oraz mikroserwisów oraz zalety jednego i drugiego rozwiązania.

Aby uruchomić aplikację należy mieć pobrany IntellIJ Ultimate oraz MySQL Workbench
1. Najpierw należy utworzyć w MySQL Workbench baze danych pod nazwą: airlinesdb
2. Nastepnie utworzyc uzytkownika o nazwie: hbstudent i hasło: hbstudent
3. Ewentualnie samemu utworzyć bazę danych ale wtedy należy zmienić konfigurację w pliku application.properties (a dokładniej spring.datasource.url, spring.datasource.username 
oraz spring.datasource.password) dodatkowo nalezy dodac ?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC do url, gdyż jeżeli nie
dodamy tego kodu pojawia się bug związany z kodowaniem znaków i strefą czasową
4. Uruchamiamy aplikację w IntellIJ

Monoolit jest napisany w Javie we frameworku Spring oraz posiada bazę danych MySQL,
do której zostały najpierw wygenerowane przykładowe dane za pomocą biblioteki JavaFaker, po czym dane te zostały pobrane przez MySQL Workbench w postaci skryptu MySQL, który jest
uruchamiany za każdym razem, gdy aplikacja jest startowana (dlatego spring.jpa.hibernate.ddl-auto=create oraz spring.datasource.initialization-mode=always co powoduje usuniecie
schematu tabeli oraz ponowne jego utworzenie za kazdym uruchomieniem i zapelnienie bazy danych skryptem data.sql), gdyż jest to rozwiązanie szybsze niż za każdym generowanie
danych z pomocą JavaFaker. 
