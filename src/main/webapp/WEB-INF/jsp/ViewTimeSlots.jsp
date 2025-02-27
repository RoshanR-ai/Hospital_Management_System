<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
    <title>View Slots</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
<style>
.container {
    display: flex;
    flex-direction: column;
    gap: 20px;
}

.date-box {
    border: 1px solid #ccc;
    padding: 10px;
    border-radius: 5px;
}

.slot-container {
    display: flex;
    flex-wrap: wrap;
    gap: 10px;
}

.slot-box {
    border: 1px solid #ccc;
    border-radius: 5px;
    cursor: pointer;
    transition: background-color 0.3s, color 0.3s;
}

.slot-box input[type="radio"] {
    display: none;
}

.slot-box label {
    display: block;
    padding: 5px;
    text-align: center;
}

.slot-box input[type="radio"]:checked + label {
    background-color: #007bff;
    color: #fff;
    border-radius: 5px;
}

button {
    background-color: #007bff;
    color: #fff;
    border: none;
    padding: 10px 20px;
    border-radius: 5px;
    cursor: pointer;
    font-size: 16px;
}

button:hover {
    background-color: #0056b3;
}

button:active {
    background-color: #004494;
}

</style>
</head>
<body>
    <h1>Available Slots</h1>
    <form method="post" action="/patient/bookAppointments/viewTimeSlots/selectSlot">
        <div class="container">
            <c:forEach var="entry" items="${slotMap}">
                <div class="date-box">
                    <h3>Date: ${entry.key}</h3>
                    <div class="slot-container">
                        <c:forEach var="slot" items="${entry.value}">
                            <div class="slot-box">
                                <input type="radio" id="${slot.slotId}" name="selectedSlotId" value="${slot.slotId}">
                                <label for="${slot.slotId}">${slot.slotTime}</label>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </c:forEach>
        </div>
        <button type="submit" class="book-slot">Book Slot</button>
    </form>
</body>
</html>