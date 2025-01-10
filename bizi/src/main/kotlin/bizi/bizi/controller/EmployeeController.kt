package bizi.bizi.controller

import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.server.ResponseStatusException
import org.springframework.http.HttpStatus
import org.springframework.security.core.annotation.AuthenticationPrincipal
import bizi.bizi.service.EmployeeService
import bizi.bizi.model.User
import bizi.bizi.model.Employee
import bizi.bizi.DTO.EmployeeDTO 

@RestController
@RequestMapping("/api/employees")
class EmployeeController(private val employeeService: EmployeeService) {
    @PostMapping
    fun createEmployee(
        @AuthenticationPrincipal user: User,
        @RequestBody employee: EmployeeDTO
    ): Employee {
        return employeeService.saveEmployee(employee, user)
    }

    @GetMapping
    fun getEmployee(@AuthenticationPrincipal user: User): Employee {
        return user.employee?.let { employeeService.findEmployee(it.id) }
            ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)
    }
}