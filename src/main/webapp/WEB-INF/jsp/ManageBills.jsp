<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Manage Bills</title>
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
        }
        th, td {
            border: 1px solid black;
            padding: 8px;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
        .paid {
            color: green;
        }
        .unpaid {
            color: red;
        }
    </style>
</head>
<body>
    <h1>Manage Bills</h1>
    <table>
           <thead>
            <tr>
                <th>Bill ID</th>
                <th>Bill Date</th>
                <th>Patient Name</th>
                <th>Doctor Name</th>
                <th>Appointment Date</th>
                <th>Time Slot</th>
                <th>Payment Status</th>
                <th>Update Status</th>
            </tr>
        </thead>
            <c:forEach var="bill" items="${bills}">
                <tr>
                    <td>${bill.billId}</td>
                    <td>${bill.billDate}</td>
                    <td>${bill.patient.patientName}</td>
                    <td>${bill.appointment.doctor.doctorName}</td>
                    <td>${bill.appointment.appointmentDate}</td>
                    <td>${bill.appointment.timeSlot}</td>
                    <td class="${bill.paymentStatus}">${bill.paymentStatus}</td>
                    <td>
                        <form action="/admin/manageBills/updatePaymentStatus" method="post">
                            <input type="hidden" name="billId" value="${bill.billId}">
                            <button type="submit">Update to PAID</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
    </table>
</body>
</html>