# Smart Clinic Management System – Schema Design

---

## MySQL Database Design

### Table: patients
- id: INT, Primary Key, Auto Increment
- name: VARCHAR(100), NOT NULL
- email: VARCHAR(100), UNIQUE, NOT NULL
- phone: VARCHAR(15), NOT NULL
- date_of_birth: DATE
- gender: ENUM('Male', 'Female', 'Other')
- created_at: TIMESTAMP DEFAULT CURRENT_TIMESTAMP

### Table: doctors
- id: INT, Primary Key, Auto Increment
- name: VARCHAR(100), NOT NULL
- specialization: VARCHAR(100)
- email: VARCHAR(100), UNIQUE, NOT NULL
- phone: VARCHAR(15)
- available_from: TIME
- available_to: TIME
- created_at: TIMESTAMP DEFAULT CURRENT_TIMESTAMP

### Table: appointments
- id: INT, Primary Key, Auto Increment
- doctor_id: INT, Foreign Key → doctors(id)
- patient_id: INT, Foreign Key → patients(id)
- appointment_time: DATETIME, NOT NULL
- status: INT (0 = Scheduled, 1 = Completed, 2 = Cancelled)

### Table: admin
- id: INT, Primary Key, Auto Increment
- username: VARCHAR(50), UNIQUE, NOT NULL
- password: VARCHAR(255), NOT NULL
- role: ENUM('admin', 'receptionist', 'staff')
- created_at: TIMESTAMP DEFAULT CURRENT_TIMESTAMP

### Table: payments
- id: INT, Primary Key, Auto Increment
- patient_id: INT, Foreign Key → patients(id)
- amount: DECIMAL(10, 2), NOT NULL
- payment_date: DATE
- method: ENUM('Cash', 'Card', 'UPI', 'Insurance')


### Collection: prescriptions
```json
{
  "_id": "ObjectId('64abc123456')",
  "appointmentId": 51,
  "patientId": 102,
  "doctorId": 12,
  "medications": [
    {
      "name": "Paracetamol",
      "dosage": "500mg",
      "instructions": "Take 1 tablet every 6 hours"
    }
  ],
  "doctorNotes": "Monitor fever. Return if not reduced in 3 days.",
  "pharmacy": {
    "name": "CityCare Pharmacy",
    "location": "Downtown Clinic"
  },
  "createdAt": "2025-08-01T09:00:00Z"
}
