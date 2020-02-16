package com.ianarbuckle.tablemapserver.tables.controller

import com.ianarbuckle.tablemapserver.tables.model.Tables
import com.ianarbuckle.tablemapserver.tables.service.TablesService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

/**
 * @author ianarbuckle on 17/07/2019.
 */
@RequestMapping("/tables")
@RestController
class TablesController {

    @Autowired
    lateinit var service: TablesService

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    fun retrieveTablesById(@PathVariable id: String): Tables = service.findTablesById(id)

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    fun deleteTablesById(@PathVariable id: String): Tables = service.deleteTablesById(id)

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    fun updateTablesById(@RequestBody tables: Tables): Tables? = service.updateTables(tables)

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun saveTables(@RequestBody tables: Tables) = service.saveTables(tables)

}