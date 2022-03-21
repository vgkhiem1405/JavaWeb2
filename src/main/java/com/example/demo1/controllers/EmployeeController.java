package com.example.demo1.controllers;

import com.example.demo1.daos.DepartmentDao;
import com.example.demo1.daos.EmployeeDao;
import com.example.demo1.daos.PositionDao;
import com.example.demo1.models.Employee;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "EmployeeController", value = {"/employees", "/employees/new", "/employees/delete", "/employees/edit", "/employees/show"})
public class EmployeeController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        String action = request.getServletPath();
        System.out.println(action);

        switch (action) {
            case "/employees":
                index(request, response);
                break;
            case "/employees/new":
                newEmployee(request, response);
                break;
            case "/employees/delete":
                deleteEmployee(request, response);
                break;
            case "/employees/edit":
                editEmployeeGet(request, response);
                break;
            case "/employees/show":
                showEmployee(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        String action = request.getServletPath();
        System.out.println(action);

        switch (action) {
            case "/employees/new":
                newEmployeePost(request, response);
                break;
            case "/employees/delete":
                deleteEmployee(request, response);
                break;
            case "/employees/edit":
                editEmployeePost(request, response);
                break;
        }
    }

    private void index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer page = request.getParameter("page") == null ? null : Integer.valueOf(request.getParameter("page"));

        List<Employee> list = EmployeeDao.getAllEmployees(page);
        request.setAttribute("list", list);
        request.setAttribute("page", page);
        request.getRequestDispatcher("/views/employees/index.jsp").forward(request, response);
    }

    private void newEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List positionList = PositionDao.getAllPositions();
        List departmentList = DepartmentDao.getAllDepartments();
        List employeeList = EmployeeDao.getAllEmployees(1);

        request.setAttribute("positionList", positionList);
        request.setAttribute("departmentList", departmentList);
        request.setAttribute("employeeList", employeeList);

        request.getRequestDispatcher("/views/employees/new.jsp").forward(request, response);
    }

    private void deleteEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        int id = Integer.parseInt(request.getParameter("id"));
        int status = EmployeeDao.delete(id);

        request.setAttribute("status", status);
        response.sendRedirect("/home/employees");
    }

    private void editEmployeePost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = Integer.valueOf(request.getParameter("id"));
        String name = request.getParameter("name");
        Integer age = request.getParameter("age").isEmpty() ? null : Integer.valueOf(request.getParameter("age"));
        String image = request.getParameter("image");
        Integer salary = request.getParameter("salary").isEmpty() ? null : Integer.valueOf(request.getParameter("salary"));
        Integer employeeId = request.getParameter("employeeId").isEmpty() ? null : Integer.valueOf(request.getParameter("employeeId"));
        Integer departmentId = request.getParameter("departmentId").isEmpty() ? null : Integer.valueOf(request.getParameter("departmentId"));
        Integer positionId = request.getParameter("positionId").isEmpty() ? null : Integer.valueOf(request.getParameter("positionId"));

        Employee emp = new Employee();
        emp.setId(id);
        emp.setName(name);
        emp.setAge(age);
        emp.setImage(image);
        emp.setSalary(salary);
        emp.setEmployeeId(employeeId);
        emp.setDepartmentId(departmentId);
        emp.setPositionId(positionId);


        int status = EmployeeDao.update(emp);
        request.setAttribute("status", status);
        request.getRequestDispatcher("/views/employees/new.jsp").forward(request, response);
    }

    private void editEmployeeGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Employee emp = EmployeeDao.getEmployeeById(id);
        List positionList = PositionDao.getAllPositions();
        List departmentList = DepartmentDao.getAllDepartments();
        List employeeList = EmployeeDao.getAllEmployees(1);

        request.setAttribute("positionList", positionList);
        request.setAttribute("departmentList", departmentList);
        request.setAttribute("emp", emp);
        request.setAttribute("employeeList", employeeList);

        request.getRequestDispatcher("/views/employees/new.jsp").forward(request, response);
    }

    private void showEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.valueOf(request.getParameter("id"));

        Employee emp = EmployeeDao.getEmployeeById(id);
        request.setAttribute("emp", emp);
        request.getRequestDispatcher("/views/employees/show.jsp").forward(request, response);
    }

    private void newEmployeePost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        Integer age = request.getParameter("age").isEmpty() ? null : Integer.valueOf(request.getParameter("age"));
        String image = request.getParameter("image");
        Integer salary = request.getParameter("salary").isEmpty() ? null : Integer.valueOf(request.getParameter("salary"));
        Integer employeeId = request.getParameter("employeeId").isEmpty() ? null : Integer.valueOf(request.getParameter("employeeId"));
        Integer departmentId = request.getParameter("departmentId").isEmpty() ? null : Integer.valueOf(request.getParameter("departmentId"));
        Integer positionId = request.getParameter("positionId").isEmpty() ? null : Integer.valueOf(request.getParameter("positionId"));

        Employee emp = new Employee();
        emp.setName(name);
        emp.setAge(age);
        emp.setImage(image);
        emp.setSalary(salary);
        emp.setEmployeeId(employeeId);
        emp.setDepartmentId(departmentId);
        emp.setPositionId(positionId);

        int status = EmployeeDao.save(emp);
        request.setAttribute("status", status);
        request.getRequestDispatcher("/views/employees.jsp").forward(request, response);
//        response.sendRedirect("/home/employees");
    }

}
