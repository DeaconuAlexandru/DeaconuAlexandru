HR Database Management - Tkinter & SQL Server

Descriere:
Acesta este un proiect Python care implementează un sistem complet CRUD pentru gestionarea angajaților folosind Tkinter pentru interfața grafică și Microsoft SQL Server 2021 pentru baza de date. Proiectul permite adăugarea, vizualizarea, actualizarea și ștergerea angajaților, precum și gestionarea departamentelor acestora.

Funcționalități:

* Adaugă angajați cu nume, prenume, poziție, salariu și departament.
* Afișează toți angajații într-un tabel cu toate informațiile relevante.
* Actualizează angajați, inclusiv schimbarea departamentului.
* Șterge angajați și relațiile acestora cu departamentele.
* Include validări pentru câmpuri obligatorii și salariu numeric.
* Mesaje de confirmare și succes pentru toate operațiile.

Tehnologii folosite:

* Python 3.x
* Tkinter (GUI)
* pyodbc (conectare la Microsoft SQL Server 2021)
* Microsoft SQL Server 2021

Structura bazei de date:
Departments
| DepartmentID | DepartmentName |

Employees
| EmployeeID | FirstName | LastName | Position | Salary |

EmployeeDepartments
| EmployeeID | DepartmentID |

Conectare la baza de date:
Codul se conectează la Microsoft SQL Server 2021 folosind pyodbc astfel:
conn = pyodbc.connect(
'DRIVER={ODBC Driver 17 for SQL Server};'
'SERVER=YOUR_SERVER_NAME;'
'DATABASE=HRDatabase;'
'Trusted_Connection=yes;'
)
Acest lucru permite aplicației să comunice direct cu baza de date și să execute operații CRUD.

Licență:
MIT License (sau licența pe care o alegi tu)
