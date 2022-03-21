<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
            crossorigin="anonymous"></script>
    <title>Employees list</title>
</head>
<body>
<nav class="navbar navbar-expand-lg  navbar-dark bg-primary">
    <div class="container-fluid">
        <a class="navbar-brand" href="/home">Home</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"
                aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="/home/employees">Employees</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" href="/home/departments">Departments</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" href="/home/positions">Positions</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link disabled">Disabled</a>
                </li>
            </ul>
        </div>
    </div>
</nav>
<div class="container mt-3">
    <h1>Employee</h1>
    <div class="card" style="width: 18rem;">
        <img src="${emp.getImage()}" class="card-img-top" alt="...">
        <div class="card-body">
            <h5 class="card-title">name: ${emp.getName()}</h5>
            <p class="card-text">descriptions:</p>
            <p class="card-text">age: ${emp.getAge()}</p>
        </div>
        <ul class="list-group list-group-flush">
            <li class="list-group-item">
                <strong>Manager: </strong>${emp.getEmployeeName()==null ? "none": emp.getEmployeeName() }</li>
            <li class="list-group-item">
                <strong>Department: </strong>${emp.getDepartmentName()==null ? "none": emp.getDepartmentName()}</li>
            <li class="list-group-item">
                <strong>Position: </strong>${emp.getPositionName()==null ? "none": emp.getPositionName()}</li>
        </ul>
        <div class="card-body">
            <td><a href="edit?id=${emp.getId()}" class="card-link">Edit</a></td>
            <td><a href="delete?id=${emp.getId()}" class="card-link"
                   onclick="return confirm('Are u sure want to delete?')">Delete</a></td>
        </div>
    </div>
    <a href="/home/employees">Back to employees</a>
</div>
</body>
</html>
