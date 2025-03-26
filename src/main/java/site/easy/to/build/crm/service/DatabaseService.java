package site.easy.to.build.crm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Paths;



import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
    
@Service
@Transactional
@RequiredArgsConstructor
public class DatabaseService {
    
        private final JdbcTemplate jdbcTemplate;
    
        private final List<String> excludedTables = List.of(
                "customer", "customer_login_info", "oauth_users",
                "roles", "user_profile", "user_roles", "users","customer_expense_cpl","v_etat_budget",
                "lead_cpl","ticket_cpl","v_etat_budget_cpl"
        );
    
        public List<String> getAllTables() {
            return jdbcTemplate.queryForList(
                    "SELECT table_name FROM information_schema.tables WHERE table_schema = DATABASE()",
                    String.class
            );
        }
    
        public List<String> getDeletableTables() {
            List<String> tables = jdbcTemplate.queryForList(
                    "SELECT table_name FROM information_schema.tables WHERE table_schema = DATABASE()",
                    String.class
            );
    
            tables.removeAll(excludedTables);
    
            return tables;
        }
    
        @Transactional
        public void deleteAllTables() {
            jdbcTemplate.execute("SET FOREIGN_KEY_CHECKS = 0;");
    
            try {
                getDeletableTables().forEach(table -> {
                    jdbcTemplate.execute("DELETE FROM " + table + ";");
                });
            } finally {
                jdbcTemplate.execute("SET FOREIGN_KEY_CHECKS = 1;");
            }
        }
    
        @Transactional
        public void deleteTable(String tableName) {
            jdbcTemplate.execute("SET FOREIGN_KEY_CHECKS = 0;");
    
            try {
                for (String query : getDeleteQueries(tableName)) {
                    jdbcTemplate.execute(query);
                }
                jdbcTemplate.execute("DELETE FROM " + tableName + ";");
            } finally {
                jdbcTemplate.execute("SET FOREIGN_KEY_CHECKS = 1;");
            }
        }
    
        @Transactional
        public void deleteRowCascade(String tableName, String pkValue) {
            jdbcTemplate.execute("SET FOREIGN_KEY_CHECKS = 0;");
    
            try {
                for (String query : getDeleteQueriesForRow(tableName, pkValue)) {
                    jdbcTemplate.execute(query);
                }
                String pkColumn = getPrimaryKeyColumn(tableName);
                jdbcTemplate.execute("DELETE FROM " + tableName + " WHERE " + pkColumn + " = " + pkValue + ";");
            } finally {
                jdbcTemplate.execute("SET FOREIGN_KEY_CHECKS = 1;");
            }
        }
    
        public List<String> getDeleteQueries(String tableName) {
            String primaryKeyColumn = getPrimaryKeyColumn(tableName);
    
            String sql = """
                    SELECT CONCAT('DELETE FROM ', TABLE_NAME, ' WHERE ', COLUMN_NAME, 
                                  ' IN (SELECT ', ?, ' FROM %s);') AS delete_query
                    FROM INFORMATION_SCHEMA.KEY_COLUMN_USAGE
                    WHERE REFERENCED_TABLE_NAME = ? 
                      AND TABLE_SCHEMA = DATABASE()
                    """.formatted(tableName);
    
            return jdbcTemplate.queryForList(sql, new Object[]{primaryKeyColumn, tableName}, String.class);
        }
    
        private List<String> getDeleteQueriesForRow(String tableName, String primaryKeyValue) {
            String sql = """
                    SELECT CONCAT('DELETE FROM ', TABLE_NAME, ' WHERE ', COLUMN_NAME, 
                                  ' = ', ?) AS delete_query
                    FROM INFORMATION_SCHEMA.KEY_COLUMN_USAGE
                    WHERE REFERENCED_TABLE_NAME = ? 
                      AND TABLE_SCHEMA = DATABASE()
                    """;
    
            return jdbcTemplate.queryForList(sql, new Object[]{primaryKeyValue, tableName}, String.class);
        }
    
        private String getPrimaryKeyColumn(String tableName) {
            String sql = """
                    SELECT COLUMN_NAME
                    FROM INFORMATION_SCHEMA.COLUMNS 
                    WHERE TABLE_SCHEMA = DATABASE() 
                      AND TABLE_NAME = ? 
                      AND COLUMN_KEY = 'PRI'
                    """;
    
            return jdbcTemplate.queryForObject(sql, new Object[]{tableName}, String.class);
        }
    }
    