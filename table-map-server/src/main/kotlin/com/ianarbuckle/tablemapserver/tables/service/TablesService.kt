package com.ianarbuckle.tablemapserver.tables.service

import com.ianarbuckle.tablemapserver.tables.model.Tables
import com.ianarbuckle.tablemapserver.tables.repository.TablesRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * @author ianarbuckle on 17/07/2019.
 */
interface TablesService {
    fun findTablesById(id: String): Tables
    fun saveTables(tables: Tables)
    fun deleteTablesById(id: String): Tables
    fun updateTables(tables: Tables): Tables?
}

@Service
class TablesServiceImpl : TablesService {

    @Autowired
    private lateinit var repository: TablesRepository

    override fun findTablesById(id: String): Tables = repository.findTablesByRestaurantId(id)

    override fun saveTables(tables: Tables) {
        repository.save(tables)
    }

    override fun updateTables(tables: Tables): Tables? {
        return repository.save(tables).takeIf { repository.existsById(tables.restaurantId!!) }
    }

    override fun deleteTablesById(id: String): Tables {
        return repository.findTablesByRestaurantId(id).apply {
            repository.delete(this)
        }
    }
}