package pillihuaman.com.pe.basebd.employee.dao.implement;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.ReplaceOptions;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import pillihuaman.com.pe.basebd.common.MyJsonWebToken;
import pillihuaman.com.pe.basebd.config.AbstractMongoRepositoryImpl;
import pillihuaman.com.pe.basebd.employee.Employee;
import pillihuaman.com.pe.basebd.employee.dao.EmployeeDAO;
import pillihuaman.com.pe.basebd.help.AuditEntity;
import pillihuaman.com.pe.basebd.help.Constants;
import pillihuaman.com.pe.basebd.user.dao.UserRepository;
import pillihuaman.com.pe.lib.request.ReqEmployee;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
@Repository
public class EmployeeDaoImplement extends AbstractMongoRepositoryImpl<Employee> implements EmployeeDAO {
    EmployeeDaoImplement() {
        DS_WRITE = Constants.DW;
        // DS_READ = Constants.DR;
        COLLECTION = Constants.COLLECTION_EMPLOYEE;
    }

    private UserRepository userRepository;

    @Override
    public Class<Employee> provideEntityClass() {
        return Employee.class;
    }

    @Override
    public List<Employee> listEmployees(ReqEmployee reqEmployee) {
        MongoCollection<Employee> collection = getCollection(this.collectionName, Employee.class);

        // Construcción del filtro
        Document query = new Document();
        if (reqEmployee.getId() != null && !reqEmployee.getId().isEmpty()) {
            query.append("id", reqEmployee.getId());
        }
        if (reqEmployee.getName() != null && !reqEmployee.getName().isEmpty()) {
            query.append("name", reqEmployee.getName());
        }
        if (reqEmployee.getLastName() != null && !reqEmployee.getLastName().isEmpty()) {
            query.append("lastName", reqEmployee.getLastName());
        }
        if (reqEmployee.getDocument() != null && !reqEmployee.getDocument().isEmpty()) {
            query.append("document", reqEmployee.getDocument());
        }

        // Configuración de paginación
        int page = reqEmployee.getPage() != null && reqEmployee.getPage() > 0 ? reqEmployee.getPage() : 1;
        int pageSize = reqEmployee.getPagesize() != null && reqEmployee.getPagesize() > 0 ? reqEmployee.getPagesize() : 10;
        int skip = (page - 1) * pageSize;

        // Ejecutar la consulta con paginación
        return collection.find(query)
                .skip(skip)             // Salta los primeros "skip" documentos
                .limit(pageSize)        // Limita la cantidad de resultados
                .into(new ArrayList<>());
    }


    @Override
    public Employee saveEmployee(Employee employee, MyJsonWebToken token) {
        if (employee == null || token == null) {
            return null;
        }
        // Obtener la colección de MongoDB
        MongoCollection<Employee> collection = getCollection(this.collectionName, Employee.class);

        if (employee.getId() == null) {
            // Configurar datos de auditoría
            AuditEntity audit = new AuditEntity();
            audit.setCodUser(token.getUser().getId());
            audit.setMail(token.getUser().getMail());
            audit.setDateRegister(new Date());
            employee.setAudit(audit);
            // Insertar nuevo registro
            collection.insertOne(employee);
        } else {
            Document filter = new Document("id", employee.getId());
            Employee existingEmployee = collection.find(filter).first();
            if (existingEmployee == null) {
                throw new IllegalStateException("No existe un empleado con ID: " + employee.getId());
            }
            AuditEntity audit = new AuditEntity();
            audit.setCodUser(token.getUser().getId());
            audit.setMail(token.getUser().getMail());
            audit.setDateUpdate(new Date());
            employee.setAudit(audit);

            collection.replaceOne(filter, employee);
            System.out.println("Documento actualizado con ID: " + employee.getId());
        }

        return employee;
    }


    @Override
    public List<Employee> findByUserId(String userId) {
        MongoCollection<Employee> collection = getCollection(this.collectionName, Employee.class);
        Document query = new Document("user.id", userId);

        return collection.find(query, Employee.class)
                .into(new ArrayList<>());
    }
}
