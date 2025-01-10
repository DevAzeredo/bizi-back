package bizi.bizi.controller

import bizi.bizi.DTO.CompanyDTO
import bizi.bizi.model.Company
import bizi.bizi.model.User
import bizi.bizi.service.CompanyService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.server.ResponseStatusException
import jakarta.servlet.http.HttpServletResponse


@RestController
@RequestMapping("/api/companies")
class CompanyController(private val companyService: CompanyService) {
    private val logger: Logger = LoggerFactory.getLogger(CompanyController::class.java)

    @PostMapping
    fun createCompany(
            @AuthenticationPrincipal user: User,
            @RequestBody company: CompanyDTO
    ): Company {
        return companyService.saveCompany(company, user)
    }

    @GetMapping
    fun getCompany(@AuthenticationPrincipal user: User): Company {
        return user.company?.let { companyService.findCompany(it.id) }
                ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)
    }

    @PostMapping("/upload-logo")
    fun uploadLogo(
            @AuthenticationPrincipal user: User,
            @RequestParam("logo") file: MultipartFile
    ): Company {
        logger.info("Requisição upload logo")
        val companyId =
                user.company?.id
                        ?: throw ResponseStatusException(HttpStatus.BANDWIDTH_LIMIT_EXCEEDED)
        logger.info("achou a company")
        return companyService.updateCompanyLogo(companyId, file)
    }
    @GetMapping("/logos/{fileName}")
    fun getLogo(@PathVariable fileName: String, response: HttpServletResponse) {
        try {
            companyService.getLogo(fileName, response)
        } catch (e: Exception) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "Logo not found", e)
        }
    }

}
