<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Patient Billing Page</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
        }
        .billing-container {
            max-width: 600px;
            margin: auto;
            padding: 20px;
            border: 1px solid #ccc;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        .billing-header {
            text-align: center;
            margin-bottom: 20px;
        }
        .billing-info {
                   }
        .billing-info p {
            margin: 5px 0;
        }
        .make-payment {
            text-align: center;
        }
        .make-payment a{
            padding: 10px 20px;
            background-color: #28a745;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
        .make-payment button:hover {
            background-color: #218838;
        }
    </style>
</head>
<body>
    <div class="billing-container">
        <div class="billing-header">
            <h2>Patient Billing Status</h2>
        </div>
        <c:forEach var="bill" items="${bills}">
            <div class="billing-info">
                <p><strong>Bill Date:</strong> <c:out value="${bill.billDate}"/></p>
                <p><strong>Total Amount:</strong> <c:out value="${bill.totalAmount}"/></p>
                <p><strong>Bill Status:</strong> <c:out value="${bill.paymentStatus}"/></p>
                <p><strong>Doctor Name:</strong> <c:out value="${bill.appointment.doctor.doctorName}"/></p>
                <p><strong>Time Slot:</strong> <c:out value="${bill.appointment.timeSlot}"/></p>
                <a href="/patient/billing/invoice?billId=${bill.billId}">View Invoice</a>
            </div>
            <c:if test="${bill.paymentStatus == 'UNPAID'}">
                <div class="make-payment">
                    <a href="/patient/billing/paybill?billId=${bill.billId}" onclick="return confirm('Are you sure you want to make Payment?');">Pay Bill</a>
                </div>
            </c:if>
        </c:forEach>
    </div>
</body>
</html>