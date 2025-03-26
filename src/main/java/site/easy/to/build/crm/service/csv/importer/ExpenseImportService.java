package site.easy.to.build.crm.service.csv.importer;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;
import site.easy.to.build.crm.entity.Customer;
import site.easy.to.build.crm.entity.Expense;
import site.easy.to.build.crm.entity.Lead;
import site.easy.to.build.crm.entity.Ticket;
import site.easy.to.build.crm.entity.csv.mapping.ExpenseMapping;
import site.easy.to.build.crm.entity.csv.results.ImportFileCsvResult;
import site.easy.to.build.crm.enums.TypeExpense;
import site.easy.to.build.crm.exception.ImportException;
import site.easy.to.build.crm.service.csv.CSVService;
import site.easy.to.build.crm.service.csv.validation.ConstrainValidator;
import site.easy.to.build.crm.service.customer.CustomerService;

@Service
@Slf4j
public class ExpenseImportService implements ICsvImporter<Expense, ExpenseMapping> {

    @Autowired
    private CSVService<Expense, ExpenseMapping> csvParser;

    @Autowired
    private ConstrainValidator<Expense> constrainValidator;

    @Autowired
    private CustomerService customerService;

    public ImportFileCsvResult<Expense> importData(MultipartFile file) {
        ImportFileCsvResult<Expense> result = new ImportFileCsvResult<>(file);
        List<ExpenseMapping> csvRecords = csvParser.parseCSV(file, ExpenseMapping.class, ';');

        int line = 1;
        for (ExpenseMapping expenseMapping : csvRecords) {
            ImportException exception = result.getImportException(line++);
            Expense expense = new Expense();
            transfer(expenseMapping, expense, exception);
            validation(expense, exception);
            result.addData(expense);
        }
        return result;
    }

    @Override
    public void validation(Expense entity, ImportException exception) {
        constrainValidator.validateConstraint(entity, exception);
    }

    @Override
    @Transactional
    public void transfer(ExpenseMapping mapping, Expense entity, ImportException exception) {
        Customer customer = customerService.findByEmail(mapping.getCustomerEmail());
        if (customer != null) {
            entity.setCustomer(customer);
            double amount = Double.parseDouble(mapping.getExpense().replace(",", ""));
            entity.setAmount(new BigDecimal(amount));
            entity.setDateExpense(LocalDateTime.now().toLocalDate().toString());

            if (mapping.getType().toLowerCase().equals(TypeExpense.LEAD.getValue())) {
                Lead lead = new Lead();
                lead.setName(mapping.getSubjectOrName());
                lead.setStatus(mapping.getStatus());
                lead.setCustomer(customer);
                lead.setCreatedAt(LocalDateTime.now());
                
                entity.setLead(lead);
            }
            else if( mapping.getType().toLowerCase().equals(TypeExpense.TICKET.getValue())){ 
                Ticket ticket = new Ticket();
                ticket.setSubject(mapping.getSubjectOrName());
                ticket.setStatus(mapping.getStatus());
                ticket.setCustomer(customer);
                ticket.setCreatedAt(LocalDateTime.now());
                ticket.setPriority("low");
                entity.setTicket(ticket);
            }
            else {
                exception.addError("Type not found");
            }
        }
    }
}
