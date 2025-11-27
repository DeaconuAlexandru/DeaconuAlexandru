import tkinter as tk
from tkinter import ttk, messagebox
import pyodbc

# Conectare la SQL Server
try:
    conn = pyodbc.connect(
        'DRIVER={ODBC Driver 17 for SQL Server};'
        'SERVER=DESKTOP-1G16PA4\\MSSQLSERVER01;'
        'DATABASE=HRDatabase;'
        'Trusted_Connection=yes;'
    )
    cursor = conn.cursor()
    print("Conexiune realizată cu succes!")
except Exception as e:
    print("Eroare la conectare:", e)
    exit()

# Preluare departamente
def get_departments():
    cursor.execute("SELECT DepartmentID, DepartmentName FROM Departments")
    return cursor.fetchall()

departments_list = get_departments()
dept_dict = {dept.DepartmentID: dept.DepartmentName for dept in departments_list}

# Funcții CRUD
def show_employees():
    cursor.execute("""
        SELECT e.EmployeeID, e.FirstName, e.LastName, d.DepartmentName, e.Position, e.Salary
        FROM Employees e
        LEFT JOIN EmployeeDepartments ed ON e.EmployeeID = ed.EmployeeID
        LEFT JOIN Departments d ON ed.DepartmentID = d.DepartmentID
    """)
    rows = cursor.fetchall()

    for row in tree.get_children():
        tree.delete(row)

    # Trimitem valori simple la treeview, nu tuple
    for row in rows:
        tree.insert("", tk.END, values=[row.EmployeeID, row.FirstName, row.LastName,
                                        row.DepartmentName, row.Position, row.Salary])

def add_employee():
    if not first_name.get() or not last_name.get() or not position.get() or not salary.get() or not department_var.get():
        messagebox.showerror("Eroare", "Completează toate câmpurile!")
        return

    try:
        salary_val = float(salary.get())
    except ValueError:
        messagebox.showerror("Eroare", "Salariul trebuie să fie număr!")
        return

    try:
        # Insertăm angajatul și extragem ID-ul
        cursor.execute("""
            INSERT INTO Employees (FirstName, LastName, Position, Salary)
            OUTPUT INSERTED.EmployeeID
            VALUES (?, ?, ?, ?)
        """, first_name.get(), last_name.get(), position.get(), salary_val)

        new_id = cursor.fetchone()[0]

        # Legăm angajatul de departament
        cursor.execute("""
            INSERT INTO EmployeeDepartments (EmployeeID, DepartmentID)
            VALUES (?, ?)
        """, new_id, department_var.get())

        conn.commit()
        show_employees()

        first_name.delete(0, tk.END)
        last_name.delete(0, tk.END)
        position.delete(0, tk.END)
        salary.delete(0, tk.END)
        department_var.set("")

        messagebox.showinfo("Succes", "Angajat adăugat!")
    except Exception as e:
        messagebox.showerror("Eroare", f"Nu s-a putut adăuga: {e}")

def update_employee():
    selected = tree.focus()
    if not selected:
        messagebox.showwarning("Atenție", "Selectează un angajat!")
        return
    values = tree.item(selected, 'values')
    emp_id = int(values[0])  # ID curat, deja int

    def save_update():
        if not first_name_entry.get() or not last_name_entry.get() or not position_entry.get() or not salary_entry.get() or not dept_menu.get():
            messagebox.showerror("Eroare", "Completează toate câmpurile!")
            return
        try:
            salary_val = float(salary_entry.get())
        except ValueError:
            messagebox.showerror("Eroare", "Salariul trebuie să fie număr!")
            return
        try:
            cursor.execute(
                "UPDATE Employees SET FirstName=?, LastName=?, Position=?, Salary=? WHERE EmployeeID=?",
                first_name_entry.get(), last_name_entry.get(), position_entry.get(), salary_val, emp_id
            )

            # Găsim DepartmentID din nume
            dept_id = None
            for k, v in dept_dict.items():
                if v == dept_menu.get():
                    dept_id = k
                    break
            if dept_id is not None:
                cursor.execute(
                    "UPDATE EmployeeDepartments SET DepartmentID=? WHERE EmployeeID=?",
                    dept_id, emp_id
                )
            conn.commit()
            update_win.destroy()
            show_employees()
            messagebox.showinfo("Succes", "Angajat actualizat!")
        except Exception as e:
            messagebox.showerror("Eroare", f"Nu s-a putut actualiza: {e}")

    update_win = tk.Toplevel(root)
    update_win.title("Actualizează angajat")

    tk.Label(update_win, text="First Name").grid(row=0, column=0)
    first_name_entry = tk.Entry(update_win)
    first_name_entry.insert(0, values[1])
    first_name_entry.grid(row=0, column=1)

    tk.Label(update_win, text="Last Name").grid(row=1, column=0)
    last_name_entry = tk.Entry(update_win)
    last_name_entry.insert(0, values[2])
    last_name_entry.grid(row=1, column=1)

    tk.Label(update_win, text="Department").grid(row=2, column=0)
    dept_menu = ttk.Combobox(update_win, values=list(dept_dict.values()), state="readonly")
    dept_menu.set(values[3])
    dept_menu.grid(row=2, column=1)

    tk.Label(update_win, text="Position").grid(row=3, column=0)
    position_entry = tk.Entry(update_win)
    position_entry.insert(0, values[4])
    position_entry.grid(row=3, column=1)

    tk.Label(update_win, text="Salary").grid(row=4, column=0)
    salary_entry = tk.Entry(update_win)
    salary_entry.insert(0, values[5])
    salary_entry.grid(row=4, column=1)

    tk.Button(update_win, text="Salvează", command=save_update).grid(row=5, column=0, columnspan=2, pady=10)

def delete_employee():
    selected = tree.focus()
    if not selected:
        messagebox.showwarning("Atenție", "Selectează un angajat!")
        return
    values = tree.item(selected, 'values')
    emp_id = int(values[0])  # ID curat

    confirm = messagebox.askyesno("Șterge", f"Ștergi angajatul {values[1]} {values[2]}?")
    if confirm:
        try:
            cursor.execute("DELETE FROM EmployeeDepartments WHERE EmployeeID=?", emp_id)
            cursor.execute("DELETE FROM Employees WHERE EmployeeID=?", emp_id)
            conn.commit()
            show_employees()
            messagebox.showinfo("Succes", "Angajat șters!")
        except Exception as e:
            messagebox.showerror("Eroare", f"Nu s-a putut șterge: {e}")

# Interfață principală
root = tk.Tk()
root.title("HR Database")

# Treeview
tree = ttk.Treeview(root)
tree.pack(fill=tk.BOTH, expand=True)

cursor.execute("""
    SELECT e.EmployeeID, e.FirstName, e.LastName, d.DepartmentName, e.Position, e.Salary
    FROM Employees e
    LEFT JOIN EmployeeDepartments ed ON e.EmployeeID = ed.EmployeeID
    LEFT JOIN Departments d ON ed.DepartmentID = d.DepartmentID
""")
columns = [column[0] for column in cursor.description]
tree["columns"] = columns
tree["show"] = "headings"
for col in columns:
    tree.heading(col, text=col)
    tree.column(col, width=100)

# Frame pentru input
frame = tk.Frame(root)
frame.pack(pady=10)

first_name = tk.Entry(frame)
first_name.grid(row=0, column=1)
tk.Label(frame, text="First Name").grid(row=0, column=0)

last_name = tk.Entry(frame)
last_name.grid(row=1, column=1)
tk.Label(frame, text="Last Name").grid(row=1, column=0)

position = tk.Entry(frame)
position.grid(row=2, column=1)
tk.Label(frame, text="Position").grid(row=2, column=0)

salary = tk.Entry(frame)
salary.grid(row=3, column=1)
tk.Label(frame, text="Salary").grid(row=3, column=0)

department_var = tk.IntVar()
dept_names = [d.DepartmentName for d in departments_list]
dept_menu = ttk.Combobox(frame, values=dept_names, state="readonly")
dept_menu.grid(row=4, column=1)
tk.Label(frame, text="Department").grid(row=4, column=0)
def dept_select(event):
    for k, v in dept_dict.items():
        if v == dept_menu.get():
            department_var.set(k)
dept_menu.bind("<<ComboboxSelected>>", dept_select)

# Butoane CRUD
tk.Button(frame, text="Adaugă", command=add_employee).grid(row=5, column=0, pady=5)
tk.Button(frame, text="Actualizează", command=update_employee).grid(row=5, column=1, pady=5)
tk.Button(frame, text="Șterge", command=delete_employee).grid(row=5, column=2, pady=5)
tk.Button(frame, text="Refresh", command=show_employees).grid(row=5, column=3, pady=5)

# Încarcă datele la start
show_employees()
root.mainloop()
