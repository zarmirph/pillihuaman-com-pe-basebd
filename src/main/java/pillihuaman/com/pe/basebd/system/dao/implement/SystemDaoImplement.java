package pillihuaman.com.pe.basebd.system.dao.implement;

import com.mongodb.client.MongoCollection;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bson.Document;
import org.springframework.stereotype.Component;
import pillihuaman.com.pe.basebd.common.MyJsonWebToken;
import pillihuaman.com.pe.basebd.config.AbstractMongoRepositoryImpl;
import pillihuaman.com.pe.basebd.help.AuditEntity;
import pillihuaman.com.pe.basebd.help.Constants;
import pillihuaman.com.pe.basebd.system.System;
import pillihuaman.com.pe.basebd.system.dao.SystemDAO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class SystemDaoImplement extends AbstractMongoRepositoryImpl<System> implements SystemDAO {
    SystemDaoImplement() {
        DS_WRITE = Constants.DW;
        // DS_READ = Constants.DR;
        COLLECTION = Constants.COLLECTION_SYSTEM;
    }

    @Override
    public Class<System> provideEntityClass() {
        // TODO Auto-generated method stub
        return System.class;
    }

    protected final Log log = LogFactory.getLog(getClass());
    private final String NAME_OBJECT = "SystemDaoImplement";

    @Override
    public List<System> listSystem(int page, int pageSize, System filter) {
        MongoCollection<System> collection = getCollection(COLLECTION, System.class);
        Document query = new Document();

        try {
            // Construir el filtro según los campos presentes en la entidad `System`
            if (filter != null) {
                if (filter.getName() != null && !filter.getName().isEmpty()) {
                    query.append("name", filter.getName());
                }
                if (filter.getId() != null) {
                    query.append("_id", filter.getId());
                }
                if (filter.getContactEmail() != null && !filter.getContactEmail().isEmpty()) {
                    query.append("contactEmail", filter.getContactEmail());
                }
            }

            // Cálculo de paginación
            int skipCount = (page - 1) * pageSize;

            // Ordenar por fecha de registro (o cualquier otro criterio relevante)
            Document sort = new Document("audit.dateRegister", -1);

            // Buscar en la colección con los filtros aplicados
            List<System> systems = collection.find(query, System.class)
                    .sort(sort)
                    .skip(skipCount)
                    .limit(pageSize)
                    .into(new ArrayList<>());

            return systems;
        } catch (Exception e) {
            log.error("Error in " + NAME_OBJECT + ".listSystem: ", e);
            throw new RuntimeException("Error retrieving systems: " + e.getMessage());
        }
    }


    @Override
    public System saveSystem(System request, MyJsonWebToken jwt) {
        System system = null;
        MongoCollection<System> collection = getCollection(COLLECTION, System.class);

        try {
            if (request != null && request.getId() == null) {
                AuditEntity auditEntity = new AuditEntity();
                auditEntity.setDateRegister(new Date());
                auditEntity.setMail(jwt.getUser().getMail()); // Assuming you have email information in the token
                auditEntity.setCodUser(jwt.getUser().getId());
                request.setAudit(auditEntity);
                system = save(request);
            } else if (request != null) {
                Document query = new Document("_id", request.getId());
                Document updatedDoc = new Document();
                updatedDoc.putAll(request.toDocument()); // Implement the toDocument method in your System class

                // Set the last updated audit information
                updatedDoc.append("audit.dateUpdate", new Date());
                updatedDoc.append("audit.mail", jwt.getUser().getMail());

                // Create an update operation
                Document updateOperation = new Document("$set", updatedDoc);
                collection.updateOne(query, updateOperation);
                return systemById(request.getId());
            }
        } catch (Exception e) {
            log.error("Error in " + NAME_OBJECT + ": ", e);
        }

        return systemById(system.getId());
    }

    @Override
    public System systemById(Object id) {
        MongoCollection<System> collection = getCollection(COLLECTION, System.class);
        Document query = new Document("_id", id);

        try {
            // Busca el documento en la colección
            System system = collection.find(query, System.class)
                    .into(new ArrayList<>())
                    .stream()
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("System not found with ID: " + id));
            return system;
        } catch (Exception e) {
            log.error("Error in " + NAME_OBJECT + ".findSystemById: ", e);
            throw new RuntimeException("Error retrieving system by ID: " + e.getMessage());
        }

    }

    @Override
    public boolean deleteSystem(Object id, MyJsonWebToken jwt) {
        try {
            MongoCollection<System> collection = getCollection(COLLECTION, System.class);
            Document query = new Document("_id", id);

            // Buscar el sistema existente
            System existingSystem = collection.find(query, System.class).into(new ArrayList<>())
                    .stream()
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("System not found"));

            if (existingSystem.getId() != null) {
                // Actualizar datos de auditoría
                AuditEntity audit = existingSystem.getAudit() != null ? existingSystem.getAudit() : new AuditEntity();
                audit.setDateUpdate(new Date());
                audit.setMailUpdate(jwt.getUser().getMail());
                audit.setCodUserUpdate(jwt.getUser().getId());

                // Marcar como inactivo
                existingSystem.setActive(false);
                existingSystem.setAudit(audit);

                // Guardar los cambios
                save(existingSystem);
            }
            return true;
        } catch (Exception e) {
            log.error("Error in " + NAME_OBJECT + ".deleteSystem: ", e);
            return false;
        }
    }


}